package com.yotrio.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 * 模块名称：study
 * 功能说明：<br>
 * 功能描述：<br>
 * 开发人员：wangyiqiang
 * 创建时间： 2017-03-08 下午8:42
 * 系统版本：1.0.0
 **/
public class DateUtils {

    /**
     * 根据一个时间格式字符串串，返回一个字符串
     *
     * @param format 时间字符串
     * @param date   要格式化的时间数据
     */
    public static final String format(String format, Date date) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (date != null) {
            df = new SimpleDateFormat(format);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    /**
     * 获取本月第一天
     *
     * @param format
     * @return
     */
    public static String getFirstDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        return format(format, cal.getTime());
    }

    /**
     * 获取本月最后一天
     *
     * @param format
     * @return
     */
    public static String getLastDayOfMonth(String format) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        return format(format, cal.getTime());
    }

    /**
     * 获取本月开始时间
     *
     * @param date
     * @return
     */
    public static Date monthStartTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取本月结束时间
     *
     * @param date
     * @return
     */
    public static Date monthEndTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 997);
        return cal.getTime();
    }

    /**
     * 获取本周开始时间
     *
     * @param date
     * @return
     */
    public static Date weekStartTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取本周结束时间
     *
     * @param date
     * @return
     */
    public static Date weekEndTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_WEEK, 1);
        cal.add(Calendar.WEEK_OF_MONTH, 1);
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 997);
        return cal.getTime();
    }

    /**
     * 由出生日期获得年龄
     *
     * @param birthDay
     * @return
     * @throws Exception
     */
    public static int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            } else {
                age--;
            }
        }
        return age;
    }
}
