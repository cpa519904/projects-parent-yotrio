package com.yotrio.pound.constants;

/**
 * 过磅记录常量类
 * 模块名称：projects-parent com.yotrio.pound.constants
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-10-08 13:16
 * 系统版本：1.0.0
 **/

public class PoundLogConstant {
    /**
     * 初始化
     */
    public static final int STATUS_INIT = 0;

    /**
     * 第一次过磅
     */
    public static final int STATUS_POUND_FIRST = 1;

    /**
     * 第二次过磅信息
     */
    public static final int STATUS_POUND_SECOND = 2;

    /**
     * 完成过磅已保存
     */
    public static final int STATUS_POUND_FINISH = 10;

    /**
     * 类型：0 进货
     */
    public static final int TYPES_IN = 0;

    /**
     * 类型：1 退货
     */
    public static final int TYPES_OUT = 1;
}