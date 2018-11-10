package com.yotrio.pound.web.controller;

import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.Callback;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.model.Goods;
import com.yotrio.pound.model.Organization;
import com.yotrio.pound.model.PoundInfo;
import com.yotrio.pound.model.PoundLog;
import com.yotrio.pound.model.dto.PoundLogDto;
import com.yotrio.pound.service.IGoodsService;
import com.yotrio.pound.service.IOrganizationService;
import com.yotrio.pound.service.IPoundInfoService;
import com.yotrio.pound.service.IPoundLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 过磅记录接口控制类
 * 模块名称：projects-parent com.yotrio.pound.web.controller
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-09-25 9:56
 * 系统版本：1.0.0
 **/
@Controller
@RequestMapping("/poundLog")
public class PoundLogController extends BaseController {

    @Autowired
    private IPoundLogService poundLogService;
    @Autowired
    private IPoundInfoService poundInfoService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private IOrganizationService organizationService;

    /**
     * 过磅记录列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/list.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Model model) {
        List<Goods> goodsList = goodsService.findAllCache();
        List<Organization> organizationList = organizationService.findAllCache();
        model.addAttribute("goodsList", goodsList);
        model.addAttribute("organizationList", organizationList);
        return "pound/pound_log_list";
    }

    /**
     * 过磅记录详情页面
     */
    @RequestMapping(value = {"/detail.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String detail(Model model, String poundLogNo) {
        PoundLog poundLog = poundLogService.findByPoundLogNo(poundLogNo);
        if (poundLog != null) {
            PoundInfo poundInfo = poundInfoService.findCacheById(poundLog.getPoundId());
            if (poundInfo != null) {
                model.addAttribute("poundInfo", poundInfo);
            }
        }
        model.addAttribute("poundLog", poundLog);
        return "pound/pound_log_detail";
    }

    /**
     * 分页获取过磅记录列表
     *
     * @param dataTablePage 分页条件
     * @param poundLogDto   过磅记录查询条件
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public Callback list(DataTablePage dataTablePage, PoundLogDto poundLogDto) {
        PageInfo pageInfo = poundLogService.findAllPaging(dataTablePage, poundLogDto);
        return returnSuccess(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 过磅记录报表展示
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/form.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String form(Model model) {
        return "pound/pound_log_form";
    }

    /**
     * 过磅记录报表展示
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/reportForm.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String reportForm(Model model) {
        return "pound/report_form";
    }

    /**
     * 根据id删除过磅信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = {RequestMethod.GET})
    @ResponseBody
    public Callback delete(Integer id) {
        if (id == null) {
            return returnError("请选择您要删除的数据");
        }

        int result = poundLogService.deleteById(id);
        if (result >= 1) {
            return returnSuccess("删除成功");
        } else {
            return returnError("删除失败");
        }
    }

    /**
     * 根据ids删除过磅信息
     *
     * @param ids [1,2,3]
     * @return
     */
    @RequestMapping(value = "/deleteByIds", method = {RequestMethod.GET})
    @ResponseBody
    public Callback deleteByIds(String ids) {
        if (ids == null || ids.split(",").length == 0) {
            return returnError("请选择您要删除的数据");
        }
        //解析ids
        List<Integer> idList = new ArrayList<>(100);
        String[] strs = ids.split(",");
        for (int i = 0; i < strs.length; i++) {
            idList.add(Integer.valueOf(strs[i]));
        }
        int result = poundLogService.deleteByIds(idList);
        if (result >= 1) {
            return returnSuccess("删除成功");
        } else {
            return returnError("删除失败");
        }
    }

}