package com.dj.songs.breakword.ui;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.dj.songs.R;
import com.dj.songs.breakword.breakiterator.BreakWord;

import java.util.List;


public class BreakWordActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;

    private EditText editText;

    private Button breakWord;



    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            textView.setText(((List<String>) msg.obj).toString());

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break_word);
        initView();
    }

    private void initView() {
        breakWord = findViewById(R.id.break_word);
        breakWord.setOnClickListener(this);
        editText = findViewById(R.id.edit_text);
        editText.setOnClickListener(this);
        textView = findViewById(R.id.show_text);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.break_word: {
                new Thread(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void run() {
                        List<String> s = new BreakWord().breakWord(editText.getText().toString());

                        handler.obtainMessage(1, s).sendToTarget();
                    }
                }).start();

                break;
            }
        }
    }

}
