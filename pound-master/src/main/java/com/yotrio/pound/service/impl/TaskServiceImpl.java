package com.yotrio.pound.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yotrio.common.constants.PoundLogConstant;
import com.yotrio.common.constants.TaskConstant;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.common.helpers.UserAuthTokenHelper;
import com.yotrio.pound.dao.InspectionMapper;
import com.yotrio.pound.dao.TaskMapper;
import com.yotrio.pound.model.Inspection;
import com.yotrio.pound.model.PoundInfo;
import com.yotrio.pound.model.PoundLog;
import com.yotrio.pound.model.Task;
import com.yotrio.pound.service.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

@Service("taskService")
public class TaskServiceImpl implements ITaskService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private IPoundLogService poundLogService;
    @Autowired
    private InspectionMapper inspectionMapper;
    @Autowired
    private IHttpService httpService;
    @Autowired
    private IDingTalkService dingTalkService;
    @Autowired
    private IPoundInfoService poundInfoService;
    @Autowired
    private IGoodsService goodsService;


    /**
     * 分页获取任务数据
     *
     * @param dataTablePage 分页条件
     * @param task          查询条件
     * @return
     */
    @Override

    public PageInfo findAllPaging(DataTablePage dataTablePage, Task task) {
        PageHelper.startPage(dataTablePage.getPage(), dataTablePage.getLimit());
        List<Task> taskList = taskMapper.selectListByMap(BeanUtil.beanToMap(task));
        PageInfo pageInfo = new PageInfo(taskList);
        return pageInfo;
    }

    /**
     * 根据id获取对象
     *
     * @param id 主键id
     * @return
     */
    @Override
    public Task findById(Integer id) {
        return taskMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新任务数据
     *
     * @param task
     * @return
     */
    @Override
    public int updateById(Task task) {
        return taskMapper.updateByPrimaryKeySelective(task);
    }

    /**
     * 执行任务
     *
     * @param task
     * @return
     */
    @Override
    public String executeTask(Task task) {
        //获取过磅记录
        Integer poundLogId = Integer.valueOf(task.getOtherId());
        PoundLog poundLog = poundLogService.findCacheById(poundLogId);
        if (poundLog == null) {
            task.setStatus(TaskConstant.STATUS_CANCEL);
            taskMapper.updateByPrimaryKeySelective(task);
            return "找不到您要执行的任务信息";
        }

        PoundInfo poundInfo = poundInfoService.findCacheById(poundLog.getPoundId());
        if (poundInfo == null) {
            return "找不到对应的地磅信息";
        }

        //获取关联的报检单
        List<Inspection> inspections = inspectionMapper.findListByPlId(poundLogId);

        if (poundLog.getTypes() == PoundLogConstant.TYPES_IN && inspections != null && inspections.size() > 0) {
            String token = UserAuthTokenHelper.getUserAuthToken(poundInfo.getAdminEmpId(), null);
            boolean sendResult = false;
            List<String> userIds = new ArrayList<>(20);
            //通过员工工号获取钉钉用户id
            String dingUserId = dingTalkService.getCacheDingTalkUserIdByEmpId(poundInfo.getAdminEmpId());
            if (dingUserId != null) {
                userIds.add(dingUserId);
            }
            if (userIds.size() > 0) {
                String userIdList = StringUtils.join(userIds, ",");
                //发送钉钉消息
                sendResult = dingTalkService.sendConfirmMessage(token, poundLog, userIdList, inspections);
            }

            if (sendResult) {
                //更新任务状态
                task.setStatus(TaskConstant.STATUS_FINISHED);
                task.setUpdateTime(new Date());
                taskMapper.updateByPrimaryKeySelective(task);
                return null;
            }
        }

        return "任务执行失败";
    }

    /**
     * 限量查找未完成任务
     *
     * @param taskAccount
     * @return
     */
    @Override
    public List<Task> findUnFinishTasksLimit(int taskAccount) {
        return taskMapper.findUnFinishTasksLimit(taskAccount);
    }

    /**
     * 创建任务
     *
     * @param task
     * @return
     */
    @Override
    public int save(Task task) {
        task.setCreateTime(new Date());
        return taskMapper.insert(task);
    }

    /**
     * 更otherId获取任务
     *
     * @param poundLogNo
     * @return
     */
    @Override
    public Task findByOtherId(String poundLogNo) {
        return taskMapper.findByOtherId(poundLogNo);
    }
}