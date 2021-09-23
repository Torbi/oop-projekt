package com.example.testmaddafakka.Controller;

import com.example.testmaddafakka.View.WatchlistView;

public class WatchlistController {

    private ModelAcces model;
    private WatchlistView view;

    public WatchlistController(ModelAcces model, WatchlistView view){
        this.model = model;
        this.view = view;
    }
}
