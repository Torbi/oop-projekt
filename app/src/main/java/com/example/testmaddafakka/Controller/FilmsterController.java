package com.example.testmaddafakka.Controller;

import com.example.testmaddafakka.View.MainView;

public class FilmsterController {
    private ModelAcces model;
    private MainView view;

    public FilmsterController(ModelAcces model, MainView view){
        this.model = model;
        this.view = view;
    }
}
