package com.yotrio.pound.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.dao.PoundLogMapper;
import com.yotrio.pound.dao.TaskMapper;
import com.yotrio.pound.model.PoundLog;
import com.yotrio.pound.model.Task;
import com.yotrio.pound.service.ITaskService;
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

@Service("taskService")
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private PoundLogMapper poundLogMapper;

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
        PoundLog poundLog = poundLogMapper.selectByPrimaryKey(Integer.valueOf(task.getOtherId()));
        if (poundLog == null) {
            return "找不到您要执行的任务信息";
        }


        return null;
    }

    /**
     * 限量查找未完成任务
     * @param taskAccount
     * @return
     */
    @Override
    public List<Task> findUnFinishTasksLimit(int taskAccount) {
        return taskMapper.findUnFinishTasksLimit(taskAccount);
    }

    /**
     * 创建任务
     * @param task
     * @return
     */
    @Override
    public int save(Task task) {
        task.setCreateTime(new Date());
        return taskMapper.insert(task);
    }
}