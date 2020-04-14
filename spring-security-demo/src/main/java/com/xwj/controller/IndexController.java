package com.xwj.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("index")
public class IndexController {

	/**
	 * 获取资源
	 */
	@GetMapping("/getResource")
	public String getResource() {
		return "OK";
	}

	/**
	 * 获取当前授权用户
	 */
	@GetMapping("/me")
	public Object getCurrrentUser(@AuthenticationPrincipal UserDetails user) {
		return user;
	}

}