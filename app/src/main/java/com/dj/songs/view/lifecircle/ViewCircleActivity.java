package com.dj.songs.view.lifecircle;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.dj.songs.R;

/**
 * author : dengjiejie
 * date : 2020/7/27 8:28 PM
 * description :
 */
public class ViewCircleActivity extends FragmentActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("dengjiejie", "onCreate");
        setContentView(R.layout.activity_life_circle);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mTextView = findViewById(R.id.lift_circle);

        Log.d("dengjiejie", "onResume");

        mTextView.getViewTreeObserver().addOnDrawListener(new ViewTreeObserver.OnDrawListener() {
            @Override
            public void onDraw() {
                Log.d("dengjiejie", "onDraw");
            }
        });
        mTextView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.d("dengjiejie", "onGlobalLayout");
            }
        });

        mTextView.getViewTreeObserver().addOnGlobalFocusChangeListener(new ViewTreeObserver.OnGlobalFocusChangeListener() {
            @Override
            public void onGlobalFocusChanged(View oldFocus, View newFocus) {
                Log.d("dengjiejie", "onGlobalFocusChanged");
            }
        });

        mTextView.getViewTreeObserver().addOnWindowAttachListener(new ViewTreeObserver.OnWindowAttachListener() {
            @Override
            public void onWindowAttached() {
                Log.d("dengjiejie", "onWindowAttached");
            }

            @Override
            public void onWindowDetached() {
                Log.d("dengjiejie", "onWindowDetached");
            }
        });


        // 图像已经展示出来后调用
        mTextView.getViewTreeObserver().addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
            @Override
            public void onWindowFocusChanged(boolean hasFocus) {
                Log.d("dengjiejie", "onWindowFocusChanged: " + hasFocus);
            }
        });














    }
}
