package com.filmster.application.api;

import com.filmster.application.model.IMedia;
import com.filmster.application.model.Movie;
import com.google.gson.JsonObject;

/**
 * The default strategy for creating media objects.
 * Creates movies
 * @author Torbjörn
 */
public class MovieFactory implements IMediaFactory {

    /**
     * Creates an IMedia object from JsonObject, if the JsonObject doesn't contain
     * the proper JSonElements, it can fail
     * @param object - A JsonObject
     * @return - An IMedia Object
     */
    @Override
    public IMedia createMediaObjectFromJson(JsonObject object) {
        //need to have try/catch in case the JsonObject doesn't contain the element you try to get
        try {
            return new Movie(shorten(object.get("title").toString()),
                    shorten(object.get("id").toString()),
                    Double.parseDouble(shorten(object.get("imDbRating").toString())),
                    shorten(object.get("image").toString()),
                    Integer.parseInt(shorten(object.get("year").toString()))
            );
        } catch (Exception e) {
            System.out.println("KOMMER HIT");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * In the response all JsonElements are surrounded with "", i.e. "response", so they need to be removed
     * @param text
     * @return
     */
    private String shorten(String text){
        return text.substring(1, text.length()-1);
    }
}
