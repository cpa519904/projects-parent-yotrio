package com.yotrio.pound.domain;

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
     * 地磅id
     */
    @Value("${pound.client.id}")
    private Integer poundClientId;

    /**
     * 地磅管理员工号
     */
    @Value("${pound.client.empId}")
    private Integer poundClientEmpId;

    /**
     * 地磅名称
     */
    @Value("${pound.client.name}")
    private String poundClientName;

    /**
     * 文件存储地址
     */
    @Value("${file.location}")
    private String fileLocation;

    /**
     * 服务器端口号
     */
    @Value("${server.port}")
    private String serverPort;

    /**
     * 本地请求头
     */
    @Value("${localhost.url}")
    private String localhostUrl;

    public String getPoundMasterBaseUrl() {
        return PoundMasterBaseUrl;
    }

    public void setPoundMasterBaseUrl(String poundMasterBaseUrl) {
        PoundMasterBaseUrl = poundMasterBaseUrl;
    }

    public Integer getPoundClientId() {
        return poundClientId;
    }

    public void setPoundClientId(Integer poundClientId) {
        this.poundClientId = poundClientId;
    }

    public String getPoundClientName() {
        return poundClientName;
    }

    public void setPoundClientName(String poundClientName) {
        this.poundClientName = poundClientName;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getLocalhostUrl() {
        return localhostUrl;
    }

    public void setLocalhostUrl(String localhostUrl) {
        this.localhostUrl = localhostUrl;
    }

    public Integer getPoundClientEmpId() {
        return poundClientEmpId;
    }

    public void setPoundClientEmpId(Integer poundClientEmpId) {
        this.poundClientEmpId = poundClientEmpId;
    }
}