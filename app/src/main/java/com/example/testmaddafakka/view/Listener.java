package com.example.testmaddafakka.view;

import com.example.testmaddafakka.model.Movie;

import java.util.LinkedList;
import java.util.List;

public class Listener {

    private List<ViewListener> listeners;

    public Listener() {
        listeners = new LinkedList<>();
    }

    public void notifyListeners(List<Movie> movies) {
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).update(movies);
        }
    }

    public void addListener(ViewListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ViewListener listener) {
        listeners.remove(listener);
    }
}
