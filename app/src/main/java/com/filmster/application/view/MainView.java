package com.filmster.application.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;


import com.filmster.application.R;
import com.filmster.application.model.IMedia;

import com.filmster.application.viewmodel.MainViewModel;

/**
 * This is the start_page view and displays the movies has
 * It has a button to preferences and watchlist.
 * And a like, dislike and watched button.
 * It also sets a onTouchListener to the MediaCard framgnet to handle swipes and clicks.
 */

public class MainView extends Fragment {
    private View view;
    private MainViewModel viewModel;
    private WatchlistView watchlistView;
    private PreferencesView preferencesView;
    private boolean backSide = false;
    private ProgressBar spinner;

    private MediaCardFrontView mediaFront;
    private MediaCardBackView mediaBack;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_start_page, container, false);

        initViews();
        initViewModel();
        viewModel.getMedia().observe(getViewLifecycleOwner(), this::updateMediaDisplayed);

        initComponents();

        getChildFragmentManager().beginTransaction()
        .replace(R.id.mediaCard, mediaFront).commit();

        initMediaCard();

        return view;
    }

    /**
     * Initializes all the view uses in this fragment.
     */
    private void initViews() {
        mediaFront = new MediaCardFrontView();
        mediaBack = new MediaCardBackView();
        watchlistView = new WatchlistView();
        preferencesView = new PreferencesView();

    }

    /**
     * Initializes all the actionListeners and components
     */
    private void initComponents() {
        initSpinner();
        initLikeBtn();
        initDislikedBtn();
        initWatchedBtn();
        initWatchlistBtn();
        initPreferencesBtn();

    }

    private void initSpinner() {
        spinner = (ProgressBar) view.findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);
    }
    private void initViewModel(){
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.init(requireContext());
    }

    private void initLikeBtn() {
        ImageView likeBtn = view.findViewById(R.id.like);
        likeBtn.setOnClickListener(view -> {
            viewModel.addLikedMedia();
            viewModel.nextMedia();
            setMediaFront();
            spinner.setVisibility(View.VISIBLE);
        });
    }

    private void initDislikedBtn() {
        ImageView dislikedBtn = view.findViewById(R.id.dislike);
        dislikedBtn.setOnClickListener(view -> {
            viewModel.addDislikedMedia();
            viewModel.nextMedia();
            setMediaFront();
            spinner.setVisibility(View.VISIBLE);
        });
    }

    private void initWatchedBtn() {
        ImageView watchedBtn = view.findViewById(R.id.watched);
        watchedBtn.setOnClickListener(view -> {
            viewModel.addWatchedMedia();
            viewModel.nextMedia();
            setMediaFront();
            spinner.setVisibility(View.VISIBLE);
        });
    }

    private void initWatchlistBtn() {
        Button watchlistBtn = view.findViewById(R.id.watchlist);
        watchlistBtn.setOnClickListener(view -> {
            FragmentTransaction fr = getFragmentManager().beginTransaction();
            fr.replace(R.id.fragmentInlogg, watchlistView);
            fr.addToBackStack(null);
            fr.commit();
        });
    }

    private void initPreferencesBtn() {
        Button preferencesBtn = view.findViewById(R.id.preferences);
        preferencesBtn.setOnClickListener(view -> {
            FragmentTransaction fr = getFragmentManager().beginTransaction();
            fr.replace(R.id.fragmentInlogg, preferencesView);
            fr.addToBackStack(null);
            fr.commit();
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initMediaCard() {
        FragmentContainerView mediaCard = view.findViewById(R.id.mediaCard);

        mediaCard.setOnTouchListener(new GestureHelper(getActivity()) {
            @Override
            public void onClick() {
                mediaFlip();
            }

            @Override
            public void onSwipeTop() {
                viewModel.addWatchedMedia();
                viewModel.nextMedia();
                setMediaFront();
                spinner.setVisibility(View.VISIBLE);
            }
            @Override
            public void onSwipeRight() {
                viewModel.addLikedMedia();
                viewModel.nextMedia();
                setMediaFront();
                spinner.setVisibility(View.VISIBLE);

            }
            @Override
            public void onSwipeLeft() {
                viewModel.addDislikedMedia();
                viewModel.nextMedia();
                setMediaFront();
                spinner.setVisibility(View.VISIBLE);
            }
        });
    }

    private void mediaFlip() {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.flip_out, R.animator.flip_in);

        if(backSide){
            setMediaFront();
        }else{
            setMediaBack();
        }
    }

    private void setMediaBack(){
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.flip_out, R.animator.flip_in);
        spinner.setVisibility(View.GONE);
        initBundle(viewModel.getCurrentMedia());
        ft.replace(R.id.mediaCard, mediaBack);
        ft.commit();

        backSide = true;
    }

    private void initBundle(IMedia media) {
        Bundle bundle = new Bundle();
        //check if media has an about or something similar, if not hardcode something
        bundle.putString("about", "A great movie to watch." + '\n' + "This movie can be found on the following networks: Netflix, HBO and The PirateBay");
        bundle.putString("year", Integer.toString(media.getYear()));
        bundle.putString("rating", Double.toString(media.getRating()));
        bundle.putString("name", media.getName());
        mediaBack.setArguments(bundle);
    }

    private void setMediaFront(){
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.flip_out, R.animator.flip_in);
        spinner.setVisibility(View.GONE);

        Bundle bundle = new Bundle();
        bundle.putString("movie", viewModel.getCurrentMedia().getImage());
        mediaFront.setArguments(bundle);
        ft.replace(R.id.mediaCard, mediaFront, "front");
        ft.commit();

        backSide = false;
    }

    @SuppressLint("SetTextI18n")
    private void updateMediaDisplayed(IMedia media) {
        TextView mediaTitle = view.findViewById(R.id.mediaTitle);
        TextView mediaRating = view.findViewById(R.id.mediaRating);
        TextView mediaYear = view.findViewById(R.id.mediaYear);

        String title = media.getName();
        mediaTitle.setText(title);

        String grade = media.getRating().toString() + "/10";
        mediaRating.setText(grade);
        mediaYear.setText(Integer.toString(media.getYear()));
        mediaFront.update(media.getImage());
    }
}