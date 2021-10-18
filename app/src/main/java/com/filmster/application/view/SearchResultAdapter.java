package com.filmster.application.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.filmster.application.R;
import com.filmster.application.api.SingletonRequestQueue;
import com.filmster.application.model.IMedia;

import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private List<IMedia> resultList;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private NetworkImageView smallResultImage;
        private TextView name;

        public ViewHolder(View itemView){
            super(itemView);

            smallResultImage = itemView.findViewById(R.id.personImageSmall);
            name = itemView.findViewById(R.id.nameText);
        }
    }

    public SearchResultAdapter(List<IMedia> resultList){
        this.resultList = new ArrayList<>();
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public SearchResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.search_results_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        IMedia media = this.resultList.get(position);

        // Set item views based on your views and data model
        //updateMovieDisplayed(movie);
        ImageLoader imageLoader = SingletonRequestQueue.getInstance(context).getImageLoader();
        String url = media.getImage();
        url = url.substring(1,url.length()-1);

        NetworkImageView smallImage = holder.smallResultImage;
        TextView personName = holder.name;

        if(url.length() > 0) {
            smallImage.setImageUrl(url, imageLoader);
        }
        String title = shorten(media.getName());
        personName.setText(checkStringLength(title));

    }

    @Override
    public int getItemCount() {
        int r;
        if(resultList == null){
            r = 0;
        } else{
            r = resultList.size();
        }
        return r;
    }

    private String shorten(String text) {
        String temp = text.substring(1, text.length() - 1);
        return temp;
    }

    private String checkStringLength(String name) {
        if (name.length() > 13) {
            return name.substring(0, 14) + "...";
        }
        return name;
    }
}