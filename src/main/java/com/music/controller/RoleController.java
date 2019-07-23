package com.music.controller;

import com.alibaba.fastjson.JSONObject;

import com.music.api.APIResponse;
import com.music.api.APIServiceCode;
import com.music.expection.BusinessException;
import com.music.service.IRoleService;
import com.music.util.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Description: 角色管理
 * @Author: xiep
 * @Date: 2019/07/10 11:56
 **/
@RestController
@CrossOrigin
@RequestMapping("/role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    /**
     * 查询数据库，根据创建用户ID，获取角色列表
     */
    @RequiresPermissions("role:select")
    @GetMapping("/select")
    APIResponse getRoles(String page, String pageSize, String userId) {
        if (StringUtils.isEmptyBatch(page, pageSize, userId)) {
            throw new BusinessException(APIServiceCode.PARAM_NOT_COMPLETE.getCode(),
                    APIServiceCode.PARAM_NOT_COMPLETE.getMessage());
        }
        JSONObject jsonObject = new JSONObject();
        List<JSONObject> roles = roleService.getRolesById(page, pageSize, userId);
        int total = roleService.countRolesById(userId);
        jsonObject.put("roles", roles);
        jsonObject.put("total", total);
        return APIResponse.success(jsonObject);
    }

    /**
     * 创建角色
     */
    @RequiresPermissions("role:insert")
    @PostMapping("/insert")
    APIResponse insertRole(@RequestBody JSONObject requestJson) {
        try {
            return roleService.insertRole(requestJson);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(APIServiceCode.SYSTEM_INNER_ERROR.getCode(),
                    APIServiceCode.SYSTEM_INNER_ERROR.getMessage());
        }
    }

    /**
     * 编辑角色
     */
    @RequiresPermissions("role:update")
    @PostMapping("/update")
    APIResponse updateRole(@Valid @RequestBody JSONObject requestJson, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(APIServiceCode.PARAM_NOT_COMPLETE.getCode(),
                    APIServiceCode.PARAM_NOT_COMPLETE.getMessage());
        }
        try {
            return roleService.updateRole(requestJson);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(APIServiceCode.SYSTEM_INNER_ERROR.getCode(),
                    APIServiceCode.SYSTEM_INNER_ERROR.getMessage());
        }
    }

    /**
     * 删除角色
     */
    @RequiresPermissions("role:delete")
    @PostMapping("/delete")
    APIResponse deleteRole(@Valid @RequestBody JSONObject requestJson, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BusinessException(APIServiceCode.PARAM_NOT_COMPLETE.getCode(),
                    APIServiceCode.PARAM_NOT_COMPLETE.getMessage());
        }
        try {
            return roleService.deleteRole(requestJson);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(APIServiceCode.SYSTEM_INNER_ERROR.getCode(),
                    APIServiceCode.SYSTEM_INNER_ERROR.getMessage());
        }
    }

    /**
     * 查询数据库，获取所有角色列表
     */
    @RequiresPermissions("role:select")
    @GetMapping("/all_permission")
    APIResponse getAllPermission() {
        JSONObject jsonObject = new JSONObject();
        List<JSONObject> allPermission = roleService.getAllPermission();
        jsonObject.put("allPermission", allPermission);
        return APIResponse.success(jsonObject);
    }
}
