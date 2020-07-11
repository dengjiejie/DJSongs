package com.dj.songs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dj.songs.binder.ui.MusicActivity;
import com.dj.songs.breakword.ui.BreakWordActivity;
import com.dj.songs.dialogfragment.DialogFragmentActivity;
import com.dj.songs.image.ui.ImageMaganerActivity;
import com.dj.songs.network.ui.NetWorkActivity;
import com.dj.songs.rxjava.ui.RxjavaActivity;
import com.dj.songs.viewmodel.ui.ViewModelActivity;
import com.dj.songs.viewpager.ViewPagerActivity;
import com.dj.songs.xcrash.ui.XCrash2Activity;
import com.dj.songs.xcrash.ui.XCrashActivity;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private Button mImageLoader;

    private Button mMusicService;

    private Button mNetWorkService;

    private Button mRxJavakService;

    private Button mBreakWordService;

    private Button mViewModelService;

    private Button mXcrashService;

    private Button mViewPagerService;

    private Button mDialogFragmentService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mImageLoader = findViewById(R.id.action_images);
        mImageLoader.setOnClickListener(this);

        mMusicService = findViewById(R.id.action_music);
        mMusicService.setOnClickListener(this);

        mNetWorkService = findViewById(R.id.action_network);
        mNetWorkService.setOnClickListener(this);

        mRxJavakService = findViewById(R.id.action_rxjava);
        mRxJavakService.setOnClickListener(this);

        mBreakWordService = findViewById(R.id.action_break_word);
        mBreakWordService.setOnClickListener(this);


        mViewModelService = findViewById(R.id.action_view_model);
        mViewModelService.setOnClickListener(this);

        mXcrashService = findViewById(R.id.action_xcrash);
        mXcrashService.setOnClickListener(this);

        mViewPagerService = findViewById(R.id.action_viewpager);
        mViewPagerService.setOnClickListener(this);

        mDialogFragmentService = findViewById(R.id.action_dialog_gragment);
        mDialogFragmentService.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_images: {
                startActivity(new Intent(MainActivity.this, ImageMaganerActivity.class));
                break;
            }

            case R.id.action_music: {
                startActivity(new Intent(MainActivity.this, MusicActivity.class));
                break;
            }

            case R.id.action_network: {
                startActivity(new Intent(MainActivity.this, NetWorkActivity.class));
                break;
            }

            case R.id.action_rxjava: {
                startActivity(new Intent(MainActivity.this, RxjavaActivity.class));
                break;
            }

            case R.id.action_break_word: {
                startActivity(new Intent(MainActivity.this, BreakWordActivity.class));
                break;
            }

            case R.id.action_view_model: {
                startActivity(new Intent(MainActivity.this, ViewModelActivity.class));
                break;
            }

            case R.id.action_xcrash: {
                startActivity(new Intent(MainActivity.this, XCrash2Activity.class));
                break;
            }

            case R.id.action_viewpager: {
                startActivity(new Intent(MainActivity.this, ViewPagerActivity.class));
                break;
            }

            case R.id.action_dialog_gragment: {
                startActivity(new Intent(MainActivity.this, DialogFragmentActivity.class));
                break;
            }


        }
    }
}
