package com.yotrio.pound.service;

import com.yotrio.pound.model.Company;
import com.yotrio.pound.model.Goods;
import com.yotrio.pound.model.Organization;
import com.yotrio.pound.model.PoundInfo;

import java.util.List;

/**
 * 模块名称：projects-parent com.yotrio.pound.service
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-10-26 10:50
 * 系统版本：1.0.0
 **/
public interface IHttpService {
    PoundInfo getPoundInfo(Integer id);

    List<Company> findAllCompany();

    List<Goods> findAllGoods();

    List<Organization> findAllOrganization();
}
