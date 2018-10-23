package com.yotrio.pound.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yotrio.common.utils.DingTalkUtil;
import com.yotrio.common.utils.PropertiesFileUtil;
import com.yotrio.pound.service.IDingTalkService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 钉钉服务层接口类
 * 模块名称：projects-parent com.yotrio.pound.service.impl
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-10-09 14:10
 * 系统版本：1.0.0
 **/

@Service("dingTaskService")
public class DingTalkServiceImpl implements IDingTalkService {

    private static final int SUCCESS_CODE = 0;

    private static final String SINGLE_URL_CONFIRM_ORDER = PropertiesFileUtil.getInstance("SystemParameter").get("url.touch.confirm.order");


    /**
     * 发送钉钉工作消息 类型：action_card
     *
     * @param token
     * @param poundLogId
     * @return
     */
    @Override
    public boolean sendConfirmMessage(String token, Integer poundLogId) {
        //获取accessToken
        String accessToken = DingTalkUtil.getAccessToken();
        // TODO: 2018-10-10 获取用户ids,这里需要修改
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
        //编辑消息内容
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
        actionCard.put("single_url", SINGLE_URL_CONFIRM_ORDER + "?token=" + token + "&poundLogId=" + poundLogId);
        msg.put("action_card", actionCard);

        //发送消息
        JSONObject jsonObject = DingTalkUtil.sendWorkMessage(userIdList, null, false, msg);
        if (jsonObject != null) {
            int code = jsonObject.getIntValue("code");
            if (code == SUCCESS_CODE) {
                return true;
            }
        }
        return false;
    }
}