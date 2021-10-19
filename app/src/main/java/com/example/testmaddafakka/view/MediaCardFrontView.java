package com.example.testmaddafakka.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.testmaddafakka.R;
import com.example.testmaddafakka.api.SingletonRequestQueue;
import com.example.testmaddafakka.model.IMedia;


public class MediaCardFrontView extends Fragment {

    private NetworkImageView mediaImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_media_front, container, false);

        mediaImage = view.findViewById(R.id.mediaImage);
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            String data = bundle.getString("movie");
            update(data);
        }
        return view;
    }

    public void update(String media){
        ImageLoader imageLoader = SingletonRequestQueue.getInstance(getContext()).getImageLoader();
        if(media.length() > 0) {
            mediaImage.setImageUrl(media, imageLoader);
        }
    }
}