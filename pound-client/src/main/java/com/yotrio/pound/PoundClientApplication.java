package com.yotrio.pound;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// mapper 接口类扫描包配置
@MapperScan("com.yotrio.pound.dao")
@SpringBootApplication
public class PoundClientApplication {

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(PoundClientApplication.class, args);
    }

}
