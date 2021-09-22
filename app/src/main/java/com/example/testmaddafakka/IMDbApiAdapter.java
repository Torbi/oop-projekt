package com.example.testmaddafakka;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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

    /**
     * Sends a request to imdbs api and gets a json response that is then translated into
     * a list of movies
     * @return a list of movies
     */
    @Override
    public List<Movie> getMovies() {
        return getTop250Movies();
    }

    private List<Movie> getTop250Movies() {
        HttpURLConnection response = sendRequest("Top250Movies");
        List<Movie> list = new LinkedList<>();
        try {
            String re = readResponse(response);
            JsonArray jsonArray = string2Json(re);
            for(int i = 0; i < 250; i++ ) {
                list.add(jsonObject2Movie((JsonObject) jsonArray.get(i)));
            }
        }   catch (Exception e) {
            e.printStackTrace();
            System.out.println("error on parse data in jsonparser.java");
        }
        return list;
    }

    private JsonArray string2Json(String jsonString) {
        Gson gson = new Gson();
        JsonObject object = gson.fromJson(jsonString, JsonObject.class);
        return object.getAsJsonArray("items");
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
                //System.out.println(line);
            }
            is.close();
            jsonResponse = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResponse;
    }


    private Movie jsonObject2Movie(JsonObject object) {
        try {
            Movie movie = new Movie(object.get("title").toString(),
                                    object.get("id").toString(),
                                    object.get("imDbRating").toString(),
                                    object.get("crew").toString(),
                                    object.get("image").toString()
            );
            System.out.println(movie.getTitle() + " title");
            return movie;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
