package com.xwj.properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuth2Property {

	private String jwtSignKey = "123456";

	private OAuth2ClientProperty[] clients = {};

}
