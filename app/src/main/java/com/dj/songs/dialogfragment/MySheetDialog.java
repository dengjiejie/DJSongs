package com.dj.songs.dialogfragment;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

/**
 * author : dengjiejie
 * date : 2020/7/14 2:05 PM
 * description :
 */
public class MySheetDialog extends BottomSheetDialog {

    private BottomSheetBehavior mBehavior;

    public MySheetDialog(@NonNull Context context) {
        super(context);
    }

    public MySheetDialog(@NonNull Context context, int theme) {
        super(context, theme);
    }

    protected MySheetDialog(@NonNull Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }















}
