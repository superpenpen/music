package com.music.controller;

import com.alibaba.fastjson.JSONObject;

import com.music.api.APIResponse;
import com.music.api.APIServiceCode;
import com.music.contants.IntContants;
import com.music.entity.Role;
import com.music.entity.User;
import com.music.expection.BusinessException;
import com.music.service.IUserService;
import com.music.util.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author xiep
 * @version V1.0
 * @Title: ${file_name}
 * @Package com.controller
 * @Description: TODO(用户管理)
 * @date 2019/5/21 15:17
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController{

    @Autowired
    IUserService userService;

    /**
     * 根据创建用户ID查询其创建的用户列表
     */
    @RequiresPermissions("user:select")
    @GetMapping("/select")
    APIResponse getUsers(String page, String pageSize, String userId) {
        if (StringUtils.isEmptyBatch(page, pageSize, userId)) {
            throw new BusinessException(APIServiceCode.PARAM_NOT_COMPLETE.getCode(),
                    APIServiceCode.PARAM_NOT_COMPLETE.getMessage());
        }
        JSONObject jsonObject = new JSONObject();
        List<User> users = userService.getUsersById(page, pageSize, userId);
        int total = userService.countUsersById(userId);
        jsonObject.put("users", users);
        jsonObject.put("total", total);
        return APIResponse.success(jsonObject);
    }

    /**
     * 创建用户
     */
    @RequiresPermissions("user:insert")
    @PostMapping("/insert")
    APIResponse insertUser(@RequestBody User user) {
        if (user.getRoleId() == IntContants.INTI_ROLE_ID) {
            throw new BusinessException(APIServiceCode.PARAM_NOT_COMPLETE.getCode(),
                    APIServiceCode.PARAM_NOT_COMPLETE.getMessage());
        }
        try {
            return userService.insertUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(APIServiceCode.SYSTEM_INNER_ERROR.getCode(),
                    APIServiceCode.SYSTEM_INNER_ERROR.getMessage());
        }
    }

    /**
     * 编辑用户
     */
    @RequiresPermissions("user:update")
    @PostMapping("/update")
    APIResponse updateUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(APIServiceCode.PARAM_NOT_COMPLETE.getCode(),
                    APIServiceCode.PARAM_NOT_COMPLETE.getMessage());
        }
        try {
            return userService.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(APIServiceCode.SYSTEM_INNER_ERROR.getCode(),
                    APIServiceCode.SYSTEM_INNER_ERROR.getMessage());
        }
    }

    /**
     * 用户删除
     */
    @RequiresPermissions("user:delete")
    @PostMapping("/delete")
    APIResponse deleteUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(APIServiceCode.PARAM_NOT_COMPLETE.getCode(),
                    APIServiceCode.PARAM_NOT_COMPLETE.getMessage());
        }
        try {
            userService.deleteUser(user.getId());
            return APIResponse.success(user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(APIServiceCode.SYSTEM_INNER_ERROR.getCode(),
                    APIServiceCode.SYSTEM_INNER_ERROR.getMessage());
        }
    }

    /**
     * 更新用户状态
     */
    @RequiresPermissions("user:delete")
    @GetMapping("/update_status")
    APIResponse updateUserStatus(String userId, String status) {
        if (StringUtils.isEmptyBatch(userId, status)) {
            throw new BusinessException(APIServiceCode.PARAM_NOT_COMPLETE.getCode(),
                    APIServiceCode.PARAM_NOT_COMPLETE.getMessage());
        }
        try {
            userService.updateUserStatus(Integer.valueOf(userId), Integer.valueOf(status));
            return APIResponse.success(userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(APIServiceCode.SYSTEM_INNER_ERROR.getCode(),
                    APIServiceCode.SYSTEM_INNER_ERROR.getMessage());
        }
    }

    /**
     * 查询数据库，获取所有角色列表
     */
    @RequiresPermissions(value = {"user:insert", "user:update"}, logical = Logical.OR)
    @GetMapping("/roles")
    APIResponse getAllRoles() {
        JSONObject jsonObject = new JSONObject();
        List<Role> roles = userService.getAllRoles();
        jsonObject.put("roles", roles);
        return APIResponse.success(jsonObject);
    }

}
