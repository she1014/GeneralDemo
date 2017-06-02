package com.techfly.demo.activity.demo;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseFragmentActivity;
import com.techfly.demo.selfview.slidelayout.ISlideCallback;
import com.techfly.demo.selfview.slidelayout.SlideDetailsLayout;

import butterknife.InjectView;


public class Demo6Activity extends BaseFragmentActivity implements ISlideCallback {

    @InjectView(R.id.slidedetails)
    SlideDetailsLayout mSlideDetailsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo6);

        setTranslucentStatus(R.color.main_bg);

        initBaseView();
        setBaseTitle("Demo6", 0);
        initBackButton(R.id.top_back_iv);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.slidedetails_front,new Demo6Fragment().init()).commit();

    }

    @Override
    public void openDetails(boolean smooth) {
        //可用EventBus接收，实现点击上滑效果
        mSlideDetailsLayout.smoothOpen(smooth);
    }

    @Override
    public void closeDetails(boolean smooth) {
        mSlideDetailsLayout.smoothClose(smooth);
    }
}
