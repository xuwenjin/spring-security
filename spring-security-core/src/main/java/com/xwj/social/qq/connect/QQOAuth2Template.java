package com.xwj.social.qq.connect;

import java.nio.charset.Charset;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QQOAuth2Template extends OAuth2Template {

	public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);

		// 设置为true后，发送请求时，会带上clientId和clientSecret
		setUseParametersForClientAuthentication(true);
	}

	/**
	 * spring Social中，获取accessToken请求返回类型默认是Map，而QQ的返回类型是字符串，现将其覆盖
	 */
	@Override
	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
		String response = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
		log.info("获取accessToken的响应：", response);

		// 分割响应字符串
		String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(response, "&");

		// StringUtils.substringAfterLast(str1, str2): 取str1内分隔符str2后的字符串
		String accessToken = StringUtils.substringAfterLast(items[0], "=");
		Long expiresIn = new Long(StringUtils.substringAfterLast(items[1], "="));
		String refreshToken = StringUtils.substringAfterLast(items[2], "=");

		return new AccessGrant(accessToken, null, refreshToken, expiresIn);
	}

	/**
	 * QQ返回的contentType是html/text，添加相应的HttpMessageConverter来处理
	 */
	@Override
	protected RestTemplate createRestTemplate() {
		RestTemplate restTemplate = super.createRestTemplate();
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return restTemplate;
	}

}
