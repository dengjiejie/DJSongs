package com.dj.songs.recyclerview;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.dj.songs.R;

/**
 * author : dengjiejie
 * date : 2020/8/3 5:59 PM
 * description :
 */
public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mBlurRecyclerView;
    private RecyclerView mViewPagerRecyclerView;
    private View mTopBlur;
    private View mBottomBlur;

    private RecyclerViewAdapter mRecyclerViewAdapter;

    private AlphaAnimation mDAlphaAnimation;
    private AlphaAnimation mAAlphaAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        mDAlphaAnimation = new AlphaAnimation(1, 0);
        mDAlphaAnimation.setDuration(300);
        mDAlphaAnimation.setFillAfter(true);

        mAAlphaAnimation = new AlphaAnimation(0, 1);
        mAAlphaAnimation.setDuration(300);
        mAAlphaAnimation.setFillAfter(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mTopBlur = findViewById(R.id.top_blur);
        mBottomBlur = findViewById(R.id.bottom_blur);
        mViewPagerRecyclerView = findViewById(R.id.view_pager);
//        mRecyclerView.addItemDecoration(new BlurBoundaryItemDecoration());
        mViewPagerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mViewPagerRecyclerView.setAdapter(new RecyclerViewAdapter(this));
        new PagerSnapHelper().attachToRecyclerView(mViewPagerRecyclerView);

        mBlurRecyclerView = findViewById(R.id.blur_boundary);
//        mRecyclerView.addItemDecoration(new BlurBoundaryItemDecoration());
        mBlurRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewAdapter = new RecyclerViewAdapter(this);
        mBlurRecyclerView.setAdapter(mRecyclerViewAdapter);
        mBlurRecyclerView.addOnScrollListener(onScrollListener);
        new LinearSnapHelper().attachToRecyclerView(mBlurRecyclerView);
    }

    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (dy > 0) {
                if (mBottomBlur.getVisibility() == View.GONE) {
                    mBottomBlur.startAnimation(mAAlphaAnimation);
                    mBottomBlur.setVisibility(View.VISIBLE);
                }
            } else if (dy < 0) {
                if (mTopBlur.getVisibility() == View.GONE) {
                    mTopBlur.startAnimation(mAAlphaAnimation);
                    mTopBlur.setVisibility(View.VISIBLE);
                }
            }
        }

        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                if (mTopBlur.getVisibility() == View.VISIBLE) {
                    mTopBlur.startAnimation(mDAlphaAnimation);
                }
                if (mBottomBlur.getVisibility() == View.VISIBLE) {
                    mBottomBlur.startAnimation(mDAlphaAnimation);
                }
            }
        }
    };
}
