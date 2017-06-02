package com.techfly.demo.activity.order;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.adpter.LogisticsInfoLvAdapter;
import com.techfly.demo.bean.EventBean;
import com.techfly.demo.bean.LogisticsDetailBean;
import com.techfly.demo.bean.LogisticsInfoBean;
import com.techfly.demo.bean.ReasultBean;
import com.techfly.demo.bean.User;
import com.techfly.demo.util.DialogUtil;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.SharePreferenceUtils;
import com.techfly.demo.util.ToastUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * 物流查看
 */
public class OrderLogisticActivity extends BaseActivity {



    @InjectView(R.id.order_logis_name_et)
    EditText order_logis_name_et;
    @InjectView(R.id.order_logis_code_et)
    EditText order_logis_code_et;

    @InjectView(R.id.order_logis_lv)
    ListView order_logis_lv;

    @InjectView(R.id.order_logis_tv)
    TextView order_logis_tv;

    @InjectView(R.id.submit_btn)
    Button submit_btn;

    private String m_logicName = "";//物流公司
    private String m_logicCode = "";//物流单号

    private String m_curStatus = "";

    private User mUser = null;
    private String m_getIntentId = "";
    private Boolean m_getIntentIsCheck = false;

    private LogisticsInfoLvAdapter adapter;
    private List<LogisticsDetailBean.DataEntity.InfosEntity> datasEntities = new ArrayList<LogisticsDetailBean.DataEntity.InfosEntity>();

    private List<String> logisCodes = new ArrayList<String>();
    private List<String> logisCompanys = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_order_logistic);
        ButterKnife.inject(this);

        setTranslucentStatus(R.color.main_bg);

        //注册EventBus
        EventBus.getDefault().register(this);

        initBaseView();
        initBackButton(R.id.top_back_iv);
        setBaseTitle("物流查看", 0);

        initView();

        initAdapter();

        loadIntent();

    }

    private void initView() {

        Collections.addAll(logisCodes, getResources().getStringArray(R.array.exp_no));
        Collections.addAll(logisCompanys, getResources().getStringArray(R.array.exp_name));

        mUser = SharePreferenceUtils.getInstance(this).getUser();
    }


    private void initAdapter() {
        adapter = new LogisticsInfoLvAdapter(this, datasEntities);
        order_logis_lv.setAdapter(adapter);
    }

    private void loadIntent() {
        m_getIntentId = getIntent().getStringExtra(Constant.CONFIG_INTENT_ORDER_ID);
        m_getIntentIsCheck = getIntent().getBooleanExtra(Constant.CONFIG_INTENT_IS_MODIFY,false);

        if(!m_getIntentIsCheck){
            submit_btn.setVisibility(View.GONE);
        }

        showProcessDialog();
        getOrderLogisInfoApi(mUser.getmId(), mUser.getmToken(), m_getIntentId);
    }

    private void updateLogics(LogisticsInfoBean.DataEntity data){
        m_logicName = data.getLogistics_company();
        m_logicCode = data.getLogistics_no();

        if(TextUtils.isEmpty(m_logicName) && TextUtils.isEmpty(m_logicCode)){
            ToastUtil.DisplayToast(this,"此订单物流信息丢失!");
        }

        order_logis_name_et.setText(m_logicName);
        order_logis_code_et.setText(m_logicCode);

        m_logicName = getLogiCompanyCode(logisCompanys, logisCodes, m_logicName);

        LogsUtil.normal("m_logicName=" + m_logicName + ",m_logicCode=" + m_logicCode);

        if(!TextUtils.isEmpty(m_logicName) && !TextUtils.isEmpty(m_logicCode)){
            getLogisProgressInfoApi(mUser.getmId(), mUser.getmToken(), m_logicName, m_logicCode);
        }
    }

    private String getLogiCompanyCode(List<String> companyNameList,List<String> companyCodeList,String curCompany){
        try{
            String result = "";
            int index = companyNameList.indexOf(curCompany);
            result = companyCodeList.get(index);
            return result;
        }catch (Exception e){
            ToastUtil.DisplayToast(this,"找不到该物流公司,物流查询失败!");
            e.printStackTrace();
            return "";
        }
    }

    @OnClick(R.id.submit_btn)
    public void jumpToSubmit() {
//        ToastUtil.DisplayToast(this,"点击了修改...");
        m_logicName = order_logis_name_et.getEditableText().toString();
        m_logicCode = order_logis_code_et.getEditableText().toString();

        if(TextUtils.isEmpty(m_logicName) || TextUtils.isEmpty(m_logicCode)){
            ToastUtil.DisplayToast(this, "物流公司或物流单号不能为空!");
            return;
        }


        DialogUtil.showDeleteDialog(this, "温馨提示", "您确认要修改物流信息?", EventBean.EVENT_CONFIRM_SUBMIT, "");
    }

    public void onEventMainThread(EventBean bean) {
        if (bean.getAction().equals(EventBean.EVENT_REFRESH_UI)) {
            initView();
        }
        if (bean.getAction().equals(EventBean.EVENT_CONFIRM_SUBMIT)) {
            showProcessDialog();
            postModifyLogisticInfoApi(mUser.getmId(), mUser.getmToken(), m_getIntentId, m_logicName, m_logicCode);
        }
        if (bean.getAction().equals(EventBean.EVENT_CLOSE_CURRENT_ACTIVITY)) {
           this.finish();
        }
    }

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);

        closeProcessDialog();

        try {
            if (type == Constant.API_GET_ORDER_LOGIS_INFO_SUCCESS) {
                LogsUtil.normal("物流信息");
                Gson gson = new Gson();
                LogisticsInfoBean data = gson.fromJson(result, LogisticsInfoBean.class);
                if (data != null) {
                    updateLogics(data.getData());
                }
            }

            if (type == Constant.API_GET_ORDER_LOGIS_PROGRESS_SUCCESS) {
                Gson gson = new Gson();
                LogisticsDetailBean data = gson.fromJson(result, LogisticsDetailBean.class);
                if (data != null) {
                    List<LogisticsDetailBean.DataEntity.InfosEntity> list = data.getData().getInfos();
                    Collections.reverse(list);
                    adapter.addAll(list);

                    if(data.getData().getInfos().size() == 0){
                        order_logis_tv.setText("物流轨迹:暂无此订单物流信息");
                    }
                }
            }

            if (type == Constant.API_POST_MODIFY_ORDER_LOGIS_SUCCESS) {
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
