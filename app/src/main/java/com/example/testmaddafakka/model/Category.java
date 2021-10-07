package com.example.testmaddafakka.model;

public class Category {

    private String listID;
    private String name;

    public Category(String listID, String name) {
        this.listID = listID;
        this.name = name;
    }

    public String getListID() {
        return listID;
    }

    public String getName() {
        return name;
    }

}
