package com.yotrio.pound.web.shiro.filter;


import com.yotrio.common.constants.SysConstants;
import com.yotrio.pound.model.SysUser;
import com.yotrio.pound.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Set;

/**
 * shiro安全过滤器，设置用户信息、角色、权限
 */
public class SysUserFilter extends PathMatchingFilter {

    @Autowired
    private ISysUserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SysUser user = userService.findByUsername(username);
        request.setAttribute(SysConstants.CURRENT_USER, user);
        Set<String> roles = userService.findRolesByUserId(user.getId());
        Set<String> permissions = userService.findPermissionsByUserId(user.getId());
        request.setAttribute("role", roles);
        request.setAttribute("permission", permissions);
        return true;
    }

}
