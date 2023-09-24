package com.example.colombocitycentreshoppingapp.presentation.adapter.HomeAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.colombocitycentreshoppingapp.R;
import com.example.colombocitycentreshoppingapp.domain.model.RecentLocation;

import java.util.ArrayList;

public class RecentLocationAdapter extends RecyclerView.Adapter<RecentLocationAdapter.ViewHolder> {

    final String TAG = this.getClass().getSimpleName();

    Context context;
    ArrayList<RecentLocation> recentLocationList;
    public RecentLocationAdapter.CustomClickListener customClickListener;

    public interface CustomClickListener {
        void onItemClick(View v, int position);
    }

    public RecentLocationAdapter(Context context, ArrayList<RecentLocation> recentLocationList, RecentLocationAdapter.CustomClickListener customClickListener) {
        this.context = context;
        this.recentLocationList = recentLocationList;
        this.customClickListener = customClickListener;

    }

    @Override
    public RecentLocationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view_recent_locations, parent, false);


        return new RecentLocationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentLocationAdapter.ViewHolder holder, int position) {

        RecentLocation recentLocation = recentLocationList.get(position);

        holder.tvLocationName.setText(recentLocation.getLocationName());
        holder.tvLocationAddress.setText(recentLocation.getLocationAddress());

    }

    @Override
    public int getItemCount() {
        return recentLocationList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvLocationName;
        TextView tvLocationAddress;
        ConstraintLayout clItem;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvLocationName = (TextView) itemView.findViewById(R.id.tv_recent_location_name);
            tvLocationAddress = (TextView) itemView.findViewById(R.id.tv_recent_location_address);
            clItem = (ConstraintLayout) itemView.findViewById(R.id.cl_root);

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



