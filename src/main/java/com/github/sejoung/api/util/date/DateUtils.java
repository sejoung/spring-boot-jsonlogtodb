package com.github.sejoung.api.util.date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {

    public static String getCurrentDay(String pattern) {
        DateTime dt = new DateTime();
        return getDateTimeFormatterDate(pattern, dt);
    }

    public static String getDateTimeFormatterDate(String pattern, DateTime dt) {
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern(pattern);
        return dtfOut.print(dt);
    }

    public static int getCurrentDayPlusDayInt(String pattern, int days) {
        return Integer.parseInt(getCurrentDayPlusDay(pattern, days));
    }

    public static String getCurrentDayPlusDay(String pattern, int days) {
        DateTime dt = new DateTime();
        return getDateTimeFormatterDate(pattern, dt.plusDays(days));
    }
    
    public static String getCurrentDayPlusDay(int days) {
        return getCurrentDayPlusDay("yyyyMMdd", days);
    }

    public static String getCurrentDayPlusMonth(String pattern, int months) {
        DateTime dt = new DateTime();
        return getDateTimeFormatterDate(pattern, dt.plusMonths(months));
    }

    public static int getCurrentDayInt() {
        return Integer.parseInt(getCurrentDay());
    }

    public static String getCurrentDay() {
        return getCurrentDay("yyyyMMdd");
    }

    public static int getCurrentDayMinusDay(String pattern, int days) {
        DateTime dt = new DateTime();
        return Integer.parseInt(getDateTimeFormatterDate(pattern, dt.minusDays(days)));
    }

    public static int getCurrentDayMinusDay(int days) {
        return getCurrentDayMinusDay("yyyyMMdd", days);
    }

    public static int getCurrentDayPlusDayInt(int days) {
        return Integer.parseInt(getCurrentDayPlusDay(days));
    }

    public static int getCurrentDayPlusMonthInt(int months) {
        DateTime dt = new DateTime();
        return Integer.parseInt(getDateTimeFormatterDate("yyyyMMdd", dt.plusMonths(months)));
    }

    public static int getCurrentDayMinusMonthInt(int months) {
        DateTime dt = new DateTime();
        return Integer.parseInt(getDateTimeFormatterDate("yyyyMMdd", dt.minusMonths(months)));
    }

}
