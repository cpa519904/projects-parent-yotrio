package com.yotrio.pound.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.dao.SysPermissionMapper;
import com.yotrio.pound.model.SysPermission;
import com.yotrio.pound.service.ISysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 用户权限逻辑实现类
 * 模块名称：projects-parent com.wyq.admin.service.impl
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-06-30 上午10:08
 * 系统版本：1.0.0
 **/
@Service
public class SysPermissionServiceImpl implements ISysPermissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 分页获取权限
     *
     * @param dataTablePage
     * @param sysPermission
     * @return
     */
    @Override
    public PageInfo findAllPaging(DataTablePage dataTablePage, SysPermission sysPermission) {
        PageHelper.startPage(dataTablePage.getPage(), dataTablePage.getLimit());
        List<SysPermission> sysPermissions = sysPermissionMapper.selectListByMap(BeanUtil.beanToMap(sysPermission));
        for (SysPermission permission : sysPermissions) {
            permission.setStatusName(null);
        }
        PageInfo pageInfo = new PageInfo(sysPermissions);
        return pageInfo;
    }


    /**
     * 根据权限id查找用户
     *
     * @param id
     * @return
     */
    @Override
    public SysPermission findById(Integer id) {
        return sysPermissionMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据权限名查找权限
     *
     * @param permissionName
     * @return
     */
    @Override
    public SysPermission findByPermissionName(String permissionName) {
        return sysPermissionMapper.findByPermissionName(permissionName);
    }

    /**
     * 新增权限
     *
     * @param sysPermission
     * @return
     */
    @Override
    public int createSysPermission(SysPermission sysPermission) {
        sysPermission.setCreateTime(new Date());
        return sysPermissionMapper.insert(sysPermission);
    }

    /**
     * 修改权限
     *
     * @param sysPermission
     * @return
     */
    @Override
    public int updateSysPermission(SysPermission sysPermission) {
        sysPermission.setUpdateTime(new Date());
        return sysPermissionMapper.updateByPrimaryKeySelective(sysPermission);
    }

    /**
     * 根据ids删除权限
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Integer> ids) {
        return sysPermissionMapper.deleteByIds(ids);
    }
    
    
}
