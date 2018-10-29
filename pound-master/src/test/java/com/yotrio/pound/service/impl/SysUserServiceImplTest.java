package com.yotrio.pound.service.impl;

import com.yotrio.common.constants.SysUserConstants;
import com.yotrio.common.enums.SysUserRank;
import com.yotrio.pound.model.SysUser;
import com.yotrio.pound.service.IHttpService;
import com.yotrio.pound.service.ISysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.Date;

/**
 * ${DESCRIPTION}
 * 模块名称：projects-parent-yotrio.com.yotrio.pound.service.impl
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-10-26 下午7:52
 * 系统版本：1.0.0
 **/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-config.xml", "classpath:spring/spring-dataSource.xml", "classpath:spring/spring-mybatis.xml",
        "classpath:spring/spring-transaction.xml"})
//加载配置
@TransactionConfiguration(defaultRollback = false)
public class SysUserServiceImplTest {
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IHttpService httpService;

    @Test
    public void createSysUser() throws Exception {
        SysUser sysUser = new SysUser();
        sysUser.setCreatedBy("SuperAdmin");
        sysUser.setCreateTime(new Date());
        sysUser.setEmail("364514921@qq.com");
        sysUser.setNickname("管理员用户");
        sysUser.setUsername("admin");
        sysUser.setStatus(SysUserConstants.SYS_USER_STATUS_IS_ENABLE);
        sysUser.setEmpId(329876);
        sysUser.setPhone("15726816286");
        sysUser.setPassword("123456");
        sysUser.setRank(SysUserRank.SYS_USER_RANK_2.getRank());
        sysUserService.createSysUser(sysUser);
    }

    @Test
    public void httpTest() throws Exception {
        String u9Token = httpService.getU9Token();
    }

}