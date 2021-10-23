package com.filmster.application.api;

import com.filmster.application.model.Actor;
import com.filmster.application.model.IMedia;
import com.google.gson.JsonObject;

/**
 * The strategy for creating mediaobjects of type actor
 * Creates actors
 * @author Albin Sundström
 */
public class SearchResultFactory implements IMediaFactory{
    /**
     * Creates an IMedia object from JsonObject, if the JsonObject doesn't contain
     * the proper JSonElements, it can fail
     * @param object - A JsonObject
     * @return - An IMedia Object
     */
    @Override
    public IMedia createMediaObjectFromJson(JsonObject object) {
        try {
            return new Actor(shorten(object.get("id").toString()),
                    shorten(object.get("title").toString()),
                    shorten(object.get("image").toString())
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String shorten(String text){
        return text.substring(1, text.length()-1);
    }

}
