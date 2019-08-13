package com.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.api.APIResponse;
import com.music.api.APIServiceCode;
import com.music.entity.MusicScore;
import com.music.expection.BusinessException;
import com.music.mapper.MusicScoreMapper;
import com.music.service.IMusicScoreService;
import com.music.util.StringUtils;
import com.music.util.UploadUtils;
import com.music.util.date.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * @author xiep
 * @version V1.0
 * @Title: ${file_name}
 * @Package com.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2019/5/24 15:46
 */
@RestController
@CrossOrigin
@RequestMapping("/music_socre")
public class MusicScoreManageController {

    @Autowired
    UploadUtils uploadUtils;

    @Autowired
    IMusicScoreService musicScoreService;

    @Autowired
    MusicScoreMapper musicScoreMapper;



    @Value("${upload.path}")
    String uploadPath;


    /**
     * 分页查询乐谱相关信息
     * @param json
     * @return
     */
    @PostMapping("/musics")
    APIResponse selectMusics(@RequestBody JSONObject json){
        int page =  json.getIntValue("page");
        int size = json.getIntValue("size");
        if(StringUtils.isEmptyBatch(page,size)){
            throw new BusinessException(APIServiceCode.SYSTEM_PARAM_NOT_COMPLETE.getCode(),
                    APIServiceCode.SYSTEM_PARAM_NOT_COMPLETE.getMessage());
        }
        Page<MusicScore> musicScorePage = new Page<MusicScore>(page,size);
        MusicScore queryParams = new MusicScore();
        Page<MusicScore> musics = musicScoreService.selectMusics(musicScorePage,queryParams);
        if(musics.getSize()>0){
            return APIResponse.success(musics) ;
        }
        return APIResponse.success(new Page<MusicScore>());

    }

    /**
     * 上传乐谱并添加相关信息
     * @return
     */
    @PostMapping("/add")
    APIResponse addMusic(@Valid MusicScore musicScore, @RequestParam("file") MultipartFile file) {
        String path = null;
        try {
            if ( null!= file  ) {
                path = uploadUtils.updateToBaseFile(uploadPath,file);
            } else {
                throw new BusinessException(APIServiceCode.SYSTEM_PARAM_NOT_COMPLETE.getCode(),
                        APIServiceCode.SYSTEM_PARAM_NOT_COMPLETE.getMessage());
            }
            musicScore.setFilePath(path);
            musicScore.setCreateTime(DateUtils.getCurrentTime());
            musicScoreService.insertMusicScore(musicScore);
            return APIResponse.success(APIServiceCode.SUCCESS.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(APIServiceCode.SYSTEM_INNER_ERROR.getCode(),
                    APIServiceCode.SYSTEM_INNER_ERROR.getMessage());
        }
    }

    /**
     * 查询作家
     * @return
     */
    @GetMapping("/names")
    APIResponse getNames(){

        return APIResponse.success(musicScoreMapper.getNames());
    }

    /**
     * 查询国家
     * @return
     */
    @GetMapping("/countrys")
    APIResponse getCountrys(){

        return APIResponse.success(musicScoreMapper.getCountrys());
    }

}
