package com.dj.songs.canvas_paint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * author : dengjiejie
 * date : 2020/12/7 3:26 PM
 * description :
 */
public class DashView extends LinearLayout {


    public DashView(Context context) {
        super(context);
        this.setWillNotDraw(false);

    }

    public DashView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setWillNotDraw(false);

    }

    public DashView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setWillNotDraw(false);

    }

    public DashView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.setWillNotDraw(false);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setPathEffect(new DashPathEffect(new float[] {15, 5}, 0));
        Path path = new Path();
        path.reset();
        path.moveTo(getWidth()/2, 0);
        path.lineTo(getWidth()/2, getHeight());
        canvas.drawPath(path, paint);
    }
}
