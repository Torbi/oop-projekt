package com.example.testmaddafakka.model;

/**
 * This is a enum for the watchlist
 */

public enum MediaState {
    LIKED,
    DISLIKED,
    SEEN;
    private final MediaState[] states = values();

    public MediaState[] getStates() {
        return states;
    }
}
