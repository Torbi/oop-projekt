package com.example.testmaddafakka.Model;

public class Category {

    private String listID;
    private String name;
    private String keyWord;


    public Category(String listID, String name, String keyWord) {
        this.listID = listID;
        this.name = name;
        this.keyWord = keyWord;
    }


    public String getListID() {
        return listID;
    }

    public String getName() {
        return name;
    }

    public String getKeyWord() {
        return keyWord;
    }
}
