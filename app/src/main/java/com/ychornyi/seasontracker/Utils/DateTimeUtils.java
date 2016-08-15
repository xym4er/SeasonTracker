package com.ychornyi.seasontracker.Utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by y.chornyi on 11.08.2016.
 */
public class DateTimeUtils {
    private static DateFormat format = new SimpleDateFormat("d MMMM yyyy, ", new Locale("ru"));
    private static DateFormat format2 = new SimpleDateFormat("d MMMM yyyy, HH:mm", new Locale("ru"));

    public static Date parseDate(String date) {
        Date out = null;
        if (date.startsWith("Сегодня")) {
            date = date.substring(9);
            out = new Date();
            date = format.format(out) + date;
            try {
                out = format2.parse(date);
                return out;
            } catch (ParseException e) {
            }
        }
        if (date.startsWith("Вчера")) {
            date = date.substring(7);
            out = new Date();
            date = format.format(out) + date;
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+01:00"));
            try {
                out = format2.parse(date);
                calendar.setTime(out);
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                out = calendar.getTime();
                return out;
            } catch (ParseException e) {
            }

        } else {
            try {
                out = format2.parse(date);
                return out;
            } catch (ParseException e) {
            }
        }
        return out;
    }
}
