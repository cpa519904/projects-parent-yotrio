package com.yotrio.pound.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yotrio.common.constants.ApiUrlConstant;
import com.yotrio.common.constants.TaskConstant;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.common.helpers.UserAuthTokenHelper;
import com.yotrio.pound.dao.InspectionMapper;
import com.yotrio.pound.dao.PoundLogMapper;
import com.yotrio.pound.dao.TaskMapper;
import com.yotrio.pound.domain.SystemProperties;
import com.yotrio.pound.model.Inspection;
import com.yotrio.pound.model.PoundLog;
import com.yotrio.pound.model.Task;
import com.yotrio.pound.service.ITaskService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yotrio.common.utils.DingTalkUtil.SUCCESS_CODE;

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
    private PoundLogMapper poundLogMapper;
    @Autowired
    private InspectionMapper inspectionMapper;
    @Autowired
    private SystemProperties systemProperties;

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
        String poundLogNo = task.getOtherId();
        PoundLog poundLog = poundLogMapper.findByPoundLogNo(poundLogNo);
        if (poundLog == null) {
            return "找不到您要执行的任务信息";
        }

        //获取关联的报检单
        List<Inspection> inspections = inspectionMapper.findListByPlNo(poundLogNo);

        JSONObject data = new JSONObject();
        data.put("poundLog", poundLog);
        data.put("inspections", inspections);
        Map<String, Object> map = new HashMap<>(10);
        map.put("data", data);
        map.put("token", UserAuthTokenHelper.getUserAuthToken(systemProperties.getPoundClientId(), null));

        //过磅记录发送到服务器
        String url = systemProperties.getPoundMasterBaseUrl() + ApiUrlConstant.SAVE_POUND_LOG;
        try {
            String response = HttpUtil.post(url, map);
            if (StringUtils.isNotEmpty(response)) {
                JSONObject json = JSONObject.parseObject(response);
                String msg = json.getString("msg");
                if (json.getIntValue("code") == SUCCESS_CODE) {
                    //上传成功
                    task.setStatus(TaskConstant.STATUS_FINISHED);
                    task.setUpdateTime(new Date());
                    task.setDescription(msg);
                    taskMapper.updateByPrimaryKey(task);
                    return null;
                } else {
                    logger.info("taskId:" + task.getId() + "|任务执行失败:" + msg + "|" + new Date());
                    return msg;
                }
            }
        } catch (Exception e) {
            logger.error("任务执行失败={}", e.getMessage());
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