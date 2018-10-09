package com.yotrio.pound;

import com.yotrio.common.utils.DateUtil;
import com.yotrio.pound.domain.SystemProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PoundClientApplicationTests {

    @Autowired
    private SystemProperties systemProperties;

    @Test
    public void contextLoads() {
        int timeToDay = DateUtil.getCurrentTimeToDay();

    }

}
