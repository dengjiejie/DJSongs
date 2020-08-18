package com.dj.songs.spannable;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.dj.songs.R;

import static android.view.View.VISIBLE;

/**
 * author : dengjiejie
 * date : 2020/8/17 2:44 PM
 * description :
 */
public class SpannableStringActivity extends Activity {

    private TextView mText;
    private Button mShow;
    private boolean isUp = false;

    int num = 111111;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable_string);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mText = findViewById(R.id.spannable);
        mShow = findViewById(R.id.show);

        String str="这是设";
        int bstart=str.indexOf("背景");
        int bend=bstart+"背景".length();
        int fstart=str.indexOf("前景");
        int fend=fstart+"前景".length();
        SpannableStringBuilder style= new SpannableStringBuilder(str);
        style.setSpan(new RoundBackGroundColorSpan(this), 0,2, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        mText.setText(style);
        mText.setOnClickListener(v -> mText.setText(++num));
        findViewById(R.id.start_ani).setOnClickListener(v -> {
            if(isUp) {
                slideUp();
            } else {
                slideDown();
            }
            isUp = !isUp;
            });

        mShow.setOnClickListener(v -> mShow.setText("dfdfdfd" + ++num));
    }

    private void slideUp() {
        mShow.setVisibility(VISIBLE);
        ObjectAnimator slide = ObjectAnimator.ofFloat(mShow,
                "translationY", 500f, 0f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(mShow, "alpha", 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(slide).with(alpha);
        animatorSet.setDuration(500);
        animatorSet.start();
    }

    private void slideDown() {
        ObjectAnimator slide = ObjectAnimator.ofFloat(mShow,
                "translationY", 0f, 500f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(mShow, "alpha", 0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(slide).with(alpha);
        animatorSet.setDuration(500);
        animatorSet.start();
    }
}
