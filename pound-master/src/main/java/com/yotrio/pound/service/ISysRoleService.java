package com.yotrio.pound.service;

import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.model.SysRole;

import java.util.List;
import java.util.Set;

/**
 * ${DESCRIPTION}
 * 模块名称：projects-parent.com.wyq.admin.service
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-06-30 上午10:09
 * 系统版本：1.0.0
 **/
public interface ISysRoleService {
    PageInfo findAllPaging(DataTablePage dataTablePage, SysRole sysRole);

    SysRole findById(Integer id);

    SysRole findByRoleName(String roleName);

    int createSysRole(SysRole sysRole);

    int updateSysRole(SysRole sysRole);

    int deleteByIds(List<Integer> ids);

    Set<String> findRolesByUserId(Integer userId);
}
