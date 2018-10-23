package com.yotrio.pound.exceptions;

/**
 * 上传过磅信息异常
 * 模块名称：projects-parent com.yotrio.pound.exceptions
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-10-19 14:55
 * 系统版本：1.0.0
 **/

public class UploadLogException extends RuntimeException {
    private Integer code;

    public UploadLogException(Integer code) {
        this.code = code;
    }

    public UploadLogException() {
        super("上传过磅信息异常");
    }

    public UploadLogException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}