package com.yotrio.common.utils;

import com.yotrio.common.domain.Capital;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public final class OftenFormat {
	// 文本格式转换至网页格式
	public static String textToWebShow(String text) {
		text = textToWeb(text);
		if (text.indexOf(' ') > -1)
			text = text.replaceAll(" ", "&nbsp;");
		if (text.indexOf("\r\n") > -1)
			text = text.replaceAll("\r\n", "<br />");
		if (text.indexOf('\n') > -1)
			text = text.replaceAll("\n", "<br />");
		if (text.indexOf('\r') > -1)
			text = text.replaceAll("\r", "<br />");
		return text;
	}
	public static String textToWeb(String text) {
		if (text.indexOf('&') > -1)
			text = text.replaceAll("&", "&amp;");
		if (text.indexOf('<') > -1)
			text = text.replaceAll("<", "&lt;");
		if (text.indexOf('>') > -1)
			text = text.replaceAll(">", "&gt;");
		if (text.indexOf('\"') > -1)
			text = text.replaceAll("\"", "&quot;");
		return text;
	}
	// 网页格式转换至文本格式
	public static String webShowToText(String text) {
		text = webToText(text);
		if (text.indexOf("&nbsp;") > -1)
			text = text.replaceAll("&nbsp;", " ");
		if (text.indexOf("<br />") > -1)
			text = text.replaceAll("<br />", "\n");
		return text;
	}
	public static String webToText(String text) {
		while (text.indexOf("&amp;") > -1)
			text = text.replaceAll("&amp;", "&");
		if (text.indexOf("&lt;") > -1)
			text = text.replaceAll("&lt;", "<");
		if (text.indexOf("&gt;") > -1)
			text = text.replaceAll("&gt;", ">");
		if (text.indexOf("&quot;") > -1)
			text = text.replaceAll("&quot;", "\"");
		return text;
	}
	// js格式转换至文本格式
	public static String jsToText(String text) {
		if (text.indexOf('\\') > -1)
			text = text.replace('\\', '/');
		if (text.indexOf('\'') > -1)
			text = text.replace('\'', '`');
		if (text.indexOf('\"') > -1)
			text = text.replace('\"', '~');
		if (text.indexOf("\r\n") > -1)
			text = text.replaceAll("\r\n", "^");
		if (text.indexOf('\n') > -1)
			text = text.replace('\n', '^');
		if (text.indexOf('\r') > -1)
			text = text.replace('\r', '^');
		return text;
	}
	// 文本格式转换至js格式
	public static String textToJs(String text) {
		if (text.indexOf('`') > -1)
			text = text.replace('`', '\'');
		if (text.indexOf('~') > -1)
			text = text.replace('~', '\"');
		if (text.indexOf('^') > -1)
			text = text.replace('~', '\n');
		return text;
	}
	// 数字格式转换至文本格式
	public static String numberToText(double value) {
		return numberToText(value, 2);
	}
	public static String numberToText(double value, int mantissaLength) {
		DecimalFormat df = new DecimalFormat("0.00000000000");
		String text = df.format(value);
		if (mantissaLength == 0) {
			text = text.substring(0, text.indexOf('.'));
		} else {
			text = text.substring(0, text.indexOf('.') + mantissaLength + 1);
		}
		return text.equals("-0.00") ? "0.00" : text;
	}
	// 浮点格式转换至文本格式
	public static String doubleText(double value) {
		return numberToText(value, 2);
	}
	public static String doubleText(double value, int mantissaLength) {
		return numberToText(value, mantissaLength);
	}
	public static String floatText(float value) {
		return numberToText(value, 2);
	}
	public static String floatText(float value, int mantissaLength) {
		return numberToText(value, mantissaLength);
	}

    //资金格式化
    public static String getFormatDouble(Capital c) {
        if (c == null||c.getCapitalAvailable() < 0.001) {
            return "0";
        }
        return new BigDecimal(String.valueOf(c.getCapitalAvailable())).setScale(2, BigDecimal.ROUND_DOWN).toString();
    }
    
    /**
     * 格式化失真的数据	失真数据直接舍弃小数点后2位
     */
    public static Double formatDouble(Double d){
    	if (d == null||d < 0.001) {
            return 0d;
        }
    	return new BigDecimal(String.valueOf(d)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
    }

    public static Double formatDouble(String d){
        if(StringUtils.isEmpty(d)){
            return 0d;
        }
        return new BigDecimal(d).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
    }
    
	 public static void main(String sunx[]) {
		 double d = 35.54;
		 System.out.println(new BigDecimal(d).setScale(2, BigDecimal.ROUND_DOWN));
		 System.out.println(new BigDecimal(String.valueOf(d)).setScale(2, BigDecimal.ROUND_DOWN));
	 }
}
