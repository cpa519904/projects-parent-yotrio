package com.yotrio.pound.dao;

import com.yotrio.pound.model.PoundLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface PoundLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoundLog record);

    int insertSelective(PoundLog record);

    PoundLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PoundLog record);

    int updateByPrimaryKey(PoundLog record);

    List<PoundLog> selectListByMap(Map<String, Object> map);

    PoundLog findByPoundLogNo(String plNo);

    List<PoundLog> listUnFinished(Integer status);

    PoundLog findLogWithInspectionsByPoundLogNo(String poundLogNo);

    int deleteByPoundLogNo(String poundLogNo);
}