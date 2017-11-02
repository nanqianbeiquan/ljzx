package com.srd.ljzd.util;



import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

public class DateUtils {
	
	public static final String CHINA_PATTERN = "yyyy年MM月dd日";
	
	public static final String formatPattern = "yyyy-MM-dd";
	
	static final String formatPattern_Short = "yyyyMMdd";
	
	public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String AllStr_PATTERN = "yyyyMMddHHmmss";
	
	private static SimpleDateFormat format = new SimpleDateFormat(formatPattern);
	
	@Deprecated
	public static String getLocalStr(String pattern,Date date){
		if(StringUtils.isEmpty(pattern)){
			pattern = DEFAULT_PATTERN;
		}
		if(date==null){
			date = Calendar.getInstance().getTime();
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return  format.format(date);
	}
	
	public static String getLocalStrNew(String pattern,Date date){
		if(StringUtils.isEmpty(pattern)){
			pattern = DEFAULT_PATTERN;
		}
		if(date==null){
			return "";
		}
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return  format.format(date);
	}
	
	public static Date getLocalDate(String pattern,String dateStr){
		if(StringUtils.isEmpty(pattern)){
			pattern = DEFAULT_PATTERN;
		}
		if(dateStr==null||"".equals(dateStr.trim())){
			return null;
		}
		format.applyPattern(pattern);
		try {
			return format.parse(dateStr.trim());
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Date getLocalDate(String dateStr){
		if(dateStr==null||"".equals(dateStr)){
			return null;
		}
		Date tempDate = DateUtils.getLocalDate("yyyy-MM-dd", dateStr);
		if(tempDate==null){
			tempDate = DateUtils.getLocalDate("yyyy年MM月dd日", dateStr);
		}
		if(tempDate==null){
			tempDate = DateUtils.getLocalDate("yy年MM月dd日", dateStr);
		}
		if(tempDate==null){
			tempDate = DateUtils.getLocalDate("yyyy.MM.dd", dateStr);
		}
		if(tempDate==null){
			tempDate = DateUtils.getLocalDate("yy/MM/dd", dateStr);
		}
		if(tempDate==null){
			tempDate = DateUtils.getLocalDate("yyyy/MM/dd", dateStr);
		}
		if(tempDate==null){
			tempDate = DateUtils.getLocalDate("yyyyMMdd", dateStr);
		}
		return tempDate;
	}
	
	/**
	 * 获取当前日期
	 * @return
	 */
	public static String getCurrentDateStr(){
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);		
		
		return format.format(new Date());
	}
	
	/**
	 * 获取当前日期
	 * @return
	 */
	public static String getCurrentDateStr(String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);		
		
		return format.format(new Date());
	}
	
	public static String getCurrentDateAllStr(){
		SimpleDateFormat format = new SimpleDateFormat(AllStr_PATTERN);		
		
		return format.format(new Date());
	}
	
	/**
	 * 获取制定毫秒数之前的日期
	 * @param timeDiff
	 * @return
	 */
	public static String getDesignatedDate(long timeDiff){
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		long nowTime = System.currentTimeMillis();
		long designTime = nowTime - timeDiff;	
		
		return format.format(designTime);
	}
	
	/**
	 * 
	 * 获取前几天的日期
	 */
	public static String getPrefixDate(String count){
		Calendar cal = Calendar.getInstance();
		int day = 0-Integer.parseInt(count);
		cal.add(Calendar.DATE,day);   // int amount   代表天数
		Date datNew = cal.getTime(); 
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		return format.format(datNew);
	}
	/**
	 * 日期转换成字符串
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		return format.format(date);
	}
	/**
	 * 字符串转换日期
	 * @param str
	 * @return
	 */
	public static Date stringToDate(String str){
		//str =  " 2008-07-10 19:20:00 " 格式
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		if(!str.equals("")&&str!=null){
			try {
				return format.parse(str);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public static String dateToStringByFormatPattern(Date date,String formatPattern){
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		return format.format(date);
	}
	
	public static Date stringToDateByFormatPattern(String str,String formatPattern){
		//str =  " 2008-07-10 19:20:00 " 格式
		SimpleDateFormat format = new SimpleDateFormat(formatPattern);
		if(!str.equals("")&&str!=null){
			try {
				return format.parse(str);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	/** 
	* @Title: getCurrentDate 
	* @Description: 获取当前日期时间
	* @param @return 设定文件 
	* @return Date 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年8月24日 下午2:11:03
	*/
	public static Date getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		
		return cal.getTime();
	}

	/**
	 * 将时间置为23时59分钟59秒
	 * 
	 * @param date
	 * @return
	 */
	public static Date setFullPassDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 将时间后退2小时
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFallBack2Hour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) - 2);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 将时间精确到小时
	 * 
	 * @param date
	 * @return
	 */
	public static Date getTimeHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 获取两个时间间隔的天数
	 * 
	 * @param date
	 * @return
	 */
	public static long getDiffDays(Date startDate, Date endDate) {
		long difftime = endDate.getTime() - startDate.getTime();
		return difftime / (24L * 60L * 60L * 1000L);
	}
	

	/**
	 * 根据日期获取当天起始时间
	 * 
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDateOfCurrentDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	public static Date getStartYesterday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 根据日期获取下一天起始时间
	 * 
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDateOfNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 根据日期当前日期顺延一周后的起始时间
	 * 
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDateOfNextSevenDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 根据日期当前日期顺延一周后的起始时间
	 * 
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDateOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/**
	 * 根据日期当前日期顺延一月后的起始时间
	 * 
	 * 
	 * @param date
	 * @return
	 */
	public static Date getStartDateOfNextMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}

	/*
	 * 封装一天只能的时间区域
	 */
	public static List<Date> getStaticByDateDateArea(Date date) {
		List<Date> dates = new ArrayList<Date>();
		Date startdate = getStartDateOfCurrentDay(date);
		Date nextday = getStartDateOfNextDay(date);
		int step = 2;
		dates.add(startdate);
		for (int i = 1; i < 12; i++) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startdate);
			calendar.add(Calendar.HOUR_OF_DAY, i * step);
			dates.add(calendar.getTime());
		}
		dates.add(nextday);
		return dates;
	}

	/*
	 * 封装一周之内时间区域
	 */
	public static List<Date> getStaticByWeekDateArea(Date date) {
		List<Date> dates = new ArrayList<Date>();
		Date startdate = getStartDateOfCurrentDay(date);
		Date nextday = getStartDateOfNextSevenDay(date);
		dates.add(startdate);
		for (int i = 1; i < 7; i++) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startdate);
			calendar.add(Calendar.DAY_OF_MONTH, i);
			dates.add(calendar.getTime());
		}
		dates.add(nextday);
		return dates;
	}

	/*
	 * 封装一周之内时间区域List<String>
	 */
	public static List<String> getStaticByWeekLabel(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
		List<String> dates = new ArrayList<String>();
		Date startdate = getStartDateOfCurrentDay(date);
		Date nextday = getStartDateOfNextSevenDay(date);
		dates.add(dateFormat.format(startdate));
		for (int i = 1; i < 7; i++) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startdate);
			calendar.add(Calendar.DAY_OF_MONTH, i);
			dates.add(dateFormat.format(calendar.getTime()));
		}
		return dates;
	}

	/*
	 * 封装一月之内时间区域
	 */
	public static List<Date> getStaticByMonthDateArea(Date date) {
		List<Date> dates = new ArrayList<Date>();
		Date startdate = getStartDateOfMonth(date);
		Date nextday = getStartDateOfNextMonth(date);
		long daydiff = getDiffDays(startdate, nextday);
		dates.add(startdate);
		for (int i = 1; i < daydiff; i++) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startdate);
			calendar.add(Calendar.DAY_OF_MONTH, i);
			dates.add(calendar.getTime());
		}
		dates.add(nextday);
		return dates;
	}
	
	/*
	 *封装一点时间之内的时间区域（天） 
	 */
	public static List<Date> getStaticBySE(Date startDate,Date endDate)
	{
		List<Date> dates = new ArrayList<Date>();
		
		long daydiff = getDiffDays(startDate, endDate);
		dates.add(startDate);
		for (int i = 1; i < daydiff; i++) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			calendar.add(Calendar.DAY_OF_MONTH, i);
			dates.add(calendar.getTime());
		}
		dates.add(endDate);
		return dates;
	}

	/*
	 * 封装一月之内时间区域
	 */
	public static List<String> getStaticByMonthLabel(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
		List<String> dates = new ArrayList<String>();
		Date startdate = getStartDateOfMonth(date);
		Date nextday = getStartDateOfNextMonth(date);
		long daydiff = getDiffDays(startdate, nextday);
		dates.add(dateFormat.format(startdate));
		for (int i = 1; i < daydiff; i++) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startdate);
			calendar.add(Calendar.DAY_OF_MONTH, i);
			dates.add(dateFormat.format(calendar.getTime()));
		}
		return dates;
	}

	public static String formatDate(String format, Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	
	public static java.sql.Date getSqlDateFromUtilDate(String fromat,String dateStringToParse){
		
		  SimpleDateFormat bartDateFormat =   new SimpleDateFormat(fromat); 
		 
		  
		  java.util.Date date =null;
		  
		try {
			date = bartDateFormat.parse(dateStringToParse);
		} catch (ParseException e) {
			
			e.printStackTrace();
		} 
	      java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	      
	      return sqlDate;
	      
	}
	
	
	/**
	 * 获取当前时间
	 * 
	 * @param date
	 * @return
	 */
	public static java.sql.Date getCurrentSqlDate() {
		return new  java.sql.Date(System.currentTimeMillis());
	}

	 private static void testConvertZwrq() {
	        String[] list = new String[] { "二○○九年四月三十日", "○九年四月三十日", "二○○九年十二月三十一日", "二零零九年十二月三十一日","2016年6月1日"};
	        for (String s : list) {
	            Date d = convertCnDate(s);
	        }
	    }

	 private static void testConvertZwrq2() {
	        String[] list = new String[] { "二○○九年四月三十日", "○九年四月三十日", "二○○九年十二月三十一日", "二零零九年十二月三十一日","2016年6月1"};
	        for (String s : list) {
	            String dateStr = convertCnDateStr(s);
	        }
	    }
	 
	    public static Date convertCnDate(String cprq) {
	        int yearPos = cprq.indexOf("年");
	        int monthPos = cprq.indexOf("月");
	        String cnYear = cprq.substring(0, yearPos);
	        String cnMonth = cprq.substring(yearPos + 1, monthPos);
	        String cnDay = cprq.substring(monthPos + 1, cprq.length() - 1);
	        String year = ConvertCnYear(cnYear);
	        String month = ConvertCnDateNumber(cnMonth);
	        String day = ConvertCnDateNumber(cnDay);
	        Calendar c = Calendar.getInstance();
	        c.set(Calendar.YEAR, Integer.parseInt(year));
	        c.set(Calendar.MONTH, Integer.parseInt(month)-1);
	        c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
	        return c.getTime();
	    }
	    
	    public static String convertCnDateStr(String cprq) {
	        try {
	        	int yearPos = cprq.indexOf("年");
		        int monthPos = cprq.indexOf("月");
		        if(yearPos<0&&monthPos<0){
		        	return cprq;
		        }
		        String cnYear = cprq.substring(0, yearPos);
		        String cnMonth = cprq.substring(yearPos + 1, monthPos);
		        String cnDay = cprq.substring(monthPos + 1, cprq.length() - 1);
		        String year = ConvertCnYear(cnYear);
		        String month = ConvertCnDateNumber(cnMonth);
		        String day = ConvertCnDateNumber(cnDay);
		        Calendar c = Calendar.getInstance();
		        c.set(Calendar.YEAR, Integer.parseInt(year));
		        c.set(Calendar.MONTH, Integer.parseInt(month)-1);
		        c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
		        // c.getTime();
		        
		       return  dateToString(c.getTime());
			} catch (Exception e) {
				e.printStackTrace();
				return cprq;
			}
	        
	       
	    }

	    private static String ConvertCnYear(String cnYear) {
	        if(cnYear.length() == 2)
	           return "20" + ConvertCnNumberChar(cnYear);
	        else
	            return ConvertCnNumberChar(cnYear);
	    }

	    private static String ConvertCnDateNumber(String cnNumber) {
	        if (cnNumber.length() == 1) {
	            if(cnNumber.equals("十"))
	          	return "10";
	            else
	          	return ConvertCnNumberChar(cnNumber);
	        } else if (cnNumber.length() == 2) {
	            if (cnNumber.startsWith("十")) {
	                return "1" + ConvertCnNumberChar(cnNumber.substring(1, 2));
	            } else if (cnNumber.endsWith("十")) {
	                return ConvertCnNumberChar(cnNumber.substring(0, 1)) + "0";
	            } else {
	                return ConvertCnNumberChar(cnNumber);
	            }
	        } else if (cnNumber.length() == 3) {
	            return ConvertCnNumberChar(cnNumber.substring(0, 1) + cnNumber.substring(2, 3));
	        }
	        return null;
	    }

	    private static String ConvertCnNumberChar(String cnNumberStr) {
	        String ALL_CN_NUMBER = "○零一二三四五六七八九";
	        String ALL_NUMBER = "00123456789";
	        StringBuffer buf = new StringBuffer();
	        for (int i = 0; i < cnNumberStr.length(); i++) {
	            char c = cnNumberStr.charAt(i);
	            int index = ALL_CN_NUMBER.indexOf(c);
	            if (index != -1) {
	                buf.append(ALL_NUMBER.charAt(index));
	            } else {
	                buf.append(cnNumberStr.charAt(i));
	            }
	        }
	        return buf.toString();
	    }
	    
	
	    /**
	    * @Title: getSpecifiedDayBefore
	    * @Description: TODO(指定日期的前一天)
	    * @param  @param specifiedDay
	    * @param  @return  
	    * @return String    返回类型
	    * @author zihui.pei  
	    * @throws
	    * @date 2016年6月21日 下午5:45:34
	    */
	    public static String getSpecifiedDayBefore(String specifiedDay){ 
	    //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
	    Calendar c = Calendar.getInstance(); 
	    Date date=null; 
	    try { 
	    date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay); 
	    } catch (ParseException e) { 
	    e.printStackTrace(); 
	    } 
	    c.setTime(date); 
	    int day=c.get(Calendar.DATE); 
	    c.set(Calendar.DATE,day-1); 

	    String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
	    return dayBefore; 
	    } 
	    /** 
	    * 获得指定日期的后一天 
	    * @param specifiedDay 
	    * @return 
	    */ 
	    public static String getSpecifiedDayAfter(String specifiedDay){ 
	    Calendar c = Calendar.getInstance(); 
	    Date date=null; 
	    try { 
	    date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay); 
	    } catch (ParseException e) { 
	    e.printStackTrace(); 
	    } 
	    c.setTime(date); 
	    int day=c.get(Calendar.DATE); 
	    c.set(Calendar.DATE,day+1); 

	    String dayAfter=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime()); 
	    return dayAfter; 
	    } 
	
	    
	    /** 
	     * 得到几天前的时间 
	     *  
	     * @param d 
	     * @param day 
	     * @return 
	     */  
	    public static Date getDateBefore(Date d, int day) {  
	        Calendar now = Calendar.getInstance();  
	        now.setTime(d);  
	        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);  
	        return now.getTime();  
	    }  
	  
	    /** 
	     * 得到几天后的时间 
	     *  
	     * @param d 
	     * @param day 
	     * @return 
	     */  
	    public static Date getDateAfter(Date d, int day){ 
	        Calendar now = Calendar.getInstance();  
	        now.setTime(d);  
	        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);  
	        return now.getTime();
	    }  

	    /**
	     * 
	    	* @Title: getFirstDayOfMonth
	    	* @Description: 获取前n个月的第一天
	    	* @param  @param num
	    	* @param  @return
	    	* @return String
	    	* @author jiang.zhou
	    	* @throws
	    	* @date 下午2:51:59
	     */
	    public static String getFirstDayOfMonth(int num) {  
		      Calendar calendar = Calendar.getInstance();  
		      calendar.add(Calendar.MONTH, -num);  
			  calendar.set(Calendar.DATE, 1);  
		      DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");  
		      return dateFormat.format(calendar.getTime());  
	   }  

	    /**
	     * 
	    	* @Title: getMondayOfWeek
	    	* @Description: 获取对应的周的礼拜一
	    	* @param  @param num
	    	* @param  @return
	    	* @return String
	    	* @author jiang.zhou
	    	* @throws
	    	* @date 下午2:58:54
	     */
	    public static String getMondayOfWeek(int num) {  
		      DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");  
		      Calendar calendar = Calendar.getInstance();  
		      calendar.add(Calendar.WEEK_OF_MONTH, -num);  
	          calendar.set(Calendar.DAY_OF_WEEK, 2);  
	          return dateFormat.format(calendar.getTime());  
	    }  
	    
	/** 
	* @Title: getDateListByNum 
	* @Description: 得到截止某个日期的日期列表
	* @param @param date
	* @param @param num
	* @param @return 设定文件 
	* @return List<Date> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月15日 下午1:51:56
	*/
	public static List<Date> getDateListByNum(Date date, int num){
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		
		List<Date> dateList = new ArrayList<Date>();
		
		Date tempDate = null;
		
		Calendar cal = Calendar.getInstance();
		
		try {
			cal.setTime(sdfDate.parse(sdfDate.format(date)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		for(int i=0;i<num;i++){
			if(i > 0){
				cal.add(Calendar.DATE, -1);
			}
			
			tempDate = cal.getTime();
			
			dateList.add(tempDate);
		}
		
		return dateList;
	}
	
	/** 
	* @Title: getDateListByWeekNum 
	* @Description: 得到截止某个日期前几个周几的日期列表
	* @param @param date
	* @param @param weekNum
	* @param @param dayOfWeek(从1到7分别代表周日到周六)
	* @param @return 设定文件 
	* @return List<Date> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月16日 上午9:19:40
	*/
	public static List<Date> getDateListByWeekNum(Date date, int weekNum, int dayOfWeek){
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		
		List<Date> dateList = new ArrayList<Date>();
		
		Date tempDate = null;
		
		Calendar cal = Calendar.getInstance();
		
		try {
			cal.setTime(sdfDate.parse(sdfDate.format(date)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int today = cal.get(Calendar.DAY_OF_WEEK);
		
		//如果当前一周中的天数大于dayOfWeek参数，则添加到最后的结果中
		if(today > dayOfWeek){
			cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
			
			tempDate = cal.getTime();
			
			dateList.add(tempDate);
			
			weekNum--;
		}
		
		for(int i=0;i<weekNum;i++){
			cal.add(Calendar.WEEK_OF_MONTH, -1);
			
			cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
			
			tempDate = cal.getTime();
			
			dateList.add(tempDate);
		}
		
		return dateList;
	}
	
	/** 
	* @Title: getDateListByMonthNum 
	* @Description: 得到截止某个日期前几月中一号的日期列表
	* @param @param date
	* @param @param monthNum
	* @param @param dayOfMonth
	* @param @return 设定文件 
	* @return List<Date> 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月16日 上午9:19:43
	*/
	public static List<Date> getDateListByMonthNum(Date date, int monthNum, int dayOfMonth){
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		
		List<Date> dateList = new ArrayList<Date>();
		
		Date tempDate = null;
		
		Calendar cal = Calendar.getInstance();
		
		try {
			cal.setTime(sdfDate.parse(sdfDate.format(date)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int today = cal.get(Calendar.DAY_OF_MONTH);
		
		//如果当前一周中的天数大于dayOfWeek参数，则添加到最后的结果中
		if(today > dayOfMonth){
			cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			
			tempDate = cal.getTime();
			
			dateList.add(tempDate);
			
			monthNum--;
		}
		
		for(int i=0;i<monthNum;i++){
			cal.add(Calendar.MONTH, -1);
			
			cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			
			tempDate = cal.getTime();
			
			dateList.add(tempDate);
		}
		
		return dateList;
	}
	    
	    
	public static void main(String[] args) {
		getDateListByMonthNum(new Date(), 12, 15);
	}
	

}

