package com.yotrio.pound.service;

import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.model.Company;
import com.yotrio.pound.model.dto.CompanyDto;

import java.util.List;

/**
 * 模块名称：projects-parent com.yotrio.pound.service
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-11-09 13:14
 * 系统版本：1.0.0
 **/
public interface ICompanyService {

    PageInfo findAllPaging(DataTablePage dataTablePage, CompanyDto companyDto);

    Company findCacheById(Integer id);

    List<Company> findAllCache();

    int updateById(Company company);

    int deleteById(Integer id);

    int deleteByIds(List<Integer> ids);

    int save(Company company);

    String checkForm(Company company);
}
