package com.yotrio.pound.task.job;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

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
     * 执行任务获取access_token
     */
    public void workForSendMsg() {
        try {
            getAccessToken();
            logger.info("执行定时任务\n");
        } catch (Exception e) {
            logger.error("任务执行异常，获取token失败\n", e);
        }
    }

    private void getAccessToken() {
        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", "client_credential");

        //获取token并赋值保存
//        String jsonToken = HttpUtil.get(ApiUrlConstants.URL_GET_ACCESS_TOKEN, params);

        logger.info("jsonToken: ");
    }

}
