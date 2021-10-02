package com.example.testmaddafakka.api;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.NetworkError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.testmaddafakka.model.Movie;
import com.example.testmaddafakka.repository.FilmsterRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * An adapter that sends requests to imdbs api and get json responses back and
 * turns them into movie-objects
 */

public class IMDbApiAdapter implements IAdapter {

    private final String urlString = "https://imdb-api.com/en/API/";
    //made over a 100 requests on one day so created another account with a new key
    private final String key = "/k_ymbjcvxu";
    private final String key2 = "/k_e598lu33";
    private List<Movie> movieList;
    private Context context;
    private int currentMovie = 0;

    public IMDbApiAdapter(Context context) {
        this.context = context;
    }

    private void getStringRequest(String stringRequest, final VolleyCallback callback) {
        VolleyLog.DEBUG = true;
        RequestQueue queue = SingletonRequestQueue.getInstance(context).getRequestQueue();

        //parse response into gsons jsonobject and then turn them into movies
        JsonObjectRequest request = new JsonObjectRequest(urlString + stringRequest + key2, null, response -> {
            movieList = new LinkedList<>();
            //VolleyLog.wtf(response.toString(), "utf-8");
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
            JsonArray array = jsonObject.getAsJsonArray("items");
            for(int i = 0; i < array.size(); i++) {
                Movie movie = jsonObject2Movie((JsonObject) array.get(i));
                movieList.add(movie);
            }
            callback.onSuccess(movieList);
        }, errorListener) {

            @Override
            public Priority getPriority() {
                return Priority.HIGH;
            }
        };
        queue.add(request);
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
     * A callback method sets the movies in the filmsterRepo on Success
     */
    @Override
    public void get250Movies() {
        getStringRequest("Top250Movies", new VolleyCallback() {
            @Override
            public void onSuccess(List<Movie> movieList) {
                FilmsterRepository.getInstance(null).setMovies(movieList);
            }
        });
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
                                    object.get("image").toString(),
                                    object.get("year").toString()

            );
            return movie;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
