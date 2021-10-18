package com.example.testmaddafakka.api.strategies;

import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.model.Movie;
import com.google.gson.JsonObject;

/**
 * The default strategy for creating media objects.
 * Creates movies
 * @author Torbjörn
 */
public class MovieFactory implements IMediaFactory {
    @Override
    public IMedia createMediaObjectFromJson(JsonObject object) {
        try {
            return new Movie(shorten(object.get("title").toString()),
                    shorten(object.get("id").toString()),
                    Double.parseDouble(shorten(object.get("imDbRating").toString())),
                    shorten(object.get("image").toString()),
                    Integer.parseInt(shorten(object.get("year").toString()))
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
