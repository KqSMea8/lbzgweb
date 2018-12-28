package com.lyarc.tp.corp.common;

import org.apache.commons.lang3.StringUtils;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	 public static void main(String[] args) {  
	        System.out.println(getMonthStartTime(strToShortDate("2016-07-01")));
	        System.out.println(getMonthEndTime(strToShortDate("2016-07-01")));
	        System.out.println("当前小时开始："+getCurrentHourStartTime().toString());  
	        System.out.println("当前小时结束："+getCurrentHourEndTime().toString());  
	        System.out.println("当前天开始："+getCurrentDayStartTime().toString());  
	        System.out.println("当前天时结束："+getCurrentDayEndTime().toString());  
	        System.out.println("当前周开始："+getCurrentWeekDayStartTime().toString());  
	        System.out.println("当前周结束："+getCurrentWeekDayEndTime().toString());  
	        System.out.println("当前月开始："+getCurrentMonthStartTime().toString());  
	        System.out.println("当前月结束："+getCurrentMonthEndTime().toString());  
	        System.out.println("当前季度开始："+getCurrentQuarterStartTime().toString());  
	        System.out.println("当前季度结束："+getCurrentQuarterEndTime().toString());  
	        System.out.println("当前半年/后半年开始："+getHalfYearStartTime().toString());  
	        System.out.println("当前半年/后半年结束："+getHalfYearEndTime().toString());  
	        System.out.println("当前年开始："+getCurrentYearStartTime().toString());  
	        System.out.println("当前年结束："+getCurrentYearEndTime().toString());
			 System.out.println("当上周开始："+getPreviousWeekDayStartTime().toString());
			 System.out.println("当上周结束："+getPreviousWeekDayEndTime().toString());
	    }  
	  
	    /** 
	     * 获取 当前年、半年、季度、月、日、小时 开始结束时间 
	     */  
	  
	    private final static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");  
	    private final static SimpleDateFormat longHourSdf = new SimpleDateFormat("yyyy-MM-dd HH");  
	    private final static SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    private final static SimpleDateFormat sdfWithOutSign = new SimpleDateFormat("yyyyMMddHHmmss");
	  
	  
	    /** 
	     * 获得本周的第一天，周一 
	     *  
	     * @return 
	     */  
	    public static Date getCurrentWeekDayStartTime() {  
	        Calendar c = Calendar.getInstance();  
	        try {  
	            int weekday = c.get(Calendar.DAY_OF_WEEK) - 2;  
	            c.add(Calendar.DATE, -weekday);  
	            c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00"));  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return c.getTime();  
	    }  
	  
	    /** 
	     * 获得本周的最后一天，周日 
	     *  
	     * @return 
	     */  
	    public static Date getCurrentWeekDayEndTime() {  
	        Calendar c = Calendar.getInstance();  
	        try {  
	            int weekday = c.get(Calendar.DAY_OF_WEEK);  
	            c.add(Calendar.DATE, 8 - weekday);  
	            c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59"));  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return c.getTime();  
	    }

	/**
	 * 获得上周的第一天，周一
	 *
	 * @return
	 */
	public static Date getPreviousWeekDayStartTime() {
		Calendar c = Calendar.getInstance();
		try {
			int weekday = c.get(Calendar.DAY_OF_WEEK) - 2;
			c.add(Calendar.DATE, -weekday-7);
			c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c.getTime();
	}

	/**
	 * 获得上周的最后一天，周日
	 *
	 * @return
	 */
	public static Date getPreviousWeekDayEndTime() {
		Calendar c = Calendar.getInstance();
		try {
			int weekday = c.get(Calendar.DAY_OF_WEEK);
			c.add(Calendar.DATE, 8 - weekday-7);
			c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return c.getTime();
	}

	/**
	     * 获得本天的开始时间，即2012-01-01 00:00:00 
	     *  
	     * @return 
	     */  
	    public static Date getCurrentDayStartTime() {  
	        Date now = new Date();  
	        try {  
	            now = shortSdf.parse(shortSdf.format(now));  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return now;  
	    }  
	  
	    /** 
	     * 获得本天的结束时间，即2012-01-01 23:59:59 
	     *  
	     * @return 
	     */  
	    public static Date getCurrentDayEndTime() {  
	        Date now = new Date();  
	        try {  
	            now = longSdf.parse(shortSdf.format(now) + " 23:59:59");  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return now;  
	    }  
	  
	    /** 
	     * 获得本小时的开始时间，即2012-01-01 23:59:59 
	     *  
	     * @return 
	     */  
	    public static Date getCurrentHourStartTime() {  
	        Date now = new Date();  
	        try {  
	            now = longHourSdf.parse(longHourSdf.format(now));  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return now;  
	    }  
	  
	    /** 
	     * 获得本小时的结束时间，即2012-01-01 23:59:59 
	     *  
	     * @return 
	     */  
	    public static Date getCurrentHourEndTime() {  
	        Date now = new Date();  
	        try {  
	            now = longSdf.parse(longHourSdf.format(now) + ":59:59");  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return now;  
	    }  
	  
	    /** 
	     * 获得本月的开始时间，即2012-01-01 00:00:00 
	     *  
	     * @return 
	     */  
	    public static Date getCurrentMonthStartTime() {  
	        Calendar c = Calendar.getInstance();  
	        Date now = null;  
	        try {  
	            c.set(Calendar.DATE, 1);  
	            now = shortSdf.parse(shortSdf.format(c.getTime()));  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return now;  
	    }  
	    /** 
         * 获得本月的开始时间，即2012-01-01 00:00:00 
         *  
         * @return 
         */  
        public static Date getMonthStartTime(Date date) {  
            Calendar c = Calendar.getInstance();  
            c.setTime(date);
            Date now = null;  
            try {  
                c.set(Calendar.DATE, 1);  
                now = shortSdf.parse(shortSdf.format(c.getTime()));  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
            return now;  
        } 
	    /** 
	     * 当前月的结束时间，即2012-01-31 23:59:59 
	     *  
	     * @return 
	     */  
	    public static Date getCurrentMonthEndTime() {  
	        Calendar c = Calendar.getInstance();  
	        Date now = null;  
	        try {  
	            c.set(Calendar.DATE, 1);  
	            c.add(Calendar.MONTH, 1);  
	            c.add(Calendar.DATE, -1);  
	            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return now;  
	    }  
	  
	    /** 
         * 当前月的结束时间，即2012-01-31 23:59:59 
         *  
         * @return 
         */  
        public static Date getMonthEndTime(Date date) {  
            Calendar c = Calendar.getInstance();  
            c.setTime(date);
            Date now = null;  
            try {  
                c.set(Calendar.DATE, 1);  
                c.add(Calendar.MONTH, 1);  
                c.add(Calendar.DATE, -1);  
                now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
            return now;  
        } 
	    
	    /** 
	     * 当前年的开始时间，即2012-01-01 00:00:00 
	     *  
	     * @return 
	     */  
	    public static Date getCurrentYearStartTime() {  
	        Calendar c = Calendar.getInstance();  
	        Date now = null;  
	        try {  
	            c.set(Calendar.MONTH, 0);  
	            c.set(Calendar.DATE, 1);  
	            now = shortSdf.parse(shortSdf.format(c.getTime()));  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return now;  
	    }  
	  
	    /** 
	     * 当前年的结束时间，即2012-12-31 23:59:59 
	     *  
	     * @return 
	     */  
	    public static Date getCurrentYearEndTime() {  
	        Calendar c = Calendar.getInstance();  
	        Date now = null;  
	        try {  
	            c.set(Calendar.MONTH, 11);  
	            c.set(Calendar.DATE, 31);  
	            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return now;  
	    }  
	  
	    /** 
	     * 当前季度的开始时间，即2012-01-1 00:00:00 
	     *  
	     * @return 
	     */  
	    public static Date getCurrentQuarterStartTime() {  
	        Calendar c = Calendar.getInstance();  
	        int currentMonth = c.get(Calendar.MONTH) + 1;  
	        Date now = null;  
	        try {  
	            if (currentMonth >= 1 && currentMonth <= 3)  
	                c.set(Calendar.MONTH, 0);  
	            else if (currentMonth >= 4 && currentMonth <= 6)  
	                c.set(Calendar.MONTH, 3);  
	            else if (currentMonth >= 7 && currentMonth <= 9)  
	                c.set(Calendar.MONTH, 4);  
	            else if (currentMonth >= 10 && currentMonth <= 12)  
	                c.set(Calendar.MONTH, 9);  
	            c.set(Calendar.DATE, 1);  
	            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return now;  
	    }  
	  
	    /** 
	     * 当前季度的结束时间，即2012-03-31 23:59:59 
	     *  
	     * @return 
	     */  
	    public static Date getCurrentQuarterEndTime() {  
	        Calendar c = Calendar.getInstance();  
	        int currentMonth = c.get(Calendar.MONTH) + 1;  
	        Date now = null;  
	        try {  
	            if (currentMonth >= 1 && currentMonth <= 3) {  
	                c.set(Calendar.MONTH, 2);  
	                c.set(Calendar.DATE, 31);  
	            } else if (currentMonth >= 4 && currentMonth <= 6) {  
	                c.set(Calendar.MONTH, 5);  
	                c.set(Calendar.DATE, 30);  
	            } else if (currentMonth >= 7 && currentMonth <= 9) {  
	                c.set(Calendar.MONTH, 8);  
	                c.set(Calendar.DATE, 30);  
	            } else if (currentMonth >= 10 && currentMonth <= 12) {  
	                c.set(Calendar.MONTH, 11);  
	                c.set(Calendar.DATE, 31);  
	            }  
	            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return now;  
	    }  
	  
	    /** 
	     * 获取前/后半年的开始时间 
	     *  
	     * @return 
	     */  
	    public static Date getHalfYearStartTime() {  
	        Calendar c = Calendar.getInstance();  
	        int currentMonth = c.get(Calendar.MONTH) + 1;  
	        Date now = null;  
	        try {  
	            if (currentMonth >= 1 && currentMonth <= 6) {  
	                c.set(Calendar.MONTH, 0);  
	            } else if (currentMonth >= 7 && currentMonth <= 12) {  
	                c.set(Calendar.MONTH, 6);  
	            }  
	            c.set(Calendar.DATE, 1);  
	            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return now;  
	  
	    }  
	  
	    /** 
	     * 获取前/后半年的结束时间 
	     *  
	     * @return 
	     */  
	    public static Date getHalfYearEndTime() {  
	        Calendar c = Calendar.getInstance();  
	        int currentMonth = c.get(Calendar.MONTH) + 1;  
	        Date now = null;  
	        try {  
	            if (currentMonth >= 1 && currentMonth <= 6) {  
	                c.set(Calendar.MONTH, 5);  
	                c.set(Calendar.DATE, 30);  
	            } else if (currentMonth >= 7 && currentMonth <= 12) {  
	                c.set(Calendar.MONTH, 11);  
	                c.set(Calendar.DATE, 31);  
	            }  
	            now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return now;  
	    }  
	    
	    /** 
	     * 获取"yyyyMMDD" 当前时间字符串
	     *  
	     * @return 
	     */  
	    public static String getYYYYMMDDStr() {  
	       Date date=new Date();
	       Format format = new SimpleDateFormat("yyyyMMdd");
	       String nowStr=format.format(date);
	       return nowStr;
	    }  
	    
	    /** 
         * 获取"yyyy-MM-dd hh:mm:ss" 当前时间字符串
         *  
         * @return 
         */  
        public static String getYYYYMMDDHHMMSSStr(Date date) {  
           Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String nowStr=format.format(date);
           return nowStr;
        }

	/**
	 * 获取pattern格式 当前时间字符串
	 *
	 * @return
	 */
	public static String getDateStr(Date date,String pattern) {
		if(StringUtils.isEmpty(pattern)){
			pattern = "yyyy-MM-dd HH:mm";
		}
		Format format = new SimpleDateFormat(pattern);
		String nowStr=format.format(date);
		return nowStr;
	}

	public static Date strToShortDate(String date){
	        if(StringUtils.isBlank(date)){
	            return null;
	        }
	        try {
                return shortSdf.parse(date);
            } catch (ParseException e) {
                return null;
            }
	    }
	    public static Date strToLongDate(String date){
            if(StringUtils.isBlank(date)){
                return null;
            }
            try {
                return longSdf.parse(date);
            } catch (ParseException e) {
                return null;
            }
        }
        
	    public static Date addDays(Date date, int days) {
	        Calendar instance = Calendar.getInstance();
	        instance.setTime(date);
	        instance.add(Calendar.DAY_OF_YEAR, days);
	        return instance.getTime();
	    }
	    
	    public static Date addYears(Date date, int years) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            instance.add(Calendar.YEAR, years);
            return instance.getTime();
        }
	    
	    /** 
	     * 添加小时
	     * 
	     * @param date
	     * @param hours
	     * @return 
	     * 
	     * @author lidoudou 
	     * @date 2017年11月20日 下午3:22:01 
	     */
	    public static Date addHours(Date date, int hours) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            instance.add(Calendar.HOUR, hours);
            return instance.getTime();
        }

	public static Date addMinute(Date date, int minute) {
		Calendar instance = Calendar.getInstance();
		instance.setTime(date);
		instance.add(Calendar.MINUTE, minute);
		return instance.getTime();
	}
	    
	    public static int daysOfTwo(Date date1, Date endTime) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
    
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(endTime);
            int day1 = cal1.get(Calendar.DAY_OF_YEAR);
            int day2 = cal2.get(Calendar.DAY_OF_YEAR);
    
            int year1 = cal1.get(Calendar.YEAR);
            int year2 = cal2.get(Calendar.YEAR);
            if (year1 != year2) // 同一年
            {
                int timeDistance = 0;
                for (int i = year1; i < year2; i++) {
                    if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0) // 闰年
                    {
                        timeDistance += 366;
                    } else // 不是闰年
                    {
                        timeDistance += 365;
                    }
                }
    
                return timeDistance + (day2 - day1);
            } else // 不同年
            {
                return day2 - day1;
            }
        }
	    
	    public static Date strToDate(String date){
            if(StringUtils.isBlank(date)){
                return null;
            }
            try {
                return sdfWithOutSign.parse(date);
            } catch (ParseException e) {
                return null;
            }
        }

	/**
	 * 获取当前日期的前days天
	 * @param cl
	 * @param days
	 * @return
	 */
	public static Date getBeforeDay(Calendar cl,Integer days){
		//使用set方法直接进行设置
		int day = cl.get(Calendar.DATE);
		cl.set(Calendar.DATE, day-days);
		try {
			return shortSdf.parse(shortSdf.format(cl.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}


	/**
	 * 当前年的开始时间，即2012-01-01 00:00:00
	 *
	 * @return
	 */
	public static Date getCurrentMonthBeforeStartTime(int beforeMonth) {
		Calendar c = Calendar.getInstance();
		Date now = null;
		try {
			c.add(Calendar.MONTH, -beforeMonth);
			c.set(Calendar.DATE, 1);
			now = shortSdf.parse(shortSdf.format(c.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}


}
