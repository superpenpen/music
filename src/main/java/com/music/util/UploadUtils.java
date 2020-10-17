package com.music.util;


import com.alibaba.fastjson.JSONObject;
import com.music.constant.PathConstant;
import com.music.util.date.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @ClassName UploadUtils
 * @Description
 * @Author xiep
 * @Date 2020/2/4 14:23
 **/
@Component
public class UploadUtils {


    static Logger logger = Logger.getLogger(UploadUtils.class);

    @Resource
    PathConstant pathConstant;


    /**
     * 功能描述:上传文件
     * @param: [file]
     * @return: java.lang.String
     * @auther: xiep
     * @date: 2019/1/30 11:48
     */
    public JSONObject updateToBaseFile(String filePath, MultipartFile file, String uuid){
        String fileName =  file.getOriginalFilename();
        String path  = filePath + uuid + ".pdf"  ;

        File dest = new File(path);
        JSONObject jsonObject = new JSONObject();
        jsonObject.fluentPut("path", path)
                .fluentPut("fileName", fileName);
        if( dest.exists() ) {
            return jsonObject;
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }



    /**
     * 功能描述:上传文件 zip
     *
     * @param filePath
     * @param fileName
     * @param file
     * @return
     */
    public String uploadZipFile(String filePath, String fileName, MultipartFile file){
//        String fileName =  file.getOriginalFilename();
        String path  = filePath + fileName + ".zip";

        File dest = new File(path);
        if( dest.exists() ) {
            return path;
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }




    /**
     * 上传base64图片 至服务器
     */
    public String uploadBase64File(String filePath, String file) {
        String path = filePath + "/" + StringUtils.getRandomCharAndNumr(10) + ".jpg";
        //对字节数组字符串进行Base64解码并生成图片
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(file);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 上传url图片 至服务器
     */
    public String uploadUrlPicFile(String filePath, String urlPath) {

        String path = filePath
                + "/" + StringUtils.getRandomCharAndNumr(10) + ".jpg";
        try {
            // 构造URL
            URL url = new URL(urlPath);
            // 打开连接
            URLConnection con = url.openConnection();
            // 输入流
            InputStream is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            OutputStream os = new FileOutputStream(path);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            os.close();
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    /**
     * 图片转为base64
     * @param imgPath
     * @return
     */
    public static String getBase64FromPath(String imgPath) {
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encode(data);
    }


    /**
     *  上传文件
     *
     * @param filePath
     * @param file
     * @return
     */
    public String uploadToFile(String filePath, MultipartFile file){
        String fileNewName = DateUtils.getCurrentTime("yyyyMMddHHmmss") + StringUtils.getRandomCharAndNumr(10);
        //判断有无目录
        judeDirExists(new File(filePath));
        String type = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        String path  = filePath + fileNewName + type ;

        File dest = new File(path);
        if( dest.exists() ) {
            logger.info("上传文件异常");
        }
        try {
            file.transferTo(dest);
            return path;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断文件夹是否存在
     * @param myPath
     */
    public static void judeDirExists(File myPath) {
        if (!myPath.exists()) {
            myPath.mkdirs();//多级文件夹目录
        }
    }

    /**
     * 创建文件夹
     */
    public String createFolder(String folderPath, String folderName) {
        String path = folderPath + "/" + folderName;

        File folder = new File(path);
        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdirs();
            logger.info("创建了 " + folderName + " 文件夹");
        } else {
            logger.info("文件夹 " + folderName + " 已存在");
        }

        return path;
    }

}
