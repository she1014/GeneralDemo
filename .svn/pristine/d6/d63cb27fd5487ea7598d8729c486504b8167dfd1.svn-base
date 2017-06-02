package com.techfly.demo.activity.setting;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.google.gson.Gson;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.bean.ReasultBean;
import com.techfly.demo.bean.User;
import com.techfly.demo.util.SharePreferenceUtils;
import com.techfly.demo.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 设置配送费
 */
public class SetDeliveryActivity extends BaseActivity {


    @InjectView(R.id.delivery_money_et1)
    EditText delivery_money_et1;
    @InjectView(R.id.delivery_money_et2)
    EditText delivery_money_et2;

    private User mUser;
    private String m_getIntentFreight = "";

    private String m_free_money = "";
    private String m_freight_money = "";

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_set_delivery);

        initBaseView();
        ButterKnife.inject(this);
        setBaseTitle(getResources().getString(R.string.mine_shop_delivery), 0);
        setTranslucentStatus(R.color.main_bg);

        initView();
        //返回按钮的点击事件
        initBackButton(R.id.top_back_iv);
        loadIntent();
    }

    private void initView() {
        mUser = SharePreferenceUtils.getInstance(SetDeliveryActivity.this).getUser();

        delivery_money_et1.setText(Constant.CURRENT_SHOP_FREE);
        delivery_money_et2.setText(Constant.CURRENT_SHOP_DELIVERY);
    }

    private void loadIntent() {
        m_getIntentFreight = getIntent().getStringExtra(Constant.CONFIG_INTENT_CONTENT);
//        delivery_money_et2.setText(m_getIntentFreight);
    }

    @OnClick(R.id.delivery_clear_iv1)
    public void confirmDelete1() {
        delivery_money_et1.setText("");
    }

    @OnClick(R.id.delivery_clear_iv2)
    public void confirmDelete2() {
        delivery_money_et2.setText("");
    }

    @OnClick(R.id.save_btn)
    public void confirmSave() {

        m_free_money = delivery_money_et1.getEditableText().toString();
        m_freight_money = delivery_money_et2.getEditableText().toString();

        if (TextUtils.isEmpty(m_freight_money) || TextUtils.isEmpty(m_free_money)) {
            ToastUtil.DisplayToast(this, Constant.TOAST_MEG_ERROR_EMPTY);
            return;
        }

        showProcessDialog();
        postSetDeliveryMoneyApi(mUser.getmId(), mUser.getmToken(),m_free_money, m_freight_money);
    }

    @Override
    public void getResult(String result, int type) {

        closeProcessDialog();

        if (type == Constant.API_REQUEST_SUCCESS) {
            try {
                Gson gson = new Gson();
                ReasultBean data = gson.fromJson(result, ReasultBean.class);
                if (data != null && data.getCode().equals("000")) {

                    Constant.CURRENT_SHOP_DELIVERY = m_freight_money;
                    ToastUtil.DisplayToast(this, data.getData());

                    setResult(RESULT_OK);
                    this.finish();

                } else {
                    ToastUtil.DisplayToast(this, "" + data.getData());
                }
            } catch (Exception e) {
                ToastUtil.DisplayToastDebug(this, Constant.TOAST_MEG_REBACK_ERROR);
                e.printStackTrace();
            }
        }
    }
}
