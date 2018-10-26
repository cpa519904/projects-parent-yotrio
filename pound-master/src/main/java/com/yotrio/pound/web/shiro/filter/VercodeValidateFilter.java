package com.yotrio.pound.web.shiro.filter;

import com.yotrio.common.constants.SysConstants;
import com.yotrio.common.enums.LoginErrorCode;
import com.yotrio.pound.utils.RedisUtil;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 验证码过滤器
 * 模块名称：projects-parent-yotrio com.yotrio.pound.web.shiro.filter
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-10-23 下午10:08
 * 系统版本：1.0.0
 **/

public class VercodeValidateFilter extends AccessControlFilter {

    private boolean vercodeEbabled = true;//是否开启验证码支持

    private String vercodeParam = "vercode";//前台提交的验证码参数名

    private String failureKeyAttribute = "shiroLoginFailure"; //验证码验证失败后存储到的属性名

    public void setVercodeEbabled(boolean vercodeEbabled) {
        this.vercodeEbabled = vercodeEbabled;
    }

    public void setVercodeParam(String vercodeParam) {
        this.vercodeParam = vercodeParam;
    }

    public void setFailureKeyAttribute(String failureKeyAttribute) {
        this.failureKeyAttribute = failureKeyAttribute;
    }

    /**
     * isAccessAllowed：表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
     *
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        //1、设置验证码是否开启属性，页面可以根据该属性来决定是否显示验证码
        servletRequest.setAttribute("vercodeEbabled", vercodeEbabled);

        HttpServletRequest httpServletRequest = WebUtils.toHttp(servletRequest);
        String method = httpServletRequest.getMethod();
        //2、判断验证码是否禁用 或不是表单提交（允许访问）
        if (vercodeEbabled == false || !"post".equalsIgnoreCase(method)) {
            return true;
        }
        //3、此时是表单提交，验证验证码是否正确
        String vercodeString = servletRequest.getParameter("vercode");
        String sessionId = httpServletRequest.getSession().getId();
        //4、与缓存在redis的验证码做比较
        if (!vercodeString.equals(RedisUtil.get(SysConstants.KEY_VERIFY_CODE + sessionId))) {
            servletRequest.setAttribute(failureKeyAttribute, LoginErrorCode.CODE_VERIFY_ERROR);
            return false;
        }
        //5、验证通过后删除缓存在redis的验证码
        RedisUtil.remove(SysConstants.KEY_VERIFY_CODE + sessionId);
        return true;
    }

    /**
     * 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //如果验证码失败了，存储失败key属性
//        servletRequest.setAttribute(failureKeyAttribute, "vercode.error");
        return true;
    }
}
