package com.dj.songs.dialogfragment;

import java.util.Objects;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.dj.songs.R;


/**
 * description : 二级点赞页分割线
 */
public class NewsFeedsUsersItemDecoration extends RecyclerView.ItemDecoration {

    @NonNull
    private Drawable mDivider;

    private  ColorDrawable mColorDrawable;

    @SuppressLint("ResourceAsColor")
    public NewsFeedsUsersItemDecoration(Context content) {
        mDivider = Objects.requireNonNull(ResourcesCompat.getDrawable(content.getResources(),
                R.drawable.news_feeds_user_like_divider, null));
        this.mColorDrawable = new ColorDrawable(Color.parseColor("#000000"));

    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull
            RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.set(0,0,0,1);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int left = 198;
        int right = parent.getWidth();
        c.drawColor(Color.parseColor("#00FFFFFF"));
        int childCount = parent.getChildCount();
        for ( int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            int bottom = child.getBottom();
            mColorDrawable.setBounds(left, bottom - mColorDrawable.getIntrinsicHeight(), right, bottom );
            mColorDrawable.draw(c);
        }
    }



}
