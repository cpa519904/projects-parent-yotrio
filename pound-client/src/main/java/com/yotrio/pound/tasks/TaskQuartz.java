package com.yotrio.pound.tasks;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yotrio.common.utils.NetStateUtil;
import com.yotrio.pound.constants.ApiUrlConstant;
import com.yotrio.pound.constants.TaskConstant;
import com.yotrio.pound.domain.SystemProperties;
import com.yotrio.pound.model.PoundLog;
import com.yotrio.pound.model.Task;
import com.yotrio.pound.service.IPoundLogService;
import com.yotrio.pound.service.ITaskService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.List;

public class TaskQuartz extends QuartzJobBean {
    private static Logger logger = LoggerFactory.getLogger(TaskQuartz.class);

    /**
     * 一次查询任务数量
     */
    private static final int TASK_ACCOUNT = 5;
    /**
     * 成功code
     */
    private static final int SUCCESS_CODE = 0;

    @Autowired
    private SystemProperties sysProperties;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private IPoundLogService poundLogService;

    /**
     * 执行定时任务
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("定时任务 quartz task {}", new Date());

        //校验网络连接状态
        if (!NetStateUtil.isConnect()) {
            logger.error("网络连接异常...{}", new Date());
            return;
        }

        //限量获取未完成任务
        List<Task> taskList = taskService.findUnFinishTasksLimit(TASK_ACCOUNT);
        logger.info("taskList", JSON.toJSONString(taskList));
        for (Task task : taskList) {
            int poundId = Integer.parseInt(task.getOtherId());
            PoundLog poundLog = poundLogService.findById(poundId);
            if (poundLog == null) {
                continue;
            }

            //过磅记录发送到服务器
            String url = sysProperties.getPoundMasterBaseUrl() + ApiUrlConstant.SAVE_POUND_LOG;
            String response = HttpUtil.post(url, BeanUtil.beanToMap(poundLog));
            if (response != null) {
                JSONObject json = JSONObject.parseObject(response);
                String msg = json.getString("msg");
                if (json.getIntValue("code") == SUCCESS_CODE) {
                    //上传成功
                    task.setStatus(TaskConstant.STATUS_FINISHED);
                    task.setUpdateTime(new Date());
                    task.setDescription(msg);
                    taskService.updateById(task);
                } else {
                    logger.info("taskId:" + task.getId() + "|任务执行失败:" + msg + "|" + new Date());
                }
            }
        }
    }


}
