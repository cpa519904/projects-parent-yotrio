package com.yotrio.common.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class StringUtil {
	private static Logger logger = Logger.getLogger(StringUtil.class);
	/**
	 * Consider whether a string is null or empty
	 * @param aStr A string
	 * @return
	 */
	public static final boolean isEmpty(String aStr) {
		return aStr == null || aStr.trim().equals("") || aStr.trim().equals("null");
	}
	/**
	 * Consider whether a string has content
	 * @param aStr A string
	 * @return
	 */
	public static final boolean isNotEmpty(String aStr) {
		return !isEmpty(aStr);
	}
	/**
	 * 删除空格
	 * @param str
	 * @return
	 */
	public static String deleteWhitespace(String str) {
		if (isEmpty(str))
			return "";
		str = str.replaceAll("[^\u4E00-\u9FA5\u3000-\u303F\uFF00-\uFFEF\u0000-\u007F\u201c-\u201d]", "");
		return str;
	}
	/**
	 * 判断2个字符是否相等
	 * @param aStr aStr
	 * @param bStr bStr
	 * @return true
	 */
	public static boolean equal(String aStr, String bStr) {
		if(aStr!=null && bStr!=null && aStr.trim().length()==0 && bStr.trim().length()==0){
			return true;
		}
		if (isEmpty(aStr) || isEmpty(bStr))
			return false;
		return aStr.equals(bStr);
	}
	/**
	 * Convert a string's /t,/n,' ' into html support characters
	 * @param aStr
	 * @return
	 */
	public static final String convert2HTML(String aStr) {
		if (isNotEmpty(aStr)) {
			aStr = aStr.replace("\n", "<br/>");
			// aStr = aStr.replace("\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
			// aStr = aStr.replace(" ", "&nbsp;");
		}
		return aStr;
	}
	public static String left(String source, char padding, int len) {
		if (source.length() >= len) {
			return source;
		}
		while (source.length() < len) {
			source = padding + source;
		}
		return source;
	}
	/**
	 * Join strings into one
	 * @param strings Strins to join
	 * @return
	 */
	public static final String joinString(String... strings) {
		StringBuffer joinOne = new StringBuffer();
		for (int i = 0; i < strings.length; i++) {
			if (StringUtil.isNotEmpty(strings[i])) {
				joinOne.append(strings[i]);
			}
		}
		return joinOne.toString();
	}
	/**
	 * clean empty or null characters
	 * @param aStr
	 * @return
	 */
	public static final String clean(String aStr) {
		if (aStr == null || aStr.equals("null")) {
			return "";
		}
		return aStr.trim();
	}
	/**
	 * toScriptUnicode的默认方法
	 * @param strText 待转换的字符串
	 * @return 转换后的Unicode码字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String toScriptUnicode(String strText) throws UnsupportedEncodingException {
		return toScriptUnicode(strText, System.getProperty("file.encoding"));
	}
	/**
	 * 把字符串转换成Unicode码,并且可以由javascript的String.fromCharCode()解释
	 * @param strText 待转换的字符串
	 * @param code 转换前字符串的编码，如"GBK"
	 * @return 转换后的Unicode码字符串
	 */
	public static String toScriptUnicode(String strText, String code) throws UnsupportedEncodingException {
		char c;
		StringBuffer strRet = new StringBuffer();
		try {
			strText = new String(strText.getBytes(), code);
			for (int i = 0; i < strText.length(); i++) {
				c = strText.charAt(i);
				Integer charCode = new Integer(c);
				strRet.append(charCode.toString());
				if (i < strText.length() - 1) {
					strRet.append(",");
				}
			}
			return strRet.toString();
		} finally {
			strRet = null;
		}
	}
	/**
	 * 将一个字符串转换成一个数组。
	 * <p>
	 * 比如：IvlP --> String[]{"I","v","l","p"}
	 * </p>
	 * @param str
	 * @return 数组
	 */
	public static String[] convertToArray(String str) {
		int len = str.length();
		String[] result = new String[len];
		for (int i = 0; i < len; i++) {
			result[i] = str.substring(i, i + 1);
		}
		return result;
	}
	/**
	 * 将字符串数组转换成一个字符串，用分隔串分开。
	 * 
	 * <pre>例如：
	 * new String[]{"hello","pengpeng5",".com"}  --转换成-->   "hello,pengpeng5,.com"
	 * </pre>
	 * @param stringArray 字符串数组
	 * @param seperator 分隔符
	 * @return 如果数组为空，那么返回空字符串
	 */
	public static String convertStringArray2String(String[] stringArray, String seperator) {
		if (null == stringArray) {
			return "";
		}
		if (StringUtil.isEmpty(seperator)) {
			return String.valueOf(stringArray);
		}
		StringBuffer bookstr = new StringBuffer();
		final int COUNT = stringArray.length;
		for (int i = 0; i < COUNT; i++) {
			bookstr.append(stringArray[i]);
			if (i < COUNT - 1) {
				bookstr.append(seperator);
			}
		}
		return bookstr.toString();
	}
	/**
	 * 将字符串数组转换成一个字符串，用分隔串分开。
	 * 
	 * <pre>例如：
	 * new Integer[]{2,4,6}  --转换成-->   "2,4,6"
	 * </pre>
	 * @param integerArray 数值数组
	 * @param seperator 分隔符
	 * @return 如果数组为空，那么返回空字符串
	 */
	public static String convertIntegerArray2String(Integer[] integerArray, String seperator) {
		StringBuffer bookstr = new StringBuffer();
		final int COUNT = integerArray.length;
		for (int i = 0; i < COUNT; i++) {
			bookstr.append(integerArray[i]);
			if (i < COUNT - 1) {
				bookstr.append(seperator);
			}
		}
		return bookstr.toString();
	}
	/**
	 * <ol>
	 * <li>替换空格符号为空字符串</li>
	 * <li>替换硬回车符号为空字符串</li>
	 * <li>替换软回车符号为空字符串</li>
	 * </ol>
	 * @param description
	 * @return
	 */
	public static String replaceSpecialNotation(String description) {
		if (null == description || "".equals(description)) {
			return description;
		}
		String spacePattern = " "; // 空格符号
		String hardEnterPattern = "\r";// 硬回车符号
		String softEnterPattern = "\n";// 软回车符号
		String replaceStr = "";
		// 替换空格
		Pattern pattern = Pattern.compile(spacePattern);
		Matcher matcher = pattern.matcher(description);
		description = matcher.replaceAll(replaceStr);
		// 替换硬回车符号
		pattern = Pattern.compile(hardEnterPattern);
		matcher = pattern.matcher(description);
		description = matcher.replaceAll(replaceStr);
		// 替换软回车符号
		pattern = Pattern.compile(softEnterPattern);
		matcher = pattern.matcher(description);
		description = matcher.replaceAll(replaceStr);
		return description;
	}
	/**
	 * <ol>
	 * <li>替换硬回车符号为空字符串</li>
	 * <li>替换软回车符号为空字符串</li>
	 * </ol>
	 * @param description
	 * @return
	 */
	public static String replaceSpecialNotationNoBlank(String description) {
		if (null == description || "".equals(description)) {
			return description;
		}
		String hardEnterPattern = "\r";// 硬回车符号
		String softEnterPattern = "\n";// 软回车符号
		String replaceStr = "";
		// 替换硬回车符号
		Pattern pattern = Pattern.compile(hardEnterPattern);
		Matcher matcher = pattern.matcher(description);
		description = matcher.replaceAll(replaceStr);
		// 替换软回车符号
		pattern = Pattern.compile(softEnterPattern);
		matcher = pattern.matcher(description);
		description = matcher.replaceAll(replaceStr);
		return description;
	}
	/**
	 * 这个工具方法将字符串数组转换成数值数值，非数值的字符串将被忽略。
	 * @param strings 字符串数组
	 * @return 数值数组
	 */
	public static Integer[] convertStringArray2Integer(String[] strings) {
		if (strings == null) {
			return null;
		}
		List<Integer> integers = new ArrayList<Integer>();
		for (int i = 0; i < strings.length; i++) {
			try {
				Integer integer = Integer.parseInt(strings[i]);
				integers.add(integer);
			} catch (Exception e) {
				// 不需要处理这个异常
			}
		}
		Integer[] newArray = new Integer[integers.size()];
		for (int i = 0; i < integers.size(); i++) {
			newArray[i] = integers.get(i);
		}
		return newArray;
	}
	/**
	 * 将字符串转成数组，如果字符串为 null 或者为空字符串，那么返回 null.
	 * @param str 字符串
	 * @param seperator 分隔符
	 * @return
	 */
	public static String[] convertString2Array(String str, String seperator) {
		if (str == null || "".equals(str)) {
			return null;
		}
		return StringUtils.split(str, seperator);
	}
	/**
	 * 将字符串中的变量进行替换.
	 * <p>
	 * 变量格式： ${VAR_NAME}
	 * </p>
	 * <p>
	 * HashMap 存储的是变量名和相应的值。
	 * </p>
	 * <code>
	 * String original = &quot;${CONTEXT_URI_RESOURCE_CSS}/global/${THEME_CSS}&quot;;<br />
	 * HashMap&lt;String,String&gt; variables = new HashMap&lt;String,String&gt;();<br />
	 * variables.put(&quot;CONTEXT_URI_RESOURCE_CSS&quot;,&quot;http://res.binguo.com/css&quot;);<br />
	 * variables.put(&quot;THEME_CSS&quot;,&quot;theme.css&quot;);<br />
	 * original = StringUtil.replaceVariables(original,variables);<br />
	 * System.out.println(original);
	 * </code>
	 * <p>
	 * 输出：
	 * </p>
	 * <p>
	 * http://res.binguo.com/css/global/theme.css
	 * </p>
	 * @param original 字符串
	 * @param variables 变量对应表
	 * @return
	 */
	public static String replaceVariables(String original, HashMap<String, String> variables) {
		if (StringUtils.isEmpty(original)) {
			return original;
		}
		try {
			Set<?> key = variables.keySet();
			for (Object _k : key) {
				String k = (String) _k;
				String v = variables.get(k);
				original = original.replaceAll("\\$\\{" + k + "\\}", v);
			}
		} catch (Exception e) {
			logger.error("无法对字符串的变量进行替换", e);
			logger.error(original);
		}
		return original;
	}
	public static String toLowerCase(String str) {
		if (str.equals("") || str.length() <= 0) {
			return "";
		}
		return str.toLowerCase();
	}
	/**
	 * 截取指定字节数文本
	 * @param value
	 * @param requireBytes 字节数
	 * @return
	 */
	public static String getBytesLengthGBK(String value, int requireBytes) {
		String result;
		if (value == null)
			return "";
		if (value.length() < requireBytes / 2)
			return value;
		try {
			if (value.length() > requireBytes)
				result = value.substring(0, requireBytes);
			else
				result = value;
			byte[] bytes = result.getBytes("GBK");
			if (bytes.length <= requireBytes) {} else {
				byte[] newBytes = new byte[requireBytes];
				System.arraycopy(bytes, 0, newBytes, 0, requireBytes);
				result = new String(newBytes, "GBK");
				if (result.charAt(result.length() - 1) == (char) 65533) {
					result = result.substring(0, result.length() - 1);
				}
			}
		} catch (Exception ex) {
			return value;
		}
		return result;
	}
	/**
	 * 截取指定中文字数文本
	 * @param value
	 * @param requireLength 字数（中文）
	 * @return
	 */
	public static String cutTextGBK(String value, int requireLength) {
		return getBytesLengthGBK(value, requireLength * 2);
	}
	public static boolean isBlankOrEmpty(String str) {
		return StringUtils.isBlank(str) || StringUtils.isEmpty(str);
	}
	/**
	 * 得到文本的真正长度
	 * @param str 文本
	 * @return
	 * @throws Exception
	 */
	public static int getRealLength(String str) {
		int length = 0;
		if (str != null) {
			length = str.getBytes().length;
		}
		return length;
	}
	/**
	 * 小写字符串首字符.
	 * @param str
	 * @return
	 */
	public static String toLowercaseStrHead(String str) {
		if (str == null) {
			return null;
		}
		if (str.length() > 0) {
			return str.substring(0, 1).toLowerCase() + (str.length() > 1 ? str.substring(1) : "");
		} else {
			return "";
		}
	}
	/**
	 * 切割文本分行显示（超出部分不显示）
	 * @param text 文本
	 * @param rowSize 每行字数（按中文字）
	 * @param rowNum 行数
	 * @return
	 */
	public static String splitTextHtml(String text, int rowSize, int rowNum) {
		if (rowNum < 1) {
			rowNum = 1;
		}
		String workText = new String(text);
		StringBuffer textSB = new StringBuffer("");
		for (int i = 1; i <= rowNum && !isBlankOrEmpty(workText); i++) {
			String partText = cutTextGBK(workText, rowSize);
			if (!isBlankOrEmpty(partText)) {
				if (i > 1) {
					textSB.append("<br>");
				}
				textSB.append(partText);
				workText = workText.substring(partText.length());
			} else {
				break;
			}
		}
		return textSB.toString();
	}
	/**
	 * 删除字符串中某个元素(字符串的格式:'2,3,4',元素与元素之间用','逗号隔开)
	 * @param str
	 * @return
	 */
	public static String deleteStringItem(String str, String item) {
		str = str.replaceAll(item, "");
		str = str.replaceAll(",,", ",");
		if (str.startsWith(",")) {
			str = str.substring(1, str.length());
		}
		if (str.endsWith(",")) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}
	/**
	 * 字符串替换，全部替换
	 */
	public static String replaceAll(String origin, String from, String to) {
		if (origin == null || origin.length() == 0 || from == null || to == null || origin.indexOf(from) < 0)
			return origin;
		StringBuffer sb = new StringBuffer();
		int k = 0;
		int idx = 0;
		while (true) {
			idx = origin.indexOf(from, k);
			if (idx < 0)
				break;
			sb.append(origin.substring(k, idx));
			sb.append(to);
			k = idx + from.length();
		}
		sb.append(origin.substring(k, origin.length()));
		return sb.toString();
	}
	public static void main(String[] args) {
		String original = "${CONTEXT_URI_RESOURCE_CSS}/global/${THEME_CSS}";
		HashMap<String, String> variables = new HashMap<String, String>();
		variables.put("CONTEXT_URI_RESOURCE_CSS", "http://res.binguo.com/css");
		variables.put("THEME_CSS", "theme.css");
		original = StringUtil.replaceVariables(original, variables);
		System.out.println("替换后的值：\n" + original);
	}
	/**
	 * 判断字符串数组是否为空，主要判断数组元素是否为空(String[] str = {" "};)
	 */
	public static int countString(String[] param) {
		int i = 0;
		for (String val : param) {
			if (StringUtil.isNotEmpty(val)) {
				i++;
			}
		}
		return i;
	}
	/**
	 * 获得字符(包含中文)长度.
	 * @param str
	 * @return
	 */
	public static int getChineseLength(String str) {
		if (StringUtils.isBlank(str)) {
			return 0;
		}
		try {
			return str.getBytes("gbk").length;
		} catch (UnsupportedEncodingException e) {
			logger.error("<getChineseLength> ", e);
		}
		return 0;
	}
	/**
	 * 数字排序
	 * @param number
	 * @return
	 */
	public static String numberOrder(String number) {
		int n[] = new int[3];
		n[0] = Integer.parseInt(number.substring(0, 1));
		n[1] = Integer.parseInt(number.substring(1, 2));
		n[2] = Integer.parseInt(number.substring(2, 3));
		n = Often.order(n);
		StringBuffer sb = new StringBuffer();
		sb.append(n[0]);
		sb.append(n[1]);
		sb.append(n[2]);
		return sb.toString();
	}
}
