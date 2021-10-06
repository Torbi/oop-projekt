package com.example.testmaddafakka.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.testmaddafakka.R;
import com.example.testmaddafakka.api.SingletonRequestQueue;
import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.viewmodel.WatchlistViewModel;

import java.util.List;

public class DislikedMedias extends Fragment {
    private WatchlistViewModel viewModel;
    private NetworkImageView smallMediaImage;
    private TextView mediaTitle;
    private TextView mediaYear;
    private TextView mediaRating;

    public DislikedMedias(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_missed_movies, container, false);
        smallMediaImage = view.findViewById(R.id.mediaImageSmall);
        mediaTitle = view.findViewById(R.id.mediaTitleSmall);
        mediaYear = view.findViewById(R.id.mediaYearSmall);
        mediaRating = view.findViewById(R.id.mediaRatingSmall);


        viewModel = new ViewModelProvider(this).get(WatchlistViewModel.class);
        viewModel.init(requireContext());
        viewModel.getDislikedMedias().observe(getViewLifecycleOwner(), medias -> {
            for(IMedia media : medias){
                //recycler view new component
                updateMediaDisplayed(media);
            }
        });

        return view;
    }
    private void updateMediaDisplayed(IMedia media) {
        ImageLoader imageLoader = SingletonRequestQueue.getInstance(getContext()).getImageLoader();
        String url = media.getImage();
        url = url.substring(1,url.length()-1);

        if(url.length() > 0)
            smallMediaImage.setImageUrl(url, imageLoader);

        String title = shorten(media.getTitle());
        mediaTitle.setText(checkMovieLength(title));

        String grade = shorten(media.getRating()) + "/10";
        mediaRating.setText(grade);
        mediaYear.setText(shorten(media.getYear()));

    }

    private String shorten(String text) {
        String temp = text.substring(1, text.length() - 1);
        return temp;
    }

    private String checkMovieLength(String title) {
        if (title.length() > 13) {
            return title.substring(0, 14) + "...";
        }
        return title;
    }
}