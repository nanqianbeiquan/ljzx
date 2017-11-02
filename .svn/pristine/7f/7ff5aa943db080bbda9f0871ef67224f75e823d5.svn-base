package com.srd.ljzd.util;

import java.util.Random;

public class PWDGenerator {

	public static String generatePWD(String prefix){
		//验证码：6位随机数字
		StringBuffer buffer = new StringBuffer(prefix);
		
		Random random = new Random();
		
		buffer.append(random.nextInt(10))
		.append(random.nextInt(10))
		.append(random.nextInt(10))
		.append(random.nextInt(10))
		.append(random.nextInt(10))
		.append(random.nextInt(10));
		
		return buffer.toString();
	}
}
