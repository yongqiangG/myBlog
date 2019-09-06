package com.johnny.myBlog.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
/**
 * 时间转字符串工具类
 * 供前台调用
 * @author johnny
 *
 */
public class DateJsonValueProcessor implements JsonValueProcessor {
	private String format;
	public DateJsonValueProcessor(String format) {
		this.format=format;
	}
	
	
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		if(value==null) {
			return "";
		}
		if(value instanceof Timestamp) {
			String str = new SimpleDateFormat(this.format).format((Timestamp)value);
			return str;
		}
		if(value instanceof Date) {
			String str = new SimpleDateFormat(this.format).format((Date)value);
			return str;
		}
		return value.toString();
	}

}
