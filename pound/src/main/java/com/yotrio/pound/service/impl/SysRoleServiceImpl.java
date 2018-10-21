package com.yotrio.pound.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.dao.SysRoleMapper;
import com.yotrio.pound.model.SysRole;
import com.yotrio.pound.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 用户角色逻辑实现类
 * 模块名称：projects-parent com.wyq.admin.service.impl
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-06-30 上午10:08
 * 系统版本：1.0.0
 **/
@Service
public class SysRoleServiceImpl implements ISysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 分页查找角色列表
     *
     * @param dataTablePage
     * @param sysRole
     * @return
     */
    @Override
    public PageInfo findAllPaging(DataTablePage dataTablePage, SysRole sysRole) {
        PageHelper.startPage(dataTablePage.getPage(), dataTablePage.getLimit());
        List<SysRole> sysRoles = sysRoleMapper.selectListByMap(BeanUtil.beanToMap(sysRole));
        for (SysRole role : sysRoles) {
            role.setStatusName(null);
        }
        PageInfo pageInfo = new PageInfo(sysRoles);
        return pageInfo;
    }

    /**
     * 根据角色id查找用户
     *
     * @param id
     * @return
     */
    @Override
    public SysRole findById(Integer id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据角色名查找角色
     *
     * @param roleName
     * @return
     */
    @Override
    public SysRole findByRoleName(String roleName) {
        return sysRoleMapper.findByRoleName(roleName);
    }

    /**
     * 新增角色
     *
     * @param sysRole
     * @return
     */
    @Override
    public int createSysRole(SysRole sysRole) {
        return sysRoleMapper.insert(sysRole);
    }

    /**
     * 修改角色
     *
     * @param sysRole
     * @return
     */
    @Override
    public int updateSysRole(SysRole sysRole) {
        return sysRoleMapper.updateByPrimaryKeySelective(sysRole);
    }

    /**
     * 根据ids删除角色
     *
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Integer> ids) {
        return sysRoleMapper.deleteByIds(ids);
    }

    /**
     * 根据用户id获取角色
     * @param userId
     * @return
     */
    @Override
    public Set<String> findRolesByUserId(Integer userId) {
        return sysRoleMapper.findRolesByUserId(userId);
    }


}
