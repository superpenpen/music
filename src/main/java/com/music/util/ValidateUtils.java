package com.music.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {

    /**
     * 判断IP格式和范围
     */
    public static boolean isIP(String addr)
    {
        if(addr.length() < 7 || addr.length() > 15 || "".equals(addr)) {
            return false;
        }
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(addr);
        return mat.find();
    }

    /**
     * 判断是否为纯数字
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 判断设备类型是否为 0,1,2
     * @param str
     * @return
     */
    public static boolean validateDeviceType(String str){
        String regex = "^[0-2]{1}$";
        return Pattern.matches(regex,str);
    }

    /**
     * 判断对外国标ID是否为20位数字
     * @param str
     * @return
     */
    public static boolean validateGbid(String str){
        String regex = "^\\d{20}$";
        return Pattern.matches(regex,str);
    }

    /**
     * 判断是否类型只能是0或1
     * @param str
     * @return
     */
    public static boolean validateYesno(String str){
        String regex = "^[0-1]{1}$";
        return Pattern.matches(regex,str);
    }

    /**
     * 判断是否是正整数
     */
    public static  boolean validateNum(String str){
        String regex = "^[1-9]\\d*$";
        return Pattern.matches(regex,str);
    }

    /**
     * IP
     */
    public static  boolean validateIp(String str){
        String regex = "((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))$";
        return Pattern.matches(regex,str);
    }

    /**
     *  手机号
     */
    public static  boolean validatemobilePhone(String str){
        String regex = "^1[3456789]\\d{9}$";
        return Pattern.matches(regex,str);
    }

    /**
     *  座机
     */
    public static  boolean validatePhone(String str){
        String regex = "^((0\\d{2,3}-\\d{7,8})|(1[3584]\\d{9}))$";
        return Pattern.matches(regex,str);
    }

    /**
     *  邮箱
     */
    public static  boolean validateEmail(String str){
        String regex = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        return Pattern.matches(regex,str);
    }

    /**
     * 经纬度校验
     * 经度longitude: -180.000000-180.000000
     * 纬度latitude：-90.000000-90.000000
     * @return
     */
    public static boolean checkLng(String longitude){
        String reglng = "^(\\-|\\+)?(((\\d|[1-9]\\d|1[0-7]\\d|0{1,3})\\.\\d{0,6})|(\\d|[1-9]\\d|1[0-7]\\d|0{1,3})|180\\.0{0,6}|180)$";
        longitude = longitude.trim();
        return Pattern.matches(reglng, longitude);
    }

    public static boolean checkLat(String latitude){
        String reglat = "^(\\-|\\+)?([0-8]?\\d{1}\\.\\d{0,6}|90\\.0{0,6}|[0-8]?\\d{1}|90)$";
        latitude = latitude.trim();
        return Pattern.matches(reglat, latitude);
    }

}
