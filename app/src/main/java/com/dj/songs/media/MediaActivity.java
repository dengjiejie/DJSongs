package com.dj.songs.media;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dj.songs.BaseActivity;
import com.dj.songs.R;
import com.dj.songs.media.audio.AudioActivity;
import com.dj.songs.media.video.VideoActivity;

import java.util.LinkedHashMap;
import java.util.Map;

public class MediaActivity extends BaseActivity {

    private Map<String, Class> mButtonMap;
    private LinearLayout mLinearContainer;

    private Class instance = MediaPlayer.class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButtonMap = new LinkedHashMap<>();
        initHashMap();
    }

    private void initHashMap() {
        mButtonMap.put("Audio_Service", AudioActivity.class);
        mButtonMap.put("Video_Service", VideoActivity.class);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mLinearContainer = findViewById(R.id.linear_container);
        initView();
    }

    private void initView() {
        mLinearContainer.removeAllViews();
        for (String name : mButtonMap.keySet()) {
            Button button = new Button(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            button.setLayoutParams(layoutParams);
            button.setText(name);
            if (mButtonMap.get(name).getSimpleName().equals(instance.getSimpleName())) {
                startActivity(new Intent(MediaActivity.this, mButtonMap.get(name)));
            }
            button.setOnClickListener(v -> {
                startActivity(new Intent(MediaActivity.this, mButtonMap.get(name)));
            });
            mLinearContainer.addView(button);
        }
    }
}
