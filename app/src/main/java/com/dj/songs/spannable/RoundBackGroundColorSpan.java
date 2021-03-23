package com.dj.songs.spannable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.ColorUtils;

import com.dj.songs.DensityUtils;
import com.dj.songs.R;

public class RoundBackGroundColorSpan extends ReplacementSpan {

    private Context mContext;

    public RoundBackGroundColorSpan(Context context) {
        this.mContext = context;
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end,
                       @Nullable Paint.FontMetricsInt fm) {
        return ((int) paint.measureText(text, start, end)  );
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top,
                     int y, int bottom, @NonNull Paint paint) {
        int color1 = paint.getColor();
        float size = paint.getTextSize();
        paint.setColor(mContext.getResources().getColor(R.color.ori));
        paint.setTextSize(size);
        RectF rectF = new RectF(x, top + 25, x + ((int) paint.measureText(text, start, end)) + DensityUtils.dip2px(6), bottom - 10 );
        canvas.drawRoundRect(rectF, DensityUtils.dip2px(2), DensityUtils.dip2px(2), paint);
        paint.setColor(Color.WHITE);
        canvas.drawText(text, start, end, x + DensityUtils.dip2px(3), y, paint);
        paint.setColor(color1);
        paint.setTextSize(size);
    }
}
