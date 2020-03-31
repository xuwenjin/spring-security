package com.xwj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import com.xwj.properties.SecurityProperty;

/**
 * JwtTokenStore配置
 */
@Configuration
@ConditionalOnProperty(prefix = "xwj.security.oauth2", name = "storeType", havingValue = "jwt", matchIfMissing = true)
public class JwtTokenStoreConfig {

	@Autowired
	private SecurityProperty securityProperty;

	@Bean
	public JwtTokenStore jwtTokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
		// 设置生成jwt的秘钥
		jwtAccessTokenConverter.setSigningKey(securityProperty.getOauth2().getJwtSignKey());
		return jwtAccessTokenConverter;
	}

}
