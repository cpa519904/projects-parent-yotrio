package com.yotrio.pound.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.dao.PoundLogMapper;
import com.yotrio.pound.model.PoundLog;
import com.yotrio.pound.model.dto.PoundLogDto;
import com.yotrio.pound.service.IPoundLogService;
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
     *
     * @param dataTablePage
     * @param poundLogDto
     * @return
     */
    @Override
    public PageInfo findAllPaging(DataTablePage dataTablePage, PoundLogDto poundLogDto) {
        PageHelper.startPage(dataTablePage.getPage(), dataTablePage.getLimit());
        List<PoundLog> poundLogs = poundLogMapper.selectListByMap(BeanUtil.beanToMap(poundLogDto));
        PageInfo pageInfo = new PageInfo(poundLogs);
        return pageInfo;
    }

    /**
     * 根据id删除过磅数据
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(Integer id) {
        return poundLogMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id删除过磅数据
     *
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Integer> ids) {
        return poundLogMapper.deleteByIds(ids);
    }

    /**
     * 根据过磅单号获取过磅记录
     *
     * @param deliveryNumb
     * @return
     */
    @Override
    public PoundLog findByPoundLogNo(String deliveryNumb) {
        return poundLogMapper.findByPoundLogNo(deliveryNumb);
    }

    /**
     * 新增过磅记录
     *
     * @param poundLog
     * @return
     */
    @Override
    public Integer save(PoundLog poundLog) {
        return poundLogMapper.insert(poundLog);
    }

    /**
     * 更具id获取过磅记录
     *
     * @param id
     * @return
     */
    @Override
    public PoundLog findById(Integer id) {
        return poundLogMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新供货单
     *
     * @param poundLog
     * @return
     */
    @Override
    public Integer update(PoundLog poundLog) {
        poundLog.setUpdateTime(new Date());
        return poundLogMapper.updateByPrimaryKeySelective(poundLog);
    }

    /**
     * 根据poundID跟poundLogNo更新过磅记录
     *
     * @param poundLog
     * @return
     */
    @Override
    public int updateByPlNoAndPoundId(PoundLog poundLog) {
        return poundLogMapper.updateByPlNoAndPoundIdSelective(poundLog);
    }

    /**
     * 根据poundID跟poundLogNo查找过磅记录
     * @param poundLogNo
     * @param poundId
     * @return
     */
    @Override
    public PoundLog findByPoundLogNoAndPoundId(String poundLogNo, Integer poundId) {
        PoundLog poundLog = new PoundLog();
        poundLog.setPoundLogNo(poundLogNo);
        poundLog.setPoundId(poundId);
        return poundLogMapper.findByPoundLogNoAndPoundId(poundLog);
    }
}