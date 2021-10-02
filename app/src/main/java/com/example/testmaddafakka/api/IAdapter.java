package com.example.testmaddafakka.api;

import com.example.testmaddafakka.model.Movie;

import java.util.List;

public interface IAdapter {

    public void get250Movies();

    public List<Movie> getList(String listID);

}
