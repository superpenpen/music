package com.music.util;

import java.io.File;

/**
 * 服务器上删除文件及目录
 *
 * @author Xiep
 * @date 2020/10/16 2:00 下午
 */
public class FileUtil {


    /**
     * 删除文件及目录
     * @param file;
     */
    public static boolean delFile(File file) {
        if (!file.exists()) {
            return true;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                delFile(f);
            }
        }
        return file.delete();
    }


}
