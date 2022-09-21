package com.dj.songs.dialogfragment;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED;
import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dj.songs.BaseActivity;
import com.dj.songs.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

/**
 * author : dengjiejie
 * date : 2020/7/11 11:22 AM
 * description :
 */
public class DialogFragmentActivity extends BaseActivity {

    private FrameLayout frameLayout;

    FrameLayout mBottomSheetBg;

    FrameLayout linearLayout;

    BottomSheetDialogFragment m;

    private BottomSheetBehavior mBottomSheetBehavior;


    private BottomSheetDialog mBottomSheetDialog;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);
//        mBottomSheetBg = findViewById(R.id.story_bottom_sheet_bg);
//
//        linearLayout = findViewById(R.id.fff);
//        ViewGroup.LayoutParams lp1;
//        lp1= new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        linearLayout.setLayoutParams(lp1);
//
//        mBottomSheetBg = new FrameLayout(this);
//        ViewGroup.LayoutParams lp;
//        lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        mBottomSheetBg.setLayoutParams(lp);
//        mBottomSheetBg.setBackgroundColor(R.color.black);
//        mBottomSheetBg.setAlpha(0.0f);
//        mBottomSheetBg.setClickable(true);



        frameLayout = findViewById(R.id.container);
        mBottomSheetBehavior = BottomSheetBehavior.from(frameLayout);
        mBottomSheetBehavior.setState(STATE_HIDDEN);
        mBottomSheetBehavior.setBottomSheetCallback(mBottomSheetCallback);
        mBottomSheetBehavior.setSkipCollapsed(true);

        findViewById(R.id.action_dialog_fragment).setOnClickListener(v -> {

            //实例化对象
            BaseFullBottomSheetFragment fragment = new BaseFullBottomSheetFragment();
            //绑定监听事件
            fragment.show(getSupportFragmentManager(), "dialog");
//            fragment.dismiss();

        });

        findViewById(R.id.action_dialog_activity).setOnClickListener(v -> {
            startActivity(new Intent(DialogFragmentActivity.this, DialogActivity.class));
            overridePendingTransition( R.anim.in_from_right,0);
        });

        findViewById(R.id.container).setOnClickListener(v -> {
            if (mBottomSheetBehavior.getState() != STATE_HIDDEN) {
                mBottomSheetBehavior.setState(STATE_HIDDEN);
            }
        });

        findViewById(R.id.action_normal_fragment_dialog).setOnClickListener(v -> {
//            showSheetDialog();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, FragmentDialog.newInstance(),"f1")
                    .commit();
            if (mBottomSheetBehavior.getState() != STATE_EXPANDED) {
                mBottomSheetBehavior.setState(STATE_EXPANDED);
            }

        });


    }

    private RecyclerView mRecyclerView;
    private void showSheetDialog() {

        View view = View.inflate(this, R.layout.dialog_fragment, null);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setAdapter(new RecyclersAdapter(new ArrayList<String>() {
        }));

        mRecyclerView.addItemDecoration(new NewsFeedsUsersItemDecoration(this));


        mBottomSheetDialog = new BottomSheetDialog(this);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        mBottomSheetBehavior.setBottomSheetCallback(mBottomSheetCallback);
        mBottomSheetBehavior.setSkipCollapsed(true);
        mBottomSheetDialog.setOnDismissListener(dismissListener);
        mBottomSheetDialog.setOnShowListener(showListener);
        mBottomSheetDialog.show();

    }


    /**
     * 弹窗高度，默认为屏幕高度的四分之三
     * 子类可重写该方法返回peekHeight
     *
     * @return height
     */
    protected int getPeekHeight() {
        int peekHeight = getResources().getDisplayMetrics().heightPixels;
        //设置弹窗高度为屏幕高度的3/4
        return peekHeight - peekHeight / 3;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private final DialogInterface.OnDismissListener dismissListener = new DialogInterface.OnDismissListener() {

        @Override
        public void onDismiss(DialogInterface dialog) {
            Log.d("dengjiejie", "onDismiss");

        }
    };
    private final DialogInterface.OnShowListener showListener = new DialogInterface.OnShowListener() {

        @Override
        public void onShow(DialogInterface dialog) {
            Log.d("dengjiejie", "onShow");

        }

    };



    private final BottomSheetBehavior.BottomSheetCallback mBottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull final View bottomSheet, final int newState) {


            Log.d("dengjiejie", "newState = " + newState);


//            if (newState == STATE_HIDDEN) {
//                mBottomSheetDialog.dismiss();
//            }
        }

        @Override
        public void onSlide(@NonNull final View bottomSheet, final float slideOffset) {
            if (Float.compare(slideOffset, Float.NaN) != 0) {
                Log.d("dengjiejie", "ghjkljhgk");

            }
        }
    };



}
