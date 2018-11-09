package com.yotrio.pound.model.dto;

import com.yotrio.pound.model.Goods;

/**
 * 货品实体类 封装
 * 模块名称：projects-parent com.yotrio.pound.model.dto
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-11-09 14:02
 * 系统版本：1.0.0
 **/

public class GoodsDto extends Goods {

    /**
     * 货品编码模糊查询
     */
    private String myLike_goodsCode;

    /**
     * 货品名称模糊查询
     */
    private String myGoods_compName;


    public String getMyGoods_compName() {
        return myGoods_compName;
    }

    public void setMyGoods_compName(String myGoods_compName) {
        this.myGoods_compName = myGoods_compName;
    }

    public String getMyLike_goodsCode() {
        return myLike_goodsCode;
    }

    public void setMyLike_goodsCode(String myLike_goodsCode) {
        this.myLike_goodsCode = myLike_goodsCode;
    }
}