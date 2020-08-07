package com.dj.songs.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dj.songs.R;

/**
 * author : dengjiejie
 * date : 2020/8/3 8:55 PM
 * description :
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    public RecyclerViewAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ExViewHolder viewHolder = (ExViewHolder) holder;
        viewHolder.mImageView.setImageResource(R.drawable.round_sample);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    private class ExViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;

        public ExViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.image_item);
        }
    }


}
