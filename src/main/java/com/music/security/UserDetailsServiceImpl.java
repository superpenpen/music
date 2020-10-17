package com.music.security;


import com.music.entity.User;
import com.music.service.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户登录认证信息查询
 *
 * @author Xiep
 * @date 2020/6/15 11:08
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.getUserByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        // 用户权限列表，根据用户拥有的权限标识
        // 与如 @PreAuthorize("hasAuthority('sys:menu:query')") 标注的接口对比，决定是否可以调用接口
//        Set<String> permissions = userService.getPermsByName(user.getName());
//        List<GrantedAuthority> grantedAuthorities = permissions.stream()
//                .map(GrantedAuthorityImpl::new).collect(Collectors.toList());
//        return new JwtUserDetails(user.getName(), user.getPassword(), user.getSalt(), grantedAuthorities);
        return new JwtUserDetails(user.getUserName(), user.getPassword(), user.getSalt(), null);
    }
}
