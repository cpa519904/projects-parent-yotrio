package com.yotrio.common.enums;

/**
 * 货品类型 枚举
 */
public enum GoodsKindEnum {
    KIND_1(1,"铝材"),
    KIND_2(2,"铁材 "),
    KIND_3(3,"气泡纸"),
    KIND_4(4,"塑料"),
    KIND_5(5,"铁材"),
    KIND_6(6,"藤条"),
    KIND_7(7,"化学品"),
    KIND_8(8,"塑粉"),
    KIND_9(9,"布"),
    KIND_10(10,"氩气"),
    KIND_11(11,"纸"),
    KIND_12(12,"网纱"),
    KIND_13(13,"棉"),
    KIND_14(14,"穿布条"),
    KIND_15(15,"废铁"),
    KIND_16(16,"油"),
    KIND_17(17,"废铝"),
    KIND_18(18,"板带"),
    KIND_19(19,"废特斯林"),
    KIND_20(20,"废坐垫"),
    KIND_21(21,"废坐垫套"),
    KIND_22(22,"废白海绵"),
    KIND_23(23,"铝材"),
    KIND_24(24,"废藤条"),
    KIND_25(25,"废纸板"),
    KIND_26(26,"珍珠棉"),
    KIND_27(27,"电焊机"),
    KIND_28(28,"堆高架"),
    KIND_29(29,"污泥"),
    KIND_30(30,"废铝+铁"),
    KIND_31(31,"薄膜"),
    KIND_32(32,"二氧化碳"),
    KIND_33(33,"废塑料"),
    KIND_34(34,"塑料+铁"),
    KIND_35(35,"玻璃碎"),
    KIND_36(36,"PP棉"),
    KIND_37(37,"废塑粉"),
    KIND_38(38,"发泡块"),
    KIND_39(39,"折扣铸铝"),
    KIND_40(40,"石灰"),
    KIND_41(41,"氩气"),
    KIND_42(42,"合金钢丸"),
    KIND_43(43,"塑粉"),
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
