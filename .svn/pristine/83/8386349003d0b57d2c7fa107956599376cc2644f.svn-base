package com.techfly.demo.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.bean.ErrorBean;
import com.techfly.demo.bean.EventBean;
import com.techfly.demo.interfaces.GetResultCallBack;
import com.techfly.demo.network.AppClient;
import com.techfly.demo.util.DialogUtil;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.NetWorkUtil;
import com.techfly.demo.util.PreferenceUtil;
import com.techfly.demo.util.ToastUtil;

import de.greenrobot.event.EventBus;


public class BaseFragment extends Fragment implements GetResultCallBack {
    private String TAG = "BaseFragment";
    public View view;
    private Context mContext;
    public Dialog loadDialog;

    public ImageView back_iv;
    public TextView title_tv;
    public TextView right_tv;
    public ImageView right_iv;

    @Override
    public View getView() {
        return super.getView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_base_listview, null);
        mContext = getActivity();

        initBaseView();

        return view;
    }

    public void initBaseView() {
        back_iv = (ImageView) view.findViewById(R.id.top_back_iv);
        title_tv = (TextView) view.findViewById(R.id.top_title_tv);
        right_iv = (ImageView) view.findViewById(R.id.top_right_iv);
        right_tv = (TextView) view.findViewById(R.id.top_right_tv);
    }

    /*
     * 设置标题
     */
    public void setMidTitle(String mTitle) {
        title_tv.setText(mTitle);
    }

    public void showProcessDialog() {
        if (loadDialog == null) {
            loadDialog = DialogUtil.loadingDialog(getActivity());
            loadDialog.show();
        }
    }

    public boolean checkLogin(Context context) {
        if (!PreferenceUtil.getBooleanSharePreference(context, Constant.CONFIG_IS_LOGIN, false)) {
            DialogUtil.showLoginDialog(context);
            return false;
        } else {
            return true;
        }
    }

    public void closeProcessDialog() {
        if (loadDialog != null & loadDialog.isShowing()) {
            loadDialog.dismiss();
        }
    }


    @Override
    public void getResult(String result, int type) {

        LogsUtil.normal("BaseFragment.mContex---" + result+",type="+type);

        if (type == Constant.API_ERROR_REBACK) {
            Gson gson = new Gson();
            ErrorBean data = gson.fromJson(result, ErrorBean.class);
            if (data != null) {
                ToastUtil.DisplayToast(getActivity(), data.getData()+"");
            }
        }
    }

    private void isNetConnect() {
        if (NetWorkUtil.isNetWorkConnected(getActivity())) {
        } else if (getActivity() != null) {
            ToastUtil.DisplayToast(getActivity(), Constant.TOAST_MEG_NETWORK_NULL);
        }
    }

    private void isNetConnect(int type) {
        if (NetWorkUtil.isNetWorkConnected(getActivity())) {
        } else if (getActivity() != null) {
            ToastUtil.DisplayToast(getActivity(), Constant.TOAST_MEG_NETWORK_NULL);
            switch (type){
                case Constant.API_GET_CITY_LIST_SUCCESS:
                    EventBus.getDefault().post(new EventBean(EventBean.EVENT_GET_CACHE_CONTENT));
                    break;
                case Constant.API_GET_BANNER_LIST_SUCCESS:
                    EventBus.getDefault().post(new EventBean(EventBean.EVENT_GET_CACHE_CONTENT));
                    break;

            }
        }
    }

    public void getCitysListApi() {
        isNetConnect(Constant.API_GET_CITY_LIST_SUCCESS);
        AppClient.getCitysList(this);
    }

    public void getBannerListApi(String cid) {
        isNetConnect(Constant.API_GET_BAIKE_SUCCESS);
        AppClient.getBannerList(cid, this);
    }

    public void getClothListInfo(String cid, String cityId) {
        isNetConnect();
        AppClient.getClothListInfo(cid, cityId, this);
    }

    //获取我推荐的人的消费情况
    public void postMyRecommendDetailAPI(String size, String page, String uid, String token) {
        isNetConnect();
        AppClient.postMyRecommendDetail(size, page, uid, token, this);
    }

    //充值--提交充值卡
    public void postMyRechargeCardAPI(String cardNum, String uid, String token) {
        isNetConnect();
        AppClient.postMyRechargeCard(cardNum, uid, token, this);
    }

    public void getUncompletedOrderListApi(String uid, String token, int no, int size) {
        isNetConnect();
        AppClient.getUncompletedOrderList(uid, token, no, size, this);
    }

    //更多--检测更新
    public void postUpdateVersionInfoAPI(String versionCode) {
        isNetConnect();
        AppClient.postUpdateVersionInfo(versionCode, this);
    }

    //取消订单
    public void postCancelOrderApi(String uid, String token, String orderId) {
        isNetConnect();
        AppClient.postCancelOrder(uid, token, orderId, this);
    }


    //百科
    public void getBaikeInfoApi() {
        isNetConnect(Constant.API_GET_BAIKE_SUCCESS);
        AppClient.getBaikeInfo(this);
    }

    //用户信息
    public void getUserBaseInfoApi(String uid,String token) {
        isNetConnect();
        AppClient.getUserBaseInfo(uid, token, this);
    }

    //优惠卷信息
    public void getVoucherCardInfoApi(String uid,String token) {
        isNetConnect();
        AppClient.getVoucherCardInfo(uid, token, this);
    }

    //还原优惠券
    public void postRevertVoucherInfoApi(String uid, String token, String orderId,String voucharId) {
        isNetConnect();
        AppClient.postRevertVoucherInfo(uid, token, orderId, voucharId, this);
    }

    //获取定位地址信息
    public void getLocateCityInfoApi(String lat,String lng) {
        isNetConnect();
        AppClient.getLocateCityInfo(lat, lng, this);
    }

    public void getShopOrderListApi(String uid, String token, int no, int size, String status) {
        isNetConnect();
        AppClient.getShopOrderList(uid, token, no, size, status, this);
    }

    public void postDeliveryShopOrderApi(String uid, String token, String oId) {
        isNetConnect();
        AppClient.postDeliveryShopOrder(uid, token, oId, this);
    }

    public void getShopBaseInfoApi(String uid, String token) {
        isNetConnect();
        AppClient.getShopBaseInfo(uid, token, this);
    }

    public void getPlatformNoticeInfoApi(String uid, String token) {
        isNetConnect();
        AppClient.getPlatformNoticeInfo(uid, token, this);
    }

    public void postUploadAvatorApi(String uid, String token, String path) {
        isNetConnect();
        AppClient.postUploadAvator(uid, token, path, this);
    }

    public void postShopGoodStatusApi(String uid, String token, String oId, String status) {
        isNetConnect();
        AppClient.postShopGoodStatus(uid, token, oId, status, this);
    }

    public void getShopGoodsListApi(String uid, String token,String shopId, int no, int size, String status) {
        isNetConnect();
        AppClient.getShopGoodsList(uid, token, shopId, no, size, status, this);
    }

    public void postUserLogOutApi(String uid, String token) {
        isNetConnect();
        AppClient.postUserLogOut(uid, token, this);
    }

    public void postUploadPictureApi(String uid, String token, String path, String classify, Context context) {
        isNetConnect();
        AppClient.postUploadPicture(uid, token, path, classify, context, this);
    }

    public void postAcceptShopOrderApi(String uid, String token, String oId,String status) {
        isNetConnect();
        AppClient.postAcceptShopOrder(uid, token, oId, status, this);
    }

    public void getShopRefundOrderListApi(String uid, String token, int no, int size,String shopId,String status) {
        isNetConnect();
        AppClient.getShopRefundOrderList(uid, token, no, size, shopId, status, this);
    }

    public void postRefundStatusApi(String uid, String token, String rId,String status) {
        isNetConnect();
        AppClient.postRefundStatus(uid, token, rId, status, this);
    }

    public void postReturnRefundStatusApi(String uid, String token, String rId,String status,String money) {
        isNetConnect();
        AppClient.postReturnRefundStatus(uid, token, rId, status,money, this);
    }


}

