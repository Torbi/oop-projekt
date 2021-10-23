package com.filmster.application.api;

import com.filmster.application.model.Actor;
import com.filmster.application.model.IMedia;
import com.google.gson.JsonObject;

//id name image
public class SearchResultFactory implements IMediaFactory{

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
