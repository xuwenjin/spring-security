package com.xwj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 配置资源服务器
 */
@Configuration
@EnableResourceServer // 开启资源服务
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// index路径下的请求不用授权，直接请求
				.antMatchers("/index/**").permitAll()

				// role路径下的请求，授权通过后，user和admin角色中的任何一个都可以访问
				.antMatchers("/role/**").hasAnyRole("user", "admin")

				// 同一个路径下，不能配置两次，否则只有第一个生效

				// // user路径下的请求，授权通过后，admin角色都能访问
				// .antMatchers("/user/**").hasRole("admin")

				// user路径下的请求，授权通过后，只有user角色可以访问，且只能访问get方法
				.antMatchers(HttpMethod.GET, "/user/**").hasRole("user")

				// 其它的请求，只有权限认证通过才能访问
				.anyRequest().authenticated();
	}

}
