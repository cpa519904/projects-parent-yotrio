package com.yotrio.pound.web.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ICaptcha;
import com.yotrio.common.enums.LoginErrorCode;
import com.yotrio.pound.service.ISysUserService;
import com.yotrio.pound.utils.RedisUtil;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户控制接口
 * 模块名称：projects-parent com.wyq.admin.web.controller
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-01-29 下午10:20
 * 系统版本：1.0.0
 **/

@Controller
@RequestMapping("/")
public class LoginController extends BaseController {

    private static final String KEY_VERIFY_CODE = "verify_code_";

    @Autowired
    private ISysUserService sysUserService;

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String showLoginForm(Model model, HttpServletRequest request) {
        //验证登录
        String exceptionClassName = null;
        String error = null;
        if (request.getAttribute("shiroLoginFailure") != null) {
            exceptionClassName = request.getAttribute("shiroLoginFailure").toString();
        }

        /**
         * 这里会返回信息
         * Exception 类名称 一般由原生shiro产生
         * LoginCodeError 枚举 一般由 com.olymtech.nebula.web.filter.TotpCodeValidateFilter 产生
         */
        try {
            LoginErrorCode loginErrorCode = LoginErrorCode.valueOf(exceptionClassName);
            error = loginErrorCode.getDescription();
        } catch (Exception e) {
            if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
                error = LoginErrorCode.LOGIN_UNKNOWN_ACCOUNT.getDescription();
            } else if (LockedAccountException.class.getName().equals(exceptionClassName)) {
                error = LoginErrorCode.LOGIN_USER_UNENABLE.getDescription();
            } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
                error = LoginErrorCode.LOGIN_USER_PASSWORD_ERROR.getDescription();
            } else if (AuthenticationException.class.getName().equals(exceptionClassName)) {
                error = LoginErrorCode.LOGIN_USER_PASSWORD_ERROR.getDescription();
            } else if (LoginErrorCode.class.getName().equals(exceptionClassName)) {
                error = LoginErrorCode.TOTP_UNBINDING.getDescription();
            } else if (ExcessiveAttemptsException.class.getName().equals(exceptionClassName)) {
                error = LoginErrorCode.LOGIN_RETRY_LIMIT.getDescription();
            } else if (exceptionClassName != null) {
                error = "其他错误：" + exceptionClassName;
            }
        }

        model.addAttribute("error", error);

        return "user/login";
    }

    /**
     * 用户注册页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/register.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public String register(Model model) {

        return "user/register";
    }

    /**
     * 获取图片验证码
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getVerifyCode", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response) {
        String sessionId = request.getSession().getId();

        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ICaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
        try {
            //写出到浏览器,Servlet输出
            captcha.write(response.getOutputStream());
            //获取验证码并存储到redis 5分钟
            String code = captcha.getCode();
            RedisUtil.set(KEY_VERIFY_CODE + sessionId, code, 5 * 60);
        } catch (IOException e) {
            logger.error("验证码生成失败：{}", e.getMessage());
        }
    }




}
