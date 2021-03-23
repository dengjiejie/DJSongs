package com.dj.songs.canvas_paint;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.dj.songs.R;

/**
 * author : dengjiejie
 * date : 2020/8/5 10:44 AM
 * description :
 */
public class CanvasPaintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas_paint);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LottieAnimationView lottieAnimationView = findViewById(R.id.la_aw);
        lottieAnimationView.playAnimation();
    }
}
