package com.music.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author xiep
 * @version V1.0
 * @Title: ${file_name}
 * @Package com.cstor.cvideoai.mapper
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2019/5/28 13:27
 */
@Repository
@Mapper
public interface PermissionMapper {

    /**
     * 查询用户的角色 菜单 权限
     */
    JSONObject getUserPermission(String username);

    /**
     * 查询所有的菜单
     */
    Set<String> getAllMenu();

    /**
     * 查询所有的权限
     */
    Set<String> getAllPermission();

}
