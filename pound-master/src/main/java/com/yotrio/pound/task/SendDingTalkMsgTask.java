package com.yotrio.pound.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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


    public void sendMessage() {


        logger.info("jsonToken: ");
    }


}
