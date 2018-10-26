package com.yotrio.pound.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yotrio.common.constants.ApiUrlConstant;
import com.yotrio.pound.domain.SystemProperties;
import com.yotrio.pound.model.PoundInfo;
import com.yotrio.pound.service.IHttpService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.yotrio.common.utils.DingTalkUtil.SUCCESS_CODE;

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

    @Autowired
    private SystemProperties systemProperties;

    /**
     * 根据地磅id获取地磅信息
     *
     * @param id
     * @return
     */
    @Override
    public PoundInfo getPoundInfo(Integer id) {
        //获取线上地磅信息
        String url = systemProperties.getPoundMasterBaseUrl() + ApiUrlConstant.GET_POUND_INFO + id;
        try {
            String response = HttpUtil.get(url);
            if (StringUtils.isNotEmpty(response)) {
                JSONObject json = JSONObject.parseObject(response);
                if (json.getIntValue("code") == SUCCESS_CODE) {
                    JSONObject data = json.getJSONObject("data");
                    PoundInfo poundInfo = JSON.parseObject(data.toJSONString(), PoundInfo.class);
                    return poundInfo;
                }
            }
        } catch (Exception e) {
            logger.error("上传过磅单失败={}", e);
        }
        return null;
    }
}