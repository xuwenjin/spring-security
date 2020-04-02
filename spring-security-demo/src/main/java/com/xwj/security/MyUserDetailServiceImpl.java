package com.xwj.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import com.xwj.entity.AuthUserInfo;
import com.xwj.service.IUserService;

import lombok.extern.slf4j.Slf4j;

/**
 * UserDetailsService用于返回用户相关数据
 */
@Slf4j
@Service
public class MyUserDetailServiceImpl implements UserDetailsService, SocialUserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
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
			log.error("User '" + username + "' not found");
			throw new BadCredentialsException("User '" + username + "' not found");
		}

		// 密码加密
		String dbPassword = passwordEncoder.encode(userInfo.getPassword());

		// 用户角色
		List<? extends GrantedAuthority> authorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_" + userInfo.getRole());

		// TODO 判断是否过期

		return new SocialUser(username, dbPassword, true, true, true, true, authorities);
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		// 密码加密(这里是将密码写死的，真实情况应该是查询数据库)
		String dbPassword = passwordEncoder.encode("1234");

		// 默认授权角色
		List<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		return new SocialUser(userId, dbPassword, true, true, true, true, authorities);
	}

}
