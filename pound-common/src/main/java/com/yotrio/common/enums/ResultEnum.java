package com.yotrio.common.enums;

/**
 * ${DESCRIPTION}
 * 模块名称：study-boot.com.wangyq.enums
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-09-03 下午2:03
 * 系统版本：1.0.0
 **/
public enum ResultEnum {
    UNKNOWN_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
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
