package com.yotrio.pound.controller;

import com.yotrio.pound.service.IPoundLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        //未处理过磅记录列表
//        List<PoundLog> poundLogs = poundLogService.listUnFinished();
        //报检单列表及过磅单数据

//        model.addAttribute("poundLogs", poundLogs);
        model.addAttribute("plNo", plNo);
        return "home/console";
    }

    /**
     * 打印过磅单页面
     * @param model
     * @return
     */
    @RequestMapping(value = { "/printPoundLog.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String printPoundLog(Model model) {
        return "print/print_pound_log";
    }


}