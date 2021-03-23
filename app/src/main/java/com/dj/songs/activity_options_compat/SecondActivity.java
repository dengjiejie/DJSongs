package com.dj.songs.activity_options_compat;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;

import com.dj.songs.R;

/**
 * author : dengjiejie
 * date : 2020/9/24 3:30 PM
 * description :
 */
public class SecondActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_ani);
//        ViewCompat.setTransitionName(findViewById(R.id.start_ani), "1111");

    }

    @Override
    public void onBackPressed() {
        finish();

    }
}
