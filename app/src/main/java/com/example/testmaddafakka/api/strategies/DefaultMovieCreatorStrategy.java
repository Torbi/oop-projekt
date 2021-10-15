package com.example.testmaddafakka.api.strategies;

import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.model.Movie;
import com.google.gson.JsonObject;

/**
 * The default strategy for creating media objects.
 * Creates movies
 * @author Torbjörn
 */
public class DefaultMovieCreatorStrategy implements IMediaObjectCreateStrategy{
    @Override
    public IMedia createMediaObjectFromJson(JsonObject object) {
        try {
            return new Movie(object.get("title").toString(),
                    object.get("id").toString(),
                    object.get("imDbRating").toString(),
                    object.get("image").toString(),
                    object.get("year").toString()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
