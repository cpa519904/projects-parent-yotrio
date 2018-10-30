package com.yotrio.pound.task.job;


import com.alibaba.fastjson.JSON;
import com.yotrio.pound.model.Task;
import com.yotrio.pound.service.ITaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * 任务执行获取token，默认两个小时执行一次
 * 模块名称：projects-parent com.wyq.wechat.task.job
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-02-25 下午2:16
 * 系统版本：1.0.0
 **/
public class QuartzJob {
    private static Logger logger = LoggerFactory.getLogger(QuartzJob.class);

    /**
     * 一次查询任务数量
     */
    private static final int TASK_ACCOUNT = 5;

    @Autowired
    private ITaskService taskService;

    /**
     * 执行任务获取access_token
     */
    public void workForSendMsg() {
        try {
            //限量获取未完成任务
            List<Task> taskList = taskService.findUnFinishTasksLimit(TASK_ACCOUNT);
            logger.info("taskList", JSON.toJSONString(taskList));
            for (Task task : taskList) {
                String result = taskService.executeTask(task);
                if (result != null) {
                    logger.info("taskId:" + task.getId() + "|任务执行失败:" + result + "|" + new Date());
                }
            }
        } catch (Exception e) {
            logger.error("任务执行异常\n", e);
        }
    }
}
