package com.yotrio.pound.tasks;

import com.yotrio.pound.model.Organization;
import com.yotrio.pound.service.IHttpService;
import com.yotrio.pound.service.IOrganizationService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.List;

/**
 * 同步组织任务
 * 模块名称：projects-parent com.yotrio.pound.tasks
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-11-10 9:21
 * 系统版本：1.0.0
 **/

public class SyncOrganizationTask extends QuartzJobBean {
    private static Logger logger = LoggerFactory.getLogger(SyncOrganizationTask.class);

    @Autowired
    private IHttpService httpService;
    @Autowired
    private IOrganizationService organizationService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("定时任务 同步组织 SyncOrganizationTask {}", new Date());
        List<Organization> organizations = httpService.findAllOrganization();
        if (organizations != null && organizations.size() > 0) {
            int[] count = {0, 0};
            try {
                for (Organization organization : organizations) {
                    Organization organizationInDB = organizationService.findByOrgCode(organization.getOrgCode());
                    //本地不存在，则插入数据
                    if (organizationInDB == null) {
                        organization.setLastUpdateTime(organization.getUpdateTime());
                        organizationService.save(organization);
                        count[0]++;
                    } else {
                        //本地存在则判断是否要更新,线上数据修改时间大于本地数据的时间，则更新
                        if (organizationInDB.getLastUpdateTime() == null || organization.getUpdateTime().after(organizationInDB.getLastUpdateTime())) {
                            //设置服务器更新的时间
                            organization.setLastUpdateTime(organization.getUpdateTime());
                            organization.setUpdateTime(new Date());
                            organizationService.updateByOrgCode(organization);
                            count[1]++;
                        }
                    }
                }
                logger.info("组织|添加：" + count[0] + "|更新：" + count[1]);
            } catch (Exception e) {
                logger.error("组织数据同步失败 {}" + e.getMessage());
            }
        }
    }
}