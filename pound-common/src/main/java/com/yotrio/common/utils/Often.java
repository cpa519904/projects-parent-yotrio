package com.yotrio.common.utils;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

//经常使用的类
public final class Often {
    private static Logger logger = Logger.getLogger(Often.class);
    public static final int SMALL_TO_BIG = 1;// 从小到大排序
    public static final int BIG_TO_SMALL = 2;// 从大到小排序
    public static final int SORT_TYPE_SMALL_TO_BIG = 1;// 从小到大排序
    public static final int SORT_TYPE_BIG_TO_SMALL = 2;// 从大到小排序
    protected static char randomSymbol[] = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    protected static Random random = new Random();
    protected static Map<String, String> sessionMemory = new Hashtable<String, String>();

    public static void p() {
        System.out.println();
    }

    public static void p(boolean b) {
        System.out.println(b);
    }

    public static void p(char c) {
        System.out.println(c);
    }

    public static void p(char[] c) {
        System.out.println(c);
    }

    public static void p(double d) {
        System.out.println(d);
    }

    public static void p(float f) {
        System.out.println(f);
    }

    public static void p(int i) {
        System.out.println(i);
    }

    public static void p(long l) {
        System.out.println(l);
    }

    public static void p(Object o) {
        System.out.println(o);
    }

    public static void p(String s) {
        System.out.println(s);
    }

    public static void p_(boolean b) {
        System.out.print(b);
    }

    public static void p_(char c) {
        System.out.print(c);
    }

    public static void p_(char[] c) {
        System.out.print(c);
    }

    public static void p_(double d) {
        System.out.print(d);
    }

    public static void p_(float f) {
        System.out.print(f);
    }

    public static void p_(int i) {
        System.out.print(i);
    }

    public static void p_(long l) {
        System.out.print(l);
    }

    public static void p_(Object o) {
        System.out.print(o);
    }

    public static void p_(String s) {
        System.out.print(s);
    }

    public static long t() {
        return System.currentTimeMillis();
    }

    // 字符串解码
    public static String stringDecode(String s) throws UnsupportedEncodingException {
        return stringDecode(s, "ISO-8859-1", "GBK");
    }

    public static String stringDecode(String s, String decodeCharsetName) throws UnsupportedEncodingException {
        return stringDecode(s, "ISO-8859-1", decodeCharsetName);
    }

    public static String stringDecode(String s, String codingCharsetName, String decodeCharsetName) throws UnsupportedEncodingException {
        return new String(s.getBytes(codingCharsetName), decodeCharsetName);
    }

    // 字符到数据库
    public static String toDatabase(String s) {
        return filter(s);
    }

    // 数据库到字符
    public static String fromDatabase(String s) {
        return filterCancel(s);
    }

    // 字符过滤
    public static String filter(String text) {
        if (text.indexOf('\'') > -1)
            text = text.replace('\'', '`');
        return text;
    }

    // 字符过滤取消
    public static String filterCancel(String text) {
        if (text.indexOf('`') > -1)
            text = text.replace('`', '\'');
        return text;
    }

    // 返回字符串字节长度(中文算2位,字母数字等ASCII有的算1位)
    public static int stringLength(String s) {
        int len, jglen = 0;
        len = s.length();
        for (int i = 0; i < len; i++) {
            if (((int) s.charAt(i)) < 257)
                jglen++;
            else
                jglen += 2;
        }
        return jglen;
    }

    // 从指定的字符串返回指定长度的字符串(中文算2位,字母数字等ASCII有的算1位)
    public static String stringPortion(String s, int byteLength) {
        return stringPortion(s, byteLength, "");
    }

    // 从指定的字符串返回指定长度的字符串，后面用指定的字符串添加(中文算2位,字母数字等ASCII有的算1位)
    public static String stringPortion(String s, int byteLength, String postfix) {
        int len, pLen;
        len = s.length();
        pLen = stringLength(postfix);
        if (stringLength(s) <= byteLength)
            return s;
        else {
            int jgLen = 0;
            for (int i = 0; i < len; i++) {
                if (((int) s.charAt(i)) < 257) {
                    if (jgLen + 1 + pLen < byteLength)
                        jgLen++;
                    else if (jgLen + 1 + pLen > byteLength) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(s.substring(0, i));
                        sb.append(postfix);
                        return sb.toString();
                    } else {
                        StringBuffer sb = new StringBuffer();
                        sb.append(s.substring(0, i + 1));
                        sb.append(postfix);
                        return sb.toString();
                    }
                } else {
                    if (jgLen + 2 + pLen < byteLength)
                        jgLen += 2;
                    else if (jgLen + 2 + pLen > byteLength) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(s.substring(0, i));
                        sb.append(postfix);
                        return sb.toString();
                    } else {
                        StringBuffer sb = new StringBuffer();
                        sb.append(s.substring(0, i + 1));
                        sb.append(postfix);
                        return sb.toString();
                    }
                }
            }
        }
        return s;
    }

    // 数组清除null
    public static Object[] arrayCompact(Object o[]) {
        int nullCount = 0, location = 0;
        for (int i = 0; i < o.length; i++)
            if (o[i] == null)
                nullCount++;
        Object obj[] = new Object[o.length - nullCount];
        for (int i = 0; i < o.length; i++)
            if (o[i] != null) {
                obj[location] = o[i];
                location++;
            }
        return obj;
    }

    // 数组分割
    public static Object[][] arraySplit(Object o[], int length) {
        if (length < 1)
            length = 1;
        Object obj[][];
        if (o.length % length == 0)
            obj = new Object[o.length / length][];
        else
            obj = new Object[o.length / length + 1][];
        int count1 = 0, count2 = 0;
        for (int i = 0; i < o.length; i++) {
            if (count2 == length) {
                count2 = 0;
                count1++;
            }
            if (obj[count1] == null) {
                if (count1 * length + length > o.length)
                    obj[count1] = new Object[o.length % length];
                else
                    obj[count1] = new Object[length];
            }
            obj[count1][count2] = o[i];
            count2++;
        }
        return obj;
    }

    // 数组扁平化
    public static Object[] arrayFlatten(Object o[][]) {
        int count = 0, location = 0;
        for (int i = 0; i < o.length; i++)
            for (int j = 0; j < o[i].length; j++)
                count++;
        Object obj[] = new Object[count];
        for (int i = 0; i < o.length; i++)
            for (int j = 0; j < o[i].length; j++) {
                obj[location] = o[i][j];
                location++;
            }
        return obj;
    }

    // 字符串是否没内容
    public static boolean $null(String s) {
        return nulls(s);
    }

    public static boolean nulls(String s) {
        if (s == null)
            return true;
        if (s.trim().length() == 0)
            return true;
        return false;
    }

    // 返回字符串
    public static String $(String s) {
        return $string(s);
    }

    public static String $(boolean b) {
        return String.valueOf(b);
    }

    public static String $(char c) {
        return String.valueOf(c);
    }

    public static String $(char[] data) {
        return String.valueOf(data);
    }

    public static String $(char[] data, int offset, int count) {
        return String.valueOf(data, offset, count);
    }

    public static String $(int i) {
        return String.valueOf(i);
    }

    public static String $(long l) {
        return String.valueOf(l);
    }

    public static String $(float f) {
        return String.valueOf(f);
    }

    public static String $(double d) {
        return String.valueOf(d);
    }

    public static String $(Object o) {
        return String.valueOf(o);
    }

    public static String $string(String s) {
        if (s == null)
            return "";
        return s.trim();
    }

    // 解析boolean
    public static boolean $boolean(String value) {
        return $boolean(value, false);
    }

    public static boolean $boolean(String value, boolean defaultValue) {
        try {
            return Boolean.parseBoolean($(value));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static boolean $boolean(int value) {
        if (value == 0)
            return true;
        return false;
    }

    // 解析int
    public static int $int(String value) {
        return $int(value, 0);
    }

    public static int $int(String value, int defaultValue) {
        try {
            return Integer.parseInt($(value));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static int $int(boolean value) {
        if (value)
            return 0;
        return 1;
    }

    // 解析long
    public static long $long(String value) {
        return $long(value, 0);
    }

    public static long $long(String value, long defaultValue) {
        try {
            return Long.parseLong($(value));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    // 解析float
    public static float $float(String value) {
        return $float(value, 0);
    }

    public static float $float(String value, float defaultValue) {
        try {
            return Float.parseFloat($(value));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    // 解析double
    public static double $double(String value) {
        return $double(value, 0);
    }

    public static double $double(String value, float defaultValue) {
        try {
            return Double.parseDouble($(value));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    // 返回随机文字
    public static String randomString(int length) {
        StringBuffer tmpString = new StringBuffer();
        for (int i = 0; i < length; i++)
            tmpString.append(randomSymbol[random.nextInt(randomSymbol.length)]);
        return tmpString.toString();
    }

    // 返回随机数字
    public static String randomNumber(int length) {
        StringBuffer tmpString = new StringBuffer();
        for (int i = 0; i < length; i++)
            tmpString.append(randomSymbol[random.nextInt(10)]);
        return tmpString.toString();
    }

    // 返回随机Boolean
    public static boolean randomBoolean() {
        return random.nextBoolean();
    }

    // 返回随机Int
    public static int randomInt() {
        return random.nextInt();
    }

    // 返回随机Int
    public static int randomInt(int max) {
        return random.nextInt(max);
    }

    // 返回随机Long
    public static long randomLong() {
        return random.nextLong();
    }

    // 返回随机Float
    public static float randomFloat() {
        return random.nextFloat();
    }

    // 返回随机Double
    public static double randomDouble() {
        return random.nextDouble();
    }

    // byte排序
    public static byte[] order(byte b[]) {
        return byteSort(b, SMALL_TO_BIG);
    }

    public static byte[] order(byte b[], int order) {
        return byteSort(b, order);
    }

    public static byte[] byteSort(byte b[]) {
        return byteSort(b, SMALL_TO_BIG);
    }

    public static byte[] byteSort(byte b[], int order) {
        byte tmpSeve;
        if (order == SMALL_TO_BIG) {
            for (int iii = 0; iii < b.length; iii++) {
                for (int jjj = 1; jjj < b.length; jjj++) {
                    if (b[jjj - 1] > b[jjj]) {
                        tmpSeve = b[jjj];
                        b[jjj] = b[jjj - 1];
                        b[jjj - 1] = tmpSeve;
                    }
                }
            }
        } else {
            for (int iii = 0; iii < b.length; iii++) {
                for (int jjj = 1; jjj < b.length; jjj++) {
                    if (b[jjj - 1] < b[jjj]) {
                        tmpSeve = b[jjj];
                        b[jjj] = b[jjj - 1];
                        b[jjj - 1] = tmpSeve;
                    }
                }
            }
        }
        return b;
    }

    // short排序
    public static short[] order(short s[]) {
        return shortSort(s, SMALL_TO_BIG);
    }

    public static short[] order(short s[], int order) {
        return shortSort(s, order);
    }

    public static short[] shortSort(short s[]) {
        return shortSort(s, SMALL_TO_BIG);
    }

    public static short[] shortSort(short s[], int order) {
        short tmpSeve;
        if (order == SMALL_TO_BIG) {
            for (int iii = 0; iii < s.length; iii++) {
                for (int jjj = 1; jjj < s.length; jjj++) {
                    if (s[jjj - 1] > s[jjj]) {
                        tmpSeve = s[jjj];
                        s[jjj] = s[jjj - 1];
                        s[jjj - 1] = tmpSeve;
                    }
                }
            }
        } else {
            for (int iii = 0; iii < s.length; iii++) {
                for (int jjj = 1; jjj < s.length; jjj++) {
                    if (s[jjj - 1] < s[jjj]) {
                        tmpSeve = s[jjj];
                        s[jjj] = s[jjj - 1];
                        s[jjj - 1] = tmpSeve;
                    }
                }
            }
        }
        return s;
    }

    // int排序
    public static int[] order(int i[]) {
        return intSort(i, SMALL_TO_BIG);
    }

    public static int[] order(int i[], int order) {
        return intSort(i, order);
    }

    public static int[] intSort(int i[]) {
        return intSort(i, SMALL_TO_BIG);
    }

    public static int[] intSort(int i[], int order) {
        int tmpSeve;
        if (order == SMALL_TO_BIG) {
            for (int iii = 0; iii < i.length; iii++) {
                for (int jjj = 1; jjj < i.length; jjj++) {
                    if (i[jjj - 1] > i[jjj]) {
                        tmpSeve = i[jjj];
                        i[jjj] = i[jjj - 1];
                        i[jjj - 1] = tmpSeve;
                    }
                }
            }
        } else {
            for (int iii = 0; iii < i.length; iii++) {
                for (int jjj = 1; jjj < i.length; jjj++) {
                    if (i[jjj - 1] < i[jjj]) {
                        tmpSeve = i[jjj];
                        i[jjj] = i[jjj - 1];
                        i[jjj - 1] = tmpSeve;
                    }
                }
            }
        }
        return i;
    }

    // long排序
    public static long[] order(long l[]) {
        return longSort(l, SMALL_TO_BIG);
    }

    public static long[] order(long l[], int order) {
        return longSort(l, order);
    }

    public static long[] longSort(long l[]) {
        return longSort(l, SMALL_TO_BIG);
    }

    public static long[] longSort(long l[], int order) {
        long tmpSeve;
        if (order == SMALL_TO_BIG) {
            for (int iii = 0; iii < l.length; iii++) {
                for (int jjj = 1; jjj < l.length; jjj++) {
                    if (l[jjj - 1] > l[jjj]) {
                        tmpSeve = l[jjj];
                        l[jjj] = l[jjj - 1];
                        l[jjj - 1] = tmpSeve;
                    }
                }
            }
        } else {
            for (int iii = 0; iii < l.length; iii++) {
                for (int jjj = 1; jjj < l.length; jjj++) {
                    if (l[jjj - 1] < l[jjj]) {
                        tmpSeve = l[jjj];
                        l[jjj] = l[jjj - 1];
                        l[jjj - 1] = tmpSeve;
                    }
                }
            }
        }
        return l;
    }

    // float排序
    public static float[] order(float f[]) {
        return floatSort(f, SMALL_TO_BIG);
    }

    public static float[] order(float f[], int order) {
        return floatSort(f, order);
    }

    public static float[] floatSort(float f[]) {
        return floatSort(f, SMALL_TO_BIG);
    }

    public static float[] floatSort(float f[], int order) {
        float tmpSeve;
        if (order == SMALL_TO_BIG) {
            for (int iii = 0; iii < f.length; iii++) {
                for (int jjj = 1; jjj < f.length; jjj++) {
                    if (f[jjj - 1] > f[jjj]) {
                        tmpSeve = f[jjj];
                        f[jjj] = f[jjj - 1];
                        f[jjj - 1] = tmpSeve;
                    }
                }
            }
        } else {
            for (int iii = 0; iii < f.length; iii++) {
                for (int jjj = 1; jjj < f.length; jjj++) {
                    if (f[jjj - 1] < f[jjj]) {
                        tmpSeve = f[jjj];
                        f[jjj] = f[jjj - 1];
                        f[jjj - 1] = tmpSeve;
                    }
                }
            }
        }
        return f;
    }

    // double排序
    public static double[] order(double d[]) {
        return doubleSort(d, SMALL_TO_BIG);
    }

    public static double[] order(double d[], int order) {
        return doubleSort(d, order);
    }

    public static double[] doubleSort(double d[]) {
        return doubleSort(d, SMALL_TO_BIG);
    }

    public static double[] doubleSort(double d[], int order) {
        double tmpSeve;
        if (order == SMALL_TO_BIG) {
            for (int iii = 0; iii < d.length; iii++) {
                for (int jjj = 1; jjj < d.length; jjj++) {
                    if (d[jjj - 1] > d[jjj]) {
                        tmpSeve = d[jjj];
                        d[jjj] = d[jjj - 1];
                        d[jjj - 1] = tmpSeve;
                    }
                }
            }
        } else {
            for (int iii = 0; iii < d.length; iii++) {
                for (int jjj = 1; jjj < d.length; jjj++) {
                    if (d[jjj - 1] < d[jjj]) {
                        tmpSeve = d[jjj];
                        d[jjj] = d[jjj - 1];
                        d[jjj - 1] = tmpSeve;
                    }
                }
            }
        }
        return d;
    }

    // 排序
    public static Comparable[] order(Comparable c[]) {
        return order(c, SMALL_TO_BIG);
    }

    public static Comparable[] order(Comparable c[], int order) {
        Comparable tmpSeve;
        if (order == SMALL_TO_BIG) {
            for (int i = 0; i < c.length; i++)
                for (int j = 1; j < c.length; j++)
                    if (c[j - 1].compareTo(c[j]) > 0) {
                        tmpSeve = c[j];
                        c[j] = c[j - 1];
                        c[j - 1] = tmpSeve;
                    }
        } else {
            for (int i = 0; i < c.length; i++)
                for (int j = 1; j < c.length; j++)
                    if (c[j - 1].compareTo(c[j]) < 0) {
                        tmpSeve = c[j];
                        c[j] = c[j - 1];
                        c[j - 1] = tmpSeve;
                    }
        }
        return c;
    }

    // 返回当前日期时间
    public static String datetime() {
        return dateTime();
    }

    public static String dateTime() {
        return dateTime(new Date());
    }

    // 返回指定日期时间
    public static String datetime(Date date) {
        return dateTime(date);
    }

    public static String dateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    // 返回指定日期时间
    public static Date datetime(String date) {
        return dateTime(date);
    }

    public static Date dateTime(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    // 返回当前适中日期时间
    public static String datetimeModerate() {
        return dateTimeModerate();
    }

    public static String dateTimeModerate() {
        return dateTimeModerate(new Date());
    }

    // 返回指定适中日期时间
    public static String datetimeModerate(Date date) {
        return dateTimeModerate(date);
    }

    public static String dateTimeModerate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(date);
    }

    // 返回当前简单日期时间
    public static String datetimeSimple() {
        return dateTimeSimple();
    }

    public static String dateTimeSimple() {
        return dateTimeSimple(new Date());
    }

    // 返回指定简单日期时间
    public static String datetimeSimple(Date date) {
        return dateTimeSimple(date);
    }

    public static String dateTimeSimple(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
        return sdf.format(date);
    }

    // 返回当前日期
    public static String date() {
        return date(new Date());
    }

    // 返回指定日期
    public static String date(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    // 返回当前时间
    public static String time() {
        return time(new Date());
    }

    // 返回指定时间
    public static String time(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(date);
    }

    // 返回当前年份
    public static int year() {
        return year(new Date());
    }

    // 返回指定年份
    public static int year(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.YEAR);
    }

    // 返回当前月份
    public static int month() {
        return month(new Date());
    }

    // 返回指定月份
    public static int month(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.MONTH) + 1;
    }

    // 返回当前日份
    public static int day() {
        return day(new Date());
    }

    // 返回指定日份
    public static int day(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }

    // 返回当前小时
    public static int hour() {
        return hour(new Date());
    }

    // 返回指定小时
    public static int hour(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.HOUR_OF_DAY);
    }

    // 返回当前分钟
    public static int minute() {
        return minute(new Date());
    }

    // 返回指定分钟
    public static int minute(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.MINUTE);
    }

    // 返回当前秒
    public static int second() {
        return second(new Date());
    }

    // 返回指定秒
    public static int second(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.SECOND);
    }

    // 返回当前年中的周数
    public static int yearWeek() {
        return yearWeek(new Date());
    }

    // 返回指定年中的周数
    public static int yearWeek(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.WEEK_OF_YEAR);
    }

    // 返回当前年中的天数
    public static int yearDay() {
        return yearDay(new Date());
    }

    // 返回指定年中的天数
    public static int yearDay(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.DAY_OF_YEAR);
    }

    // 返回当前月份中的周数
    public static int monthWeek() {
        return monthWeek(new Date());
    }

    // 返回指定月份中的周数
    public static int monthWeek(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.WEEK_OF_MONTH);
    }

    // 返回当前星期中的天数
    public static int weekDay() {
        return weekDay(new Date());
    }

    // 返回指定星期中的天数
    public static int weekDay(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(GregorianCalendar.DAY_OF_WEEK) - 1;
    }

    // 返回文件类型
    public static String fileType(File file) {
        return fileType(file.getPath());
    }

    public static String fileType(String path) {
        return path.substring(path.lastIndexOf('.') + 1).toLowerCase();
    }

    // 返回文件名
    public static String fileName(File file) {
        return fileName(file.getPath());
    }

    public static String fileName(String path) {
        return path.substring(path.lastIndexOf('\\') + 1);
    }

    // 返回文件目录
    public static String fileDirectory(File file) {
        if (file.isFile())
            return fileDirectory(file.getPath());
        return file.getPath();
    }

    public static String fileDirectory(String path) {
        return path.substring(0, path.lastIndexOf('\\'));
    }

    // 返回文件
    public static File file(String path) {
        return file(new File(path));
    }

    public static File file(File file) {
        if (!file.exists()) {
            try {
                if (!file.getParentFile().exists())
                    file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (Exception e) {
            }
        }
        return file;
    }

    // 返回BufferedInputStream
    public static BufferedInputStream fileInputStream(String path) {
        return fileInputStream(path, -1);
    }

    public static BufferedInputStream fileInputStream(String path, int bufferedSize) {
        return fileInputStream(new File(path), bufferedSize);
    }

    public static BufferedInputStream fileInputStream(File file) {
        return fileInputStream(file, -1);
    }

    public static BufferedInputStream fileInputStream(File file, int bufferedSize) {
        try {
            return fileInputStream(new FileInputStream(file), bufferedSize);
        } catch (Exception e) {
            return null;
        }
    }

    public static BufferedInputStream fileInputStream(InputStream is) {
        return fileInputStream(is, -1);
    }

    public static BufferedInputStream fileInputStream(InputStream is, int bufferedSize) {
        if (bufferedSize == -1)
            return new BufferedInputStream(is);
        else
            return new BufferedInputStream(is, bufferedSize);
    }

    // 返回BufferedOutputStream
    public static BufferedOutputStream fileOutputStream(String path) {
        return fileOutputStream(path, -1);
    }

    public static BufferedOutputStream fileOutputStream(String path, int bufferedSize) {
        return fileOutputStream(new File(path), bufferedSize);
    }

    public static BufferedOutputStream fileOutputStream(File file) {
        return fileOutputStream(file, -1);
    }

    public static BufferedOutputStream fileOutputStream(File file, int bufferedSize) {
        try {
            return fileOutputStream(new FileOutputStream(file), bufferedSize);
        } catch (Exception e) {
            return null;
        }
    }

    public static BufferedOutputStream fileOutputStream(OutputStream os) {
        return fileOutputStream(os, -1);
    }

    public static BufferedOutputStream fileOutputStream(OutputStream os, int bufferedSize) {
        if (bufferedSize == -1)
            return new BufferedOutputStream(os);
        else
            return new BufferedOutputStream(os, bufferedSize);
    }

    // 返回BufferedReader
    public static BufferedReader fileReader(String path) {
        return fileReader(path, -1);
    }

    public static BufferedReader fileReader(String path, int bufferedSize) {
        return fileReader(new File(path), bufferedSize);
    }

    public static BufferedReader fileReader(File file) {
        return fileReader(file, -1);
    }

    public static BufferedReader fileReader(File file, int bufferedSize) {
        try {
            return fileReader(new FileReader(file), bufferedSize);
        } catch (Exception e) {
            return null;
        }
    }

    public static BufferedReader fileReader(FileInputStream fis) {
        return fileReader(fis, -1);
    }

    public static BufferedReader fileReader(FileInputStream fis, int bufferedSize) {
        return fileReader(new InputStreamReader(fis), bufferedSize);
    }

    public static BufferedReader fileReader(Reader reader) {
        return fileReader(reader, -1);
    }

    public static BufferedReader fileReader(Reader reader, int bufferedSize) {
        if (bufferedSize == -1)
            return new BufferedReader(reader);
        else
            return new BufferedReader(reader, bufferedSize);
    }

    // 返回BufferedWriter
    public static BufferedWriter fileWriter(String path) {
        return fileWriter(path, -1);
    }

    public static BufferedWriter fileWriter(String path, int bufferedSize) {
        return fileWriter(new File(path), bufferedSize);
    }

    public static BufferedWriter fileWriter(File file) {
        return fileWriter(file, -1);
    }

    public static BufferedWriter fileWriter(File file, int bufferedSize) {
        try {
            return fileWriter(new FileWriter(file), bufferedSize);
        } catch (Exception e) {
            return null;
        }
    }

    public static BufferedWriter fileWriter(FileOutputStream fos) {
        return fileWriter(fos, -1);
    }

    public static BufferedWriter fileWriter(FileOutputStream fos, int bufferedSize) {
        return fileWriter(new OutputStreamWriter(fos), bufferedSize);
    }

    public static BufferedWriter fileWriter(Writer writer) {
        return fileWriter(writer, -1);
    }

    public static BufferedWriter fileWriter(Writer writer, int bufferedSize) {
        if (bufferedSize == -1)
            return new BufferedWriter(writer);
        else
            return new BufferedWriter(writer, bufferedSize);
    }

    // 返回目录
    public static File directory(String path) {
        return directory(new File(path));
    }

    public static File directory(File file) {
        if (!file.exists())
            file.mkdirs();
        return file;
    }

    // 删除目录
    public static boolean directoryDelete(String path) {
        return directoryDelete(path, true, true);
    }

    public static boolean directoryDelete(File file) {
        return directoryDelete(file, true, true);
    }

    public static boolean directoryDelete(String path, boolean dirDelete, boolean selfDirDelete) {
        File tmpFile = new File(path);
        return directoryDelete(tmpFile, dirDelete, selfDirDelete);
    }

    public static boolean directoryDelete(File file, boolean dirDelete, boolean selfDirDelete) {
        File tmpFile[] = file.listFiles();
        if (tmpFile == null)
            return true;
        for (int i = 0; i < tmpFile.length; i++) {
            if (tmpFile[i].isDirectory()) {
                if (selfDirDelete) {
                    if (!directoryDelete(tmpFile[i], selfDirDelete, selfDirDelete))
                        return false;
                } else {
                    if (!directoryDelete(tmpFile[i], dirDelete, dirDelete))
                        return false;
                }
            } else if (!tmpFile[i].delete())
                return false;
        }
        if (selfDirDelete)
            return file.delete();
        return true;
    }

    // 返回HttpURLConnection
    public static HttpURLConnection httpConnection(String url) {
        try {
            URL tmpUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) tmpUrl.openConnection();
            // / set timeout
            connection.setConnectTimeout(6000);
            connection.setReadTimeout(10000);
            return connection;
        } catch (Exception e) {
            return null;
        }
    }

    // 返回SESSIONID
    public static String httpSessionID(HttpURLConnection con) {
        return sessionMemory.get(con.getURL().getHost());
    }

    // 设置SESSIONID
    public static void httpSessionID(HttpURLConnection con, String sessionID) {
        con.setRequestProperty("Cookie", sessionID);
    }

    // HTTP访问
    public static String http(String url) {
        return http(url, null, false, null);
    }

    public static String http(String url, String parameter) {
        return http(url, parameter, false, null);
    }

    public static String httpXobj(String url, String parameter) {
        HttpURLConnection httpConnection = httpConnection(url);
        if (httpConnection == null)
            return null;
        return http(httpConnection, parameter, false, null, false);
    }

    public static String httpXobj(String url, String parameter, String encoding) {
        HttpURLConnection httpConnection = httpConnection(url);
        if (httpConnection == null)
            return null;
        return http(httpConnection, parameter, false, encoding, false);
    }

    public static String http(String url, boolean session) {
        return http(url, null, session, null);
    }

    public static String http(String url, String parameter, boolean session) {
        return http(url, parameter, session, null);
    }

    public static String http(String url, String parameter, boolean session, String streamCharsetName, boolean isXobj) {
        HttpURLConnection httpConnection = httpConnection(url);
        if (httpConnection == null)
            return null;
        return http(httpConnection, parameter, session, streamCharsetName, false);
    }

    public static String http(String url, String parameter, boolean session, String streamCharsetName) {
        HttpURLConnection httpConnection = httpConnection(url);
        if (httpConnection == null)
            return null;
        return http(httpConnection, parameter, session, streamCharsetName);
    }

    public static String http(HttpURLConnection con) {
        return http(con, null, false, null);
    }

    public static String http(HttpURLConnection con, String parameter) {
        return http(con, parameter, false, null);
    }

    public static String http(HttpURLConnection con, boolean session) {
        return http(con, null, session, null);
    }

    public static String http(HttpURLConnection con, String parameter, boolean session) {
        return http(con, parameter, session, null);
    }

    /**
     * @param money
     * @return
     * @author icelove
     */
    public static String amountWithChinese(String money) {
        if (StringUtil.isEmpty(money) || money.equals("0") || money.equals("0.0") || money.equals("0.00")) return "0";
        if (money.contains(",") || money.contains("，")) money = money.replaceAll(",", "").replaceAll("，", "");
        if (!NumberUtils.isNumber(money)) {
            return "0";
        }
        double moneyNum = NumberUtils.str2Double(money, -1);
        if (moneyNum < 0) return "0";
        double YIYUAN = 100000000D;
        String retMoney = "0";
        //大于1亿(取两位小数)
        if (moneyNum >= YIYUAN) {
            retMoney = NumberUtils.double2Money(moneyNum / YIYUAN, 2, BigDecimal.ROUND_DOWN) + "亿";
        } else if (moneyNum >= 10000D) {
            retMoney = (int) (moneyNum / 10000) + "万";
        } else {
            retMoney = (int) moneyNum + "";
        }
        return retMoney;
    }

    public static String http(HttpURLConnection con, String parameter, boolean session, String streamCharsetName, boolean isXobj) {
        String tmpHost = con.getURL().getHost();
        if (session) {
            String sessionID = sessionMemory.get(tmpHost);
            if (sessionID != null)
                con.setRequestProperty("Cookie", sessionID);
        }
        StringBuffer tmpContent = new StringBuffer();
        try {
            if (parameter != null && parameter.length() > 0) {
                con.setDoOutput(true);
                // Post 请求不能使用缓存（自从尊傲接口修改）
                con.setUseCaches(false);
                if (isXobj) {
                    con.setRequestProperty("Content-type", "application/x-java-serialized-object");
                }
                con.setRequestMethod("POST");
                BufferedWriter bw;
                if (streamCharsetName == null) {
                    bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
                } else {
                    bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), streamCharsetName));
                }
                bw.write(parameter);
                bw.flush();
                bw.close();
            }
            if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
                logger.error("<http> http fail! responseCode = " + con.getResponseCode() + ", url=" + con.getURL());
                return null;
            }
            if (session) {
                String cookei = con.getHeaderField("Set-Cookie");
                if (cookei != null) {
                    String cookeiLc = cookei.toLowerCase();
                    if (cookeiLc.indexOf("sessionid") != -1) {
                        int il1 = cookeiLc.indexOf("sessionid");
                        int il2 = cookeiLc.lastIndexOf(";", il1);
                        int il3 = cookeiLc.indexOf(";", il1);
                        if (il3 == -1)
                            il3 = cookeiLc.length();
                        String sessionID = cookei.substring(il2 + 1, il3).trim();
                        sessionMemory.put(tmpHost, sessionID);
                    }
                }
            }
            BufferedReader br;
            if (streamCharsetName == null)
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            else
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), streamCharsetName));
            String tmpText = br.readLine();
            while (tmpText != null) {
                tmpContent.append(tmpText);
                tmpContent.append("\r\n");
                tmpText = br.readLine();
            }
            br.close();
            con.disconnect();
        } catch (Exception e) {
            logger.error("<http> ANTE ERROR:url=" + con.getURL() + "," + e.getMessage());
            return null;
        }
        return tmpContent.toString();
    }

    public static String http(HttpURLConnection con, String parameter, boolean session, String streamCharsetName) {
        return http(con, parameter, session, streamCharsetName, true);
    }

    // 返回Document
    public static Document xml(String xml) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return builder.parse(new ByteArrayInputStream(xml.getBytes()));
        } catch (Exception e) {
            return null;
        }
    }

    public static Document xml(File file) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return builder.parse(file);
        } catch (Exception e) {
            return null;
        }
    }

    public static Document xml(InputStream is) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return builder.parse(is);
        } catch (Exception e) {
            return null;
        }
    }
    // public static void main(String sun[])throws Exception
    // {
    // // String s[]=new String[]{"aaa",null,"bbb","ccc",null,"ddd"};
    // // Object o[]=arrayCompact(s);
    // // for(int i=0;i<o.length;i++)arrayFlatten
    // // System.out.println((String)o[i]);
    // // String s[][]=new String[][]{{"aaa","bbb"},{"ccc"},{"111","222"}};
    // // Object o[]=arrayFlatten(s);
    // // for(int i=0;i<o.length;i++)
    // // System.out.println((String)o[i]);
    //
    // // directory("C:\\aaa\\bbb\\ccc1.txt");
    // // System.out.println(http("http://www.baicu.com"));
    // // System.out.println(http("http://www.bsms.net.cn/LANZGateway/HeartBeat.asp","sdfgsdg=fghdfgh",true));
    // // System.out.println(randomNumber(111));
    // // System.out.println(year(dateTime("2007-07-01 18:19:33")));
    // // System.out.println(weekDay());
    // // System.out.println(randomString(10));
    // // System.out.println(fileName("c:\\sun\\aaa.gif"));
    // // System.out.println(checkAndCreateDir("c:\\sun\\xiao\\aaa"));
    // // System.out.println(getEspecialPartString("全国（区域）联网22选5开奖数据",20,"..."));
    // // String s="dlkjdsfdlfsfsfds";
    // // long sj1,sj2;
    // // sj1=System.currentTimeMillis();
    // // int size=20;
    // // byte ia[]=new byte[size];
    // // java.util.Random random=new java.util.Random();
    // // for(int i=0;i<size;i++)
    // // ia[i]=random.nextBytes(200);
    // // double ia[]=new double[]{4,7,1,8,9,3,2,5,6,0};
    // // ia=Often.doubleSort(ia,1);
    // // sj2=System.currentTimeMillis();
    // // System.out.println(Often.filtrationQueryString(s));
    // // System.out.println((sj2-sj1));
    // // for(int i=0;i<ia.length;i++)
    // // System.out.println(ia[i]);
    //
    // Date date=new Date();
    // long sj1=System.currentTimeMillis(),sj2;
    // for(int i=0;i<100000;i++)
    // Often.datetime(date);
    // sj2=System.currentTimeMillis();
    // System.out.println((sj2-sj1));
    // }
}
