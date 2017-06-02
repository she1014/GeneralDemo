package com.techfly.demo.activity.base;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.techfly.demo.R;
import com.techfly.demo.bean.ErrorBean;
import com.techfly.demo.bean.StyleBean;
import com.techfly.demo.interfaces.GetResultCallBack;
import com.techfly.demo.network.AppClient;
import com.techfly.demo.util.AppManager;
import com.techfly.demo.util.DialogUtil;
import com.techfly.demo.util.NetWorkUtil;
import com.techfly.demo.util.PreferenceUtil;
import com.techfly.demo.util.StatusBarUtil;
import com.techfly.demo.util.SystemBarTintManager;
import com.techfly.demo.util.ToastUtil;

import java.util.List;


/**
 * BaseActivity-未添加侧滑效果
 */
public class BaseActivity3 extends Activity implements GetResultCallBack {

    public ImageView back_iv;
    public TextView title_tv;
    public TextView right_tv;
    public ImageView right_iv;

    public Dialog loadDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppManager.getAppManager().addActivity(this);

        loadDialog = DialogUtil.loadingDialog(BaseActivity3.this);

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
//        right_tv.setVisibility(View.GONE);
        if (rTitle == 0) {
        } else {
            right_iv.setVisibility(View.VISIBLE);
//            right_tv.setText(rTitle);
        }
    }

    public void setBaseTitle(String mTitle, String rTitle) {
        title_tv.setText(mTitle);
        right_tv.setVisibility(View.VISIBLE);
        right_tv.setText(rTitle);
    }

    public boolean checkLogin(Context context) {
        if (!PreferenceUtil.getBooleanSharePreference(context, Constant.CONFIG_IS_LOGIN, false)) {
            DialogUtil.showLoginDialog(context);
            return false;
        }
        return true;
    }

    public void setRightTitle(int mTitle, int rTitle) {
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

        int getSystemType = StatusBarUtil.StatusBarLightMode(BaseActivity3.this);
        StatusBarUtil.StatusBarLightMode(BaseActivity3.this, getSystemType);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (null != this.getCurrentFocus()) {
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void getResult(String result, int type) {
        Log.i("TTSS", "BaseActivity,result=" + result + ",type=" + type);
        if (type == Constant.API_REQUEST_FAILURE) {
            ToastUtil.DisplayToast(this, Constant.TOAST_MEG_NETWORK_ERROR);
        }
        if (type == Constant.API_ERROR_REBACK) {
            Gson gson = new Gson();
            ErrorBean data = gson.fromJson(result, ErrorBean.class);
            if (data != null) {
                ToastUtil.DisplayToast(this, data.getData() + "", Toast.LENGTH_LONG);
            }
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        AppManager.getAppManager().finishActivity(this);
        super.onDestroy();
    }

    private void isNetConnect() {
        if (NetWorkUtil.isNetWorkConnected(BaseActivity3.this)) {
        } else if (BaseActivity3.this != null) {
            ToastUtil.DisplayToast(BaseActivity3.this, Constant.TOAST_MEG_NETWORK_NULL);
        }
    }

    public void getPhoneCodeAPI(String phone) {
        isNetConnect();
        AppClient.getPhoneCode(phone, this);
    }

    public void postCodeLoginAPI(String phone, String code) {
        isNetConnect();
        AppClient.userLogin(phone, code, this);
    }

    public void postUserLoginApi(String phone, String pass,String jCode) {
        isNetConnect();
        AppClient.postUserLogin(phone, pass, jCode, this);
    }

    public void getBannerDetailApi(String pid) {
        isNetConnect();
        AppClient.getBannerDetail(pid, this);
    }

    //意见反馈
    public void postSuggestInfoApi(String uid, String token, String mes) {
        isNetConnect();
        AppClient.postSuggestInfo(uid, token, mes, this);
    }

    //推荐好友得现金--获取推荐人
    public void getRecommendInviterAPI(String name) {
        isNetConnect();
        AppClient.getRecommendInviter(name, this);
    }

    //推荐好友得现金-获取活动规则
    public void getRuleInstructionAPI() {
        isNetConnect();
        AppClient.getRuleInstruction(this);
    }

    //我的提成
    public void postConfirmWithdrawalAPI(String userName, String account, String type, String uid, String token) {
        isNetConnect();
        AppClient.postConfirmWithdrawal(userName, account, type, uid, token, this);
    }


    //更多--获取功能介绍
    public void getIntroduceFunctionInfoAPI() {
        isNetConnect();
        AppClient.getIntroduceFunctionInfo(this);
    }


    //更多--获取常见问题
    public void getCommonProblemInfoAPI() {
        isNetConnect();
        AppClient.getCommonProblemInfo(this);
    }


    //更多--检测更新
    public void postUpdateVersionInfoAPI(String  versionCode) {
        isNetConnect();
        AppClient.postUpdateVersionInfo(versionCode, this);
    }


    public void getCitysListApi() {
        isNetConnect();
        AppClient.getCitysList(this);
    }

    public void getAddressListApi(String uid, String token, int no, int size) {
        isNetConnect();
        AppClient.getAddressList(uid, token, no, size, this);
    }

    public void setDefaultAddressApi(String uid, String token, String aid) {
        isNetConnect();
        AppClient.setDefaultAddress(uid, token, aid, this);
    }

    public void postModifyAddressInfoApi(String uid, String token, String aid, String cityId, String name,
                                         String mobile, String address, String lat, String lng) {
        isNetConnect();
        AppClient.postModifyAddressInfo(uid, token, aid, cityId, name, mobile, address, lat, lng, this);
    }

    public void postDelAddressInfoApi(String uid, String token, String aid) {
        isNetConnect();
        AppClient.postDelAddressInfo(uid, token, aid, this);
    }

    public void getBookingTimeApi() {
        isNetConnect();
        AppClient.getBookingTime(this);
    }

    public void getCurrentTimeApi() {
        isNetConnect();
        AppClient.getCurrentTime(this);
    }

    public void postReserverInfoApi(String uid, String token, String username, String mobile, String address,
                                    String lat, String lng, String cid, String time, String remark,String addrId) {
        isNetConnect();
        AppClient.postReserverInfo(uid, token, username, mobile, address, lat, lng, cid, time, remark, addrId, this);
    }

    public void getCompletedOrderListApi(String uid, String token, int no, int size) {
        isNetConnect();
        AppClient.getCompletedOrderList(uid, token, no, size, this);
    }

    public void getOrderDetailInfoApi(String uid, String token, String orderId) {
        isNetConnect();
        AppClient.getOrderDetailInfo(uid, token, orderId, this);
    }

    public void getRefundOrderDetailApi(String uid, String token, String orderId) {
        isNetConnect();
        AppClient.getRefundOrderDetail(uid, token, orderId, this);
    }

    public void postPayBeforeInfoApi(String uid, String token, String orderId, String otherDiscount,
                                     String voucharId, String payType) {
        isNetConnect();
        AppClient.postPayBeforeInfo(uid, token, orderId, otherDiscount, voucharId, payType, this);
    }

    public void postPayAfterInfoApi(String uid, String token, String orderId,String voucharId) {
        isNetConnect();
        AppClient.postPayAfterInfo(uid, token, orderId, voucharId, this);
    }

    /*public void postBalanceAfterInfoApi(String uid, String token, String orderId,String voucharId) {
        isNetConnect();
        AppClient.postBalanceAfterInfo(uid, token, orderId, voucharId, this);
    }*/

    public void getVoucherListApi(String uid, String token, int no, int size) {
        isNetConnect();
        AppClient.getVoucherList(uid, token, no, size, this);
    }

    public void getUserInfoApi(String uid, String token) {
        isNetConnect();
        AppClient.getUserInfo(uid, token, this);
    }

    public void postOrderEvaInfoApi(String uid, String token, String orderId,String stars,String content) {
        isNetConnect();
        AppClient.postOrderEvaInfo(uid, token, orderId, stars, content, this);
    }

    public void postModifyNickApi(String uid, String token, String nickname) {
        isNetConnect();
        AppClient.postModifyNick(uid, token, nickname, this);
    }

    public void postUploadAvatorApi(String uid, String token, String path) {
        isNetConnect();
        AppClient.postUploadAvator(uid, token, path, this);
    }

    public void postVouchercardsBeforeApi(String uid, String token, String vid,String type,String money) {
        isNetConnect();
        AppClient.postVouchercardsBefore(uid, token, vid, type, money, this);
    }

    public void postVouchercardsAfterApi(String uid, String token, String pid,String type) {
        isNetConnect();
        AppClient.postVouchercardsAfter(uid, token, pid, type, this);
    }

    public void getBalanceHistoryApi(String uid, String token, int no, int size) {
        isNetConnect();
        AppClient.getBalanceHistory(uid, token, no, size, this);
    }

    public void postRevertVoucherInfoApi(String uid, String token, String orderId,String voucharId) {
        isNetConnect();
        AppClient.postRevertVoucherInfo(uid, token, orderId, voucharId, this);
    }

    public void getShopOrderListApi(String uid, String token, int no, int size, String status) {
        isNetConnect();
        AppClient.getShopOrderList(uid, token, no, size, status, this);
    }

    public void getFarmOrderLogisInfoApi(String uid, String token, String company,String code) {
        isNetConnect();
        AppClient.getFarmOrderLogisInfo(uid, token, company, code, this);
    }

    public void getRechargeHistoryListApi(String uid, String token, int no, int size,String sId) {
        isNetConnect();
        AppClient.getRechargeHistoryList(uid, token, no, size, sId, this);
    }

    public void getMemberHistoryListApi(String uid, String token, int no, int size,String sId) {
        isNetConnect();
        AppClient.getMemberHistoryList(uid, token, no, size, sId, this);
    }

    public void postModifyPwdApi(String uid, String token, String oldpwd, String newpwd) {
        isNetConnect();
        AppClient.postModifyPwd(uid, token, oldpwd, newpwd, this);
    }

    public void getCitysListApi(String province) {
        isNetConnect();
        AppClient.getCitysList(province, this);
    }

    public void getFruitCategoriesInfoApi(String sId){
        isNetConnect();
        AppClient.getFruitCategoriesInfo(sId, this);
    }

    public void getCategoryListApi(String sId,String cid){
        isNetConnect();
        AppClient.getCategoryList(sId, cid, this);
    }

    public void getGoodsListStatusApi() {
        isNetConnect();
        AppClient.getGoodsListStatus(this);
    }

    public void getProvinceListApi() {
        isNetConnect();
        AppClient.getProvinceList(this);
    }

    public void postReleaseGoodApi(String uid, String token,String shopId, String img, String images, String title, String descrip,
                                   String feature_labels, String text, String category_id, List<StyleBean> styleBeans){
        isNetConnect();
        AppClient.postReleaseGood(uid, token, shopId, img, images, title, descrip, feature_labels, text, category_id, styleBeans, this);
    }

    public void postGoodModifyInfoApi(String uid, String token,String gId, String img, String images, String title, String descrip,
                                   String feature_labels, String text, String category_id, List<StyleBean> styleBeans){
        isNetConnect();
        AppClient.postGoodModifyInfo(uid, token, gId, img, images, title, descrip, feature_labels, text, category_id, styleBeans, this);
    }


    public void getGoodsReviewsListApi(String uid,String token,int page,int size) {
        isNetConnect();
        AppClient.getGoodsReviewsList(uid, token, page, size, this);
    }

    public void postOrderDeliverInfoApi(String uid,String token,String oId,String company,String code) {
        isNetConnect();
        AppClient.postOrderDeliverInfo(uid, token, oId, company, code, this);
    }

    public void postModifyLogisticInfoApi(String uid,String token,String oId,String company,String code) {
        isNetConnect();
        AppClient.postModifyLogisticInfo(uid, token, oId, company, code, this);
    }

    public void getOrderReviewInfoApi(String uid,String token,String oId) {
        isNetConnect();
        AppClient.getOrderReviewInfo(uid, token, oId, this);
    }

    public void getOrderLogisInfoApi(String uid,String token,String oId) {
        isNetConnect();
        AppClient.getOrderLogisInfo(uid, token, oId, this);
    }

    public void getLogisProgressInfoApi(String uid, String token,String company,String code) {
        isNetConnect();
        AppClient.getLogisProgressInfo(uid, token, company, code, this);
    }

    public void postUploadPictureApi(String uid, String token, String path, String classify, Context context) {
        isNetConnect();
        AppClient.postUploadPicture(uid, token, path, classify, context, this);
    }

    public void getGoodsDetailApi(String uid,String token,String gId) {
        isNetConnect();
        AppClient.getGoodsDetail(uid, token, gId, this);
    }


    public void postSetDeliveryMoneyApi(String uid, String token, String freeMoney,String deliveryMoney) {
        isNetConnect();
        AppClient.postSetDeliveryMoney(uid, token, freeMoney,deliveryMoney, this);
    }

}
