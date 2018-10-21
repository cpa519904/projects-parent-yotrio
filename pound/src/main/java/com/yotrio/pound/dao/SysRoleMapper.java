package com.yotrio.pound.dao;

import com.yotrio.pound.model.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> selectListByMap(Map<String, Object> map);

    Set<String> findRolesByUserId(Integer userId);

    Set<Integer> findRoleIdsByUserId(Integer userId);

    int deleteByIds(List<Integer> ids);

    SysRole findByRoleName(String roleName);
}