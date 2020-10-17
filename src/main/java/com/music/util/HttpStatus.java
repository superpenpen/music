package com.music.util;

/**
 * @author zkk
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}( API接口错误码和错误信息, 注意：这是给api接口的错误信息，错误信息不能太详细！)
 * @date ${date} ${time}
 */
public enum HttpStatus {

    /* 成功状态码 */
    SUCCESS(0, "成功"),
    ERROR(1, "失败"),

    /*系统管理 ：1001-1999 */
    SYSTEM_INNER_ERROR(1001, "系统繁忙，请稍后重试"),
    SYSTEM_PARAM_IS_INVALID(1002, "请求参数无效"),
    SYSTEM_PARAM_IS_BLANK(1003, "请求参数为空"),
    SYSTEM_PARAM_TYPE_BIND_ERROR(1004, "请求参数类型错误"),
    SYSTEM_PARAM_NOT_COMPLETE(1005, "请求参数缺失"),
    SYSTEM_PARAM_IS_ERROR(1006, "请求参数错误"),
    SYSTEM_METHOD_NOT_TOKEN(1007, "认证失败，请重新登陆"),
    SYSTEM_USER_NOT_EXISTS(1008, "登录失败，账号不存在"),
    SYSTEM_USER_PASSWORD_ERROR(1009, "登录失败，密码错误"),

    /* 用户管理 */
    USER_NAME_EXISTS(2001, "账号已存在，请重试"),





    ;


    public static void  main (String[] args ){

        System.out.print(HttpStatus.SUCCESS);

    }



    private Integer code;
    private String message;

    HttpStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
