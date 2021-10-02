package com.example.testmaddafakka.model;

import java.util.ArrayList;


public class Filmster {



    private ArrayList <Movie> moviesList  = new ArrayList<Movie>();
    private ArrayList <User> userList  = new ArrayList<User>();


    public Filmster(ArrayList<Movie> moviesList, ArrayList<User> userList) {
        this.moviesList = moviesList;
        this.userList = userList;
    }

    public ArrayList<Movie> getMoviesList() {
        return moviesList;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }
}
