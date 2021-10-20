package com.filmster.application.repository;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.filmster.application.api.IApiListener;
import com.filmster.application.model.ICategory;
import com.filmster.application.api.ApiListener;
import com.filmster.application.api.IApiAdapter;
import com.filmster.application.api.ApiAdapter;
import com.filmster.application.api.parse_buildrequest_strategies.DefaultBuildRequestStrategy;
import com.filmster.application.api.parse_buildrequest_strategies.IMDbListBuildRequestStrategy;
import com.filmster.application.model.Filmster;
import com.filmster.application.model.IMedia;
import com.filmster.application.model.Preferences;
import com.filmster.application.model.User;
import com.filmster.application.model.WatchList;

import java.util.List;

/**
 * A repository that handles all communication between the viewmodels and the api/model data
 * Follows the mvvm architecture
 *
 * @author Albin Sundström
 */
public class FilmsterRepository implements IApiListener {
    private static FilmsterRepository instance;
    private IApiAdapter imdbAdapter;
    private MutableLiveData<List<IMedia>> medias;
    private MutableLiveData<IMedia> currentMedia;
    private ApiListener listener;
    private Filmster filmster;
    private User user;
    private int current = 0;
    private MutableLiveData<List<ICategory>> categories;


    private FilmsterRepository(Context ctx) {
        medias = new MutableLiveData<>();
        currentMedia = new MutableLiveData<>();
        user = new User("TestNamn", "TestPass", new WatchList(), new Preferences());
        filmster = new Filmster(user);
        listener = new ApiListener();
        listener.addListener(this);
        categories = new MutableLiveData<>();

        imdbAdapter = new ApiAdapter(ctx, listener);
        loadSelectedCategory("Popular");
    }

    public static FilmsterRepository getInstance(@Nullable Context ctx) {
        if (instance == null) {
            instance = new FilmsterRepository(ctx);
        }
        return instance;
    }
    public MutableLiveData<IMedia> getCurrentMedia() {
        this.currentMedia.setValue(filmster.getCurrentMedia());
        return this.currentMedia;
    }
    private void setMedias(List<IMedia> medias) {
        this.medias.setValue(medias);
        filmster.setMediaList(medias);
    }

    @Override
    public void update(List<IMedia> medias) {
        setMedias(medias);
        current = 0;
    }

    public void addLikedMedia(IMedia media) {
        filmster.addLikedMedia(media);
        nextMedia();
    }

    public void addDislikedMedia(IMedia media) {
        filmster.addDislikedMedia(media);
        nextMedia();
    }
    public void addWatchedMedia(IMedia media){
        filmster.addWatchedMedia(media);
        nextMedia();
    }

    /**
     * Updates the current media to be displayed to the next
     * If out of objects to display start at the beginning
     * of medias and display them.
     */
    public void nextMedia() {
        this.currentMedia.setValue(this.medias.getValue().get(current));
        current++;
        if(current == this.medias.getValue().size()) {
            current = 0;
        }
    }
    public List<IMedia> getLikedMedias(){
        return user.getLikedMedia();
    }
    public List<IMedia> getDislikedMedias(){
        return user.getDislikedMedia();
    }
    public List<IMedia> getWatchedMedias(){
        return user.getWatchedMedia();
    }


    /**
     * Checks which strategy should be used depending on if its the default category or not
     * @param categoryName - The name of the category
     */
    public void loadSelectedCategory(String categoryName){
        if(!categoryName.equals("Popular")) {
            imdbAdapter.setBuildRequestStrategy(new IMDbListBuildRequestStrategy());
        } else {
            imdbAdapter.setBuildRequestStrategy(new DefaultBuildRequestStrategy());
        }
        imdbAdapter.loadResponse(getSelectedCategory(categoryName));
    }

    private String getSelectedCategory(String categoryName){
        return filmster.CurrentUsersCategory(categoryName);
    }

    /**
     * Sets a list with movie genres
     * @return A list of movie genres
     */
    public MutableLiveData<List<ICategory>> getCategories() {
        this.categories.setValue(filmster.getMovieCategories());
        return this.categories;
    }

    public void search(String name){
        // api.search or something
        //imdbAdapter.setBuildRequestStrategy(new IMDbNameBuildRequestStrategy());
        //imdbAdapter.setParseStrategy(new DefaultParseStrategy("results"));
        imdbAdapter.loadResponse(name);
    }

    public List<ICategory> getSearchResults(){
        // get list from api


        return null;
    }
}
