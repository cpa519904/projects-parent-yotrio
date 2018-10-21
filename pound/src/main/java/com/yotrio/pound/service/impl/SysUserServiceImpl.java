package com.yotrio.pound.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.common.enums.SysUserRank;
import com.yotrio.pound.dao.SysPermissionMapper;
import com.yotrio.pound.dao.SysRoleMapper;
import com.yotrio.pound.dao.SysUserMapper;
import com.yotrio.pound.model.SysUser;
import com.yotrio.pound.service.ISysUserService;
import com.yotrio.pound.web.shiro.utils.PasswordHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 管理远用户
 * 模块名称：projects-parent com.wyq.admin.service.impl
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-03-04 下午7:40
 * 系统版本：1.0.0
 **/
@Service
public class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Autowired
    private PasswordHelper passwordHelper;

    /**
     * 创建系统用户
     *
     * @param sysUser
     * @return
     */
    @Override
    public int createSysUser(SysUser sysUser) {
        if (sysUser.getRank() >= 0 && sysUser.getRank() <= SysUserRank.values().length) {
            sysUser.setNickname(SysUserRank.values()[sysUser.getRank()].getAdminRankName());
        }
        passwordHelper.encryptPassword(sysUser);
        sysUser.setCreateTime(new Date());
        return sysUserMapper.insert(sysUser);
    }

    /**
     * 根据用户明查找用户
     *
     * @param username
     * @return
     */
    @Override
    public SysUser findByUsername(String username) {
        SysUser sysUser = sysUserMapper.findByUsername(username);
        return sysUser;
    }

    /**
     * 根据用户id查找用户
     *
     * @param userId
     * @return
     */
    @Override
    public SysUser findByUserId(Integer userId) {
        return sysUserMapper.selectByPrimaryKey(userId);
    }

    /**
     * 删除用户
     *
     * @param userId
     */
    @Override
    public int deleteSysUser(Integer userId) {
        return sysUserMapper.deleteByPrimaryKey(userId);
    }

    /**
     * 选择性更新用户信息
     *
     * @param sysUser
     * @return
     */
    @Override
    public int updateSysUser(SysUser sysUser) {
        if (sysUser.getRank() >= 0 && sysUser.getRank() <= SysUserRank.values().length) {
            sysUser.setNickname(SysUserRank.values()[sysUser.getRank()].getAdminRankName());
        }
        passwordHelper.encryptPassword(sysUser);
        sysUser.setUpdateTime(new Date());
        return sysUserMapper.updateByPrimaryKeySelective(sysUser);
    }

    /**
     * 获取用户分页信息
     *
     * @param sysUser
     * @return
     */
    @Override
    public PageInfo findAllPaging(DataTablePage dataTablePage, SysUser sysUser) {
        PageHelper.startPage(dataTablePage.getPage(), dataTablePage.getLimit());
        List<SysUser> sysUsers = sysUserMapper.selectListByMap(BeanUtil.beanToMap(sysUser));
        for (SysUser item : sysUsers) {
            item.setStatusName(null);
        }
        PageInfo pageInfo = new PageInfo(sysUsers);
        return pageInfo;
    }

    /**
     * 根据用户id获取用户角色
     * @param userId
     * @return
     */
    @Override
    public Set<String> findRolesByUserId(Integer userId) {
        return sysRoleMapper.findRolesByUserId(userId);
    }


    /**
     * 获取用户角色
     *
     * @param
     * @return
     */
    @Override
    public Set<String> findRolesByEmpId(Integer empId) {
        return sysRoleMapper.findRolesByUserId(empId);
    }

    /**
     * 获取用户权限
     *
     * @param userId
     * @return
     */
    @Override
    public Set<String> findPermissionsByUserId(Integer userId) {
        Set<Integer> roleIds = sysRoleMapper.findRoleIdsByUserId(userId);
        Set<String> permissionList = new HashSet<>();
        for (Integer roleId : roleIds) {
            Set<String> permissions = sysPermissionMapper.findPermissionsByRoleId(roleId);
            permissionList.addAll(permissions);
        }
        return permissionList;
    }


}
