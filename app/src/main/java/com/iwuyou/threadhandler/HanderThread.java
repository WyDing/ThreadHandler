package com.iwuyou.threadhandler;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

/**
 * Created by duanding on 16/9/30.
 */

public class HanderThread extends Thread{

    private Handler mHandler;
    private int  count = 0;
    private OnHandlerListener listener;

    public HanderThread() {

    }

    @Override
    public void run() {
        Looper.prepare();
        mHandler = new ThreadHandler();
        Looper.loop();
    }

    private class ThreadHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            SystemClock.sleep(1000);
            count++;
            if(null != listener){
                listener.onHandler(count);
            }
        }
    }

    public static interface OnHandlerListener{
        public void onHandler(int i);
    }

    public void setListener(OnHandlerListener listener) {
        this.listener = listener;
    }

    public void send10Message(){
        for (int i  = 0; i < 10 ; i ++){
            if(null != mHandler){
                mHandler.sendEmptyMessage(0);
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        SystemClock.sleep(1000);
//                        count++;
//                        if(null != listener){
//                            listener.onHandler(count);
//                        }
//                    }
//                });
            }
        }
    }

    public Handler getHandler() {
        return mHandler;
    }
}
