package com.yotrio.pound.model.dto;

import com.yotrio.pound.model.Organization;

/**
 * 组织实体类 封装
 * 模块名称：projects-parent com.yotrio.pound.model.dto
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-11-09 14:02
 * 系统版本：1.0.0
 **/

public class OrganizationDto extends Organization {

    /**
     * 组织编码模糊查询
     */
    private String myLike_orgCode;

    /**
     * 组织名称模糊查询
     */
    private String myLike_OrgName;


    public String getMyLike_OrgName() {
        return myLike_OrgName;
    }

    public void setMyLike_OrgName(String myLike_OrgName) {
        this.myLike_OrgName = myLike_OrgName;
    }

    public String getMyLike_orgCode() {
        return myLike_orgCode;
    }

    public void setMyLike_orgCode(String myLike_orgCode) {
        this.myLike_orgCode = myLike_orgCode;
    }
}