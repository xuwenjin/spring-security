package com.xwj.social.weixin.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;

import com.xwj.social.weixin.api.Weixin;

/**
 * 微信连接工厂，用来创建Connect
 * 
 * @author xuwenjin 2019年2月14日
 */
public class WeixinConnectionFactory extends OAuth2ConnectionFactory<Weixin> {

	public WeixinConnectionFactory(String providerId, String appId, String appSecret) {
		/**
		 * serviceProvider 用于执行授权流和获取本机服务API实例的ServiceProvider模型
		 * apiAdapter适配器，用于将不同服务提供商的个性化用户信息映射到 Connection
		 */
		super(providerId, new WeixinServiceProvider(appId, appSecret), new WeixinAdapter());
	}

	/**
	 * 由于微信的openId是和accessToken一起返回的，所以在这里直接通过accessToken设置providerUserId即可
	 */
	@Override
	protected String extractProviderUserId(AccessGrant accessGrant) {
		if (accessGrant instanceof WeixinAccessGrant) {
			return ((WeixinAccessGrant) accessGrant).getOpenId();
		}
		return null;
	}

	/**
	 * 覆盖连接方式
	 */
	@Override
	public Connection<Weixin> createConnection(AccessGrant accessGrant) {
		String providerUserId = extractProviderUserId(accessGrant);
		return new OAuth2Connection<>(getProviderId(), providerUserId, accessGrant.getAccessToken(),
				accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getOAuth2ServiceProvider(),
				getApiAdapter(providerUserId));
	}

	/**
	 * 覆盖连接方式
	 */
	@Override
	public Connection<Weixin> createConnection(ConnectionData data) {
		return new OAuth2Connection<>(data, getOAuth2ServiceProvider(), getApiAdapter(data.getProviderUserId()));
	}

	/**
	 * 新建WeixinAdapter对象。每个openId都会创建自己的WeixinAdapter
	 */
	private ApiAdapter<Weixin> getApiAdapter(String providerUserId) {
		return new WeixinAdapter(providerUserId);
	}

	private WeixinServiceProvider getOAuth2ServiceProvider() {
		return (WeixinServiceProvider) getServiceProvider();
	}

}
