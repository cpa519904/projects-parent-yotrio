package com.yotrio.pound.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yotrio.common.utils.BeansUtil;
import com.yotrio.pound.constants.*;
import com.yotrio.pound.domain.Result;
import com.yotrio.pound.domain.SystemProperties;
import com.yotrio.pound.model.Inspection;
import com.yotrio.pound.model.PoundLog;
import com.yotrio.pound.model.Task;
import com.yotrio.pound.service.IInspectionService;
import com.yotrio.pound.service.IPoundLogService;
import com.yotrio.pound.service.ITaskService;
import com.yotrio.pound.utils.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务控制接口类
 * 模块名称：study-boot com.wangyq.controller
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-08-31 下午5:06
 * 系统版本：1.0.0
 **/

@Controller
@RequestMapping("/poundLog")
public class PoundLogController extends BaseController {

    @Autowired
    private SystemProperties sysProperties;
    @Autowired
    private IPoundLogService poundLogService;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private IInspectionService inspectionService;

    /**
     * 过磅记录列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/list.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Model model) {
        Integer poundId = sysProperties.getPoundClientId();
        model.addAttribute("poundId", poundId);
        return "pound/pound_log_list";
    }

    /**
     * 获取未处理过磅记录列表以及当前过磅单信息
     *
     * @param poundLogNo 过磅单号
     * @return
     */
    @RequestMapping(value = "/listUnFinishedAndPoundLog", method = {RequestMethod.GET})
    @ResponseBody
    public Result listUnFinishedAndPoundLog(String poundLogNo) {
        Map<String, Object> map = new HashMap<>();
        PoundLog poundLog = null;

        if (poundLogNo != null) {
            poundLog = poundLogService.findByPoundLogNo(poundLogNo);
        }
        List<PoundLog> unFinishedLogs = poundLogService.listUnFinished();

        map.put("unFinishedLogs", unFinishedLogs);
        map.put("poundLog", poundLog);
        return ResultUtil.success(map);
    }

    /**
     * 生成本地过磅记录
     *
     * @param poundLog
     * @return
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody
    public Result save(PoundLog poundLog) {
        //校验表单
        String checkResult = poundLogService.checkFormSave(poundLog);
        if (checkResult != null) {
            return ResultUtil.error(checkResult);
        }

        //请求接口获取地磅信息 注：有网络情况下校验地磅状态，网络不通情况下暂时不做此校验
        String response = HttpUtil.get(sysProperties.getPoundMasterBaseUrl() + ApiUrlConstant.GET_POUND_INFO + poundLog.getId());
        if (StringUtils.isNotEmpty(response)) {
            JSONObject object = JSON.parseObject(response);
            if (object != null && object.getIntValue("code") == 0) {
                JSONObject data = object.getJSONObject("data");
                if (data != null) {
                    //检查地磅状态
                    Integer status = data.getIntValue("status");
                    if (status == PoundConstant.STATUS_STOP) {
                        return ResultUtil.error("本台机器已停机，如需开机，请联系管理员处理...");
                    }
                    String poundName = data.getString("poundName");
                    poundLog.setPoundName(poundName);
                }
            }
        }

        //过磅记录是否已生成
//        PoundLog logInDB = poundLogService.findByDeliveryNumb(poundLog.getDeliveryNumb());
//        if (logInDB != null) {
//            return ResultUtil.error("过磅单已生成，请勿重复扫码");
//        }

        Integer result = poundLogService.save(poundLog);
        if (result >= 1) {
            return ResultUtil.success("本地存储成功");
        } else {
            return ResultUtil.error("本地存储失败");
        }
    }

    /**
     * 更新毛重过磅记录
     *
     * @param poundLog
     * @return
     */
    @RequestMapping(value = "/updateGross", method = {RequestMethod.POST})
    @ResponseBody
    public Result updateGross(PoundLog poundLog) {
        //校验表单
        if (poundLog == null) {
            return ResultUtil.error("过磅信息为空，请先填写报检单相关信息");
        }
        if (poundLog.getGrossWeight() == null) {
            return ResultUtil.error("过磅数据为空，请检查设备是否异常");
        }
        if (poundLog.getPoundLogNo() == null) {
            return ResultUtil.error("过磅单号为空，请先填写报检单相关信息");
        }
        PoundLog logInDB = poundLogService.findByPoundLogNo(poundLog.getPoundLogNo());
        if (logInDB == null) {
            return ResultUtil.error("找不到您要更新的过磅记录");
        }

        BeansUtil.copyPropertiesIgnoreNull(poundLog, logInDB);

        //计算净重 = 总皮重 - 随车退 - 样品
        double netWeight = 0.0d;
        double totalReturnWeight = 0.0d;
        double totalSampleWeight = 0.0d;
        double totalInspWeight = 0.0d;
        double tareWeight = logInDB.getTareWeight() != null ? logInDB.getTareWeight() : 0.0d;
        List<Inspection> inspectionList = inspectionService.findListByPlNo(poundLog.getPoundLogNo());
        for (Inspection inspection : inspectionList) {
            if (inspection.getReturnWeight() != null && inspection.getReturnWeight() > 0) {
                totalReturnWeight += inspection.getReturnWeight();
            }
            if (inspection.getInspWeight() != null && inspection.getInspWeight() > 0) {
                totalInspWeight += inspection.getInspWeight();
            }
            if (inspection.getTypes() == InspectionConstant.TYPE_SAMPLE) {
                if (inspection.getInspWeight() != null && inspection.getInspWeight() > 0) {
                    totalSampleWeight += inspection.getInspWeight();
                }
            }
        }
        //计算磅差和净重,有了皮重之后才可以计算
        if (logInDB.getTareWeight() != null && logInDB.getTareWeight() > 0) {
            logInDB.setDiffWeight(logInDB.getGrossWeight() - tareWeight - totalInspWeight);
            netWeight = logInDB.getGrossWeight() - tareWeight - totalSampleWeight - totalReturnWeight;
            logInDB.setNetWeight(netWeight);
        }
        if (logInDB.getGrossWeight() < tareWeight) {
            return ResultUtil.error("数据异常，皮重怎么能比毛重大呢");
        }
        if (netWeight < 0) {
            return ResultUtil.error("数据异常，请检查退货数量是否填写有误");
        }
        logInDB.setReturnWeightTotal(totalReturnWeight);
        logInDB.setFirstTime(new Date());
        if (logInDB.getStatus() < PoundLogConstant.STATUS_POUND_FIRST) {
            logInDB.setStatus(PoundLogConstant.STATUS_POUND_FIRST);
        }

        Integer result = poundLogService.update(logInDB);
        if (result < 1) {
            return ResultUtil.error("更新失败");
        }
        return ResultUtil.success(logInDB);
    }

    /**
     * 更新皮重过磅记录
     *
     * @param poundLog
     * @return
     */
    @RequestMapping(value = "/updateTare", method = {RequestMethod.POST})
    @ResponseBody
    public Result updateTare(PoundLog poundLog) {
        //校验表单
        if (poundLog == null) {
            return ResultUtil.error("过磅信息为空，请先填写报检单相关信息");
        }
        if (poundLog.getTareWeight() == null) {
            return ResultUtil.error("过磅数据为空，请检查设备是否异常");
        }
        if (poundLog.getPoundLogNo() == null) {
            return ResultUtil.error("过磅单号为空，请先填写报检单相关信息");
        }
        PoundLog logInDB = poundLogService.findByPoundLogNo(poundLog.getPoundLogNo());
        if (logInDB == null) {
            return ResultUtil.error("找不到您要更新的过磅记录");
        }

        BeansUtil.copyPropertiesIgnoreNull(poundLog, logInDB);

        //计算净重 = 总毛重 - 随车退 - 样品 - 皮重
        double netWeight = 0.0d;
        double totalReturnWeight = 0.0d;
        double totalSampleWeight = 0.0d;
        double totalInspWeight = 0.0d;
        double tareWeight = logInDB.getTareWeight() != null ? logInDB.getTareWeight() : 0.0d;

        List<Inspection> inspectionList = inspectionService.findListByPlNo(poundLog.getPoundLogNo());
        //遍历计算各种总重
        for (Inspection inspection : inspectionList) {
            if (inspection.getReturnWeight() != null && inspection.getReturnWeight() > 0) {
                totalReturnWeight += inspection.getReturnWeight();
            }
            if (inspection.getInspWeight() != null && inspection.getInspWeight() > 0) {
                totalInspWeight += inspection.getInspWeight();
            }
            if (inspection.getTypes() == InspectionConstant.TYPE_SAMPLE) {
                if (inspection.getInspWeight() != null && inspection.getInspWeight() > 0) {
                    totalSampleWeight += inspection.getInspWeight();
                }
            }
        }

        //计算磅差及净重，有了毛重之后才可以计算
        if (logInDB.getGrossWeight() != null && logInDB.getGrossWeight() > 0) {
            logInDB.setDiffWeight(logInDB.getGrossWeight() - tareWeight - totalInspWeight);
            //计算净重
            netWeight = logInDB.getGrossWeight() - totalSampleWeight - totalReturnWeight - tareWeight;
            logInDB.setNetWeight(netWeight);
        }

        if (logInDB.getGrossWeight() < tareWeight) {
            return ResultUtil.error("数据异常，皮重怎么能比毛重大呢");
        }
        if (netWeight < 0) {
            return ResultUtil.error("数据异常，请检查退货数量是否填写有误");
        }
        logInDB.setReturnWeightTotal(totalReturnWeight);
        logInDB.setSecondTime(new Date());
        logInDB.setStatus(PoundLogConstant.STATUS_POUND_SECOND);

        Integer result = poundLogService.update(logInDB);
        if (result < 1) {
            return ResultUtil.error("更新失败");
        }
        return ResultUtil.success(logInDB);
    }

    /**
     * 更新退货 皮重过磅记录
     *
     * @param poundLog
     * @return
     */
    @RequestMapping(value = "/updateReturnTare", method = {RequestMethod.POST})
    @ResponseBody
    public Result updateReturnTare(PoundLog poundLog) {
        //校验表单
        if (poundLog == null) {
            return ResultUtil.error("过磅信息为空，请先填写报检单相关信息");
        }
        if (poundLog.getTareWeight() == null) {
            return ResultUtil.error("过磅数据为空，请检查设备是否异常");
        }
        if (poundLog.getPoundLogNo() == null) {
            return ResultUtil.error("过磅单号为空");
        }
        PoundLog logInDB = poundLogService.findByPoundLogNo(poundLog.getPoundLogNo());
        if (logInDB == null) {
            //生成退货过磅单
            poundLog.setPoundId(sysProperties.getPoundClientId());
            poundLog.setPoundName(sysProperties.getPoundClientName());
            poundLog.setSecondTime(new Date());
            poundLog.setTypes(PoundLogConstant.TYPES_OUT);
            poundLog.setStatus(PoundLogConstant.STATUS_POUND_FIRST);
            int result = poundLogService.save(poundLog);
            if (result < 1) {
                return ResultUtil.error("称重失败");
            }
            return ResultUtil.success("称重成功");
        }

        return ResultUtil.error("找不到您要更新的过磅记录");
    }

    /**
     * 上传本地数据到服务器
     *
     * @return
     */
    @RequestMapping(value = "/uploadLogAndInsps", method = {RequestMethod.POST})
    @ResponseBody
    public Result uploadPoundLogAndInspections() {
        PoundLog poundLogInDB = poundLogService.findById(null);

        //是否成功上传服务器并执行消息推送
        boolean success = false;
        String msg = "上传失败";

        //上传线上服务器
        String url = sysProperties.getPoundMasterBaseUrl() + ApiUrlConstant.SAVE_POUND_LOG;
        String response = HttpUtil.post(url, BeanUtil.beanToMap(poundLogInDB));
        if (response != null) {
            JSONObject json = JSONObject.parseObject(response);
            msg = json.getString("msg");
            if (json.getIntValue("code") == SUCCESS_CODE) {
                //上传成功
                success = true;
            } else {
                success = false;
            }
        }

        //是否上传成功？失败：创建定时任务，提示失败原因；成功：提示成功
        if (!success) {
            Task task = new Task();
            task.setStatus(TaskConstant.STATUS_INIT);
//            task.setOtherId(String.valueOf(poundLog.getId()));
            task.setWeight(TaskConstant.WEIGHT_INIT);
            task.setTypes(TaskConstant.TYPE_UPDATE_MSG);
//            task.setTaskName("上传过磅记录失败|pid=" + poundLog.getId());
            task.setDescription(msg);
            taskService.save(task);
            return ResultUtil.error(msg);
        } else {
            return ResultUtil.success(msg);
        }
    }


}
