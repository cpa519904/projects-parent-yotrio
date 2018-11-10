package com.yotrio.pound.service;

import com.yotrio.pound.model.Organization;

import java.util.List;

/**
 * 模块名称：projects-parent com.yotrio.pound.service
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-11-10 10:32
 * 系统版本：1.0.0
 **/
public interface IOrganizationService {
    Organization findById(Integer id);

    Organization findByOrgCode(String orgCode);

    int updateById(Organization organization);

    int deleteById(Integer id);

    int save(Organization organization);

    List<Organization> findAll();

    int updateByOrgCode(Organization organization);

    String findOrgNameByOrgCode(String orgCode);
}
