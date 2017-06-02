package com.techfly.demo.activity.user;

import android.os.Bundle;

import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * 用户协议
 */
public class UserAgreementActivity extends BaseActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_agreement);
        ButterKnife.inject(this);

        initBaseView();

        setBaseTitle(getResources().getText(R.string.user_agreement).toString(), 0);

        initBackButton(R.id.top_back_iv);
        setTranslucentStatus(R.color.main_bg);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
