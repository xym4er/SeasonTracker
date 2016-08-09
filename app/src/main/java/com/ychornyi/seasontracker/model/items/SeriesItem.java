package com.ychornyi.seasontracker.model.items;

import android.util.Log;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class SeriesItem {
    private int id;
    private String name="";
    private String season="";
    private String seria="";
    private String date="";
    private String translate="";
    private String url="";

    public String getUrl() {
        if(url.isEmpty()){
            return null;
        }
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTranslate() {
        if(translate.isEmpty()){
            return "";
        }
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getSeria() {
        return seria;
    }

    public void setSeria(String seria) {
        this.seria = seria;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        Log.d("MyTag", "Date: |"+date+"|");
        if (date.startsWith("Сегодня")){
            date = date.substring(9);
            Date out = new Date();
            DateFormat format = new SimpleDateFormat("d MMMM yyyy, ", new Locale("ru"));
            DateFormat format2 = new SimpleDateFormat("d MMMM yyyy, HH:mm", new Locale("ru"));
            date = format.format(out)+date;
            try {
                out = format2.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Log.d("MyTag", "Date: |"+out+"|");
        }
        if (date.startsWith("Вчера")){
            date = date.substring(7);
            Date out = new Date();
            DateFormat format = new SimpleDateFormat("d MMMM yyyy, ", new Locale("ru"));
            DateFormat format2 = new SimpleDateFormat("d MMMM yyyy, HH:mm", new Locale("ru"));
            date = format.format(out)+date;
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+01:00"));
            try {
                out = format2.parse(date);
                calendar.setTime(out);
                calendar.add(Calendar.DAY_OF_MONTH, -1);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Log.d("MyTag", "Date: |"+out+"|");
        }else {

            Locale russian = new Locale("ru");
            DateFormat format = new SimpleDateFormat("d MMMM yyyy, HH:mm", russian);
            try {
                Date out = format.parse(date);
                Log.d("MyTag", "Date: |"+out+"|");
            } catch (ParseException e) {
                System.out.println("12312");
            }
        }
        this.date = date;
    }

    public SeriesItem() {
    }

    public SeriesItem(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        boolean result = true;
        if (o instanceof SeriesItem){
            SeriesItem in = (SeriesItem)o;
            if (!this.name.equals(in.getName())){
                result = false;
            }
            if (!this.translate.equals(in.getTranslate())){
                result = false;
            }
            if (!this.seria.equals(in.getSeria())){
                result = false;
            }
            if (!this.season.equals(in.getSeason())){
                result = false;
            }
        }


        return result;
    }

    @Override
    public int hashCode() {
        return (name+translate+season+seria).hashCode();
    }

    @Override
    public String toString() {
        return name+" - "+translate+" - "+seria+" - "+season;
    }
}
