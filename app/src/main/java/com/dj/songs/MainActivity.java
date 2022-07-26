package com.dj.songs;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dj.songs.activity_options_compat.FirstActivity;
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

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MainListAdapter(this));

        recyclerView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d("dengjiejie", "hasFocus = " + hasFocus);
            }
        });
    }
}
