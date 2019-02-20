package com.xwj.social.weixin.connect;

import org.springframework.social.oauth2.AccessGrant;

import lombok.Getter;
import lombok.Setter;

/**
 * 微信的access_token信息。与标准OAuth2协议不同，微信在获取access_token时会同时返回openId
 * 
 * 所以在这里继承了标准AccessGrant，添加了openId字段，作为对微信access_token信息的封装
 * 
 * @author xuwenjin 2019年2月14日
 */
@Setter
@Getter
public class WeixinAccessGrant extends AccessGrant {

	private static final long serialVersionUID = -3111493659444606437L;

	private String openId;

	public WeixinAccessGrant() {
		super("");
	}

	public WeixinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
		super(accessToken, scope, refreshToken, expiresIn);
	}

}
