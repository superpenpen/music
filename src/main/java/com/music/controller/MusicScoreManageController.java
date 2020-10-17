package com.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.music.constant.PathConstant;
import com.music.entity.MusicScore;
import com.music.entity.MusicScoreForSel;
import com.music.entity.MusicScoreQuery;
import com.music.mapper.MusicScoreMapper;
import com.music.service.IMusicScoreService;
import com.music.util.HttpResult;
import com.music.util.HttpStatus;
import com.music.util.StringUtils;
import com.music.util.UploadUtils;
import com.music.util.date.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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

    @Resource
    UploadUtils uploadUtils;

    @Resource
    PathConstant pathConstant;

    @Resource
    IMusicScoreService musicScoreService;

    @Resource
    MusicScoreMapper musicScoreMapper;



    /**
     * 分页查询乐谱相关信息
     *
     * @param json
     * @return
     */
    @PostMapping("/musics")
    HttpResult selectMusics(@RequestBody JSONObject json){
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
            return HttpResult.error(HttpStatus.SYSTEM_PARAM_NOT_COMPLETE.getCode(),
                    HttpStatus.SYSTEM_PARAM_NOT_COMPLETE.getMessage());
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
            return HttpResult.ok(musics) ;
        }
        return  HttpResult.ok(new Page<MusicScore>());

    }

    /**
     * 上传乐谱并添加相关信息
     *
     * @return
     */
    @PostMapping("/add")
    HttpResult addMusic(MusicScore musicScore, @RequestParam("file") MultipartFile file) {
        String path = null;
        String fileName = null;
        String uuid = StringUtils.getRandomCharAndNumr(20);
        try {
            if ( null!= file  ) {
                JSONObject jsonObject = uploadUtils.updateToBaseFile(pathConstant.getMusicScorePath(), file, uuid);
                path = jsonObject.getString("path");
                fileName = jsonObject.getString("fileName");
            } else {
                return HttpResult.error(HttpStatus.SYSTEM_PARAM_NOT_COMPLETE.getCode(),
                        HttpStatus.SYSTEM_PARAM_NOT_COMPLETE.getMessage());
            }
            musicScore.setMusicName(fileName);
            musicScore.setFilePath(path);
            musicScore.setCreateTime(DateUtils.getCurrentTime());
            musicScore.setUuid(uuid);
            musicScoreService.insertMusicScore(musicScore);
            return HttpResult.okWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(HttpStatus.SYSTEM_INNER_ERROR.getCode(),
                    HttpStatus.SYSTEM_INNER_ERROR.getMessage());
        }
    }

    /**
     * 删除
     *
     * @return
     */
    @GetMapping("/delete")
    HttpResult deleteMusic(String id) {
        if(StringUtils.isEmpty(id)){
            return HttpResult.error(HttpStatus.SYSTEM_PARAM_NOT_COMPLETE.getCode(),
                    HttpStatus.SYSTEM_PARAM_NOT_COMPLETE.getMessage());
        }
        try {
            musicScoreMapper.delById(Integer.valueOf(id));
            return HttpResult.okWithoutData();
        } catch (Exception e) {
            e.printStackTrace();
            return HttpResult.error(HttpStatus.SYSTEM_INNER_ERROR.getCode(),
                    HttpStatus.SYSTEM_INNER_ERROR.getMessage());
        }
    }

    /**
     * 查询作家
     *
     * @return
     */
    @GetMapping("/names")
    HttpResult getNames(){

        return HttpResult.ok(musicScoreMapper.getNames());
    }

    /**
     * 查询国家
     *
     * @return
     */
    @GetMapping("/countrys")
    HttpResult getCountrys(){

        return HttpResult.ok(musicScoreMapper.getCountrys());
    }

}
