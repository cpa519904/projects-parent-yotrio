package com.yotrio.pound.dao;

import cn.hutool.core.util.RandomUtil;
import com.yotrio.common.constants.PoundLogConstant;
import com.yotrio.pound.model.PoundInfo;
import com.yotrio.pound.model.PoundLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 模块名称：projects-parent com.yotrio.pound.dao
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-09-26 9:27
 * 系统版本：1.0.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-config.xml", "classpath:spring/spring-dataSource.xml", "classpath:spring/spring-mybatis.xml",
        "classpath:spring/spring-transaction.xml"})
@TransactionConfiguration(defaultRollback = false)
public class PoundLogMapperTest {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PoundLogMapper poundLogMapper;
    @Autowired
    private PoundInfoMapper poundInfoMapper;

    @Test
    public void insert() {
//        String[] direct = {"东", "南", "西", "北"};
        String imgUrl1 = "https://wx4.sinaimg.cn/mw1024/5db11ff4gy1fmx4keaw9pj20dw08caa4.jpg";
        String imgUrl2 = "https://wx2.sinaimg.cn/mw690/5db11ff4gy1fmx4kec5bvj20eb0h3mxh.jpg";
//        List<PoundInfo> poundInfoList = poundInfoMapper.selectListByMap(new HashMap<String, Object>());
//        Random random = new Random();
//        logger.info("poundInfoSize={}", poundInfoList.size());
//        logger.info("poundInfoList={}", poundInfoList);


        for (int j = 1; j <= 1; j++) {
            PoundInfo poundInfo = poundInfoMapper.selectByPrimaryKey(1);

            PoundLog poundLog = new PoundLog();
            poundLog.setPoundName(poundInfo.getPoundName());
            poundLog.setPoundId(poundInfo.getId());
            poundLog.setPoundLogNo("ddddddd");
            poundLog.setGrossWeight(Double.valueOf(1000 + j * 100));
            poundLog.setTareWeight(Double.valueOf(1000 + j * 10));
            poundLog.setNetWeight(Double.valueOf(1000 + j * 100 - 1000 + j * 10));
            poundLog.setGrossImgUrl(imgUrl1);
            poundLog.setTareImgUrl(imgUrl2);
            poundLog.setUnitName("单位" + j + "部");
            poundLog.setStatus(1);
            poundLog.setTypes(PoundLogConstant.TYPES_IN);
            poundLog.setCreateTime(new Date());
            int id = poundLogMapper.insert(poundLog);
            logger.info("id={}", id);
        }
    }

    @Test
    public void update() {
        List<PoundLog> poundLogs = poundLogMapper.selectListByMap(new HashMap<String, Object>());

        int count = 0;
        for (PoundLog poundLog : poundLogs) {
            PoundLog log = new PoundLog();
            log.setId(poundLog.getId());
            log.setPlateNo(RandomUtil.randomNumbers(5));
            int i = poundLogMapper.updateByPrimaryKeySelective(log);
            if (i > 0) {
                count++;
            }
        }

        logger.info("成功更新：{}", count);
    }
}