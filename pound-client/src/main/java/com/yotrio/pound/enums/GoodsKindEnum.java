package com.yotrio.pound.enums;

/**
 * 货品类型 枚举
 */
public enum GoodsKindEnum {
    KIND_1("铁"),
    KIND_2("铝"),
    KIND_3("藤条"),
    ;

    GoodsKindEnum( String kindName) {
        this.kindName = kindName;
    }

    private String kindName;

    public String getKindName() {
        return kindName;
    }
}
