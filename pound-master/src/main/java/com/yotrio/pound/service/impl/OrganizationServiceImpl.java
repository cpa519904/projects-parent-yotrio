package com.yotrio.pound.service.impl;


import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yotrio.common.constants.OrganizationConstant;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.dao.OrganizationMapper;
import com.yotrio.pound.model.Organization;
import com.yotrio.pound.model.dto.OrganizationDto;
import com.yotrio.pound.service.IOrganizationService;
import com.yotrio.pound.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
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
     * 分页获取组织数据
     *
     * @param dataTablePage   分页条件
     * @param organizationDto 查询条件
     * @return
     */
    @Override
    public PageInfo findAllPaging(DataTablePage dataTablePage, OrganizationDto organizationDto) {
        PageHelper.startPage(dataTablePage.getPage(), dataTablePage.getLimit());
        List<Organization> organizationList = organizationMapper.selectListByMap(BeanUtil.beanToMap(organizationDto));
        PageInfo pageInfo = new PageInfo(organizationList);
        return pageInfo;
    }

    /**
     * 根据id获取对象
     *
     * @param id 主键id
     * @return
     */
    @Override
    public Organization findCacheById(Integer id) {
        String key = "Organization:id:";
        Organization organization = RedisUtil.getObj(key + id, Organization.class);
        if (organization == null) {
            organization = organizationMapper.selectByPrimaryKey(id);
            RedisUtil.setObj(key + id, organization, 10 * 60);
        }

        return organization;
    }

    /**
     * 根据组织编码获取对象
     *
     * @param orgCode 组织编码
     * @return
     */
    @Override
    public Organization findCacheByOrgCode(String orgCode) {
        String key = "FindCacheByOrgCode:orgCode:" + orgCode;
        Organization organization = RedisUtil.getObj(key, Organization.class);
        if (organization == null) {
            organization = organizationMapper.selectByOrgCode(orgCode);
            RedisUtil.setObj(key, organization, 10 * 60);
        }

        return organization;
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
     * 根据id删除组织数据
     *
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Integer> ids) {
        return organizationMapper.deleteByIds(ids);
    }

    /**
     * 添加组织
     *
     * @param organization
     * @return
     */
    @Override
    public int save(Organization organization) {
        organization.setStatus(OrganizationConstant.STATUS_INIT);
        return organizationMapper.insert(organization);
    }

    /**
     * 表单校验
     *
     * @param organization
     * @return
     */
    @Override
    public String checkForm(Organization organization) {
        if (StringUtils.isEmpty(organization.getOrgCode())) {
            return "组织编码不能为空";
        }
        if (StringUtils.isEmpty(organization.getOrgName())) {
            return "组织名称不能为空";
        }
        return null;
    }

    /**
     * 获取所有组织列表
     * @return
     */
    @Override
    public List<Organization> findAllCache() {
        String key = "Organization:all";
        String result = RedisUtil.get(key);
        List<Organization> organizations = JSONArray.parseArray(result, Organization.class);
        if (StringUtils.isEmpty(result)) {
            organizations = organizationMapper.getAllOrganizations();
            RedisUtil.set(key, JSONArray.toJSONString(organizations), 10 * 60);
        }

        return organizations;
    }

    /**
     * 获取所有组织列表
     * @return
     */
    @Override
    public List<Organization> findAll() {
        return organizationMapper.getAllOrganizations();
    }
}
