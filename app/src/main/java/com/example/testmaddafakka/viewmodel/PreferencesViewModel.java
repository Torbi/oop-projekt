package com.example.testmaddafakka.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testmaddafakka.model.ICategory;
import com.example.testmaddafakka.repository.FilmsterRepository;

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


    public void init(Context ctx) {
        if(categories != null) {
            return;
        }
        filmsterRepository = FilmsterRepository.getInstance(ctx);
        loadCategories();
    }

    public void loadSelectedCategory(String category){
        filmsterRepository.loadSelectedCategory(category);
    }

    public LiveData<List<ICategory>> getCategories() {
        if (categories == null) {
            categories = new MutableLiveData<>();
            loadCategories();
        }
        return categories;
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
