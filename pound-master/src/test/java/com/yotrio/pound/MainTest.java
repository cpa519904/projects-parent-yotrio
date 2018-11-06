package com.yotrio.pound;

import com.yotrio.common.utils.AESUtil;
import com.yotrio.common.utils.PropertiesFileUtil;

import java.io.IOException;

/**
 * 测试类
 * 模块名称：projects-parent com.yotrio.pound
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-09-17 上午11:24
 * 系统版本：1.0.0
 **/

public class MainTest {
    private static final String URL_GET_TOKEN = PropertiesFileUtil.getInstance("SystemParameter").get("url.getToken");

    public static void main(String[] args) throws IOException {
//        String accessToken = DingTalkUtil.getCacheAccessToken();
//        System.out.println("token:" + accessToken);

//        ArrayList<String> ports = SerialPortUtil.findPort();
//        System.out.println("prots:"+ports.toString());

//        DefaultHttpClient httpClient = new DefaultHttpClient();
//
//        HttpPost httpPost = new HttpPost(URL_GET_TOKEN);
//        // 设置请求的header
//        httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
//        // 设置请求的参数
//        JSONObject jsonParam = new JSONObject();
//        jsonParam.put("OrgCode", "301");
//        jsonParam.put("OrgID", "301");
//        jsonParam.put("loginUserCode", "001326601");
//        jsonParam.put("password", "123456");
//
//        StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
//        entity.setContentEncoding("UTF-8");
//        entity.setContentType("application/json");
//        httpPost.setEntity(entity);
//
//        // 执行请求
//        HttpResponse response = httpClient.execute(httpPost);
//        String json2 = EntityUtils.toString(response.getEntity(), "utf-8");
//        JSONObject jsonObject = JSON.parseObject(json2);
//
//        String token = null;
//        if (jsonObject != null) {
//            JSONObject result = jsonObject.getJSONObject("DoResponse");
//            if (result != null) {
//                token = result.getString("DoResult");
//            }
//        }
//        // 打印执行结果
//        System.out.println("token: " + token);


//        String userAuthToken = UserAuthTokenHelper.getUserAuthToken(326897, null);
//        System.out.println(userAuthToken);

        System.out.println( AESUtil.AESEncode("redis@2018."));

    }
}
