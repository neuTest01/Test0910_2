package com.zhihu.util;

import java.lang.reflect.Field;

public class Tools {
	
	/**
	 * 判断字符串是否为空
	 * @param str 需要校验字段
	 * @return 为空返回true,不为空返回false
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str);
	}
	
	public static Object[] BeanToArray(int cont, Class clazz){
		Object[] objs =  new Object[cont];
		Field[] fields = clazz.getFields();
		for (Field field : fields) {
			field.getName();
		}
		return null;
	}
}
