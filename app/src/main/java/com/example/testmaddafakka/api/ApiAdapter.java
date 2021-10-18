package com.example.testmaddafakka.api;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.testmaddafakka.api.parse_buildrequest_strategies.DefaultBuildRequestStrategy;
import com.example.testmaddafakka.api.parse_buildrequest_strategies.DefaultParseStrategy;
import com.example.testmaddafakka.api.parse_buildrequest_strategies.IBuildRequestStrategy;
import com.example.testmaddafakka.api.parse_buildrequest_strategies.IParseStrategy;
import com.example.testmaddafakka.model.IMedia;
import com.google.gson.JsonObject;

import java.util.LinkedList;
import java.util.List;


/**
 * An adapter that sends requests to imdbs api and get json responses back and
 * turns them into media-objects.
 * Can have different strategies for building requests and parsing responses
 * Has default strategies for handling the default call.
 * Apparently takes between 7-16 seconds to get a response using the IMDbList call
 * @author Torbjorn
 */

public class ApiAdapter implements IApiAdapter {

    private final Context context;
    private ApiListener listener;
    private IParseStrategy parseStrategy;
    private IBuildRequestStrategy buildRequestStrategy;
    private IMediaFactory mediaFactory;

    /**
     * Constructor for the ApiAdapter
     * @param context The application context
     * @param listener a listener that holds all objects that wants to be updated by the results
     */
    public ApiAdapter(Context context, ApiListener listener) {
        this.context = context;
        this.listener = listener;

        parseStrategy = new DefaultParseStrategy();
        buildRequestStrategy = new DefaultBuildRequestStrategy();
        mediaFactory = new MovieFactory();
    }

    /**
     * Uses a buildRequestStrategy to create a request to an api
     * Uses a parseStrategy to parse a specific response
     * Uses a mediaObjectCreateStrategy to create different media objects
     * The result is forwarded to the callback function where a listener updates all objects that are subcribed to it
     * @param stringRequest - A specific string that is built into the request using strategy
     * @param callback - A callback function that gets the result and does something with it
     */
    private void makeJsonRequest(String stringRequest, final VolleyCallback callback) {
        VolleyLog.DEBUG = true;
        RequestQueue queue = SingletonRequestQueue.getInstance(context).getRequestQueue();

        //parse response into gsons jsonobject and then turn them into medias
        JsonObjectRequest request = new JsonObjectRequest(buildRequestStrategy.buildRequest(stringRequest), null, response -> {
            List<IMedia> mediaList = new LinkedList<>();
            VolleyLog.wtf(response.toString(), "utf-8");

            List<JsonObject> jsonObjects = parseStrategy.parseResponse(response);
            for(int i = 0; i < jsonObjects.size(); i++) {
                IMedia media = mediaFactory.createMediaObjectFromJson(jsonObjects.get(i));
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

        //Can take up to 16 seconds to get a list from imdb
        //using the IMDbList call
        request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) {

            }
        });
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

    @Override
    public void setParseStrategy(IParseStrategy strategy) {
        this.parseStrategy = strategy;
    }

    @Override
    public void setBuildRequestStrategy(IBuildRequestStrategy strategy) {
        this.buildRequestStrategy = strategy;
    }

    @Override
    public void setMediaFactory(IMediaFactory factory) {
        this.mediaFactory = factory;
    }

    /**
     * Need to make sure the correct strategies are chosen before calling this
     * with a random request. The request need to be built correctly according
     * to IMDb or whatever api you use
     * @param request - a correct request to an api
     * @return a list of movies
     */
    @Override
    public void loadResponse(String request) {
        makeJsonRequest(request, new VolleyCallback() {
            @Override
            public void onSuccess(List<IMedia> mediaList) {
                listener.notifyListeners(mediaList);
            }
        });
    }

    public void getSearchResults(String request){
        //getStringRequest(request);
    }

}
