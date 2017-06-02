package com.techfly.demo.activity.base;


import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.techfly.demo.R;
import com.techfly.demo.interfaces.GetResultCallBack;
import com.techfly.demo.util.DialogUtil;
import com.techfly.demo.util.NetWorkUtil;
import com.techfly.demo.util.PreferenceUtil;
import com.techfly.demo.util.StatusBarUtil;
import com.techfly.demo.util.SystemBarTintManager;
import com.techfly.demo.util.ToastUtil;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;


/**
 * BaseActivity-AppCompatActivity
 */
public class BaseAppCompatActivity extends SwipeBackActivity implements GetResultCallBack,SwipeBackActivityBase {

    public ImageView back_iv;
    public TextView title_tv;
    public TextView right_tv;
    public ImageView right_iv;

    public Dialog loadDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadDialog = DialogUtil.loadingDialog(BaseAppCompatActivity.this);

        initBaseView();
    }

    public void initBaseView() {

        back_iv = (ImageView) findViewById(R.id.top_back_iv);
        title_tv = (TextView) findViewById(R.id.top_title_tv);
        right_iv = (ImageView) findViewById(R.id.top_right_iv);
        right_tv = (TextView) findViewById(R.id.top_right_tv);
    }

    public void showProcessDialog() {
        loadDialog.show();
    }

    public void closeProcessDialog() {
        if (loadDialog != null && loadDialog.isShowing()) {
            loadDialog.dismiss();
        }
    }

    /*
     * 结束事件
     */
    protected void initBackButton(int resid) {
        ImageView btnBack = (ImageView) findViewById(resid);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /*
     * 设置标题
     */
    public void setBaseTitle(String mTitle, int rTitle) {
        title_tv.setText(mTitle);
        if (rTitle == 0) {
            right_tv.setVisibility(View.INVISIBLE);
        } else {
            right_tv.setVisibility(View.VISIBLE);
        }
    }

    public boolean checkLogin(Context context) {
        if (!PreferenceUtil.getBooleanSharePreference(context, Constant.CONFIG_IS_LOGIN, false)) {
            DialogUtil.showLoginDialog(context);
            return false;
        }
        return true;
    }

    public void setRightTitle(String mTitle, String rTitle) {
        title_tv.setText(mTitle);
        right_tv.setVisibility(View.VISIBLE);
        right_tv.setText(rTitle);
    }

    /*
     * 设置状态栏背景状态
	 */
    public void setTranslucentStatus(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(color);//状态栏无背景

        int getSystemType = StatusBarUtil.StatusBarLightMode(BaseAppCompatActivity.this);
//        LogsUtil.normal("getSystemType=" + getSystemType);
        StatusBarUtil.StatusBarLightMode(BaseAppCompatActivity.this, getSystemType);
    }

    private void isNetConnect() {
        if (NetWorkUtil.isNetWorkConnected(this)) {
        } else if (this != null) {
            ToastUtil.DisplayToast(this, Constant.TOAST_MEG_NETWORK_NULL);
        }
    }

    @Override
    public void getResult(String result, int type) {
        if (type == Constant.API_REQUEST_FAILURE) {
            ToastUtil.DisplayToast(this, Constant.TOAST_MEG_NETWORK_ERROR);
        }
    }

    public BaseAppCompatActivity() {
        super();
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return null;
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {

    }

    @Override
    public void scrollToFinishActivity() {

    }
}
