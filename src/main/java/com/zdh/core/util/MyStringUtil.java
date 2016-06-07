package com.zdh.core.util;


public class MyStringUtil {

	public static String  getRandomCode(Integer num) {

		StringBuffer str = new StringBuffer();

		for (int i = 0; i < num ; i++) {

			str.append( (int) (Math.random() * 10)) ;

		}

		return str.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(getRandomCode(6));
	}
}
