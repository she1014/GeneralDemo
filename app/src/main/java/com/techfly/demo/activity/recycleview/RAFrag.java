package com.techfly.demo.activity.recycleview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;
import com.malinskiy.superrecyclerview.swipe.SparseItemRemoveAnimator;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.adpter.Demo7RvAdapter;
import com.techfly.demo.bean.EventBean;
import com.techfly.demo.bean.ReasultBean;
import com.techfly.demo.bean.ShopOrderListBean;
import com.techfly.demo.bean.User;
import com.techfly.demo.fragment.BaseFragment;
import com.techfly.demo.interfaces.ItemMoreClickListener;
import com.techfly.demo.util.CommonUtils;
import com.techfly.demo.util.DialogUtil;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.SharePreferenceUtils;
import com.techfly.demo.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/*
 * 定制-果园
 */
public class RAFrag extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, OnMoreListener {

    @InjectView(R.id.base_rv)
    SuperRecyclerView base_rv;

    private Context mContext;
    private View view;

    private Demo7RvAdapter rvAdapter;
    private List<ShopOrderListBean.DataEntity.DatasEntity> datasEntities = new ArrayList<ShopOrderListBean.DataEntity.DatasEntity>();
    private LinearLayoutManager linerLayoutManager;
    private SparseItemRemoveAnimator  mSparseAnimator;

    private User mUser = null;
    private int mTotalRecord = 0;
    private int mPage = 1;
    private int mSize = 10;

    private String mStatus = "ACCEPT_WAITTING,PS_WAITTING,RECEIVE_WAITTING";//待发货，待收货//PS_WAITTING,ACCEPT_WAITTING
    private int mStatusCode = 1;

    private Boolean isVisible = false;
    private Boolean isInit = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_base_rv, container, false);
        mContext = getActivity();

        ButterKnife.inject(this, view);

        initView();

        setLinearLayoutRecyclerView();

//        LogsUtil.normal("FAFrag.onCreateView");

        if (isVisible) {
            refresh();
        }
        isInit = true;

        return view;
    }

    private void initView(){
        mUser = SharePreferenceUtils.getInstance(getActivity()).getUser();
    }


    private void setLinearLayoutRecyclerView() {
        rvAdapter = new Demo7RvAdapter(mContext, datasEntities,mStatusCode);
        linerLayoutManager = new LinearLayoutManager(mContext);
        base_rv.setLayoutManager(linerLayoutManager);
        linerLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

//        View header = LayoutInflater.from(mContext).inflate(R.layout.layout_lv_foot, null);
//        base_rv.addView(header, header.getLayoutParams().MATCH_PARENT, header.getLayoutParams().WRAP_CONTENT);

        rvAdapter.setItemClickListener(new ItemMoreClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                ToastUtil.DisplayToast(mContext, "点击了条目:" + postion);
            }

            @Override
            public void onItemSubViewClick(int choice, int postion) {

            }

            @Override
            public void onItemMulViewClick(int type, int choice, int postion) {

            }
        });

        base_rv.setRefreshListener(this);
        base_rv.setRefreshingColorResources(android.R.color.holo_orange_light, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_red_light);
        base_rv.setupMoreListener(this, 1);
        base_rv.hideMoreProgress();
        base_rv.setAdapter(rvAdapter);
    }

    private void loadMore() {
        if (mTotalRecord > rvAdapter.getItemCount()) {
            mPage = mPage + 1;
            getShopOrderListApi(mUser.getmId(), mUser.getmToken(), mPage, mSize, mStatus);
        } else {
            base_rv.hideMoreProgress();
        }
    }

    private void refresh() {
        mPage = 1;
        //清除之前的数据，加载最新的数据
        rvAdapter.clearAll();
        getShopOrderListApi(mUser.getmId(), mUser.getmToken(), mPage, mSize, mStatus);
    }

    @Override
    public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
        LogsUtil.normal("onMoreAsked");
        loadMore();
    }

    @Override
    public void onRefresh() {
        LogsUtil.normal("onRefresh");
        refresh();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = true;
            if (isInit) {
                refresh();
            }
        } else {
            isVisible = false;
        }
    }

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);


        Gson gson = new Gson();
        result = CommonUtils.removeBrace(result);

        try {
            if (type == Constant.API_GET_SHOP_ORDER_LIST_SUCCESS) {
                ShopOrderListBean data = gson.fromJson(result, ShopOrderListBean.class);
                if (data != null) {
                    mTotalRecord = data.getData().getTotalRecord();
                    rvAdapter.addAll(data.getData().getDatas(), mStatusCode);

                    if (mTotalRecord == 0) {
                        ToastUtil.DisplayToast(mContext, Constant.TOAST_MEG_REBACK_EMPTY);
                    }
                } else {
                    ToastUtil.DisplayToast(mContext, Constant.TOAST_MEG_ANALYZE_ERROR);
                }
            }

            if (type == Constant.API_POST_SHOP_DELIVERY_CONFIRM_SUCCESS) {
                ReasultBean data = gson.fromJson(result, ReasultBean.class);
                if (data != null) {
                    DialogUtil.showSuccessDialog(mContext, data.getData(), EventBean.EVENT_EMPTY);
                    refresh();
                } else {
                    ToastUtil.DisplayToast(mContext, Constant.TOAST_MEG_ANALYZE_ERROR);
                }
            }

            if (type == Constant.API_POST_SHOP_ACCEPT_ORDER_SUCCESS) {
                ReasultBean data = gson.fromJson(result, ReasultBean.class);
                if (data != null) {
                    ToastUtil.DisplayToast(mContext,data.getData());
                    refresh();
                } else {
                    ToastUtil.DisplayToast(mContext, Constant.TOAST_MEG_ANALYZE_ERROR);
                }
            }

        } catch (JsonSyntaxException e) {
            ToastUtil.DisplayToastDebug(mContext, Constant.TOAST_MEG_REBACK_ERROR + "\n" + e.getMessage());
            e.printStackTrace();
        }

        LogsUtil.normal("getResult,count="+rvAdapter.getItemCount());

    }

    public void onEventMainThread(EventBean bean) {
        if (bean.getAction().equals(EventBean.EVENT_GET_DATA)) {
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
