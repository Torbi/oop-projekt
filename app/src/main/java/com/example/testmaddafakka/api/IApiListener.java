package com.example.testmaddafakka.api;

import com.example.testmaddafakka.model.Movie;

import java.util.List;

public interface IApiListener {

    public void update(List<Movie> movie);
}
