package com.zdh.back;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TestDate2String {

	
	public static void testDate(){
		Date date = new Date();
		System.out.println("第一次：" + date);
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println( "第二次：" + sm.format(date));
		try {
			Date date2 = sm.parse(sm.format(date));
			System.out.println("第三次：" + date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		String format = sm.format(System.currentTimeMillis());
		System.out.println("第四次：" + format);
		 
	}
	public static void main(String[] args) {
		testDate();
	}
}
