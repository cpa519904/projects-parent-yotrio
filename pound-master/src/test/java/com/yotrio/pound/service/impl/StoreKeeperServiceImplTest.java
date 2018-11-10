package com.yotrio.pound.service.impl;

import com.yotrio.pound.model.StoreKeeper;
import com.yotrio.pound.service.IStoreKeeperService;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class StoreKeeperServiceImplTest {
    @Autowired
    private IStoreKeeperService storeKeeperService;

    @Test
    public void findByOrgCodeAndGoodsCode() {
        StoreKeeper storeKeeper = storeKeeperService.findByOrgCodeAndGoodsCode("001", "1541768960488");
        System.out.println(storeKeeper.toString());
    }
}