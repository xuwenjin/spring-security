package com.xwj.social.weixin.api;

public interface Weixin {

	/**
	 * 获取用户信息
	 */
	WeixinUserInfo getUserInfo(String openId);

}
