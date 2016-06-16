package com.ychornyi.seasontracker.model.items;

/**
 * Created by y.chornyi on 14.06.2016.
 */
public class SeriesItem {
    private int id;
    private String name;
    private int season;
    private int seria;
    private int date;

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

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getSeria() {
        return seria;
    }

    public void setSeria(int seria) {
        this.seria = seria;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public SeriesItem(int id, String name, int season, int seria, int date) {

        this.id = id;
        this.name = name;
        this.season = season;
        this.seria = seria;
        this.date = date;
    }
}
