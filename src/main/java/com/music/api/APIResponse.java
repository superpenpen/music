package com.music.api;

/**
 * @author xiep
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}( 接口返回信息实体类 )
 * @date ${date} ${time}
 */
public class APIResponse<T> {

    /**
    *状态码
     *  1  表示成功
     * 其他表示失败
    */
    private int code;

    /**
     *提示信息
     */
    private String msg;

    /**
     * 返回信息
     */
    private T data;


    /**
     * 功能描述:当正确时返回的值
     * @param: [data]
     * @return: com.cstor.cvideoai.api.APIResponse
     * @auther: xiep
     * @date: 2019/5/21 9:19
     */
    public static APIResponse success(Object data){

        APIResponse result = new APIResponse();
        result.setCode(APIServiceCode.SUCCESS.getCode());
        result.setMsg(APIServiceCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }


    /**
     * 功能描述:当错误时返回的值
     * @param:
     * @return:
     * @auther: xiep
     * @date: 2019/5/21 9:18
     */
    public static APIResponse error(int code , String msg){
        APIResponse result = new APIResponse();
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
