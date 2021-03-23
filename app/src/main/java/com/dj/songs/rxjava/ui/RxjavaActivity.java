package com.dj.songs.rxjava.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.dj.songs.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.ReplaySubject;


public class RxjavaActivity extends AppCompatActivity {

    ReplaySubject<Boolean> m = ReplaySubject.create();
    BehaviorSubject<Boolean> mB = BehaviorSubject.createDefault(true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
    }


    @Override
    protected void onResume() {
        super.onResume();

//        Observable.timer(1000, TimeUnit.MILLISECONDS)
//                .observeOn(Schedulers.newThread())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        Log.d("dengjiejie", "aBoolean = ");
//
//                        mB.onNext(false);
//                    }
//                });
//
//        mB.observeOn(Schedulers.newThread()).subscribe(new Consumer<Boolean>() {
//            @Override
//            public void accept(Boolean aBoolean) throws Exception {
//                Log.d("dengjiejie", "aBoolean = " + aBoolean);
//            }
//        });
        BehaviorSubject<Integer> m = BehaviorSubject.create();
        m.onNext(1);

        m.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d("dengjiejie", " " + integer);
            }
        });


        m.onNext(2);

        m.onNext(3);

        m.onNext(4);

        m.onNext(5);


    }
}
