package com.xwj.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * qq配置类
 * 
 * @author xuwenjin 2019年1月26日
 */
@Getter
@Setter
public class QQProperty extends SocialProperties {

	/** 提供方id */
	private String providerId = "qq";

}
