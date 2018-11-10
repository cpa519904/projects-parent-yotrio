package com.yotrio.pound.service.impl;


import com.yotrio.pound.dao.OrganizationMapper;
import com.yotrio.pound.model.Organization;
import com.yotrio.pound.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 组织逻辑类
 * 模块名称：projects-parent com.yotrio.pound.service
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-10-26 10:50
 * 系统版本：1.0.0
 **/
@Service("organizationService")
public class OrganizationServiceImpl implements IOrganizationService {
    @Autowired
    private OrganizationMapper organizationMapper;

    /**
     * 根据id获取对象
     *
     * @param id 主键id
     * @return
     */
    @Override
    public Organization findById(Integer id) {
        return  organizationMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据组织编码获取对象
     *
     * @param orgCode 组织编码
     * @return
     */
    @Override
    public Organization findByOrgCode(String orgCode) {
        return organizationMapper.selectByOrgCode(orgCode);
    }

    /**
     * 更新组织数据
     *
     * @param organization
     * @return
     */
    @Override
    public int updateById(Organization organization) {
        organization.setUpdateTime(new Date());
        organization.setLastUpdateTime(new Date());
        return organizationMapper.updateByPrimaryKeySelective(organization);
    }

    /**
     * 根据id删除组织数据
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(Integer id) {
        return organizationMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加组织
     *
     * @param organization
     * @return
     */
    @Override
    public int save(Organization organization) {
        return organizationMapper.insert(organization);
    }

    /**
     * 获取所有组织列表
     * @return
     */
    @Override
    public List<Organization> findAll() {
        return organizationMapper.findAll();
    }

    @Override
    public int updateByOrgCode(Organization organization) {
        return organizationMapper.updateByOrgCodeSelective(organization);
    }
}
