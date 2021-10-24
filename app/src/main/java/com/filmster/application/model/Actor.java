package com.filmster.application.model;

/**
 * An actor contains a name, an id and an image
 * @author Albin Sundström
 */
public class Actor implements IMedia {
    private String listID;
    private String name;
    private String image;

    /**
     * An actor object that contains information about an actor
     * @param listID - The id an actor has on IMDb, starts with nm
     * @param name - The name of an actor
     * @param image - A string for where the image can be found online
     */

    public Actor(String listID, String name, String image) {
        this.listID = listID;
        this.name = name;
        this.image = image;
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
