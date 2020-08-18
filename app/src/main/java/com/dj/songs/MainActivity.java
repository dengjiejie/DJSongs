package com.dj.songs;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dj.songs.binder.ui.MusicActivity;
import com.dj.songs.breakword.ui.BreakWordActivity;
import com.dj.songs.canvas_paint.CanvasPaintActivity;
import com.dj.songs.dialogfragment.DialogFragmentActivity;
import com.dj.songs.image.ui.ImageMaganerActivity;
import com.dj.songs.media.MediaActivity;
import com.dj.songs.network.ui.NetWorkActivity;
import com.dj.songs.recyclerview.RecyclerViewActivity;
import com.dj.songs.rxjava.ui.RxjavaActivity;
import com.dj.songs.spannable.SpannableStringActivity;
import com.dj.songs.view.lifecircle.ViewCircleActivity;
import com.dj.songs.viewmodel.ui.ViewModelActivity;
import com.dj.songs.viewpager.ViewPagerActivity;
import com.dj.songs.xcrash.XCrash2Activity;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainActivity extends Activity{
    private static final String TAG = "MainActivity";

    private Map<String, Class> mButtonMap;
    private LinearLayout mLinearContainer;

    private Class instance = SpannableStringActivity.class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButtonMap = new LinkedHashMap<>();
        initHashMap();
    }

    private void initHashMap() {
        mButtonMap.put("Image_Loader", ImageMaganerActivity.class);
        mButtonMap.put("Music_Service", MusicActivity.class);
        mButtonMap.put("NetWork_Service", NetWorkActivity.class);
        mButtonMap.put("RxJava_Service", RxjavaActivity.class);
        mButtonMap.put("Break_Word_Service", BreakWordActivity.class);
        mButtonMap.put("View_Model_Service", ViewModelActivity.class);
        mButtonMap.put("XCrash_Service", XCrash2Activity.class);
        mButtonMap.put("View_Pager_Service", ViewPagerActivity.class);
        mButtonMap.put("Dialog_Fragment_Service", DialogFragmentActivity.class);
        mButtonMap.put("Life_Circle_Service", ViewCircleActivity.class);
        mButtonMap.put("RecyclerView__Service", RecyclerViewActivity.class);
        mButtonMap.put("Canvas_Paint_Service", CanvasPaintActivity.class);
        mButtonMap.put("Media_Service", MediaActivity.class);
        mButtonMap.put("Spannable_Service", SpannableStringActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLinearContainer = findViewById(R.id.linear_container);
        initView();
    }

    private void initView() {
        mLinearContainer.removeAllViews();
        for (String name : mButtonMap.keySet()) {
            Button button = new Button(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            button.setLayoutParams(layoutParams);
            button.setText(name);
            if (mButtonMap.get(name).getSimpleName().equals(instance.getSimpleName())) {
                startActivity(new Intent(MainActivity.this, mButtonMap.get(name)));
            }
            button.setOnClickListener(v -> {
                startActivity(new Intent(MainActivity.this, mButtonMap.get(name)));
            });
            mLinearContainer.addView(button);
        }
    }
}
