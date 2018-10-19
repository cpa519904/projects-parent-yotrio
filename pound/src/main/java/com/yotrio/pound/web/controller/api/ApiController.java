package com.yotrio.pound.web.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.Callback;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.common.utils.PropertiesFileUtil;
import com.yotrio.pound.exceptions.UploadLogException;
import com.yotrio.pound.model.Inspection;
import com.yotrio.pound.model.PoundInfo;
import com.yotrio.pound.model.PoundLog;
import com.yotrio.pound.model.dto.PoundLogDto;
import com.yotrio.pound.service.IDingTalkService;
import com.yotrio.pound.service.IInspectionService;
import com.yotrio.pound.service.IPoundInfoService;
import com.yotrio.pound.service.IPoundLogService;
import com.yotrio.pound.web.controller.BaseController;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    //本机url
    private static String LOCALHOST_URL = PropertiesFileUtil.getInstance("SystemParameter").get("localhost.url");
    //本机文件保存路径
    private static String FILE_LOCATION = PropertiesFileUtil.getInstance("SystemParameter").get("file.location");
    //客户端图片保存路径
    private static String CLIENT_FILE_LOCATION = PropertiesFileUtil.getInstance("SystemParameter").get("client.file.location");


    @Autowired
    private IPoundLogService poundLogService;
    @Autowired
    private IPoundInfoService poundInfoService;
    @Autowired
    private IInspectionService inspectionService;
    @Autowired
    private IDingTalkService dingTalkService;

    /**
     * 分页获取过磅记录列表
     *
     * @param dataTablePage 分页条件
     * @param poundLogDto   过磅记录查询条件
     * @return
     */
    @RequestMapping(value = "/poundLog/list", method = {RequestMethod.GET})
    @ResponseBody
    public Callback list(DataTablePage dataTablePage, PoundLogDto poundLogDto) {
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
     * @param data
     * @return
     */
    @RequestMapping(value = "/poundLog/save", method = {RequestMethod.POST})
    @ResponseBody
    public Callback savePoundLog(String data, String token) {
        Integer userId = getAppUserId(token);
//        if (userId == null) {
//            return returnError("无效的token");
//        }
        if (data == null) {
            return returnError("数据上传失败");
        }

        PoundLog poundLog;
        List<Inspection> inspections;
        // TODO: 2018-10-19 这个操作应该放在事物里面
        try {
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
//                if (poundInfo.getStatus() == PoundConstant.STATUS_STOP) {
//                    return returnError("此地磅已被停用，请联系管理员处理...");
//                }
                poundLog.setPoundName(poundInfo.getPoundName());

                //过磅记录是否已生成
                PoundLog logInDB = poundLogService.findByPoundLogNo(poundLog.getPoundLogNo());
                if (logInDB != null) {
                    return returnError("过磅单已生成，请勿重复提交");
                }

                //解析过磅图片，将base64图片字符串转成本地图片并保存图片url
                if (StringUtils.isNotEmpty(poundLog.getGrossImgUrlBase64())) {
                    String filePath = FILE_LOCATION + poundLog.getGrossImgUrl().substring(CLIENT_FILE_LOCATION.length());
//                    String grossImgUrlBase64 = ImageUtil.saveBase64Img(poundLog.getGrossImgUrlBase64(),);
//                    if (StringUtils.isNotEmpty(grossImgUrlBase64)) {
//                        poundLog.setGrossImgUrlBase64(grossImgUrlBase64);
//                    }
                }
//                if (StringUtils.isNotEmpty(poundLog.getTareImgUrl())) {
//                    String tareImgFilePath = sysProperties.getFileLocation() + poundLog.getTareImgUrl().substring(sysProperties.getLocalhostUrl().length());
//                    String tareImgUrlBase64 = ImageUtil.getImageBase64Str(tareImgFilePath);
//                    if (StringUtils.isNotEmpty(tareImgUrlBase64)) {
//                        poundLog.setTareImgUrlBase64(tareImgUrlBase64);
//                    }
//                }

                //保存过磅记录
                poundLogService.save(poundLog);
                //批量保存报检单
                inspectionService.saveList(inspections);

                //执行钉钉消息推送
//                String u9Token = getU9Token("301", "301", "001326601", "123456");
//                boolean success = dingTalkService.sendConfirmMessage(u9Token, poundLog.getId());
//                if (success) {
//                    return returnSuccess("数据上传成功，消息已推送，请查收！");
//                }
//                return returnError("消息推送失败！");
                return returnSuccess("上传成功");
            }
        } catch (UploadLogException e) {
            logger.error("上传过磅信息异常:", e.getMessage());
        }
        return returnError("数据上传失败");
    }

}