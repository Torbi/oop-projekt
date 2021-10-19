package com.example.testmaddafakka.api.strategies;

import com.example.testmaddafakka.model.Actor;
import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.model.Movie;
import com.google.gson.JsonObject;

public class SearchResultCreatorStrategy implements IMediaObjectCreateStrategy{

    @Override
    public IMedia createMediaObjectFromJson(JsonObject object) {
        try {
            return new Actor(object.get("id").toString(),
                    object.get("title").toString(),
                    object.get("image").toString()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
