package com.music.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author zkk
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}( 时间帮助类 )
 * @date ${date} ${time}
 */
public class DateUtils {

    private static final String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};

    public final static SimpleDateFormat formart1 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    /**
     * 根据指定格式获取当前时间
     * @param format
     * @return String
     */
    public static String getCurrentTime(String format){
        SimpleDateFormat sdf = DateFormatUtils.getFormat(format);
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * 获取当前时间，格式为：yyyy-MM-dd HH:mm:ss
     * @return String
     */
    public static String getCurrentTime(){
        return getCurrentTime(DateFormatUtils.DATE_FORMAT2);
    }

    public static String getTCurrentTime(){
        return getCurrentTime(DateFormatUtils.DATE_FORMAT_T);
    }

    /**
     * 获取当前时间，格式为：yyyy-MM-dd
     * @return String
     */
    public static String getCurrentDay(){
        return getCurrentTime(DateFormatUtils.DATE_FORMAT1);
    }


    /**
     * 获取指定格式的当前时间：为空时格式为yyyy-mm-dd HH:mm:ss
     * @param format
     * @return Date
     */
    public static Date getCurrentDate(String format){
        SimpleDateFormat sdf = DateFormatUtils.getFormat(format);
        String dateS = getCurrentTime(format);
        Date date = null;
        try {
            date = sdf.parse(dateS);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取当前时间，格式为yyyy-MM-dd HH:mm:ss
     * @return Date
     */
    public static Date getCurrentDate(){
        return getCurrentDate(DateFormatUtils.DATE_FORMAT2);
    }

    /**
     * 给指定日期加入年份，为空时默认当前时间
     * @param year 年份  正数相加、负数相减
     * @param date 为空时，默认为当前时间
     * @param format 默认格式为：yyyy-MM-dd HH:mm:ss
     * @return String
     */
    public static String addYearToDate(int year,Date date,String format){
        Calendar calender = getCalendar(date,format);
        SimpleDateFormat sdf = DateFormatUtils.getFormat(format);

        calender.add(Calendar.YEAR, year);

        return sdf.format(calender.getTime());
    }

    /**
     * 给指定日期加入年份，为空时默认当前时间
     * @param year 年份  正数相加、负数相减
     * @param date 为空时，默认为当前时间
     * @param format 默认格式为：yyyy-MM-dd HH:mm:ss
     * @return String
     */
    public static String addYearToDate(int year,String date,String format){
        Date newDate = new Date();
        if(null != date && !"".equals(date)){
            newDate = string2Date(date, format);
        }

        return addYearToDate(year, newDate, format);
    }

    /**
     * 给指定日期增加月份 为空时默认当前时间
     * @param month  增加月份  正数相加、负数相减
     * @param date 指定时间
     * @param format 指定格式 为空默认 yyyy-mm-dd HH:mm:ss
     * @return String
     */
    public static String addMothToDate(int month,Date date,String format) {
        Calendar calender = getCalendar(date,format);
        SimpleDateFormat sdf = DateFormatUtils.getFormat(format);

        calender.add(Calendar.MONTH, month);

        return sdf.format(calender.getTime());
    }

    /**
     * 给指定日期增加月份 为空时默认当前时间
     * @param month  增加月份  正数相加、负数相减
     * @param date 指定时间
     * @param format 指定格式 为空默认 yyyy-mm-dd HH:mm:ss
     * @return String
     */
    public static String addMothToDate(int month,String date,String format) {
        Date newDate = new Date();
        if(null != date && !"".equals(date)){
            newDate = string2Date(date, format);
        }

        return addMothToDate(month, newDate, format);
    }

    /**
     * 给指定日期增加天数，为空时默认当前时间
     * @param day 增加天数 正数相加、负数相减
     * @param date 指定日期
     * @param format 日期格式 为空默认 yyyy-mm-dd HH:mm:ss
     * @return String
     */
    public static String addDayToDate(int day,Date date,String format) {
        Calendar calendar = getCalendar(date, format);
        SimpleDateFormat sdf = DateFormatUtils.getFormat(format);

        calendar.add(Calendar.DATE, day);

        return sdf.format(calendar.getTime());
    }

    /**
     * 给指定日期增加天数，为空时默认当前时间
     * @param day 增加天数 正数相加、负数相减
     * @param date 指定日期
     * @param format 日期格式 为空默认 yyyy-mm-dd HH:mm:ss
     * @return String
     */
    public static String addDayToDate(int day,String date,String format) {
        Date newDate = new Date();
        if(null != date && !"".equals(date)){
            newDate = string2Date(date, format);
        }

        return addDayToDate(day, newDate, format);
    }

    /**
     * 给指定日期增加小时，为空时默认当前时间
     * @param hour 增加小时  正数相加、负数相减
     * @param date 指定日期
     * @param format 日期格式 为空默认 yyyy-mm-dd HH:mm:ss
     * @return String
     */
    public static String addHourToDate(int hour,Date date,String format) {
        Calendar calendar = getCalendar(date, format);
        SimpleDateFormat sdf = DateFormatUtils.getFormat(format);

        calendar.add(Calendar.HOUR, hour);

        return sdf.format(calendar.getTime());
    }

    /**
     * 给指定日期增加小时，为空时默认当前时间
     * @param hour 增加小时  正数相加、负数相减
     * @param date 指定日期
     * @param format 日期格式 为空默认 yyyy-mm-dd HH:mm:ss
     * @return String
     */
    public static String addHourToDate(int hour,String date,String format) {
        Date newDate = new Date();
        if(null != date && !"".equals(date)){
            newDate = string2Date(date, format);
        }

        return addHourToDate(hour, newDate, format);
    }

    /**
     * 给指定的日期增加分钟，为空时默认当前时间
     * @param minute 增加分钟  正数相加、负数相减
     * @param date 指定日期
     * @param format 日期格式 为空默认 yyyy-mm-dd HH:mm:ss
     * @return String
     */
    public static String addMinuteToDate(int minute,Date date,String format) {
        Calendar calendar = getCalendar(date, format);
        SimpleDateFormat sdf = DateFormatUtils.getFormat(format);

        calendar.add(Calendar.MINUTE, minute);

        return sdf.format(calendar.getTime());
    }

    /**
     * 给指定的日期增加分钟，为空时默认当前时间
     * @param minute 增加分钟  正数相加、负数相减
     * @param date 指定日期
     * @param format 日期格式 为空默认 yyyy-mm-dd HH:mm:ss
     * @return String
     */
    public static String addMinuteToDate(int minute,String date,String format){
        Date newDate = new Date();
        if(null != date && !"".equals(date)){
            newDate = string2Date(date, format);
        }

        return addMinuteToDate(minute, newDate, format);
    }

    /**
     * 给指定日期增加秒，为空时默认当前时间
     * @param second 增加秒 正数相加、负数相减
     * @param date 指定日期
     * @param format 日期格式 为空默认 yyyy-mm-dd HH:mm:ss
     * @return String
     */
    public static String addSecondToDate(int second,Date date,String format){
        Calendar calendar = getCalendar(date, format);
        SimpleDateFormat sdf = DateFormatUtils.getFormat(format);

        calendar.add(Calendar.SECOND, second);

        return sdf.format(calendar.getTime());
    }

    /**
     * @param second 增加秒 正数相加、负数相减
     * @param date 指定日期
     * @param format 日期格式 为空默认 yyyy-mm-dd HH:mm:ss
     * @return String
     * @throws Exception
     */
    public static String addSecondToDate(int second,String date,String format){
        Date newDate = new Date();
        if(null != date && !"".equals(date)){
            newDate = string2Date(date, format);
        }

        return addSecondToDate(second, newDate, format);
    }

    /**
     * 获取指定格式指定时间的日历
     * @param date 时间
     * @param format 格式
     * @return Calendar
     */
    public static Calendar getCalendar(Date date,String format){
        if(date == null){
            date = getCurrentDate(format);
        }

        Calendar calender = Calendar.getInstance();
        calender.setTime(date);

        return calender;
    }

    /**
     * 字符串转换为日期，日期格式为
     *
     *
     * @param value
     * @return
     */
    public static Date string2Date(String value){
        if(value == null || "".equals(value)){
            return null;
        }

        SimpleDateFormat sdf = DateFormatUtils.getFormat(DateFormatUtils.DATE_FORMAT2);
        Date date = null;

        try {
            value = DateFormatUtils.formatDate(value, DateFormatUtils.DATE_FORMAT2);
            date = sdf.parse(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将字符串(格式符合规范)转换成Date
     * @param value 需要转换的字符串
     * @param format 日期格式
     * @return Date
     */
    public static Date string2Date(String value,String format){
        if(value == null || "".equals(value)){
            return null;
        }

        SimpleDateFormat sdf = DateFormatUtils.getFormat(format);
        Date date = null;

        try {
//            value = DateFormatUtils.formatDate(value, format);
            date = sdf.parse(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将日期格式转换成String
     *
     * @param value 需要转换的日期
     * @param format 日期格式
     * @return String
     */
    public static String date2String(Date value,String format){
        if(value == null){
            return null;
        }

        SimpleDateFormat sdf = DateFormatUtils.getFormat(format);
        return sdf.format(value);
    }

    /**
     * 日期转换为字符串
     *
     * @param value
     * @return
     */
    public static String date2String(Date value){
        if(value == null){
            return null;
        }

        SimpleDateFormat sdf = DateFormatUtils.getFormat(DateFormatUtils.DATE_FORMAT2);
        return sdf.format(value);
    }

    /**
     * 获取指定日期的年份
     * @param value 日期
     * @return int
     */
    public static int getCurrentYear(Date value){
        String date = date2String(value, DateFormatUtils.DATE_YEAR);
        return Integer.valueOf(date);
    }

    /**
     * 获取指定日期的年份
     * @param value 日期
     * @return int
     */
    public static int getCurrentYear(String value) {
        Date date = string2Date(value, DateFormatUtils.DATE_YEAR);
        Calendar calendar = getCalendar(date, DateFormatUtils.DATE_YEAR);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取指定日期的月份
     * @param value 日期
     * @return int
     */
    public static int getCurrentMonth(Date value){
        String date = date2String(value, DateFormatUtils.DATE_MONTH);
        return Integer.valueOf(date);
    }

    /**
     * 获取指定日期的月份
     * @param value 日期
     * @return int
     */
    public static int getCurrentMonth(String value) {
        Date date = string2Date(value, DateFormatUtils.DATE_MONTH);
        Calendar calendar = getCalendar(date, DateFormatUtils.DATE_MONTH);

        return calendar.get(Calendar.MONTH);
    }

    /**
     * 获取指定日期的天份
     * @param value 日期
     * @return int
     */
    public static int getCurrentDay(Date value){
        String date = date2String(value, DateFormatUtils.DATE_DAY);
        return Integer.valueOf(date);
    }

    /**
     * 获取指定日期的天份
     * @param value 日期
     * @return int
     */
    public static int getCurrentDay(String value){
        Date date = string2Date(value, DateFormatUtils.DATE_DAY);
        Calendar calendar = getCalendar(date, DateFormatUtils.DATE_DAY);

        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取当前日期为星期几
     * @param value 日期
     * @return String
     */
    public static String getCurrentWeek(Date value) {
        Calendar calendar = getCalendar(value, DateFormatUtils.DATE_FORMAT1);
        int weekIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1 < 0 ? 0 : calendar.get(Calendar.DAY_OF_WEEK) - 1;

        return weeks[weekIndex];
    }

    /**
     * 获取当前日期为星期几
     * @param value 日期
     * @return String
     */
    public static String getCurrentWeek(String value) {
        Date date = string2Date(value, DateFormatUtils.DATE_FORMAT1);
        return getCurrentWeek(date);
    }

    /**
     * 获取指定日期的小时
     * @param value 日期
     * @return int
     */
    public static int getCurrentHour(Date value){
        String date = date2String(value, DateFormatUtils.DATE_HOUR);
        return Integer.valueOf(date);
    }

    /**
     * 获取指定日期的小时
     * @param value 日期
     * @return
     * @return int
     */
    public static int getCurrentHour(String value) {
        Date date = string2Date(value, DateFormatUtils.DATE_HOUR);
        Calendar calendar = getCalendar(date, DateFormatUtils.DATE_HOUR);

        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取指定日期的分钟
     * @param value 日期
     * @return int
     */
    public static int getCurrentMinute(Date value){
        String date = date2String(value, DateFormatUtils.DATE_MINUTE);
        return Integer.valueOf(date);
    }

    /**
     * 获取指定日期的分钟
     * @param value 日期
     * @return int
     */
    public static int getCurrentMinute(String value){
        Date date = string2Date(value, DateFormatUtils.DATE_MINUTE);
        Calendar calendar = getCalendar(date, DateFormatUtils.DATE_MINUTE);

        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 比较两个日期相隔多少天(月、年) <br>
     * 例：<br>
     * &nbsp;compareDate("2009-09-12", null, 0);//比较天 <br>
     * &nbsp;compareDate("2009-09-12", null, 1);//比较月 <br>
     * &nbsp;compareDate("2009-09-12", null, 2);//比较年 <br>
     *
     * @param startDay 需要比较的时间 不能为空(null),需要正确的日期格式 ,如：2009-09-12
     * @param endDay 被比较的时间  为空(null)则为当前时间
     * @param stype 返回值类型   0为多少天，1为多少个月，2为多少年
     * @return int
     */
    public static int compareDate(String startDay,String endDay,int stype) {
        int n = 0;
        startDay = DateFormatUtils.formatDate(startDay, "yyyy-MM-dd");
        endDay = DateFormatUtils.formatDate(endDay, "yyyy-MM-dd");

        String formatStyle = "yyyy-MM-dd";
        if(1 == stype){
            formatStyle = "yyyy-MM";
        }else if(2 == stype){
            formatStyle = "yyyy";
        }

        endDay = endDay==null ? getCurrentTime("yyyy-MM-dd") : endDay;

        DateFormat df = new SimpleDateFormat(formatStyle);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(startDay));
            c2.setTime(df.parse(endDay));
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (!c1.after(c2)) {                   // 循环对比，直到相等，n 就是所要的结果
            n++;
            if(stype==1){
                c1.add(Calendar.MONTH, 1);          // 比较月份，月份+1
            }
            else{
                c1.add(Calendar.DATE, 1);           // 比较天数，日期+1
            }
        }
        n = n-1;
        if(stype==2){
            n = (int)n/365;
        }
        return n;
    }

    /**
     * 比较两个时间相差多少小时(分钟、秒)
     * @param startTime 需要比较的时间 不能为空，且必须符合正确格式：2012-12-12 12:12:
     * @param endTime 需要被比较的时间 若为空则默认当前时间
     * @return int
     */
    public static long compareTime(String startTime , String endTime ) {
        //endTime是否为空，为空默认当前时间
        if(endTime == null || "".equals(endTime)){
            endTime = getCurrentTime();
        }

        SimpleDateFormat sdf = DateFormatUtils.getFormat("");
        long value = 0;
        try {
            Date begin = sdf.parse(startTime);
            Date end = sdf.parse(endTime);
            value = (end.getTime() - begin.getTime()) /1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 比较两个日期的大小。<br>
     * 若date1 > date2 则返回 1<br>
     * 若date1 = date2 则返回 0<br>
     * 若date1 < date2 则返回-1
     *
     * @param date1
     * @param date2
     * @param format  待转换的格式
     * @return 比较结果
     */
    public static int compare(String date1, String date2,String format) {
        DateFormat df = DateFormatUtils.getFormat(format);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param dateStr 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String Date2TimeStamp(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 日期格式字符串转换成 long形式的时间戳  单位 : 秒
     * @param dateStr
     * @param format
     * @return
     */
    public static long Date2TimeStampLong(String dateStr, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(dateStr).getTime() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  0;
    }

    /**
     * 日期格式由 yyyy-MM-dd*HH:mm:ss 转换为 yyyy-MM-dd'T'HH:mm:ss
     * @return
     */
    public static String dateToDateT(String  fileName ){

        try{
            SimpleDateFormat sdfM = new SimpleDateFormat(DateFormatUtils.DATE_FORMAT_M);
            SimpleDateFormat sdfT = new SimpleDateFormat(DateFormatUtils.DATE_FORMAT_T);
            Date date = sdfM.parse(fileName);
            return  sdfT.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 根据客户端发的时间 2018-08-10T10:15:00 转换为  2018-08-10
     * @param starttime
     * @return
     */
    public static String  TdateToTime(String  starttime ){

        try{
            SimpleDateFormat sdfD = new SimpleDateFormat(DateFormatUtils.DATE_FORMAT1);
            Date date= sdfD.parse(starttime);
            return  sdfD.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }
       return  "";
    }



    /**
     * 获取指定月份的第一天
     *
     * @author : chenssy
     * @date : 2016年5月31日 下午5:31:10
     *
     * @param date
     * @return
     */
    public static String getMonthFirstDate(String date){
        date = DateFormatUtils.formatDate(date);
        return DateFormatUtils.formatDate(date, "yyyy-MM") + "-01";
    }

    /**
     * 获取指定月份的最后一天
     *
     *
     * @return
     */
    public static String getMonthLastDate(String date) {
        Date strDate = DateUtils.string2Date(getMonthFirstDate(date));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(strDate);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return DateFormatUtils.formatDate(calendar.getTime());
    }

    /**
     * 获取今天周几
     * @return
     */
    public static String getDayofWeek(){
        String str[] = {  "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return str[day-1];
    }

    /**
     * 计算两个时间之间相隔秒数
     * @param startdayTime  开始时间
     * @param enddayTime 结束时间
     * @return
     */
    public static int getIntervalSeconds(String  startdayTime,String enddayTime){

        Date one;
        Date two;
        long ei=0;
        long days=0;
        int c =0;
        if( "".equals(enddayTime)||"".equals(enddayTime)
                ||null ==  enddayTime || null ==enddayTime  ){
            return 600;
        }
        try {
            one = formart1.parse(startdayTime);
            two = formart1.parse(enddayTime);
            long time1 = one.getTime();
            long time2 = two.getTime();
            //根据毫秒数计算间隔秒数
            c= (int)((time1 - time2) / 1000);
        }catch (Exception e) {
            return 600;
            // TODO: handle exception
        }
        return c;
    }

}
