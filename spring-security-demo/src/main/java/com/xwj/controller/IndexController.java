package com.xwj.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xwj.entity.User;
import com.xwj.properties.SecurityProperty;
import com.xwj.service.IUserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping
public class IndexController {

	@Autowired
	private IUserService userService;
	@Autowired
	private SecurityProperty securityProperty;

	/**
	 * 不需要认证的请求
	 */
	@GetMapping("/noAuth")
	public String noAuth() {
		return "noAuth";
	}

	/**
	 * 需要认证的请求
	 */
	@GetMapping("/user")
	public String user() {
		return "user";
	}

	/**
	 * 解析jwt
	 */
	@GetMapping("/jwt/parse")
	public Claims parseJwtToken() {
		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1ODU2MzUyODgsInVzZXJfbmFtZSI6Inh3aiIsImF1dGhvcml0aWVzIjpbImFkbWluIiwiUk9MRV9VU0VSIl0sImp0aSI6IjJlZjU3ZmFjLTEwOTYtNGFjYi04ZmNhLTE1NDQzMTViYTA2ZiIsImNsaWVudF9pZCI6InRlc3QiLCJzY29wZSI6WyJyZWFkIl19.vuPRShOdls7sAAMW85qns-3475g25nlA8g9mpXntFrU";
		return Jwts.parser().setSigningKey(securityProperty.getOauth2().getJwtSignKey()).parseClaimsJws(token)
				.getBody();
	}

	/**
	 * 获取认证信息
	 */
	@GetMapping("/me")
	public Authentication authentication(Authentication authentication) {
		return authentication;
	}

	@GetMapping("/find/{id}")
	public User findById(@PathVariable Long id) {
		return userService.findById(id);
	}

}