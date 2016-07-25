package com.example.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 * 
 * @author Administrator
 * 
 */
public class RegularExpression {
	/** 中文判断 */
	public static final String CHINA = "^[\u4e00-\u9fa5]{0,}$";
	/** 性别判断 */
	public static final String SEX = "[\u4e00-\u9fa5]";

	/**
	 * 判断是否合法
	 * 
	 * @param centent
	 *            用户输入
	 * @return 是否正确
	 */
	public static boolean Judge(String centent, String canonical) {
		boolean jufge = false;
		Pattern pattern = Pattern.compile(canonical);
		Matcher matcher = pattern.matcher(centent);
		jufge = matcher.matches();
		return jufge;

	}

};
