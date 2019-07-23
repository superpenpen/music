package com.music.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.music.api.APIResponse;
import com.music.entity.Role;
import com.music.entity.User;

import java.util.List;

/**
 * User表数据服务层接口
 */
public interface IUserService extends IService<User> {

	/**
	 * 根据创建用户ID获取用户列表
	 * @param page 第几页
	 * @param pageSize 每页多少条记录
	 * @param userId 用户ID
	 * @return 用户列表
	 */
	List<User> getUsersById(String page, String pageSize, String userId);

	/**
	 * 根据创建用户ID获取用户数
	 * @param userId 创建用户ID
	 * @return 用户数
	 */
	int countUsersById(String userId);

    /**
     * 创建用户
     * @param user
     * @return
     */
    APIResponse insertUser(User user);

    /**
     * 修改用户
     * @param user
     * @return
     */
    APIResponse updateUser(User user);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(int id);

    /**
     * 更新用户状态
     * @param userId, status
     */
    void updateUserStatus(int userId, int status);

    /**
     * 查询数据库，获取角色列表(除超级管理员角色)
     * @return
     */
    List<Role> getAllRoles();

}