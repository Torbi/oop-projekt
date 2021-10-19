package com.example.testmaddafakka.model;

/**
 * Interface for movies for a more scalable app
 */

public interface IMedia {
    String getImage();
    String getName();
    String getYear();
    String getRating();
    String getID();
}
