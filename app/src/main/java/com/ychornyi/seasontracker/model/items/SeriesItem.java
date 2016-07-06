package com.ychornyi.seasontracker.model.items;

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
