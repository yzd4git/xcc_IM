package com.zdh.core.util;

import java.security.MessageDigest;
import java.security.SecureRandom;

public class MD5Util {

	public static String getRandom() {

		SecureRandom secureRandom = new SecureRandom();
		byte[] salt = new byte[24];
		secureRandom.nextBytes(salt);
		return new String(salt);
		// int num = secureRandom.nextInt();
		// System.out.println("随机产生的字符为：" + num);

	}

	public static String md5Encode(String inStr) throws Exception {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}

		byte[] byteArray = inStr.getBytes("UTF-8");
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	/**
	 * 测试主函数
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		
		System.out.println(md5Encode("13120215658"));
	}
}
