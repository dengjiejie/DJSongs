package com.dj.songs.recyclerview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dj.songs.DensityUtils;
import com.dj.songs.R;
import com.dj.songs.SongsApplication;

/**
 * author : dengjiejie
 * date : 2020/8/6 4:49 PM
 * description :
 */
public class BlurBoundaryItemDecoration extends RecyclerView.ItemDecoration {

    private final static int ITEM_OFFSETS = DensityUtils.dip2px(10);

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
//        if(parent.getChildAdapterPosition(view) % 2 == 0) {
//            outRect.set(ITEM_OFFSETS, 0, ITEM_OFFSETS, ITEM_OFFSETS);
//        } else {
//            outRect.set(0, 0, ITEM_OFFSETS, ITEM_OFFSETS);
//        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);


//        Paint xpaint = new Paint();
////        xpaint.setShadowLayer(5, 0f,0f,Color.BLACK);
//
//        LinearGradient xlinearGradient = new LinearGradient(0, 0, 0, DensityUtils.dip2px(10), new int[]{Color.BLACK, Color.RED, Color.BLACK}, null, Shader.TileMode.CLAMP);
//        xpaint.setShader(xlinearGradient);

        Paint ypaint = new Paint();
        ypaint.setColor(Color.GREEN);
        LinearGradient ylinearGradient = new LinearGradient(0, 0, DensityUtils.dip2px(10)/2, 0, new int[]{SongsApplication.mContext.getColor(R.color.FFFFFF), R.color.F8F8F8, R.color.FFFFFF}, null, Shader.TileMode.CLAMP);
        ypaint.setShader(ylinearGradient);

        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {

            View child = parent.getChildAt(i);
            int top = child.getTop();
            Paint xpaint = new Paint();
//        xpaint.setShadowLayer(5, 0f,0f,Color.BLACK);

            LinearGradient xlinearGradient = new LinearGradient(0, top-DensityUtils.dip2px(10), 0, top-DensityUtils.dip2px(5), new int[]{SongsApplication.mContext.getColor(R.color.F1F1F1), SongsApplication.mContext.getColor(R.color.F8F8F8)}, null, Shader.TileMode.CLAMP);
            xpaint.setShader(xlinearGradient);
            c.drawRect(0, top-DensityUtils.dip2px(10), child.getWidth() + DensityUtils.dip2px(10), top , xpaint);

            if (i % 2 == 0) {






            } else {

            }






        }


    }
}
