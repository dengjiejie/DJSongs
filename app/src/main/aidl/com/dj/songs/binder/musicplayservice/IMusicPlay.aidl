// MusicPlay.aidl
package com.dj.songs.binder.musicplayservice;
import com.dj.songs.binder.musicplayservice.IMusicPlayCallBack;

// Declare any non-default types here with import statements

interface IMusicPlay {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    String play();
    String stop();
    void registerListener(IMusicPlayCallBack listener);
    void unregisterListener(IMusicPlayCallBack listener);
}
