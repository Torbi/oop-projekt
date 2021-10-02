package com.example.testmaddafakka.view;

import com.example.testmaddafakka.model.Movie;

import java.util.List;

public interface ViewListener {

    public void update(List<Movie> list);
}
