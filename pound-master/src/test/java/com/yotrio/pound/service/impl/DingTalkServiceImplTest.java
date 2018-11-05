package com.yotrio.pound.service.impl;

import com.yotrio.pound.service.IDingTalkService;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-config.xml", "classpath:spring/spring-dataSource.xml", "classpath:spring/spring-mybatis.xml",
        "classpath:spring/spring-transaction.xml"})
//加载配置
@TransactionConfiguration(defaultRollback = false)
public class DingTalkServiceImplTest {

    @Autowired
    private IDingTalkService dingTalkService;

    @Test
    public void sendConfirmMessage() {
        //通过员工工号获取钉钉用户id
        List<String> userIds = new ArrayList<>(20);

        String dingUserId = dingTalkService.getCacheDingTalkUserIdByMobile("15726816286");
        if (dingUserId != null) {
            userIds.add(dingUserId);
        }
        if (userIds.size() > 0) {
            String userIdList = StringUtils.join(userIds, ",");
//            dingTalkService.sendConfirmMessage(UserAuthTokenHelper.getUserAuthToken(1, null), , userIdList);
        }
    }

    @Test
    public void getCacheAccessToken() {
    }

    @Test
    public void getCacheDingTalkUserIdByEmpId() {
        String dingUserId = dingTalkService.getCacheDingTalkUserIdByEmpId(326879);
        System.out.println(dingUserId);
    }

    @Test
    public void getCacheDingTalkUserIdByMobile() {
        String dingUserId = dingTalkService.getCacheDingTalkUserIdByMobile("15726816286");
    }
}