package com.music.controller;


import com.music.constant.LogConstants;
import com.music.entity.User;
import com.music.security.JwtAuthenticationToken;
import com.music.security.SecurityUtils;
import com.music.service.IUserService;
import com.music.util.HttpResult;
import com.music.util.HttpStatus;
import com.music.util.PasswordUtils;
import com.music.util.date.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * 登录
 *
 * @author Xiep
 * @date 2020/9/1 10:50 上午
 */

@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    IUserService userService;

    @Resource
    private AuthenticationManager authenticationManager;


    @PostMapping("/api/login")
    public HttpResult login(User loginUser, HttpServletRequest request) {
        try{
            String userName = loginUser.getUserName();
            String password = loginUser.getPassword();
            // 用户信息
            User user = userService.getUserByUserName(userName);
            // 账号不存在、密码错误
            if (user == null) {
                return HttpResult.error(HttpStatus.SYSTEM_USER_NOT_EXISTS.getCode(), HttpStatus.SYSTEM_USER_NOT_EXISTS.getMessage());
            }
            if (!PasswordUtils.matches(user.getSalt(), password, user.getPassword())) {
                return HttpResult.error(HttpStatus.SYSTEM_USER_PASSWORD_ERROR.getCode(), HttpStatus.SYSTEM_USER_PASSWORD_ERROR.getMessage());
            }
            // 系统登录认证
            JwtAuthenticationToken token = SecurityUtils.login(request, userName, password, authenticationManager);
            token.setNickName(user.getNickName());
            token.setUserName(user.getUserName());
            token.setUserId(user.getId());
            token.setRoleId(user.getRoleId());
            // 记录登录日志

            // 返回token
            return HttpResult.ok(token);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error(HttpStatus.SYSTEM_INNER_ERROR.getCode(), HttpStatus.SYSTEM_INNER_ERROR.getMessage());
        }

    }


}
