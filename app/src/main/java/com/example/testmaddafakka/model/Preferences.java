package com.example.testmaddafakka.model;

import java.util.ArrayList;
import java.util.List;

public class Preferences {

    private List<Category> movieCategories = new ArrayList<>();

    public Preferences(){
        addMovieCategories();
    }

    private void addMovieCategories(){
        //categories.add(new Category("noID","Genre"));
        movieCategories.add(new Category("ls051091770","Action"));
        movieCategories.add(new Category("ls058726648","Comedy"));
        movieCategories.add(new Category("ls009668711","Drama"));
        movieCategories.add(new Category("ls009609925","Adventure"));
        movieCategories.add(new Category("ls009668082","Sci-fi"));
        movieCategories.add(new Category("ls009668314","Thriller"));
        movieCategories.add(new Category("ls009389114","Family"));
        movieCategories.add(new Category("ls049309814","Horror"));
        movieCategories.add(new Category("ls009668031","Romance"));
        movieCategories.add(new Category("ls009674465","Sport"));
        movieCategories.add(new Category("ls009668766","Biography"));
        movieCategories.add(new Category("ls009669258","Fantasy"));
        movieCategories.add(new Category("ls079181605","Documentary"));
        movieCategories.add(new Category("ls009668055","History"));
        movieCategories.add(new Category("ls049110047","Musical"));
    }

    public List<Category> getMovieCategories() {
        return movieCategories;
    }

    public Category searchMovieCategories(String category){
        Category r = new Category("noID", "ERROR");
        for(int i = 0; i < movieCategories.size(); i++){
            if(category.equals(movieCategories.get(i).getName())){
                r = movieCategories.get(i);
            }
        }
        return r;
    }
}
