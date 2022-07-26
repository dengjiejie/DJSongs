package com.dj.songs;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dj.songs.activity_options_compat.FirstActivity;
import com.dj.songs.binder.ui.MusicActivity;
import com.dj.songs.breakword.ui.BreakWordActivity;
import com.dj.songs.canvas_paint.CanvasPaintActivity;
import com.dj.songs.dialogfragment.DialogFragmentActivity;
import com.dj.songs.image.ui.ImageMaganerActivity;
import com.dj.songs.media.MediaActivity;
import com.dj.songs.network.ui.NetWorkActivity;
import com.dj.songs.recyclerview.RecyclerViewActivity;
import com.dj.songs.rxjava.ui.RxjavaActivity;
import com.dj.songs.spannable.SpannableStringActivity;
import com.dj.songs.view.lifecircle.ViewCircleActivity;
import com.dj.songs.viewmodel.ui.ViewModelActivity;
import com.dj.songs.viewpager.ViewPagerActivity;
import com.dj.songs.xcrash.XCrash2Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * author : dengjiejie
 * date : 2021/3/23 11:55 AM
 * description :
 */
public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.VH> {

    private final static String TAG = "MainListAdapter";
    private final Activity mActivity;

    public MainListAdapter(Activity activity) {
        mActivity = activity;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_botton_item, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Pair<String, Class> buttonPair = ActivityItemConfig.mButtonPairs.get(position);
        holder.mButton.setText(buttonPair.first);
        if (buttonPair.second.getSimpleName().equals(ActivityItemConfig.instance.getSimpleName())) {
            mActivity.startActivity(new Intent(mActivity, buttonPair.second));
        } else {
            holder.mButton.setOnClickListener(View -> {
                mActivity.startActivity(new Intent(mActivity, buttonPair.second));
            });
        }
    }

    @Override
    public int getItemCount() {
        return ActivityItemConfig.mButtonPairs.size();
    }

    public static class VH extends RecyclerView.ViewHolder {
        public final Button mButton;

        public VH(View v) {
            super(v);
            mButton = (Button) v.findViewById(R.id.main_list_button);
        }
    }

}
