package com.music.api;

/**
 * @author xiep
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}( API接口错误码和错误信息, 注意：这是给api接口的错误信息，错误信息不能太详细！)
 * @date ${date} ${time}
 */
public enum APIServiceCode {

    /* 成功状态码 */
    SUCCESS(1, "成功"),
    ERROR(0, "失败"),

    /*总日志类型*/
    SYSTEM_MANAGE(1,"系统管理"),
    VIDEO_MANAGE(2,"实时预览"),
    VIDEO_CALLBACK_MANAGE(3,"录像管理"),
    SERVICE_MANAGE(4,"服务管理"),
    PRIVILEGE_MANAGE(6,"权限管理"),
    DEVICE_MANAGE(7,"设备管理"),
    LOG_MANAGE(8,"日志管理"),
    DECODER_MANAGE(9,"解码上墙"),
    CONNECT_INTERNET_MANAGE(10,"联网管理"),
    OUTSIDE_MEDIA(11,"对外媒体"),
    OUTSIDE_CLIENT(12,"对外客户端"),
    OUTSIDE_OTHER(13,"对外其它"),
    DB_MANAGE(14,"数据库管理"),
    OTHER_MANAGE(15,"其它"),



    /*系统管理 ：1001-1999 */
    SYSTEM_INNER_ERROR(1001, "系统繁忙，请稍后重试"),
    SYSTEM_PARAM_IS_INVALID(1002, "请求路径无效"),
    SYSTEM_PARAM_IS_BLANK(1003, "请求参数为空"),
    SYSTEM_PARAM_TYPE_BIND_ERROR(1004, "请求参数类型错误"),
    SYSTEM_PARAM_NOT_COMPLETE(1005, "请求参数缺失"),
    SYSTEM_METHOD_NOT_TOKEN(1006, "认证失败，请重新登陆"),
    SYSTEM_USER_ONLINE_ERROR(1007, "登录未获取到登录信息"),
    SYSTEM_LOGIN_SUCCESS(1008, "登录成功"),
    SYSTEM_LOGINOUT_SUCCESS(1009, "退出登录成功"),
    SYSTEM_COMPEL_LOGINOUT(1010, "用户强制下线"),
    SYSTEM_NOT_DOWNLOAD(1011, "下载文件不存在"),
    SYSTEM_DOWNLOAD_ERROR(1012, "下载异常"),
    SYSTEM_GATEWAY_NOT_SET(1013, "网闸信息未配置"),
    SYSTEM_METHOD_NOT_SUPPORTED(1014, "请求方式有误，请确认POST或GET"),
    UPLOAD_FILE_EXIST(1015,"该文件已经存在"),
    PARAM_NOT_COMPLETE(10004, "请求参数缺失"),


    /*权限管理 ：6001-6999 */
    PRIVILEGE_ADD_USER(6001, "新增用户"),
    PRIVILEGE_EDIT_USER(6002, "修改用户"),
    PRIVILEGE_DEL_USER(6003, "删除用户"),
    PRIVILEGE_USER_NOT_LOGGED_IN(6004, "用户未登录"),
    PRIVILEGE_USER_LOGIN_ERROR(6005, "账号不存在或密码错误"),
    PRIVILEGE_USER_ACCOUNT_FORBIDDEN(6006, "账号已被禁用"),
    PRIVILEGE_USER_NOT_EXIST(6007, "用户不存在"),
    PRIVILEGE_USER_HAS_EXISTED(6008, "用户已存在"),
    PRIVILEGE_USER_NOT_PERMISSION(6009, "权限不足"),
    PRIVILEGE_ADD_ROLE(6010, "新增角色"),
    PRIVILEGE_EDIT_ROLE(6011, "修改角色"),
    PRIVILEGE_DEL_ROLE(6012, "删除角色"),
    PRIVILEGE_ROLE_NOT_EXIST(6013, "角色不存在"),
    PRIVILEGE_ROLE_HAS_EXISTED(6014, "角色已存在"),


    ;


    public static void  main (String[] args ){

        System.out.print(APIServiceCode.SUCCESS);

    }



    private Integer code;
    private String message;

    APIServiceCode(Integer code, String message) {
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
