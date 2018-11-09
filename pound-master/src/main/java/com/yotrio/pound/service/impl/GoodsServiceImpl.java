package com.yotrio.pound.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yotrio.common.constants.GoodsConstant;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.dao.GoodsMapper;
import com.yotrio.pound.model.Goods;
import com.yotrio.pound.model.dto.GoodsDto;
import com.yotrio.pound.service.IGoodsService;
import com.yotrio.pound.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
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
     * 分页获取货品数据
     *
     * @param dataTablePage 分页条件
     * @param goodsDto      查询条件
     * @return
     */
    @Override
    public PageInfo findAllPaging(DataTablePage dataTablePage, GoodsDto goodsDto) {
        PageHelper.startPage(dataTablePage.getPage(), dataTablePage.getLimit());
        List<Goods> goodsList = goodsMapper.selectListByMap(BeanUtil.beanToMap(goodsDto));
        PageInfo pageInfo = new PageInfo(goodsList);
        return pageInfo;
    }

    /**
     * 根据id获取对象
     *
     * @param id 主键id
     * @return
     */
    @Override
    public Goods findCacheById(Integer id) {
        String key = "Goods:id:";
        Goods goods = RedisUtil.getObj(key + id, Goods.class);
        if (goods == null) {
            goods = goodsMapper.selectByPrimaryKey(id);
            RedisUtil.setObj(key + id, goods, 10 * 60);
        }

        return goods;
    }

    /**
     * 根据货品编码获取对象
     *
     * @param goodsCode 货品编码
     * @return
     */
    @Override
    public Goods findCacheByGoodsCode(String goodsCode) {
        String key = "FindCacheByGoodsCode:goodsCode:" + goodsCode;
        Goods goods = RedisUtil.getObj(key, Goods.class);
        if (goods == null) {
            goods = goodsMapper.selectByGoodsCode(goodsCode);
            RedisUtil.setObj(key, goods, 10 * 60);
        }

        return goods;
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
     * 根据id删除货品数据
     *
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Integer> ids) {
        return goodsMapper.deleteByIds(ids);
    }

    /**
     * 添加货品
     *
     * @param goods
     * @return
     */
    @Override
    public int save(Goods goods) {
        //时间戳作为货品编码
        goods.setGoodsCode(String.valueOf(System.currentTimeMillis()));
        goods.setStatus(GoodsConstant.STATUS_INIT);
        return goodsMapper.insert(goods);
    }

    /**
     * 表单校验
     *
     * @param goods
     * @return
     */
    @Override
    public String checkForm(Goods goods) {
        if (StringUtils.isEmpty(goods.getGoodsName())) {
            return "物料名称不能为空";
        }
        return null;
    }

    /**
     * 获取所有物料列表
     *
     * @return
     */
    @Override
    public List<Goods> findAllCache() {
        String key = "Goods:all";
        String result = RedisUtil.get(key);
        List<Goods> goods = JSONArray.parseArray(result, Goods.class);
        if (StringUtils.isEmpty(result)) {
            goods = goodsMapper.findAll();
            RedisUtil.set(key, JSONArray.toJSONString(goods), 10 * 60);
        }

        return goods;
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
}