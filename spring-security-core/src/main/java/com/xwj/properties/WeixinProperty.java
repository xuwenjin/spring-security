package com.xwj.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * 微信配置类
 * 
 * @author xuwenjin 2019年1月26日
 */
@Getter
@Setter
public class WeixinProperty extends SocialProperties {

	/** 提供方id */
	private String providerId = "weixin";

}
