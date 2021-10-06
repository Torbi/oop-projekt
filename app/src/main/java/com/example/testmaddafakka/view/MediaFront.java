package com.example.testmaddafakka.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.testmaddafakka.R;
import com.example.testmaddafakka.api.SingletonRequestQueue;
import com.example.testmaddafakka.model.IMedia;


public class MediaFront extends Fragment {

    private NetworkImageView mediaImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_media_front, container, false);

        mediaImage = view.findViewById(R.id.mediaImage);

        return view;
    }
    public void update(IMedia media){
        ImageLoader imageLoader = SingletonRequestQueue.getInstance(getContext()).getImageLoader();
        String url = media.getImage();
        url = url.substring(1,url.length()-1);

        if(url.length() > 0)
            mediaImage.setImageUrl(url, imageLoader);

    }
}