package com.yotrio.pound.dao;

import com.yotrio.pound.model.Inspection;
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
@TransactionConfiguration(defaultRollback = false)
public class InspectionMapperTest {

    @Autowired
    private InspectionMapper inspectionMapper;

    @Test
    public void findByInspNo() {
        Inspection inspection = inspectionMapper.findByInspNo("FD201810311013");
        System.out.println(inspection);

    }
}