package com.yotrio.touch.controller;


import com.yotrio.touch.model.PoundLog;
import com.yotrio.touch.model.Result;
import com.yotrio.touch.service.IHttpService;
import com.yotrio.touch.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * http请求控制类
 * 模块名称：projects-parent com.yotrio.touch.controller
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-11-13 15:01
 * 系统版本：1.0.0
 **/
@Controller
@RequestMapping("/api")
@CrossOrigin
public class ApiController extends BaseController {

    @Autowired
    private IHttpService httpService;

    /**
     * 获取确认页面信息
     *
     * @param token 校验token
     * @param plId  过磅记录id
     * @return
     */
    @RequestMapping(value = "/getConfirmMessage", method = {RequestMethod.GET})
    @ResponseBody
    public Result getConfirmMessage(String token, Integer plId) {
        PoundLog poundLog = httpService.getPoundLogWithInspections(plId, token);
        if (poundLog != null) {
            return ResultUtil.success(poundLog);
        }

        return ResultUtil.error("数据获取失败");
    }
}