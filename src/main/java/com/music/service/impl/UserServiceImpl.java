package com.music.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.music.constant.IntConstants;
import com.music.entity.User;

import com.music.mapper.UserMapper;
import com.music.security.JwtTokenUtils;
import com.music.service.IUserService;
import com.music.util.HttpResult;
import com.music.util.HttpStatus;
import com.music.util.PasswordUtils;
import com.music.util.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Xiep
 * @since 2020-08-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    UserMapper userMapper;



    @Override
    public User getUserByUserName(String userName){
        return userMapper.getUserByUserName(userName);
    }

    @Override
    public HttpResult getUsersPageQuery(HttpServletRequest request,
                                        String userName, String nickName, Integer roleId, Integer page, Integer size){
        if (StringUtils.isEmptyBatch(page, size)){
            return HttpResult.error(HttpStatus.SYSTEM_PARAM_NOT_COMPLETE.getCode(),
                    HttpStatus.SYSTEM_PARAM_NOT_COMPLETE.getMessage());
        }
        String requestUserName = JwtTokenUtils.getUserNameFromToken(request);
        User u = getUserByUserName(requestUserName);
        Page<User> userPage =new Page<>(page, size);
        String join = null;
        if (u.getRoleId() == IntConstants.ROLE_SUPER_ADMIN){
            // 超级管理员
        }else if (u.getRoleId() == IntConstants.ROLE_ADMIN){
            //普通管理员
            join = " role_id != 0";
        }else if (u.getRoleId() == IntConstants.ROLE_NORMAL_USER){
            // 普通用户
            join = " role_id = 2";
        }
        Page<User> pageResult = userPage.setRecords(
                userMapper.selectUserPage(userPage, userName, nickName, roleId, join));
        return HttpResult.ok(pageResult);

    }

    @Override
    public HttpResult userCreate(User user){
        if (StringUtils.isEmptyBatch(user.getUserName(), user.getNickName())|| StringUtils.isEmpty(user.getRoleId())){
            return HttpResult.error(HttpStatus.SYSTEM_PARAM_NOT_COMPLETE.getCode(), HttpStatus.SYSTEM_PARAM_NOT_COMPLETE.getMessage());
        }
        User u = getUserByUserName(user.getUserName());
        if (u != null) {
            return HttpResult.error(HttpStatus.USER_NAME_EXISTS.getCode(), HttpStatus.USER_NAME_EXISTS.getMessage());
        } else {
            // 新建用户默认密码123456
            user.setPassword("123456");
            String salt = PasswordUtils.getSalt();
            String password = PasswordUtils.encode(user.getPassword(), salt);
            user.setSalt(salt);
            user.setPassword(password);
            userMapper.insert(user);
            return HttpResult.okWithoutData();
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public HttpResult userDelete(Integer userId){
        if (StringUtils.isEmpty(userId)){
            return HttpResult.error(HttpStatus.SYSTEM_PARAM_NOT_COMPLETE.getCode(), HttpStatus.SYSTEM_PARAM_NOT_COMPLETE.getMessage());
        }
        userMapper.deleteById(userId);
        return HttpResult.okWithoutData();
    }


    @Override
    public HttpResult userPwdReset(Integer id){
        if (StringUtils.isEmpty(id)){
            return HttpResult.error(HttpStatus.SYSTEM_PARAM_NOT_COMPLETE.getCode(), HttpStatus.SYSTEM_PARAM_NOT_COMPLETE.getMessage());
        }
        User user = userMapper.selectById(id);
        String password = PasswordUtils.encode("123456", user.getSalt());
        userMapper.pwdReset(id, password);
        return HttpResult.okWithoutData();
    }

    @Override
    public HttpResult userPwdEdit(Integer id, String password, String passwordNew){
        if (StringUtils.isEmpty(id)|| StringUtils.isEmptyBatch(password, passwordNew)){
            return HttpResult.error(HttpStatus.SYSTEM_PARAM_NOT_COMPLETE.getCode(), HttpStatus.SYSTEM_PARAM_NOT_COMPLETE.getMessage());
        }
        User user = userMapper.selectById(id);
        String passwordRequest = PasswordUtils.encode(password, user.getSalt());
        if (user.getPassword().equals(passwordRequest) ){
                passwordNew = PasswordUtils.encode(passwordNew, user.getSalt());
        }else{
            return HttpResult.error(HttpStatus.SYSTEM_USER_PASSWORD_ERROR.getCode(),
                    HttpStatus.SYSTEM_USER_PASSWORD_ERROR.getMessage());
        }
        userMapper.pwdReset(id, passwordNew);
        return HttpResult.okWithoutData();
    }

    @Override
    public HttpResult userNickNameEdit(Integer id, String nickName){
        if (StringUtils.isEmpty(id)|| StringUtils.isEmpty(nickName)){
            return HttpResult.error(HttpStatus.SYSTEM_PARAM_NOT_COMPLETE.getCode(), HttpStatus.SYSTEM_PARAM_NOT_COMPLETE.getMessage());
        }
        userMapper.updateNickName(id, nickName);
        return HttpResult.okWithoutData();
    }

    @Override
    public HttpResult userRoleEdit(Integer id, Integer roleId){
        if (StringUtils.isEmptyBatch(id, roleId)){
            return HttpResult.error(HttpStatus.SYSTEM_PARAM_NOT_COMPLETE.getCode(), HttpStatus.SYSTEM_PARAM_NOT_COMPLETE.getMessage());
        }
        userMapper.updateRoleId(id, roleId);
        return HttpResult.okWithoutData();
    }






}







