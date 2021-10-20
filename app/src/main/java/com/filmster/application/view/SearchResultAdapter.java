package com.filmster.application.view;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;
import com.filmster.application.R;
import com.filmster.application.model.ICategory;

import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {

    private List<ICategory> resultList;

    public class ViewHolder extends RecyclerView.ViewHolder {

        private NetworkImageView smallResultImage;
        private TextView name;

        public ViewHolder(View itemView){
            super(itemView);

            smallResultImage = itemView.findViewById(R.id.personImageSmall);
            name = itemView.findViewById(R.id.nameText);
        }
    }

    public SearchResultAdapter(List<ICategory> resultList){
        this.resultList = resultList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
