package com.zdh.back;

public class TestPath {

	
	public static void main(String[] args) {
		path();
	}
	
	
	
	public static void path(){
		String path = "http://192.168.1.70:8080/xcc_IM/upload/img/.xx.jpg".split("\\.")[1];
		System.out.println(path);
	}
}
