package com.example.testmaddafakka.model;

public enum MovieState {
    LIKED,
    DISLIKED,
    SEEN;
    private MovieState[] states = values();

    public MovieState[] getStates() {
        return states;
    }
}
