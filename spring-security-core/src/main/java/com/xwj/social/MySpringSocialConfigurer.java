package com.xwj.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class MySpringSocialConfigurer extends SpringSocialConfigurer {

	private String filterProcessesUrl;

	@SuppressWarnings("unchecked")
	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
		// 改变拦截url
		filter.setFilterProcessesUrl(filterProcessesUrl);
		return (T) filter;
	}

}
