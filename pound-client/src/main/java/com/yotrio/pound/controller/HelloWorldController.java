package com.yotrio.pound.controller;

import com.yotrio.pound.dao.TaskRepository;
import com.yotrio.pound.domain.Result;
import com.yotrio.pound.model.Task;
import com.yotrio.pound.utils.ResultUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 模块名称：demo com.example.demo.controller
 * 功能说明：<br> @RestController的意思就是controller里面的方法都以json格式输出，不用再写什么jackjson配置的了！
 * 开发人员：wangyiqiang
 * 创建时间： 2018-08-31 下午1:23
 * 系统版本：1.0.0
 **/

@RestController
public class HelloWorldController {
    private static final Log log = LogFactory.getLog(HelloWorldController.class);

    @Autowired
    private TaskRepository taskRepository;


    @RequestMapping("/hello")
    public String index() {
        log.info("hello world");

        return "中文会乱码吗？";
    }

    /**
     * 查找所有用户
     *
     * @return
     */
    @GetMapping("/list")
    public List<Task> userList() {
        return taskRepository.findAll();
    }

    /**
     * 根据用户名查找
     *
     * @return
     */
    @GetMapping("/findByUsername/{username}")
    public Result findByUsername(@PathVariable("username") String username) {

        return ResultUtil.success(null);
//        //jpa获取查用户信息
//        return taskRepository.findByUsername(username);
    }

    /**
     * valid校验
     *
     * @return
     */
    @PostMapping("/save")
    public int userValid(@Valid Task task, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return 0;
        }
        return 1;
    }

    /**
     * 事务案例
     *
     * @return
     */
    @PostMapping("/checkUser")
    public Result saveTwo() {
//        zsUserService.checkUser(10000L);
        return ResultUtil.success(null);
    }

    /**
     * 通过手机号码查找用户
     *
     * @param mobile
     * @return
     */
    @GetMapping("findByMobile")
    public Result findByMobile(String mobile) {
//        List<ZsUser> zsUsers = userService.findByMobile(mobile);
        return ResultUtil.success(null);
    }
}