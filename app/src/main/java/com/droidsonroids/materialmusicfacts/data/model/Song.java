package com.droidsonroids.materialmusicfacts.data.model;

import com.google.gson.annotations.SerializedName;

public class Song {

    @SerializedName("title") String title;
    @SerializedName("length") String length;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getLength() {
        return length;
    }

    public void setLength(final String length) {
        this.length = length;
    }
}
