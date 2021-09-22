package com.example.testmaddafakka.Controller;

import com.example.testmaddafakka.Model.Movie;
import com.example.testmaddafakka.View.Listener;

import java.util.List;

/**
 * The accesspoint for anything that wants to communicate with the model
 * All communication between controllers and model will go through this
 */
public class ModelAcces {

    private Listener listener;

    public ModelAcces() {
        listener = new Listener();
    }

    /**
     * Updates all current listeners
     * @param movies a list of movies
     */
    public void updateView(List<Movie> movies) {
        listener.notifyListeners(movies);
    }
}
