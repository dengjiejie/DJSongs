package com.dj.songs.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author : dengjiejie
 * date : 2020/9/4 4:32 PM
 * description :
 */
public class SelfGridLayoutManager extends GridLayoutManager {


    public SelfGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public SelfGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public SelfGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }


    @Override
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        Log.d("dengjiejie", "onLayoutCompleted");
    }
}
