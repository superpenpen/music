package com.music.util;


import okhttp3.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.Iterator;
import java.util.Map;

/**
 * @author zkk
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date ${date} ${time}
 */
@Component
public class HttpUtil {

    Logger logger = Logger.getLogger(HttpUtil.class);

    private static final MediaType FROM_DATA = MediaType.parse("multipart/form-data");

    @Resource
    OkHttpClient httpClient;

    /**
     * 返回Int的 GET 请求
     * @return
     */
    public int  getRequestInt (String url, Map parmas) {
        String respons = "";
        StringBuffer stringBuffer = new StringBuffer("?");
        Iterator<Map.Entry<String, String>> entries = parmas.entrySet().iterator();
        Response response = null ;
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            stringBuffer.append( entry.getKey()+"=" + entry.getValue() + "&");
        }

        Request request = new Request.Builder()
            .url(url + stringBuffer.toString())
            .get()
            .build();
        try {
            response = httpClient.newCall(request).execute();
            if ( !response.isSuccessful() || null ==response.body() ) {
                return 0;
            }
            respons = response.body().string();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if( null != response){
               // response.close();
            }
        }
        return Integer.parseInt(respons);
    }


    /**
     * 返回String的 GET 请求
     * @return
     */
    public String  getRequestString (String url) {
        String respons = "";
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            if ( !response.isSuccessful() || null ==response.body() ) {
                return "0";
            }
            respons = response.body().string();
        }catch (Exception e){
            e.printStackTrace();
        }
        return respons;
    }


    /**
     * 返回Int的 Post 请求 formBody为空 url传参
     *
     * @return
     */
    public int  postRequestInt (String url, Map parmas) {
        String respons = "";
        int code = 0;
        StringBuffer stringBuffer = new StringBuffer("?");
        Iterator<Map.Entry<String, String>> entries = parmas.entrySet().iterator();
        Response response = null ;
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            stringBuffer.append( entry.getKey()+"=" + entry.getValue());
        }
        RequestBody formBody = new FormBody.Builder().build();
        Request request = new Request.Builder()
                .url(url + stringBuffer.toString())
                .post(formBody)
                .build();
        try {
            response = httpClient.newCall(request).execute();
            if ( ! response.isSuccessful() ) {
                return 0;
            }
            code = response.code();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if( null != response){
             //   response.close();
            }
        }
        return code;
    }


    /**
     * 返回Body byte[] 的 Post 请求
     * @return
     */
    public  byte[] postFormParamsByte(String url , File file) {
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("data", "data", fileBody)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = httpClient.newCall(request).execute();
            if ( ! response.isSuccessful() ) {
                return null;
            }
            byte[] bodyByte = response.body().bytes();
            return bodyByte;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }


    /**
     * 返回Body byte[] 的 Post 请求
     * @return
     */
    public byte[] postFormParamsByte2(String url , byte[] bytes) {
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), bytes);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("data", "data", fileBody)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Response response = null;
        try {
            response = httpClient.newCall(request).execute();
            if ( ! response.isSuccessful() ) {
                return null;
            }
            byte[] bodyByte = response.body().bytes();
            return bodyByte;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    /**
     * 返回String的 Post 请求
     * @return
     */
    public String  postRequestString (String url, String jsonStr) {
        String respons = "";
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        Response response  = null ;
        RequestBody requestBody = RequestBody.create(JSON, jsonStr);
        Request request = new Request.Builder()
                .url(url )
                .post(requestBody)
                .build();
        try {
            response = httpClient.newCall(request).execute();
            if ( ! response.isSuccessful() ) {
                return "0";
            }
            respons = response.body().string();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if( null != response){
                   response.close();
            }
        }
        return respons;
    }


    /**
     * 返回int的 Post 请求
     * @return
     */
    public int  putRequestString (String url) {
        Response response  = null ;
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "test");
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        try {
            response = httpClient.newCall(request).execute();
            if ( !response.isSuccessful()) {
                return 1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if( null != response){
                // response.close();
            }
        }
        return 0;

    }


    /**
     * 返回int的 Delete 请求
     *
     * @return
     */
    public int deleteRequestInt(String url) {
        int code = 0;
        Request request = new Request.Builder()
                .url(url )
                .delete()
                .build();
        try (Response response = httpClient.newCall(request).execute()) {
            if ( !response.isSuccessful() || null ==response.body() ) {
                return 0;
            }
            code = response.code();
        }catch (Exception e){
            e.printStackTrace();
        }
        return code;
    }
}
