package com.yotrio.pound.service;

import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.model.SysUser;

import java.util.Set;

/**
 * ${DESCRIPTION}
 * 模块名称：projects-parent.com.wyq.admin.service
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-03-13 下午10:04
 * 系统版本：1.0.0
 **/
public interface ISysUserService {

    int createSysUser(SysUser sysUser);

    SysUser findByUsername(String username);

    SysUser findByUserId(Integer userId);

    int deleteSysUser(Integer userId);

    int updateSysUser(SysUser sysUser);


    PageInfo findAllPaging(DataTablePage dataTablePage, SysUser sysUser);

    Set<String> findRolesByUserId(Integer userId);

    Set<String> findRolesByEmpId(Integer empId);

    Set<String> findPermissionsByUserId(Integer userId);

    void saveUserTest(SysUser sysUser);
}
