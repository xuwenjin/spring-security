package com.xwj.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * Social相关配置类
 * 
 * @author xwj
 *
 */
@Getter
@Setter
public class SocialProperty {

	/** 默认拦截请求url */
	private String filterProcessesUrl = "/openLogin";

	private QQProperty qq = new QQProperty();

	private WeixinProperty weixin = new WeixinProperty();

}
