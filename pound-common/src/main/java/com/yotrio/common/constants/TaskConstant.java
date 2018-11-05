package com.yotrio.common.constants;

/**
 * 任务常量类
 * 模块名称：projects-parent com.yotrio.pound.constants
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-10-08 13:16
 * 系统版本：1.0.0
 **/

public class TaskConstant {

    /**
     * 取消执行,如：找不到过磅信息，或者找不到地磅信息等
     */
    public static final int STATUS_CANCEL = -1;

    /**
     * 待执行
     */
    public static final int STATUS_INIT = 0;

    /**
     * 已完成
     */
    public static final int STATUS_FINISHED = 1;

    /**
     * 存储第一次过磅信息
     */
    public static final int TYPE_SAVE_MSG = 1;

    /**
     * 更新第二次过磅信息
     */
    public static final int TYPE_UPDATE_MSG = 2;

    /**
     * 推送钉钉确认消息
     */
    public static final int TYPE_SEND_DING_TALK_CONFIRM_MSG = 3;

    /**
     * 上传过磅单
     */
    public static final int TYPE_UPLOAD_MSG = 3;

    /**
     * 默认权重值
     */
    public static final int WEIGHT_INIT = 0;


}