package com.yotrio.pound.web.controller.api;

import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.Callback;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.model.dto.PoundLogDto;
import com.yotrio.pound.service.IPoundLogService;
import com.yotrio.pound.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 外部接口控制类
 * 模块名称：projects-parent com.yotrio.pound.web.controller
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-09-25 9:56
 * 系统版本：1.0.0
 **/
@Controller
@RequestMapping("/api")
public class ApiController extends BaseController {

    @Autowired
    private IPoundLogService poundLogService;

    /**
     * 分页获取过磅记录列表
     * @param dataTablePage 分页条件
     * @param poundLogDto 过磅记录查询条件
     * @return
     */
    @RequestMapping(value = "/poundLog/list", method = {RequestMethod.GET})
    @ResponseBody
    public Callback list(DataTablePage dataTablePage, PoundLogDto poundLogDto) {
        PageInfo pageInfo = poundLogService.findAllPaging(dataTablePage, poundLogDto);
        return returnSuccess(pageInfo.getTotal(), pageInfo.getList());
    }

}