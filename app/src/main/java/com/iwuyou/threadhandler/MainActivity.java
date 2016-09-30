package com.iwuyou.threadhandler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements HanderThread.OnHandlerListener {

    @Bind(R.id.test)
    TextView test;
    @Bind(R.id.activity_main)
    RelativeLayout activityMain;
    HanderThread ht;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ht = new HanderThread();
        ht.setListener(this);
        ht.start();
//        ht.send10Message();
//        ht.getHandler().sendEmptyMessage(0);
    }

    @Override
    public void onHandler(final int i) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                test.setText("" + i);
            }
        });
    }

    /**
     * 由于在run方法中创建mHandler对象
     * 而线程调用start并不是立刻就启动进入run方法,若是在start之后sendMessage的话可能会导致getHandler空指针
     * 所以写在onClick中并且做了非空判断。
     */
    @OnClick(R.id.test)
    public void onClick() {
        if(ht.getHandler() != null){
            ht.send10Message();
        }
    }
}
