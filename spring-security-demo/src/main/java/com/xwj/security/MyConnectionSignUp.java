package com.xwj.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

@Component
public class MyConnectionSignUp implements ConnectionSignUp {

	/**
	 * 用户授权后，不需要注册，直接登录
	 */
	@Override
	public String execute(Connection<?> connection) {
		// 根据社交用户信息默认创建用户并返回用户唯一标识
		// 暂时用displayName作为唯一标识，项目中根据实际业务定
		return connection.getDisplayName();
	}

}
