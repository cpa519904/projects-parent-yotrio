package com.yotrio.pound.service.impl;

import com.yotrio.common.constants.CompanyConstant;
import com.yotrio.pound.model.Company;
import com.yotrio.pound.service.ICompanyService;
import com.yotrio.pound.service.IStoreKeeperService;
import com.yotrio.pound.utils.ExcelReaderUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-config.xml", "classpath:spring/spring-dataSource.xml", "classpath:spring/spring-mybatis.xml",
        "classpath:spring/spring-transaction.xml"})
//加载配置
@TransactionConfiguration(defaultRollback = false)
public class ReadExcelTest {

    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IStoreKeeperService storeKeeperService;

    @Test
    public void readExcel() {
        String path = "C:\\Users\\Administrator\\Desktop\\工作文档存储\\地磅供应商new.xlsx";
        List<List<String>> lists = ExcelReaderUtil.readExcel(path);
        int count = 0;
        for (int i = 0; i < lists.size(); i++) {
            //标题过滤掉
            if (i == 0) {
                continue;
            }
            List<String> item = lists.get(i);
            Company company = new Company();
            for (int j = 0; j < item.size(); j++) {
                if (j == 0) {
                    company.setCompName(item.get(j));
                } else {
                    company.setCompCode(item.get(j));
                }
            }
            company.setUpdateTime(new Date());
            company.setStatus(CompanyConstant.STATUS_INIT);
            int save = companyService.save(company);
            if (save > 0) {
                count++;
            }
        }
        System.out.println("list:" + lists.size());
        System.out.println("count:" + count);
    }

    @Test
    public void readStoreKeeperFromExcel() {
        String path = "C:\\Users\\Administrator\\Desktop\\工作文档存储\\过磅物料名称new.xlsx";
        List<List<String>> lists = ExcelReaderUtil.readExcel(path);
        int count = 0;
        for (int i = 0; i < lists.size(); i++) {
            //标题过滤掉
            if (i == 0) {
                continue;
            }

            List<String> item = lists.get(i);
//            Company company = new Company();
//            for (int j = 0; j < item.size(); j++) {
//                if (j == 0) {
//                    company.setCompName(item.get(j));
//                } else {
//                    company.setCompCode(item.get(j));
//                }
//            }
//            company.setUpdateTime(new Date());
//            company.setStatus(CompanyConstant.STATUS_INIT);
//            int save = companyService.save(company);
//            if (save > 0) {
//                count++;
//            }
        }
        System.out.println("list:" + lists.size());
        System.out.println("count:" + count);
    }



}