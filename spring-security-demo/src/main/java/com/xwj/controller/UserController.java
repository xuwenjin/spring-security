package com.xwj.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.xwj.entity.AuthUserInfo;
import com.xwj.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ProviderSignInUtils providerSignInUtils;
	@Autowired
	private IUserService userService;

	/**
	 * 查询所有用户
	 */
	@GetMapping("findAll")
	public List<AuthUserInfo> findAll() {
		return userService.findAll();
	}

	/**
	 * 查询用户信息
	 */
	@GetMapping("find/{id}")
	public AuthUserInfo findById(@PathVariable String id) {
		return userService.findById(Long.valueOf(id));
	}

	/**
	 * 保存用户信息
	 */
	@PostMapping("save")
	public AuthUserInfo save(@RequestBody AuthUserInfo info) {
		return userService.save(info);
	}

	/**
	 * 用户注册
	 * 
	 * @param user
	 */
	@PostMapping("/regist")
	public void regist(AuthUserInfo user, HttpServletRequest request) {
		// 用户唯一标识
		String userId = user.getUsername();
		// 通过用户id注册
		providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
	}

}