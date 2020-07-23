package com.dj.songs.dialogfragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dj.songs.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author : dengjiejie
 * date : 2020/7/13 6:26 PM
 * description :
 */
public class RecyclersAdapter extends RecyclerView.Adapter<RecyclersAdapter.ViewHolders> {

    private List<String> mlist = new ArrayList<>();
    public RecyclersAdapter(List<String> list) {
        mlist = list;
    }

    @NonNull
    @Override
    public ViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolders(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolders holder, int position) {
        holder.mView.setText("position = " + position);
    }

    @Override
    public int getItemCount() {
        return 100;
    }


    public class ViewHolders extends RecyclerView.ViewHolder{

        public TextView mView;

        public ViewHolders(View itemView) {
            super(itemView);
            mView = itemView.findViewById(R.id.show_text1);
        }

    }

}
