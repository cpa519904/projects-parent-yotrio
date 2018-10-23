package com.yotrio.pound.dao;

import com.yotrio.pound.model.PoundInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.Date;
import java.util.Random;

/**
 * 模块名称：projects-parent com.yotrio.pound.dao
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-09-25 15:40
 * 系统版本：1.0.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-config.xml", "classpath:spring/spring-dataSource.xml", "classpath:spring/spring-mybatis.xml",
        "classpath:spring/spring-transaction.xml"})
@TransactionConfiguration(defaultRollback = false)
public class PoundInfoMapperTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PoundInfoMapper poundInfoMapper;

    @Test
    public void insert() {
        String[] direct = {"东", "南", "西", "北"};
        Random random = new Random();

        for (int j = 3; j <= 25; j++) {
            PoundInfo poundInfo = new PoundInfo();
            poundInfo.setModel("Xk3190-A9+" + j);
            poundInfo.setPoundName("总部" + direct[random.nextInt(4)] + "大门地方-" + j);
            poundInfo.setStatus(1);
            poundInfo.setUnitId(1000 + j);
            poundInfo.setCreateTime(new Date());
            int i = poundInfoMapper.insert(poundInfo);
            logger.info("id={}", i);
        }
    }
}