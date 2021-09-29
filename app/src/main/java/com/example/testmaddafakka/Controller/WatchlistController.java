package com.example.testmaddafakka.Controller;

import com.example.testmaddafakka.View.WatchlistView;

/**
 * This class is a controller, it listens to input from the view and then updates the model.
 * The model then updates the view accordingly.
 */

public class WatchlistController {

    private ModelAcces model;
    private WatchlistView view;

    public WatchlistController(ModelAcces model, WatchlistView view){
        this.model = model;
        this.view = view;
    }

}
