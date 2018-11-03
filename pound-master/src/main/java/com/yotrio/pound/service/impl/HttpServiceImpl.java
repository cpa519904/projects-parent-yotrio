package com.yotrio.pound.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yotrio.common.constants.ApiUrlConstant;
import com.yotrio.common.utils.PropertiesFileUtil;
import com.yotrio.pound.service.IHttpService;
import com.yotrio.pound.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 网络请求逻辑类
 * 模块名称：projects-parent com.yotrio.pound.service
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-10-26 10:50
 * 系统版本：1.0.0
 **/

@Service("httpService")
public class HttpServiceImpl implements IHttpService {
    private Logger logger = LoggerFactory.getLogger(HttpServiceImpl.class);

    /**
     * 获取U9 token 的url
     */
    private static final String GET_U9_TOKEN_URL = PropertiesFileUtil.getInstance("SystemParameter").get("u9.url") + ApiUrlConstant.GET_U9_TOKEN;
    /**
     * 获取U9 收货单 的url
     */
    private static final String GET_U9_RECEIVE_INFO_URL = PropertiesFileUtil.getInstance("SystemParameter").get("u9.url") + ApiUrlConstant.GET_RECEIVE_INFO;

    /**
     * 获取u9 token的参数
     */
    private static final String ORG_CODE_VALUE = PropertiesFileUtil.getInstance("SystemParameter").get("u9.login.org.code");
    private static final String ORG_ID_VALUE = PropertiesFileUtil.getInstance("SystemParameter").get("u9.login.org.id");
    private static final String LOGIN_USER_CODE_VALUE = PropertiesFileUtil.getInstance("SystemParameter").get("u9.login.user.code");
    private static final String PASSWORD_VALUE = PropertiesFileUtil.getInstance("SystemParameter").get("u9.login.password");
    /**
     * 获取u9 token的参数值
     */
    private static final String ORG_CODE_KEY = "OrgCode";
    private static final String ORG_ID_KEY = "OrgID";
    private static final String LOGIN_USER_CODE_KEY = "loginUserCode";
    private static final String PASSWORD_KEY = "password";

    /**
     * 查询发货单关联U9收货单信息
     */
    @Override
    public JSONObject getU9ReceiveInfo(String DeliveryNo) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(GET_U9_TOKEN_URL + DeliveryNo);

            // 执行请求
            HttpResponse response = httpClient.execute(httpGet);
            String respObject = EntityUtils.toString(response.getEntity(), "utf-8");
            JSONObject jsonObject = JSON.parseObject(respObject);

            if (jsonObject != null) {
                JSONObject result = jsonObject.getJSONObject("DoResponse");
                if (result != null) {
                    return result;
                }
            }
        } catch (Exception e) {
            logger.error("查询发货单关联U9收货单信息失败={}", e);
        }

        return null;
    }

    /**
     * 获取U9 token
     *
     * @return
     */
    @Override
    public String getCacheU9Token() {
        String key = "U9Token";
        String u9Token = RedisUtil.get(key);
        if (StringUtils.isEmpty(u9Token)) {
            try {
                CloseableHttpClient httpClient = HttpClients.createDefault();
                HttpPost httpPost = new HttpPost(GET_U9_TOKEN_URL);
                // 设置请求的header
                httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
                // 设置请求的参数
                JSONObject jsonParam = new JSONObject();
                jsonParam.put(ORG_CODE_KEY, ORG_CODE_VALUE);
                jsonParam.put(ORG_ID_KEY, ORG_ID_VALUE);
                jsonParam.put(LOGIN_USER_CODE_KEY, LOGIN_USER_CODE_VALUE);
                jsonParam.put(PASSWORD_KEY, PASSWORD_VALUE);

                //解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                httpPost.setEntity(entity);

                // 执行请求
                HttpResponse response = httpClient.execute(httpPost);
                String respObject = EntityUtils.toString(response.getEntity(), "utf-8");
                JSONObject jsonObject = JSON.parseObject(respObject);

                if (jsonObject != null) {
                    JSONObject result = jsonObject.getJSONObject("DoResponse");
                    if (result != null) {
                        u9Token = result.getString("DoResult");
                    }
                }

                RedisUtil.set(key, u9Token, 60 * 60);
                return u9Token;
            } catch (Exception e) {
                logger.error("获取U9token失败={}", e);
            }
        }
        return u9Token;
    }

    /**
     * 写入过磅信息到U9收货单
     *
     * @param map
     * @return
     */
    @Override
    public String writeWeightToU9ReceiveInfo(Map<String, Object> map) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(GET_U9_TOKEN_URL);
            // 设置请求的header
            httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
            // 设置请求的参数
            JSONObject jsonParam = new JSONObject();
            jsonParam.put(ORG_CODE_KEY, ORG_CODE_VALUE);
            jsonParam.put(LOGIN_USER_CODE_KEY, LOGIN_USER_CODE_VALUE);
            jsonParam.put("Token", getCacheU9Token());
            jsonParam.put("BillNo", map.get("poundLogNo"));
            jsonParam.put("inspectJsonArr", map.get("inspectJsonArr"));
            jsonParam.put("Remark", map.get("remark"));

            //解决中文乱码问题
            StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);

            // 执行请求
            HttpResponse response = httpClient.execute(httpPost);
            String respObject = EntityUtils.toString(response.getEntity(), "utf-8");
            JSONObject jsonObject = JSON.parseObject(respObject);

            if (jsonObject != null) {
                JSONObject result = jsonObject.getJSONObject("DoResponse");
                if (result != null) {
                    // TODO: 2018-10-29 具体实现等待潘经理
                    return null;
                }
            }

        } catch (Exception e) {
            logger.error("写入过磅信息到U9收货单失败={}", e);
            return e.getMessage();
        }
        return "写入过磅信息到U9收货单失败";
    }

}