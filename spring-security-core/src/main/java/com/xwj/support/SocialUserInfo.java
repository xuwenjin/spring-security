package com.xwj.support;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialUserInfo {

	/** 第三方id。如qq、weixin */
	private String providerId;

	/** 第三方用户id，也就是openId */
	private String providerUserId;

	/** 昵称 */
	private String nickname;

	/** 头像 */
	private String headimg;

}
