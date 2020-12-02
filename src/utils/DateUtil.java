package utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    static long millisecondsOfOneDay = 1000 * 60 * 60 * 24;

    public static java.sql.Date date2sql(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static java.util.Date sql2date(java.sql.Date date) {
        return new java.util.Date(date.getTime());
    }

    public static Date today() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.getTime();
    }

    public static Date monthBegin() {
        Calendar c = Calendar.getInstance();
        c.setTime(today());
        c.set(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static Date monthEnd() {
        Calendar c = Calendar.getInstance();
        c.setTime(monthBegin());
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.MILLISECOND, -1);

        return c.getTime();
    }

    public static int daysOfMonth() {
        return (int) ((monthEnd().getTime() - monthBegin().getTime()) / millisecondsOfOneDay + 1);
    }

    public static int daysLeftOfMonth() {
        return (int) ((monthEnd().getTime() - today().getTime()) / millisecondsOfOneDay + 1);
    }
}
