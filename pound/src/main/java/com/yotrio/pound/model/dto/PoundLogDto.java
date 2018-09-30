package com.yotrio.pound.model.dto;

import com.yotrio.pound.model.PoundLog;

import java.util.Date;

/**
 * 数据转换类
 * 模块名称：projects-parent com.yotrio.pound.model.dto
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-09-30 14:00
 * 系统版本：1.0.0
 **/

public class PoundLogDto extends PoundLog {

    /**
     * 查询时间大于（创建时间）
     */
    private Date myGreater_createTime;

    /**
     * 查询时间小于（创建时间）
     */
    private Date myLesser_createTime;

    @Override
    public Date getMyGreater_createTime() {
        return myGreater_createTime;
    }

    @Override
    public void setMyGreater_createTime(Date myGreater_createTime) {
        this.myGreater_createTime = myGreater_createTime;
    }

    @Override
    public Date getMyLesser_createTime() {
        return myLesser_createTime;
    }

    @Override
    public void setMyLesser_createTime(Date myLesser_createTime) {
        this.myLesser_createTime = myLesser_createTime;
    }
}