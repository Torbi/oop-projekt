package com.example.testmaddafakka.api;

import com.example.testmaddafakka.model.Movie;

import java.util.LinkedList;
import java.util.List;

public class ApiListener {

    private List<IApiListener> listeners;

    public ApiListener() {
        listeners = new LinkedList<>();
    }

    public void notifyListeners(Movie movie) {
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).update(movie);
        }
    }

    public void addListener(IApiListener listener) {
        listeners.add(listener);
    }

    public void removeListener(IApiListener  listener) {
        listeners.remove(listener);
    }
}
