package com.filmster.test.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.filmster.test.model.ICategory;
import com.filmster.test.repository.FilmsterRepository;

import java.util.List;

/**
 * A viewmodel ....
 *
 * @author Albin Sundström
 */
public class PreferencesViewModel extends ViewModel {

    private FilmsterRepository filmsterRepository;

    private MutableLiveData<List<ICategory>> categories;
    private MutableLiveData<List<ICategory>> searchResults;

    public LiveData<List<ICategory>> getCategories() {
        if (this.categories == null) {
            this.categories = new MutableLiveData<>();
            loadCategories();
        }
        return this.categories;
    }
    public void init(Context ctx) {
        filmsterRepository = FilmsterRepository.getInstance(ctx);
    }

    public void loadSelectedCategory(String category){
        filmsterRepository.loadSelectedCategory(category);
    }

    private void loadCategories() {
        // Do an asynchronous operation to fetch a category
        categories = filmsterRepository.getCategories();
    }

    public void search(String name){
        filmsterRepository.search(name);
    }

    public LiveData<List<ICategory>> getSearchResults(){
        if(searchResults == null){
            searchResults = new MutableLiveData<>();
        }
        searchResults.setValue(filmsterRepository.getSearchResults());
        return searchResults;

    }

}