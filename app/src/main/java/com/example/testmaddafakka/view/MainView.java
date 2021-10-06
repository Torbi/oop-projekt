package com.example.testmaddafakka.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

//import com.bumptech.glide.Glide;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.R;
import com.example.testmaddafakka.api.SingletonRequestQueue;
import com.example.testmaddafakka.viewmodel.MainViewModel;

public class MainView extends Fragment {

    public View view;
    public Button test;
    private MainViewModel viewModel;
    private IMedia currentMedia;
    private WatchlistView watchlistView;
    private PreferencesView preferencesView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_start_page, container, false);


        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.init(requireContext());
        viewModel.getMedia().observe(getViewLifecycleOwner(), media -> {
            currentMedia = media;
            updateMediaDisplayed(currentMedia);
        });

        watchlistView = new WatchlistView();
        preferencesView = new PreferencesView();

        Button watchlistBtn = view.findViewById(R.id.watchlist);
        Button preferencesBtn = view.findViewById(R.id.preferences);
        ImageView likeBtn = view.findViewById(R.id.like);
        ImageView dislikeBtn = view.findViewById(R.id.dislike);
        ImageView watchedBtn = view.findViewById(R.id.watched);

        likeBtn.setOnClickListener(view -> {
            System.out.println("Like");
            viewModel.addLikedMedia(currentMedia);
            System.out.println("MAIN VIEW " + currentMedia);
            viewModel.nextMedia();
        });

        dislikeBtn.setOnClickListener(view -> {
            System.out.println("Dislike");
            viewModel.addDislikedMedia(currentMedia);
            viewModel.nextMedia();

        });
        watchedBtn.setOnClickListener(view -> {
            System.out.println("Watched");
            viewModel.addWatchedMedia(currentMedia);
            viewModel.nextMedia();
        });

        watchlistBtn.setOnClickListener(view -> {
            FragmentTransaction fr = getFragmentManager().beginTransaction();
            fr.replace(R.id.fragmentContainer, watchlistView);
            fr.addToBackStack(null);
            fr.commit();
        });
        preferencesBtn.setOnClickListener(view -> {
            FragmentTransaction fr = getFragmentManager().beginTransaction();
            fr.replace(R.id.fragmentContainer, preferencesView);
            fr.addToBackStack(null);
            fr.commit();
        });
        return view;
    }

    private void updateMediaDisplayed(IMedia media) {
        ImageLoader imageLoader = SingletonRequestQueue.getInstance(getContext()).getImageLoader();
        String url = media.getImage();
        url = url.substring(1,url.length()-1);

        NetworkImageView niv = view.findViewById(R.id.mediaImage);
        if(url.length() > 0)
            niv.setImageUrl(url, imageLoader);

        TextView mediaTitle = view.findViewById(R.id.mediaTitle);
        TextView mediaRating = view.findViewById(R.id.mediaRating);
        TextView mediaYear = view.findViewById(R.id.mediaYear);

        String title = shorten(media.getTitle());
        mediaTitle.setText(checkMovieLength(title));

        String grade = shorten(media.getRating()) + "/10";
        mediaRating.setText(grade);
        mediaYear.setText(shorten(media.getYear()));

    }
    private String shorten(String text){
        String temp = text.substring(1, text.length()-1);
        return temp;
    }
    private String checkMovieLength(String title){
        if(title.length() > 15){
            return title.substring(0, 16) + "...";
        }
        return title;
    }

}
