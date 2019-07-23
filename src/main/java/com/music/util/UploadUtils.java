package com.music.util;


import com.music.contants.PathConstant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author xiep
 * @version V1.0
 * @Title: ${file_name}
 * @Package com.cstor.intelligent.util
 * @Description: TODO(上传)
 * @date 2019/1/31 9:42
 */
@Component
public class UploadUtils {


    static Logger logger = Logger.getLogger(UploadUtils.class);

    @Autowired
    PathConstant pathConstant;

    /**
     * 上传base64图片 至服务器
     */
    public String uploadBase64File(String filePath, String file) {
        String basepath = pathConstant.getBaseFilepath();
        String qpath = filePath
                + "/" + StringUtils.getRandomCharAndNumr(10) + ".jpg";

        String path = basepath + qpath;
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

        String basepath = pathConstant.getBaseFilepath();
        String qpath = filePath
                + "/" + StringUtils.getRandomCharAndNumr(10) + ".jpg";
        String path = basepath + qpath;
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
     * 上传base64图片 至服务器 目标库
     */
    public String uploadBase64File2(String filePath, String file, String guid) {
        String basepath = pathConstant.getBaseFilepath();
        String qpath = filePath
                + "/" + guid + ".jpg";
        // 判断文件夹是否存在，第一次需创建
        String folderPath = basepath + filePath;
        File folder = new File(folderPath);
        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdirs();
        }
        String path = basepath + qpath;
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
            return null;
        }
        return path;
    }

    /**
     * 上传url图片 至服务器 目标库
     */
    public String uploadUrlPicFile2(String filePath, String urlPath, String guid) {

        String basepath = pathConstant.getBaseFilepath();
        String qpath = filePath
                + "/" + guid + ".jpg";
        // 判断文件夹是否存在，第一次需创建
        String folderPath = basepath + filePath;
        File folder = new File(folderPath);
        if (!folder.exists() && !folder.isDirectory()) {
            folder.mkdirs();
        }
        String path = basepath + qpath;
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
            return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return path;
    }


    /**
     * 图片转为base64
     *
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
     * 创建文件夹
     */
    public String createFolder(String folderPath, String folderName) {
        String basepath = pathConstant.getBaseFilepath();
        String path = basepath + folderPath + "/" + folderName;

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
