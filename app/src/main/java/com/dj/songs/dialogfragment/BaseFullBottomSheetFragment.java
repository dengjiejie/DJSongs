package com.dj.songs.dialogfragment;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dj.songs.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import static com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN;
import static java.security.AccessController.getContext;

/**
 * author : dengjiejie
 * date : 2020/7/14 1:42 PM
 * description :
 */
public class BaseFullBottomSheetFragment extends BottomSheetDialogFragment{

    private BottomSheetBehavior mBehavior;
    private ArrayList<String> list = new ArrayList<>();



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.dialog_fragment, null);
        initViews(view);
        dialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());

        mBehavior.setState(STATE_HIDDEN);
        mBehavior.setBottomSheetCallback(mBottomSheetCallback);
        mBehavior.setSkipCollapsed(true);

        return dialog;
    }

    private void initViews(View view) {
        for (int i = 0;i < 100;i++){
            list.add("条目"+i);
        }
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclersAdapter adapter = new RecyclersAdapter(list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (null != dialog) {
            Window window = dialog.getWindow();
            WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
            window.setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));
            params.gravity = Gravity.BOTTOM;
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = 500;

            if (window != null) {
                window.setAttributes(params);
            }
        }
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);//全屏展开
    }

    private final BottomSheetBehavior.BottomSheetCallback mBottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull final View bottomSheet, final int newState) {


            Log.d("dengjiejie", "newState = " + newState);


        }

        @Override
        public void onSlide(@NonNull final View bottomSheet, final float slideOffset) {
            if (Float.compare(slideOffset, Float.NaN) != 0) {
                Log.d("dengjiejie", "ghjkljhgk");

            }
        }
    };

}