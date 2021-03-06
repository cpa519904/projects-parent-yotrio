package com.yotrio.pound.controller;


import com.github.pagehelper.PageInfo;
import com.yotrio.common.constants.PoundLogConstant;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.pound.domain.Result;
import com.yotrio.pound.domain.SystemProperties;
import com.yotrio.pound.model.Inspection;
import com.yotrio.pound.model.PoundLog;
import com.yotrio.pound.service.ICompanyService;
import com.yotrio.pound.service.IGoodsService;
import com.yotrio.pound.service.IInspectionService;
import com.yotrio.pound.service.IPoundLogService;
import com.yotrio.pound.utils.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 报检单控制接口类
 * 模块名称：study-boot com.wangyq.controller
 * 功能说明：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2018-08-31 下午5:06
 * 系统版本：1.0.0
 **/

@Controller
@RequestMapping("/inspection")
public class InspectionController extends BaseController {

    @Autowired
    private IInspectionService inspectionService;
    @Autowired
    private IPoundLogService poundLogService;
    @Autowired
    private SystemProperties sysProperties;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ICompanyService companyService;

    /**
     * 添加报检单页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/form.htm"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String inspection(Model model) {
        model.addAttribute("goodsList", goodsService.findAll());
        model.addAttribute("companyList", companyService.findAll());
        return "home/inspect_form";
    }

    /**
     * 添加报检单
     *
     * @param inspection
     * @return
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody
    public Result save(Inspection inspection) {
        if (inspection == null) {
            return ResultUtil.error("报检单信息不能为空");
        }
        if (StringUtils.isEmpty(inspection.getPlNo())) {
            return ResultUtil.error("过磅单单号不能为空");
        }
        if (StringUtils.isEmpty(inspection.getPlateNo())) {
            return ResultUtil.error("车牌号码不能为空");
        }

        //校验报检单
        String checkResult = inspectionService.checkFormSave(inspection);
        if (StringUtils.isNotEmpty(checkResult)) {
            return ResultUtil.error(checkResult);
        }

        //一张报检单只能使用一次
        Inspection inspectionInDB = inspectionService.findByInspNo(inspection.getInspNo());
        if (inspectionInDB != null) {
            return ResultUtil.error("该报检单已录入系统，请换张报检单");
        }

        //查询是否已生成过磅记录，未生成的先生成过磅记录
        PoundLog logInDB = poundLogService.findByPoundLogNo(inspection.getPlNo());
        if (logInDB == null) {
            PoundLog poundLog = new PoundLog();
            poundLog.setPoundId(sysProperties.getPoundClientId());
            poundLog.setPoundName(sysProperties.getPoundClientName());
            poundLog.setGoodsCode(inspection.getGoodsCode());
            String goodsName = goodsService.findGoodsNameByGoodsCode(inspection.getGoodsCode());
            if (StringUtils.isNotEmpty(goodsName)) {
                poundLog.setGoodsName(goodsName);
            }
            poundLog.setCompName(inspection.getCompName());
            poundLog.setPoundLogNo(inspection.getPlNo());
            poundLog.setPlateNo(inspection.getPlateNo());
            poundLog.setCreateTime(new Date());
            poundLog.setStatus(PoundLogConstant.STATUS_INIT);
            poundLog.setTypes(PoundLogConstant.TYPES_IN);
            //如果填了收货单位，先保存
            if (StringUtils.isNotEmpty(inspection.getUnit_name())) {
                poundLog.setUnitName(inspection.getUnit_name());
            }
            logInDB = poundLog;
            int result = poundLogService.save(poundLog);
            if (result < 1) {
                return ResultUtil.error("过磅记录生成失败");
            }
        }

        //过磅单状态控制，提交之后不能再修改
        if (logInDB.getStatus() > PoundLogConstant.STATUS_POUND_SECOND) {
            return ResultUtil.error("过磅单已提交，不能再修改");
        }

        //出货不能添加或者删除报检单
        if (logInDB.getTypes() == PoundLogConstant.TYPES_OUT) {
            return ResultUtil.error("出货不支持添加报检单");
        }

        int saveResult = inspectionService.save(inspection);
        if (saveResult >= 1) {
            //添加成功更新过磅记录中数据 报检单总数、退回总数、样品总数
            poundLogService.updateWeight(logInDB);
            return ResultUtil.success(logInDB);
        } else {
            return ResultUtil.error("添加失败");
        }
    }

    /**
     * 更新报检单
     *
     * @param inspection
     * @return
     */
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ResponseBody
    public Result update(Inspection inspection) {
        if (inspection == null) {
            return ResultUtil.error("报检单信息不能为空");
        }
        if (StringUtils.isEmpty(inspection.getPlNo())) {
            return ResultUtil.error("过磅单单号不能为空");
        }

        //查询是否已生成过磅记录
        PoundLog logInDB = poundLogService.findByPoundLogNo(inspection.getPlNo());
        if (logInDB == null) {
            return ResultUtil.error("找不到相应的过磅记录，无法修改报检单");
        }
        //过磅单状态控制，提交之后不能再修改
        if (logInDB.getStatus() > PoundLogConstant.STATUS_POUND_SECOND) {
            return ResultUtil.error("过磅单已提交，不能再修改");
        }


        int result = inspectionService.update(inspection);
        if (result >= 1) {
            //重新计算过磅记录中的重量信息
            logInDB.setCompName(inspection.getCompName());
            logInDB.setGoodsCode(inspection.getGoodsCode());
            String goodsName = goodsService.findGoodsNameByGoodsCode(inspection.getGoodsCode());
            logInDB.setGoodsName(goodsName);
            poundLogService.updateWeight(logInDB);
            return ResultUtil.success(logInDB);
        } else {
            return ResultUtil.error("修改失败");
        }
    }

    /**
     * 根据ids删除报检单信息
     *
     * @param ids        [1,2,3]
     * @param poundLogNo 过磅单单号
     * @return
     */
    @RequestMapping(value = "/deleteByIds", method = {RequestMethod.GET})
    @ResponseBody
    public Result deleteByIds(String ids, String poundLogNo) {
        PoundLog logInDB = poundLogService.findByPoundLogNo(poundLogNo);
        if (logInDB == null) {
            return ResultUtil.error("找不到相应的过磅记录");
        }
        //出货不能添加或者删除报检单
        if (logInDB.getTypes() == PoundLogConstant.TYPES_OUT) {
            return ResultUtil.error("出货不支持删除报检单");
        }
        //过磅单状态控制，提交之后不能再修改
        if (logInDB.getStatus() > PoundLogConstant.STATUS_POUND_SECOND) {
            return ResultUtil.error("过磅单已提交，不能再修改");
        }

        if (ids == null || ids.split(",").length == 0) {
            return ResultUtil.error("请选择您要删除的数据");
        }
        //解析ids
        List<Integer> idList = new ArrayList<>(100);
        String[] strs = ids.split(",");
        for (int i = 0; i < strs.length; i++) {
            idList.add(Integer.valueOf(strs[i]));
        }
        int result = inspectionService.deleteByIds(idList);
        if (result >= 1) {
            poundLogService.updateWeight(logInDB);
            return ResultUtil.success(logInDB);
        } else {
            return ResultUtil.error("删除失败");
        }
    }

    /**
     * 获取报检单列表
     *
     * @param dataTablePage 分页条件
     * @param inspection    查询条件
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public Result list(DataTablePage dataTablePage, Inspection inspection) {
        PageInfo pageInfo = inspectionService.findAllPaging(dataTablePage, inspection);
        return ResultUtil.success(pageInfo.getTotal(), pageInfo.getList());
    }
}
