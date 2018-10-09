package com.yotrio.pound.web.controller.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.Callback;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.common.utils.DingTalkUtil;
import com.yotrio.pound.constants.PoundConstant;
import com.yotrio.pound.model.PoundInfo;
import com.yotrio.pound.model.PoundLog;
import com.yotrio.pound.model.dto.PoundLogDto;
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

import java.util.ArrayList;
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

    @Autowired
    private IPoundLogService poundLogService;
    @Autowired
    private IPoundInfoService poundInfoService;

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
     * 存储过磅记录
     *
     * @param poundLog
     * @return
     */
    @RequestMapping(value = "/poundLog/save", method = {RequestMethod.POST})
    @ResponseBody
    public Callback savePoundLog(PoundLog poundLog) {
        //校验表单
        String checkResult = poundLogService.checkFormSave(poundLog);
        if (checkResult != null) {
            return returnError(checkResult);
        }

        //校验地磅状态
        PoundInfo poundInfo = poundInfoService.findById(poundLog.getId());
        if (poundInfo == null) {
            return returnError("找不到对应的地磅信息");
        }
        if (poundInfo.getStatus() == PoundConstant.STATUS_STOP) {
            return returnError("此地磅已被停用，请联系管理员处理...");
        }

        //过磅记录是否已生成
        PoundLog logInDB = poundLogService.findByDeliveryNumb(poundLog.getDeliveryNumb());
        if (logInDB != null) {
            return returnError("过磅单已生成，请勿重复扫码");
        }

        Integer result = poundLogService.save(poundLog);
        if (result < 1) {
            logger.info("存储失败|poundId:{}", poundInfo.getId());
            return returnError("存储失败|poundId:" + poundInfo.getId());
        }

        //执行钉钉消息推送
        //获取accessToken
        String accessToken = DingTalkUtil.getAccessToken();
        //获取用户id
        JSONObject json = DingTalkUtil.getAuthScopes(accessToken);
        JSONObject authOrgScopes = json.getJSONObject("auth_org_scopes");
        JSONArray authed_user = authOrgScopes.getJSONArray("authed_user");
        List<String> userIds = new ArrayList<>();
        for (int i = 0; i < authed_user.size(); i++) {
            String userId = String.valueOf(authed_user.get(i));
            if (StringUtils.isNotEmpty(userId)) {
                userIds.add(userId);
            }
        }
        String userIdList = StringUtils.join(userIds, ",");

        JSONObject msg = new JSONObject();
        msg.put("msg_type", "action_card");
        JSONObject actionCard = new JSONObject();
        actionCard.put("title", "确认过磅信息");
        StringBuilder builder = new StringBuilder();
        builder.append("# 审批\n");
        builder.append("#### 请确认过磅信息是否有误\n");
        builder.append("> A man who stands for nothing will fall for anything. \n");
        actionCard.put("markdown", builder.toString());
        actionCard.put("single_title", "查看详情");
        actionCard.put("single_url", "http://client.wangyq.site:8010/#/");
        msg.put("action_card", actionCard);

        JSONObject jsonObject = DingTalkUtil.sendWorkMessage(userIdList, null, false, msg);

        return returnSuccess("更新成功，消息已推送，请查收确认！");
    }

    /**
     * 更新供货单
     *
     * @param token
     * @return
     */
    @RequestMapping(value = "/poundLog/update", method = {RequestMethod.POST})
    @ResponseBody
    public Callback updateDeliveryOrder(String token, Integer deliveryNumb) {


        return returnSuccess("更新成功，消息已推送，请查收确认！");
    }
}