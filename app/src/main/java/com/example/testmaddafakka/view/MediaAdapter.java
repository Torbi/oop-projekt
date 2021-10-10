package com.example.testmaddafakka.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.testmaddafakka.R;
import com.example.testmaddafakka.api.SingletonRequestQueue;
import com.example.testmaddafakka.model.IMedia;
import com.example.testmaddafakka.model.Movie;

import java.util.List;

/**
 * A helper class to implement a recyclerView Adapter
 * It binds a type of data to a view
 */
public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.ViewHolder> {

    private List<IMedia> mediaList;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        private NetworkImageView smallMediaImage;
        private TextView mediaTitle;
        private TextView mediaYear;
        private TextView mediaRating;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            smallMediaImage = itemView.findViewById(R.id.mediaImageSmall);
            mediaTitle = itemView.findViewById(R.id.mediaTitleSmall);
            mediaYear = itemView.findViewById(R.id.mediaYearSmall);
            mediaRating = itemView.findViewById(R.id.mediaRatingSmall);

        }
    }

    public MediaAdapter(List<IMedia> mediaList) {
        this.mediaList = mediaList;
    }
    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public MediaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.media_list_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(MediaAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        IMedia media = this.mediaList.get(position);

        // Set item views based on your views and data model
        //updateMovieDisplayed(movie);
        ImageLoader imageLoader = SingletonRequestQueue.getInstance(context).getImageLoader();
        String url = media.getImage();
        url = url.substring(1,url.length()-1);

        NetworkImageView smallMovieImage = holder.smallMediaImage;
        TextView movieTitle = holder.mediaTitle;
        TextView movieRating = holder.mediaRating;
        TextView movieYear = holder.mediaYear;

        if(url.length() > 0) {
            smallMovieImage.setImageUrl(url, imageLoader);
        }
        String title = shorten(media.getTitle());
        movieTitle.setText(checkMovieLength(title));

        String grade = shorten(media.getRating()) + "/10";
        movieRating.setText(grade);
        movieYear.setText(shorten(media.getYear()));
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mediaList.size();
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
