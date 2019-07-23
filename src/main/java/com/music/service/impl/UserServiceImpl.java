package com.music.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.api.APIResponse;
import com.music.api.APIServiceCode;
import com.music.contants.IntContants;
import com.music.contants.StringConstants;
import com.music.entity.Role;
import com.music.entity.User;
import com.music.mapper.UserMapper;
import com.music.service.IUserService;
import com.music.util.date.DateUtils;
import com.music.util.redis.RedisUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisUtils redisUtils;

    @Override
    public List<User> getUsersById(String page, String pageSize, String userId) {
        int start = (Integer.valueOf(page) - 1) * (Integer.valueOf(pageSize));
        int limit = Integer.valueOf(pageSize);
        if (Integer.valueOf(userId) == IntContants.SUPER_ADMIN_USER_ID) {
            return userMapper.getAllUsers(start, limit);
        } else {
            return userMapper.getUsersById(Integer.valueOf(userId), start, limit);
        }
    }

    @Override
    public int countUsersById(String userId) {
        if (Integer.valueOf(userId) == IntContants.SUPER_ADMIN_USER_ID) {
            return userMapper.countAllUsers();
        } else {
            return userMapper.countUsersById(Integer.valueOf(userId));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public APIResponse insertUser(User user) {
        // 根据用户名username，判断用户是否已存在
        User u = userMapper.getUserByUserName(user.getUsername());
        if (u != null) {
            return APIResponse.error(APIServiceCode.PRIVILEGE_USER_HAS_EXISTED.getCode(),
                    APIServiceCode.PRIVILEGE_USER_HAS_EXISTED.getMessage());
        }
        // 根据roleId获取roleName
        String roleName = userMapper.getRoleNameById(user.getRoleId());
        // 设置用户角色名称role_name
        user.setRoleName(roleName);
        // 设置用户创建人Id, create_user_id
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(StringConstants.SESSION_USER_INFO);
        user.setCreateUserId(userInfo.getInteger("userId"));
        // 设置用户状态status, 默认为1(启用)
        user.setStatus(1);
        // 设置用户创建时间create_time
        user.setCreateTime(DateUtils.getCurrentTime());
        insert(user);
        return APIResponse.success(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public APIResponse updateUser(User user) {
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setDescInfo(user.getDescInfo());
        user.setRoleId(user.getRoleId());
        // 根据roleId获取roleName
        String roleName = userMapper.getRoleNameById(user.getRoleId());
        // 设置用户角色名称role_name
        user.setRoleName(roleName);
        user.setStatus(user.getStatus());
        user.setEmail(user.getEmail());
        user.setPhone(user.getPhone());
        user.setAddress(user.getAddress());
        // 设置用户修改时间create_time
        user.setUpdateTime(DateUtils.getCurrentTime());
        updateById(user);
        return APIResponse.success(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserStatus(int userId, int status) {
        userMapper.updateUserStatus(userId, status);
    }

    @Override
    public List<Role> getAllRoles() {
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(StringConstants.SESSION_USER_INFO);
        int userId = userInfo.getInteger("userId");
        if (userId == IntContants.SUPER_ADMIN_USER_ID) {
            return userMapper.getAllRoles();
        } else {
            return userMapper.getRolesById(userId);
        }
    }

}
