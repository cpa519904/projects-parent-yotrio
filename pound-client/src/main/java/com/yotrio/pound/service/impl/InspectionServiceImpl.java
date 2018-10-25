package com.yotrio.pound.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yotrio.common.constants.InspectionConstant;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.dao.InspectionMapper;
import com.yotrio.pound.dao.PoundLogMapper;
import com.yotrio.pound.model.Inspection;
import com.yotrio.pound.model.PoundLog;
import com.yotrio.pound.service.IInspectionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 报检单逻辑层
 * 模块名称：projects-parent com.yotrio.pound.service.impl
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-10-12 16:15
 * 系统版本：1.0.0
 **/

@Service("inspectionService")
public class InspectionServiceImpl implements IInspectionService {

    @Autowired
    private InspectionMapper inspectionMapper;
    @Autowired
    private PoundLogMapper poundLogMapper;

    /**
     * 新增报检单
     *
     * @param inspection
     * @return
     */
    @Override
    public int save(Inspection inspection) {
        if (inspection.getTypes() == null) {
            //默认为非样品
            inspection.setTypes(InspectionConstant.TYPE_INIT);
        }
        inspection.setCreateTime(new Date());
        inspection.setStatus(InspectionConstant.STATUS_INIT);
        return inspectionMapper.insert(inspection);
    }

    /**
     * 修改报检单
     *
     * @param inspection
     * @return
     */
    @Override
    public int update(Inspection inspection) {
        if (inspection.getTypes() == null) {
            //默认为非样品
            inspection.setTypes(InspectionConstant.TYPE_INIT);
        }
        inspection.setUpdateTime(new Date());
        inspection.setStatus(InspectionConstant.STATUS_INIT);
        return inspectionMapper.updateByPrimaryKeySelective(inspection);
    }

    /**
     * 根据id删除
     *
     * @param idList
     * @return
     */
    @Override
    public int deleteByIds(List<Integer> idList) {
        return inspectionMapper.deleteByIds(idList);
    }

    /**
     * 根据过磅单号获取过磅单列表
     *
     * @param poundLogNo
     * @return
     */
    @Override
    public List<Inspection> findListByPlNo(String poundLogNo) {
        return inspectionMapper.findListByPlNo(poundLogNo);
    }

    /**
     * 根据报检单单号查询报检单
     *
     * @param inspNo
     * @return
     */
    @Override
    public Inspection findByInspNo(String inspNo) {
        return inspectionMapper.findByInspNo(inspNo);
    }

    /**
     * 计算每张报检单的称重重量，按报检单上重量比例分配
     *
     * @param inspections
     * @param poundLog
     */
    @Override
    public void countInspNetWeight(List<Inspection> inspections, PoundLog poundLog) {
        //报检单总重量
        double totalInspWeight = 0.0d;
        for (Inspection inspection : inspections) {
            totalInspWeight += inspection.getInspWeight();
        }
        poundLog.setInspWeightTotal(totalInspWeight);
        poundLog.setUpdateTime(new Date());
        poundLogMapper.updateByPrimaryKeySelective(poundLog);

        //样品重量+非样品重量
        double totalNetWeight = poundLog.getNetWeight() + poundLog.getSampleNetWeight();
        //报检单称重后，我们给的总净重
        double totalInspNetWeight = 0.0d;
        //实际总重按小的来，如果自己称重大，按报检单总重
        if (totalNetWeight >= totalInspWeight) {
            totalInspNetWeight = totalInspWeight;
            for (Inspection inspection : inspections) {
                inspection.setInspNetWeight(inspection.getInspWeight());
                inspectionMapper.updateByPrimaryKeySelective(inspection);
            }
        } else {//如果自己称重小于报检单总重，则按自己的来
            totalInspNetWeight = totalNetWeight;
            double totalCountWeight = 0.0d;
            for (int i = 0; i < inspections.size(); i++) {
                Inspection inspection = inspections.get(i);
                double inspNetWeight = 0.0d;
                if (i < inspections.size() - 1) {
                    //前面按比例计算
                    inspNetWeight = Math.round(inspection.getInspWeight() / totalInspWeight * totalInspNetWeight);
                    totalCountWeight += inspNetWeight;
                } else {
                    //最后一个的时候用减（总数 - 前面报价单重量之和），确保总的重量不变
                    inspNetWeight = totalInspNetWeight - totalCountWeight;
                }

                inspection.setInspNetWeight(inspNetWeight);
                inspectionMapper.updateByPrimaryKeySelective(inspection);
            }
        }
    }

    /**
     * 添加报检单 表单校验
     *
     * @param inspection
     * @return
     */
    @Override
    public String checkFormSave(Inspection inspection) {
        if (StringUtils.isEmpty(inspection.getInspNo())) {
            return "报检单号不能为空";
        }
        if (inspection.getGoodsKind() == null) {
            return "货品类型不能为空";
        }
        if (StringUtils.isEmpty(inspection.getCompName())) {
            return "供货商名称不能为空";
        }
        if (inspection.getInspWeight() == null) {
            return "报检单重量不能为空";
        }

        return null;
    }

    /**
     * 分页获取任务数据
     *
     * @param dataTablePage 分页条件
     * @param inspection    查询条件
     * @return
     */
    @Override

    public PageInfo findAllPaging(DataTablePage dataTablePage, Inspection inspection) {
        PageHelper.startPage(dataTablePage.getPage(), dataTablePage.getLimit());
        List<Inspection> inspectionList = inspectionMapper.selectListByMap(BeanUtil.beanToMap(inspection));
        PageInfo pageInfo = new PageInfo(inspectionList);
        return pageInfo;
    }


}