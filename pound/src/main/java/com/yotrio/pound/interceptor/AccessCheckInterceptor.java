package com.yotrio.pound.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;
import java.util.List;

/**
 * Created by Sting on 17-2-13.
 * 访问校验拦截器
 * 1、token验证
 * 2、跨域访问验证
 */
public class AccessCheckInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private List<String> allowedHosts;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String refer = request.getHeader("Referer");

        if (refer == null) {
            return false;
        }
        URL url = new URL(refer);
        String host = url.getHost().toLowerCase();

        if (!allowedHosts.contains(host)) {
            return false;
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        return true;
    }


    public void setAllowedHosts(List<String> allowedHosts) {
        this.allowedHosts = allowedHosts;
    }
}
