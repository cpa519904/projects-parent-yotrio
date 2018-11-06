package com.yotrio.common.constants;

/**
 * 接口url常量类
 * 模块名称：projects-parent com.yotrio.pound.constants
 * 功能说明：<br>
 * 开发人员：Wangyq
 * 创建时间： 2018-10-08 13:16
 * 系统版本：1.0.0
 **/

public class ApiUrlConstant {
    /**
     * 根据id获取地磅信息
     */
    public static final String GET_POUND_INFO = "/api/pound/info/";

    /**
     * 根据poundLogNo和poundId获取过磅详情
     */
    public static final String GET_POUND_LOG_DETAIL = "/api/getPoundLogDetail";
    /**
     * 根据poundLogNo和poundId获取报检单
     */
    public static final String GET_INSPECTION_LIST = "/api/inspection/list";

    /**
     * 上传过磅信息
     */
    public static final String SAVE_POUND_LOG = "/api/poundLog/save";

    /**
     * 获取U9token5
     * POST方式调用，头标注 Content-Type: application/json
     * {
     * "OrgCode":"301",
     * "OrgID":"301",
     * "loginUserCode":"001326601",
     * "password":"123456"
     * }
     */
    public static final String GET_U9_TOKEN = "/u9login/Do";

    /**
     * 查询发货单关联U9收货单信息
     * http://192.168.0.97:8280/GetReceiveInfos/do?DeliveryNo=发货单号
     * GET方式调用，收货单号为空则U9未生成收货单
     * 发货单号      收货单号           发货数量       数量单位   发货重量        重量单位    点收数量        供应商
     * deliveryNo    RcvDocNo           deliveryQty    countUnit  deliveryWeight  weightUnit  EyeballingQty   SupplierName
     * F201803125004  301-RCV-18030145  3464.000000000  支        1325.000000000  公斤        3464.000000000  浙江龙威灯饰有限公司
     */
    public static final String GET_RECEIVE_INFO = "/GetReceiveInfos/do?DeliveryNo=";

    /**
     * 过磅数据写入U9收货业务接口
     * http://192.168.0.97:8280/YQWeightingSVC/do
     * POST方式调用，头标注 Content-Type: application/json
     * 参数格式
     * {
     * "OrgCode":"组织编码，如301",
     * "UserCode":"U9用户编码，如001100995",
     * "BillNo":"过磅单号",
     * "WeightValue":"888",
     * "DeliveryNo":"报检单号",
     * "Remark":"备注信息",
     * "Token":"U9登录token，如f6dbacc8-8e54-4e33-8a39-5bb4ae649e7e",
     * }
     */
    public static final String WRITE_WEIGHT_TO_U9_RECEIVE_INFO = "/YQWeightingSVC/do";

    /**
     * 根据员工号或手机号获取钉钉userId
     * 请求说明
     * 请求方式: GET
     * 请求地址: http://192.168.0.97:8088/DingTalk/http/getUserId?user=1377113734&&type=mobile
     */
    public static final String GET_DING_TALK_USERID = "/DingTalk/http/getUserId";

    /**
     * 获取钉钉access_token
     */
    public static final String GET_DING_TALK_ACCESS_TOKEN = "/DingTalk/http/getAccess_token";

}