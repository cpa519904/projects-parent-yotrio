package com.yotrio.pound.web.controller;

import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.Callback;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.model.PoundInfo;
import com.yotrio.pound.service.IPoundInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 地磅接口控制类
 * 模块名称：projects-parent com.yotrio.pound.web.controller
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-09-25 9:56
 * 系统版本：1.0.0
 **/
@Controller
@RequestMapping("/poundInfo")
public class PoundInfoController extends BaseController {

    @Autowired
    private IPoundInfoService poundInfoService;

    /**
     * 过磅记录列表
     * @param model
     * @return
     */
    @RequestMapping(value = {"/list.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Model model) {

        return "pound/pound_list";
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Callback userList(DataTablePage dataTablePage, PoundInfo poundInfo) {
        PageInfo pageInfo = poundInfoService.findAllPaging(dataTablePage, poundInfo);
        List<PoundInfo> sysUserList = pageInfo.getList();
//        List<PoundInfo> data = new ArrayList<>(200);
//        for (PoundInfo info : sysUserList) {
//            PoundInfo item = new PoundInfo();
//            item.setId(info.getId());
//            item.setNickname(user.getNickname());
//        }
        return returnSuccess(pageInfo.getTotal(), pageInfo.getList());
    }
}