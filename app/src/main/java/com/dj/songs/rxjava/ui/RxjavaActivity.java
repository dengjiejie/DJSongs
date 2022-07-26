package com.dj.songs.rxjava.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.dj.songs.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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

        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.newThread())
                .flatMap(new Function<Long, Observable<Long>>() {
                    @Override
                    public Observable<Long> apply(@NonNull Long aLong) throws Exception {
                        return Observable.just(aLong);
                    }
                })
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.d("dengjiejie", "aBoolean = ");

                        mB.onNext(false);
                    }
                });

        mB.observeOn(Schedulers.newThread()).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                Log.d("dengjiejie", "aBoolean = " + aBoolean);
            }
        });
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



        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("haha");
                emitter.onComplete();
            }
        }).flatMap(new Function<String, Observable<String>>() {
            @Override
            public Observable<String> apply(@NonNull String s) throws Exception {
                return Observable.just(s);
            }
        }).map(new Function<String, Integer>() {
            @Override
            public Integer apply(@NonNull String s) throws Exception {
                return 0;
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });













    }


    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        super.setRequestedOrientation(requestedOrientation);
    }
}
