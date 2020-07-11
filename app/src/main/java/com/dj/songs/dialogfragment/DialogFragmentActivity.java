package com.dj.songs.dialogfragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.dj.songs.R;

/**
 * author : dengjiejie
 * date : 2020/7/11 11:22 AM
 * description :
 */
public class DialogFragmentActivity extends FragmentActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);

        findViewById(R.id.action_dialog).setOnClickListener(v -> {

            //实例化对象
            MyDialogFragment fragment = new MyDialogFragment();
            //绑定监听事件
            fragment.show(getSupportFragmentManager(), "dialog");
        });

        findViewById(R.id.action_dialog_activity).setOnClickListener(v -> {
            startActivity(new Intent(DialogFragmentActivity.this, DialogActivity.class));
            overridePendingTransition( R.anim.in_from_right,0);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }




}
