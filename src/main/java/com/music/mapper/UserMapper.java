package com.music.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.music.entity.Role;
import com.music.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User 表数据库控制层接口
 */
@Repository
@Mapper
public interface UserMapper extends SuperMapper<User> {

    /**
     * 查询User信息-分页查询
     */
    List<User> selectUserList(Page<User> page,
                              @Param("user") User user,
                              @Param("orderName") String orderName,
                              @Param("sort") String sort);

    /**
     * 根据用户名(username)和密码(password)查询对应的用户
     */
    JSONObject getUserJson(@Param("username") String username, @Param("password") String password);



    /**
     * 根据起始页(start),每页显示数目(limit)分页显示所有用户
     */
    @Select("SELECT * FROM `user` WHERE id!=1 LIMIT #{start}, #{limit}")
    List<User> getAllUsers(@Param("start") int start, @Param("limit") int limit);

    /**
     * 根据创建用户Id(create_user_id),起始页(start),每页显示数目(limit)分页显示该用户所创建的用户
     */
    @Select("SELECT * FROM `user` WHERE create_user_id=#{userId} LIMIT #{start}, #{limit}")
    List<User> getUsersById(@Param("userId") int userId, @Param("start") int start, @Param("limit") int limit);

    /**
     * 计算出所有用户数
     */
    @Select("SELECT COUNT(*) FROM `user` WHERE id!= 1 AND status=1")
    int countAllUsers();

    /**
     * 根据创建用户Id(create_user_id)计算出该用户所创建的用户数
     */
    @Select("SELECT COUNT(*) FROM `user` WHERE create_user_id=#{userId} AND status=1")
    int countUsersById(@Param("userId") int userId);

    /**
     * 查询User信息-用户名
     */
    @Select("SELECT * FROM `user` WHERE username=#{username} AND status=1")
    User getUserByUserName(@Param("username") String username);

    /**
     * 根据角色Id获取角色名称(role_name)
     */
    @Select("SELECT role_name FROM `role` WHERE id=#{roleId}")
    String getRoleNameById(@Param("roleId") int roleId);

    /**
     * 根据用户Id删除用户
     */
    @Delete("DELETE FROM `user` WHERE id=#{id}")
    void deleteUser(@Param("id") int id);

    /**
     * 根据用户Id更新用户状态
     */
    @Delete("UPDATE `user` SET status=#{status} WHERE id=#{userId}")
    void updateUserStatus(@Param("userId") int userId, @Param("status") int status);

    /**
     * 查询超级管理员之外所有的角色
     */
    @Select("SELECT * FROM `role` WHERE id!=1")
    List<Role> getAllRoles();

    /**
     * 查询超级管理员之外所有该用户创建的角色
     */
    @Select("SELECT * FROM `role` WHERE create_user_id=#{userId} AND id!=1")
    List<Role> getRolesById(@Param("userId") int userId);

}