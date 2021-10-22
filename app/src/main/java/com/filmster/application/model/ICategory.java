package com.filmster.application.model;

/**
 * Interface for categories for a more scalable app
 *
 * @author Albin Sundström, Torbjörn
 */

public interface ICategory {
    /**
     * The imdb id of a category, starts with ls
     * @return - A string representing an imdb list
     */
    String getID();

    /**
     * Returns the name of a category, i.e. drama, comedy, action
     * @return - A string with the name
     */
    String getName();
}
