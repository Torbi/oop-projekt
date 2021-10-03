package com.example.testmaddafakka.model;

import android.provider.Settings;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private String password;
    private WatchList watchList;
    private Preferences Preferences;

    /**
     * A movie object that contains information about a movie
     * @param name - The name of the user
     * @param password - the users password
     * @param watchList - The users watchList (an ArrayList) containing seen, liked or disliked movies
     * @param preferences - A selection of categories
     */

    public User (String name, String password, WatchList watchList, Preferences preferences) {
        this.name = name;
        this.password = password;
        this.watchList = watchList;
        this.Preferences = preferences;

    }

    public void addLikedMovie(Movie movie) {
        watchList.addLikedMovie(movie);
        System.out.println("RE");
    }

    public List<Movie> getLikedMovies(){
        return watchList.getLikedList();
    }
    public List<Movie> getDislikedMovies(){
        return watchList.getDislikedList();
    }
    public List<Movie> getWatchedMovies(){
        return watchList.getWatchedList();
    }
    public void addDislikedMovie(Movie movie) {
        watchList.addDislikedMovie(movie);
    }

    public void addWatchedMovie(Movie movie) {
        watchList.addWatchedMovie(movie);
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public WatchList getWatchList() {
        return watchList;
    }

    public Preferences getPreferences() {
        return Preferences;
    }
}
