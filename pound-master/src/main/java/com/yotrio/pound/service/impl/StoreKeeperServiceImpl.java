package com.yotrio.pound.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yotrio.common.constants.StoreKeeperConstant;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.dao.StoreKeeperMapper;
import com.yotrio.pound.model.StoreKeeper;
import com.yotrio.pound.model.dto.StoreKeeperDto;
import com.yotrio.pound.service.IStoreKeeperService;
import com.yotrio.pound.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 仓库逻辑层
 * 模块名称：projects-parent com.yotrio.pound.service.impl
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-11-09 17:02
 * 系统版本：1.0.0
 **/
@Service("storeKeeperService")
public class StoreKeeperServiceImpl implements IStoreKeeperService {


    @Autowired
    private StoreKeeperMapper storeKeeperMapper;

    /**
     * 分页获取仓库数据
     *
     * @param dataTablePage  分页条件
     * @param storeKeeperDto 查询条件
     * @return
     */
    @Override
    public PageInfo findAllPaging(DataTablePage dataTablePage, StoreKeeperDto storeKeeperDto) {
        PageHelper.startPage(dataTablePage.getPage(), dataTablePage.getLimit());
        List<StoreKeeper> storeKeeperList = storeKeeperMapper.selectListByMap(BeanUtil.beanToMap(storeKeeperDto));
        PageInfo pageInfo = new PageInfo(storeKeeperList);
        return pageInfo;
    }

    /**
     * 根据id获取对象
     *
     * @param id 主键id
     * @return
     */
    @Override
    public StoreKeeper findCacheById(Integer id) {
        String key = "StoreKeeper:id:";
        StoreKeeper storeKeeper = RedisUtil.getObj(key + id, StoreKeeper.class);
        if (storeKeeper == null) {
            storeKeeper = storeKeeperMapper.selectByPrimaryKey(id);
            RedisUtil.setObj(key + id, storeKeeper, 10 * 60);
        }

        return storeKeeper;
    }

    /**
     * 更新仓库数据
     *
     * @param storeKeeper
     * @return
     */
    @Override
    public int updateById(StoreKeeper storeKeeper) {
        storeKeeper.setUpdateTime(new Date());
        return storeKeeperMapper.updateByPrimaryKeySelective(storeKeeper);
    }

    /**
     * 根据id删除仓库数据
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(Integer id) {
        return storeKeeperMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id删除仓库数据
     *
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Integer> ids) {
        return storeKeeperMapper.deleteByIds(ids);
    }

    /**
     * 添加仓库
     *
     * @param storeKeeper
     * @return
     */
    @Override
    public int save(StoreKeeper storeKeeper) {
        storeKeeper.setStatus(StoreKeeperConstant.STATUS_INIT);
        return storeKeeperMapper.insert(storeKeeper);
    }

    /**
     * 表单校验
     *
     * @param storeKeeper
     * @return
     */
    @Override
    public String checkForm(StoreKeeper storeKeeper) {
        if (StringUtils.isEmpty(storeKeeper.getGoodsCode())) {
            return "商品名称不能为空";
        }
        if (StringUtils.isEmpty(storeKeeper.getOrgCode())) {
            return "组织名称不能为空";
        }
        return null;
    }

}