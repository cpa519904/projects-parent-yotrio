package com.yotrio.common.helpers;

import com.alibaba.fastjson.JSONObject;
import com.yotrio.common.utils.AuthTokenEncryptUtils;
import com.yotrio.common.utils.DateUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserAuthTokenHelper {
    private static final String  TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public UserAuthTokenHelper() {
    }

    public static String getUserAuthToken(Integer userId, String param) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userId", userId);
            jsonObject.put("validTime", DATE_FORMAT.format(DateUtil.add(new Date(), 30)));
            return AuthTokenEncryptUtils.getEncString(jsonObject.toString());
        } catch (Exception var3) {
            return null;
        }
    }

    public static JSONObject verifyUserAuthToken(String userToken, String parm) {
        try {
            String de = AuthTokenEncryptUtils.getDesString(userToken);
            JSONObject json = JSONObject.parseObject(de);
            String ss = json.getString("validTime");
            Date validtime = DateUtil.toUtilDateN(ss, TIME_FORMAT);
            return validtime.before(new Date()) ? null : json;
        } catch (Exception var6) {
            return null;
        }
    }

    public static JSONObject loginByUserAuthToken(String userToken, String parm) {
        try {
            String de = AuthTokenEncryptUtils.getDesString(userToken);
            JSONObject json = JSONObject.parseObject(de);
            String ss = json.getString("validTime");
            Date validtime = DateUtil.toUtilDateN(ss, TIME_FORMAT);
            return validtime.before(new Date()) ? null : json;
        } catch (Exception var6) {
            return null;
        }
    }
}