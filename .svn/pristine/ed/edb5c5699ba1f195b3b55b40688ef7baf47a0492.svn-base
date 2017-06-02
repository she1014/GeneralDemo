package com.techfly.demo.activity.demo;

import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;

import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.selfview.barrage.BarrageView;
import com.techfly.demo.util.LogsUtil;

import java.util.Random;


public class Demo5Activity extends BaseActivity {
    //两两弹幕之间的间隔时间
    public static final int DELAY_TIME = 500;

    /**
     * 标签：程序是否处于暂停状态
     * 15/11/01 测试按Home后一分钟以上回到程序会发生满屏线程阻塞
     */
    private boolean isOnPause = false;

    private Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo5);

        setTranslucentStatus(R.color.main_bg);

        initBaseView();
        setBaseTitle("Demo5", 0);
        initBackButton(R.id.top_back_iv);

        //读取文字资源
        final String[] texts = getResources().getStringArray(R.array.exp_name);

        //设置宽高全屏
        final ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        final Handler handler = new Handler();
        Runnable createBarrageView = new Runnable() {
            @Override
            public void run() {
                if (!isOnPause) {
                    LogsUtil.normal("发送弹幕");
                    //新建一条弹幕，并设置文字
                    final BarrageView barrageView = new BarrageView(Demo5Activity.this);
                    barrageView.setText(texts[random.nextInt(texts.length)]); //随机设置文字
                    addContentView(barrageView, lp);
                }
                //发送下一条消息
                handler.postDelayed(this, DELAY_TIME);
            }
        };
        handler.post(createBarrageView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isOnPause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isOnPause = false;
    }
}
