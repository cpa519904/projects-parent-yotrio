package com.yotrio.common.enums;

/**
 * ${DESCRIPTION}
 * 模块名称：projects-parent.com.wyq.common.enums.admin
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-04-16 下午9:17
 * 系统版本：1.0.0
 **/
public enum SysUserRank {
    SYS_USER_RANK_0(0, ""),
    SYS_USER_RANK_1(1, "超级管理员"),
    SYS_USER_RANK_2(2, "钻石管理员"),
    SYS_USER_RANK_3(3, "铂金管理员"),
    SYS_USER_RANK_4(4, "黄金管理员"),
    SYS_USER_RANK_5(5, "白银管理员"),
    SYS_USER_RANK_6(6, "青铜管理员"),
    SYS_USER_RANK_7(7, "系统用户");

    private Integer rank;

    private String adminRankName;

    SysUserRank(Integer rank, String adminRankName) {
        this.rank = rank;
        this.adminRankName = adminRankName;
    }

    public Integer getRank() {
        return rank;
    }

    public String getAdminRankName() {
        return adminRankName;
    }

}
