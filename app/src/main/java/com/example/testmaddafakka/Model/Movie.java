package com.example.testmaddafakka.Model;

public class Movie {

    private String title;
    private String information;
    private String rating;
    private String starring;
    private String image;
    private String imdbID;

    /**
     * A movie object that contains information about a movie
     * @param title - The title of the movie
     * @param information - Information about the movie
     * @param rating - The imdb rating of the movie
     * @param starring - The actors in the movie
     * @param image - A string for where the image can be found online
     * @param imdbID - The id a movie has on imdb, starts with "tt"
     */
    public Movie(String title, String information, String rating, String starring, String image, String imdbID) {
        this.title = title;
        this.information = information;
        this.rating = rating;
        this.starring = starring;
        this.image = image;
        this.imdbID = imdbID;
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

    public String getImdbID() {
        return imdbID;
    }

    public String getInformation() {
        return information;
    }
}
