package com.dj.songs.binder.musicplayservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @author dengjie09
 * @description:
 * @date：2020/4/6 6:01 PM
 */
public class MusicService extends Service {

    private RemoteCallbackList<IMusicPlayCallBack> callback = new RemoteCallbackList<>();

    private IBinder mMusicService = new IMusicPlay.Stub() {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
        @Override
        public String play() throws RemoteException {

            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            final int N = callback.beginBroadcast();

            for (int i = 0; i < N; i++) {

                callback.getBroadcastItem(i).playFinish("finish");
            }

            Log.d("dengjiejie", "play music");

            return "play";
        }

        @Override
        public String stop() throws RemoteException {
            System.out.println("stop");
            return "stop";
        }

        @Override
        public void registerListener(IMusicPlayCallBack listener) throws RemoteException {
            Log.d("dengjiejie", "registerListener");
            callback.register(listener);
        }

        @Override
        public void unregisterListener(IMusicPlayCallBack listener) throws RemoteException {
            callback.unregister(listener);


        }
    };

    private IBinder mVideoPlay = new IVideoPlay.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void playVideo() throws RemoteException {

            Log.d("dengjiejie", "mVideoPlay play");

        }
    };

    private IBinder mBinderPool = new IBinderPool.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public IBinder queryBinder(int binderCode) throws RemoteException {
            switch (binderCode) {
                case 0: {
                    return mMusicService;
                }
                case 1: {
                    return mVideoPlay;
                }
            }
            return null;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinderPool;
    }


}
