package com.yotrio.pound.utils;


import com.yotrio.pound.domain.Result;

/**
 * 返回结果工具类
 * 模块名称：study-boot com.wangyq.utils
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-09-03 下午1:35
 * 系统版本：1.0.0
 **/

public class ResultUtil {
    /**
     * 状态码-成功
     */
    private static final int CODE_SUCCESS = 0;
    /**
     * 状态码-失败
     */
    private static final int CODE_ERROR = -1;

    /**
     * 成功
     *
     * @param data 返回数据
     * @return
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setCode(CODE_SUCCESS);
        result.setMsg("成功！");
        result.setData(data);
        return result;
    }

    /**
     * 成功
     *
     * @param msg 返回信息
     * @return
     */
    public static Result success(String msg) {
        Result result = new Result();
        result.setCode(CODE_SUCCESS);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    /**
     * 成功（分页）
     *
     * @param count data中数据条数
     * @param data  返回数据
     * @return
     */
    public static Result success(Long count, Object data) {
        Result result = new Result();
        result.setCode(CODE_SUCCESS);
        result.setMsg("成功！");
        result.setData(data);
        result.setCount(count);
        return result;
    }

    /**
     * 失败
     *
     * @param errorCode 错误码
     * @param msg       错误信息
     * @return
     */
    public static Result error(Integer errorCode, String msg) {
        Result result = new Result();
        result.setCode(errorCode);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    /**
     * 失败
     *
     * @param msg 错误信息
     * @return
     */
    public static Result error(String msg) {
        Result result = new Result();
        result.setCode(CODE_ERROR);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }


}
