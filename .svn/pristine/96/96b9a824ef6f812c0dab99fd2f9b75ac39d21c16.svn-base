package com.techfly.demo.activity.order;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.adpter.ShopOrderLvAdapter;
import com.techfly.demo.bean.EventBean;
import com.techfly.demo.bean.ShopOrderListBean;
import com.techfly.demo.bean.User;
import com.techfly.demo.interfaces.ItemMoreClickListener;
import com.techfly.demo.util.CommonUtils;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.PreferenceUtil;
import com.techfly.demo.util.SharePreferenceUtils;
import com.techfly.demo.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;


/**
 * 已完成订单列表
 */
public class CompletedOrderListActivity extends BaseActivity {

    @InjectView(R.id.base_plv)
    PullToRefreshListView base_lv;

//    @InjectView(R.id.base_noorder_linear)
//    LinearLayout base_noorder_linear;
    @InjectView(R.id.base_empty_linear)
    LinearLayout base_empty_linear;

    private User mUser = null;
    private int mPage = 1;
    private int mSize = 10;
    private int mTotalRecord = 0;

    private String mStatus = "FINISHED";//待发货，待收货
    private int mStatusCode = 2;

    private Boolean isVisible = false;
    private Boolean isInit = false;

    private ShopOrderLvAdapter adapter;
    private List<ShopOrderListBean.DataEntity.DatasEntity> datasEntities = new ArrayList<ShopOrderListBean.DataEntity.DatasEntity>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_pulllistview);

        ButterKnife.inject(this);
        EventBus.getDefault().register(this);

        initBaseView();

        setBaseTitle("已收货",0);

        initBackButton(R.id.top_back_iv);

        setTranslucentStatus(R.color.main_bg);

        initView();

        initLisener();

        initAdapter();

        showProcessDialog();

        refresh();
    }

    private void initLisener(){
        base_lv.setMode(PullToRefreshBase.Mode.BOTH);
        base_lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refresh();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                loadMore();
            }
        });
    }

    private void initAdapter() {
        adapter = new ShopOrderLvAdapter(this, datasEntities, mStatusCode);
        base_lv.setAdapter(adapter);

        adapter.setItemClickListener(new ItemMoreClickListener() {
            @Override
            public void onItemClick(View view, int postion) {

            }

            @Override
            public void onItemSubViewClick(int choice, int postion) {
            }

            @Override
            public void onItemMulViewClick(int type, int choice, int postion) {

                ShopOrderListBean.DataEntity.DatasEntity bean = (ShopOrderListBean.DataEntity.DatasEntity) adapter.getItem(postion);

                LogsUtil.normal("type=" + type + ",choice=" + choice + ",postion=" + postion);

                if (type == mStatusCode) {
                    switch (choice) {
                        case 4://物流可查
                            Intent intent1 = new Intent(CompletedOrderListActivity.this,OrderLogisticActivity.class);
                            intent1.putExtra(Constant.CONFIG_INTENT_ORDER_ID,bean.getId()+"");
                            intent1.putExtra(Constant.CONFIG_INTENT_IS_MODIFY,true);
                            startActivity(intent1);
                            break;
                        case 5://物流可查
                            Intent intent2 = new Intent(CompletedOrderListActivity.this,OrderLogisticActivity.class);
                            intent2.putExtra(Constant.CONFIG_INTENT_ORDER_ID,bean.getId()+"");
                            intent2.putExtra(Constant.CONFIG_INTENT_IS_MODIFY,true);
                            startActivity(intent2);
                            break;
                        case 6://尚未评论
                            ToastUtil.DisplayToast(CompletedOrderListActivity.this, "尚未评论");
                            break;
                        case 7://已评论
                            Intent intent4 = new Intent(CompletedOrderListActivity.this,OrderReviewActivity.class);
                            intent4.putExtra(Constant.CONFIG_INTENT_ORDER_ID,bean.getId()+"");
                            startActivity(intent4);
                            break;
                    }
                }
            }
        });

    }

    private void initView(){

        mUser = SharePreferenceUtils.getInstance(this).getUser();

        if(PreferenceUtil.getBooleanSharePreference(this, Constant.CONFIG_IS_LOGIN)) {
            base_empty_linear.setVisibility(View.INVISIBLE);
        }else{
            base_empty_linear.setVisibility(View.VISIBLE);
        }
    }

    private void loadMore(){
        if(checkLogin(this)){
            if(adapter.getCount() < mTotalRecord){
                mPage = mPage + 1;
                getShopOrderListApi(mUser.getmId(), mUser.getmToken(), mPage, mSize, mStatus);
            }else{
                ToastUtil.DisplayToast(this, Constant.TOAST_MEG_LOADING_FINISH);
                base_lv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        base_lv.onRefreshComplete();
                    }
                },200);
            }
        }else{
            base_lv.postDelayed(new Runnable() {
                @Override
                public void run() {
                    base_lv.onRefreshComplete();
                }
            }, 200);
        }

    }

    private void refresh(){
        if(checkLogin(this)){
            mPage = 1;
            mSize = 10;

            //清除之前的数据，加载最新的数据
            adapter.clearAll();
            getShopOrderListApi(mUser.getmId(), mUser.getmToken(), mPage, mSize, mStatus);
        }else{
            base_lv.postDelayed(new Runnable() {
                @Override
                public void run() {
                    base_lv.onRefreshComplete();
                }
            }, 200);
        }
    }


    public void onEventMainThread(EventBean bean) {
//        Log.i("TTSS", "onEventMainThread,Main,action=" + bean.getAction());
        if (bean.getAction().equals(EventBean.EVENT_REFRESH_COMPLETED_LIST)) {
            refresh();
        }
    }

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);

        result = CommonUtils.removeBrace(result);

        closeProcessDialog();

        base_lv.onRefreshComplete();

        Gson gson = new Gson();

        LogsUtil.normal("getResult...4,type="+type+",result="+result);

        try {
            if (type == Constant.API_GET_SHOP_ORDER_LIST_SUCCESS) {

                LogsUtil.normal("getResult...5,type="+type+",result="+result);

                ShopOrderListBean data = gson.fromJson(result, ShopOrderListBean.class);

                LogsUtil.normal("getResult...6,type="+type+",result="+result);

                if (data != null) {

                    LogsUtil.normal("加载数据...5");

                    mTotalRecord = data.getData().getTotalRecord();
                    adapter.addAll(data.getData().getDatas(), mStatusCode);

                    if (mTotalRecord == 0) {
                        ToastUtil.DisplayToast(this, Constant.TOAST_MEG_REBACK_EMPTY);
                    }
                } else {
                    ToastUtil.DisplayToast(this, Constant.TOAST_MEG_ANALYZE_ERROR);
                }
            }
        } catch (JsonSyntaxException e) {
            ToastUtil.DisplayToastDebug(this,Constant.TOAST_MEG_REBACK_ERROR+"\n"+e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
