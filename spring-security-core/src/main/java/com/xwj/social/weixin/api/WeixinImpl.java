package com.xwj.social.weixin.api;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WeixinImpl extends AbstractOAuth2ApiBinding implements Weixin {

	/** 获取用户信息 */
	private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=%s";

	private ObjectMapper objectMapper = new ObjectMapper();

	public WeixinImpl(String accessToken) {
		// 默认是将accessToken放入请求头中，现在改为放入请求参数中
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
	}

	@Override
	public WeixinUserInfo getUserInfo(String openId) {
		// 通过openId获取用户信息
		String url = String.format(URL_GET_USER_INFO, openId);
		String result = getRestTemplate().getForObject(url, String.class);
		log.info("获取用户信息响应：{}", result);
		// 请求异常
		if (StringUtils.contains(result, "errcode")) {
			return null;
		}

		WeixinUserInfo userInfo = null;
		try {
			userInfo = objectMapper.readValue(result, WeixinUserInfo.class);
			return userInfo;
		} catch (Exception e) {
			throw new RuntimeException("获取用户信息失败", e);
		}
	}
	
	/**
	 * 默认注册的StringHttpMessageConverter字符集是ISO-8859-1，而微信返回的是UTF-8的，所以覆盖了原来的方法
	 */
	@Override
	protected List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
		messageConverters.remove(0);
		messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return messageConverters;
	}

}
