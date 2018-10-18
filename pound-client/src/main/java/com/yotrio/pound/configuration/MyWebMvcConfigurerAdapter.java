package com.yotrio.pound.configuration;

import com.yotrio.pound.domain.SystemProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 资源解析配置类
 *
 * @param
 */
@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Autowired
    private SystemProperties systemProperties;


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //指向外部目录 http://localhost:8000/images/1539762376305.jpg 就可以访问E:/yotrio-pound/images/1539762376305.jpg 了
        String outPath = "file:" + systemProperties.getFileLocation() + "/images/";
        registry.addResourceHandler("/images/**").addResourceLocations(outPath);
    }

}