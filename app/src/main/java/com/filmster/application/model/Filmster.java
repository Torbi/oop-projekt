package com.filmster.application.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Filmster keeps track of all movies, users and currentMovies displayed on the screen
 */
public class Filmster{

    private List<IMedia> mediaList;
    private final User user;
    private int currentMediaCounter;
    private final List<ICategory> categoryList;

    /**
     * Constructor for filmster, initializes some data and receives a user for the program
     * @param user A user
     */
    public Filmster(User user) {
        this.user = user;
        this.currentMediaCounter = 0;
        this.mediaList = new ArrayList<>();
        this.categoryList = new ArrayList<>();

        //a fake movie is added to give time for imdbapiadapter to get real movies from the api
        this.mediaList.add(new Movie("Inception", "tt1375666", 9.9, "https://imdb-api.com/Images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6791_AL_.jpg",2010));
        //this.mediaList.add(new Movie("Inception", "12123", 9.1, "https://imdb-api.com/Images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6791_AL_.jpg",2020));
    }

    public void setMediaList(List<IMedia> moviesList) {
        this.mediaList = moviesList;
        this.currentMediaCounter = 0;
    }

    public IMedia getCurrentMedia() {
       return mediaList.get(currentMediaCounter);
   }

    public void nextMedia() {
      currentMediaCounter++;
   }


    /**
     * Sets the state of the current media object to liked and then adds it the the watchlist,
     * then calls for the next media to be displayed
     * @param media - The media to be added to the users watchlist
     */
    public void addLikedMedia(IMedia media) {
        media.setState(MediaState.LIKED);
        this.user.addMedia(media);
        nextMedia();
    }

    /**
     * Sets the state of the current media object to disliked and then adds it the the watchlist,
     * then calls for the next media to be displayed
     * @param media - The media to be added to the users watchlist
     */
    public void addDislikedMedia(IMedia media) {
        media.setState(MediaState.DISLIKED);
        this.user.addMedia(media);
        nextMedia();
    }

    /**
     * Sets the state of the current media object to seen and then adds it the the watchlist,
     * then calls for the next media to be displayed
     * @param media - The media to be added to the users watchlist
     */
    public void addWatchedMedia(IMedia media){
        media.setState(MediaState.SEEN);
        this.user.addMedia(media);
        nextMedia();
    }

    public String currentUsersCategory(String categoryName){
        ICategory category = user.getPreferences().getMatchingGenre(categoryName);
        //Genre category = user.getPreferences().searchMovieGenres(categoryName);
        return category.getID();
    }

    /**
     * Collects categories
     * @return A list of categories
     */
    public List<ICategory> getMovieCategories() {
        int listSize = user.getPreferences().getMovieGenres().size();
        if(categoryList.isEmpty()){
            for(int i = 0; i < listSize; i++){
                categoryList.add(user.getPreferences().getMovieGenres().get(i));
            }
        }
        return categoryList;
    }
}
