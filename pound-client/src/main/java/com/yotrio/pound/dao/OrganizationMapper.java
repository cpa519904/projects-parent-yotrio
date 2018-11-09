package com.yotrio.pound.dao;

import com.yotrio.pound.model.Organization;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrganizationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);
}