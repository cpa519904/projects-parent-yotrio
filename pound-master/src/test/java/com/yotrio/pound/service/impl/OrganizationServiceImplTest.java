package com.yotrio.pound.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yotrio.common.constants.OrganizationConstant;
import com.yotrio.pound.model.Organization;
import com.yotrio.pound.service.IOrganizationService;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-config.xml", "classpath:spring/spring-dataSource.xml", "classpath:spring/spring-mybatis.xml",
        "classpath:spring/spring-transaction.xml"})
//加载配置
@TransactionConfiguration(defaultRollback = false)
public class OrganizationServiceImplTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IOrganizationService organizationService;

    @Test
    public void save() {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://192.168.0.197:8280/getorgs/orgs");

            // 执行请求
            HttpResponse response = httpClient.execute(httpGet);
            String respObject = EntityUtils.toString(response.getEntity(), "utf-8");
            JSONObject jsonObject = JSON.parseObject(respObject);

            if (jsonObject != null) {
                JSONObject result = jsonObject.getJSONObject("LoginOrgs");
                if (result != null) {
                    JSONArray array = result.getJSONArray("LoginOrg");
                    for (int i = 0; i < array.size(); i++) {
                        JSONObject object =array.getJSONObject(i);
                        System.out.println(object.toJSONString());
                        Organization organization = new Organization();
                        organization.setStatus(OrganizationConstant.STATUS_INIT);
                        organization.setOrgCode(object.getString("Code"));
                        organization.setOrgName(object.getString("Name"));
                        organizationService.save(organization);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("查询发货单关联U9收货单信息失败={}", e);
        }

    }
}