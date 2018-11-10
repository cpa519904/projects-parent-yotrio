package com.yotrio.pound.controller;


import com.github.pagehelper.PageInfo;
import com.yotrio.common.constants.TaskConstant;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.domain.Result;
import com.yotrio.pound.model.Task;
import com.yotrio.pound.service.ITaskService;
import com.yotrio.pound.utils.NetStateUtil;
import com.yotrio.pound.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/list.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Model model) {

        return "task/task_list";
    }

    /**
     * 分页获取任务列表
     *
     * @param dataTablePage 分页条件
     * @param task          查询条件
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public Result list(DataTablePage dataTablePage, Task task) {
        task.setStatus(TaskConstant.STATUS_INIT);
        PageInfo pageInfo = taskService.findAllPaging(dataTablePage, task);
        return ResultUtil.success(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 执行任务
     *
     * @param taskId
     * @return
     */
    @RequestMapping(value = "/execute", method = {RequestMethod.GET})
    @ResponseBody
    public Result execute(Integer taskId) {
        //判断网络是否异常
        if (!NetStateUtil.isConnect()) {
           return ResultUtil.error("网络连接失败，请稍后再试...");
        }
        if (taskId == null) {
            return ResultUtil.error("请输入任务ID");
        }
        Task task = taskService.findById(taskId);
        if (task == null) {
            return ResultUtil.error("找不到您要执行的任务");
        }
        if (task.getStatus() == TaskConstant.STATUS_FINISHED) {
            return ResultUtil.success("已执行");
        }

        //执行任务
        String result = taskService.executeTask(task);
        if (result == null) {
            return ResultUtil.success("执行成功");
        } else {
            return ResultUtil.success(result);
        }
    }


}
