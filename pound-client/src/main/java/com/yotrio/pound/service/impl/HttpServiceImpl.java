package com.yotrio.pound.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yotrio.common.constants.ApiUrlConstant;
import com.yotrio.common.helpers.UserAuthTokenHelper;
import com.yotrio.pound.domain.SystemProperties;
import com.yotrio.pound.model.Company;
import com.yotrio.pound.model.Goods;
import com.yotrio.pound.model.Organization;
import com.yotrio.pound.model.PoundInfo;
import com.yotrio.pound.service.IHttpService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
            logger.error("上传过磅单失败 {}", e.getMessage());
        }
        return null;
    }

    /**
     * 获取所有供应商
     *
     * @return
     */
    @Override
    public List<Company> findAllCompany() {
        String token = UserAuthTokenHelper.getUserAuthToken(systemProperties.getPoundClientId(), null);
        String url = systemProperties.getPoundMasterBaseUrl() + ApiUrlConstant.GET_ALL_COMPANY + "?token=" + token;
        try {
            String response = HttpUtil.get(url);
            if (StringUtils.isNotEmpty(response)) {
                JSONObject json = JSONObject.parseObject(response);
                if (json.getIntValue("code") == SUCCESS_CODE) {
                    JSONArray data = json.getJSONArray("data");
                    List<Company> companyList = JSON.parseArray(data.toJSONString(), Company.class);
                    return companyList;
                }
            }
        } catch (Exception e) {
            logger.error("获取供应商列表失败 {}", e.getMessage());
        }
        return null;
    }

    /**
     * 获取所有物料
     *
     * @return
     */
    @Override
    public List<Goods> findAllGoods() {
        String token = UserAuthTokenHelper.getUserAuthToken(systemProperties.getPoundClientId(), null);
        String url = systemProperties.getPoundMasterBaseUrl() + ApiUrlConstant.GET_ALL_GOODS + "?token=" + token;
        try {
            String response = HttpUtil.get(url);
            if (StringUtils.isNotEmpty(response)) {
                JSONObject json = JSONObject.parseObject(response);
                if (json.getIntValue("code") == SUCCESS_CODE) {
                    JSONArray data = json.getJSONArray("data");
                    List<Goods> goodsList = JSON.parseArray(data.toJSONString(), Goods.class);
                    return goodsList;
                }
            }
        } catch (Exception e) {
            logger.error("获取物料列表失败 {}", e.getMessage());
        }
        return null;
    }

    /**
     * 获取所有组织
     *
     * @return
     */
    @Override
    public List<Organization> findAllOrganization() {
        String token = UserAuthTokenHelper.getUserAuthToken(systemProperties.getPoundClientId(), null);
        String url = systemProperties.getPoundMasterBaseUrl() + ApiUrlConstant.GET_ALL_ORGANIZATION + "?token=" + token;
        try {
            String response = HttpUtil.get(url);
            if (StringUtils.isNotEmpty(response)) {
                JSONObject json = JSONObject.parseObject(response);
                if (json.getIntValue("code") == SUCCESS_CODE) {
                    JSONArray data = json.getJSONArray("data");
                    List<Organization> organizationList = JSON.parseArray(data.toJSONString(), Organization.class);
                    return organizationList;
                }
            }
        } catch (Exception e) {
            logger.error("获取组织列表失败 {}", e.getMessage());
        }
        return null;
    }
}