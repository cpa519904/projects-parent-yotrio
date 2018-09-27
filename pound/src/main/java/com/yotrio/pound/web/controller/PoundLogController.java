package com.yotrio.pound.web.controller;

import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.Callback;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.model.PoundLog;
import com.yotrio.pound.service.IPoundLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 过磅记录接口控制类
 * 模块名称：projects-parent com.yotrio.pound.web.controller
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-09-25 9:56
 * 系统版本：1.0.0
 **/
@Controller
@RequestMapping("/poundLog")
public class PoundLogController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(PoundLogController.class);

    @Autowired
    private IPoundLogService poundLogService;

    /**
     * 过磅记录列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/list.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Model model) {
        return "pound/pound_log_list";
    }

    /**
     * 分页获取过磅记录列表
     * @param dataTablePage 分页条件
     * @param poundLog 过磅记录查询条件
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public Callback list(DataTablePage dataTablePage, PoundLog poundLog) {
        PageInfo pageInfo = poundLogService.findAllPaging(dataTablePage, poundLog);
        return returnSuccess(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 过磅记录报表展示
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/form.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String form(Model model) {
        return "pound/pound_log_form";
    }

    /**
     * 过磅记录报表展示
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/reportForm.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String reportForm(Model model) {
        return "pound/report_form";
    }


}