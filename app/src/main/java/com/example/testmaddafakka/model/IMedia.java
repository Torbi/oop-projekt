package com.example.testmaddafakka.model;

/**
 * Interface for movies for a more scalable app
 */

public interface IMedia {
    String getId();
    String getImage();
    String getTitle();
    String getYear();
    String getRating();

}
