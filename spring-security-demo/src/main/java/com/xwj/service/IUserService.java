package com.xwj.service;

import java.util.List;

import com.xwj.entity.AuthUserInfo;

public interface IUserService {

	List<AuthUserInfo> findAll();

	AuthUserInfo findById(Long id);

	AuthUserInfo save(AuthUserInfo info);

	AuthUserInfo findByUsername(String username);

}
