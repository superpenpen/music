package com.music.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.api.APIResponse;
import com.music.api.APIServiceCode;
import com.music.contants.IntContants;
import com.music.contants.StringConstants;
import com.music.entity.Role;
import com.music.entity.RolePermission;
import com.music.entity.User;
import com.music.mapper.RoleMapper;
import com.music.service.IRoleService;
import com.music.util.date.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 角色管理服务实现类
 * @Author: xiep
 * @Date: 2019/07/10 13:25
 **/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public List<JSONObject> getRolesById(String page, String pageSize, String userId) {
        int start = (Integer.valueOf(page) - 1) * (Integer.valueOf(pageSize));
        int limit = Integer.valueOf(pageSize);
        if (Integer.valueOf(userId) == IntContants.SUPER_ADMIN_USER_ID) {
            return roleMapper.getAllRoles(start, limit + 1);
        } else {
            return roleMapper.getRolesById(Integer.valueOf(userId), start, limit);
        }
    }

    @Override
    public int countRolesById(String userId) {
        if (Integer.valueOf(userId) == IntContants.SUPER_ADMIN_USER_ID) {
            return roleMapper.countAllRoles();
        } else {
            return roleMapper.countRolesById(Integer.valueOf(userId));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public APIResponse insertRole(JSONObject requestJson) {
        // 根据用户名role_name，判断角色是否已存在
        Role r = roleMapper.getRoleByRoleName(requestJson.getString("roleName"));
        if (r != null) {
            return APIResponse.error(APIServiceCode.PRIVILEGE_ROLE_HAS_EXISTED.getCode(),
                    APIServiceCode.PRIVILEGE_ROLE_HAS_EXISTED.getMessage());
        }
        // `role`表
        String roleName = requestJson.getString("roleName");
        String descInfo = requestJson.getString("descInfo");
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(StringConstants.SESSION_USER_INFO);
        int createUserId = userInfo.getInteger("userId");
        String createTime = DateUtils.getCurrentTime();
        roleMapper.insertRole(roleName, descInfo, createUserId, createTime);

        // `role_permission`表
        Role role = roleMapper.getRoleByName(roleName, createUserId);
        if (role != null) {
            int roleId = role.getId();
            List<Integer> permissions = (List<Integer>) requestJson.get("permissions");
            roleMapper.insertRolePermission(roleId, permissions, IntContants.ROLE_PERMISSION_STATUS_ON);
        }
        return APIResponse.success(requestJson);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public APIResponse updateRole(JSONObject requestJson) {
        // 根据用户名role_name，判断角色是否已存在
        Role r = roleMapper.getRoleByRoleName(requestJson.getString("roleName"));
        if (r != null && r.getId() != requestJson.getInteger("roleId")) {
            return APIResponse.error(APIServiceCode.PRIVILEGE_ROLE_HAS_EXISTED.getCode(),
                    APIServiceCode.PRIVILEGE_ROLE_HAS_EXISTED.getMessage());
        }
        // `role`表
        int roleId = requestJson.getInteger("roleId");
        String roleName = requestJson.getString("roleName");
        String descInfo = requestJson.getString("descInfo");
        String updateTime = DateUtils.getCurrentTime();
        roleMapper.updateRole(roleId, roleName, descInfo, updateTime);

        // `role_permission`表
        List<Integer> newPerms = (List<Integer>) requestJson.get("permissions");
        List<Integer> oldPerms = roleMapper.getPermissionIds(roleId);
        updateRolePermission(roleId, newPerms, oldPerms);
        return APIResponse.success(requestJson);
    }

    private void updateRolePermission(int roleId, List<Integer> newPerms, List<Integer> oldPerms) {
        List<Integer> newPermsOnly = new ArrayList<>();
        List<Integer> oldPermsOnly = new ArrayList<>();
        for (int perm : newPerms) {
            if (!oldPerms.contains(perm)) {
                newPermsOnly.add(perm);
            }
        }
        for (int perm : oldPerms) {
            if (!newPerms.contains(perm)) {
                oldPermsOnly.add(perm);
            }
        }

        List<Integer> insertPerms = new ArrayList<>();
        List<Integer> updatePerms = new ArrayList<>();
        for (int perm : newPermsOnly) {
            RolePermission rolePermission = roleMapper.getRolePermission(roleId, perm);
            if (rolePermission == null) {
                insertPerms.add(perm);
            } else {
                updatePerms.add(perm);
            }
        }
        if (oldPermsOnly.size() > 0) {
            roleMapper.updateRolePermission(roleId, oldPermsOnly, IntContants.ROLE_PERMISSION_STATUS_OFF);
        }
        if (insertPerms.size() > 0) {
            roleMapper.insertRolePermission(roleId, insertPerms, IntContants.ROLE_PERMISSION_STATUS_ON);
        }
        if (updatePerms.size() > 0) {
            roleMapper.updateRolePermission(roleId, updatePerms, IntContants.ROLE_PERMISSION_STATUS_ON);
        }
    }

    @Override
    public APIResponse deleteRole(JSONObject requestJson) {
        int roleId = requestJson.getInteger("roleId");
        List<User> users = roleMapper.getUsers(roleId);
        if (users != null && users.size() > 0) {
            return APIResponse.error(APIServiceCode.PRIVILEGE_DEL_ROLE.getCode(), APIServiceCode.PRIVILEGE_DEL_ROLE.getMessage());
        }
        roleMapper.deleteRole(roleId);
        roleMapper.deleteRoleAllPermission(roleId);
        return APIResponse.success(requestJson);
    }

    @Override
    public List<JSONObject> getAllPermission() {
        return roleMapper.getAllPermission();
    }
}
