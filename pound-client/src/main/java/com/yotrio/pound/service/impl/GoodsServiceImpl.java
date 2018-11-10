package com.yotrio.pound.service.impl;

import com.yotrio.pound.dao.GoodsMapper;
import com.yotrio.pound.model.Goods;
import com.yotrio.pound.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 货品逻辑层
 * 模块名称：projects-parent com.yotrio.pound.service.impl
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-11-09 13:21
 * 系统版本：1.0.0
 **/
@Service("goodsService")
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 根据货品编码获取对象
     *
     * @param goodsCode 货品编码
     * @return
     */
    @Override
    public Goods findByGoodsCode(String goodsCode) {
        return goodsMapper.selectByGoodsCode(goodsCode);
    }

    /**
     * 根据货品编码获取对象
     *
     * @param goodsCode 货品编码
     * @return
     */
    @Override
    public String findGoodsNameByGoodsCode(String goodsCode) {
        return goodsMapper.findGoodsNameByGoodsCode(goodsCode);
    }

    /**
     * 更新货品数据
     *
     * @param goods
     * @return
     */
    @Override
    public int updateById(Goods goods) {
        goods.setUpdateTime(new Date());
        goods.setLastUpdateTime(new Date());
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }

    /**
     * 根据id删除货品数据
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(Integer id) {
        return goodsMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加货品
     *
     * @param goods
     * @return
     */
    @Override
    public int save(Goods goods) {
        return goodsMapper.insert(goods);
    }

    /**
     * 获取所有物料列表
     *
     * @return
     */
    @Override
    public List<Goods> findAll() {
        return goodsMapper.findAll();
    }

    @Override
    public int updateByGoodsCode(Goods goods) {
        return goodsMapper.updateByGoodsCodeSelective(goods);
    }
}