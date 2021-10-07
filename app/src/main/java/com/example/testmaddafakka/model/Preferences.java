package com.example.testmaddafakka.model;

import java.util.ArrayList;
import java.util.List;

public class Preferences {

    private List<Category> categories = new ArrayList<>();
    private User user;

    public Preferences(){
        //this.user = user; // borde nog ha en user här
        addCategories();
    }

    private void addCategories(){
        categories.add(new Category("noID","Genre"));
        categories.add(new Category("ls051091770","Action"));
        categories.add(new Category("ls058726648","Comedy"));
        categories.add(new Category("ls009668711","Drama"));
        categories.add(new Category("ls009609925","Adventure"));
        categories.add(new Category("ls009668082","Sci-fi"));
        categories.add(new Category("ls009668314","Thriller"));
        categories.add(new Category("ls009389114","Family"));
        categories.add(new Category("ls049309814","Horror"));
        categories.add(new Category("ls009668031","Romance"));
        categories.add(new Category("ls009674465","Sport"));
        categories.add(new Category("ls009668766","Biography"));
        categories.add(new Category("ls009669258","Fantasy"));
        categories.add(new Category("ls079181605","Documentary"));
        categories.add(new Category("ls009668055","History"));
        categories.add(new Category("ls049110047","Musical"));
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Category searchCategory(String category){
        Category r = new Category("noID", "ERROR");
        for(int i = 0; i < categories.size(); i++){
            if(category.equals(categories.get(i).getName())){
                r = categories.get(i);
            }
        }
        return r;
    }
}
