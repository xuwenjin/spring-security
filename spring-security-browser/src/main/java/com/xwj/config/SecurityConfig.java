package com.xwj.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import com.xwj.consts.SecurityConst;
import com.xwj.handler.LoginFailureHandler;
import com.xwj.handler.LoginSuccessHandler;
import com.xwj.properties.BrowserProperty;
import com.xwj.properties.SecurityProperty;

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(SecurityProperty.class) // 开启配置文件
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityProperty securityProperty;

	@Autowired
	private LoginSuccessHandler loginSuccessHandler; // 登录成功处理

	@Autowired
	private LoginFailureHandler loginFailureHandler; // 登录失败处理

	@Autowired
	private UserDetailsService myUserDetailServiceImpl; // 用户信息服务

	@Autowired
	private DataSource dataSource; // 数据源

	@Autowired
	private SpringSocialConfigurer socialSecurityConfig;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		BrowserProperty browser = securityProperty.getBrowser();

		this.applyPasswordAuthenticationConfig(http);

		http.apply(socialSecurityConfig) // 社交配置
				.and().rememberMe().userDetailsService(myUserDetailServiceImpl) // 设置userDetailsService
				.tokenRepository(persistentTokenRepository()) // 设置数据访问层
				.tokenValiditySeconds(browser.getRememberMeTime()) // 记住我的时间(秒)
				.and().authorizeRequests() // 对请求授权

				.antMatchers(SecurityConst.AUTH_REQUIRE, browser.getLoginPage(), browser.getSignUpUrl(), "/user/regist") //// 处理社交注册请求
				.permitAll() // 允许所有人访问login.html和自定义的登录页

				.antMatchers(HttpMethod.GET, "/user/**").hasRole("user") // user角色，只能访问/user/*下面的get请求
				.antMatchers("/auth").hasRole("admin") // admin角色，只能访问/auth请求

				.anyRequest().authenticated() // 任何请求，需要身份认证

				.and().csrf().disable() // 关闭跨站伪造保护

				.cors() // 开启跨域
		;
	}

	private void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
		// formLogin()是默认的登录表单页，如果不配置 loginPage(url)，则使用 spring security
		// 默认的登录页，如果配置了 loginPage()则使用自定义的登录页
		http.formLogin() // 表单登录
				.loginPage(SecurityConst.AUTH_REQUIRE).loginProcessingUrl(SecurityConst.AUTH_FORM) // 登录请求拦截的url,也就是form表单提交时指定的action
				.successHandler(loginSuccessHandler).failureHandler(loginFailureHandler).and();
	}

	/**
	 * 持久化token
	 * 
	 * Security中，默认是使用PersistentTokenRepository的子类InMemoryTokenRepositoryImpl，将token放在内存中
	 * 如果使用JdbcTokenRepositoryImpl，会创建表persistent_logins，将token持久化到数据库
	 */
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource); // 设置数据源
		// tokenRepository.setCreateTableOnStartup(true); // 启动创建表，创建成功后注释掉
		return tokenRepository;
	}

}
