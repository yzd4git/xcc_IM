package com.zdh.core.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zdh.back.remote.impl.RemoteRestfulServiceImpl;

public class CommonStringUtils {
	private static final Log log = 
			LogFactory.getLog(CommonStringUtils.class);
	public static String checkText(String text) {
		text = text.replace("\\", "\\\\");
		text = text.replace("\"", "\\\"");
		text = text.replace("<", "&lt;");
		text = text.replace(">", "&lg;");
		return text;
	}

	public static int fix2Int(Object o) {
		int result = 0;
		if (StringUtils.isNotEmpty(fix2String(o))) {
			result = Integer.valueOf(o.toString()).intValue();
		}
		return result;
	}

	public static String fix2String(Object o) {
		String result = null;
		if (o != null) {
			result = o.toString();
		}
		return result;
	}

	public static String[] fix2StringArray(Object o) {
		String[] result = (String[]) null;
		if (o != null) {
			result = (String[]) o;
		}
		return result;
	}

	public static String fix2StringArrayIndex(Object o, int index) {
		String[] result = fix2StringArray(o);
		if ((result != null) && (result.length >= index)) {
			return result[index];
		}
		return null;
	}
}