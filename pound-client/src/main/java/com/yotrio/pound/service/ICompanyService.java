package com.yotrio.pound.service;

import com.yotrio.pound.model.Company;

import java.util.List;

/**
 * 模块名称：projects-parent com.yotrio.pound.service
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-11-10 10:35
 * 系统版本：1.0.0
 **/
public interface ICompanyService {
    Company findById(Integer id);

    List<Company> findAll();

    Company findByCompCode(String compCode);

    int updateById(Company company);

    int updateByCompCode(Company company);

    int deleteById(Integer id);

    int save(Company company);
}
