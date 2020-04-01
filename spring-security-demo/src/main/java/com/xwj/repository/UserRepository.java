package com.xwj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xwj.entity.AuthUserInfo;

public interface UserRepository extends JpaRepository<AuthUserInfo, Long>{

}
