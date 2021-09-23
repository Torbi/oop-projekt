package com.example.testmaddafakka.Controller;

import com.example.testmaddafakka.View.PreferencesView;

/**
 * This class is a controller, it listens to input from the view and then updates the model.
 * The model then updates the view accordingly.
 */

public class PreferencesController {

    private ModelAcces model;
    private PreferencesView view;

    public PreferencesController(ModelAcces model, PreferencesView view){
        this.model = model;
        this.view = view;
    }
}
