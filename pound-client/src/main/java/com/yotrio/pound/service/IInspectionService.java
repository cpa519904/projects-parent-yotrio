package com.yotrio.pound.service;

import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.model.Inspection;
import com.yotrio.pound.model.PoundLog;

import java.util.List;

/**
 * 模块名称：projects-parent com.yotrio.pound.service
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-10-12 16:18
 * 系统版本：1.0.0
 **/
public interface IInspectionService {

    int save(Inspection inspection);

    String checkFormSave(Inspection inspection);

    PageInfo findAllPaging(DataTablePage dataTablePage, Inspection inspection);

    int update(Inspection inspection);

    int deleteByIds(List<Integer> idList);

    List<Inspection> findListByPlNo(String poundLogNo);

    Inspection findByInspNo(String inspNo);

    void countInspNetWeight(List<Inspection> inspections, PoundLog poundLog);

    void deleteHistoryInspections(Integer beforeTime);
}
