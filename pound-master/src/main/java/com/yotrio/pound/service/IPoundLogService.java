package com.yotrio.pound.service;

import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.model.PoundLog;
import com.yotrio.pound.model.dto.PoundLogDto;

import java.util.List;

/**
 * 模块名称：projects-parent com.yotrio.pound.service
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-09-25 15:38
 * 系统版本：1.0.0
 **/
public interface IPoundLogService {
    PageInfo findAllPaging(DataTablePage dataTablePage, PoundLogDto poundLogDto);

    int deleteById(Integer id);

    int deleteByIds(List<Integer> idList);

    PoundLog findByPoundLogNo(String deliveryNumb);

    Integer save(PoundLog poundLog);

    PoundLog findById(Integer id);

    Integer update(PoundLog poundLog);

    int updateByPlNoAndPoundId(PoundLog poundLog);

    PoundLog findByPoundLogNoAndPoundId(String poundLogNo, Integer poundId);
}
