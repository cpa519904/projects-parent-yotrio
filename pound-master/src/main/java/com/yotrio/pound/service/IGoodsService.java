package com.yotrio.pound.service;

import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.model.Goods;
import com.yotrio.pound.model.dto.GoodsDto;

import java.util.List;

/**
 * 模块名称：projects-parent com.yotrio.pound.service
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-11-09 13:22
 * 系统版本：1.0.0
 **/
public interface IGoodsService {
    PageInfo findAllPaging(DataTablePage dataTablePage, GoodsDto goodsDto);

    Goods findCacheById(Integer id);

    int updateById(Goods goods);

    int deleteById(Integer id);

    int deleteByIds(List<Integer> ids);

    int save(Goods goods);

    String checkForm(Goods goods);

    List<Goods> findAllCache();
}
