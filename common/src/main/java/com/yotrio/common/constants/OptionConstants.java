/*
 * Copyright (c) 2006, Marry5 Technology Center, M-Time Corporation. All Rights
 * Reserved.
 *
 * developer 			time 		version 		description
 * ------------ 		----------- ----------		----------------
 * Watson 				2006-8-25 	3.3
M03 新形象照规则修改
 * Watson				2006-9-19	3.4
M06	新增审核状态,添加审核通过结果
 */
package com.yotrio.common.constants;
/**
 * Marry5选项内容常量接口.
 * <p/>
 * <p/>
 * 本接口存储所有选择项的类键值
 * </p>
 * <p/>
 * Created by 2006-6-16
 * </p>
 * @author <a href="mailto:Watson@tom.com">Watson</a>
 */
public interface OptionConstants {
	/***************************************************************************
	 * 选项类型 *
	 **************************************************************************/
	/**
	 * 城市
	 */
	public static final String CITY = "CITY";
	/**
	 * 省份
	 */
	public static final String PROVINCE = "PROVINCE";
	/**
	 * 会员年龄
	 */
	public static final String AGE = "1";
	/**
	 * 身高
	 */
	public static final String STATURE = "2";
	/**
	 * 体重
	 */
	public static final String WEIGHT = "3";
	/**
	 * 体型
	 */
	public static final String BODY_FIGURE = "4";
	/**
	 * 吸烟习惯
	 */
	public static final String SMOKE_HABIT = "5";
	/**
	 * 饮酒习惯
	 */
	public static final String DRINK_HABIT = "6";
	/**
	 * 情感生活
	 * @deprecated
	 */
	public static final String MARRAGE_STATUS = "7";
	public static final String MARRIAGE_STATUS = "7";
	/**
	 * 是否有小孩
	 */
	public static final String CHILD_STATUS = "8";
	/**
	 * 是否要小孩
	 */
	public static final String CHILD_DESIRED = "9";
	/**
	 * 教育程度
	 */
	public static final String EDUCATION = "10";
	/**
	 * 行业
	 */
	public static final String WORKING_INDUSTRY = "11";
	/**
	 * 收入
	 */
	public static final String INCOMING = "12";
	/**
	 * 住房条件
	 */
	public static final String HOUSING_STATUS = "13";
	/**
	 * 民族
	 */
	public static final String NATION = "14";
	/**
	 * 语言
	 */
	public static final String LANGUAGE = "15";
	/**
	 * 信仰
	 */
	public static final String FAITH = "16";
	/**
	 * 会员状态
	 */
	public static final String MEMBER_STATUS = "17";
	/**
	 * 审核状态
	 */
	public static final String APPROVAL_STATUS = "18";
	/**
	 * 会员性别
	 */
	public static final String SEX = "19";
	/**
	 * 是否属性
	 */
	public static final String YES_NO = "20";
	/**
	 * 活动规模
	 */
	public static final String PARTY_SCALE = "21";
	/**
	 * 活动状态
	 */
	public static final String PARTY_STATUS = "22";
	/**
	 * 约会状态
	 */
	public static final String APPOINTMENT_STATUS = "23";
	/**
	 * 约会付费方式
	 */
	public static final String APPOINTMENT_PAYMENT = "24";
	/**
	 * 活动类型
	 */
	public static final String PARTY_TYPE = "25";
	/**
	 * 活动支付方式
	 */
	public static final String PARTY_PAYMENT_MODE = "26";
	/**
	 * 会员级别
	 */
	public static final String MEMBER_LEVEL = "27";
	/**
	 * 会员身份
	 */
	public static final String MEMBER_CHARACTER = "28";
	/**
	 * 会员来源
	 */
	public static final String MEMBER_SOURCE = "29";
	/**
	 * 验证模式
	 */
	public static final String APPROVING_MODE = "30";
	/**
	 * 活动参加状态
	 */
	public static final String PARTY_PRESENTED = "31";
	/**
	 * 证件类型
	 */
	public static final String CERTIFICATE_TYPE = "32";
	/**
	 * 职业类型
	 */
	public static final String OCCUPATION = "33";
	/**
	 * 血型
	 */
	public static final String BLOOD_TYPE = "34";
	/**
	 * 图片查看方式
	 */
	public static final String Love_Type = "35";
	/* M06 添加审核通过结果 BEGIN */
	/**
	 * 通过结果
	 */
	public static final String APPROVED_RESULT = "45";
	/* M06 添加审核通过结果 END */
	/**
	 * 文章类别.
	 */
	public static final String ARTICLE_TYPE = "46";
	/**
	 * 文章推荐级别.
	 */
	public static final String ARTICLE_RECOMMEND_LEVEL = "47";
	/**
	 * 幸福关系.
	 */
	public static final String HAPPINESS_RELATION = "48";
	/**
	 * 部门.
	 */
	public static final String DEPARTMENT = "49";
	/**
	 * 生肖
	 */
	public static final String ANIMAL_SIGN = "50";
	/**
	 * 脸型
	 */
	public static final String FACE = "51";
	/**
	 * 发型（男）。
	 */
	public static final String HAIR_STYLE_MALE = "52";
	/**
	 * 外貌自评（男）。
	 */
	public static final String APPEARANCE_MALE = "53";
	/**
	 * 毕业学校
	 */
	public static final String COLLEGE = "54";
	/**
	 * 所学专业
	 */
	public static final String KNOWLEDGE = "55";
	/**
	 * 工作状况
	 */
	public static final String WORK_STATUS = "56";
	/**
	 * 事业前景
	 */
	public static final String CAREER = "57";
	/**
	 * 家中排行
	 */
	public static final String SIBLING = "58";
	/**
	 * 是否愿意与父母同住
	 */
	public static final String INMATE_PARENT = "59";
	/**
	 * 满意部位
	 */
	public static final String SATISFIED_PART = "60";
	/**
	 * 作息习惯
	 */
	public static final String REST_HABIT = "61";
	/**
	 * 锻炼频次
	 */
	public static final String EXERCISE_TIMES = "62";
	/**
	 * 擅长生活技能
	 */
	public static final String LIVING_SKILLS = "63";
	/**
	 * 休闲去处
	 */
	public static final String HEALTH_RESORT = "64";
	/**
	 * 饲养宠物
	 */
	public static final String PET = "65";
	/**
	 * 所属群落
	 */
	public static final String PERSONALITY_GROUP = "66";
	/**
	 * 个性自评（男）。
	 */
	public static final String INDIVIDUALITY_MALE = "67";
	/**
	 * 兴趣爱好
	 */
	public static final String LIKINGS = "76";
	/**
	 * 明星所在区域，类型(大陆明星，港台明星..)
	 */
	public static final String STAR_AREA = "77";
	/**
	 * 多选项保留代号（77 到 99）： 数据库需要对 MEMBER_ITEM_DATA 作分区，由于作分区后面再扩展会比较麻烦，所以先预留着这批代号.
	 */
	/**
	 * 多选项保留代号 1 .
	 */
	public static final String MULTI_RESERVED_2 = "78";
	public static final String MULTI_RESERVED_3 = "79";
	public static final String MULTI_RESERVED_4 = "80";
	public static final String MULTI_RESERVED_5 = "81";
	public static final String MULTI_RESERVED_6 = "82";
	public static final String MULTI_RESERVED_7 = "83";
	public static final String MULTI_RESERVED_8 = "84";
	public static final String MULTI_RESERVED_9 = "85";
	public static final String MULTI_RESERVED_10 = "86";
	public static final String MULTI_RESERVED_11 = "87";
	public static final String MULTI_RESERVED_12 = "88";
	public static final String MULTI_RESERVED_13 = "89";
	public static final String MULTI_RESERVED_14 = "90";
	public static final String MULTI_RESERVED_15 = "91";
	public static final String MULTI_RESERVED_16 = "92";
	public static final String MULTI_RESERVED_17 = "93";
	public static final String MULTI_RESERVED_18 = "94";
	public static final String MULTI_RESERVED_19 = "95";
	public static final String MULTI_RESERVED_20 = "96";
	public static final String MULTI_RESERVED_21 = "97";
	public static final String MULTI_RESERVED_22 = "98";
	public static final String MULTI_RESERVED_23 = "99";
	/**
	 * 喜爱的运动
	 */
	public static final String FAVORITE_SPORT = "69";
	/**
	 * 喜爱的食物
	 */
	public static final String FAVORITE_FOOD = "70";
	/**
	 * 喜爱的音乐
	 */
	public static final String FAVORITE_MUSIC = "71";
	/**
	 * 喜爱看的书
	 */
	public static final String FAVORITE_BOOK = "72";
	/**
	 * 外貌自评（女）。
	 */
	public static final String APPEARANCE_FEMALE = "73";
	/**
	 * 个性自评（女）。
	 */
	public static final String INDIVIDUALITY_FEMALE = "74";
	/**
	 * 发型（女）。
	 */
	public static final String HAIR_STYLE_FEMALE = "75";
	/**
	 * 星座
	 */
	public static final String CONSTELLATION = "101";
	/**
	 * 活动合作商
	 */
	public static final String COLLABORATE = "102";
	/**
	 * 会员资料：是否有车.
	 */
	public static final String CAR_STATUS = "103";
	/**
	 * 征友状态.
	 */
	public static final String SEEKING_STATUS = "104";
	/**
	 * 会员来信类别.
	 */
	public static final String MEMBER_LETTER_TYPE = "106";
	/**
	 * 今日心情
	 */
	public static final String TODAY_MOOD = "107";
	/**
	 * 日记公开模式
	 */
	public static final String DIARY_VIEW_MODEL = "108";
	/**
	 * 用户空间等级
	 */
	public static final String USER_SPACE_GRADE = "109";
	/**
	 * 用户勋章类别
	 */
	public static final String USER_HONOR_TYPE = "110";
	/**
	 * 明星空间等级
	 */
	public static final String STAR_SPACE_GRADE = "111";
	/**
	 * 礼物类别
	 */
	public static final String GIFT_TYPE = "10727";
	/**
	 * 礼物支付类别
	 */
	public static final String GIFT_PAY_TYPE = "10728";
	/**
	 * 属性标签
	 */
	public static final String TAG = "10729";
	/***************************************************************************
	 * 性别选项允许值
	 **************************************************************************/
	/**
	 * 女性
	 */
	public static final String SEX_FEMALE = "0";
	public static final int FEMALE = 0;
	/**
	 * 男性
	 */
	public static final String SEX_MALE = "1";
	public static final int MALE = 1;
	/***************************************************************************
	 * 会员状态允许值
	 **************************************************************************/
	/**
	 * 已冻结
	 */
	public static final String MEMBER_STATUS_FREEZED = "0";
	/**
	 * 正常
	 */
	public static final String MEMBER_STATUS_NORMAL = "1";
	/***************************************************************************
	 * 审核状态允许值
	 **************************************************************************/
	/**
	 * 未审核
	 * @deprecated
	 */
	public static final String APPROVAL_STATUS_UNAPPROVED = "0";
	/**
	 * 已审核通过
	 * @deprecated
	 */
	public static final String APPROVAL_STATUS_APPROVED = "1";
	/**
	 * 审核不通过
	 * @deprecated
	 */
	public static final String APPROVAL_STATUS_INVALID = "-1";
	/**
	 * 会员审核通过的代号.
	 */
	public final static int APPROVAL_PASSED = 1;
	/**
	 * 会员审核不通过的代号.
	 */
	public final static int APPROVAL_FAILED = -1;
	/**
	 * 会员未审核的代号.
	 */
	public final static int APPROVAL_UNAPPROVED = 0;
	/***************************************************************************
	 * 是否选项允许值
	 **************************************************************************/
	/**
	 * 否
	 */
	public static final String NO = "0";
	/**
	 * 是
	 */
	public static final String YES = "1";
	/***************************************************************************
	 * 审核选项允许值
	 **************************************************************************/
	/**
	 * 审核方式：自动审核
	 */
	public static final String APPROVAL_MODE_AUTO = "0";
	/**
	 * 审核方式：手工审核
	 */
	public static final String APPROVAL_MODE_MANUAL = "1";
	/***************************************************************************
	 * 会员级别选项允许值
	 **************************************************************************/
	/**
	 * 会员级别：普通会员
	 */
	public static final String MEMBER_LEVEL_NORNAL = "0";
	/**
	 * 会员级别是：VIP会员
	 */
	public static final String MEMBER_LEVEL_VIP = "1";
	/**
	 * 会员身份：普通身份（嫁我网）
	 */
	public static final String MEMBER_CHARACTER_NORMAL = "0";
	/***************************************************************************
	 * 活动状态选项允许值
	 **************************************************************************/
	/**
	 * 活动未举行
	 */
	public static final String PARTY_STATUS_NOT_START = "0";
	/**
	 * 活动已举行
	 */
	public static final String PARTY_STATUS_FINISHED = "1";
	/**
	 * 活动已删除
	 */
	public static final String PARTY_STATUS_DELETED = "2";
	/***************************************************************************
	 * 约会状态选项允许值
	 **************************************************************************/
	/**
	 * 约会未确认
	 */
	public static final String APPOINTMENT_STATUS_UNKNOW = "0";
	/**
	 * 约会成功
	 */
	public static final String APPOINTMENT_STATUS_SUCCESS = "1";
	/**
	 * 约会被拒绝
	 */
	public static final String APPOINTMENT_STATUS_REJECTED = "2";
	/***************************************************************************
	 * 约会付费方案选项允许值
	 **************************************************************************/
	/**
	 * 替TA付费
	 */
	public static final String APPOINTMENT_PAYMENT_TAKE_CHARGE_FOR_PARTNER = "0";
	/**
	 * AA制
	 */
	public static final String APPOINTMENT_PAYMENT_AA = "1";
	/**
	 * 替你付款（用于前台显现）
	 */
	public static final String APPOINTMENT_PAYMENT_TAKE_CHARGE_BY_PARTNER = "2";
	/**
	 * ************************************************************************ 默认的空选项显示值 ************************************************************************
	 */
	public static final String DEFAULT_EMPTY_OPTION_TITLE = "- 不限 -";
	// for translate tag only
	public static final String DEFAULT_EMPTY_TITLE = "不限";
	// public static final String UNDEFINED = "未透露";
	public static final String UNDEFINED = "保密";
	/**
	 * “不限”代号.
	 */
	public static final String DEFAULT_EMPTY_OPTION_VALUE = "-2";
	/***************************************************************************
	 * 活动支付手段
	 **************************************************************************/
	/**
	 * 现场支付
	 */
	public static final String PARTY_PAYMENT_MODE_BY_CASH = "0";
	/**
	 * 网上支付
	 */
	public static final String PARTY_PAYMENT_MODE_BY_WEB = "1";
	/**
	 * 无线服务供应商
	 */
	public static final String SP_CMCC = "CMCC";
	public static final String SP_UNICOM = "UNICOM";
	/***************************************************************************
	 * 图片查看方式
	 **************************************************************************/
	/* M03 新形象照公开代号修改 BEGIN */
	// /**
	// * 完全公开
	// */
	// public static final String Love_All = "0";
	//
	// /**
	// * 需要爱情密码
	// */
	// public static final String LOVE_PASSWORD = "1";
	//
	// /**
	// * 要有会员照
	// */
	// public static final String Love_Photo = "2";
	/* M03 新形象照公开代号修改 END */
	/**
	 * ************************************************************************ 会员等级 ************************************************************************
	 */
	// 优秀
	public static final int QUALITY_PERFECT = 15;
	// 可用
	public static final int QUALITY_NORMAL = 10;
	// 考虑
	public static final int QUALITY_CONSIDER = 5;
	// 有照片未评分
	public static final int HAS_PHOTO_QUALITY_DEFAULT = 1;
	// 无照片未评分
	public static final int NO_PHOTO_QUALITY_DEFAULT = 0;
}
