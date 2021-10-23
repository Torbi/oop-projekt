package com.filmster.application.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.selection.SelectionTracker;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.filmster.application.R;
import com.filmster.application.api.SingletonRequestQueue;
import com.filmster.application.model.IMedia;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Albin Sundström
 */

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private List<IMedia> resultList;
    private Context context;
    private static ClickListener clickListener;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private NetworkImageView smallResultImage;
        private TextView name;

        public ViewHolder(View itemView){
            super(itemView);

            itemView.setOnClickListener(this);

            smallResultImage = itemView.findViewById(R.id.personImageSmall);
            name = itemView.findViewById(R.id.nameText);
        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener){
        SearchResultAdapter.clickListener = clickListener;
    }

    public SearchResultAdapter(List<IMedia> resultList){
        this.resultList = resultList;
        System.out.println(resultList.size() + " RESULTLIST SEARCHRESULTADAPTER");
    }

    @NonNull
    @Override
    public SearchResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.search_results_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        IMedia media = this.resultList.get(position);

        // Set item views based on your views and data model
        ImageLoader imageLoader = SingletonRequestQueue.getInstance(context).getImageLoader();
        System.out.println(media); // null
        String url = media.getImage();

        NetworkImageView smallImage = holder.smallResultImage;
        TextView personName = holder.name;

        if(url.length() > 0) {
            smallImage.setImageUrl(url, imageLoader);
        }
        personName.setText(media.getName());
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    @Override
    public long getItemId(int position) {
        return (long) position;
    }




    public interface ClickListener {
        void onItemClick(int pos, View view);
    }
}