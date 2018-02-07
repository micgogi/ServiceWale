package com.rahul.servicewale;


public class Album {
    private String name;
    private String des;
    private int thumbnail;

    public Album() {
    }

    public Album(String name, String des, int thumbnail) {
        this.name = name;
        this.des = des;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumOfSongs() {
        return des;
    }

    public void setNumOfSongs(String des) {
        this.des = des;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
