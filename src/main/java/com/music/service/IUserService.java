package com.music.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.music.entity.User;
import com.music.util.HttpResult;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Xiep
 * @since 2020-08-31
 */
public interface IUserService extends IService<User> {

    /**
     * 根据用户名查询用户
     *
     * @param userName 名称
     * @return User
     */
    User getUserByUserName(String userName);


    /**
     * 根据条件查询用户
     *
     * @param userName
     * @param nickName
     * @param roleId
     * @param page
     * @param size
     * @return
     */
    HttpResult getUsersPageQuery(HttpServletRequest reques,
                                 String userName, String nickName, Integer roleId, Integer page, Integer size);


    /**
     * 用户新增
     *
     * @param user
     * @return
     */
    HttpResult userCreate(User user);

    /**
     * 用户删除
     *
     * @param userId
     * @return
     */
    HttpResult userDelete(Integer userId);

    /**
     * 用户密码重置
     *
     * @param id
     * @return
     */
    HttpResult userPwdReset(Integer id);


    /**
     * 用户密码修改
     *
     * @param id
     * @param password
     * @param passwordNew
     * @return
     */
    HttpResult userPwdEdit(Integer id, String password, String passwordNew);


    /**
     * 用户昵称修改
     *
     * @param id
     * @param nickName
     * @return
     */
    HttpResult userNickNameEdit(Integer id, String nickName);


    /**
     * 用户角色修改
     *
     * @param id
     * @param roleId
     * @return
     */
    HttpResult userRoleEdit(Integer id, Integer roleId);





}
