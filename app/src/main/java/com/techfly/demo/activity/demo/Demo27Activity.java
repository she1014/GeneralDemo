package com.techfly.demo.activity.demo;

import android.content.Intent;
import android.os.Bundle;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.adpter.ShopOrderLvAdapter;
import com.techfly.demo.bean.ShopOrderListBean;
import com.techfly.demo.bean.User;
import com.techfly.demo.mvp.presenter.Demo27PresenterV1;
import com.techfly.demo.mvp.view.Demo27View;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Demo27Activity extends BaseActivity implements Demo27View{

    @InjectView(R.id.base_plv)
    PullToRefreshListView base_plv;

    private User mUser = null;
    private int mPage = 1;
    private int mSize = 10;
    private int mTotalRecord = 0;

    private ShopOrderLvAdapter adapter;
    private List<ShopOrderListBean.DataEntity.DatasEntity> datasEntities = new ArrayList<ShopOrderListBean.DataEntity.DatasEntity>();
    private int mStatusCode = 1;

    long m_start = 0;
    long m_end = 0;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_pulllistview);

        m_start = System.currentTimeMillis();

        ButterKnife.inject(this);

        initBaseView();
        setBaseTitle("Demo27", 0);
        initBackButton(R.id.top_back_iv);
        setTranslucentStatus(R.color.main_bg);

        initAdapter();

        new Demo27PresenterV1(this).fetch();
    }

    private void initAdapter(){
        adapter = new ShopOrderLvAdapter(this,datasEntities,mStatusCode);
        base_plv.setAdapter(adapter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void showLoading() {
        ToastUtil.DisplayToast(Demo27Activity.this,"加载中...");
    }

    @Override
    public void showData(List<ShopOrderListBean.DataEntity.DatasEntity> list) {
        adapter.addAll(list,mStatusCode);

        m_end = System.currentTimeMillis();

        LogsUtil.normal("时间差:"+(m_end-m_start));

    }
}
