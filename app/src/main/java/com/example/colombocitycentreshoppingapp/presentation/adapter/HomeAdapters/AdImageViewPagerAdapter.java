package com.example.colombocitycentreshoppingapp.presentation.adapter.HomeAdapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.colombocitycentreshoppingapp.R;

import java.util.ArrayList;

public class AdImageViewPagerAdapter extends RecyclerView.Adapter<AdImageViewPagerAdapter.ViewHolder> {

    final String TAG = this.getClass().getSimpleName();

    Context context;
    ArrayList<Drawable> itemsList;

    public AdImageViewPagerAdapter(Context context, ArrayList<Drawable> itemsList) {
        this.context = context;
        this.itemsList = itemsList;

    }

    @Override
    public AdImageViewPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_shops, parent, false);
        return new AdImageViewPagerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdImageViewPagerAdapter.ViewHolder holder, int position) {

        holder.ivBanner.setImageDrawable(itemsList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivBanner;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivBanner = itemView.findViewById(R.id.iv_item);
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


