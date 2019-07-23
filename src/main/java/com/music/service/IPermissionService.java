package com.music.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author xiep
 * @version V1.0
 * @Title: ${file_name}
 * @Package com.cstor.cvideoai.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2019/5/28 13:54
 */
public interface IPermissionService  {

    /**
     * 查询某用户的 角色  菜单列表   权限列表
     */
    JSONObject getUserPermission(String username);

}
