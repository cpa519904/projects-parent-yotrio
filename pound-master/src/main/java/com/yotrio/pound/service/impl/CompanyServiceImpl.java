package com.yotrio.pound.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yotrio.common.constants.CompanyConstant;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.dao.CompanyMapper;
import com.yotrio.pound.model.Company;
import com.yotrio.pound.model.dto.CompanyDto;
import com.yotrio.pound.service.ICompanyService;
import com.yotrio.pound.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
     * 分页获取公司数据
     *
     * @param dataTablePage 分页条件
     * @param companyDto    查询条件
     * @return
     */
    @Override
    public PageInfo findAllPaging(DataTablePage dataTablePage, CompanyDto companyDto) {
        PageHelper.startPage(dataTablePage.getPage(), dataTablePage.getLimit());
        List<Company> companyList = companyMapper.selectListByMap(BeanUtil.beanToMap(companyDto));
        PageInfo pageInfo = new PageInfo(companyList);
        return pageInfo;
    }

    /**
     * 根据id获取对象
     *
     * @param id 主键id
     * @return
     */
    @Override
    public Company findCacheById(Integer id) {
        String key = "Company:id:";
        Company company = RedisUtil.getObj(key + id, Company.class);
        if (company == null) {
            company = companyMapper.selectByPrimaryKey(id);
            RedisUtil.setObj(key + id, company, 10 * 60);
        }

        return company;
    }

    /**
     * 更新公司数据
     *
     * @param company
     * @return
     */
    @Override
    public int updateById(Company company) {
        company.setUpdateTime(new Date());
        return companyMapper.updateByPrimaryKeySelective(company);
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
     * 根据id删除公司数据
     *
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Integer> ids) {
        return companyMapper.deleteByIds(ids);
    }

    /**
     * 添加公司
     *
     * @param company
     * @return
     */
    @Override
    public int save(Company company) {
        company.setStatus(CompanyConstant.STATUS_INIT);
        return companyMapper.insert(company);
    }

    /**
     * 表单校验
     *
     * @param company
     * @return
     */
    @Override
    public String checkForm(Company company) {
        if (StringUtils.isEmpty(company.getCompCode())) {
            return "公司编码不能为空";
        }
        if (StringUtils.isEmpty(company.getCompName())) {
            return "公司名称不能为空";
        }
        return null;
    }
}
