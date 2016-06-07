package com.zdh.core.util;

import com.sun.istack.Nullable;
/**
 *  测试是否为空
 * @author yzd
 *
 */
public class TextUtils {

    public static boolean isEmpty(@Nullable CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }
    public static void main(String[] args) {
		String str = null;
		String qq = "";
    	
		System.out.println(isEmpty(str));
		System.out.println(isEmpty(qq));
    	
	}
    
}
