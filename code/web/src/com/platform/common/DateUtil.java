package com.platform.common;

import java.util.Calendar;
import java.util.Date;
public class DateUtil {

	/**  
     * 根据原来的时间（Date）获得相对偏移 N 天的时间（Date）  
     * @param protoDate 原来的时间（java.util.Date）  
     * @param dateOffset（向前移负数，向后移正数）  
     * @return 时间（java.util.Date）  
     */ 
	public static Date getOffsetDate(Date protoDate,int dateOffset){   
        Calendar cal = Calendar.getInstance();   
        cal.setTime(protoDate);   
        cal.add(Calendar.DATE, dateOffset); //正确写法   
        //System.out.println(cal.get(Calendar.MONTH));   
        return cal.getTime();   
    }
	
	/**  
     * 根据原来的时间（Date）获得相对偏移 N 分钟的时间（Date）  
     * @param protoDate 原来的时间（java.util.Date）  
     * @param dateOffset（向前移负数，向后移正数）  
     * @return 时间（java.util.Date）  
     */ 
	public static Date getOffsetMinuteDate(Date protoDate,int minuteOffset){   
        Calendar cal = Calendar.getInstance();   
        cal.setTime(protoDate);   
        cal.add(Calendar.MINUTE, minuteOffset); //正确写法   
        //System.out.println(cal.get(Calendar.MONTH));   
        return cal.getTime();   
    }
	
	/**  
     * 根据原来的时间（Date）获得相对偏移 N 月的时间（Date）  
     * @param protoDate 原来的时间（java.util.Date）  
     * @param dateOffset（向前移负数，向后移正数）  
     * @return 时间（java.util.Date）  
     */ 
	public static Date getOffsetMonthDate(Date protoDate,int monthOffset){   
        Calendar cal = Calendar.getInstance();   
        cal.setTime(protoDate);   
        cal.add(Calendar.MONTH, monthOffset); //正确写法   
        //System.out.println(cal.get(Calendar.MONTH));   
        return cal.getTime();   
    }
	
	/**  
     * 根据原来的时间（Date）获得相应周的第一天（默认设置为已周一为一周的第一天）  
     * @param protoDate 原来的时间（java.util.Date）  
     * @return 时间（java.util.Date）  
     */ 
	public static Date getWeekFirstDate(Date protoDate){   
        Calendar cal = Calendar.getInstance();   
        cal.setTime(protoDate);   
        cal.setFirstDayOfWeek(Calendar.MONDAY); //以周1为首日
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime(); 
    }
	
	/**  
     * 根据原来的时间（Date）获得相应周的第一天（默认设置为已周一为一周的第一天）  
     * @param protoDate 原来的时间（java.util.Date）  
     * @return 时间（java.util.Date）  
     */ 
	public static int getWeek(Date protoDate){   
        Calendar cal = Calendar.getInstance();   
        cal.setTime(protoDate);   
        cal.setFirstDayOfWeek(Calendar.MONDAY); //以周1为首日
        return cal.get(Calendar.WEEK_OF_YEAR);
    }
	
}
