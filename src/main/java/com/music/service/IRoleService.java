package com.music.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.music.api.APIResponse;
import com.music.entity.Role;


import java.util.List;

/**
 * @Description: 角色管理接口
 * @Author: xiep
 * @Date: 2019/07/10 13:15
 **/
public interface IRoleService extends IService<Role> {

    /**
     * 根据创建用户ID获取角色列表
     * @param page 第几页
     * @param pageSize 每页多少条记录
     * @param userId 创建用户ID
     * @return 角色列表
     */
    List<JSONObject> getRolesById(String page, String pageSize, String userId);

    /**
     * 根据创建用户ID获取角色数
     * @param userId 创建用户ID
     * @return 角色数
     */
    int countRolesById(String userId);

    /**
     * 创建角色
     * @param requestJson
     * @return
     */
    APIResponse insertRole(JSONObject requestJson);

    /**
     * 编辑角色
     * @param requestJson
     * @return
     */
    APIResponse updateRole(JSONObject requestJson);

    /**
     * 删除角色
     * @param requestJson
     * @return
     */
    APIResponse deleteRole(JSONObject requestJson);

    /**
     * 查询数据库，获取所有权限列表
     * @return
     */
    List<JSONObject> getAllPermission();
}
