package com.yotrio.pound.model.dto;

import com.yotrio.pound.model.Company;

/**
 * 公司实体类 封装
 * 模块名称：projects-parent com.yotrio.pound.model.dto
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-11-09 14:02
 * 系统版本：1.0.0
 **/

public class CompanyDto extends Company {

    /**
     * 公司编码模糊查询
     */
    private String myLike_compCode;

    /**
     * 公司名称模糊查询
     */
    private String myLike_compName;


    public String getMyLike_compName() {
        return myLike_compName;
    }

    public void setMyLike_compName(String myLike_compName) {
        this.myLike_compName = myLike_compName;
    }

    public String getMyLike_compCode() {
        return myLike_compCode;
    }

    public void setMyLike_compCode(String myLike_compCode) {
        this.myLike_compCode = myLike_compCode;
    }
}