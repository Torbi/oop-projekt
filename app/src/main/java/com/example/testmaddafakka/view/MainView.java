package com.example.testmaddafakka.view;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.R;
import com.example.testmaddafakka.model.OnSwipeTouchListener;
import com.example.testmaddafakka.viewmodel.MainViewModel;

public class MainView extends Fragment {

    public View view;
    public Button test;
    private MainViewModel viewModel;
    private IMedia currentMedia;
    private WatchlistView watchlistView;
    private PreferencesView preferencesView;
    private boolean backSide = false;

    private MediaFront mediaFront;
    private MediaBack mediaBack;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_start_page, container, false);

        mediaFront = new MediaFront();
        mediaBack = new MediaBack();

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

        FragmentContainerView mediaCard = view.findViewById(R.id.mediaCard);


        likeBtn.setOnClickListener(view -> {
            viewModel.addLikedMedia(currentMedia);
            viewModel.nextMedia();
        });

        dislikeBtn.setOnClickListener(view -> {
            viewModel.addDislikedMedia(currentMedia);
            viewModel.nextMedia();

        });
        watchedBtn.setOnClickListener(view -> {
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

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.replace(R.id.mediaCard, mediaFront).commit();

        mediaCard.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {
            /*
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                System.out.println("REERERERERE");
                //mediaFlip(currentMedia);
                if(view.performClick()){
                    mediaFlip(currentMedia);
                    return true;
                }
                return false;
            }
             */

            @Override
            public void onSwipeTop() {
                viewModel.addWatchedMedia(currentMedia);
                viewModel.nextMedia();
            }
            @Override
            public void onSwipeRight() {
                viewModel.addLikedMedia(currentMedia);
                viewModel.nextMedia();
            }
            @Override
            public void onSwipeLeft() {
                viewModel.addDislikedMedia(currentMedia);
                viewModel.nextMedia();
            }
        });
        return view;
    }

    public void mediaFlip(IMedia media) {

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();

        ft.setCustomAnimations(R.animator.flip_out, R.animator.flip_in);

        if (backSide) {
            ft.replace(R.id.mediaCard, mediaFront);
            ft.commit();
            backSide = false;
            mediaFront.update(media);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("data", media.getTitle() + "@" + media.getRating() + "@" + media.getYear());
            mediaBack.setArguments(bundle);
            ft.replace(R.id.mediaCard, mediaBack);
            ft.commit();
            backSide = true;
            System.out.println("Borde vara skapat nu");
            //mediaBack.update(media);
        }

    }

    private void updateMediaDisplayed(IMedia media) {

        mediaFront.update(media);

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
