package com.music.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.music.contants.StringConstants;
import com.music.mapper.UserMapper;
import com.music.service.ILoginService;
import com.music.service.IPermissionService;
import com.music.util.CommonUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xiep
 * @version V1.0
 * @Title: ${file_name}
 * @Package com.cstor.cvideoai.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2019/5/28 13:21
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    IPermissionService permissionService;
    /**
     * 登录表单提交
     */
    @Override
    public JSONObject authLogin(JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        JSONObject info = new JSONObject();
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            currentUser.login(token);
            info.put("result", "success");
            info.put("session", currentUser.getSession().getId());
        } catch (AuthenticationException e) {
            info.put("result", "fail");
        }
        return CommonUtil.successJson(info);
    }

    /**
     * 根据用户名和密码查询对应的用户
     */
    @Override
    public JSONObject getUser(String username, String password) {
        return userMapper.getUserJson(username, password);
    }

    /**
     * 查询当前登录用户的权限等信息
     */
    @Override
    public JSONObject getInfo() {
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(StringConstants.SESSION_USER_INFO);
        if (userInfo != null ) {
            String username = userInfo.getString("username");
            JSONObject info = new JSONObject();
            JSONObject userPermission = permissionService.getUserPermission(username);
            session.setAttribute(StringConstants.SESSION_USER_PERMISSION, userPermission);
            info.put("userPermission", userPermission);
            return CommonUtil.successJson(info);
        }
        return null;
    }

    /**
     * 退出登录
     */
    @Override
    public JSONObject logout() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
        } catch (Exception e) {
        }
        return CommonUtil.successJson();
    }
}
