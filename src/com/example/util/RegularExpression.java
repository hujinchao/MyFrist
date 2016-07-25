package com.example.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ������ʽ
 * 
 * @author Administrator
 * 
 */
public class RegularExpression {
	/** �����ж� */
	public static final String CHINA = "^[\u4e00-\u9fa5]{0,}$";
	/** �Ա��ж� */
	public static final String SEX = "[\u4e00-\u9fa5]";

	/**
	 * �ж��Ƿ�Ϸ�
	 * 
	 * @param centent
	 *            �û�����
	 * @return �Ƿ���ȷ
	 */
	public static boolean Judge(String centent, String canonical) {
		boolean jufge = false;
		Pattern pattern = Pattern.compile(canonical);
		Matcher matcher = pattern.matcher(centent);
		jufge = matcher.matches();
		return jufge;

	}

};
