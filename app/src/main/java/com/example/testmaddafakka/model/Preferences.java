package com.example.testmaddafakka.model;

import java.util.ArrayList;
import java.util.List;


/**
 * A class ....
 *
 * @author Albin Sundström
 */

public class Preferences {

    private List<ICategory> movieGenres;

    public Preferences(){
        addMovieGenres();
        movieGenres = new ArrayList<>();
    }

    private void addMovieGenres(){
        //default category is top250movies
        movieGenres.add(new Genre("Top250Movies","Popular"));
        movieGenres.add(new Genre("ls051091770","Action"));
        movieGenres.add(new Genre("ls058726648","Comedy"));
        movieGenres.add(new Genre("ls009668711","Drama"));
        movieGenres.add(new Genre("ls009609925","Adventure"));
        movieGenres.add(new Genre("ls009668082","Sci-fi"));
        movieGenres.add(new Genre("ls009668314","Thriller"));
        movieGenres.add(new Genre("ls009389114","Family"));
        movieGenres.add(new Genre("ls049309814","Horror"));
        movieGenres.add(new Genre("ls009668031","Romance"));
        movieGenres.add(new Genre("ls009674465","Sport"));
        movieGenres.add(new Genre("ls009668766","Biography"));
        movieGenres.add(new Genre("ls009669258","Fantasy"));
        movieGenres.add(new Genre("ls079181605","Documentary"));
        movieGenres.add(new Genre("ls009668055","History"));
        movieGenres.add(new Genre("ls049110047","Musical"));
    }

    public List<ICategory> getMovieGenres() {
        return movieGenres;
    }
    /**
     * Iterates through a list of genres to find the genre-object matching with the genre-name
     * @param genre - The name of a genre
     * @return The matching genre-object
     */
    public ICategory getMatchingGenre(String genre){
        ICategory r = new Genre("noID", "ERROR");
        for(int i= 0; i < movieGenres.size(); i++){
            if(genre.equals(movieGenres.get(i).getName())){
                r = movieGenres.get(i);
            }
        }
        return r;
    }
}
