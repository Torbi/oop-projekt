package com.example.testmaddafakka.Model;

import android.icu.util.ULocale;

import java.util.ArrayList;
import java.util.List;


public class Filmster {



    private ArrayList <Movie> moviesList  = new ArrayList<Movie>();
    private ArrayList <User> userList  = new ArrayList<User>();


    public Filmster(ArrayList<Movie> moviesList, ArrayList<User> userList) {
        this.moviesList = moviesList;
        this.userList = userList;
    }
}
