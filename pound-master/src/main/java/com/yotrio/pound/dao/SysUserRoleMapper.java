package com.yotrio.pound.dao;

import com.yotrio.pound.model.SysUserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserRole record);

    int insertSelective(SysUserRole record);

    SysUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserRole record);

    int updateByPrimaryKey(SysUserRole record);
}