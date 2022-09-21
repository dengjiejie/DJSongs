package com.dj.songs.network.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.dj.songs.BaseActivity;
import com.dj.songs.R;
import com.dj.songs.network.model.IPModel;
import com.dj.songs.network.net.IPService;
import com.dj.songs.network.net.OkHttpNetWork;
import com.dj.songs.network.net.RetrofitOkhttp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NetWorkActivity extends BaseActivity implements View.OnClickListener {

    private TextView textView;

    private Button button1;
    private Button button2;

    private Button button3;


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            textView.setText((String) msg.obj);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_work);
        initView();
    }

    private void initView() {
        button1 = findViewById(R.id.action1);
        button1.setOnClickListener(this);
        button2 = findViewById(R.id.action2);
        button2.setOnClickListener(this);
        button3 = findViewById(R.id.action3);
        button3.setOnClickListener(this);
        textView = findViewById(R.id.showText);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action1: {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String s = new OkHttpNetWork().requestNet();
                        handler.obtainMessage(1, s).sendToTarget();
                    }
                }).start();

                break;
            }
            case R.id.action2: {
                new IPService().request(new Callback<IPModel>() {
                    @Override
                    public void onResponse(Call<IPModel> call, Response<IPModel> response) {
                        if (response == null || response.body() == null || response.body().getData() == null) {
                            return ;
                        }
                        Log.d("dengjiejie", response.body().getData().getCountry());
                        textView.setText(response.body().getData().getCountry());
                    }

                    @Override
                    public void onFailure(Call<IPModel> call, Throwable t) {
                        Log.d("dengjiejie", "onFailure");

                    }
                });

                break;
            }
            case R.id.action3: {
                new RetrofitOkhttp().request(callback);

                break;
            }

        }
    }


    private Callback callback = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            handler.obtainMessage(1, response.body().toString()).sendToTarget();

        }

        @Override
        public void onFailure(retrofit2.Call call, Throwable t) {

        }
    };



}
