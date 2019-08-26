package com.music.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.music.api.APIResponse;
import com.music.entity.*;

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
	Page<MusicScoreForSel> selectMusics(Page<MusicScoreForSel> page, MusicScoreQuery queryParams);


	/**
	 * 乐谱添加
	 * @param musicScore
	 */
	void insertMusicScore(MusicScore musicScore);

}