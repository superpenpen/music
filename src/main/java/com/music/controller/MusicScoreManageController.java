package com.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.api.APIResponse;
import com.music.api.APIServiceCode;
import com.music.entity.MusicScore;
import com.music.entity.MusicScoreForSel;
import com.music.entity.MusicScoreQuery;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        String musicName = json.getString("musicName");
        String musicScope = json.getString("musicScope");
        String musicType = json.getString("musicType");
        String hands = json.getString("hands");
        String musicCharacter = json.getString("musicCharacter");
        String musicTime = json.getString("musicTime");
        String authorKnownDegree = json.getString("authorKnownDegree");
        String authorCountryId = json.getString("authorCountryId");
        String authorNameId = json.getString("authorNameId");
        if(StringUtils.isEmptyBatch(page,size)){
            throw new BusinessException(APIServiceCode.SYSTEM_PARAM_NOT_COMPLETE.getCode(),
                    APIServiceCode.SYSTEM_PARAM_NOT_COMPLETE.getMessage());
        }
        Page<MusicScoreForSel> musicScorePage = new Page<MusicScoreForSel>(page,size);
        MusicScoreQuery queryParams = new MusicScoreQuery();
        if (!StringUtils.isEmpty(musicName)){
            queryParams.setMusicName(musicName);
        }
        if (!StringUtils.isEmpty(musicType)){
            queryParams.setMusicType(Integer.valueOf(musicType));
        }
        if (!StringUtils.isEmpty(musicScope)){
            queryParams.setMusicScope(Integer.valueOf(musicScope));
        }
        if (!StringUtils.isEmpty(hands)){
            queryParams.setHands(Integer.valueOf(hands));
        }
        if (!StringUtils.isEmpty(musicCharacter)){
            List<String> musicCharacterList = new ArrayList<>();
            if (musicCharacter.length()>1){
                String[] musicCharacters = musicCharacter.split(",");
                musicCharacterList = Arrays.asList(musicCharacters);
            }else{
                musicCharacterList.add(musicCharacter);
            }
            queryParams.setMusicCharacter(musicCharacterList);
        }
        if (!StringUtils.isEmpty(musicTime)){
            queryParams.setMusicTime(Integer.valueOf(musicTime));
        }
        if (!StringUtils.isEmpty(authorKnownDegree)){
            queryParams.setAuthorKnownDegree(Integer.valueOf(authorKnownDegree));
        }
        if (!StringUtils.isEmpty(authorCountryId)){
            queryParams.setAuthorCountryId(Integer.valueOf(authorCountryId));
        }
        if (!StringUtils.isEmpty(authorNameId)){
            queryParams.setAuthorNameId(Integer.valueOf(authorNameId));
        }

        Page<MusicScoreForSel> musics = musicScoreService.selectMusics(musicScorePage,queryParams);
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
        String fileName = null;
        String uuid = StringUtils.getRandomCharAndNumr(20);
        try {
            if ( null!= file  ) {
                JSONObject jsonObject = uploadUtils.updateToBaseFile(uploadPath,file, uuid);
                path = jsonObject.getString("path");
                fileName = jsonObject.getString("fileName");
            } else {
                throw new BusinessException(APIServiceCode.SYSTEM_PARAM_NOT_COMPLETE.getCode(),
                        APIServiceCode.SYSTEM_PARAM_NOT_COMPLETE.getMessage());
            }
            String[] names = fileName.split(",");
            musicScore.setMusicName(names[0]);
            musicScore.setFilePath(path);
            musicScore.setCreateTime(DateUtils.getCurrentTime());
            musicScore.setUuid(uuid);
            musicScoreService.insertMusicScore(musicScore);
            return APIResponse.success(APIServiceCode.SUCCESS.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(APIServiceCode.SYSTEM_INNER_ERROR.getCode(),
                    APIServiceCode.SYSTEM_INNER_ERROR.getMessage());
        }
    }

    /**
     * 删除
     * @return
     */
    @GetMapping("/delete")
    APIResponse deleteMusic(String id) {
        if(StringUtils.isEmpty(id)){
            throw new BusinessException(APIServiceCode.SYSTEM_PARAM_NOT_COMPLETE.getCode(),
                    APIServiceCode.SYSTEM_PARAM_NOT_COMPLETE.getMessage());
        }
        try {
            musicScoreMapper.delById(Integer.valueOf(id));
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
