package com.music.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.entity.MusicScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: Role 表数据库控制层接口
 * @Author: xiep
 * @Date: 2019/07/10 13:26
 **/
@Repository
@Mapper
public interface MusicScoreMapper extends SuperMapper<MusicScore> {


    List<MusicScore> selectMusics(Page<MusicScore> page, @Param("queryParams")MusicScore queryParams);


}
