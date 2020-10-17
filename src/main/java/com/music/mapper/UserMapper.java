package com.music.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Xiep
 * @since 2020-08-31
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名称查询用户
     *
     * @param userName 用户名称
     * @return User
     */
    User getUserByUserName(@Param("userName") String userName);


    /**
     * 分页获取用户
     *
     * @param page
     * @param userName
     * @param nickName
     * @param roleId
     * @return
     */
    List<User> selectUserPage(Page<User> page,
                              @Param("userName") String userName,
                              @Param("nickName") String nickName,
                              @Param("roleId") Integer roleId,
                              @Param("join") String join);


    /**
     * 重置用户登录密码
     *
     * @param id
     */
    void pwdReset(@Param("id") Integer id,
                  @Param("password") String password);


    /**
     * 用户昵称更新
     *
     * @param id
     * @param nickName
     */
    void updateNickName(@Param("id") Integer id,
                  @Param("nickName") String nickName);


    /**
     * 用户角色更新
     *
     * @param id
     * @param roleId
     */
    void updateRoleId(@Param("id") Integer id,
                  @Param("roleId") Integer roleId);


}
