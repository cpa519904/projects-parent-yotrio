package com.yotrio.pound.service;

import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.model.StoreKeeper;
import com.yotrio.pound.model.dto.StoreKeeperDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 模块名称：projects-parent com.yotrio.pound.service
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-11-09 13:18
 * 系统版本：1.0.0
 **/
public interface IStoreKeeperService {

    PageInfo findAllPaging(DataTablePage dataTablePage, StoreKeeperDto storeKeeperDto);

    StoreKeeper findCacheById(Integer id);

    int updateById(StoreKeeper storeKeeper);

    int deleteById(Integer id);

    int deleteByIds(List<Integer> ids);

    int save(StoreKeeper storeKeeper);

    String checkForm(StoreKeeper storeKeeper);
}
