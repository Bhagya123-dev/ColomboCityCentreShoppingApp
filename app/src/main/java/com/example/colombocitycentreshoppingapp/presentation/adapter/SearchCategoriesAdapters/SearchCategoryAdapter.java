package com.example.colombocitycentreshoppingapp.presentation.adapter.SearchCategoriesAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.colombocitycentreshoppingapp.R;
import com.example.colombocitycentreshoppingapp.presentation.adapter.HomeAdapters.HomeTrendingItemsAdapter;

import java.util.ArrayList;

public class SearchCategoryAdapter extends RecyclerView.Adapter<SearchCategoryAdapter.ViewHolder> {

    final String TAG = this.getClass().getSimpleName();

    Context context;
    ArrayList<String> itemNameList;

    public SearchCategoryAdapter.CustomClickListener customClickListener;

    public interface CustomClickListener {
        void onItemClick(View v, int position);
    }

    public SearchCategoryAdapter(Context context, ArrayList<String> itemNameList, SearchCategoryAdapter.CustomClickListener customClickListener) {
        this.context = context;
        this.itemNameList = itemNameList;
        this.customClickListener = customClickListener;
    }

    @Override
    public SearchCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_searches, parent, false);
        return new SearchCategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchCategoryAdapter.ViewHolder holder, int position) {

        holder.tvListItem.setText(itemNameList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemNameList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvListItem;
        ConstraintLayout clItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvListItem = itemView.findViewById(R.id.tv_list_item);
            clItem = (ConstraintLayout) itemView.findViewById(R.id.cl_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customClickListener.onItemClick(v,getAdapterPosition());
                }
            });
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}



