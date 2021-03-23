package com.dj.songs.canvas_paint;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.dj.songs.R;

/**
 * author : dengjiejie
 * date : 2020/8/5 11:27 AM
 * description :
 */
public class CanvasArcPaint extends LinearLayout {
    private int mWidth;
    private int mHeight;
    /**
     * 弧形高度
     */
    private int mArcHeight;
    /**
     * 背景颜色
     */
    private int mBgColor;
    private Paint mPaint;
    private boolean right;
    private Context mContext;

    public CanvasArcPaint(Context context) {
        this(context, null);
    }

    public CanvasArcPaint(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasArcPaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }


    public CanvasArcPaint(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CanvasArcPaint);
        mArcHeight = typedArray.getDimensionPixelSize(R.styleable.CanvasArcPaint_arcHeight, 0);
        mBgColor = typedArray.getColor(R.styleable.CanvasArcPaint_bgColor, Color.parseColor("#303F9F"));
        right = typedArray.getBoolean(R.styleable.CanvasArcPaint_right, true);
        mPaint = new Paint();
        this.setWillNotDraw(false);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!right) {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(mBgColor);
            Rect rect = new Rect(0, 0, 100, mHeight);
            canvas.drawRect(rect, mPaint);
            mPaint.setColor(Color.BLUE);
            Path path = new Path();
            mPaint.setAntiAlias(true);
            path.moveTo(100, 0);
            path.quadTo(500, mHeight / 2, 100, mHeight);
            canvas.drawPath(path, mPaint);
        } else {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(mBgColor);
            Rect rect = new Rect( mArcHeight , 0, mWidth, mHeight);
            canvas.drawRect(rect, mPaint);
            mPaint.setColor(Color.BLUE);
            Path path = new Path();
            mPaint.setAntiAlias(true);
            path.moveTo(mArcHeight, 0);
            path.quadTo(0, mHeight / 2, mArcHeight, mHeight);
            canvas.drawPath(path, mPaint);

        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        }
        setMeasuredDimension(mWidth, mHeight);
    }
}
