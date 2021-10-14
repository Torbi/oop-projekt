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
import com.example.testmaddafakka.api.strategies.DefaultBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.DefaultParseStrategy;
import com.example.testmaddafakka.api.strategies.IBuildRequestStrategy;
import com.example.testmaddafakka.api.strategies.IParseStrategy;
import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.model.Movie;
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

public class ApiAdapter implements IAdapter {

    private final Context context;
    private ApiListener listener;
    private IParseStrategy parseStrategy;
    private IBuildRequestStrategy buildRequestStrategy;


    public ApiAdapter(Context context, ApiListener listener) {
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
            public void retry(VolleyError error) throws VolleyError {

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

    /**
     * Need to make sure the correct strategies are chosen before calling this
     * with a random request. The request need to be built correctly according
     * to IMDb or whatever api you use
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
