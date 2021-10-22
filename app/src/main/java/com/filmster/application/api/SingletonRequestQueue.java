package com.filmster.application.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Singleton for volleys requestqueue
 * Also instantiates the imageloader which can save images in the cache
 * Code taken from volleys guide
 * @author Torbjörn
 */
public class SingletonRequestQueue {
    private static SingletonRequestQueue instance;
    private static Context context;
    private RequestQueue requestQueue;
    private final ImageLoader imageLoader;

    private SingletonRequestQueue(Context ctx) {
        context = ctx;
        requestQueue = getRequestQueue();

        imageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    /**
     * Returns an instance of SingletionRequestQueue, if no exists, creates one
     * The method is synchronized to only let one thread at a time access it
     * @param context - The application context
     * @return - A SingletonRequestQueue
     */
    public static synchronized SingletonRequestQueue getInstance(Context context) {
        if (instance == null) {
            instance = new SingletonRequestQueue(context);
        }
        return instance;
    }

    /**
     * Getter for volleys RequestQueue
     * @return A RequestQueue
     */
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }
        return requestQueue;
    }

    /**
     * Getter for volleys ImageLoader
     * @return - An ImageLoader
     */
    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
