package com.yotrio.common.utils;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lujun
 */
public final class NumberUtils {
	private final static String sqe = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	/**
	 * 判断一个数对象是否有效 null || <=0 都认为是无效的,常用来前端传递参数判断
	 * @param obj
	 * @return
	 */
	public final static boolean isValid(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof Integer) {
			Integer num = (Integer) obj;
			return num != null && num.intValue() > 0;
		} else if (obj instanceof Long) {
			Long num = (Long) obj;
			return num != null && num.intValue() > 0;
		}
		return false;
	}
	public final static boolean IntIsTrue(Integer val){
		return val!=null && val.intValue()==1;
	}
	/**
	 * 比较两个Integer型数字是否相等 val 不能为null
	 * @param val 不能为null
	 * @param val2 可以为null
	 * @return
	 */
	public final static boolean IntEquals(Integer val, Integer val2){
		return val!=null && val.equals(val2);
	}
	/**
	 * Boolean 型的字符串True || 1 转 Boolean
	 * @return 返回Boolean
	 */
	public final static boolean str2Boolean(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof String))
			return false;
		String str = obj.toString() + "";
		str = str.trim();
		if (str.equals("true") || str.equals("True") || str.equals("1")) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 字符串转整数型 默认是0
	 * @return
	 */
	public final static int str2Int(String str) {
		return str2Int(str, 0);
	}
	public final static int str2Int(String str, int defaultRef) {
		try {
			return Integer.parseInt(str);
		} catch (Exception ex) {
			return defaultRef;
		}
	}
	/**
	 * 字符串转长整数 默认是0
	 * @return
	 */
	public final static long str2Long(String str) {
		return str2Long(str, 0);
	}
	public final static long str2Long(String str, long defaultRef) {
		try {
			return Long.parseLong(str);
		} catch (Exception ex) {
			return defaultRef;
		}
	}
	public final static String formatDouble(Double num, int len) {
		if (num == null)
			return "0.00";
		if (len < 1)
			return String.valueOf(num);
		BigDecimal b = new BigDecimal(String.valueOf(num));
		return String.valueOf(b.setScale(len, BigDecimal.ROUND_HALF_UP).doubleValue());
	}

	/**
	 * 字符串转double 默认是0
	 * @return
	 */
	public final static double str2Double(String str) {
		return str2Double(str, 0);
	}
	public final static double str2Double(String str, double defaultRef) {
		try {
			return Double.parseDouble(str);
		} catch (Exception ex) {
			return defaultRef;
		}
	}
	/**
	 * 字符串转 金钱格式 默认是
	 * @return
	 */
	public final static double str2Money(String str) {
		return str2Money(str, 0.00);
	}
	public final static double str2Money(String str, double defaultRef) {
		try {
			return double2Money(Double.parseDouble(str));
		} catch (Exception ex) {
			return double2Money(defaultRef);
		}
	}
	/**
	 * 对double类型的金钱数格式化,两位小数 已经进行了四舍五入
	 * @param d
	 * @return
	 */
	public final static double double2Money(double d) {
		BigDecimal b = new BigDecimal(String.valueOf(d));
		return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 格式化double金额
	 * @param d 数据值
	 * @param len 长度
	 * @param type 类别 @seee BigDecimal.ROUND_HALF_UP,BigDecimal.ROUND_HALF_DOWN等
	 * @return
	 */
	public final static double double2Money(double d, int len, int type) {
		BigDecimal b = new BigDecimal(String.valueOf(d));
		return b.setScale(len, type).doubleValue();
	}
	public final static String formatMoney(double d) {
		try {
			return new DecimalFormat("0.00").format(d);
		} catch (Exception e) {
			return String.valueOf(d);
		}
	}
	/**
	 * 先截取小数后3位再进行银行家格式化
	 * @param d
	 * @return
	 */
	public final static String formatBy3Decimal(double d) {
		String number = String.valueOf(d);
        //科学计数法转换
        if(number.toLowerCase().contains("e")){
            number = new BigDecimal(number).toPlainString();
        }
		String[] nums = number.split("\\.");
		if (nums.length != 2) {
			return NumberUtils.formatMoney(d);
		}
		if (nums[1].length() <= 3) {
			return NumberUtils.formatMoney(d);
		}
		String deci = nums[1].substring(0, 3);
		return NumberUtils.formatMoney(Double.valueOf(nums[0] + "." + deci));
	}
	// 返回随机号码
	public static String randomNumberText(int source[], int quantity) {
		return randomNumberText(source, quantity, true, false);
	}
	public static String randomNumberText(int source[], int quantity, boolean repeat, boolean addZero) {
		int result[] = randomNumber(source, quantity, repeat);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			if (i != 0)
				sb.append(",");
			if (addZero)
				sb.append(zero(result[i]));
			else
				sb.append(result[i]);
		}
		return sb.toString();
	}
	public static String zero(int i) {
		if (i < 10)
			return "0" + i;
		return String.valueOf(i);
	}
	public static int[] randomNumber(int source[], int quantity, boolean repeat) {
		return randomNumber(source, quantity, repeat, true);
	}
	public static int[] randomNumber(int source[], int quantity, boolean repeat, boolean order) {
		if (repeat)
			return randomNumber(source, quantity);
		if (quantity > source.length)
			quantity = source.length - 1;
		java.util.ArrayList<Integer> sourceList = new java.util.ArrayList<Integer>();
		for (int i = 0; i < source.length; i++)
			sourceList.add(source[i]);
		int result[] = new int[quantity];
		for (int i = 0; i < quantity; i++)
			result[i] = sourceList.remove(Often.randomInt(sourceList.size()));
		return order ? Often.order(result) : result;
	}
	public static int[] randomNumber(int source[], int quantity) {
		if (quantity > source.length)
			quantity = source.length - 1;
		int result[] = new int[quantity];
		for (int i = 0; i < quantity; i++)
			result[i] = source[Often.randomInt(source.length)];
		return result;
	}
	/**
	 * 判断String是否是整数
	 * @param str
	 * @return
	 */
	public final static boolean isInt(String str) {
		if (str != null)
			return str.matches("^[0-9]+$");
		else
			return false;
	}
	/**
	 * 判断字符串是否为一个数字,包括是一个双精度
	 * @param str
	 * @return
	 */
	public final static boolean isDouble(String str) {
		if (str == null || str.length() == 0 || str.equals("."))
			return false;
		Pattern pattern = Pattern.compile("-?\\+?[0-9]*\\.?[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	/**
	 * 是否是数字
	 * @param str
	 * @return
	 */
	public final static boolean isNumber(String str) {
		return isInt(str) || isDouble(str);
	}
	/**
	 * 转到16进制写法
	 * @param num
	 * @return
	 */
	public final static String toHexString(int num) {
		return "0x" + Integer.toHexString(num);
	}
	/**
	 * 获取随机数
	 * @param len 多少位
	 * @return
	 */
	public final static String randrom(int len) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer("");
		while (len > 0) {
			sb.append(random.nextInt(10));
			len--;
		}
		return sb.toString();
	}
	public final static String randromStr(int len) {
		Random random = new Random();
		int strLen = sqe.length();
		StringBuffer sb = new StringBuffer("");
		while (len > 0) {
			int index = random.nextInt(strLen);
			sb.append(String.valueOf(sqe.charAt(index)));
			len--;
		}
		return sb.toString();
	}
	/**
	 * 32进制字符串转long型 大小写不敏感
	 * @param strId
	 * @return
	 */
	public final static long str32long(String strId) {
		String sq = "0123456789ABCDEFGHJKMNPQRSTUVWXY";
		strId = strId.toUpperCase();
		long id = 0;
		for (int i = 0; i < strId.length(); i++) {
			char w = strId.charAt(i);
			int m = strId.length() - 1 - i;
			if (m > 0)
				id = id + sq.indexOf(w) * ((long) Math.pow(sq.length(), m));
			else
				id = id + sq.indexOf(w);
		}
		return id;
	}
	/**
	 * 判断一个整型是否在一个数组中
	 * @param num
	 * @param array
	 * @return
	 */
	public final static boolean intInArray(Integer num, int[] array){
		if(num==null)return false;
		for(int n:array)if(num.intValue()==n)return true;
		return false;
	}
	/**
	 * 32进制long型转字符串
	 * @param strId
	 * @return
	 */
	public final static String long32str(long strId) {
		String sq = "0123456789ABCDEFGHJKMNPQRSTUVWXY";
		int len = sq.length();
		char[] digits = sq.toCharArray();
		char[] buf = new char[len];
		int charPos = len - 1;
		do {
			buf[--charPos] = digits[(int) (strId & (len - 1))];
			strId >>>= 5;
		} while (strId != 0);
		return new String(buf, charPos, (len - 1 - charPos));
	}
	/**
	 * 乘法
	 * @param a
	 * @param b
	 * @return
	 */
	public final static String multiply(double a, double b){
	    BigDecimal aa = new BigDecimal(String.valueOf(a));
	    BigDecimal bb = new BigDecimal(String.valueOf(b));
	    return formatMoney(aa.multiply(bb).doubleValue());
	}
	
	public static void main(String[] args) {
		double a = (1040.00- 150 )*(3 / 100.0);
		System.out.println(a);
		
		double d = a;
		d = d * 100 / 100.00;
		BigDecimal b = new BigDecimal(d);
		double result = b.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
		System.out.println(result);
		
		BigDecimal b1 = new BigDecimal(String.valueOf(a));
		double result1 = b1.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
		System.out.println(result1);
	}
}
