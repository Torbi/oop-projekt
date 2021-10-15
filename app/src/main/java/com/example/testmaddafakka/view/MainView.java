package com.example.testmaddafakka.view;

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

import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.R;
import com.example.testmaddafakka.viewmodel.MainViewModel;

public class MainView extends Fragment {
    private View view;
    private MainViewModel viewModel;
    private WatchlistView watchlistView;
    private PreferencesView preferencesView;
    private boolean backSide = false;
    private ProgressBar spinner;

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
        viewModel.getMedia().observe(getViewLifecycleOwner(), this::updateMediaDisplayed);

        watchlistView = new WatchlistView();
        preferencesView = new PreferencesView();
        spinner = (ProgressBar) view.findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);


        Button watchlistBtn = view.findViewById(R.id.watchlist);
        Button preferencesBtn = view.findViewById(R.id.preferences);
        ImageView likeBtn = view.findViewById(R.id.like);
        ImageView dislikeBtn = view.findViewById(R.id.dislike);
        ImageView watchedBtn = view.findViewById(R.id.watched);

        FragmentContainerView mediaCard = view.findViewById(R.id.mediaCard);

        likeBtn.setOnClickListener(view -> {
            viewModel.addLikedMedia();
            viewModel.nextMedia();
            setMediaFront();
            spinner.setVisibility(View.VISIBLE);
        });

        dislikeBtn.setOnClickListener(view -> {
            viewModel.addDislikedMedia();
            viewModel.nextMedia();
            setMediaFront();
            spinner.setVisibility(View.VISIBLE);
        });
        watchedBtn.setOnClickListener(view -> {
            viewModel.addWatchedMedia();
            viewModel.nextMedia();
            setMediaFront();
            spinner.setVisibility(View.VISIBLE);
        });

        watchlistBtn.setOnClickListener(view -> {
            FragmentTransaction fr = getFragmentManager().beginTransaction();
            fr.replace(R.id.fragmentInlogg, watchlistView);
            fr.addToBackStack(null);
            fr.commit();
        });
        preferencesBtn.setOnClickListener(view -> {
            FragmentTransaction fr = getFragmentManager().beginTransaction();
            fr.replace(R.id.fragmentInlogg, preferencesView);
            fr.addToBackStack(null);
            fr.commit();
        });

        getChildFragmentManager().beginTransaction()
        .replace(R.id.mediaCard, mediaFront).commit();

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
        return view;
    }

    public void mediaFlip() {
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

        Bundle bundle = new Bundle();
        bundle.putString("about", "This is a great movie highly recommend fjdpsafijdsa" +
                "fkpdosafkpdosa fkdsoakpfd fsdkofd fdf df df df df df d fd fd fd f" +
                "fd sfds fds f dsf ds fs ");
        mediaBack.setArguments(bundle);
        ft.replace(R.id.mediaCard, mediaBack);
        ft.commit();

        backSide = true;
    }

    private void setMediaFront(){
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.flip_out, R.animator.flip_in);
        spinner.setVisibility(View.GONE);

        Bundle bundle = new Bundle();
        bundle.putString("movie", viewModel.getCurrentMedia().getImage());
        mediaFront.setArguments(bundle);
        ft.replace(R.id.mediaCard, mediaFront, "te");
        ft.commit();

        backSide = false;
    }

    private void updateMediaDisplayed(IMedia media) {
        TextView mediaTitle = view.findViewById(R.id.mediaTitle);
        TextView mediaRating = view.findViewById(R.id.mediaRating);
        TextView mediaYear = view.findViewById(R.id.mediaYear);

        String title = viewModel.shorten(media.getName());
        mediaTitle.setText(viewModel.checkMovieLength(title));

        String grade = viewModel.shorten(media.getRating()) + "/10";
        mediaRating.setText(grade);
        mediaYear.setText(viewModel.shorten(media.getYear()));
        mediaFront.update(media.getImage());
    }
}
