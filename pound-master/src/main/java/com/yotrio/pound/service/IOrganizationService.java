package com.yotrio.pound.service;

import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.model.Organization;
import com.yotrio.pound.model.dto.OrganizationDto;

import java.util.List;

/**
 * 模块名称：projects-parent com.yotrio.pound.service
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-11-09 13:16
 * 系统版本：1.0.0
 **/
public interface IOrganizationService {

    PageInfo findAllPaging(DataTablePage dataTablePage, OrganizationDto organizationDto);

    Organization findCacheById(Integer id);

    Organization findCacheByOrgCode(String orgCode);

    int updateById(Organization organization);

    int deleteById(Integer id);

    int deleteByIds(List<Integer> ids);

    int save(Organization organization);

    String checkForm(Organization organization);

    List<Organization> findAllCache();

    List<Organization> findAll();
}
