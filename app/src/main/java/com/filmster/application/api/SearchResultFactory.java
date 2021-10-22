package com.filmster.application.api;

import com.filmster.application.model.Actor;
import com.filmster.application.model.IMedia;
import com.google.gson.JsonObject;

public class SearchResultFactory implements IMediaFactory{
    @Override
    public IMedia createMediaObjectFromJson(JsonObject object) {
        try {
            return new Actor(shorten(object.get("name").toString()),
                    shorten(object.get("id").toString()),
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
