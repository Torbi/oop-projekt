package com.example.testmaddafakka.model;

import java.util.ArrayList;
import java.util.List;

public class WatchList {

    private List<Movie> likedList;
    private List<Movie> dislikedList;
    private List<Movie> watchedList;

    public WatchList (){
        this.likedList = new ArrayList<>();
        this.dislikedList = new ArrayList<>();
        this.watchedList = new ArrayList<>();

    }

    public void addLikedMovie(Movie movie) {
       this.likedList.add(movie);
    }


    public void addDislikedMovie(Movie movie) {
        this.dislikedList.add(movie);
        if(likedList.contains(movie)){
            likedList.remove(movie);
        }
    }


    public void addWatchedMovie(Movie movie) {
        this.watchedList.add(movie);
    }


    public List<Movie> getLikedList() {
        return likedList;
    }

    public List<Movie> getDislikedList() {
        return dislikedList;
    }

    public List<Movie> getWatchedList() {
        return watchedList;
    }
}


