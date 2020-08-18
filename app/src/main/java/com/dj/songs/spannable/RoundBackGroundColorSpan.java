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

import com.dj.songs.R;

public class RoundBackGroundColorSpan extends ReplacementSpan {

    private Context mContext;

    public RoundBackGroundColorSpan(Context context) {
        this.mContext = context;
    }

    @Override
    public int getSize(@NonNull Paint paint, CharSequence text, int start, int end,
                       @Nullable Paint.FontMetricsInt fm) {
        return ((int) paint.measureText(text, start, end) + 60);
    }

    @Override
    public void draw(@NonNull Canvas canvas, CharSequence text, int start, int end, float x, int top,
                     int y, int bottom, @NonNull Paint paint) {
        int color1 = paint.getColor();
        float size = paint.getTextSize();
        paint.setColor(mContext.getResources().getColor(R.color.ori));
        paint.setTextSize(size - 20);
        RectF rectF = new RectF(x, top, x + ((int) paint.measureText(text, start, end) + dip2px(6)), bottom );
        canvas.drawRoundRect(rectF, dip2px(2), dip2px(2), paint);
        paint.setColor(Color.WHITE);
        canvas.drawText(text, start, end, x + dip2px(3), y, paint);
        paint.setColor(color1);
        paint.setTextSize(size);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
