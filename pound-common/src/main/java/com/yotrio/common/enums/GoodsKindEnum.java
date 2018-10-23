package com.yotrio.common.enums;

/**
 * 货品类型 枚举
 */
public enum GoodsKindEnum {
    KIND_1(1,"铁"),
    KIND_2(2,"铝"),
    KIND_3(3,"藤条"),
    ;

    GoodsKindEnum(Integer code, String kindName) {
        this.code = code;
        this.kindName = kindName;
    }

    private Integer code;

    private String kindName;

    public static String getKindName(Integer code) {
        for (GoodsKindEnum kindEnum : GoodsKindEnum.values()) {
            if (code == kindEnum.code) {
                return kindEnum.kindName;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }
}
