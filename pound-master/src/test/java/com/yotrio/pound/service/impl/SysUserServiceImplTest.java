package com.yotrio.pound.service.impl;

import com.yotrio.common.constants.SysUserConstants;
import com.yotrio.common.enums.SysUserRank;
import com.yotrio.pound.model.SysUser;
import com.yotrio.pound.service.IHttpService;
import com.yotrio.pound.service.ISysUserService;
import com.yotrio.pound.web.shiro.utils.PasswordHelper;
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
    @Autowired
    private PasswordHelper passwordHelper;

    @Test
    public void createSysUser() throws Exception {
        SysUser sysUser = new SysUser();
        sysUser.setId(0);
        sysUser.setCreatedBy("SuperAdmin");
        sysUser.setCreateTime(new Date());
        sysUser.setEmail("364514921@qq.com");
        sysUser.setNickname("系统用户");
        sysUser.setUsername("panyuyan");
        sysUser.setStatus(SysUserConstants.SYS_USER_STATUS_IS_ENABLE);
        sysUser.setEmpId(326601);
        sysUser.setPhone("13957673594");
        sysUser.setRank(SysUserRank.SYS_USER_RANK_7.getRank());
        sysUser.setPassword("UzsUBwJyRtEIoB0Z");

        sysUserService.createSysUser(sysUser);
//        sysUserService.saveUserTest(sysUser);

    }

    @Test
    public void httpTest() throws Exception {
        String u9Token = httpService.getCacheU9Token();
    }

}