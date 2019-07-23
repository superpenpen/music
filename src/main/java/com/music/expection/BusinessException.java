package com.music.expection;

/**
 * @author xiep
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}(自定义异常类)
 * @date ${date} ${time}
 */
public class BusinessException extends RuntimeException {

    public BusinessException(int code, String msg) {
//        super(msg);
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

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
}
