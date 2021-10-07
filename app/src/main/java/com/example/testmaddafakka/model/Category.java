package com.example.testmaddafakka.model;

public class Category implements ICategory{

    private String listID;
    private String name;

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
