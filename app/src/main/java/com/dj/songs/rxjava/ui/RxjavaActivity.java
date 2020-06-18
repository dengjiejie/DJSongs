package com.dj.songs.rxjava.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dj.songs.R;



public class RxjavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
    }


    @Override
    protected void onResume() {
        super.onResume();


//        Subscriber<String> stringSubscriber = new Subscriber<String>() {
//            @Override
//            public void onSubscribe(Subscription s) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//
//            }
//
//            @Override
//            public void onError(Throwable t) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        }

    }
}
