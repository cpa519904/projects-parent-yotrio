package com.yotrio.touch.service;

import com.yotrio.touch.model.PoundLog;

/**
 * 模块名称：projects-parent com.yotrio.touch.service
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-11-13 14:55
 * 系统版本：1.0.0
 **/
public interface IHttpService {
    PoundLog getPoundLogWithInspections(Integer plId, String token);
}
