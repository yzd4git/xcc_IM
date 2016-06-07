package com.zdh.back.yzd;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

public class TestSimplePropertyPreFilter {
	
	public static void main(String[] args) {
		test001();
	}
	
	
	
	public static void test001(){
		
		AppUserInfo user = new AppUserInfo("1001", "Z3", "153766941741", "110", "MiMa", "http://baidu.com", null, null, null, "Man", 17, null, null, null, null, "0000000000", "11111111111111", "1111111111111", "null", "sallt");
		
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(AppUserInfo.class, new String[]{"phone","id","nickname","sex","age"});
		
		System.out.println(JSON.toJSONString(user,filter));
	}
	
	
}
