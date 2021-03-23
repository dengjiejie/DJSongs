package com.dj.songs.activity_options_compat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;

import com.dj.songs.R;

/**
 * author : dengjiejie
 * date : 2020/9/24 3:30 PM
 * description :
 */
public class FirstActivity extends FragmentActivity {

    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_ani);
    }

    @Override
    protected void onResume() {
        super.onResume();
        imageView = findViewById(R.id.start_ani);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(FirstActivity.this, imageView, "1111");
//                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeScaleUpAnimation(imageView, imageView.getWidth() / 2, imageView.getHeight()/ 2,imageView.getWidth(), imageView.getHeight() );
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                ActivityCompat.startActivity(FirstActivity.this, intent, optionsCompat.toBundle());
            }
        });

    }
}
