package com.techfly.demo.activity.recycleview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseFragmentActivity2;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.bean.EventBean;
import com.techfly.demo.selfview.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

//订单

public class RecyclerviewMenuActivity extends BaseFragmentActivity2 {

    @InjectView(R.id.pager)
    ViewPager pager;
    @InjectView(R.id.tabs)
    PagerSlidingTabStrip tabs;

    private RAFrag afrag;
    private RBFrag bfrag;
    private RCFrag cfrag;
//    private ODFrag dfrag;
//    private OEFrag efrag;

    private String[] TOP_TAB = null;//new String[] {"隐藏标题"};
    private Fragment[] fragmetArray = new Fragment[]{};
    private List<Fragment> mChild;

    private int m_select = 0;
    private String m_title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_menu);


        ButterKnife.inject(this);
        EventBus.getDefault().register(this);

        setTranslucentStatus(R.color.main_bg);

        initBaseView();
        setBaseTitle("RecyclerView示例", 0);
        initBackButton(R.id.top_back_iv);

        initFragment();

    }


    private void initFragment(){

        tabs.setDividerColor(R.color.divider_line_color);
        tabs.setVisibility(View.VISIBLE);
        TOP_TAB = new String[] {"标题1","标题2","标题3"};

        afrag=new RAFrag();
        bfrag=new RBFrag();
        cfrag=new RCFrag();

        fragmetArray = new Fragment[]{afrag,bfrag,cfrag};

        mChild= new ArrayList<Fragment>();
        if(mChild!=null){
            mChild.clear();
        }

        mChild.add(afrag);
        mChild.add(bfrag);
        mChild.add(cfrag);

        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
//                LogsUtil.normal("getItem=" + position);
                return mChild.get(position);
            }

            @Override
            public int getCount() {
//                LogsUtil.normal("getCount="+TOP_TAB.length);
                return TOP_TAB.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
//                LogsUtil.normal("getPageTitle=" + position);
                return TOP_TAB[position];
            }

        });
        //设置ViewPage预加载页面个数，最少为1
        pager.setOffscreenPageLimit(fragmetArray.length);
//        //初始化 默认显示哪个
        pager.setCurrentItem(0);
        tabs.setViewPager(pager);
    }


    private void loadIntent(){
        m_select = 0;//getIntent().getIntExtra(Constant.CONFIG_INTENT_SELECT,0);
        m_title = getIntent().getStringExtra(Constant.CONFIG_INTENT_TITLE);

        setBaseTitle(m_title, 0);
    }

    public void onEventMainThread(EventBean bean) {
        Log.i("TTSS", "onEventMainThread,Main,action=" + bean.getAction());
    }

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
