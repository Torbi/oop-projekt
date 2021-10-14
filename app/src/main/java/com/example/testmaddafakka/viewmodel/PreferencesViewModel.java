package com.example.testmaddafakka.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testmaddafakka.model.ICategory;
import com.example.testmaddafakka.repository.FilmsterRepository;

import java.util.List;

public class PreferencesViewModel extends ViewModel {

    private FilmsterRepository filmsterRepository;


    public void init(Context ctx) {
        filmsterRepository = FilmsterRepository.getInstance(ctx);
        filmsterRepository.initCategories(ctx);
    }

    public void loadSelectedCategory(String category){
        filmsterRepository.loadSelectedCategory(category);
    }

    public LiveData<List<ICategory>> getCategories() {
        return filmsterRepository.getCategories();
    }


}
