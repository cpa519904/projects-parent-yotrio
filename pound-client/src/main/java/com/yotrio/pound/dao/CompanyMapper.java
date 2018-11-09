package com.yotrio.pound.dao;

import com.yotrio.pound.model.Company;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Company record);

    int insertSelective(Company record);

    Company selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Company record);

    int updateByPrimaryKey(Company record);
}