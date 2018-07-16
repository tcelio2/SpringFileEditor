package com.att.ub.auth.htpasswd;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

public class HtPasswdUserDetails implements UserDetails {

    private static final long serialVersionUID = -8056714367144971362L;

    private String username;
    private String password;
    private HtPasswdAuthorityRole role = HtPasswdAuthorityRole.ROLE_USER;
    
    public HtPasswdUserDetails(String username, String password) {
	this.username = username;
	this.password = password;
    }
    
    public HtPasswdUserDetails(String username, String password, HtPasswdAuthorityRole role) {
   	this.username = username;
   	this.password = password;
   	this.role = role;
       }
       
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	return AuthorityUtils.createAuthorityList(role.toString());
    }

    @Override
    public String getPassword() {
	return this.password;
    }

    @Override
    public String getUsername() {
	return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
	return true;
    }

    @Override
    public boolean isAccountNonLocked() {
	return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return true;
    }

    @Override
    public boolean isEnabled() {
	return true;
    }

}
