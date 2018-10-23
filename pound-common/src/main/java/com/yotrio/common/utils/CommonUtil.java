package com.yotrio.common.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.EmailValidator;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 封装项目基础工具方法.
 * @author Watson
 */
public class CommonUtil {
	private static Logger logger = Logger.getLogger(CommonUtil.class);
	public static String MIX_CHARS = "0123456789abcdefghijklmnopqrstuvwxyz";
	/**
	 * 判断是否是数字字符串.
	 * @param str
	 * @return true : 是; false : 不是.
	 */
	public static boolean isMobile(String str) {
		if (StringUtils.isBlank(str) || str.length() != 11) {
			return false;
		}
		String reg = "1(2|3|4|5|6|7|8|9)[0-9]{9}";
		return str.matches(reg);
	}
	/**
	 * 固定电话号码验证
	 * @param nums
	 * @return
	 */
	public static boolean isTel(String nums) {
		if (StringUtils.isBlank(nums) || (nums.length() < 3 && nums.length() > 8)) {
			return false;
		}
		// String pattern="/(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/";
		String pattern = "^((0\\d{2,3})-)(\\d{7,8})(-(\\d{3,}))?$";
		return nums.matches(pattern);
	}
	/**
	 * 判断是否是数字字符串.
	 * @param str
	 * @return true : 是; false : 不是.
	 */
	public static boolean isDigit(String str) {
		if (StringUtils.isBlank(str)) {
			return false;
		}
		return str.trim().matches("^[0123456789]+$");
	}
	/**
	 * 随时生成 n 位数字和字符的组合串.
	 * @param digit 位数
	 * @return 组合串
	 */
	public static String generateRandomMix(int digit) {
		String mix = "";
		int L = MIX_CHARS.length();
		for (int i = 0; i < digit; i++) {
			final int random = generateRandomInt(0, L - 1);
			mix += MIX_CHARS.charAt(random);
		}
		return mix;
	}
	/**
	 * 随机生成 n 位数字，位数在minDigit 和 maxDigit中之间随机产生 例子： generateRandomNumber(4,6)，那么就可能生成 4432, 45678, 421345。
	 * @param minDigit 最小位数
	 * @param maxDigit 最大位数
	 * @return 生成数字
	 */
	public static String generateRamdonNumber(int minDigit, int maxDigit) {
		return randNumber(getCharCount(minDigit, maxDigit));
	}
	/**
	 * 生成字符串 位数在minDigit 和 minDigit中之间随机产生
	 * @param minDigit
	 * @param maxDigit
	 * @return 生成字符串
	 */
	public static String generateRamdonAlpha(int minDigit, int maxDigit) {
		return randAlpha(getCharCount(minDigit, maxDigit));
	}
	/**
	 * 生成随机数字或字母串，由于数字的0，1，2易和字母的o，l,z混淆，使人眼难以识别，因此不生成数字和字母的混合串。
	 * @param minDigit
	 * @param maxDigit
	 * @return 生成随机数字或字母串
	 * @see #generateRandomMix(int) 生成数字和字母混合串
	 */
	public static String generateRandomString(int minDigit, int maxDigit) {
		if (generateRandomBoolean()) {
			return generateRamdonNumber(minDigit, maxDigit);
		} else {
			return generateRamdonAlpha(minDigit, maxDigit);
		}
	}
	/**
	 * 随机生成 n 位数字组合.
	 * @param digit 位数
	 * @return 随机数字组合.
	 */
	public static String randNumber(int digit) {
		StringBuffer randomString = new StringBuffer("");
		for (int i = 0; i < digit; i++) {
			String _char = String.valueOf(generateRandomInt(0, 10));
			randomString.append(_char);
		}
		return randomString.toString();
	}
	/**
	 * 随机生成 n 位字母组合.
	 * @param digit 位数
	 * @return
	 */
	public static String randAlpha(int digit) {
		StringBuffer randomString = new StringBuffer("");
		// 生成随机字母串
		for (int i = 0; i < digit; i++) {
			char c = (char) (generateRandomInt(0, 26) + 'A');
			randomString.append(c);
		}
		return randomString.toString();
	}
	/**
	 * 随机生成 n 位数字组合.(为了兼容保留此方法,以后建议使用randNumber方法)
	 * @param digit 位数
	 * @return 随机数字组合.
	 */
	public static String generateRandomString(int digit) {
		return randNumber(digit);
	}
	/**
	 * 生成一个指定范围内的随机整数。
	 * <p>
	 * 随机生成的范围包含指定的“最小值”和“最大值”。
	 * </p>
	 * @param min 最小范围数
	 * @param max 最大范围数
	 * @return 整数
	 */
	public static Integer generateRandomInteger(int min, int max) {
		Long l = Math.round(Math.random() * (max - min) + min);
		return Integer.valueOf(l.intValue());
	}
	/**
	 * 生成一个指定范围内的随机整数。
	 * <p>
	 * 随机生成的范围包含指定的“最小值”和“最大值”。
	 * </p>
	 * @param min 最小范围数
	 * @param max 最大范围数
	 * @return 整数
	 */
	public static int generateRandomInt(int min, int max) {
		Random r = new Random();
		return min + r.nextInt(max - min);
	}
	/**
	 * 获取随机的boolean值
	 * @return boolean
	 */
	public static boolean generateRandomBoolean() {
		Random r = new Random();
		return r.nextBoolean();
	}
	/**
	 * 对给定的字符串进行MD5加密返回一个不可逆的加密字符串.
	 * @param source String
	 * @return String 32 位的加密字符串
	 */
	public static String encyptByMD5(String source) {
		String encyptStr = "";
		if (null == source || "".equals(source)) {
			return encyptStr;
		}
		try {
			MessageDigest mgd = MessageDigest.getInstance("MD5");
			mgd.update(source.getBytes());
			BigInteger bgi = new BigInteger(1, mgd.digest());
			encyptStr = bgi.toString(16).toLowerCase();
		} catch (NoSuchAlgorithmException ex) {
			// ingore this error
		}
		return encyptStr;
	}
	/**
	 * 生成长度为32位的UUID，字母全部大写。
	 * @return 32位字符串
	 */
	public static String uuid() {
		UUID uuid = UUID.randomUUID();
		String uuid32 = uuid.toString().replaceAll("-", "");
		return uuid32.toUpperCase();
	}
	/**
	 * 取得一个数组里面最小的数值.
	 * 
	 * <pre>
     * 比如：
     * 传入值 values = {3,4,6,5,9,10,2}
     * 最终返回的值为 2 。
     * </pre>
	 * @param values 一个整数的 Set
	 * @return Set 里头最小的数值
	 */
	public static Integer evalMinValue(Set<Integer> values) {
		List<Integer> tempList = new ArrayList<Integer>(values);
		Collections.sort(tempList);
		return tempList.get(0);
	}
	/**
	 * 取得一个数组里面最大的数值.
	 * 
	 * <pre>
     * 比如：
     * 传入值 values = {3,4,6,5,9,10,2}
     * 最终返回的值为 10 。
     * </pre>
	 * @param values 一个整数的 Set
	 * @return Set 里头最大的数值
	 */
	public static Integer evalMaxValue(Set<Integer> values) {
		List<Integer> tempList = new ArrayList<Integer>(values);
		Collections.sort(tempList);
		return tempList.get(tempList.size() - 1);
	}
	/**
	 * HTML 格式解码(UTF-8).
	 * @param value 要解码支付
	 * @return 解码后的值
	 */
	public static String urlDecode(String value) {
		try {
			return URLDecoder.decode(value, "UTF-8");
		} catch (Exception e) {
			logger.error("<urlDecode> value=" + value, e);
			return null;
		}
	}
	/**
	 * HTML 格式编码(UTF-8).
	 * @param value 编码值
	 * @return 编码后的值
	 */
	public static String urlEncode(String value) {
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (Exception e) {
			logger.error("<urlEecode> value=" + value, e);
			return null;
		}
	}
	/**
	 * Return the root cause of this exception
	 * @param name Properties的文件名
	 * @return Properties对象
	 */
	public static Properties loadProperties(String name) {
		// Set up to load the property resource for this locale key, if we can
		InputStream is = null;
		Properties tmpprops = new Properties();
		// Load the specified property resource
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("  Loading resource '" + name + "'");
			}
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			is = classLoader.getResourceAsStream(name);
			if (is != null) {
				tmpprops.load(is);
				is.close();
			}
			if (logger.isDebugEnabled()) {
				logger.debug("  Loading resource completed");
			}
		} catch (Throwable t) {
			logger.error("loadLocale()", t);
			if (is != null) {
				try {
					is.close();
				} catch (Throwable u) {
					;
				}
			}
		}
		return tmpprops;
	}
	public static BigDecimal convertBytesToMeager(long bytes) {
		float f = (float) bytes / 1024 / 1024;
		BigDecimal bd = new BigDecimal(f).setScale(2, BigDecimal.ROUND_HALF_UP);
		return bd;
	}
	private static int getCharCount(int f, int t) {
		int count = 0;
		if (f < 0) {
			f = -f;
		}
		if (t < 0) {
			t = -t;
		}
		if (f >= t) {
			count = f;
		}
		if (f == 0) {
			count = t;
		} else if (t == 0) {
			count = f;
		} else {
			count = generateRandomInt(f, t + 1);
		}
		if (count == 0) {
			count = 6;
		}
		return count;
	}
	/**
	 * 检查字符串是否是一个有效的邮箱地址.
	 * @param email 字符串
	 * @return 是的话，返回 true，否则返回 false
	 */
	public static boolean isEmailWellFormat(String email) {
		EmailValidator validator = EmailValidator.getInstance();
		return validator.isValid(email);
	}
	/**
	 * 将 html 代码的 tag 删除掉，只留下 text ，tag 之间用空格代替。
	 * @param htmlString html 代码
	 * @return 文字
	 */
	public static String removeHtmlTag(String htmlString) {
		// Remove HTML tag from java String
		if (StringUtils.isBlank(htmlString)) {
			return "";
		}
		String noHTMLString = htmlString.replaceAll("\\<.*?\\>", " ");
		// 附加功能，有必要的时候再打开用
		// noHTMLString = noHTMLString.replaceAll("\r", "<br/>");
		// noHTMLString = noHTMLString.replaceAll("\n", " ");
		// noHTMLString = noHTMLString.replaceAll("\'", "&#39;");
		// noHTMLString = noHTMLString.replaceAll("\"", "&quot;");
		return noHTMLString.trim();
	}
	/**
	 * 格式化2位小数的金额
	 * @param value
	 * @return
	 */
	public static String formatMoney(Double value) {
		if (value == null) {
			return "";
		}
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(value);
	}
	public static String multiplyToStr(double mulNum, int value) {
		return String.valueOf(multiply(mulNum, value));
	}
	public static int multiply(double mulNum, int value) {
		return new BigDecimal(String.valueOf(mulNum)).multiply(new BigDecimal(value)).intValue();
	}
	/**
	 * 转换数字为日期格式(20120102 --> 2012-01-02)
	 * @param date
	 * @return
	 */
	public static String intConverDate(Integer date) {
		if (date == null) {
			return "";
		}
		StringBuffer autoBuyStartDate = new StringBuffer();
		autoBuyStartDate.append(date);
		if (autoBuyStartDate.length() == 8) {
			autoBuyStartDate.insert(autoBuyStartDate.length() - 2, "-");
			autoBuyStartDate.insert(autoBuyStartDate.length() - 5, "-");
		}
		return autoBuyStartDate.toString();
	}
	/**
	 * 转换数字为日期格式(100205 --> 10:02:05 )
	 * @param time
	 * @return
	 */
	public static String intConverTime(Integer time) {
		if (time == null) {
			return "";
		}
		StringBuffer autoBuyStartTime = new StringBuffer();
		autoBuyStartTime.append(time);
		while (autoBuyStartTime.length() < 6) {
			autoBuyStartTime.insert(0, "0");
		}
		if (autoBuyStartTime.length() == 6) {
			autoBuyStartTime.insert(autoBuyStartTime.length() - 2, ":");
			autoBuyStartTime.insert(autoBuyStartTime.length() - 5, ":");
		}
		return autoBuyStartTime.toString();
	}
	public static void main(String[] args) {
		System.out.println(generateRandomInt(4, 7));
	}
}
