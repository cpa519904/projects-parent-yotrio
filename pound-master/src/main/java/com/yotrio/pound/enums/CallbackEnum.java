package com.yotrio.pound.enums;

/**
 * ${DESCRIPTION}
 * 模块名称：study-boot.com.wangyq.enums
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-09-03 下午2:03
 * 系统版本：1.0.0
 **/
public enum CallbackEnum {
    UNKNOWN_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    ERROR(-1, "失败"),

    //1开头 增
    SAVE_ERROR(1000,"插入失败"),
    //2开头 删
    DELETE_ERROR(2000,"删除失败"),
    //3开头 改
    UPDATE_ERROR(3000,"更新失败"),
    //4开头 查
    GET_ERROR(4000,"获取失败"),

    //其他
    //5开头
    USER_ERROR(5000, "用户异常信息"),
    USER_NO_INFO(5001, "请填写用户注册信息"),
    USER_NO_USERNAME(5002, "请输入用户名"),
    USER_MSG_NO_VERIFY_CODE(5003, "请输入短信验证码"),
    USER_MSG_INVALID_VERIFY_CODE(5004, "验证码无效或已过期，请重新发送验证码"),
    USER_MOBILE_HAVE_REGISTER(5005, "该手机号已注册，请登录"),
    ;

    private Integer code;

    private String msg;

    CallbackEnum(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
