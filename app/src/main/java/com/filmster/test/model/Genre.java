package com.filmster.test.model;

/**
 *
 * @author Albin Sundström
 */
public class Genre implements ICategory {
    private String listID;
    private String name;

    /**
     * A genre-object holds information about a genre, and mainly connects the name of a genre to
     * an ID of a list in an API
     * @param listID - The ID of the list in the API
     * @param name - The name of the category in the application, this is displayed to the use
     */
    public Genre(String listID, String name) {
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
