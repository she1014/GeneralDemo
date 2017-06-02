package com.techfly.demo.activity.record;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.adpter.RechargHistoryAdapter;
import com.techfly.demo.bean.RechargeHistoryBean;
import com.techfly.demo.bean.User;
import com.techfly.demo.interfaces.ItemClickListener;
import com.techfly.demo.util.CommonUtils;
import com.techfly.demo.util.SharePreferenceUtils;
import com.techfly.demo.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/*
 * 充值记录
 */
public class RechargeHistoryActivity extends BaseActivity {

    @InjectView(R.id.recharge_number_tv)
    TextView recharge_number_tv;

    @InjectView(R.id.base_plv)
    PullToRefreshListView base_plv;

    private User mUser;
    private int mPage = 1;
	private int mSize = 10;
    private int mTotalRecord = 0;
    private boolean isRefresh = false;

    private RechargHistoryAdapter rechargHistoryAdapter = null;
    private List<RechargeHistoryBean.DataEntity.DatasEntity> datasEntityList = new ArrayList<RechargeHistoryBean.DataEntity.DatasEntity>();

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_recharge_history);

        ButterKnife.inject(this);

        initBaseView();
        //设置标题
        setBaseTitle("充值记录", 0);
        //返回按钮的点击事件
        initBackButton(R.id.top_back_iv);
        setTranslucentStatus(R.color.main_bg);

        initView();

        initLisener();

        initAdapter();

//        loadIntent();

        refresh();

//        loadData();

    }

    private void initView(){
        mUser = SharePreferenceUtils.getInstance(this).getUser();
    }


    /*private void loadIntent(){
        String money = getIntent().getStringExtra(Constant.CONFIG_INTENT_USER_RECHARGE_MONEY);
        recharge_number_tv.setText("充值记录:20");
    }*/

    /*private void loadData(){
        List<RechargeHistoryBean.DataEntity.DatasEntity> list = new ArrayList<RechargeHistoryBean.DataEntity.DatasEntity>();
        for(int i = 0;i < 20;i++){
            //int id, String create_time, String money, String type
            RechargeHistoryBean.DataEntity.DatasEntity data = new RechargeHistoryBean.DataEntity.DatasEntity(i,"2016.08.1"+i,(i++)+"","微信");
            list.qq_add(data);
        }
        rechargHistoryAdapter.addAll(list);
    }*/

    private void initLisener(){
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

    private void initAdapter() {
        rechargHistoryAdapter = new RechargHistoryAdapter(RechargeHistoryActivity.this, datasEntityList);
        base_plv.setAdapter(rechargHistoryAdapter);

        rechargHistoryAdapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
            }

            @Override
            public void onItemLongClick(View view, int postion) {
            }

            @Override
            public void onItemSubViewClick(View view, int postion) {
//				ToastUtil.DisplayToast(mContext,"跳转到充值");
                /*RechargeCardBean.DataEntity bean = (RechargeCardBean.DataEntity) rechargeOnlineAdapter.getItem(postion);

                Intent intent = new Intent(mContext, RechargeActivity.class);
                intent.putExtra(Constant.CONFIG_INTENT_RECHARGE_MONEY, bean);
                startActivityForResult(intent, REQUESCODE_RECHARGE);*/
            }
        });
    }

    private void loadMore(){
        if(mUser != null){
            if(rechargHistoryAdapter.getCount() < mTotalRecord){
				mPage = mPage + 1;
                getRechargeHistoryListApi(mUser.getmId(), mUser.getmToken(), mPage, mSize,mUser.getiCode());
            }else{
                ToastUtil.DisplayToast(RechargeHistoryActivity.this, Constant.TOAST_MEG_LOADING_FINISH);
                base_plv.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        base_plv.onRefreshComplete();
                    }
                },200);
            }
        }
    }

    private void refresh(){
        if(mUser != null){
			mPage = 1;
			mSize = 10;
            //清除之前的数据，加载最新的数据
            rechargHistoryAdapter.clearAll();
            getRechargeHistoryListApi(mUser.getmId(), mUser.getmToken(), mPage, mSize,mUser.getiCode());
        }
    }

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);

//        base_load.setVisibility(View.INVISIBLE);
        result = CommonUtils.removeBrace(result);
        base_plv.onRefreshComplete();

        if(type == Constant.API_ANALYZE_SUCCESS){
            try{
                Gson gson = new Gson();
                RechargeHistoryBean data = gson.fromJson(result,RechargeHistoryBean.class);
                if(data != null){
                    mTotalRecord = data.getData().getTotalRecord();
                    recharge_number_tv.setText("充值记录:"+mTotalRecord);

                    rechargHistoryAdapter.addAll(data.getData().getDatas());
                }else{
                    ToastUtil.DisplayToast(RechargeHistoryActivity.this,Constant.TOAST_MEG_ANALYZE_ERROR);
                }
            }catch (Exception e){
                ToastUtil.DisplayToast(RechargeHistoryActivity.this,Constant.TOAST_MEG_REBACK_ERROR);
                e.printStackTrace();
            }
        }
    }



}
