package com.yotrio.pound.dao;

import com.yotrio.pound.model.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    Goods selectByGoodsCode(String goodsCode);

    List<Goods> findAll();

    int updateByGoodsCodeSelective(Goods goods);

    String findGoodsNameByGoodsCode(String goodsCode);
}