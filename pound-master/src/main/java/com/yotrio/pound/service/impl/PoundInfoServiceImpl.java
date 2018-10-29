package com.yotrio.pound.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.dao.PoundInfoMapper;
import com.yotrio.pound.model.PoundInfo;
import com.yotrio.pound.service.IPoundInfoService;
import com.yotrio.pound.utils.RedisUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 地磅接口服务层
 * 模块名称：projects-parent com.yotrio.pound.service.impl
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-09-25 15:37
 * 系统版本：1.0.0
 **/

@Service("poundInfoService")
public class PoundInfoServiceImpl implements IPoundInfoService {

    @Autowired
    private PoundInfoMapper poundInfoMapper;

    /**
     * 分页获取地磅数据
     *
     * @param dataTablePage 分页条件
     * @param poundInfo     查询条件
     * @return
     */
    @Override
    public PageInfo findAllPaging(DataTablePage dataTablePage, PoundInfo poundInfo) {
        PageHelper.startPage(dataTablePage.getPage(), dataTablePage.getLimit());
        List<PoundInfo> poundInfos = poundInfoMapper.selectListByMap(BeanUtil.beanToMap(poundInfo));
        PageInfo pageInfo = new PageInfo(poundInfos);
        return pageInfo;
    }

    /**
     * 根据id获取对象
     *
     * @param id 主键id
     * @return
     */
    @Override
    public PoundInfo findById(Integer id) {
        String key = "PoundInfo:id:";
        PoundInfo poundInfo = RedisUtil.getObj(key + id, PoundInfo.class);
        if (poundInfo == null) {
            poundInfo = poundInfoMapper.selectByPrimaryKey(id);
            RedisUtil.setObj(key + id, poundInfo);
        }

        return poundInfo;
    }

    /**
     * 更新地磅数据
     *
     * @param poundInfo
     * @return
     */
    @Override
    public int updateById(PoundInfo poundInfo) {
        return poundInfoMapper.updateByPrimaryKeySelective(poundInfo);
    }

    /**
     * 根据id删除地磅数据
     *
     * @param id
     * @return
     */
    @Override
    public int deleteById(Integer id) {
        return poundInfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据id删除地磅数据
     *
     * @param ids
     * @return
     */
    @Override
    public int deleteByIds(List<Integer> ids) {
        return poundInfoMapper.deleteByIds(ids);
    }

    /**
     * 添加地磅
     *
     * @param poundInfo
     * @return
     */
    @Override
    public int save(PoundInfo poundInfo) {
        poundInfo.setCreateTime(new Date());
        return poundInfoMapper.insert(poundInfo);
    }

    /**
     * 地磅表单校验
     *
     * @param poundInfo
     * @return
     */
    @Override
    public String checkForm(PoundInfo poundInfo) {
        if (StringUtils.isEmpty(poundInfo.getPoundName())) {
            return "地磅名称不能为空";
        }
        if (poundInfo.getAdminEmpId() == null) {
            return "管理员工号不能为空";
        }
        if (StringUtils.isEmpty(poundInfo.getAdminName())) {
            return "管理员名称不能为空";
        }
        if (StringUtils.isEmpty(poundInfo.getAdminMobile())) {
            return "管理员手机号码不能为空";
        }

        return null;
    }
}