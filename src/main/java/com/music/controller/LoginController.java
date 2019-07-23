package com.music.controller;

import com.alibaba.fastjson.JSONObject;

import com.music.service.ILoginService;
import com.music.service.IUserService;
import com.music.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiep
 * @version V1.0
 * @Title: ${file_name}
 * @Package com.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2019/5/24 15:46
 */
@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @Autowired
    IUserService userService;

    @Autowired
    ILoginService loginService;
    /**
     * 登录
     */
    @PostMapping("/auth")
    public JSONObject authLogin(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "username,password");
        return loginService.authLogin(requestJson);
    }

    /**
     * 查询当前登录用户的信息
     */
    @PostMapping("/info")
    public JSONObject getInfo() {
        return loginService.getInfo();
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    public JSONObject logout() {
        return loginService.logout();
    }

}
