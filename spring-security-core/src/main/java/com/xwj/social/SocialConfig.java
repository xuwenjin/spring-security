package com.xwj.social;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import com.xwj.properties.SecurityProperty;

/**
 * 社交登录配置主类
 * 
 * @author xwj
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SecurityProperty securityProperty;

	@Autowired(required = false)
	private ConnectionSignUp connectionSignUp;

	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		/**
		 * textEncryptor： 存到数据库时的加密方式。Encryptors.noOpText()表示不加密
		 */
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
				connectionFactoryLocator, Encryptors.noOpText());
		repository.setTablePrefix("xwj_"); // 设置表名前缀

		// 如果服务调用方配置了ConnectionSignUp的实现类
		if (connectionSignUp != null) {
			// 用户授权后，不需要注册，直接登录
//			repository.setConnectionSignUp(connectionSignUp);
		}

		return repository;
	}

	/**
	 * 将SocialAuthenticationFilter放到Security的过滤器链中
	 */
	@Bean
	public SpringSocialConfigurer socialSecurityConfig() {
		// 设置拦截url
		String filterProcessesUrl = securityProperty.getSocial().getFilterProcessesUrl();
		MySpringSocialConfigurer config = new MySpringSocialConfigurer(filterProcessesUrl);
		// 设置注册页地址
		config.signupUrl(securityProperty.getBrowser().getSignUpUrl());
		return config;
	}

	/**
	 * spring Social工具类。有两个目的： 1、注册过程中，获取Social信息 2、注册完成后，把用户id传给Social
	 */
	@Bean
	public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
		return new ProviderSignInUtils(connectionFactoryLocator,
				getUsersConnectionRepository(connectionFactoryLocator));
	}

}
