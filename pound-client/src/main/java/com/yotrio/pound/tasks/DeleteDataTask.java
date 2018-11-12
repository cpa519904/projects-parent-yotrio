package com.yotrio.pound.tasks;

import com.yotrio.pound.service.IInspectionService;
import com.yotrio.pound.service.IPoundLogService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * 定时删除数据
 * 模块名称：projects-parent com.yotrio.pound.tasks
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-11-12 13:52
 * 系统版本：1.0.0
 **/

public class DeleteDataTask extends QuartzJobBean {
    private static Logger logger = LoggerFactory.getLogger(DeleteDataTask.class);

    /**
     * 删除一个月前的数据
     */
    private static final int DAY_BEFORE = 30;

    @Autowired
    private IPoundLogService poundLogService;
    @Autowired
    private IInspectionService inspectionService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("定时任务 删除数据 DeleteDataTask {}", new Date());
        poundLogService.deleteHistoryLogs(DAY_BEFORE);
        inspectionService.deleteHistoryInspections(DAY_BEFORE);
    }
}