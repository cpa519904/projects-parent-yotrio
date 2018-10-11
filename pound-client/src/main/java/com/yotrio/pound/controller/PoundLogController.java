package com.yotrio.pound.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yotrio.common.utils.BeansUtil;
import com.yotrio.pound.constants.ApiUrlConstant;
import com.yotrio.pound.constants.PoundConstant;
import com.yotrio.pound.constants.TaskConstant;
import com.yotrio.pound.domain.Result;
import com.yotrio.pound.domain.SystemProperties;
import com.yotrio.pound.model.PoundLog;
import com.yotrio.pound.model.Task;
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
        PoundLog logInDB = poundLogService.findByDeliveryNumb(poundLog.getDeliveryNumb());
        if (logInDB != null) {
            return ResultUtil.error("过磅单已生成，请勿重复扫码");
        }

        Integer result = poundLogService.save(poundLog);
        if (result >= 1) {
            return ResultUtil.success("本地存储成功");
        } else {
            return ResultUtil.error("本地存储失败");
        }
    }

    /**
     * 更新过磅记录
     *
     * @param poundLog
     * @return
     */
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ResponseBody
    public Result update(PoundLog poundLog) {
        //校验表单
        String checkResult = poundLogService.checkFormSave(poundLog);
        if (checkResult != null) {
            return ResultUtil.error(checkResult);
        }

        PoundLog logInDB = poundLogService.findById(poundLog.getId());
        if (logInDB == null) {
            return ResultUtil.error("找不到您要更新的过磅记录");
        }
        BeansUtil.copyPropertiesIgnoreNull(poundLog,logInDB);

        Integer result = poundLogService.update(logInDB);
        if (result < 1) {
            return ResultUtil.error("本地更新失败");
        }
        PoundLog poundLogInDB = poundLogService.findById(poundLog.getId());

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
            task.setOtherId(String.valueOf(poundLog.getId()));
            task.setWeight(TaskConstant.WEIGHT_INIT);
            task.setTypes(TaskConstant.TYPE_UPDATE_MSG);
            task.setTaskName("上传过磅记录失败|pid=" + poundLog.getId());
            task.setDescription(msg);
            taskService.save(task);
            return ResultUtil.error(msg);
        } else {
            return ResultUtil.success(msg);
        }
    }
}
