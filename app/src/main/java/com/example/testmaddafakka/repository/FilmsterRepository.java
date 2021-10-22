<<<<<<< HEAD
<<<<<<< HEAD
package com.example.testmaddafakka.repository;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.testmaddafakka.api.ApiListener;
import com.example.testmaddafakka.api.IAdapter;
import com.example.testmaddafakka.api.ApiAdapter;
import com.example.testmaddafakka.api.strategies.DefaultBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.DefaultMovieCreatorStrategy;
import com.example.testmaddafakka.api.strategies.DefaultParseStrategy;
import com.example.testmaddafakka.api.strategies.IMDbListBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.IMDbNameBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.IMDbSearchNameBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.SearchResultCreatorStrategy;
import com.example.testmaddafakka.model.Filmster;
import com.example.testmaddafakka.model.ICategory;
import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.api.IApiListener;
import com.example.testmaddafakka.model.Preferences;
import com.example.testmaddafakka.model.User;
import com.example.testmaddafakka.model.WatchList;

import java.util.List;

/**
 * A repository that handles all communication between the viewmodels and the api/model data
 * Follows the mvvm architecture
 *
 * @author Albin Sundström
 */
public class FilmsterRepository implements IApiListener {
    private static FilmsterRepository instance;
    private IAdapter imdbAdapter;
    private MutableLiveData<List<IMedia>> medias;
    private MutableLiveData<IMedia> currentMedia;
    private ApiListener listener;
    private Filmster filmster;
    private User user;
    private int current = 0;
    private MutableLiveData<List<ICategory>> categories;
    private MutableLiveData<List<IMedia>> searchResults;
    private boolean isSearchResults;


    private FilmsterRepository(Context ctx) {
        medias = new MutableLiveData<>();
        currentMedia = new MutableLiveData<>();
        user = new User("TestNamn", "TestPass", new WatchList(), new Preferences());
        filmster = new Filmster(user);
        listener = new ApiListener();
        listener.addListener(this);
        categories = new MutableLiveData<>();
        searchResults = new MutableLiveData<>();
        isSearchResults = false;

        imdbAdapter = new ApiAdapter(ctx, listener);
        //loadMedias();
        loadSelectedCategory("Popular");
    }

    public static FilmsterRepository getInstance(@Nullable Context ctx) {
        if (instance == null) {
            instance = new FilmsterRepository(ctx);
        }
        return instance;
    }
    public void initCategories(Context ctx) {
        if(categories != null) {
            return;
        }
        getInstance(ctx);
        loadCategories();
    }
    public void loadCategories() {
        // Do an asynchronous operation to fetch a category
        categories = getCategories();
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
        if(!isSearchResults){
            setMedias(medias);
        }else{
            setSearchResults(medias);
        }
    }

    private void setSearchResults(List<IMedia> results){
        this.searchResults.setValue(results);
        filmster.setResultList(results);
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

    public void nextMedia() {
        this.currentMedia.setValue(this.medias.getValue().get(current));
        current++;
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
        imdbAdapter.getList(getSelectedCategory(categoryName));
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


    public LiveData<List<ICategory>> getCategoriesNEW() {
        if (this.categories == null) {
            this.categories = new MutableLiveData<>();
            loadCategories();
        }
        return this.categories;
    }

    public void search(String name){
        imdbAdapter.setBuildRequestStrategy(new IMDbSearchNameBuildRequestStrategy());
        imdbAdapter.setParseStrategy(new DefaultParseStrategy("results"));
        imdbAdapter.setMediaObjectCreateStrategy(new SearchResultCreatorStrategy());
        isSearchResults = true;
        imdbAdapter.getList(name);
    }

    public MutableLiveData<List<IMedia>> getSearchResults(){
        // get list from api
        this.searchResults.setValue(filmster.getResultList());

        return this.searchResults;
    }

    public void loadChosenID(int pos){
        String id = filmster.getChosenID(pos);
        loadSearchedNameMedias(id);
    }

    private void loadSearchedNameMedias(String id){
        imdbAdapter.setBuildRequestStrategy(new IMDbNameBuildRequestStrategy());
        imdbAdapter.setParseStrategy(new DefaultParseStrategy("castMovies"));
        imdbAdapter.setMediaObjectCreateStrategy(new DefaultMovieCreatorStrategy());
        isSearchResults = false;
        imdbAdapter.getList(id);
    }
}
=======
package com.example.testmaddafakka.repository;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.testmaddafakka.api.ApiListener;
import com.example.testmaddafakka.api.IAdapter;
import com.example.testmaddafakka.api.ApiAdapter;
import com.example.testmaddafakka.api.strategies.CastMovieCreatorStrategy;
import com.example.testmaddafakka.api.strategies.DefaultBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.DefaultMovieCreatorStrategy;
import com.example.testmaddafakka.api.strategies.DefaultParseStrategy;
import com.example.testmaddafakka.api.strategies.IMDbListBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.IMDbNameBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.IMDbSearchCurrentMovieBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.IMDbSearchNameBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.MovieParseStrategy;
import com.example.testmaddafakka.api.strategies.SearchResultCreatorStrategy;
import com.example.testmaddafakka.model.Filmster;
import com.example.testmaddafakka.model.ICategory;
import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.api.IApiListener;
import com.example.testmaddafakka.model.Preferences;
import com.example.testmaddafakka.model.User;
import com.example.testmaddafakka.model.WatchList;

import java.util.List;

/**
 * A repository that handles all communication between the viewmodels and the api/model data
 * Follows the mvvm architecture
 *
 * @author Albin Sundström
 */
public class FilmsterRepository implements IApiListener {
    private static FilmsterRepository instance;
    private IAdapter imdbAdapter;
    private MutableLiveData<List<IMedia>> medias;
    private MutableLiveData<IMedia> currentMedia;
    private ApiListener listener;
    private Filmster filmster;
    private User user;
    private int current = 0;
    private MutableLiveData<List<ICategory>> categories;
    private MutableLiveData<List<IMedia>> searchResults;
    private MutableLiveData<List<IMedia>> castMovies;
    private boolean isSearchResults;
    private boolean isCastMovies;


    private FilmsterRepository(Context ctx) {
        medias = new MutableLiveData<>();
        currentMedia = new MutableLiveData<>();
        user = new User("TestNamn", "TestPass", new WatchList(), new Preferences());
        filmster = new Filmster(user);
        listener = new ApiListener();
        listener.addListener(this);
        categories = new MutableLiveData<>();
        searchResults = new MutableLiveData<>();
        isSearchResults = false;
        isCastMovies = false;

        imdbAdapter = new ApiAdapter(ctx, listener);
        //loadMedias();
        loadSelectedCategory("Popular");
    }

    public static FilmsterRepository getInstance(@Nullable Context ctx) {
        if (instance == null) {
            instance = new FilmsterRepository(ctx);
        }
        return instance;
    }
    public void initCategories(Context ctx) {
        if(categories != null) {
            return;
        }
        getInstance(ctx);
        loadCategories();
    }
    public void loadCategories() {
        // Do an asynchronous operation to fetch a category
        categories = getCategories();
    }

    public MutableLiveData<IMedia> getCurrentMedia() {
        if(!isCastMovies){
            this.currentMedia.setValue(filmster.getCurrentMedia());
        }else{
            String id = filmster.getCurrentMedia().getID();
        }



        return this.currentMedia;
    }
    private void setMedias(List<IMedia> medias) {
        this.medias.setValue(medias);
        filmster.setMediaList(medias);
    }
    private void setCastMovies(List<IMedia> medias) {
        System.out.println(medias.size());
        this.castMovies.setValue(medias); // null object reference här
        filmster.setCastMovieList(medias);
    }

    @Override
    public void update(List<IMedia> medias) {
        if(!isSearchResults){
            if(!isCastMovies){
                setMedias(medias);
            }else{
                setCastMovies(medias);
            }
        }else{
            setSearchResults(medias);
        }
    }

    private void setSearchResults(List<IMedia> results){
        this.searchResults.setValue(results);
        filmster.setResultList(results);
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

    public void nextMedia() {
        if(isCastMovies){
            loadCurrentSearchedNameMedia(this.castMovies.getValue().get(current).getID());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.currentMedia.setValue(this.medias.getValue().get(current));
        current++;
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
        imdbAdapter.getList(getSelectedCategory(categoryName));
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


    public LiveData<List<ICategory>> getCategoriesNEW() {
        if (this.categories == null) {
            this.categories = new MutableLiveData<>();
            loadCategories();
        }
        return this.categories;
    }

    public void search(String name){
        imdbAdapter.setBuildRequestStrategy(new IMDbSearchNameBuildRequestStrategy());
        imdbAdapter.setParseStrategy(new DefaultParseStrategy("results"));
        imdbAdapter.setMediaObjectCreateStrategy(new SearchResultCreatorStrategy());
        isSearchResults = true;
        imdbAdapter.getList(name);
    }

    public MutableLiveData<List<IMedia>> getSearchResults(){
        // get list from api
        this.searchResults.setValue(filmster.getResultList());

        return this.searchResults;
    }

    public void loadChosenID(int pos){
        loadSearchedNameMedias(filmster.getChosenID(pos));
    }

    private void loadSearchedNameMedias(String id){
        imdbAdapter.setBuildRequestStrategy(new IMDbNameBuildRequestStrategy());
        imdbAdapter.setParseStrategy(new DefaultParseStrategy("castMovies"));
        imdbAdapter.setMediaObjectCreateStrategy(new CastMovieCreatorStrategy());
        isSearchResults = false;
        isCastMovies = true;
        imdbAdapter.getList(id);
    }

    private void loadCurrentSearchedNameMedia(String id){
        imdbAdapter.setBuildRequestStrategy(new IMDbSearchCurrentMovieBuildRequestStrategy());
        imdbAdapter.setParseStrategy(new MovieParseStrategy());
        imdbAdapter.setMediaObjectCreateStrategy(new DefaultMovieCreatorStrategy());
        isSearchResults = false;
        isCastMovies = false;
        imdbAdapter.getList(id);
    }
}
>>>>>>> 98d445f (had to change search a bit because of the API-structure)
=======
package com.example.testmaddafakka.repository;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.testmaddafakka.api.ApiListener;
import com.example.testmaddafakka.api.IAdapter;
import com.example.testmaddafakka.api.ApiAdapter;
import com.example.testmaddafakka.api.strategies.CastMovieCreatorStrategy;
import com.example.testmaddafakka.api.strategies.DefaultBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.DefaultMovieCreatorStrategy;
import com.example.testmaddafakka.api.strategies.DefaultParseStrategy;
import com.example.testmaddafakka.api.strategies.IMDbListBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.IMDbNameBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.IMDbSearchCurrentMovieBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.IMDbSearchNameBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.MovieParseStrategy;
import com.example.testmaddafakka.api.strategies.SearchResultCreatorStrategy;
import com.example.testmaddafakka.model.Filmster;
import com.example.testmaddafakka.model.ICategory;
import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.api.IApiListener;
import com.example.testmaddafakka.model.Preferences;
import com.example.testmaddafakka.model.User;
import com.example.testmaddafakka.model.WatchList;

import java.util.List;

/**
 * A repository that handles all communication between the viewmodels and the api/model data
 * Follows the mvvm architecture
 *
 * @author Albin Sundström
 */
public class FilmsterRepository implements IApiListener {
    private static FilmsterRepository instance;
    private IAdapter imdbAdapter;
    private MutableLiveData<List<IMedia>> medias;
    private MutableLiveData<IMedia> currentMedia;
    private ApiListener listener;
    private Filmster filmster;
    private User user;
    private int current = 1;
    private MutableLiveData<List<ICategory>> categories;
    private MutableLiveData<List<IMedia>> searchResults;
    private MutableLiveData<List<IMedia>> castMovies;
    private boolean isSearchResults;
    private boolean isCastMovies;
    private boolean isSingleMovie;


    private FilmsterRepository(Context ctx) {
        medias = new MutableLiveData<>();
        currentMedia = new MutableLiveData<>();
        user = new User("TestNamn", "TestPass", new WatchList(), new Preferences());
        filmster = new Filmster(user);
        listener = new ApiListener();
        listener.addListener(this);
        categories = new MutableLiveData<>();
        searchResults = new MutableLiveData<>();
        castMovies = new MutableLiveData<>();
        isSearchResults = false;
        isCastMovies = false;
        isSingleMovie = false;

        imdbAdapter = new ApiAdapter(ctx, listener);
        //loadMedias();
        loadSelectedCategory("Popular");
    }

    public static FilmsterRepository getInstance(@Nullable Context ctx) {
        if (instance == null) {
            instance = new FilmsterRepository(ctx);
        }
        return instance;
    }
    public void initCategories(Context ctx) {
        if(categories != null) {
            return;
        }
        getInstance(ctx);
        loadCategories();
    }
    public void loadCategories() {
        // Do an asynchronous operation to fetch a category
        categories = getCategories();
    }

    public MutableLiveData<IMedia> getCurrentMedia() {
        this.currentMedia.setValue(filmster.getCurrentMedia());
        return this.currentMedia;
    }

    private void setMedias(List<IMedia> medias) {
        this.medias.setValue(medias);
        filmster.setMediaList(medias);
    }
    private void setCastMovies(List<IMedia> medias) {
        this.castMovies.setValue(medias);
        filmster.setCastMovieList(medias);
        loadCurrentSearchedNameMedia(filmster.getCastMovies().get(current).getID()); // If movie is "in-production" it return nulls, so must check that
    }


    @Override
    public void update(List<IMedia> medias) {
        if(!isSearchResults){
            if(!isCastMovies){
                setMedias(medias);
                System.out.println(medias.get(0).getName());
            }else{
                setCastMovies(medias);
            }
        }else{
            setSearchResults(medias);
        }
    }

    private void setSearchResults(List<IMedia> results){
        this.searchResults.setValue(results);
        filmster.setResultList(results);
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

    public void nextMedia() {
        if(!isSingleMovie){
            this.currentMedia.setValue(this.medias.getValue().get(current));
        }else {
            loadCurrentSearchedNameMedia(filmster.getCastMovies().get(current).getID()); // If movie is "in-production" it return nulls, so must check that
            this.currentMedia.setValue(this.medias.getValue().get(current));
        }
        current++;
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
        imdbAdapter.getList(getSelectedCategory(categoryName));
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


    public LiveData<List<ICategory>> getCategoriesNEW() {
        if (this.categories == null) {
            this.categories = new MutableLiveData<>();
            loadCategories();
        }
        return this.categories;
    }

    public void search(String name){
        imdbAdapter.setBuildRequestStrategy(new IMDbSearchNameBuildRequestStrategy());
        imdbAdapter.setParseStrategy(new DefaultParseStrategy("results"));
        imdbAdapter.setMediaObjectCreateStrategy(new SearchResultCreatorStrategy());
        isSearchResults = true;
        imdbAdapter.getList(name);
    }

    public MutableLiveData<List<IMedia>> getSearchResults(){
        // get list from api
        this.searchResults.setValue(filmster.getResultList());

        return this.searchResults;
    }

    public void loadChosenID(int pos){
        loadSearchedNameMedias(filmster.getChosenID(pos));
    }

    private void loadSearchedNameMedias(String id){
        imdbAdapter.setBuildRequestStrategy(new IMDbNameBuildRequestStrategy());
        imdbAdapter.setParseStrategy(new DefaultParseStrategy("castMovies"));
        imdbAdapter.setMediaObjectCreateStrategy(new CastMovieCreatorStrategy());
        isSearchResults = false;
        isCastMovies = true;
        imdbAdapter.getList(id);
    }

    private void loadCurrentSearchedNameMedia(String id){
        imdbAdapter.setBuildRequestStrategy(new IMDbSearchCurrentMovieBuildRequestStrategy());
        imdbAdapter.setParseStrategy(new MovieParseStrategy());
        imdbAdapter.setMediaObjectCreateStrategy(new DefaultMovieCreatorStrategy());
        isSearchResults = false;
        isCastMovies = false;
        isSingleMovie = true;
        imdbAdapter.getList(id);
    }
}
>>>>>>> 36b5d39 (fixed getting a single movie from the api)
=======
package com.example.testmaddafakka.repository;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.testmaddafakka.api.ApiListener;
import com.example.testmaddafakka.api.IAdapter;
import com.example.testmaddafakka.api.ApiAdapter;
import com.example.testmaddafakka.api.strategies.CastMovieCreatorStrategy;
import com.example.testmaddafakka.api.strategies.DefaultBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.DefaultMovieCreatorStrategy;
import com.example.testmaddafakka.api.strategies.DefaultParseStrategy;
import com.example.testmaddafakka.api.strategies.IMDbListBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.IMDbNameBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.IMDbSearchCurrentMovieBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.IMDbSearchNameBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.MovieParseStrategy;
import com.example.testmaddafakka.api.strategies.SearchResultCreatorStrategy;
import com.example.testmaddafakka.model.Filmster;
import com.example.testmaddafakka.model.ICategory;
import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.api.IApiListener;
import com.example.testmaddafakka.model.Preferences;
import com.example.testmaddafakka.model.User;
import com.example.testmaddafakka.model.WatchList;

import java.util.List;

/**
 * A repository that handles all communication between the viewmodels and the api/model data
 * Follows the mvvm architecture
 * @author Albin Sundström
 */
public class FilmsterRepository implements IApiListener {
    private static FilmsterRepository instance;
    private IAdapter imdbAdapter;
    private MutableLiveData<List<IMedia>> medias;
    private MutableLiveData<IMedia> currentMedia;
    private ApiListener listener;
    private Filmster filmster;
    private User user;
    private int current = 1;
    private MutableLiveData<List<ICategory>> categories;
    private MutableLiveData<List<IMedia>> searchResults;
    private MutableLiveData<List<IMedia>> castMovies;
    private boolean isSearchResults;
    private boolean isCastMovies;
    private boolean isSingleMovie;


    private FilmsterRepository(Context ctx) {
        medias = new MutableLiveData<>();
        currentMedia = new MutableLiveData<>();
        user = new User("TestNamn", "TestPass", new WatchList(), new Preferences());
        filmster = new Filmster(user);
        listener = new ApiListener();
        listener.addListener(this);
        categories = new MutableLiveData<>();
        searchResults = new MutableLiveData<>();
        castMovies = new MutableLiveData<>();
        isSearchResults = false;
        isCastMovies = false;
        isSingleMovie = false;

        imdbAdapter = new ApiAdapter(ctx, listener);
        //loadMedias();
        loadSelectedCategory("Popular");
    }

    public static FilmsterRepository getInstance(@Nullable Context ctx) {
        if (instance == null) {
            instance = new FilmsterRepository(ctx);
        }
        return instance;
    }
    public void initCategories(Context ctx) {
        if(categories != null) {
            return;
        }
        getInstance(ctx);
        loadCategories();
    }
    public void loadCategories() {
        // Do an asynchronous operation to fetch a category
        categories = getCategories();
    }

    public MutableLiveData<IMedia> getCurrentMedia() {
        this.currentMedia.setValue(filmster.getCurrentMedia());
        return this.currentMedia;
    }

    private void setMedias(List<IMedia> medias) {
        //System.out.println("Nu sätts medias " + this.medias.getValue().size() + " storlek innan");
        this.medias.setValue(medias);
        filmster.setMediaList(medias);
    }
    private void setCastMovies(List<IMedia> medias) {
        this.castMovies.setValue(medias);
        filmster.setCastMovieList(medias);
        for(IMedia media: medias) {
        }
        loadCurrentSearchedNameMedia(filmster.getCastMovies().get(current).getID()); // If movie is "in-production" it return nulls, so must check that
    }


    @Override
    public void update(List<IMedia> medias) {
        if(!isSearchResults){
            if(!isCastMovies){
                setMedias(medias);
                System.out.println(medias.get(0).getName());
            }else{
                setCastMovies(medias);
            }
        }else{
            setSearchResults(medias);
        }
    }

    private void setSearchResults(List<IMedia> results){
        this.searchResults.setValue(results);
        filmster.setResultList(results);
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

    public void nextMedia() {
        if(!isSingleMovie){
            this.currentMedia.setValue(this.medias.getValue().get(current));
        }else {
            loadCurrentSearchedNameMedia(filmster.getCastMovies().get(current).getID()); // If movie is "in-production" it return nulls, so must check that
            this.currentMedia.setValue(this.medias.getValue().get(0));
        }
        current++;
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
        imdbAdapter.getList(getSelectedCategory(categoryName));
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


    public LiveData<List<ICategory>> getCategoriesNEW() {
        if (this.categories == null) {
            this.categories = new MutableLiveData<>();
            loadCategories();
        }
        return this.categories;
    }

    public void search(String name){
        imdbAdapter.setBuildRequestStrategy(new IMDbSearchNameBuildRequestStrategy());
        imdbAdapter.setParseStrategy(new DefaultParseStrategy("results"));
        imdbAdapter.setMediaObjectCreateStrategy(new SearchResultCreatorStrategy());
        isSearchResults = true;
        imdbAdapter.getList(name);
    }

    public MutableLiveData<List<IMedia>> getSearchResults(){
        // get list from api
        this.searchResults.setValue(filmster.getResultList());

        return this.searchResults;
    }

    public void loadChosenID(int pos){
        loadSearchedNameMedias(filmster.getChosenID(pos));
    }

    private void loadSearchedNameMedias(String id){
        imdbAdapter.setBuildRequestStrategy(new IMDbNameBuildRequestStrategy());
        imdbAdapter.setParseStrategy(new DefaultParseStrategy("castMovies"));
        imdbAdapter.setMediaObjectCreateStrategy(new CastMovieCreatorStrategy());
        isSearchResults = false;
        isCastMovies = true;
        imdbAdapter.getList(id);
    }

    private void loadCurrentSearchedNameMedia(String id){
        imdbAdapter.setBuildRequestStrategy(new IMDbSearchCurrentMovieBuildRequestStrategy());
        imdbAdapter.setParseStrategy(new MovieParseStrategy());
        imdbAdapter.setMediaObjectCreateStrategy(new DefaultMovieCreatorStrategy());
        isSearchResults = false;
        isCastMovies = false;
        isSingleMovie = true;
        imdbAdapter.getList(id);
    }
}
>>>>>>> 44a7f7d (search fully works)
