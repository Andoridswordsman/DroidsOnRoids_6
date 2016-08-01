package com.droidsonroids.materialmusicfacts.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Album {

    @SerializedName("title") String title;
    @SerializedName("year") int year;
    @SerializedName("cover_img") String coverImg;
    @SerializedName("songs") List<Song> songs;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(final int year) {
        this.year = year;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(final String coverImg) {
        this.coverImg = coverImg;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(final List<Song> songs) {
        this.songs = songs;
    }
}
