package com.yotrio.pound.controller;

import com.yotrio.pound.model.Inspection;
import com.yotrio.pound.model.Organization;
import com.yotrio.pound.model.PoundLog;
import com.yotrio.pound.service.IInspectionService;
import com.yotrio.pound.service.IOrganizationService;
import com.yotrio.pound.service.IPoundLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 模块名称：demo com.example.demo.controller
 * 功能说明：<br> @RestController的意思就是controller里面的方法都以json格式输出，不用再写什么jackjson配置的了！
 * 开发人员：wangyiqiang
 * 创建时间： 2018-08-31 下午1:23
 * 系统版本：1.0.0
 **/

@Controller
@RequestMapping("/")
public class IndexController extends BaseController{

    @Autowired
    private IPoundLogService poundLogService;
    @Autowired
    private IOrganizationService organizationService;
    @Autowired
    private IInspectionService inspectionService;

    @RequestMapping(value = {"/", "/index.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model) {

        return "index";
    }

    /**
     * 控制台
     * @param model
     * @return
     */
    @RequestMapping(value = { "/console.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String console(Model model,String plNo) {
        List<Organization> organizationList = organizationService.findAll();
        model.addAttribute("organizationList", organizationList);
        model.addAttribute("plNo", plNo);
        return "home/console";
    }

    /**
     * 打印过磅单页面
     * @param model
     * @return
     */
    @RequestMapping(value = { "/printPoundLog.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String printPoundLog(Model model,String plNo) {
        PoundLog poundLog = poundLogService.findByPoundLogNo(plNo);
        List<Inspection> inspections = inspectionService.findListByPlNo(plNo);
        double totalInspNetWeight = 0.0d;
        for (Inspection inspection : inspections) {
            totalInspNetWeight += inspection.getInspNetWeight();
        }
        model.addAttribute("poundLog", poundLog);
        model.addAttribute("inspections", inspections);
        model.addAttribute("totalInspNetWeight", totalInspNetWeight);
        return "print/print_pound_log";
    }


}