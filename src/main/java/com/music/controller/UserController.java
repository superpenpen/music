package com.music.controller;



import com.music.entity.User;
import com.music.service.IUserService;
import com.music.util.HttpResult;
import com.music.util.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  用户管理
 * </p>
 *
 * @author Xiep
 * @since 2020-08-28
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    IUserService userService;


    /**
     * 分页查询用户
     *
     * @param userName
     * @param nickName
     * @param roleId
     * @param page
     * @param size
     * @param request
     * @return
     */
    @PostMapping("/pageQuery")
    public HttpResult getUsersPageQuery(String userName,
                                        String nickName,
                                        Integer roleId,
                                        Integer page,
                                        Integer size,
                                        HttpServletRequest request) {
        try{
            return userService.getUsersPageQuery(request, userName, nickName, roleId, page, size);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error(HttpStatus.SYSTEM_INNER_ERROR.getCode(), HttpStatus.SYSTEM_INNER_ERROR.getMessage());
        }
    }


    /**
     * 用户创建
     *
     * @param user
     * @return
     */
    @PostMapping("/create")
    public HttpResult userCreate(User user) {
        try{
            return userService.userCreate(user);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error(HttpStatus.SYSTEM_INNER_ERROR.getCode(), HttpStatus.SYSTEM_INNER_ERROR.getMessage());
        }
    }


    /**
     * 用户删除
     *
     * @param userId
     * @return
     */
    @PostMapping("/delete")
    @Transactional(rollbackFor = Exception.class)
    public HttpResult userDelete(Integer userId) {
        try{
            return userService.userDelete(userId);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error(HttpStatus.SYSTEM_INNER_ERROR.getCode(), HttpStatus.SYSTEM_INNER_ERROR.getMessage());
        }
    }


    /**
     * 用户密码重置
     *
     * @param id
     * @return
     */
    @PostMapping("/reset/pwd")
    public HttpResult userPwdEdit(Integer id) {
        try{
            return userService.userPwdReset(id);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error(HttpStatus.SYSTEM_INNER_ERROR.getCode(), HttpStatus.SYSTEM_INNER_ERROR.getMessage());
        }
    }


    /**
     * 用户密码修改
     *
     * @param id
     * @param password
     * @param passwordNew
     * @return
     */
    @PostMapping("/edit/pwd")
    public HttpResult userPwdReset(Integer id, String password, String passwordNew) {
        try{
            return userService.userPwdEdit(id, password, passwordNew);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error(HttpStatus.SYSTEM_INNER_ERROR.getCode(), HttpStatus.SYSTEM_INNER_ERROR.getMessage());
        }
    }

    /**
     * 用户昵称修改
     *
     * @param id
     * @param nickName
     * @return
     */
    @PostMapping("/edit/nickName")
    public HttpResult userNickNameEdit(Integer id, String nickName) {
        try{
            return userService.userNickNameEdit(id, nickName);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error(HttpStatus.SYSTEM_INNER_ERROR.getCode(), HttpStatus.SYSTEM_INNER_ERROR.getMessage());
        }
    }


    /**
     * 用户角色修改
     *
     * @param id
     * @param roleId
     * @return
     */
    @PostMapping("/edit/role")
    public HttpResult userRoleEdit(Integer id, Integer roleId) {
        try{
            return userService.userRoleEdit(id, roleId);
        }catch (Exception e){
            e.printStackTrace();
            return HttpResult.error(HttpStatus.SYSTEM_INNER_ERROR.getCode(), HttpStatus.SYSTEM_INNER_ERROR.getMessage());
        }
    }



}
