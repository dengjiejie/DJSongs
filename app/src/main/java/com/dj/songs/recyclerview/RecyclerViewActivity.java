package com.dj.songs.recyclerview;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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
public class RecyclerViewActivity extends FragmentActivity {

    private RecyclerView mBlurRecyclerView;
    private RecyclerView mViewPagerRecyclerView;
    private View mTopBlur;
    private View mBottomBlur;
    private View mAddData;

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
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus) {


        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTopBlur = findViewById(R.id.top_blur);
        mBottomBlur = findViewById(R.id.bottom_blur);
        mAddData = findViewById(R.id.add_data);

        ((Button)findViewById(R.id.scroll_position)).setOnClickListener(v -> mBlurRecyclerView.smoothScrollToPosition(0));

        mAddData.setOnClickListener(v -> {
            mRecyclerViewAdapter.add();
//            mBlurRecyclerView.postDelayed(() -> mBlurRecyclerView.smoothScrollToPosition(0), 200);
        });


        mViewPagerRecyclerView = findViewById(R.id.view_pager);
        mViewPagerRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(10,10,10,10);
            }
        });
        mViewPagerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mViewPagerRecyclerView.setAdapter(new RecyclerViewAdapter(this));
        new PagerSnapHelper().attachToRecyclerView(mViewPagerRecyclerView);

        mBlurRecyclerView = findViewById(R.id.blur_boundary);
        mBlurRecyclerView.addItemDecoration(new BlurBoundaryItemDecoration() );
        mBlurRecyclerView.setLayoutManager(new SelfGridLayoutManager(this, 1));
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

            Log.d("dengjiejie", "recyclerView.newState() = " + newState);

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
