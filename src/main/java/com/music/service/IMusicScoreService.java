package com.music.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.music.api.APIResponse;
import com.music.entity.MusicScore;
import com.music.entity.Role;
import com.music.entity.User;

import java.util.List;

/**
 * MusicScore表数据服务层接口
 */
public interface IMusicScoreService extends IService<MusicScore> {


	/**
	 * 分页查询 乐谱
	 * @param page
	 * @param queryParams
	 * @return
	 */
	Page<MusicScore> selectMusics(Page<MusicScore> page, MusicScore queryParams);


	/**
	 * 乐谱添加
	 * @param musicScore
	 */
	void insertMusicScore(MusicScore musicScore);

}