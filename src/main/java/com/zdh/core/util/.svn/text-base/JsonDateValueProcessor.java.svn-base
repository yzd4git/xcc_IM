package com.zdh.core.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonDateValueProcessor implements JsonValueProcessor {
	private String datePattern = "yyyy-MM-dd";
	private String timeStampPattern = "yyyy-MM-dd HH:mm:ss";

	public JsonDateValueProcessor() {
	}

	public JsonDateValueProcessor(String format) {
		this.datePattern = format;
	}

	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		return process(value);
	}

	public Object processObjectValue(String key, Object value,
			JsonConfig jsonConfig) {
		return process(value);
	}

	private Object process(Object value) {
		try {
			if (value.getClass().equals(Timestamp.class)) {
				SimpleDateFormat sdf = new SimpleDateFormat(
						this.timeStampPattern, Locale.UK);
				return sdf.format((java.util.Date) value);
			}
			if (value.getClass().equals(java.sql.Date.class)) {
				SimpleDateFormat sdf = new SimpleDateFormat(this.datePattern,
						Locale.UK);
				return sdf.format((java.util.Date) value);
			}
			if (value.getClass().equals(java.util.Date.class)) {
				SimpleDateFormat sdf = new SimpleDateFormat(this.datePattern,
						Locale.UK);
				return sdf.format((java.util.Date) value);
			}
			return value == null ? "" : value.toString();
		} catch (Exception e) {
		}
		return "";
	}

	public String getDatePattern() {
		return this.datePattern;
	}

	public void setDatePattern(String pDatePattern) {
		this.datePattern = pDatePattern;
	}
}