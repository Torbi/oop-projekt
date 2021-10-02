package com.example.testmaddafakka.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Movie implements Serializable {

    @SerializedName("title")
    private String title;
    @SerializedName("id")
    private String id;
    @SerializedName("IMDbRating")
    private String rating;
    @SerializedName("Crew")
    private String starring;
    @SerializedName("image")
    private String image;
    @SerializedName("Year")
    private String year;

    /**
     * A movie object that contains information about a movie
     * @param title - The title of the movie
     * @param id - The id a movie has on imdb, starts with "tt"
     * @param rating - The imdb rating of the movie
     * @param image - A string for where the image can be found online
     * @param year - A string representing the year the movie was made
     */
    public Movie(String title, String id, String rating, String starring, String image, String year) {
        this.title = title;
        this.id = id;
        this.rating = rating;
        this.starring = starring;
        this.image = image;
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public String getStarring() {
        return starring;
    }

    public String getImage() {
        return image;
    }

    public String getRating() {
        return rating;
    }


}
