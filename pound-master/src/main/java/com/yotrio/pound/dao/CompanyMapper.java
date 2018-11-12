package com.yotrio.pound.dao;

import com.yotrio.pound.model.Company;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);

    List<Company> selectListByMap(Map<String, Object> beanToMap);

    int deleteByIds(List<Integer> ids);

    List<Company> findAll();

    Company findByCompCode(String compCode);

}