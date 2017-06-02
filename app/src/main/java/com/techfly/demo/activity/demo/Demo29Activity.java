package com.techfly.demo.activity.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.util.LogsUtil;

import butterknife.ButterKnife;

public class Demo29Activity extends BaseActivity {


    private MyThread1 thread1;
    private MyThread2 thread2;
    private Handler handler1 = null;
    private Handler handler2 = null;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo29);

        ButterKnife.inject(this);

        initBaseView();
        setBaseTitle("Demo29", 0);
        initBackButton(R.id.top_back_iv);
        setTranslucentStatus(R.color.main_bg);

        thread1 = new MyThread1();
        thread1.start();

        thread2 = new MyThread2();
        thread2.start();

        handler1 = thread1.getHandler1();
        handler2 = thread2.getHandler2();

        while(handler1 == null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler1 = thread1.getHandler1();
        }
        LogsUtil.normal("handler1="+handler1);

        while(handler2 == null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler2 = thread2.getHandler2();
        }
        LogsUtil.normal("handler2=" + handler2);
    }

    class MyThread1 extends Thread{

        private Handler handler1;

        public Handler getHandler1() {
            return handler1;
        }

        @Override
        public void run() {
            super.run();

            LogsUtil.normal("MyThread1.run");

            Looper.prepare();

            handler1 = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    //这里接收处理消息
                    if(msg.what == 2){
                        LogsUtil.normal("MyThread1接收到消息了,"+System.currentTimeMillis());
                    }
                }
            };

            for(int i = 0;i < 10;i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler2.sendEmptyMessage(3);
            }

            Looper.loop();
        }
    }

    class MyThread2 extends Thread{

        private Handler handler2;

        public Handler getHandler2() {
            return handler2;
        }

        @Override
        public void run() {
            super.run();

            LogsUtil.normal("MyThread2.run");

            Looper.prepare();

            handler2 = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    //这里接收处理消息
                    if(msg.what == 3){
                        LogsUtil.normal("MyThread2接收到消息了,"+System.currentTimeMillis());
                    }
                }
            };

            for(int i = 0;i < 10;i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler1.sendEmptyMessage(2);
            }

            Looper.loop();

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);
    }
}
