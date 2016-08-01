package com.droidsonroids.materialmusicfacts.data.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Madonna {

    @SerializedName("nickname") String nickname;
    @SerializedName("name") String name;
    @SerializedName("surname") String surname;
    @SerializedName("place_of_birth") String placeOfBirth;
    @SerializedName("date_of_birth") String dateOfBirth;
    @SerializedName("genre") String genre;
    @SerializedName("albums") List<Album> albums;
    @SerializedName("bio") String bio;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(final String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(final String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(final List<Album> albums) {
        this.albums = albums;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(final String bio) {
        this.bio = bio;
    }
}
