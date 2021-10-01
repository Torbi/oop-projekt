package com.example.testmaddafakka.Model;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testmaddafakka.SingletonRequestQueue;
import com.example.testmaddafakka.View.Listener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;


/**
 * An adapter that sends requests to imdbs api and get json responses back and
 * turns them into movie-objects
 */

public class IMDbApiAdapter implements IAdapter {

    private final String urlString = "https://imdb-api.com/en/API/";
    private final String key = "/k_ymbjcvxu";
    private List<Movie> movieList;
    private int numberOfRequests;
    private Listener listener;
    private Context context;

    public IMDbApiAdapter(Context context, Listener listener) {
        this.context = context;
        this.listener = listener;
    }

    //numberOfRequests is probably not needed
    private List<Movie> getStringRequest(String stringRequest) {
        //movieList.clear();
        numberOfRequests = 0;
        VolleyLog.DEBUG = true;
        RequestQueue queue = SingletonRequestQueue.getInstance(context).getRequestQueue();

        //parse response into gsons jsonobject and then turn them into movies
        JsonObjectRequest request = new JsonObjectRequest(urlString + stringRequest + key, null, response -> {
            movieList = new LinkedList<>();
            //VolleyLog.wtf(response.toString(), "utf-8");
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
            JsonArray array = jsonObject.getAsJsonArray("items");
            for(int i = 0; i < array.size(); i++) {
                Movie movie = jsonObject2Movie((JsonObject) array.get(i));
                //System.out.println(movie.getTitle() + " title");
                movieList.add(movie);
            }
            listener.notifyListeners(movieList);
            ++numberOfRequests;

        }, errorListener) {

            @Override
            public Priority getPriority() {
                return Priority.HIGH;
            }
        };

        queue.add(request);

        if(numberOfRequests == 1) {
            return movieList;
        }
        return null;
    }

    //errorListener to use when making a request with Volley
    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            if(error instanceof NetworkError) {
                Toast.makeText(context, "No network available", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
            }
        }
    };

    /**
     * Sends a request to imdbs api and gets a json response that is then translated into
     * a list of movies
     * @return a list of movies, might not return anything due to async bs
     */
    @Override
    public List<Movie> get250Movies() {
        return getStringRequest("Top250Movies");
    }

    /**
     * This would be pretty easy to implement, just call getMovies with the id
     * just need to check that the format in the request is right
     * @param listID - an imdb id for a list, starts with ls
     * @return a list of movies
     */
    @Override
    public List<Movie> getList(String listID) {
        return null;
    }

    private Movie jsonObject2Movie(JsonObject object) {
        try {
            Movie movie = new Movie(object.get("title").toString(),
                                    object.get("id").toString(),
                                    object.get("imDbRating").toString(),
                                    object.get("crew").toString(),
                                    object.get("image").toString()

            );
            return movie;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
