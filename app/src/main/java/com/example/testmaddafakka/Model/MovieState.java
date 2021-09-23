package com.example.testmaddafakka.Model;

public enum MovieState {
    LIKED,
    DISLIKED,
    SEEN;
    private MovieState[] states = values();

    public MovieState[] getStates() {
        return states;
    }
}
