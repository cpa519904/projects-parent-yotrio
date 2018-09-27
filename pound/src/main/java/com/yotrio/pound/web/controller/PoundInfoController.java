package com.yotrio.pound.web.controller;

import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.Callback;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.model.PoundInfo;
import com.yotrio.pound.service.IPoundInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 地磅接口控制类
 * 模块名称：projects-parent com.yotrio.pound.web.controller
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-09-25 9:56
 * 系统版本：1.0.0
 **/
@Controller
@RequestMapping("/poundInfo")
public class PoundInfoController extends BaseController {

    @Autowired
    private IPoundInfoService poundInfoService;

    /**
     * 地磅列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/list.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Model model) {

        return "pound/pound_list";
    }

    /**
     * 分页获取地磅列表
     *
     * @param dataTablePage
     * @param poundInfo
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public Callback list(DataTablePage dataTablePage, PoundInfo poundInfo) {
        PageInfo pageInfo = poundInfoService.findAllPaging(dataTablePage, poundInfo);

        return returnSuccess(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 地磅表单页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/form.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String form(Model model) {

        return "pound/pound_form";
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ResponseBody
    public Callback update(PoundInfo poundInfo) {
        if (poundInfo == null || poundInfo.getId() == null) {
            return returnError("请输入您要更新的内容");
        }

        int result = poundInfoService.updateById(poundInfo);
        if (result >= 1) {
            return returnSuccess("更新成功");
        } else {
            return returnError("更新失败");
        }
    }


}