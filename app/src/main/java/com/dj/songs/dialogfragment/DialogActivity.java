package com.dj.songs.dialogfragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.dj.songs.R;

/**
 * author : dengjiejie
 * date : 2020/7/11 1:48 PM
 * description :
 */
public class DialogActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_activity);

        Window window = getWindow();
        window.setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = 500;

        if (window != null) {
            window.setAttributes(params);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition( 0,R.anim.out_to_left);

    }
}
