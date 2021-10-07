package com.example.testmaddafakka.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.testmaddafakka.model.Category;
import com.example.testmaddafakka.repository.FilmsterRepository;

public class PreferencesViewModel extends ViewModel {

    private FilmsterRepository filmsterRepository;

    public void init(Context ctx) {
        filmsterRepository = FilmsterRepository.getInstance(ctx);
    }

    public void loadSelectedCategory(String category){
        filmsterRepository.loadSelectedCategory(category);
    }
}
