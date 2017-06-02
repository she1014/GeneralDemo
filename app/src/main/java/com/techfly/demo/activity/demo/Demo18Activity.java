package com.techfly.demo.activity.demo;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.adpter.Demo18RvAdapter;
import com.techfly.demo.bean.DemoBean;
import com.techfly.demo.bean.EventBean;
import com.techfly.demo.bean.ReasultBean;
import com.techfly.demo.bean.ShopOrderListBean;
import com.techfly.demo.bean.User;
import com.techfly.demo.interfaces.ItemMoreClickListener;
import com.techfly.demo.selfview.RefreshableView;
import com.techfly.demo.util.CommonUtils;
import com.techfly.demo.util.DialogUtil;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.SharePreferenceUtils;
import com.techfly.demo.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Demo18Activity extends BaseActivity implements  RefreshableView.PullToRefreshListener{

    @InjectView(R.id.refreshable_view)
    RefreshableView refreshable_view;
    @InjectView(R.id.base_rv)
    RecyclerView base_rv;

    private Demo18RvAdapter rvAdapter;
    private List<DemoBean> datasEntities = new ArrayList<DemoBean>();
    private LinearLayoutManager linerLayoutManager;
    private int mStatusCode = 1;


    private User mUser = null;
    private int mTotalRecord = 0;
    private int mPage = 1;
    private int mSize = 10;

    private int count = 0;

    private String mStatus = "ACCEPT_WAITTING,PS_WAITTING,RECEIVE_WAITTING";//待发货，待收货//PS_WAITTING,ACCEPT_WAITTING

    private Boolean isRefresh = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo18);

        ButterKnife.inject(this);

        initBaseView();
        setBaseTitle("Demo18", 0);

        refreshable_view.setOnRefreshListener(this, 0);

        initView();

        setLinearLayoutRecyclerView();

//        refresh();

        initData();
    }

    private void initView(){
        mUser = SharePreferenceUtils.getInstance(this).getUser();
    }

    private void setLinearLayoutRecyclerView(){

        rvAdapter = new Demo18RvAdapter(this, datasEntities,mStatusCode);
        linerLayoutManager = new LinearLayoutManager(this);
        base_rv.setLayoutManager(linerLayoutManager);
        linerLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        rvAdapter.setItemClickListener(new ItemMoreClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                ToastUtil.DisplayToast(Demo18Activity.this, "点击了条目:" + postion);
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
        for(int i = 0;i < 3;i++){
            DemoBean bean = new DemoBean("AA"+(count++),"BB"+(count++),"CC"+(count++),"DD"+(count++),"EE"+(count++),"FF"+(count++),"GG"+(count++),"HH"+(count++),"II"+(count++),"JJ"+(count++));
            list.add(bean);
        }
        rvAdapter.addAll(list,mStatusCode);
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
        } else {
//            base_rv.hideMoreProgress();
        }
    }


    /**
     * 侧拉加载的回调接口
     */
    @Override
    public void onRefresh() {

        LogsUtil.normal("onRefresh");

        isRefresh = true;
        //此处执行刷新操作
        refreshable_view.finishRefreshing();

        initData();
    }

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);

        LogsUtil.normal("getResult");

        Gson gson = new Gson();
        result = CommonUtils.removeBrace(result);

        try {
            if (type == Constant.API_GET_SHOP_ORDER_LIST_SUCCESS) {
                ShopOrderListBean data = gson.fromJson(result, ShopOrderListBean.class);
                if (data != null) {
                    mTotalRecord = data.getData().getTotalRecord();
//                    rvAdapter.addAll(data.getData().getDatas(), mStatusCode);

                    if (mTotalRecord == 0) {
                        ToastUtil.DisplayToast(this, Constant.TOAST_MEG_REBACK_EMPTY);
                    }
                } else {
                    ToastUtil.DisplayToast(this, Constant.TOAST_MEG_ANALYZE_ERROR);
                }
            }

            if (type == Constant.API_POST_SHOP_DELIVERY_CONFIRM_SUCCESS) {
                ReasultBean data = gson.fromJson(result, ReasultBean.class);
                if (data != null) {
                    DialogUtil.showSuccessDialog(this, data.getData(), EventBean.EVENT_EMPTY);
                    refresh();
                } else {
                    ToastUtil.DisplayToast(this, Constant.TOAST_MEG_ANALYZE_ERROR);
                }
            }

            if (type == Constant.API_POST_SHOP_ACCEPT_ORDER_SUCCESS) {
                ReasultBean data = gson.fromJson(result, ReasultBean.class);
                if (data != null) {
                    ToastUtil.DisplayToast(this,data.getData());
                    refresh();
                } else {
                    ToastUtil.DisplayToast(this, Constant.TOAST_MEG_ANALYZE_ERROR);
                }
            }

        } catch (JsonSyntaxException e) {
            ToastUtil.DisplayToastDebug(this, Constant.TOAST_MEG_REBACK_ERROR + "\n" + e.getMessage());
            e.printStackTrace();
        }
    }
}