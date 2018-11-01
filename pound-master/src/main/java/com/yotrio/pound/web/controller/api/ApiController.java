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
import com.yotrio.common.helpers.UserAuthTokenHelper;
import com.yotrio.common.utils.DateUtil;
import com.yotrio.common.utils.ImageUtil;
import com.yotrio.common.utils.PropertiesFileUtil;
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
    /**
     * 本机url
     */
    private static String LOCALHOST_URL = PropertiesFileUtil.getInstance("SystemParameter").get("localhost.url");
    /**
     * 本机文件保存路径
     */
    private static String FILE_LOCATION = PropertiesFileUtil.getInstance("SystemParameter").get("file.location");

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
    public Callback savePoundLog(String token,String data) {

        PoundLog poundLog;
        List<Inspection> inspections;
        // TODO: 2018-10-19 这个操作应该放在事物里面
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

                if (poundLog.getInspWeightTotal() == null) {
                    //过磅单总数
                    double inspWeightTotal = 0.0d;
                    for (Inspection inspection : inspections) {
                        inspWeightTotal += inspection.getInspWeight();
                        if (StringUtils.isEmpty(poundLog.getCompName())) {
                            poundLog.setCompName(inspection.getCompName());
                        }
                        if (poundLog.getGoodsKind() == null) {
                            poundLog.setGoodsKind(inspection.getGoodsKind());
                        }
                    }
                    poundLog.setInspWeightTotal(inspWeightTotal);
                }

                //构建图片存储路径
                StringBuilder filePath = new StringBuilder();
                String basePath = DateUtil.getFilePathByDate(FILE_LOCATION + "/images/upload/");
                filePath.append(basePath).append(poundLog.getPoundLogNo()).append("/");

                //解析过磅图片，将base64图片字符串转成本地图片并保存图片url
                if (StringUtils.isNotEmpty(poundLog.getGrossImgUrlBase64())) {
                    //生成上传图片名
                    String fileName = System.currentTimeMillis() + ".jpg";
                    String imgLocalPath = ImageUtil.saveBase64Img(poundLog.getGrossImgUrlBase64(), filePath.toString(), fileName);
                    if (StringUtils.isNotEmpty(imgLocalPath)) {
                        //转换成http图片地址
                        String imgUrl = LOCALHOST_URL + imgLocalPath.substring(FILE_LOCATION.length());
                        poundLog.setGrossImgUrl(imgUrl);
                    }
                }
                if (StringUtils.isNotEmpty(poundLog.getTareImgUrlBase64())) {
                    //生成上传图片名
                    String fileName = System.currentTimeMillis() + ".jpg";
                    String imgLocalPath = ImageUtil.saveBase64Img(poundLog.getGrossImgUrlBase64(), filePath.toString(), fileName);
                    if (StringUtils.isNotEmpty(imgLocalPath)) {
                        //转换成http图片地址
                        String imgUrl = LOCALHOST_URL + imgLocalPath.substring(FILE_LOCATION.length());
                        poundLog.setTareImgUrl(imgUrl);
                    }
                }

                //过磅记录是否已生成
                PoundLog logInDB = poundLogService.findByPoundLogNoAndPoundId(poundLog.getPoundLogNo(), poundLog.getPoundId());
                if (logInDB != null) {
                    //已生成,执行更新操作
                    poundLogService.updateByPlNoAndPoundId(poundLog);
                    for (Inspection inspection : inspections) {
                        inspection.setPlId(logInDB.getId());
                        inspection.setPlNo(logInDB.getPoundLogNo());
                        inspectionService.updateByPlIdSelective(inspection);
                    }
                } else {
                    //未生成,执行插入操作
                    poundLog.setId(null);
                    poundLogService.save(poundLog);
                    //保存报检单信息
                    for (Inspection inspection : inspections) {
                        inspection.setId(null);
                        inspection.setPlId(poundLog.getId());
                        inspection.setPlNo(poundLog.getPoundLogNo());
                        inspectionService.save(inspection);
                    }
                }

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
                        sb.append("过磅记录ID：").append(poundLog.getId()).append("|地磅ID：").append(poundInfo.getId()).append("|管理员工号：").append(poundInfo.getAdminEmpId());
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

    @RequestMapping(value = "/update/receiveInfo", method = {RequestMethod.GET})
    @ResponseBody
    public Callback updateReceiveInfo(String token, Integer poundLogId) {
        Integer userId = UserAuthTokenHelper.getAppUserId(token);
        if (userId == null) {
            return returnError("token验证失败");
        }
        if (poundLogId == null) {
            return returnError("过磅单号为空");
        }
        PoundLog poundLog = poundLogService.findById(poundLogId);
        if (poundLog == null) {
            return returnError("获取过磅单失败");
        }
        List<Inspection> inspections = inspectionService.findListByPlId(poundLog.getId());
        if (inspections == null || inspections.size() <= 0) {
            return returnError("获取报价单失败");
        }
        for (Inspection inspection : inspections) {
            Map<String, Object> paramsMap = new HashMap<>(10);
            paramsMap.put("poundLogNo", poundLog.getPoundLogNo());
            paramsMap.put("inspNetWeight", inspection.getInspNetWeight());
            paramsMap.put("inspNo", inspection.getInspNo());
            paramsMap.put("remark", poundLog.getRemark());
            httpService.writeWeightToU9ReceiveInfo(paramsMap);
        }
        return returnSuccess("操作成功");
    }

}