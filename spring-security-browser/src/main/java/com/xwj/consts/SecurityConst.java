package com.xwj.consts;

/**
 * Security相关常量类
 */
public interface SecurityConst {

	/**
	 * 默认登录页url
	 */
	String AUTH_REQUIRE = "/authentication/require";

	/**
	 * 登录页请求url
	 */
	String AUTH_FORM = "/authentication/form";

	/**
	 * 自定义社交social拦截地址，默认/auth (SocialAuthenticationFilter)
	 */
	String DEFAULT_SOCIAL_PROCESS_URL = "/login";

	/**
	 * 提供商的ID
	 */
	String DEFAULT_SOCIAL_QQ_PROVIDER_ID = "qq";

	/**
	 * 提供商的ID
	 */
	String DEFAULT_SOCIAL_WEIXIN_PROVIDER_ID = "weixin";

}
