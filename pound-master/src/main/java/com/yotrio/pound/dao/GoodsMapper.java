package com.yotrio.pound.dao;

import com.yotrio.pound.model.Goods;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    List<Goods> selectListByMap(Map<String, Object> beanToMap);

    int deleteByIds(List<Integer> ids);

    List<Goods> findAll();

    Goods selectByGoodsCode(String goodsCode);

    String findGoodsNameByGoodsCode(String goodsCode);
}