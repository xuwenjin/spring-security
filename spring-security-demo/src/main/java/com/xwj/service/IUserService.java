package com.xwj.service;

import com.xwj.entity.AuthUserInfo;

public interface IUserService {

	AuthUserInfo findById(Long id);

	AuthUserInfo save(AuthUserInfo info);

	AuthUserInfo findByUsername(String username);

}
