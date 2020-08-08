package com.dj.songs.xcrash;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.dj.songs.R;

/**
 * @author dengjie09
 * @description:
 * @date：2020/4/21 1:47 PM
 */
public class XCrashActivity extends Activity {



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash);
    }

    @Override
    protected void onResume() {
        super.onResume();


        findViewById(R.id.xcrash).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        });
    }
}
