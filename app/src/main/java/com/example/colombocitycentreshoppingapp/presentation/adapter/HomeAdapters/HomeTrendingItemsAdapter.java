package com.example.colombocitycentreshoppingapp.presentation.adapter.HomeAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.colombocitycentreshoppingapp.R;
import com.example.colombocitycentreshoppingapp.domain.model.TrendingItem;

import java.util.ArrayList;

public class HomeTrendingItemsAdapter extends RecyclerView.Adapter<HomeTrendingItemsAdapter.ViewHolder> {

    final String TAG = this.getClass().getSimpleName();

    Context context;
    ArrayList<TrendingItem> trendingItemsList;
    public CustomClickListener customClickListener;

    public interface CustomClickListener {
        void onItemClick(View v, int position);
    }

    public HomeTrendingItemsAdapter(Context context, ArrayList<TrendingItem> trendingItemsList,CustomClickListener customClickListener) {
        this.context = context;
        this.trendingItemsList = trendingItemsList;
        this.customClickListener = customClickListener;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_trending_items, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    TrendingItem trendingItem = trendingItemsList.get(position);

    holder.ivItemImage.setImageDrawable(trendingItem.getImageName());
    holder.tvItemName.setText(trendingItem.getItemName());
    holder.tvItemPrice.setText(trendingItem.getItemPrice());

    if(trendingItem.isFavorite()){
        holder.ivItemFavoriteIcon.setImageDrawable(context.getDrawable(R.drawable.favorite));
    }else {
        holder.ivItemFavoriteIcon.setImageDrawable(context.getDrawable(R.drawable.not_favorite));
    }

    }

    @Override
    public int getItemCount() {
        return trendingItemsList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivItemImage;
        ImageView ivItemFavoriteIcon;
        TextView tvItemName;

        TextView tvItemPrice;
        ConstraintLayout clItem;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivItemImage = (ImageView) itemView.findViewById(R.id.iv_item);
            ivItemFavoriteIcon = (ImageView) itemView.findViewById(R.id.iv_favorite);
            tvItemName = (TextView) itemView.findViewById(R.id.tv_item_name);
            tvItemPrice = (TextView) itemView.findViewById(R.id.tv_item_price);
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

