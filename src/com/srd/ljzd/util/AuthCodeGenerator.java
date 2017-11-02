package com.srd.ljzd.util;

import java.util.Random;

public class AuthCodeGenerator {

	public static final String IMPOSSIBLE_CODE = "IMPOSSIBLE_CODE";
	public static String generateAuthCode(){
		//验证码：6位随机数字
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		buffer.append(random.nextInt(10))
		.append(random.nextInt(10))
		.append(random.nextInt(10))
		.append(random.nextInt(10));
		return buffer.toString();
	}
}
