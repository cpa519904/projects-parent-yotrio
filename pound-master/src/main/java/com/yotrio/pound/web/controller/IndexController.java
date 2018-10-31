package com.yotrio.pound.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 模块名称：projects-parent com.wyq.wechat.controller
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-09-17 上午10:09
 * 系统版本：1.0.0
 **/
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    /**
     * 首页
     * @param model
     * @return
     */
    @RequestMapping(value = {"/", "/index.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model) {
//        String username = (String) SecurityUtils.getSubject().getPrincipal();
//        model.addAttribute("username", username);
        return "index";
    }

    /**
     * 控制台
     * @param model
     * @return
     */
//    @RequestMapping(value = { "/console.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
//    public String console(Model model) {
//
//        return "home/console";
//    }

    /**
     * totalRow
     * @param model
     * @return
     */
    @RequestMapping(value = {"/totalRow.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String totalRow(Model model) {

        return "component/table/totalRow";
    }
}
