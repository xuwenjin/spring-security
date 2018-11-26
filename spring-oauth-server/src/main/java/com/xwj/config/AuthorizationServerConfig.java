package com.xwj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * 配置认证服务器
 */
@Configuration
@EnableAuthorizationServer // 开启认证服务
public class AuthorizationServerConfig {

	@Bean
	AuthorizationServerConfigurer authorizationServerConfigurer() {
		return new AuthorizationServerConfigurerAdapter() {
			/**
			 * 用来配置客户端详情服务
			 */
			@Override
			public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
				clients.inMemory() // 使用in-memory存储
						.withClient("myid") // client_id
						.secret("mysecret")// client_secret
						.authorizedGrantTypes("authorization_code") // 该client允许的授权类型
						.scopes("read") // 允许的授权范围
						.autoApprove(true); // 自动审核
			}
		};
	}

}
