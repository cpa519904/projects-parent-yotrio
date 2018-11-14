package com.yotrio.touch.model;

import java.io.Serializable;

/**
 * 返回结果
 * 模块名称：study-boot com.wangyq.domain
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-09-03 下午1:29
 * 系统版本：1.0.0
 **/

public class Result<T> implements Serializable{
    /**
     * 返回状态码
     */
    private int code;

    /**
     * 返回提示信息
     */
    private String msg;

    /**
     * 返回数据结果
     */
    private T data;

    private Long count;

    public Result() {

    }
    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
