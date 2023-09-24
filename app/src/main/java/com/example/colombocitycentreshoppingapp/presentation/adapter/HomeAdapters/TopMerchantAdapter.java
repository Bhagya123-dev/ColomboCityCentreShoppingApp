package com.example.colombocitycentreshoppingapp.presentation.adapter.HomeAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.colombocitycentreshoppingapp.R;
import com.example.colombocitycentreshoppingapp.domain.model.Shops;

import java.util.ArrayList;

public class TopMerchantAdapter extends RecyclerView.Adapter<TopMerchantAdapter.ViewHolder> {

    final String TAG = this.getClass().getSimpleName();

    Context context;
    ArrayList<Shops> shopsItemsList;
    public TopMerchantAdapter.CustomClickListener customClickListener;

    public interface CustomClickListener {
        void onItemClick(View v, int position);
    }

    public TopMerchantAdapter(Context context, ArrayList<Shops> shopsItemsList, TopMerchantAdapter.CustomClickListener customClickListener) {
        this.context = context;
        this.shopsItemsList = shopsItemsList;
        this.customClickListener = customClickListener;

    }

    @Override
    public TopMerchantAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_top_merchant, parent, false);


        return new TopMerchantAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopMerchantAdapter.ViewHolder holder, int position) {

        Shops shopsItem = shopsItemsList.get(position);

        holder.ivItemImage.setImageDrawable(shopsItem.getShopImage());

    }

    @Override
    public int getItemCount() {
        return shopsItemsList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivItemImage;
        ConstraintLayout clItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivItemImage = (ImageView) itemView.findViewById(R.id.iv_item);
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



