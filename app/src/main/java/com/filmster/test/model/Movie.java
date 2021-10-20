package com.filmster.test.model;

/**
 * A movie contains a name, id , rating, may contain a crew/starring, an image, a year it was made and
 * a state on if its watched, liked or disliked.
 */
public class Movie implements IMedia {

    private String name;
    private String id;
    private Double rating;
    private String starring;
    private String image;
    private int year;
    private MediaState state;

    /**
     * A movie object that contains information about a movie
     * @param name - The title of the movie
     * @param id - The id a movie has on imdb, starts with "tt"
     * @param rating - The imdb rating of the movie
     * @param image - A string for where the image can be found online
     * @param year - A string representing the year the movie was made
     */
    public Movie(String name, String id, Double rating, String image, int year) {
        this.name = checkMovieLength(name);
        this.id = id;
        this.rating = rating;
        this.starring = "";
        this.image = image;
        this.year = year;
    }

    public Movie(String name, String id, Double rating, String starring , String image, int year) {
        this.name = checkMovieLength(name);
        this.id = id;
        this.rating = rating;
        this.starring = starring;
        this.image = image;
        this.year = year;
        this.state = MediaState.DEFAULT;
    }

    private String checkMovieLength(String title){
        if(title.length() > 15){
            return title.substring(0, 16) + "...";
        }
        return title;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getStarring() {
        return starring;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public Double getRating() {
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