package com.zdh.core.base.util;

import java.util.UUID;

public class ToolUtil {
	/** 
     * 生成32位UUID 并去掉"-" 
     */  
    public static String getUUID() {  
        return UUID.randomUUID().toString().replaceAll("-", "");  
    }  

    public static void main(String[] args) {  
        System.out.println(ToolUtil.getUUID());  
        System.out.println(ToolUtil.getUUID().length());// 32  
    }  

}
