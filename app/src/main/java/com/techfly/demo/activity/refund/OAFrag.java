package com.techfly.demo.activity.refund;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.adpter.ShopOrderRefundLvAdapter;
import com.techfly.demo.bean.EventBean;
import com.techfly.demo.bean.ReasultBean;
import com.techfly.demo.bean.ShopRefundOrderListBean;
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
 * 退款
 */
public class OAFrag extends BaseFragment {

    @InjectView(R.id.base_plv)
    PullToRefreshListView base_plv;
    @InjectView(R.id.base_load)
    View base_load;

    private Context mContext;
    private View view;

    private User mUser = null;
    private int mPage = 1;
    private int mSize = 10;

    private String mStatus = "ACCEPT_WAITTING,PS_WAITTING&refund_status=REFUND_APPLY";
    private int mStatusCode = 3;

    private int mTotalRecord = 0;

    private Boolean isVisible = false;
    private Boolean isInit = false;

    private ShopOrderRefundLvAdapter adapter;
    private List<ShopRefundOrderListBean.DataEntity.DatasEntity> datasEntities = new ArrayList<ShopRefundOrderListBean.DataEntity.DatasEntity>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_base_pulllistview, container, false);
        mContext = getActivity();

        ButterKnife.inject(this, view);
        EventBus.getDefault().register(this);

        initView();

        initAdapter();

        initLisener();

        if (isVisible) {
            refresh();
        }
        isInit = true;

        return view;
    }

    private void initView() {
        mUser = SharePreferenceUtils.getInstance(getActivity()).getUser();
    }

    private void initAdapter() {

        adapter = new ShopOrderRefundLvAdapter(mContext, datasEntities, mStatusCode);
        base_plv.setAdapter(adapter);

        adapter.setItemClickListener(new ItemMoreClickListener() {
            @Override
            public void onItemClick(View view, int postion) {

                ShopRefundOrderListBean.DataEntity.DatasEntity bean = (ShopRefundOrderListBean.DataEntity.DatasEntity) adapter.getItem(postion);

                LogsUtil.normal("postion="+postion+",getOrder_id="+bean.getOrder_id());

                Intent intent = new Intent(mContext,RefundOrderDetailActivity.class);
                intent.putExtra(Constant.CONFIG_INTENT_ORDER_ID,bean.getOrder_id()+"");
                intent.putExtra(Constant.CONFIG_INTENT_TYPE,"1");
                startActivityForResult(intent, Constant.REQUESTCODE_NORMAL);
            }

            @Override
            public void onItemSubViewClick(int choice, int postion) {
            }

            @Override
            public void onItemMulViewClick(int type, int choice, int postion) {

                ShopRefundOrderListBean.DataEntity.DatasEntity bean = (ShopRefundOrderListBean.DataEntity.DatasEntity) adapter.getItem(postion);

                if (type == mStatusCode) {
                    switch (choice) {
                        case 0:
                            DialogUtil.showDeleteDialog(mContext, "温馨提示", "您确认同意退款?", EventBean.CONFIRM_ORDER_CONFIRM_REFUND, bean.getOrder_id() + "");
                            break;
                        case 1:
                            DialogUtil.showDeleteDialog(mContext, "温馨提示", "您确认拒绝退款?", EventBean.CONFIRM_ORDER_REFUSE_REFUND,bean.getOrder_id() + "");
                            break;
                        case 2:
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + bean.getMobile()));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            break;
                    }
                }
            }
        });

    }

    private void initLisener() {
        base_plv.setMode(PullToRefreshBase.Mode.BOTH);
        base_plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
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

    private void loadMore() {
        if (adapter.getCount() < mTotalRecord) {
            mPage = mPage + 1;
            getShopRefundOrderListApi(mUser.getmId(), mUser.getmToken(), mPage, mSize,mUser.getiCode(),mStatus);
        } else {
            ToastUtil.DisplayToast(mContext, Constant.TOAST_MEG_LOADING_FINISH);
            base_plv.postDelayed(new Runnable() {
                @Override
                public void run() {
                    base_plv.onRefreshComplete();
                }
            }, 200);
        }
    }

    private void refresh() {
        mPage = 1;
        mSize = 10;

        //清除之前的数据，加载最新的数据
        adapter.clearAll();
        if (mUser != null) {
            getShopRefundOrderListApi(mUser.getmId(), mUser.getmToken(), mPage, mSize,mUser.getiCode(),mStatus);
        } else {
            ToastUtil.DisplayToast(mContext, Constant.TOAST_MEG_LOGIN_FIRST);
        }
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUESTCODE_NORMAL && resultCode == getActivity().RESULT_OK) {
            refresh();
        }
    }

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);

        base_plv.onRefreshComplete();
        base_load.setVisibility(View.INVISIBLE);
        Gson gson = new Gson();

        result = CommonUtils.removeBrace(result);

        try {
            if (type == Constant.API_GET_SHOP_REFUND_LIST_SUCCESS) {
                ShopRefundOrderListBean data = gson.fromJson(result, ShopRefundOrderListBean.class);
                if (data != null) {
                    mTotalRecord = data.getData().getTotalRecord();
                    adapter.addAll(data.getData().getDatas(), mStatusCode);

                    if (mTotalRecord == 0) {
                        ToastUtil.DisplayToast(mContext, Constant.TOAST_MEG_REBACK_EMPTY);
                    }
                } else {
                    ToastUtil.DisplayToast(mContext, Constant.TOAST_MEG_ANALYZE_ERROR);
                }
            }

            if (type == Constant.API_POST_SHOP_REFUND_STATUS_SUCCESS) {
                ReasultBean data = gson.fromJson(result, ReasultBean.class);
                if (data != null) {
                    DialogUtil.showSuccessDialog(mContext, data.getData(), EventBean.EVENT_EMPTY);
                    refresh();
                } else {
                    ToastUtil.DisplayToast(mContext, Constant.TOAST_MEG_ANALYZE_ERROR);
                }
            }

        } catch (JsonSyntaxException e) {
            ToastUtil.DisplayToastDebug(mContext, Constant.TOAST_MEG_REBACK_ERROR + "\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    public void onEventMainThread(EventBean bean) {
        if (bean.getAction().equals(EventBean.CONFIRM_ORDER_CONFIRM_REFUND) && isVisible) {
            String oId = bean.getMsg();
            postRefundStatusApi(mUser.getmId(), mUser.getmToken(), oId,"REFUNDED");
        }
        if (bean.getAction().equals(EventBean.CONFIRM_ORDER_REFUSE_REFUND) && isVisible) {
            String oId = bean.getMsg();
            postRefundStatusApi(mUser.getmId(), mUser.getmToken(), oId,"REFUND_DENY");
        }
    }

    //http://192.168.0.101:8025/manageOrder/refund?uid=2&lt-token=xxx&order_id=1&refund_status=REFUNDED&money=20
    //http://192.168.0.101:8025/manageOrder/refund?uid=2&lt-token=xxx&order_id=1&refund_status=REFUND_DENY&money=

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
