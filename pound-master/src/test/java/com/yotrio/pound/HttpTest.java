package com.yotrio.pound;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yotrio.common.constants.ApiUrlConstant;
import com.yotrio.common.utils.PropertiesFileUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 模块名称：projects-parent com.yotrio.pound
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-10-29 11:52
 * 系统版本：1.0.0
 **/

public class HttpTest {
    private static final String  URL_GET_TOKEN = PropertiesFileUtil.getInstance("SystemParameter").get("u9.url")+ ApiUrlConstant.GET_U9_TOKEN;

    public static void main(String[] args) throws IOException {
        DefaultHttpClient httpClient = new DefaultHttpClient();

        HttpPost httpPost = new HttpPost(URL_GET_TOKEN);
        // 设置请求的header
        httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
        // 设置请求的参数
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("OrgCode", "301");
        jsonParam.put("OrgID", "301");
        jsonParam.put("loginUserCode", "001326601");
        jsonParam.put("password", "123456");

        StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);
        String json2 = EntityUtils.toString(response.getEntity(), "utf-8");
        JSONObject jsonObject = JSON.parseObject(json2);

        String token = null;
        if (jsonObject != null) {
            JSONObject result = jsonObject.getJSONObject("DoResponse");
            if (result != null) {
                token = result.getString("DoResult");
            }
        }
        // 打印执行结果
        System.out.println("token: " + token);
    }
}