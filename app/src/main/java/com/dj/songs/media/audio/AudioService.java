package com.dj.songs.media.audio;

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
        mMediaPlayer.setVolume(1f, 1f);
        mMediaPlayer.setLooping(true);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new AudioBinder();
    }

    public void setMediaPlayerSource() {

    }

    public void paush() {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
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