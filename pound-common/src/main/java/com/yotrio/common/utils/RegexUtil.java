/******************************************************************************
 * Copyright (c) 2009, m-time. All Rights Reserved.                           *
 ******************************************************************************/
package com.yotrio.common.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: zhanghailang Date: 2009-11-12 Time: 12:22:19 Desc: 正则表达式验证
 */
public final class RegexUtil {
	private static final String EMAIL_REGEX = "^([a-z0-9]+[-|\\.]?)+[a-z0-9]@([a-z0-9]+(-[a-z0-9]+)?\\.)+[a-z]{2,}$";// Email正则表达式
	public static final String MOBILE_REGEX = "^1(3|5|8){1}[0-9]{1}[0-9]{8}$";
	/**
	 * 验证数据的有效性
	 * @param valContent 验证的内容
	 * @param regex 验证表达式
	 * @return boolean
	 */
	public static boolean validate(String valContent, String regex) {
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(valContent);
		return matcher.matches();
	}
	/**
	 * 验证Email的有效性
	 * @param email 验证的内容
	 * @return boolean 验证是否通过
	 */
	public static boolean valEmail(String email) {
		return validate(email, EMAIL_REGEX);
	}
	/**
	 * 验证手机号码的有效性
	 * @param phone 验证的内容
	 * @return boolean 验证是否通过
	 */
	public static boolean valPhone(String phone) {
		return validate(phone, MOBILE_REGEX);
	}
}
