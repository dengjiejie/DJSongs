package com.dj.songs.binder.ui;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dj.songs.BaseActivity;
import com.dj.songs.R;
import com.dj.songs.binder.musicplayservice.IBinderPool;
import com.dj.songs.binder.musicplayservice.IMusicPlay;
import com.dj.songs.binder.musicplayservice.IMusicPlayCallBack;
import com.dj.songs.binder.musicplayservice.IVideoPlay;
import com.dj.songs.binder.musicplayservice.MusicService;

/**
 * @author dengjie09
 * @description:
 * @date：2020/4/6 5:59 PM
 */
public class MusicActivity extends BaseActivity implements View.OnClickListener {

    private IMusicPlay iMusicPlay;
    private IVideoPlay iVideoPlay;
    private IBinderPool iBinderPool;
    private TextView state;
    private Button mPlay;
    private Button mStop;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            Log.d("dengjiejie", "handleMessage " +  (String) msg.obj);

            state.setText((String) msg.obj);
            super.handleMessage(msg);
        }
    };

    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.d("dengjiejie", "binderDied");
            if (iMusicPlay == null)
                return;
            iMusicPlay.asBinder().unlinkToDeath(deathRecipient, 0);
            iMusicPlay = null;
        }
    };

    private com.dj.songs.binder.musicplayservice.IMusicPlayCallBack IMusicPlayCallBack = new IMusicPlayCallBack.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void playFinish(String s) throws RemoteException {

            mHandler.obtainMessage(1, s).sendToTarget();
        }
    };

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBinderPool = IBinderPool.Stub.asInterface(service);
            try {
                iMusicPlay = IMusicPlay.Stub.asInterface(iBinderPool.queryBinder(0));
                iMusicPlay.play();

                iVideoPlay = IVideoPlay.Stub.asInterface(iBinderPool.queryBinder(1));

                iVideoPlay.playVideo();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                iMusicPlay.asBinder().linkToDeath(deathRecipient, 0);
                iMusicPlay.registerListener(IMusicPlayCallBack);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Log.d("dengjiejie", "onServiceConnected");
            bindService(new Intent(MusicActivity.this, MusicService.class), serviceConnection, Context.BIND_AUTO_CREATE);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("dengjiejie", "onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        initView();
        bindService(new Intent(MusicActivity.this, MusicService.class), serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void initView() {
        state = findViewById(R.id.state);
        mPlay = findViewById(R.id.play);
        mStop = findViewById(R.id.stop);
        mPlay.setOnClickListener(this);
        mStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play: {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (iMusicPlay != null) {
                            try {
                                String s = iMusicPlay.play();
                                Log.d("dengjiejie", s);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
                break;
            }

            case R.id.stop: {
                if (iMusicPlay != null) {
                    try {
                        String s = iMusicPlay.stop();
                        mHandler.obtainMessage(1, s).sendToTarget();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        try {
            iMusicPlay.unregisterListener(IMusicPlayCallBack);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        super.onDestroy();

    }
}
