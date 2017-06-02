package com.techfly.demo.activity.order;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.bean.EventBean;
import com.techfly.demo.bean.ReasultBean;
import com.techfly.demo.bean.User;
import com.techfly.demo.util.DialogUtil;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.SharePreferenceUtils;
import com.techfly.demo.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by wang on 2016/3/19.
 */
public class DeliverDetailActivity extends BaseActivity {

    /*@InjectView(R.id.detail_date_tv)
    TextView detail_date_tv;
    @InjectView(R.id.detail_name_tv)
    TextView detail_name_tv;
    @InjectView(R.id.detail_content_tv)
    TextView detail_content_tv;
    @InjectView(R.id.detail_money_tv)
    TextView detail_money_tv;*/

    @InjectView(R.id.detail_logistics_name_et)
    EditText detail_logistics_name_et;
    @InjectView(R.id.detail_logistics_number_et)
    EditText detail_logistics_number_et;

    @InjectView(R.id.submit_btn)
    Button submit_btn;

    private User mUser;
//    private ShopOrderListBean.DataEntity.DatasEntity m_getIntentBean = null;
//    private String m_getIntentDate = "";
//    private String m_getIntentName = "";
//    private String m_getIntentPrice = "";

    private String m_getIntentId = "";


    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_order_deliver_detail);
        //获取用户信息的方法
        ButterKnife.inject(this);
        EventBus.getDefault().register(this);

        setTranslucentStatus(R.color.main_bg);
        initBaseView();
        setBaseTitle("发货", 0);
        initBackButton(R.id.top_back_iv);

        initView();

        loadIntent();
    }

    private void initView() {
        mUser = SharePreferenceUtils.getInstance(this).getUser();
    }

    private void loadIntent() {

        m_getIntentId = getIntent().getStringExtra(Constant.CONFIG_INTENT_ORDER_ID);
//        m_getIntentDate = getIntent().getStringExtra(Constant.CONFIG_INTENT_ORDER_DATE);
//        m_getIntentName = getIntent().getStringExtra(Constant.CONFIG_INTENT_ORDER_NAME);
//        m_getIntentPrice = getIntent().getStringExtra(Constant.CONFIG_INTENT_ORDER_PRICE);
//        updateView();
    }

    private void updateView() {
//        detail_date_tv.setText(m_getIntentDate);
//        detail_name_tv.setText(m_getIntentName);
//        detail_money_tv.setText("¥" + m_getIntentPrice);
    }

    @OnClick(R.id.submit_btn)
    public void submit() {

        String logic_company = detail_logistics_name_et.getEditableText().toString();
        String logic_number = detail_logistics_number_et.getEditableText().toString();

        LogsUtil.normal("logic_company="+logic_company);

        if(TextUtils.isEmpty(logic_company) || TextUtils.isEmpty(logic_number)){
            ToastUtil.DisplayToast(this, "物流公司或物流单号不能为空!");
            return;
        }

        showProcessDialog();
        postOrderDeliverInfoApi(mUser.getmId(), mUser.getmToken(),m_getIntentId,logic_company,logic_number);
    }

    public void onEventMainThread(EventBean bean) {
        if (bean.getAction().equals(EventBean.EVENT_CLOSE_CURRENT_ACTIVITY)) {
            setResult(RESULT_OK);
            this.finish();
        }
    }


    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);

        closeProcessDialog();

        if (type == Constant.API_REQUEST_SUCCESS) {

            try {
                Gson gson = new Gson();
                ReasultBean data = gson.fromJson(result,ReasultBean.class);
                if(data != null){
                    DialogUtil.showSuccessDialog(this, data.getData(), EventBean.EVENT_CLOSE_CURRENT_ACTIVITY);
                }
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        }

        if (type == Constant.API_GET_FARM_ORDER_LOGIS_INFO_SUCCESS) {
            try {
                Gson gson = new Gson();
                /*LogicBean data = gson.fromJson(result,LogicBean.class);
                if(data != null){
                    detail_logistics_name_et.setText(data.getData().getLogistics_company());
                    detail_logistics_number_et.setText(data.getData().getLogistics_no());
                }*/
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
