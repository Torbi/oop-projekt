package com.filmster.application.api.parse_buildrequest_strategies;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Default strategy for parsing JSONObject responses
 * Standard responses contain an array called items
 * NonStandard responses can have arrays with other names
 * but still be parsed the same
 * @author Torbjörn
 */
public class DefaultParseStrategy implements IParseStrategy {
    private final String memberName;

    /**
     * Used for creating DefaultParseStrategies for standard responses with array items
     */
    public DefaultParseStrategy(){
        memberName = "items";
    }

    /**
     * Used for creating DefaultParseStrategies for nonstandard responses with different array names
     * @param memberName - The name of the JsonArray in the response
     */
    public DefaultParseStrategy(String memberName){
        this.memberName = memberName;
    }

    @Override
    public List<JsonObject> parseResponse(JSONObject response) {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
        JsonArray array = jsonObject.getAsJsonArray(memberName);
        System.out.println(memberName);

        List<JsonObject> jsonObjects = new ArrayList<>();
        for(int i = 0; i < array.size(); i++) {
            jsonObjects.add((JsonObject) array.get(i));
        }
        return jsonObjects;
    }
}
