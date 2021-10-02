package com.example.testmaddafakka.model;

import com.example.testmaddafakka.api.IApiListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Filmster keeps track of all movies, users and currentMovies displayed on the screen
 */
public class Filmster{

    private List<Movie> moviesList;
    private User user;
    private int currentMovieCounter;

    /**
     * Constructor for filmster, initializes some data and receives a user for the program
     * @param user A user
     */
    public Filmster(User user) {
        this.user = user;
        this.currentMovieCounter = 0;
        moviesList = new ArrayList<>();

        //a fake movie is added to give time for imdbapiadapter to get movies from the api
        moviesList.add(new Movie(" Inception ", "12123", " 9.1 ", " Jessica Alba ", " https://imdb-api.com/Images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6791_AL_.jpg "," 2010 "));
    }

    public void setMoviesList(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    public List<Movie> getMoviesList() {
        return moviesList;
    }

    public User getUser() {
        return this.user;
    }


   public Movie getCurrentMovie() {
       return moviesList.get(currentMovieCounter);
   }

   public void nextMovie() {
      currentMovieCounter++;
   }


    /**
     * Adds a movie to the user liked movie list and calls for the next movie to be displayed
     * @param movie - The movie to be added to the users liked movies list
     */
    public void addLikedMovie(Movie movie) {
        this.user.addLikedMovie(movie);
        nextMovie();
    }

    /**
     * Adds a movie to the user disliked movie list and calls for the next movie to be displayed
     * @param movie - The movie to be added to the users disliked movies list
     */
    public void addDislikedMovie(Movie movie) {
        this.user.addDislikedMovie(movie);
        nextMovie();
    }

}
