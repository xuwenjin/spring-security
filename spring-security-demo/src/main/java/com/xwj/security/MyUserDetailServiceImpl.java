package com.xwj.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import lombok.extern.slf4j.Slf4j;

/**
 * UserDetailsService用于返回用户相关数据
 */
@Slf4j
@Service
public class MyUserDetailServiceImpl implements UserDetailsService, SocialUserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	private List<? extends GrantedAuthority> authorities = AuthorityUtils
			.commaSeparatedStringToAuthorityList("admin, ROLE_USER");

	/**
	 * 根据username查询用户实体
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("登录用户名:{}", username);
		// return new MyUser(username, dbPassword);
		return buildUser(username);
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		log.info("登录用户名:{}", userId);
		return buildUser(userId);
	}

	private SocialUserDetails buildUser(String userId) {
		// 密码加密(这里是将密码写死的，真实情况应该是查询数据库)
		String dbPassword = passwordEncoder.encode("1234");
		log.info("数据库密码{}", dbPassword);
		return new SocialUser(userId, dbPassword, true, true, true, true, authorities);
	}

}
