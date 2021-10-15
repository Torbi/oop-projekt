package com.example.testmaddafakka.api.strategies;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Strategy for parsing response from the IMDbList call
 * @author Torbjörn
 */
public class IMDbListParseStrategy implements IParseStrategy {

    @Override
    public List<JsonObject> parseResponse(JSONObject response) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        JsonObject object = gson.fromJson(response.toString(), JsonObject.class);
        JsonArray array = (JsonArray) object.get("items");
        List<JsonObject> objectList = new ArrayList<>();
        for(int i = 0; i < array.size(); i++) {
            objectList.add((JsonObject) array.get(i));
        }
        return objectList;
    }
}
