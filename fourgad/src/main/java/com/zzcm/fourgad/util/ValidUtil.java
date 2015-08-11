package com.zzcm.fourgad.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidUtil {
	
	/** 年龄是否符合要求 */ 
	public static boolean isValidBirthday(String birthday) {
		int age = getAgeByBirthday(birthday);
		if( age >= 25 && age <= 50){
			return true;
		}
		return false;
	}
	
	/**
	 * 根据生日计算年龄
	 */
	public static int getAgeByBirthday(String birthday) {
		Date birth = null;
		try {
			birth = new SimpleDateFormat("yyyyMMdd").parse(birthday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();

		if (cal.before(birth)) {
			return -1;
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birth);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth 
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth 
				age--;
			}
		}
		return age;
	}
	
	/** 生日格式是否错误 */ 
	public static boolean isBirthday(String birthday) {
		if (birthday == null) {
			return false;
		}
		if (birthday.length() != 8) {
			return false;
		}
		Pattern pattern = Pattern.compile("[1,2]\\d{3}(0[1-9]||1[0-2])(0[1-9]||[1,2][0-9]||3[0,1])$");
		Matcher matcher = pattern.matcher(birthday);
		if (!matcher.matches()) {
			return false;
		}
		return true;
	}

	/** 姓名是否合法 2-8位中文字符 */
	public static boolean isValidName(String uname) {
		if( uname == null ){
			return false;
		}
		Pattern pattern = Pattern.compile("([\u4e00-\u9fa5]){2,6}$");
		Matcher matcher = pattern.matcher(uname);
		if(matcher.matches()){
			return true;
		}
		return false;
	}
	
	/** 是否为手机号码 */
	public static boolean isPhoneNumber(String phone) {
		if( phone == null ){
			return false;
		}
		Pattern pattern = Pattern.compile("(13[0-9]|15[012356789]|17[012356789]|18[0123456789]|14[57])[0-9]{8}$");
		Matcher matcher = pattern.matcher(phone);
		if(matcher.matches()){
			return true;
		}
		return false;
	}
	
    public static void main(String[] args) {
		System.out.println(isValidName("中阿桑的"));
	}
}
