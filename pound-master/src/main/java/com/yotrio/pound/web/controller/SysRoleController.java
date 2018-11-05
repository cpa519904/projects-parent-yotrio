package com.yotrio.pound.web.controller;

import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.Callback;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.model.SysRole;
import com.yotrio.pound.service.ISysRoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色控制类
 * 模块名称：projects-parent com.wyq.admin.web.controller
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-04-22 上午12:24
 * 系统版本：1.0.0
 **/

@Controller
@RequestMapping("/sysRole")
public class SysRoleController extends BaseController {

    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 系统角色列表 页面
     *
     * @param model
     * @return
     */
    @RequiresPermissions("roleList:view")
    @RequestMapping(value = "/list.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public String roleList(Model model) {
        return "user/administrators/role_list";
    }

    /**
     * 新增角色 页面
     *
     * @param model
     * @return
     */
//    @RequiresPermissions("add:view")
    @RequestMapping(value = "/add.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public String add(Model model) {

        return "sysUser/roleAdd";
    }

    /**
     * 新增角色
     *
     * @param sysRole
     * @return
     */
    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Callback add(SysRole sysRole) {
        //获取登录的管理员信息
        String adminUsername = (String) SecurityUtils.getSubject().getPrincipal();
        if (adminUsername == null) {
            return returnError("管理员用户登录失效");
        }

        SysRole roleInDB = sysRoleService.findByRoleName(sysRole.getRoleName());
        if (roleInDB != null) {
            return returnError("角色已存在，请重新输入");
        }

        int result = sysRoleService.createSysRole(sysRole);
        if (result >= 1) {
            return returnSuccess("创建成功");
        } else {
            return returnError("创建失败");
        }
    }


    /**
     * 修改角色 页面
     *
     * @param model
     * @return
     */
    @RequiresPermissions("update:view")
    @RequestMapping(value = "/roleForm.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public String roleForm(Model model, Integer id) {
        SysRole sysRole = sysRoleService.findById(id);
        model.addAttribute("role", sysRole);
        return "user/administrators/role_form";
    }

    /**
     * 修改角色
     *
     * @param sysRole
     * @return
     */
    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Callback update(SysRole sysRole) {
        //获取登录的管理员信息
        String adminUsername = (String) SecurityUtils.getSubject().getPrincipal();
        if (adminUsername == null) {
            return returnError("管理员用户登录失效");
        }

        SysRole roleInDB = sysRoleService.findByRoleName(sysRole.getRoleName());
        if (roleInDB != null && !roleInDB.getId().equals(sysRole.getId())) {
            return returnError("角色已存在，请重新输入");
        }

        int result = sysRoleService.updateSysRole(sysRole);
        if (result >= 1) {
            return returnSuccess("更新成功");
        } else {
            return returnError("更新失败");
        }
    }

    /**
     * 角色列表分页
     *
     * @param dataTablePage
     * @return
     */
    @RequiresPermissions("roleList:view")
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Callback roleList(DataTablePage dataTablePage, SysRole sysRole) {
        PageInfo pageInfo = sysRoleService.findAllPaging(dataTablePage, sysRole);
        return returnSuccess(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 删除角色
     *
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Callback delete(String ids) {
        List<Integer> idList = new ArrayList<>(200);
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            idList.add(Integer.valueOf(idsStr[i]));
        }
        int result = sysRoleService.deleteByIds(idList);
        if (result >= 1) {
            return returnSuccess("您已成功删除" + result + "条数据");
        } else {
            return returnError("删除失败");
        }
    }
}
