package bml.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 月影
 * Date 2020/2/28 18:17
 */
public class DateFormatUtil {

    public DateFormatUtil() {

    }

    /**
     * 时间工具类
     * 获取当天的开始时间与结束时间
     * @return Date
     */
    public static Date getStartTime(){
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime();
    }

    public static Date getEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime();
    }

    /**
     * 把当天的时间转换成特定字符串 用于数据库存储
     * 比如2020年3月13转换成20200313
     * @param date data
     */
    public static String getStringTime(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(date);
    }

    public static void main(String[] args) {
        System.out.println(new Date());
    }




}
