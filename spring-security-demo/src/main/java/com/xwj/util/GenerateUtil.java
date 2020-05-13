package com.xwj.util;

import java.nio.charset.StandardCharsets;

import javax.xml.bind.DatatypeConverter;

public class GenerateUtil {

	/**
	 * 通过clientId和clientSecret生成授权头
	 */
	public static String createBasic(String clientId, String clientSecret) {
		String auth = clientId.concat(":").concat(clientSecret);
		byte[] val = auth.getBytes(StandardCharsets.UTF_8);
		return DatatypeConverter.printBase64Binary(val);
	}

	public static void main(String[] args) {
		String result = createBasic("test", "testsecret");
		System.out.println("Authorization.Basic --> " + result);
	}

}
