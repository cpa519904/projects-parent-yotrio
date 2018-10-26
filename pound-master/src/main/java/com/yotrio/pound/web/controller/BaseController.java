package com.yotrio.pound.web.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yotrio.common.domain.Callback;
import com.yotrio.common.helpers.UserAuthTokenHelper;
import com.yotrio.common.utils.PropertiesFileUtil;
import com.yotrio.pound.enums.CallbackEnum;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 控制接口基类
 * 模块名称：projects-parent com.wyq.admin.web.controller
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-9-17 下午2:20
 * 系统版本：1.0.0
 **/

public class BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected Callback callback = new Callback();
    protected Map<String, Object> callbackMap = new HashMap<String, Object>();
    protected String msg;
    protected Object data;
    protected Integer code;
    @Resource
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    /**
     * 状态码-成功
     */
    private static final int CODE_SUCCESS = 0;
    /**
     * 状态码-失败
     */
    private static final int CODE_ERROR = -1;

    /**
     * 从u9获取token
     */
    private static final String URL_GET_TOKEN = PropertiesFileUtil.getInstance("SystemParameter").get("url.getToken");



    /**
     * 成功
     *
     * @param data 返回数据
     * @return
     */
    public Callback returnSuccess(Object data) {
        Callback result = new Callback();
        result.setCode(CODE_SUCCESS);
        result.setMsg("成功！");
        result.setData(data);
        return result;
    }

    /**
     * 成功
     *
     * @param count 条数
     * @param data  返回数据
     * @return
     */
    public Callback returnSuccess(Long count, Object data) {
        Callback result = new Callback();
        result.setCode(CODE_SUCCESS);
        result.setMsg("成功！");
        result.setData(data);
        result.setCount(count);
        return result;
    }

    /**
     * 失败
     *
     * @param errorCode 错误码
     * @param msg       错误信息
     * @return
     */
    public static Callback returnError(Integer errorCode, String msg) {
        Callback result = new Callback();
        result.setCode(errorCode);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    /**
     * 失败
     *
     * @param msg 错误信息
     * @return
     */
    public static Callback returnError(String msg) {
        Callback result = new Callback();
        result.setCode(CallbackEnum.ERROR.getCode());
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    /**
     * 失败
     *
     * @param callbackEnum
     * @return
     */
    public static Callback returnError(CallbackEnum callbackEnum) {
        Callback result = new Callback();
        result.setCode(callbackEnum.getCode());
        result.setMsg(callbackEnum.getMsg());
        result.setData(null);
        return result;
    }

    /**
     * 获得用户Id.
     *
     * @return
     */
    protected Integer getAppUserId(String token) {
        JSONObject json = UserAuthTokenHelper.verifyUserAuthToken(token, null);
        if (json != null) {
            return json.getInteger("userId");
        }
        return null;
    }

    /**
     * 获取u9的u9Token
     * @param orgCode 组织code
     * @param orgId   组织id
     * @param loginUserCode  登录用户code
     * @param passward 用户密码
     * @return
     */
    protected String getU9Token(String orgCode, String orgId, String loginUserCode, String passward) {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost(URL_GET_TOKEN);
        // 设置请求的header
        httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
        // 设置请求的参数
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("OrgCode", orgCode);
        jsonParam.put("OrgID", orgId);
        jsonParam.put("loginUserCode", loginUserCode);
        jsonParam.put("password", passward);
        StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        HttpResponse response = null;
        String u9Token = null;
        try {
            // 执行请求
            response = httpClient.execute(httpPost);

            String json2 = EntityUtils.toString(response.getEntity(), "utf-8");
            JSONObject jsonObject = JSON.parseObject(json2);
            if (jsonObject != null) {
                JSONObject result = jsonObject.getJSONObject("DoResponse");
                if (result != null) {
                    u9Token = result.getString("DoResult");
                }
            }
        } catch (IOException e) {
            logger.error("Get token fail...{}", e.getMessage());
        }

        return u9Token;
    }


    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public Map<String, Object> getCallbackMap() {
        return callbackMap;
    }

    public void setCallbackMap(Map<String, Object> callbackMap) {
        this.callbackMap = callbackMap;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
//        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"), true));
//        webDataBinder.registerCustomEditor(int.class, new IntegerEditor());
//        webDataBinder.registerCustomEditor(long.class, new LongEditor());
//        webDataBinder.registerCustomEditor(double.class, new DoubleEditor());
//        webDataBinder.registerCustomEditor(float.class, new FloatEditor());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
