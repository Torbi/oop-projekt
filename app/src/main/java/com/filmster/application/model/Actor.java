package com.filmster.application.model;

public class Actor implements IMedia {
    private String listID;
    private String name;
    private String image;
    private String birthYear;

    public Actor(String listID, String name, String image) {
        this.listID = listID;
        this.name = name;
        this.image = image;
        //this.birthYear = birthYear;
    }

    @Override
    public String getID() {
        return listID;
    }

    @Override
    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    @Override
    public int getYear() {
        return 0;
    }
    @Override
    public Double getRating() {
        return null;
    }

    @Override
    public MediaState getState() {
        return null;
    }

    @Override
    public void setState(MediaState state) {

    }
}
