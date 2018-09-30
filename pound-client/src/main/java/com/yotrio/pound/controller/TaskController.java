package com.yotrio.pound.controller;


import cn.hutool.core.bean.BeanUtil;
import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.domain.Result;
import com.yotrio.pound.model.Task;
import com.yotrio.pound.service.ITaskService;
import com.yotrio.pound.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 任务控制接口类
 * 模块名称：study-boot com.wangyq.controller
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-08-31 下午5:06
 * 系统版本：1.0.0
 **/

@Controller
@RequestMapping("/task")
public class TaskController extends BaseController {

    @Autowired
    private ITaskService taskService;

    /**
     * 任务列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = { "/list.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Model model) {

        return "task/task_list";
    }

    /**
     * 分页获取任务列表
     * @param dataTablePage 分页条件
     * @param task 查询条件
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public Result list(DataTablePage dataTablePage, Task task) {
        Map<String, Object> map = BeanUtil.beanToMap(task);
        PageInfo pageInfo = taskService.findAllPaging(dataTablePage, task);
        return ResultUtil.success(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 控制台
     * @param model
     * @return
     */
    @RequestMapping(value = { "/form.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String form(Model model) {

        return "task/task_form";
    }


}
