package com.yotrio.pound.service.impl;

import com.yotrio.pound.service.IInspectionService;
import com.yotrio.pound.service.IPoundLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PoundLogServiceImplTest {
    @Autowired
    private IPoundLogService poundLogService;
    @Autowired
    private IInspectionService inspectionService;

    @Test
    public void deleteHistoryLogs() {
        poundLogService.deleteHistoryLogs(8);
        inspectionService.deleteHistoryInspections(8);
    }
}