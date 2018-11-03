package com.yotrio.pound.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.yotrio.common.constants.ApiUrlConstant;
import com.yotrio.common.utils.DingTalkUtil;
import com.yotrio.common.utils.PropertiesFileUtil;
import com.yotrio.pound.service.IDingTalkService;
import com.yotrio.pound.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 成功返回码
     */
    private static final int SUCCESS_CODE = 0;
    /**
     * 成功返回码
     */
    private static final String SUCCESS_RESULT = "1";
    /**
     * 成功返回码
     */
    private static final String RESULT_KEY = "result";
    /**
     * 确认供货单页面url
     */
    private static final String SINGLE_URL_CONFIRM_ORDER = PropertiesFileUtil.getInstance("SystemParameter").get("url.touch.confirm.order");

    /**
     * 获取钉钉access_token
     */
    private static final String GET_DING_TALK_ACCESS_TOKEN_URL = PropertiesFileUtil.getInstance("SystemParameter").get("url.ding.talk") + ApiUrlConstant.GET_DING_TALK_ACCESS_TOKEN;

    /**
     * 获取丁丁用户id
     */
    private static final String GET_DING_TALK_USER_ID_URL = PropertiesFileUtil.getInstance("SystemParameter").get("url.ding.talk") + ApiUrlConstant.GET_DING_TALK_USERID;

    /**
     * 通过发送钉钉工作消息 类型：action_card
     *
     * @param token
     * @param poundLogId
     * @return
     */
    @Override
    public boolean sendConfirmMessage(String token, Integer poundLogId, String userIds) {

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
        actionCard.put("single_url", SINGLE_URL_CONFIRM_ORDER + "?token=" + token + "&plId=" + poundLogId);
        msg.put("action_card", actionCard);

        //发送消息
        JSONObject jsonObject = DingTalkUtil.sendWorkMessage(userIds, null, false, msg);
        if (jsonObject != null) {
            int code = jsonObject.getIntValue("code");
            if (code == SUCCESS_CODE) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取钉钉的accessToken
     *
     * @return
     */
    @Override
    public String getCacheAccessToken() {
        String key = "DingTalkAccessToken";
        String accessToken = RedisUtil.get(key);
        try {
            if (StringUtils.isEmpty(accessToken)) {
                String response = HttpUtil.get(GET_DING_TALK_ACCESS_TOKEN_URL);
                if (StringUtils.isNotEmpty(response)) {
                    JSONObject json = JSONObject.parseObject(response);
                    if (json.getString(RESULT_KEY) == SUCCESS_RESULT) {
                        JSONObject data = json.getJSONObject("response");
                        accessToken = data.getString("access_token");
                        if (StringUtils.isNotEmpty(accessToken)) {
                            RedisUtil.set(key, accessToken, 60 * 60);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("获取U9token失败={}", e);
        }

        return accessToken;
    }

    /**
     * 根据员工工号获取钉钉userId
     *
     * @return
     */
    @Override
    public String getCacheDingTalkUserIdByEmpId(Integer empId) {
        String key = "DingTalkUserId:EmpId:" + empId;
        String userId = RedisUtil.get(key);
        try {
            if (StringUtils.isEmpty(userId)) {
                HashMap<String, Object> paramMap = new HashMap<>(5);
                paramMap.put("type", "jobNumber");
                paramMap.put("user", empId);

                String response = HttpUtil.get(GET_DING_TALK_USER_ID_URL);
                if (StringUtils.isNotEmpty(response)) {
                    JSONObject json = JSONObject.parseObject(response);
                    if (json.getString(RESULT_KEY) == SUCCESS_RESULT) {
                        JSONObject data = json.getJSONObject("response");
                        userId = data.getString("userId");
                        if (StringUtils.isNotEmpty(userId)) {
                            RedisUtil.set(key, userId, 60 * 60);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("获取用户ID失败={}", e);
        }

        return userId;
    }

    /**
     * 根据员工手机号码获取钉钉userId
     *
     * @return
     */
    @Override
    public String getCacheDingTalkUserIdByMobile(String mobile) {
        String key = "DingTalkUserId:mobile:" + mobile;
        String userId = RedisUtil.get(key);
        try {
            if (StringUtils.isEmpty(userId)) {
                HashMap<String, Object> paramMap = new HashMap<>(5);
                paramMap.put("type", "mobile");
                paramMap.put("user", mobile);

                String response = HttpUtil.get(GET_DING_TALK_USER_ID_URL);
                if (StringUtils.isNotEmpty(response)) {
                    JSONObject json = JSONObject.parseObject(response);
                    if (json.getString(RESULT_KEY) == SUCCESS_RESULT) {
                        JSONObject data = json.getJSONObject("response");
                        userId = data.getString("userId");
                        if (StringUtils.isNotEmpty(userId)) {
                            RedisUtil.set(key, userId, 60 * 60);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("获取用户ID失败={}", e);
        }

        return userId;
    }
}