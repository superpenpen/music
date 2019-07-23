package com.music.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SuperMapper<T> extends BaseMapper<T> {

    // 公共的方法
}
