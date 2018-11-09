package com.yotrio.pound.web.controller;

import com.github.pagehelper.PageInfo;
import com.yotrio.common.domain.Callback;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.model.Goods;
import com.yotrio.pound.model.dto.GoodsDto;
import com.yotrio.pound.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 货品控制类
 * 模块名称：projects-parent com.yotrio.pound.web.controller
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-11-09 13:28
 * 系统版本：1.0.0
 **/
@Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController {
    @Autowired
    private IGoodsService goodsService;

    /**
     * 货品列表页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/list.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Model model) {

        return "goods/goods_list";
    }

    /**
     * 货品表单页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/form.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String form(Model model) {

        return "goods/goods_form";
    }

    /**
     * 分页获取货品列表
     *
     * @param dataTablePage 分页数据封装
     * @param goodsDto    查询条件封装
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public Callback list(DataTablePage dataTablePage, GoodsDto goodsDto) {
        PageInfo pageInfo = goodsService.findAllPaging(dataTablePage, goodsDto);

        return returnSuccess(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 更新货品信息
     *
     * @param goods 货品信息
     * @return
     */
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ResponseBody
    public Callback update(Goods goods) {
        if (goods == null || goods.getId() == null) {
            return returnError("请输入您要更新的内容");
        }

        int result = goodsService.updateById(goods);
        if (result >= 1) {
            return returnSuccess("更新成功");
        } else {
            return returnError("更新失败");
        }
    }

    /**
     * 添加货品信息
     *
     * @param goods 货品信息
     * @return
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody
    public Callback save(Goods goods) {
        if (goods == null) {
            return returnError("请输入您要保存的内容");
        }
        //表单校验
        String checkResult = goodsService.checkForm(goods);
        if (checkResult != null) {
            return returnError(checkResult);
        }

        int result = goodsService.save(goods);
        if (result >= 1) {
            return returnSuccess("保存成功");
        } else {
            return returnError("保存失败");
        }
    }

    /**
     * 根据ids删除货品信息
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
        int result = goodsService.deleteByIds(idList);
        if (result >= 1) {
            return returnSuccess("删除成功");
        } else {
            return returnError("删除失败");
        }
    }
}