package com.dj.songs.media.audio;

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


/**
 * author : dengjiejie
 * date : 2020/8/8 6:15 PM
 * description :
 */
public class AudioActivity extends Activity implements View.OnClickListener {

    private Button mStop;

    private Button mStart;

    private AudioService mAudioService;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mAudioService = ((AudioService.AudioBinder)service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        Intent intent = new Intent(AudioActivity.this, AudioService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mStart = findViewById(R.id.audio_start);
        mStart.setOnClickListener(this);
        mStop = findViewById(R.id.audio_stop);
        mStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.audio_start: {
                if (mAudioService != null) {
                    mAudioService.start();
                }
                break;
            }
            case R.id.audio_stop: {
                if (mAudioService != null) {
                    mAudioService.paush();
                }
                break;
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

}
