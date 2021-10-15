package com.example.testmaddafakka.model;

public class Movie implements IMedia {

    private String title;
    private String id;
    private String rating;
    private String starring;
    private String image;
    private String year;
    private MediaState state;

    /**
     * A movie object that contains information about a movie
     * @param title - The title of the movie
     * @param id - The id a movie has on imdb, starts with "tt"
     * @param rating - The imdb rating of the movie
     * @param image - A string for where the image can be found online
     * @param year - A string representing the year the movie was made
     */
    public Movie(String title, String id, String rating, String image, String year) {
        this.title = title;
        this.id = id;
        this.rating = rating;
        this.starring = "";
        this.image = image;
        this.year = year;
    }

    public Movie(String title, String id, String rating, String starring , String image, String year) {
        this.title = title;
        this.id = id;
        this.rating = rating;
        this.starring = starring;
        this.image = image;
        this.year = year;
        this.state = MediaState.DEFAULT;
    }

    @Override
    public String getYear() {
        return year;
    }

    @Override
    public String getName() {
        return title;
    }

    public String getStarring() {
        return starring;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public String getRating() {
        return rating;
    }

    @Override
    public MediaState getState() {
        return state;
    }

    @Override
    public void setState(MediaState state) {
        this.state = state;
    }
}
