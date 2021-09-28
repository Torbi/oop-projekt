package com.example.testmaddafakka;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.example.testmaddafakka.Model.Movie;
import com.example.testmaddafakka.View.Listener;
import com.example.testmaddafakka.View.MainView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

/**
 * All kod som ligger här ska bort härifrån i princip
 */
public class MainActivity extends AppCompatActivity {

    private List<Movie> movieList;
    private String url = "https://imdb-api.com/en/API/";
    private String key = "/k_ymbjcvxu";
    private String testUrl = url + "Top250Movies" + key;
    private RequestQueue requestQueue;
    private int numberOfRequests;
    private Listener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainView mainView = new MainView();
        //Test github
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer, mainView);
        fragmentTransaction.commit();
        getStringRequest("Top250Movies");
    }

    //errorListener to use when making a request with Volley
    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            if(error instanceof NetworkError) {
                Toast.makeText(getApplicationContext(), "No network available", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }
    };

    public List<Movie> getMovieList() {
        return movieList;
    }

    //numberOfRequests is probably not needed
    private void getStringRequest(String stringRequest) {
        //movieList.clear();
        numberOfRequests = 0;
        VolleyLog.DEBUG = true;
        RequestQueue queue = SingletonRequestQueue.getInstance(getApplicationContext()).getRequestQueue();

        //parse response into gsons jsonobject and then turn them into movies
        JsonObjectRequest request = new JsonObjectRequest(url + stringRequest + key, null, response -> {
            movieList = new LinkedList<>();
            VolleyLog.wtf(response.toString(), "utf-8");
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            JsonObject jsonObject = gson.fromJson(response.toString(), JsonObject.class);
            JsonArray array = jsonObject.getAsJsonArray("items");
            for(int i = 0; i < array.size(); i++) {
                Movie movie = jsonObject2Movie((JsonObject) array.get(i));
                System.out.println(movie.getTitle() + " title");
                movieList.add(movie);
            }
            //listener.notifyListeners(movieList);
            ++numberOfRequests;

        }, errorListener) {

            @Override
            public Priority getPriority() {
                return Priority.HIGH;
            }
        };

        queue.add(request);

        /*
        queue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {
            @Override
            public  void onRequestFinished(Request<Object> request1) {
                try {
                    if(request1.getCacheEntry() != null) {
                        String cacheValue = new String(request1.getCacheEntry().data, "UTF-8");
                        VolleyLog.d(request1.getCacheKey() + " " + cacheValue);
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
         */
    }

    private Movie jsonObject2Movie(JsonObject object) {
        try {
            Movie movie = new Movie(
                    object.get("title").toString(),
                    object.get("id").toString(),
                    object.get("imDbRating").toString(),
                    object.get("crew").toString(),
                    object.get("image").toString()
            );
            System.out.println(movie.getImage() + " title");
            return movie;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}