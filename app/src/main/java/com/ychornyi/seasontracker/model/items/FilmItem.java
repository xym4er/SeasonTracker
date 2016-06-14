package com.ychornyi.seasontracker.model.items;

public class FilmItem {
    private String title;
    private String lastUpdate;

    public FilmItem(String title, String lastUpdate) {
        this.title = title;
        this.lastUpdate = lastUpdate;
    }

    public FilmItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
