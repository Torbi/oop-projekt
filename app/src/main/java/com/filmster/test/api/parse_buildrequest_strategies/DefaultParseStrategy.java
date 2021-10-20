package com.filmster.test.api.parse_buildrequest_strategies;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Default strategy for parsing JSONObject responses
 * Presumes JSONObject contains a JSONArray called items
 * @author Torbjörn
 */
public class DefaultParseStrategy implements IParseStrategy {

    private String memberName;

    public DefaultParseStrategy(){
        memberName = "items";
    }

    public DefaultParseStrategy(String memberName){
        this.memberName = memberName;
    }
    @Override
    public List<JsonObject> parseResponse(JSONObject response) {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
        JsonArray array = jsonObject.getAsJsonArray(memberName);

        List<JsonObject> jsonObjects = new ArrayList<>();
        for(int i = 0; i < array.size(); i++) {
            jsonObjects.add((JsonObject) array.get(i));
        }
        return jsonObjects;
    }
}
