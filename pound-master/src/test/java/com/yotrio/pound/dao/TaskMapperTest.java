//package com.yotrio.pound.dao;
//
//import cn.hutool.core.util.RandomUtil;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.transaction.TransactionConfiguration;
//
//import java.util.Date;
//import java.util.Random;
//
///**
// * 模块名称：projects-parent com.yotrio.pound.dao
// * 功能说明：<br>
// * 开发人员：Wangyq
// * 创建时间： 2018-09-25 15:40
// * 系统版本：1.0.0
// **/
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:spring/spring-config.xml", "classpath:spring/spring-dataSource.xml", "classpath:spring/spring-mybatis.xml",
//        "classpath:spring/spring-transaction.xml"})
//@TransactionConfiguration(defaultRollback = false)
//public class TaskMapperTest {
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    @Autowired
//    private TaskMapper taskMapper;
//
//    @Test
//    public void insert() {
//        String[] direct = {"东", "南", "西", "北"};
//        Random random = new Random();
//
//        for (int j = 1; j <= 35; j++) {
//            Task task = new Task();
//            task.setTaskName("task-" + j);
//            if (j % 2 == 0) {
//                task.setStatus(0);
//            } else {
//                task.setStatus(1);
//            }
//            task.setCreateTime(new Date());
//            task.setDescription("第" + j + "个task");
//            task.setOtherId(RandomUtil.randomNumbers(4));
//            task.setWeight(0);
//            task.setCreateTime(new Date());
//            int i = taskMapper.insert(task);
//            logger.info("id={}", i);
//        }
//    }
//}