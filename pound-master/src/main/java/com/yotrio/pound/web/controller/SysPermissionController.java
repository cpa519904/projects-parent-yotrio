package com.yotrio.pound.web.controller;

import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.Callback;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.model.SysPermission;
import com.yotrio.pound.service.ISysPermissionService;
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
 * 权限控制类
 * 模块名称：projects-parent com.wyq.admin.web.controller
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-04-22 上午12:25
 * 系统版本：1.0.0
 **/

@Controller
@RequestMapping("/sysPermission")
public class SysPermissionController extends BaseController {
    @Autowired
    private ISysPermissionService sysPermissionService;

    /**
     * 系统权限列表页面
     *
     * @param model
     * @return
     */
    @RequiresPermissions("permissionList:view")
    @RequestMapping(value = "/list.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public String permissionList(Model model) {
        return "user/administrators/permission_list";
    }

    /**
     * 系统权限列表页面
     *
     * @param dataTablePage
     * @return
     */
    @RequiresPermissions("permissionList:view")
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Callback permissionList(DataTablePage dataTablePage, SysPermission sysPermission) {
        PageInfo pageInfo = sysPermissionService.findAllPaging(dataTablePage, sysPermission);
        return returnSuccess(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 系统权限新增页面
     *
     * @param model
     * @return
     */
    @RequiresPermissions("permissionAdd:view")
    @RequestMapping(value = "/add.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public String permissionAdd(Model model) {

        return "sysUser/permissionAdd";
    }


    /**
     * 新增权限
     *
     * @param sysPermission
     * @return
     */
    @RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Callback add(SysPermission sysPermission) {
        //获取登录的管理员信息
        String adminUsername = (String) SecurityUtils.getSubject().getPrincipal();
        if (adminUsername == null) {
            return returnError("管理员用户登录失效");
        }

        SysPermission roleInDB = sysPermissionService.findByPermissionName(sysPermission.getPermissionName());
        if (roleInDB != null) {
            return returnError("权限已存在，请重新输入");
        }

        int result = sysPermissionService.createSysPermission(sysPermission);
        if (result >= 1) {
            return returnSuccess("创建成功");
        } else {
            return returnError("创建失败");
        }
    }

    /**
     * 系统权限修改页面
     *
     * @param model
     * @return
     */
    @RequiresPermissions("permissionForm:view")
    @RequestMapping(value = "/permissionForm.htm", method = {RequestMethod.GET, RequestMethod.POST})
    public String permissionUpdate(Model model, Integer id) {
        SysPermission permission = sysPermissionService.findById(id);
        model.addAttribute("permission", permission);
        return "user/administrators/permission_form";
    }

    /**
     * 修改权限
     *
     * @param sysPermission
     * @return
     */
    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Callback update(SysPermission sysPermission) {
        //获取登录的管理员信息
        String adminUsername = (String) SecurityUtils.getSubject().getPrincipal();
        if (adminUsername == null) {
            return returnError("管理员用户登录失效");
        }

        SysPermission roleInDB = sysPermissionService.findByPermissionName(sysPermission.getPermissionName());
        if (roleInDB != null && !roleInDB.getId().equals(sysPermission.getId())) {
            return returnError("权限已存在，请重新输入");
        }

        int result = sysPermissionService.updateSysPermission(sysPermission);
        if (result >= 1) {
            return returnSuccess("更新成功");
        } else {
            return returnError("更新失败");
        }
    }


    /**
     * 删除权限
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
        int result = sysPermissionService.deleteByIds(idList);
        if (result >= 1) {
            return returnSuccess("您已成功删除" + result + "条数据");
        } else {
            return returnError("删除失败");
        }
    }
}
