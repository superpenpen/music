package com.music.util;

/**
 * @ClassName APIResponse
 * @Description 接口返回格式
 * @Author xiep
 * @Date 2020/2/4 14:23
 **/
public class HttpResult<T> {

    /**
     * 状态码
     * 1  表示成功
     * 其他表示失败
     */
    private int code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回信息
     */
    private T data;


    //当正确时返回的值
    public static HttpResult ok(Object data) {

        HttpResult result = new HttpResult();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    //当正确时返回的值
    public static HttpResult okWithoutData() {
        HttpResult result = new HttpResult();
        result.setCode(0);
        result.setMsg("成功");
        return result;
    }

    //当错误时返回的值
    public static HttpResult error(int code, String msg) {
        HttpResult result = new HttpResult();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
