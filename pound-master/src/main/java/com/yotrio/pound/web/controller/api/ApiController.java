package com.yotrio.pound.web.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yotrio.common.constants.PoundConstant;
import com.yotrio.common.constants.PoundLogConstant;
import com.yotrio.common.constants.TaskConstant;
import com.yotrio.common.domain.Callback;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.common.enums.GoodsKindEnum;
import com.yotrio.common.helpers.UserAuthTokenHelper;
import com.yotrio.pound.exceptions.UploadLogException;
import com.yotrio.pound.model.Inspection;
import com.yotrio.pound.model.PoundInfo;
import com.yotrio.pound.model.PoundLog;
import com.yotrio.pound.model.Task;
import com.yotrio.pound.model.dto.PoundLogDto;
import com.yotrio.pound.service.*;
import com.yotrio.pound.web.controller.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 外部接口控制类
 * 模块名称：projects-parent com.yotrio.pound.web.controller
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-09-25 9:56
 * 系统版本：1.0.0
 **/
@Controller
@RequestMapping("/api")
public class ApiController extends BaseController {


    @Autowired
    private IPoundLogService poundLogService;
    @Autowired
    private IPoundInfoService poundInfoService;
    @Autowired
    private IInspectionService inspectionService;
    @Autowired
    private IDingTalkService dingTalkService;
    @Autowired
    private IHttpService httpService;
    @Autowired
    private ITaskService taskService;

    /**
     * 分页获取过磅记录列表
     *
     * @param dataTablePage 分页条件
     * @param poundLogDto   过磅记录查询条件
     * @param token         认证秘钥
     * @return
     */
    @RequestMapping(value = "/poundLog/list", method = {RequestMethod.GET})
    @ResponseBody
    public Callback list(DataTablePage dataTablePage, PoundLogDto poundLogDto, String token) {
        if (poundLogDto == null || poundLogDto.getPoundId() == null) {
            return returnError("地磅参数异常");
        }
        if (StringUtils.isEmpty(token)) {
            return returnError("token丢失");
        }
        PoundInfo poundInfo = poundInfoService.findById(poundLogDto.getPoundId());
        if (poundInfo == null) {
            return returnError("获取地磅信息失败");
        }
        Integer userId = getAppUserId(token);
        if (!poundInfo.getAdminEmpId().equals(userId)) {
            return returnError("token校验失败");
        }
        PageInfo pageInfo = poundLogService.findAllPaging(dataTablePage, poundLogDto);
        return returnSuccess(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 根据id获取地磅信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/pound/info/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public Callback info(@PathVariable(value = "id") Integer id) {
        if (id == null) {
            return returnError("地磅ID不能为空");
        }
        PoundInfo poundInfo = poundInfoService.findById(id);
        return returnSuccess(poundInfo);
    }

    /**
     * 存储过磅记录,推送钉钉消息
     *
     * @return
     */
    @RequestMapping(value = "/poundLog/save", method = {RequestMethod.POST})
    @ResponseBody
    public Callback savePoundLog(String token, String data) {

        PoundLog poundLog;
        List<Inspection> inspections;
        try {
            //字符串格式转换，防止中文乱码，这里很奇怪，用httpUtil上传整合shiro后就会出现中文乱码问题,暂未有更好解决办法
            data = new String(data.getBytes("iso-8859-1"), "utf-8");
            token = new String(token.getBytes("iso-8859-1"), "utf-8");
            Integer userId = getAppUserId(token);
            if (userId == null) {
                return returnError("无效的token");
            }
            if (data == null) {
                return returnError("数据上传失败");
            }
            JSONObject root = JSON.parseObject(data);
            JSONObject poundLogObj = root.getJSONObject("poundLog");
            JSONArray inspectionsArr = root.getJSONArray("inspections");
            poundLog = JSON.parseObject(JSONObject.toJSONString(poundLogObj), PoundLog.class);
            inspections = JSONArray.parseArray(JSONArray.toJSONString(inspectionsArr), Inspection.class);

            if (poundLog != null && inspections != null) {
                //校验地磅状态
                PoundInfo poundInfo = poundInfoService.findById(poundLog.getPoundId());
                if (poundInfo == null) {
                    return returnError("找不到对应的地磅信息");
                }
                if (!poundInfo.getAdminEmpId().equals(userId)) {
                    return returnError("token验证失败");
                }
                if (poundInfo.getStatus() == PoundConstant.STATUS_STOP) {
                    return returnError("此地磅已被停用，请联系管理员处理...");
                }
                poundLog.setPoundName(poundInfo.getPoundName());

                //交给事务区处理并保存过磅单以及报检单
                poundLogService.savePoundLogAndInspections(poundLog, inspections);

                //进货有报检单的情况下，发送钉钉消息并更新进货单
                if (poundLog.getTypes() == PoundLogConstant.TYPES_IN && inspections != null && inspections.size() > 0) {
                    //是否发送成功
                    boolean sendResult = false;
                    //查询发货单关联U9收货单信息，1.没生成发货单，创建定时任务，定时执行；2.生成发货单，直接钉钉推送消息
                    for (Inspection inspection : inspections) {
                        JSONObject u9ReceiveInfo = httpService.getU9ReceiveInfo(inspection.getInspNo());
                        if (u9ReceiveInfo != null) {
                            List<String> userIds = new ArrayList<>(20);
                            //通过员工工号获取钉钉用户id
                            String dingUserId = dingTalkService.getDingTalkUserIdByEmpId(poundInfo.getAdminEmpId());
                            userIds.add(dingUserId);
                            String userIdList = StringUtils.join(userIds, ",");
                            sendResult = dingTalkService.sendConfirmMessage(token, poundLog.getId(), userIdList);
                            //成功发送一次就够了
                            if (sendResult) {
                                break;
                            }
                        } else {
                            //有一种收货单未生成，就先退出发送消息
                            break;
                        }
                    }

                    if (!sendResult) {
                        //发送失败，创建任务，定时去执行发送
                        Task task = new Task();
                        task.setStatus(TaskConstant.STATUS_INIT);
                        task.setOtherId(poundLog.getId().toString());
                        task.setTaskName("发送钉钉确认消息");
                        task.setTypes(TaskConstant.TYPE_SEND_DING_TALK_CONFIRM_MSG);
                        task.setWeight(TaskConstant.WEIGHT_INIT);
                        StringBuffer sb = new StringBuffer();
                        sb.append("过磅记录ID：").append(poundLog.getId()).append("|地磅ID：").append(poundInfo.getId()).append("|管理员工号：").append(poundInfo
                                .getAdminEmpId());
                        task.setDescription(sb.toString());
                        taskService.save(task);
                    }
                }

                return returnSuccess("上传成功");
            }
        } catch (UploadLogException e) {
            logger.error("上传过磅信息异常:", e.getMessage());
        } catch (UnsupportedEncodingException e) {
            logger.error("上传过磅信息异常:", e.getMessage());
        }
        return returnError("数据上传失败");
    }

    /**
     * 更新u9收货单
     *
     * @param token 校验token
     * @param plId  过磅记录id
     * @return
     */
    @RequestMapping(value = "/update/receiveInfo", method = {RequestMethod.GET})
    @ResponseBody
    public Callback updateReceiveInfo(String token, Integer plId) {
        Integer userId = UserAuthTokenHelper.getAppUserId(token);
        if (userId == null) {
            return returnError("token验证失败");
        }
        if (plId == null) {
            return returnError("过磅单号为空");
        }
        PoundLog poundLog = poundLogService.findById(plId);
        if (poundLog == null) {
            return returnError("获取过磅单失败");
        }
        List<Inspection> inspections = inspectionService.findListByPlId(poundLog.getId());
        if (inspections == null || inspections.size() <= 0) {
            return returnError("获取报检单失败");
        }

        JSONArray inspectionArr = new JSONArray();
        for (Inspection inspection : inspections) {
            JSONObject obj = new JSONObject();
            obj.put("DeliveryNo", inspection.getInspNo());
            obj.put("WeightValue", inspection.getInspNetWeight());
            inspectionArr.add(obj);
        }
        //封装信息
        Map<String, Object> paramsMap = new HashMap<>(10);
        paramsMap.put("poundLogNo", poundLog.getPoundLogNo());
        paramsMap.put("inspectJsonArr", inspectionArr.toJSONString());
        paramsMap.put("remark", poundLog.getRemark());
        httpService.writeWeightToU9ReceiveInfo(paramsMap);
        return returnSuccess("操作成功!");
    }

    /**
     * 获取确认页面信息
     *
     * @param token 校验token
     * @param plId  过磅记录id
     * @return
     */
    @RequestMapping(value = "/getConfirmMessage", method = {RequestMethod.GET})
    @ResponseBody
    public Callback getConfirmMessage(String token, Integer plId) {
        logger.error("进入控制类");
        Integer userId = UserAuthTokenHelper.getAppUserId(token);
        if (userId == null) {
            return returnError("token验证失败");
        }
        if (plId == null) {
            return returnError("过磅单号为空");
        }
        PoundLog poundLog = poundLogService.findById(plId);
        if (poundLog == null) {
            return returnError("获取过磅单失败");
        }
        if (poundLog.getGoodsKind() != null) {
            poundLog.setGoodsName(GoodsKindEnum.getKindName(poundLog.getGoodsKind()));
        }
        List<Inspection> inspections = inspectionService.findListByPlId(poundLog.getId());
        poundLog.setInspections(inspections);

        return returnSuccess(poundLog);
    }


}