package com.example.testmaddafakka.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.testmaddafakka.R;
import com.example.testmaddafakka.api.SingletonRequestQueue;
import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.viewmodel.WatchlistViewModel;

/**
 * Liked Medias a part of the watchlist responsable to show all liked movies
 */
public class LikedMedias extends Fragment {

    private NetworkImageView smallMediaImage;
    private TextView mediaTitle;
    private TextView mediaYear;
    private TextView mediaRating;

    private View view;
    private WatchlistViewModel viewModel;

    public LikedMedias() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_liked_movies, container, false);

        smallMediaImage = view.findViewById(R.id.mediaImageSmall);
        mediaTitle = view.findViewById(R.id.mediaTitleSmall);
        mediaYear = view.findViewById(R.id.mediaYearSmall);
        mediaRating = view.findViewById(R.id.mediaRatingSmall);

        viewModel = new ViewModelProvider(this).get(WatchlistViewModel.class);
        viewModel.init(requireContext());
        viewModel.getLikedMedias().observe(getViewLifecycleOwner(), medias -> {
            for(IMedia media : medias){
                //new compentn
                updateMediaDisplayed(media);
            }
        });

        return view;
    }

    /**
     * This updates the current media and displaces it in the likeMedias fragment.
     * @param media the current media object
     */
    private void updateMediaDisplayed(IMedia media) {
        ImageLoader imageLoader = SingletonRequestQueue.getInstance(getContext()).getImageLoader();
        String url = media.getImage();
        url = url.substring(1,url.length()-1);

        if(url.length() > 0)
            smallMediaImage.setImageUrl(url, imageLoader);

        String title = shorten(media.getTitle());
        mediaTitle.setText(checkMediaLength(title));

        String grade = shorten(media.getRating()) + "/10";
        mediaRating.setText(grade);
        mediaYear.setText(shorten(media.getYear()));

    }

    /**
     * This is a method to remove quotes in front and after the text.
     * @param text title
     * @return text without quotes in front and after.
     */
    private String shorten(String text) {
        String temp = text.substring(1, text.length() - 1);
        return temp;
    }

    /**
     * This is a method to shorten the media title so it fits the screen otherwise adds
     * ... after
     * @param title Media title
     * @return The media title shorten
     */
    private String checkMediaLength(String title) {
        if (title.length() > 13) {
            return title.substring(0, 14) + "...";
        }
        return title;
    }
}