package com.xypsp.admin.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author rp
 */
public class DateUtils {

    public static Date getYearAfterToday(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, +1);
        return calendar.getTime();
    }

    /**
     * 获取某个时间n天后的时间
     * */
    public static Date getDateAddDays(Date currentTime,Integer days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentTime);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }

    /**
     * 获取n天后的时间
     * */
    public static Date getAfterDays(Integer days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return calendar.getTime();
    }
}
