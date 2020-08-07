package com.dj.songs.recyclerview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * author : dengjiejie
 * date : 2020/8/6 4:49 PM
 * description :
 */
public class BlurBoundaryItemDecoration extends RecyclerView.ItemDecoration {


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.set(0,0,parent.getWidth(),0);
        }
        if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
            outRect.set(0,view.getBottom(),parent.getWidth(),0);
        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
//        Paint paint = new Paint();
//        LinearGradient linearGradient = new LinearGradient();

    }
}
