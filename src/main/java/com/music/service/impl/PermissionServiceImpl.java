package com.music.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.music.contants.IntContants;
import com.music.mapper.PermissionMapper;
import com.music.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author xiep
 * @version V1.0
 * @Title: ${file_name}
 * @Package com.cstor.cvideoai.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2019/5/28 13:54
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 查询某用户的 角色  菜单列表   权限列表
     */
    @Override
    public JSONObject getUserPermission(String username) {
        JSONObject userPermission = getUserPermissionFromDB(username);
        return userPermission;
    }

    /**
     * 从数据库查询用户权限信息
     */
    private JSONObject getUserPermissionFromDB(String username) {
        JSONObject userPermission = permissionMapper.getUserPermission(username);
        String roleIdKey = "roleId";
        if (userPermission.getIntValue(roleIdKey) == IntContants.SUPER_ADMIN_ROLE_ID) {
            // 如果是超级管理员,查询所有菜单和权限
            Set<String> menuList = permissionMapper.getAllMenu();
            Set<String> permissionList = permissionMapper.getAllPermission();
            userPermission.put("menuList", menuList);
            userPermission.put("permissionList", permissionList);
        }
        return userPermission;
    }
}
