package com.xwj.social.qq.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

import com.xwj.social.qq.api.QQ;
import com.xwj.social.qq.api.impl.QQImpl;

/**
 * 连接服务提供商
 * 
 * @author xwj
 *
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

	// 获取Authorization Code
	private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

	// 通过Authorization Code获取Access Token
	private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

	private String appId;

	public QQServiceProvider(String appId, String appSecret) {
		super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
	}

	@Override
	public QQ getApi(String accessToken) {
		return new QQImpl(accessToken, appId);
	}

}