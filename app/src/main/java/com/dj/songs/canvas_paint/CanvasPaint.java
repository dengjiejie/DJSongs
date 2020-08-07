package com.dj.songs.canvas_paint;

import android.content.Context;
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
public class CanvasPaint extends LinearLayout {

    private Context mContext;

    public CanvasPaint(Context context) {
        super(context);
        this.mContext = context;
    }

    public CanvasPaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

    }

    public CanvasPaint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;

    }

    public CanvasPaint(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = context;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(20);
        paint.setTextSize(150);
        paint.setAlpha(100);
        paint.setAntiAlias(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStyle(Paint.Style.STROKE);

        Rect rect = new Rect(50, 50, 500, 500);
        RectF rectf = new RectF(50, 50, 500, 500);

        canvas.drawColor(Color.WHITE);
        canvas.drawRect(rect, paint);
        canvas.drawCircle(250, 750, 200, paint);
        Path path = new Path();
        path.moveTo(260, 1000);
        path.lineTo(510, 1250);
        path.lineTo(10, 1250);
        path.close();
        canvas.drawPath(path, paint);

        float[] pos = {20,1320,20,1335,40,1340,50,1387,60,1370,90,1420};

        canvas.drawLines(pos,4, 8, paint);
        canvas.drawArc(rectf, 0, 225, true, paint);
        canvas.drawOval(550, 50, 1000, 1000, paint);

        float[] pos1 = {20,1520,20,1535,40,1540,50,1587,60,1570,90,1620};
        canvas.drawPoints(pos1, paint);

        String text = "I am A";
        canvas.drawText(text, 50,1800, paint);

        canvas.drawTextOnPath(text, path, -250,250, paint);
        Bitmap roundSample = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.round_sample);
        Bitmap rectSample = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.rect_sample);

        Rect rect1 = new Rect(550, 1200, 1000, 1650);
        Rect rect2 = new Rect(700, 1500, 800, 1650);
        canvas.drawBitmap(roundSample, null,rect2, paint);


        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(roundSample, 0, 0, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DARKEN));
        canvas.drawBitmap(rectSample, 0, 0, paint);


        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        BitmapShader bitmapShader = new BitmapShader(roundSample, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        LinearGradient linearGradient = new LinearGradient(0, 600, getWidth(), getHeight(), Color.RED, Color.GREEN, Shader.TileMode.CLAMP);
        ComposeShader composeShader = new ComposeShader(bitmapShader, linearGradient, PorterDuff.Mode.DARKEN);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.getPaint().setShader(composeShader);
        shapeDrawable.setBounds(0, 600, getWidth(), getHeight());
        shapeDrawable.draw(canvas);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0,0,getWidth(),getHeight(),paint);

    }
}
