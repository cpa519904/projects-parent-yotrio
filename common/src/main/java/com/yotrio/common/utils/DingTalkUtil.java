package com.yotrio.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.DingTalkConstants;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.taobao.api.ApiException;
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

public class DingTalkUtil {
    private static final Logger logger = LoggerFactory.getLogger(DingTalkUtil.class);

    /**
     * 钉钉企业号AppKey
     */
//    public static final String APP_KEY = "dingyzwm1i5jahdt44av";
    public static final String APP_KEY = "dingew5vvjt4mmhuwqtx";

    /**
     * 钉钉企业号AppSecret
     */
//    public static final String APP_SECRET = "kHVowHEs9WntGi06RSbq1fmyAZUa-1CiYKmeKRArEk-_W7L_yTw4J71kGPvZyq5O";
    public static final String APP_SECRET = "2fPjVXdrczsCzrIaa98PNYmlUwD2u_iG1kQh3eRJXumXjeT4mxADX_a0Hd6N5TVr";

    /**
     * 钉钉企业号AgentId
     */
//    public static final long AGENT_ID = 193238118L;
    public static final long AGENT_ID = 194463808L;

    /**
     * 授权方corpid
     */
    public static final String AUTH_CORPID = "";

    /**
     * 成功返回码
     */
    public static final int SUCCESS_CODE = 0;


    /**
     * 企业内部开发获取access_token
     *
     * @return access_token
     */
    public static String getAccessToken() {
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(APP_KEY);
        request.setAppsecret(APP_SECRET);
        request.setHttpMethod(DingTalkConstants.HTTP_METHOD_GET);
        try {
            OapiGettokenResponse response = client.execute(request);
            if (response == null || response.getErrcode() != SUCCESS_CODE) {
                return null;
            }
            return response.getAccessToken();
        } catch (ApiException e) {
            logger.error("getAccessToken fail={}", e);
            return null;
        }
    }

    /**
     * 获取通讯录权限范围
     *
     * @param accessToken
     * @return authScopes
     */
    public static JSONObject getAuthScopes(String accessToken) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/auth/scopes");
        OapiAuthScopesRequest request = new OapiAuthScopesRequest();
        request.setHttpMethod("GET");
        try {
            OapiAuthScopesResponse response = client.execute(request, accessToken);
            if (response == null || response.getErrcode() != SUCCESS_CODE) {
                return null;
            }
            return JSON.parseObject(response.getBody());
        } catch (ApiException e) {
            logger.error("getAuthScopes fail={}", e);
            return null;
        }
    }

    /**
     * 发送工作消息
     *
     * @param userIdList 可选 接收者的用户userid列表，最大列表长度：20 如：zhangsan,lisi
     * @param deptIdList 可选 接收者的部门id列表，最大列表长度：20 如：123,456
     * @param toAllUser  可选 是否发送给企业全部用户(ISV不能设置true)
     * @param message    Json 必须 必须	{"msg_type":"text","text":{"content":"消息内容"}}或者{"msg_type":"image","image":{"media_id":"@lADOdvRYes0CbM0CbA"}}
     * @return {"errcode":0,"errmsg":"ok","task_id":123}
     */
    public static JSONObject sendWorkMessage(String userIdList, String deptIdList, Boolean toAllUser, JSONObject message) {
        if (message == null) {
            return null;
        }

        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2");

        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
        request.setUseridList(userIdList);
        request.setAgentId(AGENT_ID);
        request.setDeptIdList(deptIdList);
        request.setToAllUser(toAllUser);

        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();

        //message中获取消息类型
        String msgType = message.getString("msg_type");
        if (msgType == null) {
            return null;
        }
        switch (msgType) {
            case "text":
                msg.setMsgtype("text");
                msg.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
                JSONObject text = message.getJSONObject("text");
                msg.getText().setContent(text.getString("content"));
                break;
            case "image":
                msg.setMsgtype("image");
                msg.setImage(new OapiMessageCorpconversationAsyncsendV2Request.Image());
                JSONObject image = message.getJSONObject("image");
                msg.getImage().setMediaId(image.getString("media_id"));
                break;
            case "file":
                msg.setMsgtype("file");
                msg.setFile(new OapiMessageCorpconversationAsyncsendV2Request.File());
                JSONObject file = message.getJSONObject("file");
                msg.getImage().setMediaId(file.getString("media_id"));
                break;
            case "link":
                msg.setMsgtype("link");
                msg.setLink(new OapiMessageCorpconversationAsyncsendV2Request.Link());
                JSONObject link = message.getJSONObject("link");
                msg.getLink().setTitle(link.getString("title"));
                msg.getLink().setText(link.getString("text"));
                msg.getLink().setMessageUrl(link.getString("message_url"));
                msg.getLink().setPicUrl(link.getString("pic_url"));
                break;
            case "markdown":
                msg.setMsgtype("markdown");
                msg.setMarkdown(new OapiMessageCorpconversationAsyncsendV2Request.Markdown());
                JSONObject markdown = message.getJSONObject("markdown");
                msg.getMarkdown().setTitle(markdown.getString("title"));
                msg.getMarkdown().setText(markdown.getString("text"));
                break;
            case "oa":
                msg.setMsgtype("oa");
                msg.setOa(new OapiMessageCorpconversationAsyncsendV2Request.OA());
                JSONObject oa = message.getJSONObject("oa");
                msg.getOa().setHead(new OapiMessageCorpconversationAsyncsendV2Request.Head());
                JSONObject head = oa.getJSONObject("head");
                msg.getOa().getHead().setText(head.getString("head"));
                msg.getOa().setBody(new OapiMessageCorpconversationAsyncsendV2Request.Body());
                JSONObject body = oa.getJSONObject("body");
                msg.getOa().getBody().setContent(body.getString("content"));
                break;
            case "action_card":
                msg.setMsgtype("action_card");
                msg.setActionCard(new OapiMessageCorpconversationAsyncsendV2Request.ActionCard());
                JSONObject actionCard = message.getJSONObject("action_card");
                msg.getActionCard().setTitle(actionCard.getString("title"));
                msg.getActionCard().setMarkdown(actionCard.getString("markdown"));
                msg.getActionCard().setSingleTitle(actionCard.getString("single_title"));
                msg.getActionCard().setSingleUrl(actionCard.getString("single_url"));
                break;
            default:
                logger.info("unKnow msgType!");
                return null;
        }

        request.setMsg(msg);
        try {
            //获取accessToken
            String accessToken = getAccessToken();
            OapiMessageCorpconversationAsyncsendV2Response response = client.execute(request, accessToken);
            if (response == null) {
                return null;
            }
            return JSON.parseObject(response.getBody());
        } catch (ApiException e) {
            logger.error("sendWorkMessage fail={}", e);
            return null;
        }
    }

    /**
     * 获取工作通知消息的发送进度
     *
     * @param taskId 发送任务id
     * @return
     */
    public static JSONObject getSendProgress(Long taskId) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/message/corpconversation/getsendprogress");
        OapiMessageCorpconversationGetsendprogressRequest request = new OapiMessageCorpconversationGetsendprogressRequest();
        request.setAgentId(AGENT_ID);
        request.setTaskId(taskId);
        try {
            OapiMessageCorpconversationGetsendprogressResponse response = client.execute(request, getAccessToken());
            if (response == null) {
                return null;
            }
            return JSON.parseObject(response.getBody());
        } catch (ApiException e) {
            logger.error("getSendProgress fail={}", e);
            return null;
        }
    }

    /**
     * 获取工作通知消息的发送结果
     *
     * @param taskId 发送任务id
     * @return
     */
    public static JSONObject getSendResult(Long taskId) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/message/corpconversation/getsendresult");
        OapiMessageCorpconversationGetsendresultRequest request = new OapiMessageCorpconversationGetsendresultRequest();
        request.setAgentId(AGENT_ID);
        request.setTaskId(taskId);
        try {
            OapiMessageCorpconversationGetsendresultResponse response = client.execute(request, getAccessToken());
            if (response == null) {
                return null;
            }
            return JSON.parseObject(response.getBody());
        } catch (ApiException e) {
            logger.error("getSendResult fail={}", e);
            return null;
        }
    }


}
