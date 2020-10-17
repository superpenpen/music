package com.music.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * 权限封装
 *
 * @author Xiep
 * @date 2020/6/15 11:02
 **/
public class GrantedAuthorityImpl implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "GrantedAuthorityImpl{" +
                "authority='" + authority + '\'' +
                '}';
    }
}