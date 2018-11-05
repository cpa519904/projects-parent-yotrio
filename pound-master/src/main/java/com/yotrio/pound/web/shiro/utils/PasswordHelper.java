package com.yotrio.pound.web.shiro.utils;

import com.yotrio.pound.model.SysUser;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

/**
 * shiro用户密码加密，在CredentialsMatcher需要和此处加密算法一样
 * 模块名称：projects-parent com.wyq.common.utils
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-04-15 下午9:16
 * 系统版本：1.0.0
 **/

@Component
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public void encryptPassword(SysUser user) {

        user.setSalt(randomNumberGenerator.nextBytes().toHex());

        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();

        user.setPassword(newPassword);
    }

    /**获取加密后的密码
     *
     * @param user 用户
     * @return
     */
    public String getEncryptPassword(SysUser user,String password) {
        try {
           return new SimpleHash(
                    algorithmName,
                    password,
                    ByteSource.Util.bytes(user.getCredentialsSalt()),
                    hashIterations).toHex();
        } catch (Exception e) {
            return null;
        }
    }

}
