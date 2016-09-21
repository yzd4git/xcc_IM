package com.zdh.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 将中文转为拼音
 * @author yzd
 *
 */
public class Cn2Spell {
		/**
		 * 汉字转换为汉语拼音首字母。英语不变
		 * @param chines 汉语
		 * @return 拼音首字母
		 */
	    public static String converterToFirstSpell(String chines){         
	        String pinyinName = "";  
	        char[] nameChar = chines.toCharArray();  
	        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();  
	        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
	        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
	        for (int i = 0; i < nameChar.length; i++) {  
	            if (nameChar[i] > 128) {  
	                try {  
	                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0].charAt(0);  
	                } catch (BadHanyuPinyinOutputFormatCombination e) {  
	                    e.printStackTrace();  
	                }  
	            }else{  
	                pinyinName += nameChar[i];  
	            }  
	        }  
	        return pinyinName;  
	    }  
	   
	    
	    /**
	     * 汉字转换为拼音。英文不变
	     * @param chines 汉语
	     * @return 拼音
	     */
	    public static String converterToSpell(String chines){          
	        String pinyinName = "";  
	        char[] nameChar = chines.toCharArray();  
	        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();  
	        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
	        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
	        for (int i = 0; i < nameChar.length; i++) {  
	            if (nameChar[i] > 128) {  
	                try {  
	                	if(PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat) != null) {
	                		 //如果有特殊字符 不变
	                		 pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0]; 
	                	} else {
	                		pinyinName += nameChar[i] ;
	                	}
	                    
	                } catch (BadHanyuPinyinOutputFormatCombination e) {  
	                    e.printStackTrace();  
	                }  
	            }else{  
	                pinyinName += nameChar[i];  
	            }  
	        }  
	        return pinyinName;  
	    }  

	    public static void main(String[] args) {  
	    	
	    	System.out.println(converterToSpell("☺岳振东"));
	    }  
}
