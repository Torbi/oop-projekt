package com.filmster.application.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.filmster.application.R;
import com.filmster.application.api.SingletonRequestQueue;
import com.filmster.application.model.IMedia;

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

        NetworkImageView smallMovieImage = holder.smallMediaImage;
        TextView movieTitle = holder.mediaTitle;
        TextView movieRating = holder.mediaRating;
        TextView movieYear = holder.mediaYear;

        if(url.length() > 0) {
            smallMovieImage.setImageUrl(url, imageLoader);
        }
        String title = media.getName();
        movieTitle.setText(title);

        String grade = media.getRating().toString() + "/10";
        movieRating.setText(grade);
        movieYear.setText(Integer.toString(media.getYear()));
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mediaList.size();
    }

}
