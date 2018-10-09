package com.yotrio.pound.service;

import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.model.PoundLog;

/**
 * 模块名称：projects-parent com.yotrio.pound.service
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-09-25 15:38
 * 系统版本：1.0.0
 **/
public interface IPoundLogService {
    PageInfo findAllPaging(DataTablePage dataTablePage, PoundLog poundLog);

    Integer save(PoundLog poundLog);

    String checkFormSave(PoundLog poundLog);

    String checkFormUpdate(PoundLog poundLog);

    PoundLog findByDeliveryNumb(String deliveryNumb);

    PoundLog findById(Integer id);

    Integer update(PoundLog poundLog);
}
