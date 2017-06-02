package com.techfly.demo.activity.goods;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.google.gson.Gson;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseFragmentActivity;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.adpter.MyFragementPagerAdapter;
import com.techfly.demo.bean.EventBean;
import com.techfly.demo.bean.GoodsKindNumBean;
import com.techfly.demo.bean.User;
import com.techfly.demo.interfaces.FragmentChangeListener;
import com.techfly.demo.selfview.PagerSlidingTabStrip;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.SharePreferenceUtils;
import com.techfly.demo.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

//订单

public class GoodManagerMenuActivity extends BaseFragmentActivity implements FragmentChangeListener {

    @InjectView(R.id.pager)
    ViewPager pager;
    @InjectView(R.id.tabs)
    PagerSlidingTabStrip tabs;

    private GMAFrag afrag;
    private GMBFrag bfrag;

    private String[] TOP_TAB = null;//new String[]{"出售中(20)", "仓库中(0)"};
    private List<Fragment> mChild;

    private MyFragementPagerAdapter adapter = null;
    private User mUser = null;

    private int mTotal = 0;//总订单数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_manager_menu);

        ButterKnife.inject(this);
        EventBus.getDefault().register(this);

        setTranslucentStatus(R.color.main_bg);

        initBaseView();
        setBaseTitle("商品管理", 0);
        initBackButton(R.id.top_back_iv);

        initView();

        loadData();
//        initFragment();

    }

    private void initView() {
        mUser = SharePreferenceUtils.getInstance(this).getUser();
    }

    private void loadData() {
        getShopGoodsKindsNumApi(mUser.getmId(), mUser.getmToken(),mUser.getiCode());
    }


    private void initFragment() {
        afrag = new GMAFrag();
        bfrag = new GMBFrag();

        mChild = new ArrayList<Fragment>();
        if (mChild != null) {
            mChild.clear();
        }

        mChild.add(afrag);
        mChild.add(bfrag);


        adapter = new MyFragementPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                LogsUtil.normal("getItem=" + position);
                return mChild.get(position);
            }

            @Override
            public int getCount() {
                return TOP_TAB.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return TOP_TAB[position];
            }
        };

        pager.setAdapter(adapter);

        //设置ViewPage预加载页面个数，最少为1
        pager.setOffscreenPageLimit(2);
//        //初始化 默认显示哪个
        pager.setCurrentItem(0);

        tabs.setViewPager(pager);
    }

    @Override
    public void onCurrentposition(int postion,int value) {
//        LogsUtil.normal("onCurrentposition.postion=" + postion+",mTotal="+mTotal+",tabs="+tabs);
        if(postion == 1){
            TOP_TAB = new String[]{"出售中(" + value + ")", "仓库中(" + (mTotal-value) + ")"};
        }else if(postion == 2){
            TOP_TAB = new String[]{"出售中(" + (mTotal-value) + ")", "仓库中(" + value + ")"};
        }
        tabs.notifyDataSetChanged();
    }

    public void onEventMainThread(EventBean bean) {
        Log.i("TTSS", "onEventMainThread,Main,action=" + bean.getAction());
    }

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);

        if (type == Constant.API_ANALYZE_SUCCESS) {
            try {
                Gson gson = new Gson();
                GoodsKindNumBean data = gson.fromJson(result, GoodsKindNumBean.class);
                if (data != null) {
                    mTotal = data.getData().getOffshelf_count() + data.getData().getNormal_count();

                    TOP_TAB = new String[]{"出售中(" + data.getData().getNormal_count() + ")", "仓库中(" + data.getData().getOffshelf_count() + ")"};

                    initFragment();
                } else {
                    ToastUtil.DisplayToast(this, Constant.TOAST_MEG_ANALYZE_ERROR);
                }
            } catch (Exception e) {
                ToastUtil.DisplayToast(this, Constant.TOAST_MEG_REBACK_ERROR + "\n" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
