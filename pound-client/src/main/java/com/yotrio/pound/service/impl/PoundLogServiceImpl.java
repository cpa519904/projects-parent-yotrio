package com.yotrio.pound.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.constants.PoundLogConstant;
import com.yotrio.pound.dao.PoundLogMapper;
import com.yotrio.pound.model.PoundLog;
import com.yotrio.pound.service.IPoundLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 过磅记录接口服务层
 * 模块名称：projects-parent com.yotrio.pound.service.impl
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-09-25 15:37
 * 系统版本：1.0.0
 **/

@Service("poundLogService")
public class PoundLogServiceImpl implements IPoundLogService {

    @Autowired
    private PoundLogMapper poundLogMapper;

    /**
     * 分页获取过磅数据
     * @param dataTablePage
     * @param poundLog
     * @return
     */
    @Override
    public PageInfo findAllPaging(DataTablePage dataTablePage, PoundLog poundLog) {
        PageHelper.startPage(dataTablePage.getPage(), dataTablePage.getLimit());
        List<PoundLog> poundLogs = poundLogMapper.selectListByMap(BeanUtil.beanToMap(poundLog));
        PageInfo pageInfo = new PageInfo(poundLogs);
        return pageInfo;
    }

    /**
     * 新增过磅记录
     * @param poundLog
     * @return
     */
    @Override
    public Integer save(PoundLog poundLog) {
        poundLog.setCreateTime(new Date());
        poundLog.setStatus(PoundLogConstant.STATUS_POUND_FIRST);
        return poundLogMapper.insert(poundLog);
    }

    @Override
    public String checkForm(PoundLog poundLog) {
        if (poundLog == null) {
            return "过磅信息为空,请检查设备是否异常";
        }
        if (StringUtils.isEmpty(poundLog.getDeliveryNumb())) {
            return "供货单号不能为空";
        }
        if (StringUtils.isEmpty(poundLog.getPlateNumb())) {
            return "车牌号不能为空";
        }
        if (StringUtils.isEmpty(poundLog.getCompName())) {
            return "供应商不能为空";
        }
        if (StringUtils.isEmpty(poundLog.getGoodsName())) {
            return "货品不能为空";
        }
        if (StringUtils.isEmpty(poundLog.getUnitName())) {
            return "收货单位不能为空";
        }
        return null;
    }


}