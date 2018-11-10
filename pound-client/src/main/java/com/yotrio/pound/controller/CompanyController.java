package com.yotrio.pound.controller;

import com.yotrio.pound.domain.Result;
import com.yotrio.pound.model.Company;
import com.yotrio.pound.service.ICompanyService;
import com.yotrio.pound.utils.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 供应商接口控制类
 * 模块名称：projects-parent com.yotrio.pound.controller
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-11-10 16:58
 * 系统版本：1.0.0
 **/
@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController {

    @Autowired
    private ICompanyService companyService;

    /**
     * 获取供应商 by 供应商编码
     *
     * @param compCode
     * @return
     */
    @RequestMapping(value = "/getInfoByCompCode", method = {RequestMethod.POST})
    @ResponseBody
    public Result getInfoByCompCode(String compCode) {
        if (StringUtils.isEmpty(compCode)) {
            return ResultUtil.error("供应商编码为空");
        }

        //获取供应商
        Company company = companyService.findByCompCode(compCode);
        return ResultUtil.success(company);
    }
}