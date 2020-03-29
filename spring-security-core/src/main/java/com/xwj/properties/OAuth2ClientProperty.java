package com.xwj.properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuth2ClientProperty {

	private String clientId;

	private String clientSecret;

	/** 令牌有效时间(秒) */
	private int accessTokenValiditySeconds = 7200;

}
