package com.example.testmaddafakka.api;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.model.Movie;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.LinkedList;
import java.util.List;


/**
 * An adapter that sends requests to imdbs api and get json responses back and
 * turns them into media-objects.
 * Can have different strategies for building requests and parsing responses
 */

public class IMDbApiAdapter implements IAdapter {

    private final Context context;
    private ApiListener listener;
    private IParseStrategy parseStrategy;
    private IBuildRequestStrategy buildRequestStrategy;


    public IMDbApiAdapter(Context context, ApiListener listener) {
        this.context = context;
        this.listener = listener;
        parseStrategy = new DefaultParseStrategy();
        buildRequestStrategy = new DefaultBuildRequestStrategy();
    }

    private void getStringRequest(String stringRequest, final VolleyCallback callback) {
        VolleyLog.DEBUG = true;
        RequestQueue queue = SingletonRequestQueue.getInstance(context).getRequestQueue();

        //parse response into gsons jsonobject and then turn them into medias
        JsonObjectRequest request = new JsonObjectRequest(buildRequestStrategy.buildRequest(stringRequest), null, response -> {
            List<IMedia> mediaList = new LinkedList<>();
            //VolleyLog.wtf(response.toString(), "utf-8");

            List<JsonObject> jsonObjects = parseStrategy.parseResponse(response);
            for(int i = 0; i < jsonObjects.size(); i++) {
                IMedia media = jsonObject2Media(jsonObjects.get(i));
                mediaList.add(media);
            }
            callback.onSuccess(mediaList);
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

    public void setParseStrategy(IParseStrategy strategy) {
        this.parseStrategy = strategy;
    }

    public void setBuildRequestStrategy(IBuildRequestStrategy strategy) {
        this.buildRequestStrategy = strategy;
    }

    /**
     * Sends a request to imdbs api and gets a json response that is then translated into
     * a list of movies
     * A callback method notifies all listeners about the new movies
     */
    @Override
    public void get250Movies() {
        getStringRequest("Top250Movies", new VolleyCallback() {
            @Override
            public void onSuccess(List<IMedia> mediaList) {
                listener.notifyListeners(mediaList);
            }
        });
    }

    /**
     * Need to make sure the correct strategies are chosen before calling this
     * with a random request. The request need to be built correctly according
     * @param request - a correct request to an api
     * @return a list of movies
     */
    @Override
    public void getList(String request) {
        getStringRequest(request, new VolleyCallback() {
            @Override
            public void onSuccess(List<IMedia> mediaList) {
                listener.notifyListeners(mediaList);
            }
        });
    }

    private IMedia jsonObject2Media(JsonObject object) {
        try {
            IMedia media = new Movie(object.get("title").toString(),
                                    object.get("id").toString(),
                                    object.get("imDbRating").toString(),
                                    object.get("crew").toString(),
                                    object.get("image").toString(),
                                    object.get("year").toString()
            );
            return media;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
