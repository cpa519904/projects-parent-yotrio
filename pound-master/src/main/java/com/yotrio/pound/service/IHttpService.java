package com.yotrio.pound.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 模块名称：projects-parent com.yotrio.pound.service
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-10-26 10:50
 * 系统版本：1.0.0
 **/
public interface IHttpService {
    JSONObject getU9ReceiveInfo(String DeliveryNo);

    String getCacheU9Token();

    String writeWeightToU9ReceiveInfo(Map<String, Object> map);
}
