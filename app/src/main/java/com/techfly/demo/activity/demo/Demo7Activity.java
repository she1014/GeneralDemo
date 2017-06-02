package com.techfly.demo.activity.demo;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseAppCompatActivity;
import com.techfly.demo.adpter.FragmentAdapter;
import com.techfly.demo.interfaces.ItemScrollListener;
import com.techfly.demo.util.DensityUtil;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/*
 * 农场众筹
 */
public class Demo7Activity extends BaseAppCompatActivity implements ItemScrollListener{

    @InjectView(R.id.order_iv)
    ImageView order_iv;

    public FragmentPagerAdapter mAdapter;
    public List<Fragment> mFragments = new ArrayList<Fragment>();

    private String m_pic = "";
    private String m_id = "";

    private CoordinatorLayout app_coordlinear;
    private TabLayout tabLayout;
    private FragmentAdapter adapter;
    private AppBarLayout app_barLayout;
    private ViewPager viewPager;
    private String title[];
    private AppBarLayout.LayoutParams mParams;

    private int m_screenHeight = 0;
    private int m_screenWight = 0;
    private int m_firstYHeight = 0;

    private Double m_maxPoint = 0.0;//临界值

    private List<String> bannerList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo7);

        ButterKnife.inject(this);
        setTranslucentStatus(R.color.main_bg);

        initBaseView();
        initBackButton(R.id.top_back_iv);
        setBaseTitle("Demo7", 0);

        initFragment();

    }

    private void initFragment() {
        app_coordlinear = (CoordinatorLayout) findViewById(R.id.app_coordlinear);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        title = this.getResources().getStringArray(R.array.farm_chip_menu);
        viewPager.setAdapter(adapter = new FragmentAdapter(getSupportFragmentManager(), this, getFragments(), title));
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        app_barLayout = (AppBarLayout) findViewById(R.id.app_barLayout);
        mParams = (AppBarLayout.LayoutParams) app_barLayout.getChildAt(0).getLayoutParams();

        m_screenHeight = DensityUtil.getScreenHeight(this);
        m_screenWight = DensityUtil.getScreenWidth(this);

        int[] location = new int[2];
        app_barLayout.getLocationOnScreen(location);
        m_firstYHeight = location[1];

    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Demo7Fragment1());
        fragments.add(new Demo7Fragment2());
        fragments.add(new Demo7Fragment3());
        return fragments;
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

    /*
     * 是否达到临界点
     */
    private void calcMaxPoint(Double divide) {
        if (divide > m_maxPoint) {
            m_maxPoint = divide;
        }
    }

    @Override
    public void onItemClick(View view, int postion) {
        LogsUtil.normal("Demo7Activity="+postion);
        if(postion < 1){
            order_iv.setVisibility(View.INVISIBLE);
        }else{
            order_iv.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.order_iv)
    public void jumpToShowMore(){
        ToastUtil.DisplayToast(this,"查看更多?");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
