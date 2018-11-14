package com.yotrio.touch.service;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yotrio.touch.constants.ApiUrlConstant;
import com.yotrio.touch.model.PoundLog;
import com.yotrio.touch.model.SystemProperties;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 网络请求逻辑类
 * 模块名称：projects-parent com.yotrio.pound.service
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-10-26 10:50
 * 系统版本：1.0.0
 **/

@Service("httpService")
public class HttpServiceImpl implements IHttpService {
    private Logger logger = LoggerFactory.getLogger(HttpServiceImpl.class);

    private static final int SUCCESS_CODE = 0;

    @Autowired
    private SystemProperties systemProperties;

    /**
     * 过去过磅单和报检单
     *
     * @param plId
     * @param token
     * @return
     */
    @Override
    public PoundLog getPoundLogWithInspections(Integer plId, String token) {
        //获取线上地磅信息
        String url = systemProperties.getPoundMasterBaseUrl() + ApiUrlConstant.GET_POUNDLOG_WITH_INSPECTIONS + "?token=" + token + "&plId=" + plId;
        try {
            String response = HttpUtil.get(url);
            if (StringUtils.isNotEmpty(response)) {
                JSONObject json = JSONObject.parseObject(response);
                if (json.getIntValue("code") == SUCCESS_CODE) {
                    JSONObject data = json.getJSONObject("data");
                    PoundLog poundLog = JSON.parseObject(data.toJSONString(), PoundLog.class);
                    return poundLog;
                }
            }
        } catch (Exception e) {
            logger.error("数据获取失败 {}", e.getMessage());
        }
        return null;
    }

}