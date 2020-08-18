package com.dj.songs.media.video;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.dj.songs.R;
import com.dj.songs.media.audio.AudioService;


/**
 * author : dengjiejie
 * date : 2020/8/8 6:15 PM
 * description :
 */
public class VideoActivity extends Activity {




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
