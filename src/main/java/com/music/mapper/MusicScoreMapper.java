package com.music.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.entity.*;
import org.apache.ibatis.annotations.Delete;
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


    List<MusicScoreForSel> selectMusics(Page<MusicScoreForSel> page, @Param("queryParams")MusicScoreQuery queryParams);

    @Select(" select * from author_country")
    List<AuthorCountry> getCountrys();

    @Select(" select * from author_name")
    List<AuthorName> getNames();

    @Delete( "delete from music_score where id = #{id}")
    void delById(@Param("id") Integer id);

}
