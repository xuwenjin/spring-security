package com.xwj.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * security相关配置(不能取名SecurityProperties，因为security中也有这个类名，不然会报错)
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "xwj.security")
public class SecurityProperty {

	private BrowserProperty browser = new BrowserProperty();
	
	private SocialProperty social = new SocialProperty();
	
	private OAuth2Property oauth2 = new OAuth2Property();

}
