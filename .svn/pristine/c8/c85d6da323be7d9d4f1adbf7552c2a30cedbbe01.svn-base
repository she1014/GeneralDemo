package com.techfly.demo.activity.goods;

import android.content.Context;
import android.content.Intent;
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
import com.techfly.demo.activity.release.CommentReleaseActivity;
import com.techfly.demo.adpter.GoodsManageAdapter;
import com.techfly.demo.bean.EventBean;
import com.techfly.demo.bean.ReasultBean;
import com.techfly.demo.bean.ShopGoodsBean;
import com.techfly.demo.bean.User;
import com.techfly.demo.fragment.BaseFragment;
import com.techfly.demo.interfaces.FragmentChangeListener;
import com.techfly.demo.interfaces.ItemMultClickListener;
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
 * 出售中
 */
public class GMAFrag extends BaseFragment {

    @InjectView(R.id.base_plv)
    PullToRefreshListView base_plv;
    @InjectView(R.id.base_load)
    View base_load;

    private Context mContext;
    private View view;

    private Boolean isFirst = true;

    private Boolean isVisible = false;
    private Boolean isInit = false;

    private String m_orderId = "";
    private Double m_price = 0.0;

    private int selectType = 1;

    private List<ShopGoodsBean.DataEntity.DatasEntity> datasEntityList = new ArrayList<ShopGoodsBean.DataEntity.DatasEntity>();
    private GoodsManageAdapter adapter = null;

    private User mUser = null;
    private int mPage = 1;
    private int mSize = 10;
    private int mTotalRecord = 0;
    private String mStatus = "正常";

    private String m_cur_aid = "";
    private int m_default = 0;

    private int type = 1;//出售中

    private FragmentChangeListener fragmentChangeListener;

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

        fragmentChangeListener = (FragmentChangeListener) getActivity();

        adapter = new GoodsManageAdapter(mContext, datasEntityList, type);
        base_plv.setAdapter(adapter);

        adapter.setItemClickListener(new ItemMultClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
            }

            @Override
            public void onItemSubViewClick(int choice, int postion) {
                ShopGoodsBean.DataEntity.DatasEntity data = (ShopGoodsBean.DataEntity.DatasEntity) adapter.getItem(postion);
                if (choice == 0) {//修改
                    Intent intent = new Intent(mContext, CommentReleaseActivity.class);
                    intent.putExtra(Constant.CONFIG_INTENT_GOODS_ID, data.getId() + "");
                    startActivityForResult(intent,Constant.REQUESTCODE_NORMAL);
                } else if (choice == 1) {//下架
                    DialogUtil.showDeleteDialog(mContext, "温馨提示", "您确定要下架此商品?", EventBean.EVENT_GOODS_STATUS_OFF_SHELF, data.getId() + "");
                } else if (choice == 2) {//上架
                    DialogUtil.showDeleteDialog(mContext, "温馨提示", "您确定要上架此商品?", EventBean.EVENT_GOODS_STATUS_NORMAL, data.getId() + "");
                } else if (choice == 3) {//删除
                    DialogUtil.showDeleteDialog(mContext, "温馨提示", "删除的商品将不可恢复\n您确定要删除此商品?", EventBean.EVENT_GOODS_STATUS_DELETE, data.getId() + "");
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
            getShopGoodsListApi(mUser.getmId(), mUser.getmToken(),mUser.getiCode(), mPage, mSize, mStatus);
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
            getShopGoodsListApi(mUser.getmId(), mUser.getmToken(),mUser.getiCode(), mPage, mSize, mStatus);
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

        result = CommonUtils.removeBrace(result);

        LogsUtil.normal("removeBrace="+result);

        Gson gson = new Gson();

        try {
            if (type == Constant.API_GET_SHOP_GOODS_LIST_SUCCESS) {
                ShopGoodsBean data = gson.fromJson(result, ShopGoodsBean.class);
                if (data != null) {

                    mTotalRecord = data.getData().getTotalRecord();
                    adapter.addAll(data.getData().getDatas());

                    fragmentChangeListener.onCurrentposition(1,mTotalRecord);

                    if (mTotalRecord == 0) {
                        ToastUtil.DisplayToast(mContext, Constant.TOAST_MEG_REBACK_EMPTY);
                    }

                } else {
                    ToastUtil.DisplayToast(mContext, Constant.TOAST_MEG_ANALYZE_ERROR);
                }
            }

            if (type == Constant.API_POST_GOODS_EDIT_STATUS_SUCCESS) {
                ReasultBean data = gson.fromJson(result, ReasultBean.class);
                if (data != null) {
                    DialogUtil.showSuccessDialog(mContext, data.getData(), EventBean.EVENT_CLOSE_CURRENT_ACTIVITY);
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
        if (bean.getAction().equals(EventBean.EVENT_GOODS_STATUS_NORMAL) && isVisible) {
            String oId = bean.getMsg();
            postShopGoodStatusApi(mUser.getmId(),mUser.getmToken(),oId,"正常");
        }
        if (bean.getAction().equals(EventBean.EVENT_GOODS_STATUS_OFF_SHELF) && isVisible) {
            String oId = bean.getMsg();
            postShopGoodStatusApi(mUser.getmId(),mUser.getmToken(),oId,"下架");
        }
        if (bean.getAction().equals(EventBean.EVENT_GOODS_STATUS_DELETE) && isVisible) {
            String oId = bean.getMsg();
            postShopGoodStatusApi(mUser.getmId(),mUser.getmToken(),oId,"DELETED");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
