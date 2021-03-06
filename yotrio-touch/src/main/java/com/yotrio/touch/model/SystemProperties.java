package com.yotrio.touch.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 系统参数类
 * 模块名称：projects-parent com.yotrio.pound.domain
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-10-08 14:30
 * 系统版本：1.0.0
 **/

@Component
public class SystemProperties {

    /**
     * 线上服务器域名
     */
    @Value("${pound.master.base.url}")
    private String PoundMasterBaseUrl;

    /**
     * 服务器端口号
     */
    @Value("${server.port}")
    private String serverPort;

    public String getPoundMasterBaseUrl() {
        return PoundMasterBaseUrl;
    }

    public void setPoundMasterBaseUrl(String poundMasterBaseUrl) {
        PoundMasterBaseUrl = poundMasterBaseUrl;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

}