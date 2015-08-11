package com.zzcm.fourgad.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    
    /** 日期格式 yyyy-MM-dd **/
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    /** 日期格式 yyyyMMdd **/
    public static final String YYYYMMDD = "yyyyMMdd";
    
	@SuppressWarnings("static-access")
	public static String getYestDay(){
		Date date=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
		date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
	 
		//System.out.println(dateString);
		return dateString;
	}
	
	/**
	 * 获取当前日期 yyyy-MM-dd
	 * @return
	 */
	public static String getTodayDate(){
		Date date=new Date();//取时间				
		SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD);
		String dateString = formatter.format(date);	 
		return dateString;
	}
	
	/**
	 * 把yyyyMMdd格式日期转为yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String changeDateFormat(String date){
		try {
			Date d = new SimpleDateFormat(YYYYMMDD).parse(date);
			SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD);
			String dateString = formatter.format(d);	 
			return dateString;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String getDateTime(){
		Date date=new Date();//取时间				
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(date);	 
		return dateString;
	}
	
	public static String getDateTime2(){
		Date date=new Date();//取时间				
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = formatter.format(date);	 
		return dateString;
	}
	
	/**
     * 得到当前日期如果参数为空返回yyyy-MM-dd
     * 
     * @param date
     * @param pattern
     *            格式化格式
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        DateFormat simpleDateFormat = new SimpleDateFormat(pattern == null ? YYYY_MM_DD : pattern);
        String sdate = simpleDateFormat.format(date == null ? new Date() : date);
        return sdate;
    }
	
	/**
     * 将指定字符日期转日期对象
     * 
     * @param pattern
     *            格式化格式默认为(yyyy-MM-dd)
     * @param date
     *            日期为空当前日期
     * @return
     */
    public static Date parseDate(String date, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern == null ? YYYY_MM_DD : pattern);
        try {
            return df.parse(date == null ? formatDate(new Date(), pattern) : date);
        } catch (ParseException e) {
            return new Date();
        }
    }
    
    /**
     * 得到日期的下一天的日期
     * 
     * @param date
     *            如果为空，取当时日期
     * @return
     */
    public static Date getNextDayDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }
    
    public static String getNextDayDate(String date, String pattern) {
        
        Date nowDate = parseDate(date,pattern);
        Date nextDayDate = getNextDayDate(nowDate);
        String nextDayStr = formatDate(nextDayDate,pattern);
        
        return nextDayStr;
    }
    
    /**
     * 判断某天与当前时间大小
     * 
     * @param s
     * @return
     */
    public static boolean isAfter(String d,String pattern) {
        Date date, now ,nowDate;
        now = new Date();
        String nowStr = formatDate(now,pattern);
        nowDate = parseDate(nowStr,pattern);
        date = parseDate(d,pattern);
        return (nowDate.after(date));
    }
    
    public static void main(String args [])
    {
        System.out.println(isAfter("2015-06-27",YYYY_MM_DD));
    }
}
