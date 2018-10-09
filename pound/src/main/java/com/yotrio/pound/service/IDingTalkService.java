package com.yotrio.pound.service;

/**
 * 模块名称：projects-parent com.yotrio.pound.service
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-10-09 14:11
 * 系统版本：1.0.0
 **/
public interface IDingTalkService {


    boolean sendConfirmMessage(String token, Integer poundLogId);
}
