package com.yotrio.pound.dao;

import com.yotrio.pound.model.PoundLog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PoundLogMapper {
    int deleteByPrimaryKey(Integer id);

    Integer insert(PoundLog record);

    int insertSelective(PoundLog record);

    PoundLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PoundLog record);

    int updateByPrimaryKey(PoundLog record);

    List<PoundLog> selectListByMap(Map<String, Object> map);

    PoundLog findByPoundLogNo(String plNo);

    List<PoundLog> listUnFinished(Integer status);

    int deleteByIds(List<Integer> ids);

    int updateByPlNoAndPoundIdSelective(PoundLog poundLog);

    PoundLog findByPoundLogNoAndPoundId(PoundLog poundLog);
}