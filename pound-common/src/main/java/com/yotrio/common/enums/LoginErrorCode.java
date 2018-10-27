package com.yotrio.common.enums;

/**
 * ${DESCRIPTION}
 * 模块名称：projects-parent.com.wyq.common.enums
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-04-15 下午1:54
 * 系统版本：1.0.0
 **/
public enum LoginErrorCode {
    LOGIN_UNKNOWN_ACCOUNT("该用户不存在"),
    LOGIN_USER_UNENABLE("该用户未启用"),
    LOGIN_USER_PASSWORD_ERROR("用户名/密码错误"),
    LOGIN_RETRY_LIMIT("密码尝试次数过多，锁定5分钟，请稍后尝试"),
    CODE_VERIFY_ERROR("验证码无效,请重新输入");

    private String description;

    LoginErrorCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
