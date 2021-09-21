package com.example.testmaddafakka;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

public class IMDbApiAdapter implements IAdapter {

    private URL url;
    private final String urlString = "https://imdb-api.com/en/API/";
    private HttpURLConnection connection;
    private final String key = "/k_ymbjcvxu";

    public IMDbApiAdapter() {}

    @Override
    public List<Movie> getMovies() {
        //sendRequest("Top250Movies");

        return getTop250Movies();
    }

    private List<Movie> getTop250Movies() {
        HttpURLConnection response = sendRequest("Top250Movies");
        List<Movie> list = new LinkedList<>();
        try {
            String re = readResponse(response);
            //konverteras ej till jsonobject, stringen är rätt
            //JSONObject json = new JSONObject(re);
            //JSONArray jsonArray = new JSONArray(json.getJSONArray("items"));
            JSONArray jsonArray = string2Json(re);
            for(int i = 0; i < 250; i++ ) {
                list.add(jsonObject2Movie(jsonArray.getJSONObject(i)));
            }
        }   catch (Exception e) {
            e.printStackTrace();
            System.out.println("error on parse data in jsonparser.java");
        }
        return list;
    }

    private JSONObject string2Json(String jsonString) {

        return new JsonParser.parse(jsonString).getAsJsonObject();
    }

    private HttpURLConnection sendRequest(String request)  {
        try {
            url = new URL(urlString + request + key);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            return connection;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String readResponse(HttpURLConnection response) {

        String jsonResponse = "";
        try {
            InputStream is = (InputStream) response.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.ISO_8859_1), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;

            while((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
                System.out.println(line);
            }
            is.close();
            jsonResponse = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResponse;
    }


    private Movie jsonObject2Movie(JSONObject object) {
        try {
            Movie movie = new Movie(object.getString("title"), object.getString("id")
                                    , object.getString("imDbRating")
                                    , object.getString("crew")
                                    , object.getString("image"));
            return movie;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
