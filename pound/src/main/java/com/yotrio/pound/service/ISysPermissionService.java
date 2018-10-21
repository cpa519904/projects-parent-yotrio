package com.yotrio.pound.service;

import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.model.SysPermission;

import java.util.List;

/**
 * ${DESCRIPTION}
 * 模块名称：projects-parent.com.wyq.admin.service
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-06-30 下午12:37
 * 系统版本：1.0.0
 **/
public interface ISysPermissionService {
    PageInfo findAllPaging(DataTablePage dataTablePage, SysPermission sysPermission);

    SysPermission findById(Integer id);

    SysPermission findByPermissionName(String permissionName);

    int createSysPermission(SysPermission sysPermission);

    int updateSysPermission(SysPermission sysPermission);

    int deleteByIds(List<Integer> ids);
}
