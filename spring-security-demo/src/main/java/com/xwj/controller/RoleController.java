package com.xwj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 只允许授权角色请求
 */
@RestController
@RequestMapping("role")
public class RoleController {

	/**
	 * 获取user角色资源
	 */
	@GetMapping("/getUserResource")
	public String getUserResource() {
		return "getUserResource";
	}

	/**
	 * 获取admin角色资源
	 */
	@GetMapping("/getAdminResource")
	public String getAdminResource() {
		return "getAdminResource";
	}

}