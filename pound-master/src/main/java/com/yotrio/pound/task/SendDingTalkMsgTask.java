package com.yotrio.pound.task;

import com.alibaba.fastjson.JSON;
import com.yotrio.pound.model.Task;
import com.yotrio.pound.service.ITaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 微信两小时定时任务
 * 模块名称：projects-parent com.wyq.wechat.task
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-02-25 下午1:49
 * 系统版本：1.0.0
 **/
@Component
public class SendDingTalkMsgTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 一次查询任务数量
     */
    private static final int TASK_ACCOUNT = 5;

    @Autowired
    private ITaskService taskService;

    public void sendMessage() {
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
