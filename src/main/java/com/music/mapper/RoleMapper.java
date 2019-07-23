package com.music.mapper;

import com.alibaba.fastjson.JSONObject;
import com.music.entity.Role;
import com.music.entity.RolePermission;
import com.music.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: Role 表数据库控制层接口
 * @Author: xiep
 * @Date: 2019/07/10 13:26
 **/
@Repository
@Mapper
public interface RoleMapper extends SuperMapper<Role> {

    /**
     * 根据起始页(start),每页显示数目(limit)分页显示所有角色
     */
    List<JSONObject> getAllRoles(@Param("start") int start, @Param("limit") int limit);

    /**
     * 根据创建用户Id(create_user_id),起始页(start),每页显示数目(limit)分页显示该用户所创建的角色
     */
    List<JSONObject> getRolesById(@Param("userId") int userId, @Param("start") int start, @Param("limit") int limit);

    /**
     * 根据创建用户Id(create_user_id)计算出该用户所创建的用户数
     */
    @Select("SELECT COUNT(*) FROM `role` WHERE id!=1")
    int countAllRoles();

    /**
     * 根据创建用户Id(create_user_id)计算出该用户所创建的用户数
     */
    @Select("SELECT COUNT(*) FROM `role` WHERE create_user_id=#{userId}")
    int countRolesById(@Param("userId") int userId);

    @Select("SELECT * FROM `role` WHERE role_name=#{roleName}")
    Role getRoleByRoleName(@Param("roleName") String roleName);

    /**
     * 根据创建用户Id(create_user_id)，创建角色
     */
    @Insert("INSERT INTO `role` (role_name, desc_info, create_user_id, create_time) " +
            "VALUES (#{roleName}, #{descInfo}, #{createUserId}, #{createTime})")
    void insertRole(@Param("roleName") String roleName, @Param("descInfo") String descInfo,
                    @Param("createUserId") int createUserId, @Param("createTime") String createTime);

    /**
     * 根据角色名称(role_name)和角色创建用户Id(create_user_id), 获取角色
     */
    @Select("SELECT * FROM `role` WHERE role_name=#{roleName} AND create_user_id=#{createUserId}")
    Role getRoleByName(@Param("roleName") String roleName, @Param("createUserId") int createUserId);

    /**
     * 根据角色Id(role_id)，在`role_permission`表中添加记录
     */
    void insertRolePermission(@Param("roleId") int roleId, @Param("permissions") List<Integer> permissions,
                              @Param("status") int status);

    /**
     * 修改角色信息
     */
    @Update("UPDATE `role` SET role_name=#{roleName}, desc_info=#{descInfo}, update_time=#{updateTime} WHERE id=#{roleId}")
    void updateRole(@Param("roleId") int roleId, @Param("roleName") String roleName,
                    @Param("descInfo") String descInfo, @Param("updateTime") String updateTime);

    /**
     * 根据角色ID(role_id)获取权限ID(permission_id)
     */
    @Select("SELECT permission_id FROM `role_permission` WHERE role_id=#{roleId} AND status=1")
    List<Integer> getPermissionIds(@Param("roleId") int roleId);

    /**
     * 修改role_permission记录
     */
    void updateRolePermission(@Param("roleId") int roleId, @Param("permissions") List<Integer> permissions,
                              @Param("status") int status);
    /**
     * 根据role_id和permission_id获取role_permission记录
     */
    @Select("SELECT * FROM `role_permission` WHERE role_id=#{roleId} AND permission_id=#{permissionId}")
    RolePermission getRolePermission(@Param("roleId") int roleId, @Param("permissionId") int permissionId);


    /**
     * 根据角色ID(role_id)获取用户列表
     */
    @Select("SELECT * FROM `user` WHERE role_id=#{roleId} AND status=1")
    List<User> getUsers(@Param("roleId") int roleId);

    /**
     * 根据角色ID(role_id)删除角色
     */
    @Delete("DELETE FROM `role` WHERE id=#{roleId}")
    void deleteRole(@Param("roleId") int roleId);

    /**
     * 根据角色ID(role_id)删除权限
     */
    @Delete("DELETE FROM `role_permission` WHERE role_id=#{roleId}")
    void deleteRoleAllPermission(@Param("roleId") int roleId);

    /**
     * 获取所有权限列表
     */
    List<JSONObject> getAllPermission();

}
