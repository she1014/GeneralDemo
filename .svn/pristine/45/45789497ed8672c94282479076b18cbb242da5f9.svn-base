package com.techfly.demo.activity.demo;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseAppCompatActivity;
import com.techfly.demo.selfview.AppBarLayoutScrollLisener;
import com.techfly.demo.selfview.NestScrollLisenerView;
import com.techfly.demo.selfview.wheelview.CoordLayoutScrollLisener;
import com.techfly.demo.util.LogsUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;

/*
 */
public class Demo15Activity extends BaseAppCompatActivity{

    @InjectView(R.id.demo_nsv)
    NestScrollLisenerView demo_nsv;
    @InjectView(R.id.app_barLayout)
    AppBarLayoutScrollLisener app_barLayout;
    @InjectView(R.id.app_coordlinear)
    CoordLayoutScrollLisener app_coordlinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo15);

        ButterKnife.inject(this);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        initView();

    }

    private void initView() {
        demo_nsv.setOnScrollChangedCallback(new NestScrollLisenerView.OnScrollChangedCallback() {
            @Override
            public void onScroll(int dx, int dy) {
                LogsUtil.normal("1.onScroll.dx="+dx+",dy="+dy);
            }
        });

        app_barLayout.setOnScrollChangedCallback(new AppBarLayoutScrollLisener.OnScrollChangedCallback() {
            @Override
            public void onScroll(int dx, int dy) {
                LogsUtil.normal("2.onScroll.dx="+dx+",dy="+dy);
            }
        });

        app_coordlinear.setOnScrollChangedCallback(new CoordLayoutScrollLisener.OnScrollChangedCallback() {
            @Override
            public void onScroll(int dx, int dy) {
                LogsUtil.normal("3.onScroll.dx="+dx+",dy="+dy);
            }
        });
    }

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
