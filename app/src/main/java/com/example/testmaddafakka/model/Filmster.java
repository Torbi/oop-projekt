package com.example.testmaddafakka.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Filmster keeps track of all movies, users and currentMovies displayed on the screen
 */
public class Filmster{

    private List<IMedia> mediaList;
    private User user;
    private int currentMediaCounter;
    private List<ICategory> categoryList;
    private List<IMedia> resultList;
    private List<IMedia> castMovies;

    /**
     * Constructor for filmster, initializes some data and receives a user for the program
     * @param user A user
     */
    public Filmster(User user) {
        this.user = user;
        this.currentMediaCounter = 0;
        mediaList = new ArrayList<>();
        categoryList = new ArrayList<>();
        resultList = new ArrayList<>();

        //a fake movie is added to give time for imdbapiadapter to get movies from the api
        mediaList.add(new Movie(" Inception ", "12123", " 9.1 ", " https://imdb-api.com/Images/original/MV5BMjAxMzY3NjcxNF5BMl5BanBnXkFtZTcwNTI5OTM0Mw@@._V1_Ratio0.6791_AL_.jpg "," 2010 "));
    }

    public void setMediaList(List<IMedia> moviesList) {
        this.mediaList = moviesList;
    }

    public IMedia getCurrentMedia() {
        if(castMovies != null){
            return castMovies.get(currentMediaCounter);
        }else{
            return mediaList.get(currentMediaCounter);
        }
   }

    public void nextMedia() {
      currentMediaCounter++;
   }

   public void setResultList(List<IMedia> results){
        this.resultList = results;
   }

    public List<IMedia> getResultList() {
        return resultList;
    }

    public String getChosenID(int pos){
        return resultList.get(pos).getID();
    }

    public void setCastMovieList(List<IMedia> moviesList) {
        this.castMovies = moviesList;
    }

    /**
     * Adds a media to the user liked movie list and calls for the next movie to be displayed
     * @param media - The movie to be added to the users liked movies list
     */
    public void addLikedMedia(IMedia media) {
        this.user.addLikedMedia(media);
        nextMedia();
    }

    /**
     * Adds a movie to the user disliked movie list and calls for the next movie to be displayed
     * @param media - The movie to be added to the users disliked movies list
     */
    public void addDislikedMedia(IMedia media) {
        this.user.addDislikedMedia(media);
        nextMedia();
    }
    public void addWatchedMedia(IMedia media){
        this.user.addWatchedMedia(media);
        nextMedia();
    }


    public String CurrentUsersCategory(String categoryName){

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
        for(int i = 0; i < listSize; i++){
            categoryList.add(user.getPreferences().getMovieGenres().get(i));
        }
        return categoryList;
    }

    public List<IMedia> getMediaList(){
        return mediaList;
    }
}
