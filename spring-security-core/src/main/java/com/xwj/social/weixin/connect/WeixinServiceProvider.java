package com.xwj.social.weixin.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import com.xwj.social.weixin.api.Weixin;
import com.xwj.social.weixin.api.WeixinImpl;

/**
 * 服务提供商
 * 
 * @author xuwenjin 2019年2月14日
 */
public class WeixinServiceProvider extends AbstractOAuth2ServiceProvider<Weixin> {

	/** 获取授权码code */
	private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";

	/** 通过code获取access_token */
	private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

	public WeixinServiceProvider(String appId, String appSecret) {
		super(new WeixinOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
	}

	@Override
	public Weixin getApi(String accessToken) {
		return new WeixinImpl(accessToken);
	}

}
