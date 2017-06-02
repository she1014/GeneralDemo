package com.techfly.demo.activity.order;


import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.adpter.OrderReviewsLvAdapter;
import com.techfly.demo.bean.EventBean;
import com.techfly.demo.bean.ReveiwListBean;
import com.techfly.demo.bean.User;
import com.techfly.demo.util.CommonUtils;
import com.techfly.demo.util.SharePreferenceUtils;
import com.techfly.demo.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;

/**
 * 订单评论
 */
public class OrderReviewActivity extends BaseActivity {

    @InjectView(R.id.base_plv)
    PullToRefreshListView base_plv;

    private User mUser = null;
    private String m_getIntentId = "";

    private List<ReveiwListBean.DataEntity> datasEntityList = new ArrayList<ReveiwListBean.DataEntity>();
    private OrderReviewsLvAdapter adpter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_reviews);

        ButterKnife.inject(this);
        EventBus.getDefault().register(this);

        initBaseView();
        setBaseTitle("评论查看", 0);
        initBackButton(R.id.top_back_iv);
        setTranslucentStatus(R.color.main_bg);

        initView();

        loadIntent();

        initLisener();

        initAdapter();
    }

    private void initView() {
        mUser = SharePreferenceUtils.getInstance(this).getUser();
    }

    private void loadIntent() {
        m_getIntentId = getIntent().getStringExtra(Constant.CONFIG_INTENT_ORDER_ID);

        showProcessDialog();
        getOrderReviewInfoApi(mUser.getmId(), mUser.getmToken(), m_getIntentId);
    }


    private void initLisener() {
        base_plv.setMode(PullToRefreshBase.Mode.DISABLED);
        base_plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                refresh();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//                loadMore();
            }
        });

    }


    private void initAdapter() {
        adpter = new OrderReviewsLvAdapter(this, datasEntityList);
        base_plv.setAdapter(adpter);
    }


    private void refresh() {
        getOrderReviewInfoApi(mUser.getmId(), mUser.getmToken(), m_getIntentId);
    }
    /*private void updateView(ReveiwListBean.DataEntity data) {
        String url = data.getAvatar();
        if (TextUtils.isEmpty(url)) {
            url = "";
        }
        ImageLoader.getInstance().displayImage(url, item_avator_iv, ImageLoaderUtil.mAvatarIconLoaderOptions);

        item_name_tv.setText(data.getMobile());
        item_content_tv.setText(data.getContent());
        item_ratingbar.setRating(Float.parseFloat(data.getMark() + ""));
        item_date_tv.setText(data.getCreate_time());
    }*/

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);

        closeProcessDialog();
        result = CommonUtils.removeBrace(result);

        if (type == Constant.API_GET_ORDER_REVIEW_INFO_SUCCESS) {
            try {
                Gson gson = new Gson();
                ReveiwListBean data = gson.fromJson(result, ReveiwListBean.class);
                if (data != null) {
                    adpter.addAll(data.getData());
                }
            } catch (JsonSyntaxException e) {
                ToastUtil.DisplayToastDebug(this,Constant.TOAST_MEG_REBACK_ERROR+"\n"+e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void onEventMainThread(EventBean bean) {
        if (bean.getAction().equals(EventBean.EVENT_REFRESH_UI)) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
