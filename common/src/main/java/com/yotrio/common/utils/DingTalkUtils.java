package com.yotrio.common.utils;

import cn.hutool.json.JSONUtil;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkConstants;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.taobao.api.ApiException;
import com.yotrio.common.constants.DingTalkUrlConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 钉钉开发工具类
 * 模块名称：projects-parent com.yotrio.common.utils
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-09-18 上午11:29
 * 系统版本：1.0.0
 **/

public class DingTalkUtils {
    private static final Logger logger = LoggerFactory.getLogger(DingTalkUtils.class);

    /**
     * 钉钉企业号appKey
     */
    private static final String APP_KEY = "dingyzwm1i5jahdt44av";

    /**
     * 钉钉企业号appSecret
     */
    private static final String APP_SECRET = "kHVowHEs9WntGi06RSbq1fmyAZUa-1CiYKmeKRArEk-_W7L_yTw4J71kGPvZyq5O";

    /**
     * 钉钉企业号agent_id
     */
    private static final long AGENT_ID = 193238118L;

    /**
     * 授权方corpid
     */
    private static final String AUTH_CORPID = "";

    /**
     * 成功返回码
     */
    private static final int SCCESS_CODE = 0;


    /**
     * 企业内部开发获取access_token
     *
     * @return access_token
     */
    public static String getAccessToken() {
        DefaultDingTalkClient client = new DefaultDingTalkClient(DingTalkUrlConstants.GET_TOKEN);
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(APP_KEY);
        request.setAppsecret(APP_SECRET);
        request.setHttpMethod(DingTalkConstants.HTTP_METHOD_GET);
        try {
            OapiGettokenResponse response = client.execute(request);
            logger.info("response={}", JSONUtil.toJsonStr(response));
            if (response == null || response.getErrcode() != SCCESS_CODE) {
                return null;
            }
            return response.getAccessToken();
        } catch (ApiException e) {
            logger.error("getAccessToken fail={}", e);
            return null;
        }
    }


}
