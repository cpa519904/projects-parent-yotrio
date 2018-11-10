package com.yotrio.pound.service;

import com.yotrio.pound.model.Goods;

import java.util.List;

/**
 * 模块名称：projects-parent com.yotrio.pound.service
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-11-10 10:27
 * 系统版本：1.0.0
 **/
public interface IGoodsService {
    Goods findByGoodsCode(String goodsCode);

    int updateById(Goods goods);

    int deleteById(Integer id);

    int save(Goods goods);

    List<Goods> findAll();

    int updateByGoodsCode(Goods goods);

    String findGoodsNameByGoodsCode(String goodsCode);
}
