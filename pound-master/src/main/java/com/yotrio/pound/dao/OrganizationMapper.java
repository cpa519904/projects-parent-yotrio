package com.yotrio.pound.dao;

import com.yotrio.pound.model.Organization;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OrganizationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Organization record);

    int insertSelective(Organization record);

    Organization selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);

    List<Organization> selectListByMap(Map<String, Object> beanToMap);

    int deleteByIds(List<Integer> ids);

    List<Organization> getAllOrganizations();

    Organization selectByOrgCode(String orgCode);

    String findOrgNameByOrgCode(String orgCode);
}