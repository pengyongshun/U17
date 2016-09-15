package com.example.u17.base_uitls.net_uitls;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 时光彼岸之外 on 2016/6/18.
 */
public class LongTimeUtil {
    public static String getTime(long time) {
        Calendar newCalendar = Calendar.getInstance();
        Calendar oldCalendar = Calendar.getInstance();
        oldCalendar.setTime(new Date(time));
        if (newCalendar.get(Calendar.YEAR) > oldCalendar.get(Calendar.YEAR)) {
            return getFormatTime(time);
        } else if (newCalendar.get(Calendar.MONTH) > oldCalendar.get(Calendar.MONTH)) {
            return (newCalendar.get(Calendar.MONTH) - oldCalendar.get(Calendar.MONTH)) + "月前";
        } else if (newCalendar.get(Calendar.DAY_OF_MONTH) > oldCalendar.get(Calendar.DAY_OF_MONTH)) {
            return (newCalendar.get(Calendar.DAY_OF_MONTH) - oldCalendar.get(Calendar.DAY_OF_MONTH)) + "天前";
        } else if (newCalendar.get(Calendar.HOUR) > oldCalendar.get(Calendar.HOUR)) {
            return (newCalendar.get(Calendar.HOUR) - oldCalendar.get(Calendar.HOUR)) + "小时前";
        } else if (newCalendar.get(Calendar.MINUTE) > oldCalendar.get(Calendar.MINUTE)) {
            return (newCalendar.get(Calendar.MINUTE) - oldCalendar.get(Calendar.MINUTE)) + "分钟前";
        } else {
            return "刚刚";
        }
    }

    public static String getFormatTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date(time));
    }
}
