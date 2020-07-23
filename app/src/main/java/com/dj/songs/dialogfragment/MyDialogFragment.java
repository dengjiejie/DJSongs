package com.dj.songs.dialogfragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.dj.songs.R;

/**
 * author : dengjiejie
 * date : 2020/7/11 11:51 AM
 * description :
 */
public class MyDialogFragment extends DialogFragment {

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (null != dialog) {
            Window window = dialog.getWindow();
            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
            window.setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));

//            DisplayMetrics dm = new DisplayMetrics();
//            window.getWindowManager().getDefaultDisplay().getMetrics( dm );
            params.gravity = Gravity.BOTTOM;
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = 500;

            if (window != null) {
                window.setAttributes(params);
            }
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.FragmentDialogAnimation;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View layoutView = inflater.inflate(R.layout.dialog_activity, container, false);

        return layoutView;

    }
}
