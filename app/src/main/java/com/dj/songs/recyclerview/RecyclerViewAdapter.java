package com.dj.songs.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dj.songs.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * author : dengjiejie
 * date : 2020/8/3 8:55 PM
 * description :
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    List<Integer> list = new ArrayList<>(Arrays.asList(R.drawable.round_sample, R.drawable.round_sample, R.drawable.round_sample));

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
        viewHolder.mTextView.setText(String.valueOf(position));
//        viewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                list.clear();
//                list.add(R.drawable.rect_sample);
//                notifyDataSetChanged();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    private class ExViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mTextView;

        public ExViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_item);
            mTextView = itemView.findViewById(R.id.text_num);
        }
    }

    public void add() {
        notifyItemRangeInserted(0, 1);
    }

    public List<Integer> cal(List<Integer> list) {
        Collections.sort(list, (o1, o2) -> o1 <= o2 ? o1 : o2);
        int size = list.size();
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            result.add(size - list.lastIndexOf(list.get(i)) - 1);
        }
        return result;
    };






}
