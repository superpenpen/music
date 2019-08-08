package com.music.util;


import com.music.api.APIServiceCode;
import com.music.expection.BusinessException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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

    /**
     * 功能描述:上传文件
     * @param: [file]
     * @return: java.lang.String
     * @auther: xiep
     * @date: 2019/1/30 11:48
     */
    public String updateToBaseFile(String filePath,MultipartFile file){
        String fileName =  file.getOriginalFilename();
        String path  = filePath + fileName  ;

        File dest = new File(path);
        if( dest.exists() ) {
            throw new BusinessException(APIServiceCode.UPLOAD_FILE_EXIST.getCode(),
                    APIServiceCode.UPLOAD_FILE_EXIST.getMessage());
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }



}
