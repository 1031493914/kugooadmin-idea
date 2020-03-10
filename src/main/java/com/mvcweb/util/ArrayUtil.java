package com.mvcweb.util;

public class ArrayUtil {

	public static String join(Object[] params,String separator){
		if(params == null) return "";
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<params.length;i++){
			if(i>0) builder.append(separator);
			builder.append(""+params[i]);
		}
		return builder.toString();
	}
}
