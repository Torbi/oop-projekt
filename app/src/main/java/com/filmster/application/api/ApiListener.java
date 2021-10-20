package com.filmster.application.api;

import com.filmster.application.model.IMedia;

import java.util.LinkedList;
import java.util.List;

/**
 * Listener for objects that want to listen on the apiAdapter
 * @author Torbjörn
 */
public class ApiListener {
    private final List<IApiListener> listeners;

    public ApiListener() {
        listeners = new LinkedList<>();
    }

    public void notifyListeners(List<IMedia> media) {
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).update(media);
        }
    }

    public void addListener(IApiListener listener) {
        listeners.add(listener);
    }

    public void removeListener(IApiListener listener) {
        listeners.remove(listener);
    }
}
