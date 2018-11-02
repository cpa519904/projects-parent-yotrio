package com.yotrio.pound.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.common.enums.GoodsKindEnum;
import com.yotrio.pound.dao.InspectionMapper;
import com.yotrio.pound.dao.PoundLogMapper;
import com.yotrio.pound.model.Inspection;
import com.yotrio.pound.model.PoundLog;
import com.yotrio.pound.model.dto.PoundLogDto;
import com.yotrio.pound.service.IPoundLogService;
import com.yotrio.pound.utils.RedisUtil;
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
    @Autowired
    private InspectionMapper inspectionMapper;

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
        for (PoundLog poundLog : poundLogs) {
            List<Inspection> inspections = inspectionMapper.findListByPlNo(poundLog.getPoundLogNo());
            for (Inspection inspection : inspections) {
                poundLog.setCompName(inspection.getCompName());
                int goodsKind = inspection.getGoodsKind();
                String goodKindName = GoodsKindEnum.getKindName(goodsKind);
                poundLog.setGoodsName(goodKindName);
                if (StringUtils.isNotEmpty(poundLog.getCompName()) && StringUtils.isNotEmpty(poundLog.getGoodsName())) {
                    break;
                }
            }
        }
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
        String key = "PoundLog:id:" + id;
        PoundLog poundLog = RedisUtil.getObj(key, PoundLog.class);
        if (poundLog == null) {
            poundLog = poundLogMapper.selectByPrimaryKey(id);
            if (poundLog != null) {
                RedisUtil.setObj(key, poundLog, 5 * 60);
            }
        }
        return poundLog;
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
     *
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