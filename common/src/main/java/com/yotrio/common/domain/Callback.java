package com.yotrio.common.domain;

/**
 * 模块名称：projects-parent com.wyq.common.constants.admin
 * 功能说明：返回信息的封装<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-09-117 下午9:25
 * 系统版本：1.0.0
 **/
public class Callback {

    /**
     * 请求返回 状态的信息
     */
    private String msg;
    /**
     * 请求返回 具体内容
     */
    private Object data;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * data数量
     */
    private Long count;

    public Callback() {
        super();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Callback(Integer code, String msg, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Callback(Integer code, String msg, Long count, Object data) {
        this.msg = msg;
        this.data = data;
        this.code = code;
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
