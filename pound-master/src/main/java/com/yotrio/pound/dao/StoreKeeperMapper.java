package com.yotrio.pound.dao;

import com.yotrio.pound.model.StoreKeeper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StoreKeeperMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(StoreKeeper record);

    int insertSelective(StoreKeeper record);

    StoreKeeper selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreKeeper record);

    int updateByPrimaryKey(StoreKeeper record);

    List<StoreKeeper> selectListByMap(Map<String, Object> beanToMap);

    int deleteByIds(List<Integer> ids);

    List<StoreKeeper> findByOrgCodeAndGoodsCode(@Param("orgCode") String orgCode, @Param("goodsCode") String goodsCode);
}