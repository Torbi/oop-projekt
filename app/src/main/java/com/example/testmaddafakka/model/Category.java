package com.example.testmaddafakka.model;


public class Category implements IPreferences {


    private String listID;
    private String name;

    /**
     * A category-object holds information about a category, and mainly connects the name of a category to
     * an ID of a list in an API
     * @param listID - The ID of the list in the API
     * @param name - The name of the category in the application, this is displayed to the use
     */

    public Category(String listID, String name) {
        this.listID = listID;
        this.name = name;
    }

    public String getID() {
        return listID;
    }
    public String getName() {
        return name;
    }

}
