package com.dj.songs;

import android.util.Pair;

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

import java.util.ArrayList;
import java.util.List;

/**
 * author : dengjiejie
 * date : 2021/3/23 2:40 PM
 * description :
 */
public class ActivityItemConfig {

    public final static Class instance = Object.class;

    public final static List<Pair<String, Class>> mButtonPairs = new ArrayList<>();
    static  {
        mButtonPairs.add(new Pair<>("Image_Loader", ImageMaganerActivity.class));
        mButtonPairs.add(new Pair<>("Image_Loader", ImageMaganerActivity.class));
        mButtonPairs.add(new Pair<>("Music_Service", MusicActivity.class));
        mButtonPairs.add(new Pair<>("NetWork_Service", NetWorkActivity.class));
        mButtonPairs.add(new Pair<>("RxJava_Service", RxjavaActivity.class));
        mButtonPairs.add(new Pair<>("Break_Word_Service", BreakWordActivity.class));
        mButtonPairs.add(new Pair<>("View_Model_Service", ViewModelActivity.class));
        mButtonPairs.add(new Pair<>("XCrash_Service", XCrash2Activity.class));
        mButtonPairs.add(new Pair<>("View_Pager_Service", ViewPagerActivity.class));
        mButtonPairs.add(new Pair<>("Dialog_Fragment_Service", DialogFragmentActivity.class));
        mButtonPairs.add(new Pair<>("Life_Circle_Service", ViewCircleActivity.class));
        mButtonPairs.add(new Pair<>("RecyclerView__Service", RecyclerViewActivity.class));
        mButtonPairs.add(new Pair<>("Canvas_Paint_Service", CanvasPaintActivity.class));
        mButtonPairs.add(new Pair<>("Media_Service", MediaActivity.class));
        mButtonPairs.add(new Pair<>("Spannable_Service", SpannableStringActivity.class));
        mButtonPairs.add(new Pair<>("Ani_Service", FirstActivity.class));
    }

}
