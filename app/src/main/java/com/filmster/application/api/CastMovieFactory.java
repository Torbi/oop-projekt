package com.filmster.application.api;

import com.filmster.application.model.IMedia;
import com.filmster.application.model.Movie;
import com.google.gson.JsonObject;

/**
 * The strategy for creating mediaobjects containing only title and id
 * Creates movies
 * @author Albin Sundström
 */
public class CastMovieFactory implements IMediaFactory{

    /**
     * Creates an IMedia object from JsonObject, if the JsonObject doesn't contain
     * the proper JSonElements, it can fail
     * @param object - A JsonObject
     * @return - An IMedia Object
     */
    public IMedia createMediaObjectFromJson(JsonObject object) {
        try {
            return new Movie(object.get("title").toString(),
                    object.get("id").toString()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
