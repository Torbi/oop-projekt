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

    /**
     * Notifies all listeners that are subcribed to this object
     * @param media - A list of IMedia objects that are sent to all listeners on update
     */
    public void notifyListeners(List<IMedia> media) {
        for(int i = 0; i < listeners.size(); i++) {
            listeners.get(i).update(media);
        }
    }

    /**
     * Adds a listener to the list of listeners to update
     * @param listener - A specific IApiListener
     */
    public void addListener(IApiListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes a listener from the list of listeners
     * @param listener - A specific IApiListener
     */
    public void removeListener(IApiListener listener) {
        listeners.remove(listener);
    }
}
