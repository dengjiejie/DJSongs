package com.dj.songs.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.dj.songs.R;

/**
 * author : dengjiejie
 * date : 2020/8/6 5:08 PM
 * description :
 */
public class BlurView extends LinearLayout {

    private boolean mIsTop;

    public BlurView(Context context) {
        this(context, null);
    }

    public BlurView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public BlurView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);

    }

    public BlurView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BlurView);
        mIsTop = typedArray.getBoolean(R.styleable.BlurView_isTop, true);
        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStrokeWidth(10);
        LinearGradient linearGradient;
        if(mIsTop) {
            linearGradient = new LinearGradient(0,0, 0, getHeight(), Color.WHITE, Color.TRANSPARENT, Shader.TileMode.CLAMP);
        } else {
            linearGradient = new LinearGradient(0,getHeight(), 0, 0, Color.WHITE, Color.TRANSPARENT, Shader.TileMode.CLAMP);
        }
        paint.setShader(linearGradient);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

    }
}
