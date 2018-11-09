package com.yotrio.pound.model.dto;

import com.yotrio.pound.model.StoreKeeper;

/**
 * 公司实体类 封装
 * 模块名称：projects-parent com.yotrio.pound.model.dto
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-11-09 14:02
 * 系统版本：1.0.0
 **/

public class StoreKeeperDto extends StoreKeeper {

    /**
     * 物料名称模糊查询
     */
    private String myLike_goodsName;

    /**
     * 组织名称模糊查询
     */
    private String myLike_orgName;

    /**
     * 仓管员模糊查询
     */
    private String myLike_keeperName;

    public String getMyLike_goodsName() {
        return myLike_goodsName;
    }

    public void setMyLike_goodsName(String myLike_goodsName) {
        this.myLike_goodsName = myLike_goodsName;
    }

    public String getMyLike_orgName() {
        return myLike_orgName;
    }

    public void setMyLike_orgName(String myLike_orgName) {
        this.myLike_orgName = myLike_orgName;
    }

    public String getMyLike_keeperName() {
        return myLike_keeperName;
    }

    public void setMyLike_keeperName(String myLike_keeperName) {
        this.myLike_keeperName = myLike_keeperName;
    }
}