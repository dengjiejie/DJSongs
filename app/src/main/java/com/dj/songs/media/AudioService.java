package com.dj.songs.media;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.dj.songs.R;

/**
 * author : dengjiejie
 * date : 2020/8/8 8:15 PM
 * description :
 */
public class AudioService extends Service {

    private MediaPlayer mMediaPlayer;


    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = MediaPlayer.create(this, R.raw.sound_music);
        mMediaPlayer.setVolume(0.5f, 0.5f);
        mMediaPlayer.setLooping(true);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new AudioBinder();
    }

    public void setMediaPlayerSource() {

    }

    public void stop() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
        }
    }

    public void start() {
        if (!mMediaPlayer.isPlaying()) {
            mMediaPlayer.start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.stop();
        mMediaPlayer.release();
    }


    public class AudioBinder extends Binder {
        public AudioService getService() {
            return AudioService.this;
        }
    }
}