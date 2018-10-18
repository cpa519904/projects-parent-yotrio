package com.yotrio.common.utils;


import com.yotrio.common.constants.ConstellationConstants;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 系统名称：彩猫<br>
 * 模块名称：时间工具<br>
 * 功能说明：<br>
 * 功能描述：<br>
 * 开发人员：lujun <br>
 * 软件著作权：彩猫 caimao.com 版权所有<br>
 * 开发时间：Jun 18, 2012 4:33:36 PM<br>
 * 系统版本：1.0.0<br>
 */
public abstract class DateUtil {
    private static final Logger logger = Logger.getLogger(DateUtil.class);
    /**
     * Chinese date time formate
     */
    public static final String CHINESE_DATE_TIME = "yyyy年MM月dd日 HH:mm";
    /**
     * Chinese date format
     */
    public static final String CHINESE_DATE = "yyyy年MM月dd日";
    /**
     * 月日
     */
    public static final String CHINESE_MONTH_DATE = "MM月dd日";
    /**
     * 月日 时分
     */
    public static final String CHINESE_MONTH_TIME = "MM月dd日 HH:mm";
    /**
     * Default date time format
     */
    public static final String DEFAULT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    /**
     * Default date time format
     */
    public static final String DEFAULT_DATE_TIME_SECOND = "yyyy-MM-dd HH:mm";
    /**
     * Default date time format
     */
    public static final String DEFAULT_DATE_TIME_HOUR = "yyyy-MM-dd HH";
    /**
     * Default date format
     */
    public static final String DEFAULT_DATE = "yyyy-MM-dd";
    /**
     * Default time format
     */
    public static final String DEFAULT_TIME = "HH:mm:ss";
    /**
     * For log and file's date format
     */
    public static final String LOG_DATE_TIME = "yyyyMMddHHmmssSSS";
    /**
     * 一天的表示形式
     */
    public static final long DURATION = 1000L * 60 * 60 * 24;// 一天
    public static final long DURATION_HOUR = 1000L * 60 * 60;// 一小时
    public static final String[] weeks = new String[]{"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

    /**
     * Count the days between 2 date,if the first date argument is before the second one, a negative number wiil be returned
     *
     * @param date1 A <code>java.util.Date</code> object
     * @param date2 A <code>java.util.Date</code> object
     * @return Day counts between the two input day If the first date argument is before the seconde one, a negative number will be returned.
     */
    public static int daysBetween(Date date1, Date date2) {
        if (date1 == null || date2 == null)
            return 0;
        GregorianCalendar cal1 = new GregorianCalendar();
        cal1.setTime(date1);
        GregorianCalendar cal2 = new GregorianCalendar();
        cal2.setTime(date2);
        int before = cal1.compareTo(cal2);
        // make the later one always in cal1
        if (before < 0) {
            cal1.setTime(date2);
            cal2.setTime(date1);
        }
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        int yearBetween = year1 - year2;
        for (int i = 0; i < yearBetween; i++) {
            cal2.set(Calendar.YEAR, year2 + i);
            day1 = day1 + cal2.getActualMaximum(Calendar.DAY_OF_YEAR);
        }
        return (day1 - day2) * before;
    }

    /**
     * 计算两个日期的时间差 返回具体的天数
     *
     * @param compareTime
     * @param currentTime
     * @return int
     */
    public static int hoursBetween(Date compareTime, Date currentTime) {
        if (compareTime == null || currentTime == null)
            return 0;
        return (int) ((compareTime.getTime() - currentTime.getTime()) / DURATION);
    }

    /**
     * 计算两个时间之间的小时数，精确到秒。
     * <p>
     * 比如：2008-07-10 17:41:01 和 2008-07-11 17:41:01 之间相差为 24 个小时。
     * </p>
     * <p>
     * 比如：2008-07-10 17:41:01 和 2008-07-11 17:41:00 之间相差为 23 个小时。
     * </p>
     *
     * @param time1 时间1
     * @param time2 时间2
     * @return int
     */
    public static int calHours(Date time1, Date time2) {
        if (time1 == null || time2 == null) {
            return 0;
        }
        int v = (int) ((time1.getTime() - time2.getTime()) / DURATION_HOUR);
        return Math.abs(v);
    }

    /**
     * 计算两个时间之间相差的分钟数，“结束时间”减去“开始时间”之间的分钟数.
     *
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return int 分钟数。如果开始时间或者结束时间为 null 的话，返回 0 。
     */
    public static int minutesBetween(Date beginTime, Date endTime) {
        if (beginTime == null || endTime == null) {
            return 0;
        }
        long diff = endTime.getTime() - beginTime.getTime();
        return (int) diff / (60 * 1000);
    }

    /**
     * 计算两个日期的时间差 返回具体的天数
     *
     * @param compareTime
     * @param currentTime
     * @return int
     */
    public static int secondsBetween(Date compareTime, Date currentTime) {
        if (compareTime == null || currentTime == null)
            return 0;
        return (int) ((compareTime.getTime() - currentTime.getTime()) / 1000L);
    }

    /**
     * 根据生日计算当前年龄.
     * <p>
     * 准确到天，比如：<br/>
     * 当前时间为 2008-06-17 ，生日为 1982-06-16 ，计算结果为 26<br/>
     * 当前时间为 2008-06-17 ，生日为 1982-06-17 ，计算结果为 26<br/>
     * 当前时间为 2008-06-17 ，生日为 1982-06-18 ，计算结果为 25<br/>
     * </p>
     *
     * @param birthDay 必须是一个 Date 对象，否则直接返回 0
     * @return
     */
    public static int getAge(Object birthDay) {
        Date aDate = null;
        // if(birthDay instanceof DATE){
        // DATE date = (DATE)birthDay;
        // aDate = date.dateValue();
        // }else if(birthDay instanceof Date){
        if (birthDay instanceof Date) {
            aDate = (Date) birthDay;
        }
        if (aDate != null) {
            Calendar today = Calendar.getInstance();
            Calendar birthday = Calendar.getInstance();
            birthday.setTime(aDate);
            int yearTotal = today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
            int bDay = today.get(Calendar.DAY_OF_YEAR) - birthday.get(Calendar.DAY_OF_YEAR);
            // not yet match 1 round age, minus one year
            if (bDay < 0) {
                yearTotal = yearTotal - 1;
            }
            return yearTotal;
        }
        return 0;
    }

    /**
     * Get birthday date according to the age
     *
     * @param age Age number
     * @return The birthdate
     */
    public static Date getBirthDay(int age) {
        GregorianCalendar gc = new GregorianCalendar();
        int currYear = gc.get(Calendar.YEAR);
        gc.set(Calendar.YEAR, currYear - age);
        Date birthDate = new Date(gc.getTime().getTime());// 精确到天，haidong.zhou edit 2006-07-14
        return birthDate;
    }

    /**
     * Get recent date according to the days
     *
     * @param days day number
     * @return The date
     */
    public static Date getRecentDay(int days) {
        GregorianCalendar gc = new GregorianCalendar();
        int currDay = gc.get(Calendar.DAY_OF_YEAR);
        gc.set(Calendar.DAY_OF_YEAR, currDay - days);
        return new Date(gc.getTime().getTime());
    }
    // /**
    // * Get Oracle's oracle.sql.DATE object by birthday(support for oracle)
    // * @param age Age number
    // * @return
    // * @deprecated
    // */
    // public static DATE getOracleBirthDay(int age){
    // DATE oracleDate = new DATE(new java.sql.Date(getBirthDay(age).getTime()));
    // return oracleDate;
    // }

    /**
     * Change a java.util.Date to a TO_DATE() format string for oracle sql format
     *
     * @param aDate A java.util.Date Object
     * @return
     */
    public static String toOracleDateFormat(Object aDate) {
        StringBuffer buff = new StringBuffer();
        buff.append("TO_DATE('");
        if (aDate instanceof Date) {
            String dateStr = parseToDefaultDateString((Date) aDate);
            buff.append(dateStr).append("','YYYY-MM-DD HH24:MI:SS')");
            // don't include other package
            // }else if (aDate instanceof DATE){
            // DATE date= (DATE) aDate;
            // buff.append(date.stringValue()).append("','MM/DD/YYYY HH24:MI:SS')");
        } else {
            return null;
        }
        return buff.toString();
    }

    /**
     * Change a java.util.Date to a TO_DATE() format string for oracle sql format
     *
     * @param aDate A java.util.Date Object
     * @return
     */
    public static String toOracleTimeFormat(Object aDate) {
        StringBuffer buff = new StringBuffer();
        buff.append("TO_DATE('");
        if (aDate instanceof Date) {
            String dateStr = parseToDefaultTimeString((Date) aDate);
            buff.append(dateStr).append("','HH24:MI:SS')");
            // don't include other package
            // }else if (aDate instanceof DATE){
            // DATE date= (DATE) aDate;
            // buff.append(date.stringValue()).append("','MM/DD/YYYY HH24:MI:SS')");
        } else {
            return null;
        }
        return buff.toString();
    }

    /**
     * Change a java.util.Date to a TO_DATE() format string for oracle sql format
     *
     * @param aDate A java.util.Date Object
     * @return
     */
    public static String toOracleDateTimeFormat(Object aDate) {
        StringBuffer buff = new StringBuffer();
        buff.append("TO_DATE('");
        if (aDate instanceof Date) {
            String dateStr = parseToDefaultDateTimeString((Date) aDate);
            buff.append(dateStr).append("','YYYY-MM-DD HH24:MI:SS')");
            // don't include other package
            // }else if (aDate instanceof DATE){
            // DATE date= (DATE) aDate;
            // buff.append(date.stringValue()).append("','MM/DD/YYYY HH24:MI:SS')");
        } else {
            return null;
        }
        return buff.toString();
    }

    /**
     * Exchange a date format String into a TO_DATE format string for sql. The exchange principle is following an Oracle way
     *
     * @param str    Date formate string , i.e. 2005-09-28 14:30
     * @param patten oracle date patten Parameter Explanation YEAR Year, spelled out YYYY 4-digit year YYY YY Y Last 3, 2, or 1 digit(s) of year. IYY IY I Last 3, 2, or 1 digit(s) of ISO year. IYYY 4-digit year based on the ISO standard RRRR Accepts a 2-digit year and returns a 4-digit year. A value between 0-49 will return a 20xx year. A value between 50-99 will return a 19xx year. Q Quarter of year (1, 2, 3, 4; JAN-MAR = 1). MM Month (01-12; JAN = 01). MON Abbreviated name of month. MONTH Name
     *               of month, padded with blanks to length of 9 characters. RM Roman numeral month (I-XII; JAN = I). WW Week of year (1-53) where week 1 starts on the first day of the year and continues to the seventh day of the year. W Week of month (1-5) where week 1 starts on the first day of the month and ends on the seventh. IW Week of year (1-52 or 1-53) based on the ISO standard. D Day of week (1-7). DAY Name of day. DD Day of month (1-31). DDD Day of year (1-366). DY Abbreviated name of day. J Julian
     *               day; the number of days since January 1, 4712 BC. HH Hour of day (1-12). HH12 Hour of day (1-12). HH24 Hour of day (0-23). MI Minute (0-59). SS Second (0-59). SSSSS Seconds past midnight (0-86399). AM, A.M., PM, or P.M. AD or A.D AD indicator BC or B.C. BC indicator TZD Daylight savings information. For example, 'PST' TZH Time zone hour. TZM Time zone minute. TZR Time zone region. Usage example： toOracleDateFormat("2003/07/09","yyyy/mm/dd") returns TO_DATE('2003/07/09', 'yyyy/mm/dd')
     * @return
     */
    public static String toOracleDateFormat(String str, String patten) {
        return "TO_DATE('" + str + "','" + patten + "')";
    }

    /**
     * Parse a date format string into a java.util.Date object
     *
     * @param dateStr Date string
     * @param dateFmt Date format to parse the string
     * @return
     * @throws ParseException If the string and the format is not match, exception will be thrown
     */
    public static Date toUtilDate(String dateStr, String dateFmt) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(dateFmt);
        return format.parse(dateStr);
    }

    public static Date toUtilDateN(String dateStr, String dateFmt) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(dateFmt);
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Exchange day format string like yyyy-MM-dd HH:mm into a java.util.Date
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date toDefaultDateTime(String dateStr) throws ParseException {
        Date date = null;
        try {
            date = toUtilDate(dateStr.trim(), DEFAULT_DATE_TIME);
        } catch (ParseException e) {
            date = toUtilDate(dateStr.trim(), DEFAULT_DATE);
        }
        return date;
    }

    /**
     * Exchange day format string like yyyy-MM-dd HH into a java.util.Date
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date toDefaultDateTimeHour(String dateStr) throws ParseException {
        Date date = null;
        try {
            date = toUtilDate(dateStr.trim(), DEFAULT_DATE_TIME_HOUR);
        } catch (ParseException e) {
            date = toUtilDate(dateStr.trim(), DEFAULT_DATE);
        }
        return date;
    }

    /**
     * Exchange day format string like yyyy-MM-dd HH:mm into a java.util.Date
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date toDefaultDateTimeSecond(String dateStr) throws ParseException {
        Date date = null;
        try {
            date = toUtilDate(dateStr.trim(), DEFAULT_DATE_TIME_SECOND);
        } catch (ParseException e) {
            date = toUtilDate(dateStr.trim(), DEFAULT_DATE);
        }
        return date;
    }

    /**
     * Exchange day format string like yyyy-MM-dd into a java.util.Date
     *
     * @param date
     * @return String
     */
    public static String parseToDefaultDateSecondString(Date date) {
        return parseToFormatDateString(date, DEFAULT_DATE_TIME_SECOND);
    }

    /**
     * Exchange day format string like yyyy-MM-dd into a java.util.Date
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date toDefaultDate(String dateStr) throws ParseException {
        return toUtilDate(dateStr, DEFAULT_DATE);
    }

    /**
     * Exchange day format string like HH:mm into a java.util.Date
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static Date toDefaultTime(String dateStr) throws ParseException {
        return toUtilDate(dateStr, DEFAULT_TIME);
    }

    /**
     * Merge the date and the time into one java.util.Date object
     *
     * @param date Date
     * @param time Time
     * @return A java.util.Date object containing the date and the time
     */
    public static Date mergeDateAndTime(Date date, Date time) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        // split the date into year, month and day
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(time);
        cal.set(year, month, day);
        return cal.getTime();
    }

    /**
     * Exchange the java.util.Date object into the DEFAULT_DATE_TIME formate string
     *
     * @param date
     * @return
     */
    public static String parseToDefaultDateTimeString(Date date) {
        return parseToFormatDateString(date, DEFAULT_DATE_TIME);
    }

    /**
     * Exchange the java.util.Date object into the DEFAULT_DATE formate string
     *
     * @param date
     * @return
     */
    public static String parseToDefaultDateString(Date date) {
        return parseToFormatDateString(date, DEFAULT_DATE);
    }

    /**
     * Exchange the java.util.Date object into the DEFAULT_TIME formate string
     *
     * @param date
     * @return
     */
    public static String parseToDefaultTimeString(Date date) {
        return parseToFormatDateString(date, DEFAULT_TIME);
    }

    public static String parseToFormatDateString(Date date, String patten) {
        SimpleDateFormat format = new SimpleDateFormat(patten);
        if (date != null) {
            return format.format(date);
        } else {
            return "";
        }
    }

    /**
     * Compare two java.util.Date object in date field(year, month and day)
     *
     * @param date1
     * @param date2
     * @return If the two Date object are the same day, return true
     */
    public static boolean isDateEqual(Date date1, Date date2) {
        if (date1 == null || date2 == null)
            // one of the input is null, skip the comparation
            return false;
        boolean rtnVal = true;
        Calendar cal1 = new GregorianCalendar();
        cal1.setTime(date1);
        Calendar cal2 = new GregorianCalendar();
        cal2.setTime(date2);
        // compare year
        rtnVal = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
        // compare day
        if (rtnVal)
            // if two dates are in the same year, compare them with the day
            rtnVal = cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
        return rtnVal;
    }

    /**
     * Compare two date in time zone(hour,minute,no need to compare for second for it is useless)
     *
     * @param date1
     * @param date2
     * @return true if the two date are the same in hour and minute
     */
    public static final boolean isTimeEqual(Date date1, Date date2) {
        boolean rtnVal = true;
        Calendar cal1 = new GregorianCalendar();
        cal1.setTime(date1);
        Calendar cal2 = new GregorianCalendar();
        cal1.setTime(date2);
        rtnVal = cal1.get(Calendar.HOUR_OF_DAY) == cal2.get(Calendar.HOUR_OF_DAY);
        if (rtnVal)
            rtnVal = cal1.get(Calendar.MINUTE) == cal2.get(Calendar.MINUTE);
        return rtnVal;
    }

    /**
     * Test whether a date is within an date range
     *
     * @param date The date to be test
     * @param from From range
     * @param to   To range
     * @return
     */
    public static final boolean isBetween(Date date, Date from, Date to) {
        return from.before(date) && to.after(date);
    }

    /**
     * Get the day of tomorrow.
     *
     * @param date Date
     * @return Date
     */
    public static Date getTomorrow(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    /**
     * 获取中国农历年信息.
     *
     * @param date 公历日期
     * @return ChineseYear 农历年信息
     */
    public static ChineseYear getChineseYear(Date date) {
        return new ChineseYear(date);
    }

    /**
     * 返回生肖.
     *
     * @param date 公历生日
     * @return 生肖名
     */
    public static String getAnimalName(Date date) {
        ChineseYear year = new ChineseYear(date);
        try {
            return year.getAnimalName();
        } finally {
            year = null; // release resources
        }
    }

    /**
     * 返回生肖代号.
     *
     * @param date 公历生日
     * @return 生肖名
     */
    public static Integer getAnimalCode(Date date) {
        ChineseYear year = new ChineseYear(date);
        try {
            return new Integer(year.getAnimalCode().intValue() + 1);
        } finally {
            year = null; // release resources
        }
    }

    /**
     * 中国农历类.
     * <p/>
     * 装载农历年月日和生肖等信息，如果是闰月，则显示月份会用-好显示，但是在toString()方法中 会转换为可直接输出的"闰"字
     * </p>
     * <p>
     * Created by 2006-10-8
     * </p>
     *
     * @author <a href="mailto:Watson@tom.com">Watson</a>
     */
    static class ChineseYear {
        private int chineseYear;
        private int chineseMonth;
        private int chineseDay;

        public ChineseYear(Date date) {
            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1; // Calendar类的月份从0开始，运算月份从1开始，故要加1
            int day = cal.get(Calendar.DAY_OF_MONTH);
            if (year < 1901 || year > 2100) {
                throw new IllegalArgumentException("Year must between from 1901 to 2100");
            }
            int startYear = 1901;
            int startMonth = 1;
            int startDay = 1;
            chineseYear = 4597;
            chineseMonth = 11;
            chineseDay = 11;
            // 第二个对应日，用以提高计算效率
            // 公历 2000 年 1 月 1 日，对应农历 4697 年 11 月 25 日
            if (year >= 2000) {
                startYear = 2000;
                startMonth = 1;
                startDay = 1;
                chineseYear = 4696;
                chineseMonth = 11;
                chineseDay = 25;
            }
            // 计算与基准日的天数差距
            int daysDiff = 0;
            for (int i = startYear; i < year; i++) {
                daysDiff += 365;
                if (isGregorianLeapYear(i))
                    daysDiff += 1; // leap year
            }
            for (int i = startMonth; i < month; i++) {
                daysDiff += daysInGregorianMonth(year, i);
            }
            daysDiff += day - startDay;
            // 计算农历日
            chineseDay += daysDiff;
            int lastDay = daysInChineseMonth(chineseYear, chineseMonth);
            int nextMonth = nextChineseMonth(chineseYear, chineseMonth);
            while (chineseDay > lastDay) {
                if (Math.abs(nextMonth) < Math.abs(chineseMonth))
                    chineseYear++;
                chineseMonth = nextMonth;
                chineseDay -= lastDay;
                lastDay = daysInChineseMonth(chineseYear, chineseMonth);
                nextMonth = nextChineseMonth(chineseYear, chineseMonth);
            }
        }

        public String getAnimalName() {
            return animalNames[(chineseYear - 1) % 12];
        }

        public Integer getAnimalCode() {
            return new Integer(((chineseYear - 1) % 12));
        }

        public String toString() {
            StringBuffer buff = new StringBuffer();
            buff.append(stemNames[(chineseYear - 1) % 10]); // 天干纪年
            buff.append(branchNames[(chineseYear - 1) % 12]).append("年 "); // 地支纪年
            if (chineseMonth < 0) {
                buff.append("闰");
            }
            buff.append(monthNames[Math.abs(chineseMonth) - 1]).append("月");
            buff.append(dayNames[chineseDay - 1]);
            String show = buff.toString();
            buff = null;
            return show;
        }

        // 天干
        private final static String[] stemNames = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
        // 地支
        private final static String[] branchNames = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
        // 月份中文名
        private final static String[] monthNames = {"正", "二", "三", "四", "五", "六", "七", "八", "九", "十", "冬", "腊"};
        private final static String[] dayNames = {"初一", "初二", "初三", "初四", "初五", "初六", "初七", "初八", "初九", "初十", "十一", "十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十", "廿一", "廿二", "廿三", "廿四", "廿五", "廿六", "廿七", "廿八", "廿九", "三十",};
        // 生肖名
        private static String[] animalNames = {"鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪"};
        // 大闰月的闰年年份
        private final static int[] bigLeapMonthYears = {6, 14, 19, 25, 33, 36, 38, 41, 44, 52, 55, 79, 117, 136, 147, 150, 155, 158, 185, 193};
        private final static char[] daysInGregorianMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        private final static char[] chineseMonths = {
                // 农历月份大小压缩表，两个字节表示一年。两个字节共十六个二进制位数，
                // 前四个位数表示闰月月份，后十二个位数表示十二个农历月份的大小。
                0x00, 0x04, 0xad, 0x08, 0x5a, 0x01, 0xd5, 0x54, 0xb4, 0x09, 0x64, 0x05, 0x59, 0x45, 0x95, 0x0a, 0xa6, 0x04, 0x55, 0x24, 0xad, 0x08, 0x5a, 0x62, 0xda, 0x04, 0xb4, 0x05, 0xb4, 0x55, 0x52, 0x0d, 0x94, 0x0a, 0x4a, 0x2a, 0x56, 0x02, 0x6d, 0x71, 0x6d, 0x01, 0xda, 0x02, 0xd2, 0x52, 0xa9, 0x05, 0x49, 0x0d, 0x2a, 0x45, 0x2b, 0x09, 0x56, 0x01, 0xb5, 0x20, 0x6d, 0x01, 0x59, 0x69, 0xd4, 0x0a, 0xa8, 0x05, 0xa9, 0x56, 0xa5, 0x04, 0x2b, 0x09, 0x9e, 0x38, 0xb6, 0x08, 0xec, 0x74, 0x6c, 0x05,
                0xd4, 0x0a, 0xe4, 0x6a, 0x52, 0x05, 0x95, 0x0a, 0x5a, 0x42, 0x5b, 0x04, 0xb6, 0x04, 0xb4, 0x22, 0x6a, 0x05, 0x52, 0x75, 0xc9, 0x0a, 0x52, 0x05, 0x35, 0x55, 0x4d, 0x0a, 0x5a, 0x02, 0x5d, 0x31, 0xb5, 0x02, 0x6a, 0x8a, 0x68, 0x05, 0xa9, 0x0a, 0x8a, 0x6a, 0x2a, 0x05, 0x2d, 0x09, 0xaa, 0x48, 0x5a, 0x01, 0xb5, 0x09, 0xb0, 0x39, 0x64, 0x05, 0x25, 0x75, 0x95, 0x0a, 0x96, 0x04, 0x4d, 0x54, 0xad, 0x04, 0xda, 0x04, 0xd4, 0x44, 0xb4, 0x05, 0x54, 0x85, 0x52, 0x0d, 0x92, 0x0a, 0x56, 0x6a,
                0x56, 0x02, 0x6d, 0x02, 0x6a, 0x41, 0xda, 0x02, 0xb2, 0xa1, 0xa9, 0x05, 0x49, 0x0d, 0x0a, 0x6d, 0x2a, 0x09, 0x56, 0x01, 0xad, 0x50, 0x6d, 0x01, 0xd9, 0x02, 0xd1, 0x3a, 0xa8, 0x05, 0x29, 0x85, 0xa5, 0x0c, 0x2a, 0x09, 0x96, 0x54, 0xb6, 0x08, 0x6c, 0x09, 0x64, 0x45, 0xd4, 0x0a, 0xa4, 0x05, 0x51, 0x25, 0x95, 0x0a, 0x2a, 0x72, 0x5b, 0x04, 0xb6, 0x04, 0xac, 0x52, 0x6a, 0x05, 0xd2, 0x0a, 0xa2, 0x4a, 0x4a, 0x05, 0x55, 0x94, 0x2d, 0x0a, 0x5a, 0x02, 0x75, 0x61, 0xb5, 0x02, 0x6a, 0x03,
                0x61, 0x45, 0xa9, 0x0a, 0x4a, 0x05, 0x25, 0x25, 0x2d, 0x09, 0x9a, 0x68, 0xda, 0x08, 0xb4, 0x09, 0xa8, 0x59, 0x54, 0x03, 0xa5, 0x0a, 0x91, 0x3a, 0x96, 0x04, 0xad, 0xb0, 0xad, 0x04, 0xda, 0x04, 0xf4, 0x62, 0xb4, 0x05, 0x54, 0x0b, 0x44, 0x5d, 0x52, 0x0a, 0x95, 0x04, 0x55, 0x22, 0x6d, 0x02, 0x5a, 0x71, 0xda, 0x02, 0xaa, 0x05, 0xb2, 0x55, 0x49, 0x0b, 0x4a, 0x0a, 0x2d, 0x39, 0x36, 0x01, 0x6d, 0x80, 0x6d, 0x01, 0xd9, 0x02, 0xe9, 0x6a, 0xa8, 0x05, 0x29, 0x0b, 0x9a, 0x4c, 0xaa, 0x08,
                0xb6, 0x08, 0xb4, 0x38, 0x6c, 0x09, 0x54, 0x75, 0xd4, 0x0a, 0xa4, 0x05, 0x45, 0x55, 0x95, 0x0a, 0x9a, 0x04, 0x55, 0x44, 0xb5, 0x04, 0x6a, 0x82, 0x6a, 0x05, 0xd2, 0x0a, 0x92, 0x6a, 0x4a, 0x05, 0x55, 0x0a, 0x2a, 0x4a, 0x5a, 0x02, 0xb5, 0x02, 0xb2, 0x31, 0x69, 0x03, 0x31, 0x73, 0xa9, 0x0a, 0x4a, 0x05, 0x2d, 0x55, 0x2d, 0x09, 0x5a, 0x01, 0xd5, 0x48, 0xb4, 0x09, 0x68, 0x89, 0x54, 0x0b, 0xa4, 0x0a, 0xa5, 0x6a, 0x95, 0x04, 0xad, 0x08, 0x6a, 0x44, 0xda, 0x04, 0x74, 0x05, 0xb0, 0x25,
                0x54, 0x03};

        private boolean isGregorianLeapYear(int year) {
            boolean isLeap = false;
            if (year % 4 == 0)
                isLeap = true;
            if (year % 100 == 0)
                isLeap = false;
            if (year % 400 == 0)
                isLeap = true;
            return isLeap;
        }

        /*
         * 计算农历日期
         */
        private int daysInChineseMonth(int y, int m) {
            // 注意：闰月 m < 0
            int index = y - 4597;
            int v = 0;
            int l = 0;
            int d = 30;
            if (1 <= m && m <= 8) {
                v = chineseMonths[2 * index];
                l = m - 1;
                if (((v >> l) & 0x01) == 1)
                    d = 29;
            } else if (9 <= m && m <= 12) {
                v = chineseMonths[2 * index + 1];
                l = m - 9;
                if (((v >> l) & 0x01) == 1)
                    d = 29;
            } else {
                v = chineseMonths[2 * index + 1];
                v = (v >> 4) & 0x0F;
                if (v != Math.abs(m)) {
                    d = 0;
                } else {
                    d = 29;
                    for (int i = 0; i < bigLeapMonthYears.length; i++) {
                        if (bigLeapMonthYears[i] == index) {
                            d = 30;
                            break;
                        }
                    }
                }
            }
            return d;
        }

        /*
         * 计算下一个农历月的日期
         */
        private int nextChineseMonth(int y, int m) {
            int n = Math.abs(m) + 1;
            if (m > 0) {
                int index = y - 4597;
                int v = chineseMonths[2 * index + 1];
                v = (v >> 4) & 0x0F;
                if (v == m)
                    n = -m;
            }
            if (n == 13)
                n = 1;
            return n;
        }

        /*
         * 计算公历月份
         */
        private int daysInGregorianMonth(int y, int m) {
            int d = daysInGregorianMonth[m - 1];
            if (m == 2 && isGregorianLeapYear(y))
                d++; // 公历闰年二月多一天
            return d;
        }
    }

    /**
     * 星座处理
     *
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
    public static Integer getConstellation(Date date) throws Exception {
        if (null == date)
            throw new NullPointerException("日期参数为空");
        if ((isDateInRange(date, new Date(date.getYear(), 11, 22), new Date(date.getYear(), 11, 31))) || (isDateInRange(date, new Date(date.getYear(), 0, 1), new Date(date.getYear(), 0, 19))))
            return ConstellationConstants.CAPRICORN;
        else if (isDateInRange(date, new Date(date.getYear(), 0, 20), new Date(date.getYear(), 1, 18)))
            return ConstellationConstants.AQUARIUS;
        else if (isDateInRange(date, new Date(date.getYear(), 1, 19), new Date(date.getYear(), 2, 20)))
            return ConstellationConstants.PISCES;
        else if (isDateInRange(date, new Date(date.getYear(), 2, 21), new Date(date.getYear(), 3, 20)))
            return ConstellationConstants.ARIES;
        else if (isDateInRange(date, new Date(date.getYear(), 3, 21), new Date(date.getYear(), 4, 20)))
            return ConstellationConstants.TAURUS;
        else if (isDateInRange(date, new Date(date.getYear(), 4, 21), new Date(date.getYear(), 5, 21)))
            return ConstellationConstants.GEMINI;
        else if (isDateInRange(date, new Date(date.getYear(), 5, 22), new Date(date.getYear(), 6, 22)))
            return ConstellationConstants.CANCER;
        else if (isDateInRange(date, new Date(date.getYear(), 6, 23), new Date(date.getYear(), 7, 22)))
            return ConstellationConstants.LEO;
        else if (isDateInRange(date, new Date(date.getYear(), 7, 23), new Date(date.getYear(), 8, 22)))
            return ConstellationConstants.VIRGO;
        else if (isDateInRange(date, new Date(date.getYear(), 8, 23), new Date(date.getYear(), 9, 22)))
            return ConstellationConstants.LIBRA;
        else if (isDateInRange(date, new Date(date.getYear(), 9, 23), new Date(date.getYear(), 10, 21)))
            return ConstellationConstants.SCORPIO;
        else if (isDateInRange(date, new Date(date.getYear(), 10, 22), new Date(date.getYear(), 11, 21)))
            return ConstellationConstants.ARCHER;
        throw new Exception("星座转换出错");
    }

    /**
     * 判断 date是否大于等于开始日期,小于等于结束日期
     *
     * @param date 指定某个日期
     * @param from 开始日期
     * @param to   结束日期
     * @return
     * @author: zhanghailang
     */
    public static boolean isDateInRange(Date date, Date from, Date to) {
        if ((date.after(from) && date.before(to)) || date.compareTo(from) == 0 || date.compareTo(to) == 0) {
            return true;
        } else
            return false;
    }

    /**
     *
     */
    public static String getLastLoginTimeString(Date lastSignonTime) {
        int day = daysBetween(new Date(), lastSignonTime);
        String lastLoginTime = "";
        if (day <= 1)
            lastLoginTime = "24小时内";
        if (day > 1 && day <= 3)
            lastLoginTime = "3天内";
        else if (day > 3 && day <= 7)
            lastLoginTime = "1个星期内";
        else if (day > 7 && day <= 15)
            lastLoginTime = "半个月内";
        else if (day > 15)
            lastLoginTime = "半月以上";
        return lastLoginTime;
    }

    /**
     * 1. 显示内容为三种：”三日内”，＂一星期＂，＂一个月＂ 2. 规则（计算精度到天）： a) ”三日内”: i. 登陆时间为当前时间3天内的； ii. 登陆时间为当前时间6个月以上的； b) ＂一星期＂ i. 登陆时间为当前时间７天内的； c) ＂一个月＂ i. 登陆时间为当前时间３０天内的； ii. 登陆时间为当前时间6个月内的；
     *
     * @param lastSignonTime 最后登录时间
     * @return String 时间段
     */
    public static String retriverLastTime(Date lastSignonTime) {
        int day = daysBetween(new Date(), lastSignonTime);
        if (day <= 3 || day >= 30 * 6) {
            return "三日内";
        } else if (day <= 7) {
            return "一星期";
        } else {
            return "一个月";
        }
    }

    /**
     * 在日期上添加多几秒并返回日期.
     *
     * @param date
     * @param second
     * @return
     */
    public static Date addSecond(Date date, int second) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, second);
        return cal.getTime();
    }

    /**
     * 在日期上添加多几毫秒秒并返回日期.
     *
     * @param date
     * @param second
     * @return
     */
    public static Date addMilliSecond(Date date, int milliSecond) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MILLISECOND, milliSecond);
        return cal.getTime();
    }

    /**
     * 在日期上添加多几分钟并返回日期.
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date addMinute(Date date, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();
    }

    /**
     * 在日期上添加多几天并返回日期.
     *
     * @param date
     * @param days
     * @return
     */
    public static Date add(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * 在日期上添加多几天并返回日期. 获取这个月的第一天
     *
     * @return Date
     */
    @SuppressWarnings("deprecation")
    public static Date firstDayOfMonth() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.set(date.getYear(), date.getMonth(), 1, 0, 0, 0);
        return cal.getTime();
    }

    /**
     * 在日期上添加多几天并返回日期. 获取这个月的第一天
     *
     * @return Date
     */
    @SuppressWarnings("deprecation")
    public static Date lastDayOfMonth() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.set(date.getYear(), date.getMonth() + 1, 1, 0, 0, 0);
        return cal.getTime();
    }

    /**
     * 在日期上添加多几个月，并返回日期.
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMonth(Date date, int amount) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, amount);
        return cal.getTime();
    }

    public static Date addHour(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);
        return cal.getTime();
    }

    /**
     * 在日期上添加多几天并返回日期.
     *
     * @param date
     * @param days
     * @return
     */
    public static Date subtract(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);
        return cal.getTime();
    }

    /**
     * 年龄小或者大多少岁的日期
     *
     * @param date
     * @param year
     * @return
     */
    public static Date compareAge(Date date, int year) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, year);
        return cal.getTime();
    }

    /**
     * 旧用户截至时间
     *
     * @return Date
     */
    public static Date getOlderUserExpireTime() {
        Calendar startTime = Calendar.getInstance();
        startTime.set(2008, 6, 1);
        return startTime.getTime();
    }

    /**
     * 获取当前时间至当天晚上23:59:59的秒数
     *
     * @return long
     */
    public static int getCurrentTimeToDay() {
        Date d = new Date();
        long currentLongTime = d.getTime();
        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_YEAR), 23, 59, 59);
        long dayMaxLongTime = c.getTime().getTime();
        return (int) ((dayMaxLongTime - currentLongTime) / 1000);
    }

    /**
     * 根据创建时间与当前时间对比返回一个时间标签
     * <p/>
     * 1.发布时间在1小时内的,显示格式为:**分钟前 2.发布时间在10小时内的，显示格式为:**小时前 2.发布时间为本日，1小时前，显示格式为:今天 16:01 3.发布时间为非本日，显示格式为：*月*日 16:09 4.发布时间为非本年，显示格式为：*年*月*日 16:09
     *
     * @param createTime
     * @return String
     */
    public static String getTimelab(Date createTime) {
        String timelab = "";
        if (createTime == null) {
            return timelab;
        }
        Calendar createTimeCal = Calendar.getInstance();
        createTimeCal.setTime(createTime);
        Calendar currCal = Calendar.getInstance();
        int minutes = minutesBetween(createTimeCal.getTime(), currCal.getTime());
        int hours = calHours(createTimeCal.getTime(), currCal.getTime());
        int betweenToday = currCal.get(Calendar.DAY_OF_YEAR) - createTimeCal.get(Calendar.DAY_OF_YEAR);
        int betweenYear = currCal.get(Calendar.YEAR) - createTimeCal.get(Calendar.YEAR);
        // if(logger.isDebugEnabled()) {
        // logger.debug("minutes: " + minutes);
        // logger.debug("hours: " + hours);
        // logger.debug("betweenToday: " + betweenToday);
        // logger.debug("betweenYear: " + betweenYear);
        // }
        if (betweenYear > 0) {
            timelab = parseToFormatDateString(createTime, CHINESE_DATE_TIME);
        } else {
            if (betweenToday > 0) {
                timelab = parseToFormatDateString(createTime, CHINESE_MONTH_TIME);
            } else {
                if (hours > 10) {
                    timelab = "今天" + parseToFormatDateString(createTime, "HH:mm");
                } else {
                    if (minutes > 60) {
                        if (hours <= 0) {
                            timelab = "1小时前";
                        } else {
                            timelab = hours + "小时前";
                        }
                    } else {
                        if (minutes <= 0) {
                            timelab = "1分钟前";
                        } else {
                            timelab = minutes + "分钟前";
                        }
                    }
                }
            }
        }
        return timelab;
    }

    /**
     * 根据创建时间与当前时间对比返回一个时间标签
     * <p/>
     * 1.发布时间在1小时内的,显示格式为:**分钟前 2.发布时间在24小时内的，显示格式为:**小时前 2.发布时间为24小时以上的，显示格式为:*天前
     *
     * @param time
     * @return String
     */
    public static String getTimelab2(long time) {
        time = System.currentTimeMillis() - time;
        long day = time / (60 * 1000 * 60 * 24);
        time = time - day * 60 * 1000 * 60 * 24;
        long hour = time / (60 * 1000 * 60);
        time = time - hour * 60 * 1000 * 60;
        long mi = time / (60 * 1000);
        time = time - mi * 60 * 1000;
        if (day > 0)
            return day + "天前";
        else if (hour > 0)
            return hour + "小时前";
        else if (mi > 0 && mi < 30)
            return mi + "分钟前";
        return "刚刚";
    }

    /**
     * 获取日期
     *
     * @return Date
     */
    public static Date getCurrentTimeByDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 当日凌晨时间，方便基于日期的比较
     *
     * @return Date, 返回的日期
     */
    public static Date getToday() {
        Calendar cld = Calendar.getInstance();
        cld.set(Calendar.HOUR_OF_DAY, 0);
        cld.set(Calendar.MINUTE, 0);
        cld.set(Calendar.SECOND, 0);
        cld.set(Calendar.MILLISECOND, 0);
        return cld.getTime();
    }

    /**
     * 返回当前截止时间:*年*月*日 23:59:59:999
     *
     * @return
     */
    public static Date getTodayEnd() {
        Calendar cld = Calendar.getInstance();
        cld.set(Calendar.HOUR_OF_DAY, 23);
        cld.set(Calendar.MINUTE, 59);
        cld.set(Calendar.SECOND, 59);
        cld.set(Calendar.MILLISECOND, 999);
        return cld.getTime();
    }

    /**
     * 获取当前日期的前后时间
     *
     * @param days
     * @return
     */
    public static Date getTodayUpDown(int days) {
        Calendar cld = Calendar.getInstance();
        cld.add(Calendar.DAY_OF_MONTH, days);
        cld.set(Calendar.HOUR_OF_DAY, 0);
        cld.set(Calendar.MINUTE, 0);
        cld.set(Calendar.SECOND, 0);
        cld.set(Calendar.MILLISECOND, 0);
        return cld.getTime();
    }

    /**
     * 某年某月的第一天
     *
     * @param year  int 年份
     * @param month int 月份
     */
    public static Date getFristDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 某年某月的第一天 到毫秒
     *
     * @param year  int 年份
     * @param month int 月份
     */
    public static Date getFristTimeOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 某年某月的最后一天
     *
     * @param year  int 年份
     * @param month int 月份
     */
    public static Date getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 某年某月的最后一天最后一刻
     *
     * @param year  int 年份
     * @param month int 月份
     */
    public static Date getLastTimeOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        //修正毫秒级偏差
        //SQL SERVER中DATETIME表示的时间为00:00:00到23:59:59.997，它的时间精度为1/300秒，在使用时会舍入到舍入到 .000、.003 或 .007 秒三个增量
        cal.set(Calendar.MILLISECOND, 997);
        return cal.getTime();
    }

    /**
     * 获取指定时间差的日期
     *
     * @param date date 基准日期
     * @param day  相差天数
     * @return Date, 返回的日期
     */
    public static Date addDay(Date date, int day) {
        Date result = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        result = new Date(cal.getTime().getTime());
        return result;
    }

    /**
     * 获取指定时间差的日期
     *
     * @param date  基准日期
     * @param month 相差月数
     * @return Date, 返回的日期
     */
    public static Date addMonth2(Date date, int month) {
        Date result = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);
        result = new Date(cal.getTime().getTime());
        return result;
    }

    /**
     * 获取指定时间差的日期
     *
     * @param date 基准日期
     * @param year 相差年数
     * @return Date, 返回的日期
     */
    public static Date addYear(Date date, int year) {
        Date result = null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, year);
        result = new Date(cal.getTime().getTime());
        return result;
    }

    /**
     * 日期格式化
     *
     * @param v
     * @param fm  格式化标示符 例如:yyyy-MM-dd 或者 yyyy-MM-dd HH:mm:ss
     * @param def
     * @return Date, 返回的日期
     */
    public static Date dateValue(String v, String fm, Date def) {
        if (v == null || v.length() == 0)
            return def;
        try {
            return new SimpleDateFormat(fm).parse(v.trim());
        } catch (Exception e) {
            return def;
        }
    }

    /**
     * 生成格式化日期 yyyy-MM-dd
     *
     * @param def
     * @return Date, 返回的日期
     */
    public static Date dateValue(String v, Date def) {
        return dateValue(v, "yyyy-MM-dd", def);
    }

    /**
     * 生成格式化日期 yyyy-MM-dd HH:mm:ss
     *
     * @param def
     * @return Date, 返回的日期
     */
    public static Date datetimeValue(String v, Date def) {
        return dateValue(v, DEFAULT_DATE_TIME, def);
    }

    /**
     * 输出格式化日期 yyyy-MM-dd HH:mm:ss
     *
     * @param def
     * @return
     */
    public static String timeString(Date def) {
        if (def == null) return "";
        return dateStringAll(DEFAULT_DATE_TIME, def);
    }
    /**
     * 输出格式化日期 yyyy-MM-dd
     * @param def 要格式化输出的日期
     * @return String, 返回格式化的日期字符串
     */
    /**
     * 输出格式化日期 yyyy-MM-dd
     *
     * @param def 要格式化输出的日期
     * @return String, 返回格式化的日期字符串
     */
    public static String dateString(Date def) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_TIME);
        return sdf.format(def).substring(0, 10);
    }

    public static String dateString(String fm, Date def) {
        SimpleDateFormat sdf = new SimpleDateFormat(fm);
        return sdf.format(def).substring(0, 10);
    }

    /**
     * 输出格式化日期 yyyy年MM月dd日
     *
     * @param def 要格式化输出的日期
     * @return String, 返回格式化的日期字符串
     */
    public static String dateStringChinese(String fm, Date def) {
        SimpleDateFormat sdf = new SimpleDateFormat(fm);
        return sdf.format(def).substring(0, 11);
    }

    public static String dateStringAll(String fm, Date def) {
        SimpleDateFormat sdf = new SimpleDateFormat(fm);
        return sdf.format(def);
    }

    public static String currentDateStr() {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_TIME);
        return sdf.format(new Date());
    }

    public static String formatAfterParse(String dateStr, String parseFormat, String format) {
        dateStr = StringUtil.left(dateStr, '0', 6);
        try {
            Date date = new SimpleDateFormat(parseFormat).parse(dateStr);
            return new SimpleDateFormat(format).format(date);
        } catch (ParseException e) {
            logger.error(e);
        }
        return "";
    }

    public static String formatDate(String dateStr, String outFormat) {
        try {
            Date date = new SimpleDateFormat("yyyyMMdd").parse(dateStr);
            return new SimpleDateFormat(outFormat).format(date);
        } catch (ParseException e) {
            logger.error(e);
        }
        return "";
    }

    public static String formatTime(String dateStr, String outFormat) {
        Date date = parseTime(dateStr);
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(outFormat).format(date);
    }

    /**
     * 解析奖期的买彩时间
     *
     * @param dateStr 买彩时间字符串
     * @return 若解析失败，返回null
     */
    public static Date parseTime(String dateStr) {
        dateStr = StringUtil.left(dateStr, '0', 6);
        try {
            return new SimpleDateFormat("HHmmss").parse(dateStr);
        } catch (ParseException e) {
            logger.error(e);
        }
        return null;
    }

    /**
     * 输出格式化日期 yyyy-MM-dd 00:00:00
     *
     * @param def 要格式化输出的日期
     * @return String, 返回格式化的日期字符串
     */
    public static Date dateStart(Date def) {
        Calendar calo = Calendar.getInstance();
        calo.setTime(def);
        Calendar cal = Calendar.getInstance();
        cal.set(calo.get(Calendar.YEAR), calo.get(Calendar.MONTH), calo.get(Calendar.DATE), 0, 0, 0);
        //修正毫秒级偏差 modified by lujun
        cal.set(Calendar.MILLISECOND, 0);
        return new Date(cal.getTime().getTime());
    }

    /**
     * 输出格式化日期 yyyy-MM-dd 23:59:59
     *
     * @param def 要格式化输出的日期
     * @return String, 返回格式化的日期字符串
     */
    public static Date dateEnd(Date def) {
        Calendar calo = Calendar.getInstance();
        calo.setTime(def);
        Calendar cal = Calendar.getInstance();
        cal.set(calo.get(Calendar.YEAR), calo.get(Calendar.MONTH), calo.get(Calendar.DATE), 23, 59, 59);
        //修正毫秒级偏差 modified by lujun
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 输出格式化日期到秒
     *
     * @param def 要格式化输出的日期
     * @return String, 返回格式化的日期字符串
     */
    public static Date second(Date def) {
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // return toDefaultDateTime(sdf.format(def).substring(0, 10) + " 00:00:00");
        Calendar calo = Calendar.getInstance();
        calo.setTime(def);
        Calendar cal = Calendar.getInstance();
        cal.set(calo.get(Calendar.YEAR), calo.get(Calendar.MONTH), calo.get(Calendar.DATE), calo.get(Calendar.HOUR_OF_DAY), calo.get(Calendar.MINUTE), calo.get(Calendar.SECOND));
        return new Date(cal.getTime().getTime());
    }

    /**
     * 转换时间说明
     *
     * @param time
     * @return
     */
    public static String getTimeDesc(long time) {
        time = System.currentTimeMillis() - time;
        long day = time / (60 * 1000 * 60 * 24);
        time = time - day * 60 * 1000 * 60 * 24;
        long hour = time / (60 * 1000 * 60);
        time = time - hour * 60 * 1000 * 60;
        long mi = time / (60 * 1000);
        time = time - mi * 60 * 1000;
        if (day > 0)
            return day + "天前";
        else if (hour > 0)
            return hour + "小时前";
        else if (mi > 0 && mi < 30)
            return mi + "分钟前";
        return "刚刚";
    }

    /**
     * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
     * @author fy.zhang
     */
    public static String formatDuring(long mss) {
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
        String[] daojishi = new String[]{"天", "小时", "分", "秒"};
        if (days == 0 && hours == 0 && minutes == 0) {
            return minutes + daojishi[2] + seconds + daojishi[3];
        }
        if (days == 0 && hours == 0) {
            return minutes + daojishi[2] + seconds + daojishi[3];
        }
        if (days == 0) {
            return hours + daojishi[1] + minutes + daojishi[2];
        }
        return days + daojishi[0] + hours + daojishi[1];
    }

    public static String getWeekStr(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return weeks[dayOfWeek - 1];
    }

    public static Integer getWeekNumber(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek - 1;
    }

    public static String getWeekDay(Integer date) {
        Date week = null;
        try {
            week = toUtilDate(date.toString(), "yyyyMMdd");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getWeekStr(week);
    }

    /**
     * 重置一个时间
     *
     * @param time
     * @param hour
     * @param minute
     * @param second
     * @param millisecond
     * @return
     */
    public static Date resetTime(Date time, int hour, int minute, int second, int millisecond) {
        return resetTime(time.getTime(), hour, minute, second, millisecond);
    }

    public static Date resetTime(long millis, int hour, int minute, int second, int millisecond) {
        Calendar ca = Calendar.getInstance();
        ca.setTimeInMillis(millis);
        ca.set(Calendar.HOUR_OF_DAY, hour);
        ca.set(Calendar.MINUTE, minute);
        ca.set(Calendar.SECOND, second);
        ca.set(Calendar.MILLISECOND, millisecond);
        return ca.getTime();
    }

    /**
     * 更具日期获取路径
     *
     * @param basePath
     * @return
     */
    public static String getFilePathByDate(String basePath) {
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        StringBuilder filePath = new StringBuilder();
        filePath.append(basePath).append(year).append("/").append(month).append("/").append(day).append("/");
        return filePath.toString();
    }

    public static void main(String[] args) {
        // System.out.println(formatDuring(100000000L));
        System.out.print(currentDateStr());
    }
}
