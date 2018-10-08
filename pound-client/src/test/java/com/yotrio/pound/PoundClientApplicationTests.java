package com.yotrio.pound;

import cn.hutool.http.HttpUtil;
import com.yotrio.pound.constants.ApiUrlConstant;
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
        //请求接口获取地磅信息
        String baseUrl = systemProperties.getPoundMasterBaseUrl();
        String response = HttpUtil.get(systemProperties.getPoundMasterBaseUrl() + ApiUrlConstant.GET_POUND_INFO + 3);
        System.out.println(response);
    }

}
