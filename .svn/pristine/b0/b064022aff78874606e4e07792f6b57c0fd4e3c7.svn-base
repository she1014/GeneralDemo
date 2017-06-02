package com.techfly.demo.activity.demo;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.adpter.NineGridLvAdapter;
import com.techfly.demo.bean.DemoBean;
import com.techfly.demo.bean.EventBean;
import com.techfly.demo.bean.ReasultBean;
import com.techfly.demo.bean.ShopOrderListBean;
import com.techfly.demo.bean.User;
import com.techfly.demo.util.CommonUtils;
import com.techfly.demo.util.DialogUtil;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.SharePreferenceUtils;
import com.techfly.demo.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/*
 * 九宫格图片
 */
public class Demo19Activity extends BaseActivity{

    @InjectView(R.id.base_plv)
    PullToRefreshListView base_plv;

    private NineGridLvAdapter rvAdapter;
    private List<DemoBean> datasEntities = new ArrayList<DemoBean>();

    private User mUser = null;
    private int mTotalRecord = 0;
    private int mPage = 1;
    private int mSize = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_pulllistview);

        ButterKnife.inject(this);

        initBaseView();
        setBaseTitle("Demo19", 0);
        setTranslucentStatus(R.color.main_bg);

        initView();

        initAdapter();

        initData();
    }

    private void initView(){
        mUser = SharePreferenceUtils.getInstance(this).getUser();
    }

    private void initAdapter(){
        rvAdapter = new NineGridLvAdapter(this,datasEntities);
        base_plv.setAdapter(rvAdapter);
    }

    private void initData(){
        List<DemoBean> list = new ArrayList<DemoBean>();
        for(int i = 0 ;i < 10;i++){
            DemoBean bean = new DemoBean("http://img5.imgtn.bdimg.com/it/u=646468994,661336821&fm=21&gp=0.jpg","http://img5.imgtn.bdimg.com/it/u=646468994,661336821&fm=21&gp=0.jpg",
                    "郭德纲","刚刚 来自努比亚z11","哈哈，微博内容，好搞笑啊"+i,Constant.picArray);
            list.add(bean);
        }
        rvAdapter.addAll(list);
    }

    private void refresh() {

    }

    private void loadMore() {

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