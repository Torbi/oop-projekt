package com.filmster.application.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.filmster.application.model.ICategory;
import com.filmster.application.model.IMedia;
import com.filmster.application.repository.FilmsterRepository;

import java.util.List;

/**
 * A viewmodel ....
 *
 * @author Albin Sundström
 */
public class PreferencesViewModel extends ViewModel {

    private FilmsterRepository filmsterRepository;

    private MutableLiveData<List<ICategory>> categories;
    private MutableLiveData<List<IMedia>> searchResults;



    public void init(Context ctx) {
        filmsterRepository = FilmsterRepository.getInstance(ctx);
    }

    public void loadSelectedCategory(String category){
        filmsterRepository.loadSelectedCategory(category);
    }

    public LiveData<List<ICategory>> getCategories() {
        return filmsterRepository.getCategories();
    }


    private void loadCategories() {
        // Do an asynchronous operation to fetch a category
        categories = filmsterRepository.getCategories();
    }

    public void search(String name){
        filmsterRepository.search(name);
    }

    public LiveData<List<IMedia>> getSearchResults(){
        if(searchResults == null){
            searchResults = new MutableLiveData<>();
        }
        searchResults.setValue(filmsterRepository.getSearchResults().getValue());
        return searchResults;
    }

}
