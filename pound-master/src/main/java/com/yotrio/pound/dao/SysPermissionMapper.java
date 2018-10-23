package com.yotrio.pound.dao;

import com.yotrio.pound.model.SysPermission;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public interface SysPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    List<SysPermission> selectListByMap(Map<String, Object> map);

    int deleteByIds(List<Integer> ids);

    SysPermission findByPermissionName(String permissionName);

    Set<String> findPermissionsByRoleId(Integer roleId);
}