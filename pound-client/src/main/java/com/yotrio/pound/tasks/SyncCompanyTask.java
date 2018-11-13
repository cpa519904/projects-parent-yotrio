package com.yotrio.pound.tasks;

import com.yotrio.pound.model.Company;
import com.yotrio.pound.service.ICompanyService;
import com.yotrio.pound.service.IHttpService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.List;

/**
 * 同步供应商任务
 * 模块名称：projects-parent com.yotrio.pound.tasks
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-11-10 9:21
 * 系统版本：1.0.0
 **/

public class SyncCompanyTask extends QuartzJobBean {
    private static Logger logger = LoggerFactory.getLogger(SyncCompanyTask.class);

    @Autowired
    private IHttpService httpService;
    @Autowired
    private ICompanyService companyService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("定时任务 同步供应商 SyncCompanyTask {}", new Date());
        List<Company> allCompany = httpService.findAllCompany();
        int[] count = {0, 0};
        if (allCompany != null && allCompany.size() > 0) {
            try {
                for (Company company : allCompany) {
                    Company companyInDB = companyService.findByCompCode(company.getCompCode());
                    //本地不存在，则插入数据
                    if (companyInDB == null) {
                        company.setLastUpdateTime(company.getUpdateTime());
                        companyService.save(company);
                        count[0]++;
                    } else {
                        //本地存在则判断是否要更新,线上数据修改时间大于本地数据的时间，则更新
                        if (companyInDB.getLastUpdateTime() == null || company.getUpdateTime().after(companyInDB.getLastUpdateTime())) {
                            //设置服务器更新的时间
                            company.setLastUpdateTime(company.getUpdateTime());
                            company.setUpdateTime(new Date());
                            companyService.updateByCompCode(company);
                            count[1]++;
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("供应商数据同步失败 {}" + e.getMessage());
            }
        }
        logger.info("供应商|添加：" + count[0] + "|更新：" + count[1]);
    }
}