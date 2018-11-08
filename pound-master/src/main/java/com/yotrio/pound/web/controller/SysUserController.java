package com.yotrio.pound.web.controller;

import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.Callback;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.model.SysUser;
import com.yotrio.pound.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户控制接口
 * 模块名称：projects-parent com.wyq.admin.web.controller
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-01-29 下午10:20
 * 系统版本：1.0.0
 **/

@Controller
@RequestMapping("/sysUser")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 系统用户列表页面
     *
     * @param model
     * @return
     */
    @RequiresPermissions("user:view")
    @RequestMapping(value = "/list.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public String userList(Model model) {

        return "user/administrators/admin_list";
    }

    /**
     * 管理员页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/adminform.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public String adminform(Model model) {

        return "user/administrators/adminform";
    }

    /**
     * 用户个人页面（基本资料）
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/info.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public String info(Model model) {

        return "set/user/info";
    }

    /**
     * 用户个人页面（基本资料）
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/password.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public String password(Model model) {

        return "set/user/password";
    }

    /**
     * 新增用户页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public String addUser(Model model) {

        return "sysUser/userAdd";
    }

    /**
     * 编辑用户页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/update.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public String updateUser(Model model, Integer id) {
        SysUser user = sysUserService.findByUserId(id);

        model.addAttribute("user", user);
        return "sysUser/userUpdate";
    }

    /**
     * 新增用户
     *
     * @param sysUser
     * @return
     */
    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Callback add(SysUser sysUser) {
        //获取登录的管理员信息
        String adminUsername = (String) SecurityUtils.getSubject().getPrincipal();
        if (adminUsername == null) {
            return returnError("管理员用户登录失效");
        }

        SysUser userInDB = sysUserService.findByUsername(sysUser.getUsername());
        if (userInDB != null) {
            return returnError("用户名已存在,请重新输入");
        }

        int result = sysUserService.createSysUser(sysUser);
        if (result >= 1) {
            return returnSuccess("创建成功");
        } else {
            return returnError("创建失败");
        }
    }

    /**
     * 修改用户信息
     *
     * @param sysUser
     * @return
     */
    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Callback update(SysUser sysUser) {
        //获取登录的管理员信息
        String adminUsername = (String) SecurityUtils.getSubject().getPrincipal();
        if (adminUsername == null) {
            return returnError("管理员用户登录失效");
        }
        SysUser userInDB = sysUserService.findByUsername(sysUser.getUsername());
        if (userInDB != null && !userInDB.getId().equals(sysUser.getId())) {
            return returnError("该用户名已存在,请重新输入");
        }
        int result = sysUserService.updateSysUser(sysUser);
        if (result >= 1) {
            return returnSuccess("更新成功");
        } else {
            return returnError("更新失败");
        }
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Callback delete(@PathVariable("id") Integer id) {
        int result = sysUserService.deleteSysUser(id);
        if (result >= 1) {
            return returnSuccess("删除成功");
        } else {
            return returnError("删除失败");
        }
    }


    /**
     * 用户列表
     *
     * @param dataTablePage
     * @return
     */
    @RequiresPermissions("userList:view")
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Callback userList(DataTablePage dataTablePage, SysUser sysUser) {
        PageInfo pageInfo = sysUserService.findAllPaging(dataTablePage, sysUser);
        List<SysUser> sysUserList = pageInfo.getList();
        List<SysUser> data = new ArrayList<>(200);
        for (SysUser user : sysUserList) {
            SysUser item = new SysUser();
            item.setId(user.getId());
            item.setNickname(user.getNickname());
            
        }
        return returnSuccess(pageInfo.getTotal(), pageInfo.getList());
    }

}
