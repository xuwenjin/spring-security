package com.xwj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xwj.properties.SecurityProperty;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("index")
public class IndexController {

	@Autowired
	private SecurityProperty securityProperty;

	/**
	 * 获取资源
	 */
	@GetMapping("/getResource")
	public String getResource() {
		return "OK";
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
	 * 获取当前授权用户
	 */
	@GetMapping("/me")
	public Object getCurrrentUser(@AuthenticationPrincipal UserDetails user) {
		return user;
	}

}