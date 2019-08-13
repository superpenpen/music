package com.music.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.entity.AuthorCountry;
import com.music.entity.AuthorName;
import com.music.entity.MusicScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    @Select(" select * from author_country")
    List<AuthorCountry> getCountrys();

    @Select(" select * from author_name")
    List<AuthorName> getNames();


}
