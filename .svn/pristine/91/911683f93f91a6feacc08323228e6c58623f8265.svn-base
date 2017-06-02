package com.techfly.demo.activity.demo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.HorizontalScrollView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshHorizontalScrollView;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.adpter.Demo18RvAdapter;
import com.techfly.demo.bean.DemoBean;
import com.techfly.demo.bean.User;
import com.techfly.demo.interfaces.ItemMoreClickListener;
import com.techfly.demo.selfview.DividerItemDecoration;
import com.techfly.demo.util.CommonUtils;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.SharePreferenceUtils;
import com.techfly.demo.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/*
 * 横向加载更多
 */
public class Demo21Activity extends BaseActivity {

    @InjectView(R.id.base_phs)
    PullToRefreshHorizontalScrollView base_phs;
    @InjectView(R.id.base_rv)
    RecyclerView base_rv;

    private User mUser;
    private int mPage = 1;
    private int mSize = 10;
    private int mTotalRecord = 0;

    private int count = 0;

    private Demo18RvAdapter rvAdapter;
    private List<DemoBean> datasEntities = new ArrayList<DemoBean>();
    private LinearLayoutManager linerLayoutManager;

    private String mStatus = "ACCEPT_WAITTING,PS_WAITTING,RECEIVE_WAITTING";//待发货，待收货//PS_WAITTING,ACCEPT_WAITTING
    private int mStatusCode = 1;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_demo21);

        ButterKnife.inject(this);

        initBaseView();
        setBaseTitle("Demo21", 0);
        initBackButton(R.id.top_back_iv);
        setTranslucentStatus(R.color.main_bg);

        initView();

        initLisener();

        setLinearLayoutRecyclerView();

        initData();

    }

    private void initView(){
        mUser = SharePreferenceUtils.getInstance(this).getUser();
    }

    private void initLisener(){

        base_phs.setMode(PullToRefreshBase.Mode.BOTH);

        base_phs.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<HorizontalScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<HorizontalScrollView> refreshView) {
                LogsUtil.normal("refresh");
//                refresh();
                initData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<HorizontalScrollView> refreshView) {
                LogsUtil.normal("loadMore");
//                loadMore();
                initData();
            }
        });
    }

    private void setLinearLayoutRecyclerView(){

        rvAdapter = new Demo18RvAdapter(this, datasEntities,mStatusCode);
        linerLayoutManager = new LinearLayoutManager(this);
        base_rv.setLayoutManager(linerLayoutManager);
        linerLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        base_rv.addItemDecoration(new DividerItemDecoration(Demo21Activity.this,DividerItemDecoration.HORIZONTAL_LIST));

        rvAdapter.setItemClickListener(new ItemMoreClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                ToastUtil.DisplayToast(Demo21Activity.this, "点击了条目:" + postion);
            }

            @Override
            public void onItemSubViewClick(int choice, int postion) {

            }

            @Override
            public void onItemMulViewClick(int type, int choice, int postion) {

            }
        });
        base_rv.setAdapter(rvAdapter);
        LogsUtil.normal("GDFrag.base_rv.setOnScrollChangeListener");

    }

    private void initData(){
        List<DemoBean> list = new ArrayList<DemoBean>();
        for(int i = 0;i < 5;i++){
            DemoBean bean = new DemoBean("AA"+(count++),"BB"+(count++),"CC"+(count++),"DD"+(count++),"EE"+(count++),"FF"+(count++),"GG"+(count++),"HH"+(count++),"II"+(count++),"JJ"+(count++));
            list.add(bean);
        }
        rvAdapter.addAll(list, mStatusCode);

        base_phs.postDelayed(new Runnable() {
            @Override
            public void run() {
                base_phs.onRefreshComplete();
            }
        },500);
    }

    private void refresh() {
        mPage = 1;
        mSize = 10;
        //清除之前的数据，加载最新的数据
        rvAdapter.clearAll();
        getShopOrderListApi(mUser.getmId(), mUser.getmToken(), mPage, mSize, mStatus);
    }

    private void loadMore() {
        if (mTotalRecord > rvAdapter.getItemCount()) {
            mPage = mPage + 1;
            getShopOrderListApi(mUser.getmId(), mUser.getmToken(), mPage, mSize, mStatus);
        }
    }

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);

        LogsUtil.normal("getResult");

        result = CommonUtils.removeBrace(result);
        base_phs.onRefreshComplete();

        if (type == Constant.API_GET_SHOP_ORDER_LIST_SUCCESS) {
            Gson gson = new Gson();
            /*ShopOrderListBean data = gson.fromJson(result, ShopOrderListBean.class);
            if (data != null) {
                mTotalRecord = data.getData().getTotalRecord();
                rvAdapter.addAll(data.getData().getDatas(), mStatusCode);

                if (mTotalRecord == 0) {
                    ToastUtil.DisplayToast(this, Constant.TOAST_MEG_REBACK_EMPTY);
                }
            } else {
                ToastUtil.DisplayToast(this, Constant.TOAST_MEG_ANALYZE_ERROR);
            }*/
        }
    }


}
