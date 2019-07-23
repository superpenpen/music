package com.music.contants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiep
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date ${date} ${time}
 */

@Configuration
public class PathConstant {


    @Value("${music.baseFilepath}")
    String baseFilepath ;

    @Value("${music.musicScore}")
    String faceFilepath ;

    public String getBaseFilepath() {
        return baseFilepath;
    }

    public void setBaseFilepath(String baseFilepath) {
        this.baseFilepath = baseFilepath;
    }

    public String getFaceFilepath() {
        return faceFilepath;
    }

    public void setFaceFilepath(String faceFilepath) {
        this.faceFilepath = faceFilepath;
    }
}
