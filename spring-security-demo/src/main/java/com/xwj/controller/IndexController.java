package com.xwj.controller;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xwj.entity.User;
import com.xwj.service.IUserService;

@RestController
@RequestMapping
public class IndexController {

	@Autowired
	private IUserService userService;

	/**
	 * 不需要认证的请求
	 */
	@GetMapping("/noAuth")
	public String noAuth() {
		return "noAuth";
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