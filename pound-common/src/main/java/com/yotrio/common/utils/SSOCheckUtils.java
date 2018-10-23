package com.yotrio.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * SSO校验工具类 单点登陆
 * 模块名称：study com.wyq.study.utils
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2017-09-17 下午8:51
 * 系统版本：1.0.0
 **/

public class SSOCheckUtils {
    public static final String USERNAME = "wangyq";
    public static final String PASSWORD = "123456";

    /**
     * 统一登陆登陆校验
     *
     * @param username
     * @param password
     * @return
     */
    public static boolean checkLogin(String username, String password) {
        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
            return true;
        }
        return false;
    }

    /**
     * 统一登陆校验接口
     *
     * @param request
     * @return
     */
    public static boolean checkCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("ssocookie") && cookie.getValue().equals("sso")) {
                    return true;
                }
            }
        }
        return false;
    }
}
