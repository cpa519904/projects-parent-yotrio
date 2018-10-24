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

    /**
     * 模糊搜索组织
     */
    private String myLike_unitName;

    /**
     * 模糊搜索供应商名称
     */
    private String myLike_compName;

    /**
     * 模糊搜索车牌号码
     */
    private String myLike_plateNo;

    public Date getMyGreater_createTime() {
        return myGreater_createTime;
    }

    public void setMyGreater_createTime(Date myGreater_createTime) {
        this.myGreater_createTime = myGreater_createTime;
    }

    public Date getMyLesser_createTime() {
        return myLesser_createTime;
    }

    public void setMyLesser_createTime(Date myLesser_createTime) {
        this.myLesser_createTime = myLesser_createTime;
    }

    public String getMyLike_unitName() {
        return myLike_unitName;
    }

    public void setMyLike_unitName(String myLike_unitName) {
        this.myLike_unitName = myLike_unitName;
    }

    public String getMyLike_compName() {
        return myLike_compName;
    }

    public void setMyLike_compName(String myLike_compName) {
        this.myLike_compName = myLike_compName;
    }

    public String getMyLike_plateNo() {
        return myLike_plateNo;
    }

    public void setMyLike_plateNo(String myLike_plateNo) {
        this.myLike_plateNo = myLike_plateNo;
    }
}