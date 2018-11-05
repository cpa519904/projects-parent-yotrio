package com.yotrio.pound.dao;

import com.yotrio.pound.model.Inspection;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface InspectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Inspection record);

    int insertSelective(Inspection record);

    Inspection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Inspection record);

    int updateByPrimaryKey(Inspection record);

    List<Inspection> selectListByMap(Map<String, Object> map);

    int deleteByIds(List<Integer> idList);

    List<Inspection> findListByPlNo(String poundLogNo);

    List<Inspection> findListByPlId(Integer poundLogId);

    Inspection findByInspNo(String inspNo);
}