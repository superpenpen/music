package com.music.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.entity.MusicScore;
import com.music.mapper.MusicScoreMapper;
import com.music.service.IMusicScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiep
 * @version V1.0
 * @Title: ${file_name}
 * @Package com.music.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2019/5/28 13:54
 */
@Service
public class MusicScoreServiceImpl extends ServiceImpl<MusicScoreMapper, MusicScore> implements IMusicScoreService {


    @Autowired
    MusicScoreMapper musicScoreMapper;

    @Override
    public Page<MusicScore> selectMusics(Page<MusicScore> page, MusicScore queryParams){
        return (Page)page.setRecords(musicScoreMapper.selectMusics(page,queryParams));
    }

    @Override
    public void insertMusicScore(MusicScore musicScore){
        musicScoreMapper.insert(musicScore);
    }

}
