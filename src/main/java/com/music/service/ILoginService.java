package com.music.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author xiep
 * @version V1.0
 * @Title: ${file_name}
 * @Package com.cstor.cvideoai.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @date 2019/5/28 13:21
 */
public interface ILoginService {

    /**
     * 登录表单提交
     */
    JSONObject authLogin(JSONObject jsonObject);

    /**
     * 根据用户名和密码查询对应的用户
     *
     * @param username 用户名
     * @param password 密码
     */
    JSONObject getUser(String username, String password);

    /**
     * 查询当前登录用户的权限等信息
     */
    JSONObject getInfo();

    /**
     * 退出登录
     */
    JSONObject logout();

}
