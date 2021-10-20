package com.filmster.test.model;

/**
 * Interface for movies for a more scalable app
 *
 */

public interface IMedia {

    /**
     * Return the url to the objects image
     * @return - A string with a url to an image
     */
    String getImage();

    /**
     * Returns the name of the object
     * @return - A string with the name of the object
     */
    String getName();

    /**
     * Returns the year the object was created
     * @return - An int with the year it was created
     */
    int getYear();

    /**
     * Returns the rating it has
     * @return - A double with the rating
     */
    Double getRating();

    /**
     * Returns the MediaState of a media object
     * @return - An enum MediaState, SEEN, LIKED or DISLIKED
     */
    MediaState getState();

    /**
     * Sets the MediaState of a media object
     * @param state - An enum MediaState, SEEN, LIKED or DISLIKED
     */
    void setState(MediaState state);

}
