package com.yotrio.pound;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yotrio.common.utils.DingTalkUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 模块名称：projects-parent com.yotrio.pound
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-10-09 14:18
 * 系统版本：1.0.0
 **/

public class MainTest {
    private static final Logger logger = LoggerFactory.getLogger(MainTest.class);

    public static void main(String[] args) {
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
        actionCard.put("single_url", "http://47.98.181.17:8080/#/confirmOrder");
        msg.put("action_card", actionCard);

        JSONObject jsonObject = DingTalkUtil.sendWorkMessage(userIdList, null, false, msg);
        JSONObject sendResult = DingTalkUtil.getSendResult(jsonObject.getLong("task_id"));

        logger.debug("scopesResponse==" + json);
        logger.debug("userIdList==" + userIdList);
        logger.debug("response==" + jsonObject);
        logger.debug("sendResult==" + sendResult);
    }
}