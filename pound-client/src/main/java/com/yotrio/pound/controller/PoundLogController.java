package com.yotrio.pound.controller;


import com.yotrio.pound.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 任务控制接口类
 * 模块名称：study-boot com.wangyq.controller
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-08-31 下午5:06
 * 系统版本：1.0.0
 **/

@Controller
@RequestMapping("/poundLog")
public class PoundLogController extends BaseController {

    @Autowired
    private ITaskService taskService;

    /**
     * 过磅记录列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = { "/list.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Model model) {

        return "pound/pound_log_list";
    }

}
