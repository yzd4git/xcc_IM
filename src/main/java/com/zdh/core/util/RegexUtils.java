package com.zdh.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();

	}

	public static void main(String[] args) {
		
		if(isMobileNO("18119869912")){
			System.out.println("这是一个手机号");
		} else {
			System.out.println("这是什么东西");
		}
	}
}
