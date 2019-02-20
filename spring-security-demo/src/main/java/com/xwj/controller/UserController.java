package com.xwj.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.xwj.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ProviderSignInUtils providerSignInUtils;

	/**
	 * 用户注册
	 * 
	 * @param user
	 */
	@PostMapping("/regist")
	public void regist(User user, HttpServletRequest request) {
		// 用户唯一标识
		String userId = user.getUsername();
		// 通过用户id注册
		providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
	}

}