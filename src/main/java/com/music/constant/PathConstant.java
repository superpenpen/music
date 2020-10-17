package com.music.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author zkk
 * @version V1.0
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date ${date} ${time}
 */

@Configuration
public class PathConstant {


    @Value("${upload.musicScorePath}")
    String musicScorePath ;

    @Value("${http.musicScoreUrl}")
    String musicScoreUrl ;


    public String getMusicScorePath() {
        return musicScorePath;
    }

    public void setMusicScorePath(String musicScorePath) {
        this.musicScorePath = musicScorePath;
    }

    public String getMusicScoreUrl() {
        return musicScoreUrl;
    }

    public void setMusicScoreUrl(String musicScoreUrl) {
        this.musicScoreUrl = musicScoreUrl;
    }
}
