package com.zdh.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AgeUtil {
	
	private AgeUtil(){
		throw new AssertionError();
	}
	
	public static void main(String[] args) throws Exception {
		String Sdate = "1994-10-10";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("===" + getAgeByBirthday(sdf.parse(Sdate)));
	}
//	
//	public static String CalculateDatePoor(String data) throws Exception{
//		if("".equals(data)){
//			throw new Exception("Sorry this data 	");
//		}
//		
//		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = new Date();
//		
//		Date beDate = myFormat.parse(data);
//		Date EdDate = myFormat.parse(getTimesStr(date,"yyyy-MM-dd"));
//		if(EdDate.getTime() < beDate.getTime()){
//			throw new Exception("出生日期不能大于系统当前日期");
//		}
//		long day = (EdDate.getTime() - beDate.getTime())/(24*60*60*1000) + 1;
//		String year = new DecimalFormat("#.00").format(day/365f);
//		String age[] = year.split("\\.");
//		return age[0].replace("", "").length() < 1? "0":age[0];
//	}
//
//	private static String getTimesStr(Date date, String format) {
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
//		return simpleDateFormat.format(date);
//	}
//	
//	private static String getTimeFormat (String date,String format) throws Exception {
//		if("".equals(date)||"".equals(format)){
//			throw new Exception("缺少必要的数据！");
//		}
//		SimpleDateFormat myDateFormat = new SimpleDateFormat(format);
//		Date src = myDateFormat.parse(date);
//		String str = myDateFormat.format(src);
//		return str;
//	}
	
	public static Integer getAgeByBirthday(Date birthday) {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthday)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		
		cal.setTime(birthday);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth 
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth 
				age--;
			}
		}
		return age;
	}
	
	public static Date String2Date(String Sdate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
		Date Ddate = null;
		try {
			Ddate = sdf.parse(Sdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Ddate;
	}
	
}
