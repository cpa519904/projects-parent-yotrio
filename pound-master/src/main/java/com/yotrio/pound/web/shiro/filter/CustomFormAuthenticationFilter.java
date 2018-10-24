package com.yotrio.pound.web.shiro.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义表单过滤器
 * 模块名称：projects-parent-yotrio com.yotrio.pound.web.shiro.filter
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-10-23 下午10:23
 * 系统版本：1.0.0
 **/

public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if(request.getAttribute(getFailureKeyAttribute()) != null) {
            return true;
        }
        return super.onAccessDenied(request, response, mappedValue);
    }
}
