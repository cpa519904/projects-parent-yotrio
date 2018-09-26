package com.yotrio.pound;

import com.yotrio.common.utils.SerialPortUtil;

import java.util.ArrayList;

/**
 * 测试类
 * 模块名称：projects-parent com.yotrio.pound
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-09-17 上午11:24
 * 系统版本：1.0.0
 **/

public class MainTest {
    public static void main(String[] args) {
//        String accessToken = DingTalkUtils.getAccessToken();
//        System.out.println("token:" + accessToken);

        ArrayList<String> ports = SerialPortUtil.findPort();
        System.out.println("prots:"+ports.toString());
    }
}
