package com.yotrio.pound.service.impl;

import com.yotrio.pound.dao.CompanyMapper;
import com.yotrio.pound.model.Company;
import com.yotrio.pound.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公司逻辑类
 * 模块名称：projects-parent com.yotrio.pound.service
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-10-26 10:50
 * 系统版本：1.0.0
 **/
@Service("companyService")
public class CompanyServiceImpl implements ICompanyService {

    @Autowired
    private CompanyMapper companyMapper;

    /**
     * 根据id获取对象
     *
     * @param id 主键id
     * @return
     */
    @Override
    public Company findById(Integer id) {
        return companyMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据供应商编码获取供应商
     *
     * @param compCode 供应商编码
     * @return
     */
    @Override
    public Company findByCompCode(String compCode) {
        return companyMapper.findByCompCode(compCode);
    }

    /**
     * 根据供应商编码获取供应商名称
     *
     * @param compCode 供应商编码
     * @return
     */
    @Override
    public String findCompNameByCompCode(String compCode) {
        return companyMapper.findCompNameByCompCode(compCode);
    }

    /**
     * 更新公司数据
     *
     * @param company
     * @return
     */
    @Override
    public int updateById(Company company) {
        return companyMapper.updateByPrimaryKeySelective(company);
    }
    /**
     * 更新公司数据 by compCode
     *
     * @param company
     * @return
     */
    @Override
    public int updateByCompCode(Company company) {
        return companyMapper.updateByCompCodeSelective(company);
    }


    /**
     * 根据id删除公司数据
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(Integer id) {
        return companyMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加公司
     *
     * @param company
     * @return
     */
    @Override
    public int save(Company company) {
        return companyMapper.insert(company);
    }

    /**
     * 获取所有供应商列表
     *
     * @return
     */
    @Override
    public List<Company> findAll() {
        return companyMapper.findAll();
    }
}
