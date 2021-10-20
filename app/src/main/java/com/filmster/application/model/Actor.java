package com.filmster.application.model;

public class Actor implements ICategory {
    private String listID;
    private String name;

    public Actor(String listID, String name) {
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
