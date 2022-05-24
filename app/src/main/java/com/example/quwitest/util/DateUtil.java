package com.example.quwitest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    public static String format(String timestamp) {
        SimpleDateFormat timeStampSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());
        try {
            Date date = timeStampSdf.parse(timestamp);
            if (isToday(date)) {
                SimpleDateFormat outDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                return outDateFormat.format(date);
            } else {
                SimpleDateFormat outDateFormat = new SimpleDateFormat("dd MMM", Locale.getDefault());
                return outDateFormat.format(date);
            }
        } catch (ParseException e) {
            return timestamp;
        }
    }

    private static Boolean isToday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE) == Calendar.getInstance().get(Calendar.DATE);
    }
}
