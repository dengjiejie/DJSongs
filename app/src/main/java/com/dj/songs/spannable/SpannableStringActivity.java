package com.dj.songs.spannable;

import static android.view.View.VISIBLE;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.dj.songs.BaseActivity;
import com.dj.songs.R;

/**
 * author : dengjiejie
 * date : 2020/8/17 2:44 PM
 * description :
 */
public class SpannableStringActivity extends BaseActivity {

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

        String str="这是背景背景背景背";
        SpannableStringBuilder style= new SpannableStringBuilder(str);
        style.setSpan(new RoundBackGroundColorSpan(this), 0,2, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        mText.setText(style);

        Paint paint = new Paint();
        paint.setTextSize(dip2px(30));
        float strWidth = paint.measureText(str);

        Log.d("dengjiejie", "size = " + strWidth);

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

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
