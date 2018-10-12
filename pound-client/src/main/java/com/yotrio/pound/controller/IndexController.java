package com.yotrio.pound.controller;

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
    public String console(Model model) {

        return "home/console";
    }

    /**
     * 控制台
     * @param model
     * @return
     */
    @RequestMapping(value = { "/inspection/form.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String inspection(Model model) {

        return "home/inspect_form";
    }
}