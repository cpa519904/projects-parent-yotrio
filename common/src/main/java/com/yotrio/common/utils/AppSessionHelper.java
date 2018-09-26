package com.yotrio.common.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * AppSession工具
 * 模块名称：study
 * 功能说明：<br>
 * 功能描述：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2017-03-05 上午10:37
 * 系统版本：1.0.0
 **/

public class AppSessionHelper {
    private static Logger logger = Logger.getLogger(AppSessionHelper.class);
    public static final String SESSION_KEY = "WE2323@#$$$@&*FDGZfgfgf$%^$%*&*123FGeer*0!c2pY";

    /**
     * 根据用户ID获得session
     */
    public static String getUserSession(Integer userId) {

        MD5 md5 = new MD5();
        final String s = userId + "_" + SESSION_KEY;
        return userId + "_" + md5.calcMD5(s);
    }

    /**
     * 根据用户session获得拥护ID(没校验用户身份存在)
     */
    public static Integer getAppUserId(String userSession) {
        if (StringUtils.isBlank(userSession)) {
            return null;
        }
        String cookieValues[] = userSession.split("_");// 加密方式为：uid +"_"+ MD5(uid+key)
        if (cookieValues.length != 2) { // 如果cookie值长度不够，则返回重新登录
            return null;
        }
        final String cookieText = cookieValues[0] + "_" + SESSION_KEY;
        MD5 md5 = new MD5();
        String confirmValue = md5.calcMD5(cookieText);
        if (confirmValue.equals(cookieValues[1])) {
            try {
                return Integer.parseInt(cookieValues[0]);
            } catch (Exception e) {
                logger.error("<getAppUserId> parse userId fail! ", e);
                return null;
            }
        }
        return null;
    }

    public static void main(String... args) {
        System.out.println(AppSessionHelper.getUserSession(11082));
        System.out.println(AppSessionHelper.getAppUserId("11082_77caab1670c88a8304602ab7cfd6f69e"));
    }
}
