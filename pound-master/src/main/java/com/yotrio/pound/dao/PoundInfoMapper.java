package com.yotrio.pound.dao;

import com.yotrio.pound.model.PoundInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PoundInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoundInfo record);

    int insertSelective(PoundInfo record);

    PoundInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PoundInfo record);

    int updateByPrimaryKey(PoundInfo record);

    List<PoundInfo> selectListByMap(Map<String, Object> map);

    int deleteByIds(List<Integer> idList);
}