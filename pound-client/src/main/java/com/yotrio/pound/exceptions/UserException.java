package com.yotrio.pound.exceptions;


import com.yotrio.pound.enums.ResultEnum;

/**
 * 用户异常类
 * 模块名称：study-boot com.wangyq.exceptions
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-09-03 下午2:14
 * 系统版本：1.0.0
 **/

public class UserException extends RuntimeException {
    private Integer code;

    public UserException(Integer code) {
        this.code = code;
    }

    public UserException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
