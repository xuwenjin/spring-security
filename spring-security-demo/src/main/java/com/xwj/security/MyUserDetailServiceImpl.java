package com.xwj.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import com.xwj.entity.AuthUserInfo;
import com.xwj.service.IUserService;

/**
 * 如果使用密码模式，需要实现UserDetailsService，用于覆盖默认的InMemoryUserDetailsManager方法
 * 
 * 可以用来校验用户信息，并且可以添加自定义的用户属性
 */
@Service
public class MyUserDetailServiceImpl implements UserDetailsService, SocialUserDetailsService {

	@Autowired
	private IUserService userService;

	/**
	 * 根据username查询用户实体
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 通过用户名查询数据
		AuthUserInfo userInfo = userService.findByUsername(username);
		if (userInfo == null) {
			throw new BadCredentialsException("User '" + username + "' not found");
		}

		// 用户角色
		List<? extends GrantedAuthority> authorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_" + userInfo.getRole());

		return new SocialUser(username, userInfo.getPassword(), true, true, true, true, authorities);
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		// 通过用户名查询数据
		AuthUserInfo userInfo = userService.findByUsername(userId);
		if (userInfo == null) {
			throw new BadCredentialsException("User '" + userId + "' not found");
		}

		// 默认授权角色
		List<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		return new SocialUser(userId, userInfo.getPassword(), true, true, true, true, authorities);
	}

}
