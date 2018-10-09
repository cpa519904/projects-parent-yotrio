package com.yotrio.pound.task;

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
    private static final int TASK_ACCOUNT = 10;

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
        //限量获取未完成任务
        List<Task> taskList = taskService.findUnFinishTasksLimit(TASK_ACCOUNT);
        for (Task task : taskList) {
            int poundId = Integer.parseInt(task.getOtherId());
            PoundLog poundLog = poundLogService.findById(poundId);
            if (poundLog == null) {
                continue;
            }

            //过磅记录发送到服务器

        }
    }


}
