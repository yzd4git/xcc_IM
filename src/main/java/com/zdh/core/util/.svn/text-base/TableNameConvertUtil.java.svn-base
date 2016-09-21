package com.zdh.core.util;

import com.alibaba.druid.stat.TableStat.Name;

public class TableNameConvertUtil {
	public static String convertName(String tName) {
		StringBuffer des = new StringBuffer();
		char[] sName = tName.toCharArray();
		for (int i = 0; i < sName.length; i++) {
			if (sName[i] >= 'A' && sName[i] <= 'Z') {
				des.append("_" + sName[i]);
			} else {
				des.append(sName[i]);
			}
		}

		return des.toString();
	}

	/**
	 * 添加表“t”前缀
	 * 
	 * @param tName
	 * @return
	 */
	public static String addExt(String tName) {
		if (tName.toLowerCase().startsWith("system")) {
			return ""; // 如果是system开头的不需要加t 看数据库表名
		}
		return "t"; // 如果是普通表需要加上t前缀
	}

	public static void main(String agrs[]) {
		System.out.println(convertName("adsfadAAgf"));
	}
}
