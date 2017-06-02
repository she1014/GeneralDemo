package com.techfly.demo.activity.user;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.bean.ReasultBean;
import com.techfly.demo.bean.User;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.SharePreferenceUtils;
import com.techfly.demo.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * 修改密码
 */
public class ModifyPassActivity extends BaseActivity {

    @InjectView(R.id.modify_pass_originEt)
    EditText modify_pass_originEt;
    @InjectView(R.id.modify_pass_newEt)
    EditText modify_pass_newEt;
    @InjectView(R.id.modify_pass_confirmEt)
    EditText modify_pass_confirmEt;

    private User mUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_modify_pass);
        ButterKnife.inject(this);

        setTranslucentStatus(R.color.main_bg);

        initBaseView();
        setBaseTitle("修改密码", 0);
        initBackButton(R.id.top_back_iv);

        initView();

//        closeProcessDialog();

        loadIntent();
    }

    private void initView(){
        mUser = SharePreferenceUtils.getInstance(this).getUser();
    }

    private void loadIntent(){
        String type = getIntent().getStringExtra(Constant.CONFIG_INTENT_TYPE);
        LogsUtil.normal("ModifyPass.type=" + type);
    }

    @OnClick(R.id.modify_confirmBtn)
    public void ConfirmModifyMsg(){
        String origin = modify_pass_originEt.getEditableText().toString();
        String newpwd = modify_pass_newEt.getEditableText().toString();
        String confirmpwd = modify_pass_confirmEt.getEditableText().toString();

        if(TextUtils.isEmpty(origin) || TextUtils.isEmpty(newpwd) || TextUtils.isEmpty(confirmpwd)){
            ToastUtil.DisplayToast(ModifyPassActivity.this, Constant.TOAST_MEG_ERROR_EMPTY);
            return;
        }
        if(newpwd.length() < 6 || confirmpwd.length() > 16){
            ToastUtil.DisplayToast(ModifyPassActivity.this, "密码长度有误!");
            return;
        }
        if(!newpwd.equals(confirmpwd)){
            ToastUtil.DisplayToast(ModifyPassActivity.this, Constant.TOAST_MEG_DIFFERENT_PASS);
            return;
        }

        postModifyPwdApi(mUser.getmId(),mUser.getmToken(), origin,newpwd);

    }

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);

        Log.i("TTSS", "result=" + result + ",type=" + type);
        if(type == Constant.API_REQUEST_SUCCESS){
            try{
                Gson gson = new Gson();
                ReasultBean data = gson.fromJson(result,ReasultBean.class);
                if(data != null && data.getCode().equals("000")){
                    ToastUtil.DisplayToast(ModifyPassActivity.this, "修改密码成功!", Toast.LENGTH_LONG);
                    this.finish();
                }else{
                    ToastUtil.DisplayToast(this, Constant.TOAST_MEG_ANALYZE_ERROR);
                }
            }catch(Exception e){
                e.printStackTrace();
                ToastUtil.DisplayToast(this, Constant.TOAST_MEG_REBACK_ERROR + "\n" + e.getMessage());
            }
        }
    }
}
