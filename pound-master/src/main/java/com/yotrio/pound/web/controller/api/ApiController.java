package com.yotrio.pound.web.controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.yotrio.common.constants.PoundConstant;
import com.yotrio.common.constants.PoundLogConstant;
import com.yotrio.common.constants.TaskConstant;
import com.yotrio.common.domain.Callback;
import com.yotrio.common.domain.DataTablePage;
import com.yotrio.common.helpers.UserAuthTokenHelper;
import com.yotrio.pound.exceptions.UploadLogException;
import com.yotrio.pound.model.*;
import com.yotrio.pound.model.dto.PoundLogDto;
import com.yotrio.pound.service.*;
import com.yotrio.pound.web.controller.BaseController;
import com.yotrio.pound.web.shiro.utils.PasswordHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private IPoundInfoService poundInfoService;
    @Autowired
    private IInspectionService inspectionService;
    @Autowired
    private IDingTalkService dingTalkService;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private IOrganizationService organizationService;
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IStoreKeeperService storeKeeperService;
    @Autowired
    private PasswordHelper passwordHelper;

    /**
     * 分页获取过磅记录列表
     *
     * @param dataTablePage 分页条件
     * @param poundLogDto   过磅记录查询条件
     * @param token         认证秘钥
     * @return
     */
    @RequestMapping(value = "/poundLog/list", method = {RequestMethod.GET})
    @ResponseBody
    public Callback list(DataTablePage dataTablePage, PoundLogDto poundLogDto, String token) {
        if (poundLogDto == null) {
            return returnError("地磅参数异常");
        }
        if (StringUtils.isEmpty(token)) {
            return returnError("token丢失");
        }
        Integer poundId = UserAuthTokenHelper.getAppUserId(token);
        if (poundId == null) {
            return returnError("token校验失败");
        }
        PoundInfo poundInfo = poundInfoService.findCacheById(poundId);
        if (poundInfo == null) {
            return returnError("获取地磅信息失败");
        }

        PageInfo pageInfo = poundLogService.findAllPaging(dataTablePage, poundLogDto);
        return returnSuccess(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 根据根据过磅单那报检单列表
     *
     * @param plNo  过磅单单号
     * @param token 认证秘钥
     * @return
     */
    @RequestMapping(value = "/inspection/list", method = {RequestMethod.GET})
    @ResponseBody
    public Callback list(String plNo, String token) {
        Integer poundId = UserAuthTokenHelper.getAppUserId(token);
        if (poundId == null) {
            return returnError("token校验失败");
        }
        if (StringUtils.isEmpty(plNo)) {
            return returnError("过磅单单号为空");
        }
        PoundLog poundLog = poundLogService.findByPoundLogNoAndPoundId(plNo, poundId);
        if (poundLog == null) {
            return returnError("获取过磅信息失败");
        }
        List<Inspection> inspections = inspectionService.findListByPlNo(plNo);
        if (inspections == null || inspections.size() == 0) {
            return returnError("报检单数据为空");
        }
        return returnSuccess(Long.valueOf(inspections.size()), inspections);
    }

    /**
     * 根据id获取地磅信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/pound/info/{id}", method = {RequestMethod.GET})
    @ResponseBody
    public Callback info(@PathVariable(value = "id") Integer id) {
        if (id == null) {
            return returnError("地磅ID不能为空");
        }
        PoundInfo poundInfo = poundInfoService.findCacheById(id);
        return returnSuccess(poundInfo);
    }

    /**
     * 根据id获取地磅信息
     *
     * @param token      校验凭证
     * @param poundLogNo 过磅单单号
     * @return
     */
    @RequestMapping(value = "/getPoundLogDetail", method = {RequestMethod.GET})
    @ResponseBody
    public Callback getPoundLogDetail(String token, String poundLogNo) {
        Integer poundId = UserAuthTokenHelper.getAppUserId(token);
        if (poundId == null) {
            return returnError("token校验失败");
        }
        if (StringUtils.isEmpty(poundLogNo)) {
            return returnError("过磅单号为空");
        }

        //获取过磅信息
        PoundLog poundLog = poundLogService.findByPoundLogNoAndPoundId(poundLogNo, poundId);
        PoundInfo poundInfo = poundInfoService.findCacheById(poundId);
        JSONObject dataObject = new JSONObject();
        dataObject.put("poundLog", poundLog);
        dataObject.put("poundInfo", poundInfo);

        return returnSuccess(dataObject);
    }

    /**
     * 存储过磅记录,推送钉钉消息
     *
     * @return
     */
    @RequestMapping(value = "/poundLog/save", method = {RequestMethod.POST})
    @ResponseBody
    public Callback savePoundLog(String token, String data) {

        PoundLog poundLog;
        List<Inspection> inspections;
        try {
            //字符串格式转换，防止中文乱码，这里很奇怪，用httpUtil上传整合shiro后就会出现中文乱码问题,暂未有更好解决办法
            data = new String(data.getBytes("iso-8859-1"), "utf-8");
            token = new String(token.getBytes("iso-8859-1"), "utf-8");
            Integer poundId = UserAuthTokenHelper.getAppUserId(token);
            if (poundId == null) {
                return returnError("无效的token");
            }
            if (data == null) {
                return returnError("数据上传失败");
            }
            JSONObject root = JSON.parseObject(data);
            JSONObject poundLogObj = root.getJSONObject("poundLog");
            JSONArray inspectionsArr = root.getJSONArray("inspections");
            poundLog = JSON.parseObject(JSONObject.toJSONString(poundLogObj), PoundLog.class);
            inspections = JSONArray.parseArray(JSONArray.toJSONString(inspectionsArr), Inspection.class);

            if (poundLog != null) {
                //校验地磅状态
                PoundInfo poundInfo = poundInfoService.findCacheById(poundId);
                if (poundInfo == null) {
                    return returnError("找不到对应的地磅信息");
                }
                if (poundInfo.getStatus() == PoundConstant.STATUS_STOP) {
                    return returnError("此地磅已被停用，请联系管理员处理...");
                }
                poundLog.setPoundName(poundInfo.getPoundName());

                //交给事务去处理并保存过磅单以及报检单
                poundLogService.savePoundLogAndInspections(poundLog, inspections);

                //进货有报检单的情况下，发送钉钉消息并更新进货单
                if (poundLog.getTypes() == PoundLogConstant.TYPES_IN && inspections != null && inspections.size() > 0) {
                    //是否发送成功
                    boolean sendResult = false;
                    List<String> userIds = new ArrayList<>(20);
                    //根据组织和物料获取仓管员信息
                    // TODO: 2018-11-10
                    StoreKeeper storeKeeper = storeKeeperService.findByOrgCodeAndGoodsCode(poundLog.getOrgCode(), poundLog.getGoodsCode());
                    //通过员工工号获取钉钉用户id
                    String dingUserId = dingTalkService.getCacheDingTalkUserIdByMobile(poundInfo.getAdminMobile());
                    if (dingUserId != null) {
                        userIds.add(dingUserId);
                    }
                    if (userIds.size() > 0) {
                        String userIdList = StringUtils.join(userIds, ",");
                        PoundLog logInDB = poundLogService.findByPoundLogNoAndPoundId(poundLog.getPoundLogNo(), poundId);
                        sendResult = dingTalkService.sendConfirmMessage(token, logInDB, userIdList, inspections);
                    }

                    if (!sendResult) {
                        //发送失败，创建任务，定时去执行发送
                        Task task = new Task();
                        task.setStatus(TaskConstant.STATUS_INIT);
                        task.setOtherId(poundLog.getId().toString());
                        task.setTaskName("发送钉钉消息");
                        task.setTypes(TaskConstant.TYPE_SEND_DING_TALK_CONFIRM_MSG);
                        task.setWeight(TaskConstant.WEIGHT_INIT);
                        StringBuffer sb = new StringBuffer();
                        sb.append("过磅记录ID：").append(poundLog.getId()).append("|地磅ID：").append(poundInfo.getId()).append("|管理员工号：").append(poundInfo
                                .getAdminEmpId());
                        task.setDescription(sb.toString());
                        taskService.save(task);
                    }
                }

                return returnSuccess("上传成功");
            }
        } catch (UploadLogException e) {
            logger.error("上传过磅信息异常:", e.getMessage());
        } catch (UnsupportedEncodingException e) {
            logger.error("上传过磅信息异常:", e.getMessage());
        }
        return returnError("数据上传失败");
    }

    /**
     * 获取确认页面信息
     *
     * @param token 校验token
     * @param plId  过磅记录id
     * @return
     */
    @RequestMapping(value = "/getConfirmMessage", method = {RequestMethod.GET})
    @ResponseBody
    public Callback getConfirmMessage(String token, Integer plId) {
        Integer poundId = UserAuthTokenHelper.getAppUserId(token);
        if (poundId == null) {
            return returnError("token验证失败");
        }
        PoundInfo poundInfo = poundInfoService.findCacheById(poundId);
        if (poundInfo == null) {
            return returnError("过去地磅信息失败");
        }
        if (plId == null) {
            return returnError("过磅单号为空");
        }
        PoundLog poundLog = poundLogService.findCacheById(plId);
        if (poundLog == null) {
            return returnError("获取过磅单失败");
        }
        List<Inspection> inspections = inspectionService.findListByPlId(poundLog.getId());
        poundLog.setInspections(inspections);

        return returnSuccess(poundLog);
    }

    /**
     * 获取地磅的token
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/getPoundToken", method = {RequestMethod.GET})
    @ResponseBody
    public Callback getConfirmMessage(String username, String password) {
        if (StringUtils.isEmpty(username)) {
            return returnError("用户名不能空");
        }
        if (StringUtils.isEmpty(password)) {
            return returnError("密码不能为空");
        }
        SysUser sysUser = sysUserService.findByUsername(username);
        if (sysUser == null) {
            return returnError("用户不存在");
        }

        String encryptPassword = passwordHelper.getEncryptPassword(sysUser, password);
        if (!sysUser.getPassword().equals(encryptPassword)) {
            return returnError("登录失败，请检查您的用户名和密码");
        }
        String token = UserAuthTokenHelper.getUserAuthToken(sysUser.getId(), null);
        JSONObject object = new JSONObject();
        object.put("token", token);
        return returnSuccess(object);
    }

    /**
     * 获取报检单 给u9系统用
     *
     * @param token
     * @param deliveryNo 报检单号等同于inspNo
     * @return
     */
    @RequestMapping(value = "/getInspectionInfo", method = {RequestMethod.GET})
    @ResponseBody
    public Callback getInspectionInfo(String token, String deliveryNo) {
        if (StringUtils.isEmpty(token)) {
            return returnError("token为空");
        }
        Integer userId = UserAuthTokenHelper.getAppUserId(token);
        if (userId == null) {
            return returnError("token校验失败");
        }
        SysUser sysUser = sysUserService.findByUserId(userId);
        if (sysUser == null) {
            return returnError("token校验失败");
        }
        if (StringUtils.isEmpty(deliveryNo)) {
            return returnError("deliveryNo为空");
        }
        Inspection inspection = inspectionService.findByInspNo(deliveryNo);
        if (inspection == null) {
            return returnError("找不到您要获取的报检单");
        }
        PoundLog poundLog = poundLogService.findByPoundLogNo(inspection.getPlNo());
        if (poundLog == null) {
            return returnError("找不到您要获取的过磅信息");
        }

        JSONObject data = new JSONObject();
        data.put("BillNo", poundLog.getPoundLogNo());
        data.put("Remark", poundLog.getRemark());
        data.put("DeliveryNo", deliveryNo);
        data.put("WeightValue", inspection.getInspNetWeight());
        return returnSuccess(data);
    }

    /**
     * 获取所有组织
     */
    @RequestMapping(value = "/getAllOrganization", method = {RequestMethod.GET})
    @ResponseBody
    public Callback getAllOrganization(String token) {
        if (StringUtils.isEmpty(token)) {
            return returnError("token为空");
        }
        Integer poundId = UserAuthTokenHelper.getAppUserId(token);
        if (poundId == null) {
            return returnError("token校验失败");
        }

        List<Organization> organizations = organizationService.findAllCache();
        return returnSuccess(organizations);
    }

    /**
     * 获取所有物料
     */
    @RequestMapping(value = "/getAllGoods", method = {RequestMethod.GET})
    @ResponseBody
    public Callback getAllGoods(String token) {
        if (StringUtils.isEmpty(token)) {
            return returnError("token为空");
        }
        Integer poundId = UserAuthTokenHelper.getAppUserId(token);
        if (poundId == null) {
            return returnError("token校验失败");
        }

        List<Goods> goodsList = goodsService.findAllCache();
        return returnSuccess(goodsList);
    }

    /**
     * 获取所有供应商
     */
    @RequestMapping(value = "/getAllCompany", method = {RequestMethod.GET})
    @ResponseBody
    public Callback getAllCompany(String token) {
        if (StringUtils.isEmpty(token)) {
            return returnError("token为空");
        }
        Integer poundId = UserAuthTokenHelper.getAppUserId(token);
        if (poundId == null) {
            return returnError("token校验失败");
        }

        List<Company> companyList = companyService.findAllCache();
        return returnSuccess(companyList);
    }
}