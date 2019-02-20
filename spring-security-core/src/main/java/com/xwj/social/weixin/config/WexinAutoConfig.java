package com.xwj.social.weixin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

import com.xwj.properties.SecurityProperty;
import com.xwj.properties.WeixinProperty;
import com.xwj.social.weixin.connect.WeixinConnectionFactory;

@Configuration
@ConditionalOnProperty(prefix = "xwj.security.social.weixin", name = "app-id")
public class WexinAutoConfig extends SocialAutoConfigurerAdapter {

	@Autowired
	private SecurityProperty securityProperty;

	/**
	 * 创建连接工厂
	 */
	@Override
	public ConnectionFactory<?> createConnectionFactory() {
		WeixinProperty weixinProperty = securityProperty.getSocial().getWeixin();
		return new WeixinConnectionFactory(weixinProperty.getProviderId(), weixinProperty.getAppId(),
				weixinProperty.getAppSecret());
	}

}
