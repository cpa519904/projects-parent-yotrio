package com.yotrio.pound.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yotrio.common.constants.InspectionConstant;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.common.constants.PoundLogConstant;
import com.yotrio.pound.dao.InspectionMapper;
import com.yotrio.pound.dao.PoundLogMapper;
import com.yotrio.pound.model.Inspection;
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
    @Autowired
    private InspectionMapper inspectionMapper;

    /**
     * 分页获取过磅数据
     *
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
     *
     * @param poundLog
     * @return
     */
    @Override
    public Integer save(PoundLog poundLog) {
        poundLog.setCreateTime(new Date());
        return poundLogMapper.insert(poundLog);
    }

    /**
     * 添加过磅记录表单校验
     *
     * @param poundLog
     * @return
     */
    @Override
    public String checkFormSave(PoundLog poundLog) {
        if (poundLog == null) {
            return "过磅信息为空,请检查设备是否异常";
        }
        if (poundLog.getPoundId() == null) {
            return "地磅ID不能为空";
        }
        if (StringUtils.isEmpty(poundLog.getUnitName())) {
            return "收货单位不能为空";
        }
        if (poundLog.getGrossWeight() == null || poundLog.getGrossWeight() <= 0.0d) {
            return "过磅重量不能为空";
        }
        if (poundLog.getGrossImgUrl() == null) {
            return "过磅照片不能为空";
        }

        return null;
    }

    /**
     * 更新过磅记录表单校验
     *
     * @param poundLog
     * @return
     */
    @Override
    public String checkFormUpdate(PoundLog poundLog) {
        if (poundLog == null) {
            return "过磅信息为空,请检查设备是否异常";
        }
        if (poundLog.getPoundId() == null) {
            return "地磅ID不能为空";
        }
        if (poundLog.getTareWeight() == null) {
            return "过磅重量不能为空";
        }
        if (poundLog.getTareImgUrl() == null) {
            return "过磅照片不能为空";
        }
        return null;
    }

    /**
     * 根据ID获取过磅记录
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
     * 更具过磅单单号获取过磅记录
     *
     * @param plNo
     * @return
     */
    @Override
    public PoundLog findByPoundLogNo(String plNo) {
        return poundLogMapper.findByPoundLogNo(plNo);
    }

    /**
     * 查出未完成的过磅单记录
     *
     * @return
     */
    @Override
    public List<PoundLog> listUnFinished() {
        return poundLogMapper.listUnFinished(PoundLogConstant.STATUS_POUND_FINISH);
    }

    /**
     * 根据过磅单单号查出过磅单及对应的报检单
     *
     * @param poundLogNo
     * @return
     */
    @Override
    public PoundLog findLogWithInspectionsByPoundLogNo(String poundLogNo) {
        return poundLogMapper.findLogWithInspectionsByPoundLogNo(poundLogNo);
    }

    /**
     * 删除过磅单以及相应的报检单
     *
     * @param poundLogNo
     */
    @Override
    public void destroyPoundLogAndInspections(String poundLogNo) {
        poundLogMapper.deleteByPoundLogNo(poundLogNo);
        inspectionMapper.deleteByPlNo(poundLogNo);
    }

    /**
     * 操作报检单后，更新过磅单数据
     * @param logInDB
     * @return
     */
    @Override
    public String updateWeight(PoundLog logInDB) {
        try {
            double totalReturnWeight = 0.0d;
            double totalSampleWeight = 0.0d;
            double totalInspWeight = 0.0d;
            List<Inspection> inspections = inspectionMapper.findListByPlNo(logInDB.getPoundLogNo());
            for (Inspection item : inspections) {
                totalInspWeight += item.getInspWeight();
                totalReturnWeight += item.getReturnWeight();
                if (item.getTypes() == InspectionConstant.TYPE_SAMPLE) {
                    totalSampleWeight += item.getInspWeight();
                }
            }
            if (logInDB.getNetWeight() != null) {
                logInDB.setDiffWeight(logInDB.getNetWeight() - totalInspWeight);
            }
            logInDB.setInspWeightTotal(totalInspWeight);
            logInDB.setReturnWeightTotal(totalReturnWeight);
            logInDB.setSampleNetWeight(totalSampleWeight);
            logInDB.setUpdateTime(new Date());
            poundLogMapper.updateByPrimaryKeySelective(logInDB);
            return null;
        } catch (Exception e) {
            return "更新过磅单数据异常";
        }
    }


}