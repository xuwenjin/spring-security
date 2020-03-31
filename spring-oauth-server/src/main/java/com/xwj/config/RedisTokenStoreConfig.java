package com.xwj.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * RedisTokenStore配置
 */
@Configuration
@ConditionalOnProperty(prefix = "xwj.security.oauth2", name = "storeType", havingValue = "redis")
public class RedisTokenStoreConfig {

	@Autowired
	private RedisConnectionFactory connectionFactory;

	/**
	 * 配置Token存储到Redis中
	 */
	@Bean
	public TokenStore redisTokenStore() {
		return new RedisTokenStore(connectionFactory);
	}

}
