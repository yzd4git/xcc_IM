package com.zdh.core.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

import net.sf.json.JSONObject;

public class Base64Util {
	
	public static void main(String[] args) {
		System.out.println(decode("eyJpbV91c2VybmFtZSI6IjRlMjgyNzM4NTFhYTQyM2U2NWM3Y2EzMTk2N2RhOGQwIn0="));
//		test2();
	}
	/**
	 * @param bytes
	 * @return
	 */
	public static String decode(final String string) {
		try {
			return new String(Base64.decodeBase64(string.getBytes("UTF-8")), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 二进制数据编码为BASE64字符串
	 * 
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	public static String encode(final String string) {
		try {
			return new String(Base64.encodeBase64(string.getBytes("UTF-8")), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void test() {
		JSONObject obj1 = new JSONObject();
		JSONObject obj2 = new JSONObject();

		obj1.put("id", "1001");
		obj1.put("name", "X3");
		obj1.put("sex", "Nan");
		System.out.println("obj1==" + obj1.toString());

		obj2.put("Data", obj1);
		System.out.println("obj2====" + obj2.toString());

		JSONObject inf = JSONObject.fromObject(obj2);
		System.out.println("通过键值得到的值： " + inf.get("Data"));

		System.out.println("经过加密后的obj2的值为： " + encode(obj2.toString()));//
		// //eyJEYXRhIjp7ImlkIjoiMTAwMSIsIm5hbWUiOiJYMyIsInNleCI6Ik5hbiJ9fQ==
		System.out.println("经过解密后的值为：__" + decode("eyJEYXRhIjp7ImlkIjoiMTAwMSIsIm5hbWUiOiJYMyIsInNleCI6Ik5hbiJ9fQ=="));
		JSONObject aa = JSONObject
				.fromObject(decode("eyJEYXRhIjp7ImlkIjoiMTAwMSIsIm5hbWUiOiJYMyIsInNleCI6Ik5hbiJ9fQ=="));
		System.out.println("取解密后的Data对应的值： " + aa.get("Data"));
		JSONObject bb = JSONObject.fromObject(aa.get("Data"));
		System.out.println("取Data对应的值的值： " + bb.get("name"));
	}

	public static void test2() {
		JSONObject obj1 = new JSONObject();
		JSONObject obj2 = new JSONObject();
		obj1.put("id", "1001");
		obj1.put("name", "小瘪三");
		obj1.put("sex", "纯爷们");
		System.out.println("obj1==" + obj1.toString());
		String test = encode(obj1.toString());
		System.out.println("现在要对obj1进行加密： " + encode(obj1.toString()));

		System.out.println("现在要对obj1进行解密： " + decode(encode(obj1.toString())));
		System.out.println("现在就可以取JSON了：");
		JSONObject inf = JSONObject.fromObject(decode(encode(obj1.toString())));
		System.out.println("我现在需要JSON中的ID： " + inf.get("id"));

	}

}
