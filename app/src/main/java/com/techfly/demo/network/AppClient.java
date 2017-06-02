package com.techfly.demo.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.squareup.okhttp.Request;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.bean.ErrorBean;
import com.techfly.demo.bean.EventBean;
import com.techfly.demo.bean.ImageBean;
import com.techfly.demo.bean.ImgBean;
import com.techfly.demo.bean.JsonKey;
import com.techfly.demo.bean.StyleBean;
import com.techfly.demo.interfaces.GetBeanCallBack;
import com.techfly.demo.interfaces.GetResultCallBack;
import com.techfly.demo.util.DisplayUtil;
import com.techfly.demo.util.FileUtils;
import com.techfly.demo.util.ImageUtils;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.OkHttpClientManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;

/**
 * @author ssm
 */
public class AppClient {

    public static GetResultCallBack mCallback;

    public static AppClient getInstance() {
        return new AppClient();
    }

    public static final String TAG = "AppClient";

//    public static final String BASE_URL = "http://192.168.0.118:8080/weishop_app/";//测试服务器
//    public static final String BASE_URL = "http://192.168.1.80/weixin/";//李陆服务器
    public static final String BASE_URL = "http://114.55.250.185/weishop_app/";//官网服务器

    public static final String BASE_PIC = "http://121.43.158.189/liuTai";//图片地址

    //第三方登陆
    public static final String API_GET_LOGIN_INFO = BASE_URL + "/user/sns?snsid=%s";
    public static final String API_GET_WEIXIN_OPENID = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";

    //用户个人信息
    public static final String API_GET_USER_BASE_INFO = BASE_URL + "user/personInfo?lt-id=%s&lt-token=%s";

    //充值
    public static final String API_POST_RECHARGE_INFO = BASE_URL + "user/charge?";

    //获取验证码
    public static final String API_GET_PHONE_CODE = BASE_URL + "user/vercode?name=%s&type=2";
    //验证码登陆
    public static final String API_POST_LOGIN_INFO = BASE_URL + "user/loginByCode?";
    //密码登陆
    public static final String API_POST_USER_LOGIN = BASE_URL + "manageUser/login?";
    //获取首页Banner列表
    public static final String API_GET_BANNER_LIST = BASE_URL + "user/imgs?city=%s";
    //获取首页Banner详情
    public static final String API_GET_BANNER_DETAIL = BASE_URL + "user/getImgDetail?id=%s";
    //获取用户信息
    public static final String API_GET_USER_INFO = BASE_URL + "user/info?lt-id=%s&lt-token=%s";
    //洗衣百科
    public static final String API_GET_BAIKE_INFO = BASE_URL + "system/baike?";

    //意见反馈  拼接URL BASE_URL+ user/feedback?
    public static final String API_POST_SUGGEST_INFO = BASE_URL + "user/feedback?";

    //获取洗衣计价标题相关【0洗衣，6洗鞋，5奢侈品，7洗家纺，8精洗】
    public static final String API_GET_WASH_CLOTHES_TITLE = BASE_URL + "cleanPrice/getTitle?type=%s";
    //获取衣服价格列表
    public static final String API_GET_CLOTHES_LIST_INFO = BASE_URL + "cleanPrice/list?categoryId=%s&city=%s";
    //获取预约时间
    public static final String API_GET_BOOKING_TIME = BASE_URL + "cleanReserve/rsvTime?";

    //设置默认地址
    public static final String API_GET_ADDRESS_DEFAULT_URL = BASE_URL + "user/setaddress?";
    //获取地址列表
    public static final String API_GET_ADDRESS_URL = BASE_URL + "user/addresses?lt-id=%s&lt-token=%s&no=%s&size=%s";
    //新增地址
    public static final String API_POST_ADD_ADDRESS = BASE_URL + "user/doaddress?";
    //删除地址
    public static final String API_POST_DEL_ADDRESS = BASE_URL + "user/deladdress?";

    //立即预约
    public static final String API_POST_RESERVER_INFO = BASE_URL + "cleanReserve/reserve?";
    //取消预约
    public static final String API_POST_CANCEL_ORDER = BASE_URL + "cleanReserve/cancel?";
    //获取未完成订单-列表
    public static final String API_GET_UNCOMPLETED_ORDER = BASE_URL + "cleanReserve/ordersForUser?lt-id=%s&lt-token=%s&pageNo=%s&pageSize=%s";
    //获取已完成订单-列表
    public static final String API_GET_COMPLETED_ORDER = BASE_URL + "cleanReserve/ordersForUser?lt-id=%s&lt-token=%s&pageNo=%s&pageSize=%s&status=FINISHED";
    //获取订单详情
    public static final String API_GET_ORDER_DETAIL_INFO = BASE_URL + "manageOrder/details?lt-id=%s&lt-token=%s&order_id=%s";
    //订单评价
    public static final String API_POST_ORDER_EVALUATION = BASE_URL + "system/comment?";

    //获取推荐有奖--活动规则
    public static final String API_GET_RELUS = BASE_URL + "spread/getActivityRules?";


    //我的优惠卷
    public static final String API_GET_MY_VOUCHER_INFO = BASE_URL + "user/myvouchers?lt-id=%s&lt-token=%s&no=%s&size=%s";

    //优惠券还原-支付失败调用
    public static final String API_POST_REVERT_VOUCHER = BASE_URL + "cleanReserve/alipayFailed?";

    //我推荐的人
    public static final String API_POST_MYRECOMMENDDETAIL_INFO = BASE_URL + "spread/getInvitedByMe?";

    //我推荐人获的提成明细
    public static final String API_POST_MYREWARDDETAIL_INFO = BASE_URL + "spread/getRewards?";

    //我的提成---我的奖励
    public static final String API_POST_SUMREWARD_INFO = BASE_URL + "spread/getSumRewards?";

    //提现
    public static final String API_POST_WITHDRAW_INFO = BASE_URL + "cleanReserve/withdraw?";

    public static final String API_GET_H5_URL = BASE_URL + "spread/getRegURL?name=%s";

    //充值--充值卡
    public static final String API_POST_RECHARGE_CARD = BASE_URL + "user/chargeByCard?";

    //更多--功能介绍
    public static final String INTRODUCE_FUNCTION_INFO = BASE_URL + "system/introduce?";

    //更多--常见问题
    public static final String COMMOM_PROBLEM_INFO = BASE_URL + "system/help?";

    //更多-检测更新
    public static final String UPDATA_VERSION_INFO = BASE_URL + "system/upgrade?";

    //调起支付接口之前首先调用接口
    public static final String API_POST_PAY_BEFORE_INFO = BASE_URL + "cleanReserve/cleanRsvPay?";
    //支付成功回调接口
    public static final String API_POST_PAY_AFTER_INFO = BASE_URL + "cleanReserve/alipaySuccess?";
    //余额支付后调用
//    public static final String API_POST_BALANCE_AFTER_INFO = BASE_URL + "cleanReserve/balancePay?";

    //修改昵称
    public static final String API_POST_NICK_NAME_INFO = BASE_URL + "user/modifynickname?";
    //上传头像
    public static final String API_POST_AVATAR_INFO = BASE_URL + "manageUser/editAvatar?";

    //在线充值列表
    public static final String API_GET_VOUCHERCARDS_INFO = BASE_URL + "user/vouchercards?lt-id=%s&lt-token=%s";
    //在线充值前
    public static final String API_POST_VOUCHERCARDS_BEFORE = BASE_URL + "user/charge?";
    //在线充值后
    public static final String API_POST_VOUCHERCARDS_AFTER = BASE_URL + "common/payData?";
    //充值记录
    public static final String API_GET_BALANCE_HISTORY_URL = BASE_URL +"user/moneyrecords?lt-id=%s&lt-token=%s&no=%s&size=%s";

    //根据经纬度获取城市信息
    public static final String API_GET_CITY_INFO_BY_LATANDLNG = BASE_URL + "user/getUserCity?lat=%s&lng=%s";

    //店铺订单列表-不看申请退款中的订单和不看同意退款的订单
    public static final String API_GET_SHOP_ORDER_LIST = BASE_URL + "manageOrder/list?lt-id=%s&lt-token=%s&pageNo=%s&size=%s&status=%s&refund_status=REFUND_APPLY,REFUNDED";
    //店铺发货
    public static final String API_POST_SHOP_DELIVERY_CONFIRM = BASE_URL + "shop/deliveryConfirm?";

    //查询本月收益,今日收益 (营业额)(物流端)
    public static final String API_GET_SHOP_BASE_INFO = BASE_URL + "manageUser/getProfit?lt-id=%s&lt-token=%s";
    //平台公告展示(物流端)
    public static final String API_GET_PLATFORM_NOTICE_INFO = BASE_URL + "shop/platformNotice?lt-id=%s&lt-token=%s";

    //充值记录(物流端)
    public static final String API_GET_RECHARGE_HISTORY_URL = BASE_URL +"shop/rechargeList?lt-id=%s&lt-token=%s&pageNo=%s&size=%s&shop_id=%s";
    //会员记录(物流端)
    public static final String API_GET_MEMBER_HISTORY_URL = BASE_URL +"shop/userList?lt-id=%s&lt-token=%s&pageNo=%s&size=%s&shop_id=%s";

    //更换密码(需要旧密码验证)(物流端)
    public static final String API_POST_MODIFY_PASSWORD = BASE_URL +"manageUser/editPassword?";

    //商品状态列表
    public static final String API_GET_GOODS_LIST_STATUS = BASE_URL + "goods/list_status?";
    //省份列表
    public static final String API_GET_PROVINCE_LIST = BASE_URL + "city/provinces?";
    //获取城市列表
    public static final String API_GET_CITYS_LIST = BASE_URL + "city/list?province=%s";

    //商品评论-列表-针对店铺的评论
    public static final String API_GET_SHOPS_REVIEWS_LIST = BASE_URL + "orderReview/list?lt-id=%s&lt-token=%s&pageNo=%s&size=%s";

    //查询水果类别
    public static final String API_GET_FRUIT_CATEGORIES = BASE_URL + "category/queryFirstClass?shop_id=%s";
    //类别查询详情
    public static final String API_GET_CATEGORY_LIST = BASE_URL + "category/querySecondClass?shop_id=%s&super_id=%s";
    //商品发布
    public static final String API_POST_GOODS_RELEASE = BASE_URL + "goods/qq_add?";
    //商品修改
    public static final String API_POST_GOODS_MODIFY = BASE_URL + "goods/edit?";

    //商品编辑（修改商品状态）商品状态 NORMAL 正常，OFF_THE_SHELF 下架，DELETED 删除 传英文
    public static final String API_POST_GOODS_EDIT_STATUS = BASE_URL + "goods/edit?";
    //查询店铺水果
    public static final String API_GET_SHOP_GOODS_LIST = BASE_URL + "goods/sellerList?lt-id=%s&lt-token=%s&shop_id=%s&pageNo=%s&size=%s&status=%s";//NORMAL 正常，OFF_THE_SHELF 下架，DELETED 删除
    //商品种类数查询
    public static final String API_GET_GOODS_KIND_NUM = BASE_URL + "goods/count?lt-id=%s&lt-token=%s&shop_id=%s";

    //订单发货(物流端)
    public static final String API_POST_ORDER_DELIVER_INFO = BASE_URL + "manageOrder/deliver?";
    //修改物流信息 (物流端)
    public static final String API_POST_MODIFY_ORDER_LOGIS = BASE_URL + "manageOrder/editLogistics?";

    //注销-退出登录
    public static final String API_POST_LOGOUT_INFO = BASE_URL + "manageUser/loginOut?";

    //评价查看(物流端)
    public static final String API_GET_ORDER_REVIEW_INFO = BASE_URL + "manageOrder/review?lt-id=%s&lt-token=%s&order_id=%s";
    //物流查看(物流端)
    public static final String API_GET_ORDER_LOGIS_INFO = BASE_URL + "manageOrder/logistics?lt-id=%s&lt-token=%s&order_id=%s";
    //获取订单物流进度
    public static final String API_GET_ORDER_LOGIS_PROGRESS = BASE_URL + "logisticsInfo/info?lt-id=%s&lt-token=%s&express_company=%s&express_code=%s";

    //上传图片
    public static final String API_POST_PICTURE_INFO = BASE_URL + "upload/upload?";
    //商品详情
    public static final String API_GET_GOODS_DETAIL = BASE_URL + "goods/sellerDetail?lt-id=%s&lt-token=%s&goods_id=%s";

    //设置配送费
    public static final String API_POST_SHOP_DELIVERY_MONEY = BASE_URL + "shop/editFreight?";

    //接单(物流端)
    public static final String API_POST_SHOP_ACCEPT_ORDER = BASE_URL + "manageOrder/accept?";
    //退款列表(物流端)
    public static final String API_GET_SHOP_REFUND_LIST = BASE_URL + "manageOrder/refundList?lt-id=%s&lt-token=%s&pageNo=%s&size=%s&shop_id=%s&status=%s";

    //退款处理(物流端)  REFUNDED-同意；REFUND_DENY-拒绝
    public static final String API_POST_SHOP_REFUND_STATUS = BASE_URL + "manageOrder/refundMoney?";
    //退款详情(物流端)
    public static final String API_GET_ORDER_DETAIL_INFO_REFUND = BASE_URL + "manageOrder/refundDetail?lt-id=%s&lt-token=%s&order_id=%s";

    //退货退款处理(物流端)
    public static final String API_POST_SHOP_RETURN_REFUND = BASE_URL + "manageOrder/refund?";

    //获取手机验证码
    public static void getPhoneCode(String phone, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_PHONE_CODE, phone);
        get(URL, Constant.API_GET_PHONE_CODE_SUCCESS);
    }



    //获取城市列表
    public static void getCitysList(GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_CITYS_LIST);
        get(URL, Constant.API_GET_CITY_LIST_SUCCESS);
    }

    //验证码登录
    public static void userLogin(String username, String code, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put("name", username);
        map.put("code", code);
        post(API_POST_LOGIN_INFO, map, Constant.API_POST_CODE_LOGIN_SUCCESS);
    }

    //用户登录
    public static void postUserLogin(String username, String pass,String jpushCode, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.MOBILE, username);
        map.put(JsonKey.UserKey.PASS, pass);
        map.put(JsonKey.UserKey.JPUSHCODE, jpushCode);
        post(API_POST_USER_LOGIN, map, Constant.API_POST_CODE_LOGIN_SUCCESS);
    }

    //获取Banner列表
    public static void getBannerList(String cid, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_BANNER_LIST, cid);
        get(URL, Constant.API_GET_BANNER_LIST_SUCCESS);
    }

    //获取Banner详情
    public static void getBannerDetail(String pid, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_BANNER_DETAIL, pid);
        get(URL, Constant.API_ANALYZE_SUCCESS);
    }

    //获取用户信息
    public static void getUserInfo(String uid, String token, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_USER_INFO, uid, token);
        get(URL, Constant.API_GET_USER_BALANCE_SUCCESS);
    }

    //获取店铺信息
    public static void getShopBaseInfo(String uid, String token, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_SHOP_BASE_INFO, uid, token);
        get(URL, Constant.API_GET_SHOP_BASE_INFO_SUCCESS);
    }

    //平台公告展示(物流端)
    public static void getPlatformNoticeInfo(String uid, String token, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_PLATFORM_NOTICE_INFO, uid, token);
        get(URL, Constant.API_GET_PLATFORM_NOTICE_INFO_SUCCESS);
    }

    //充值记录(物流端)
    public static void getRechargeHistoryList(String uid, String token, int no, int size,String sId, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_RECHARGE_HISTORY_URL, uid, token, no, size,sId);
        get(URL, Constant.API_ANALYZE_SUCCESS);
    }

    //会员记录(物流端)
    public static void getMemberHistoryList(String uid, String token, int no, int size,String sId, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_MEMBER_HISTORY_URL, uid, token, no, size,sId);
        get(URL, Constant.API_ANALYZE_SUCCESS);
    }

    //更换密码
    public static void postModifyPwd(String uid, String token, String oldpwd, String newpwd, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put("order_pwd", oldpwd);
        map.put("new_pwd", newpwd);
        post(API_POST_MODIFY_PASSWORD, map, Constant.API_REQUEST_SUCCESS);
    }

    //订单发货(物流端)
    public static void postOrderDeliverInfo(String uid, String token,String oId,String company,String code, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put(JsonKey.OrderInfoKey.ORDER_ID, oId);
        map.put(JsonKey.OrderInfoKey.LOGS_COMPANY, company);
        map.put(JsonKey.OrderInfoKey.LOGS_NUMBER, code);
        post(API_POST_ORDER_DELIVER_INFO, map, Constant.API_REQUEST_SUCCESS);
    }

    //修改物流信息 (物流端)
    public static void postModifyLogisticInfo(String uid, String token,String oId,String company,String code, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put(JsonKey.OrderInfoKey.ORDER_ID, oId);
        map.put(JsonKey.OrderInfoKey.LOGS_COMPANY, company);
        map.put(JsonKey.OrderInfoKey.LOGS_NUMBER, code);
        post(API_POST_MODIFY_ORDER_LOGIS, map, Constant.API_POST_MODIFY_ORDER_LOGIS_SUCCESS);
    }

    //注销-退出登录
    public static void postUserLogOut(String uid, String token, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        postWithOutCheck(API_POST_LOGOUT_INFO, map, Constant.API_POST_LOGOUT_SUCCESS);
    }

    //设置配送费
    public static void postSetDeliveryMoney(String uid, String token,String freeMoney,String deliveryMoney, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put(JsonKey.UserKey.POINT, freeMoney);
        map.put(JsonKey.UserKey.FREIGHT, deliveryMoney);
        postWithOutCheck(API_POST_SHOP_DELIVERY_MONEY, map, Constant.API_REQUEST_SUCCESS);
    }

    //评价查看(物流端)
    public static void getOrderReviewInfo(String uid, String token,String oId, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_ORDER_REVIEW_INFO, uid, token,oId);
        get(URL, Constant.API_GET_ORDER_REVIEW_INFO_SUCCESS);
    }

    //物流查看(物流端)
    public static void getOrderLogisInfo(String uid, String token,String oId, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_ORDER_LOGIS_INFO, uid, token,oId);
        get(URL, Constant.API_GET_ORDER_LOGIS_INFO_SUCCESS);
    }

    //获取订单物流进度
    public static void getLogisProgressInfo(String uid, String token,String company,String code, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_ORDER_LOGIS_PROGRESS, uid, token,company,code);
        get(URL, Constant.API_GET_ORDER_LOGIS_PROGRESS_SUCCESS);
    }

    //商品状态列表
    public static void getGoodsListStatus(GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_GOODS_LIST_STATUS);
        get(URL, Constant.API_GET_GOODS_LIST_STATUS_SUCCESS);
    }

    //省份列表
    public static void getProvinceList(GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_PROVINCE_LIST);
        get(URL, Constant.API_GET_PROVINCE_LIST_SUCCESS);
    }

    //获取城市列表
    public static void getCitysList(String province, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_CITYS_LIST, province);
        get(URL, Constant.API_GET_CITY_LIST_SUCCESS);
    }

    //查询水果类别
    public static void getFruitCategoriesInfo(String sId,GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_FRUIT_CATEGORIES, sId);
        get(URL, Constant.API_GET_FRUIT_CATEGORIES_SUCCESS);
    }

    //类别查询
    public static void getCategoryList(String sId,String categoryId, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_CATEGORY_LIST,sId,categoryId);
        get(URL, Constant.API_GET_CATEGORY_LIST_SUCCESS);
    }

    //商品发布
    public static void postReleaseGood(String uid, String token,String shopId, String img, String images, String title, String descrip,
                                       String feature_labels, String text, String category_id, List<StyleBean> styleBeans, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put(JsonKey.ReleaseKey.SHOPID, shopId);
        map.put(JsonKey.ReleaseKey.IMG, img);
        map.put(JsonKey.ReleaseKey.IMAGES, images);
        map.put(JsonKey.ReleaseKey.TITLE, title);
        map.put(JsonKey.ReleaseKey.DESCRIP, ""+descrip);
        map.put(JsonKey.ReleaseKey.FEATURE_LABELS, feature_labels);
        map.put(JsonKey.ReleaseKey.TEXT, text);
        map.put(JsonKey.ReleaseKey.CATEGORY_ID, category_id);



        JSONArray array = new JSONArray();
        for(int i = 0;i < styleBeans.size();i++){
            if(!styleBeans.get(i).toString().isEmpty()){
                JSONObject object = new JSONObject();
                try {
                    object.put("title",styleBeans.get(i).getTitle());
                    object.put("price",styleBeans.get(i).getPrice());
                    object.put("rest_num",styleBeans.get(i).getRest());

                    if(i == 0){
                        //取规格的第一个价格为默认单价
                        map.put(JsonKey.ReleaseKey.ENTITY_PRICE, styleBeans.get(i).getPrice());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                array.put(object);
            }
        }
        map.put(JsonKey.ReleaseKey.STYLE, array.toString());

        post(API_POST_GOODS_RELEASE, map, Constant.API_POST_GOODS_RELEASE_SUCCESS);
    }

    //商品修改
    public static void postGoodModifyInfo(String uid, String token,String gId, String img, String images, String title, String descrip,
                                       String feature_labels, String text, String category_id, List<StyleBean> styleBeans, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put(JsonKey.ReleaseModifyKey.GOOD_ID, gId);
        map.put(JsonKey.ReleaseModifyKey.IMG, img);
        map.put(JsonKey.ReleaseModifyKey.IMAGES, images);
        map.put(JsonKey.ReleaseModifyKey.TITLE, title);
        map.put(JsonKey.ReleaseModifyKey.DESCRIP, ""+descrip);
        map.put(JsonKey.ReleaseModifyKey.FEATURE_LABELS, feature_labels);
        map.put(JsonKey.ReleaseModifyKey.TEXT, text);
        map.put(JsonKey.ReleaseModifyKey.CATEGORY_ID, category_id);

        JSONArray array = new JSONArray();

        JSONArray newArray = new JSONArray();

        for(int i = 0;i < styleBeans.size();i++){
            if(!styleBeans.get(i).toString().isEmpty()){

                if(styleBeans.get(i).getSpeces_id() != 0 && styleBeans.get(i).getPrice_id() != 0 && styleBeans.get(i).getNum_id() != 0){

                    JSONObject object1 = new JSONObject();
                    try {
                        object1.put("speces_id",styleBeans.get(i).getSpeces_id());
                        object1.put("title",styleBeans.get(i).getTitle());
                        object1.put("price_id",styleBeans.get(i).getPrice_id());
                        object1.put("speces_price",styleBeans.get(i).getPrice());
                        object1.put("num_id",styleBeans.get(i).getNum_id());
                        object1.put("rest_num",styleBeans.get(i).getRest());

                        if(i == 0){
                            //取规格的第一个价格为默认单价
                            map.put(JsonKey.ReleaseKey.PRICE, styleBeans.get(i).getPrice());
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    array.put(object1);

                }else{//修改情况下的新增
                    if(styleBeans.get(i).isFullData()){
                        JSONObject object2 = new JSONObject();
                        try {
                            object2.put("title",styleBeans.get(i).getTitle());
                            object2.put("speces_price",styleBeans.get(i).getPrice());
                            object2.put("rest_num",styleBeans.get(i).getRest());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        newArray.put(object2);
                    }
                }
            }
        }
        map.put(JsonKey.ReleaseModifyKey.STYLE, array.toString());

        map.put(JsonKey.ReleaseModifyKey.NEW, newArray.toString());

        post(API_POST_GOODS_MODIFY, map, Constant.API_POST_GOODS_MODIFY_SUCCESS);
    }

    //商品详情接口
    public static void getGoodsDetail(String uid,String token, String gId,GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_GOODS_DETAIL,uid,token, gId);
        get(URL, Constant.API_GET_GOODS_DETAIL_SUCCESS);
    }

    //更多-检测更新  API_ANALYZE_SUCCESS
    public static void postUpdateVersionInfo(String versionCode, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UpgradeKey.V_CODE, versionCode);
        map.put(JsonKey.UpgradeKey.TARGET, "LOGISTICS");
        postWithOutCheck(UPDATA_VERSION_INFO, map, Constant.API_POST_UPDATA_VERSION_SUCCESS);
    }

    public static void postShopGoodStatus(String uid, String token, String oId, String status, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put(JsonKey.GoodsKey.ID, oId);
        map.put(JsonKey.OrderBasketKey.STATUS, status);
        post(API_POST_GOODS_EDIT_STATUS, map, Constant.API_POST_GOODS_EDIT_STATUS_SUCCESS);
    }

    //店铺评论接口-列表
    public static void getGoodsReviewsList(String uid, String token, int page, int size, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_SHOPS_REVIEWS_LIST, uid, token, page, size);
        get(URL, Constant.API_GET_GOODS_REVIEWS_LIST_SUCCESS);
    }

    //查询店铺水果
    public static void getShopGoodsList(String uid, String token,String shopId, int no, int size, String status, GetResultCallBack callback) {
        mCallback = callback;

        JSONArray array = new JSONArray();
        array.put(status);
        status = array.toString();

        String URL = String.format(API_GET_SHOP_GOODS_LIST, uid, token,shopId, no, size, status);
        get(URL, Constant.API_GET_SHOP_GOODS_LIST_SUCCESS);
    }

    //商品种类数查询
    public static void getShopGoodsKindsNum(String uid, String token,String shopId, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_GOODS_KIND_NUM, uid, token,shopId);
        get(URL, Constant.API_ANALYZE_SUCCESS);
    }


    //获取洗衣百科
    public static void getBaikeInfo(GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_BAIKE_INFO);
        get(URL, Constant.API_GET_BAIKE_SUCCESS);
    }

    //获取洗衣计价标题相关
    public static void getWashClothesTitle(String type, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_WASH_CLOTHES_TITLE, type);
        get(URL, Constant.API_GET_MODULE_TITLE_SUCCESS);
    }

    //获取预约时间
    public static void getBookingTime(GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_BOOKING_TIME);
        get(URL, Constant.API_GET_WASH_BOOKING_TIME);
    }

    //获取当前时间
    public static void getCurrentTime(GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_BOOKING_TIME);
        get(URL, Constant.API_GET_CURRENT_TIME);
    }

    //获取洗衣计价标题相关
    public static void getClothListInfo(String cid, String cityId, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_CLOTHES_LIST_INFO, cid, cityId);
        get(URL, Constant.API_GET_CLOTHES_LIST_SUCCESS);
    }


    //意见反馈
    public static void postSuggestInfo(String uid, String token, String mes, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put(JsonKey.SuggestKey.CONTENT, mes);
        post(API_POST_SUGGEST_INFO, map, Constant.API_ANALYZE_SUCCESS);
    }

    //设置默认地址
    public static void setDefaultAddress(String uid, String token, String aid, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put(JsonKey.AddressKey.AID, aid);
        post(API_GET_ADDRESS_DEFAULT_URL, map, Constant.API_POST_SET_DEFAULT_ADDRESS);
    }

    //推荐好友得现金-获取活动规则
    public static void getRuleInstruction(GetResultCallBack callback) {
        mCallback = callback;
        get(API_GET_RELUS, Constant.API_GET_RELUS_SUCCESS);
    }

    //推荐好友得现金-获取推荐人
    public static void getRecommendInviter(String name, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_H5_URL, name);
        get(URL, Constant.API_ANALYZE_SUCCESS);
    }


    //我的提成---提现
    public static void postConfirmWithdrawal(String userName, String account, String type, String uid, String token,
                                             GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put("account_name", userName);
        map.put("account", account);
        map.put("account_type", type);
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        post(API_POST_WITHDRAW_INFO, map, Constant.API_ANALYZE_SUCCESS);
    }

    //我推荐的人
    public static void postMyRecommendDetail(String size, String page, String uid, String token,
                                             GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.MyOrderKey.SIZE, size);
        map.put(JsonKey.VoucherKey.PAGE, page);
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        post(API_POST_MYRECOMMENDDETAIL_INFO, map, Constant.API_POST_RECOMMEND_SUCCESS);
    }

    //更多-获取功能介绍
    public static void getIntroduceFunctionInfo(GetResultCallBack callback) {
        mCallback = callback;
        get(INTRODUCE_FUNCTION_INFO, Constant.API_GET_RELUS_SUCCESS);
    }

    //更多-获取常见问题
    public static void getCommonProblemInfo(GetResultCallBack callback) {
        mCallback = callback;
        get(COMMOM_PROBLEM_INFO, Constant.API_GET_RELUS_SUCCESS);
    }

    //充值--充值卡
    public static void postMyRechargeCard(String cardNum, String uid, String token,
                                          GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put("password", cardNum);
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        post(API_POST_RECHARGE_CARD, map, Constant.API_ANALYZE_SUCCESS);
    }

    //新增(修改)地址
    public static void postModifyAddressInfo(String uid, String token, String aid, String cityId, String name,
                                             String mobile, String address, String lat, String lng, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        if (!TextUtils.isEmpty(aid)) {
            map.put(JsonKey.AddressKey.AID, aid);
        }
        map.put(JsonKey.AddressKey.CITY, cityId);
        map.put(JsonKey.AddressKey.NAME, name);
        map.put(JsonKey.AddressKey.MOBILE, mobile);
        map.put(JsonKey.AddressKey.ADDRESS, address);
        map.put(JsonKey.AddressKey.LAT, lat);
        map.put(JsonKey.AddressKey.LNG, lng);
        post(API_POST_ADD_ADDRESS, map, Constant.API_ANALYZE_SUCCESS);
    }

    //删除地址
    public static void postDelAddressInfo(String uid, String token, String aid, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put(JsonKey.AddressKey.AID, aid);
        post(API_POST_DEL_ADDRESS, map, Constant.API_POST_DELETE_ADDRESS);
    }

    //立即预约
    public static void postReserverInfo(String uid, String token, String username, String mobile, String address,
                String lat, String lng, String cid, String time, String remark,String addrId,GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put(JsonKey.OrderReserve.NAME, username);
        map.put(JsonKey.OrderReserve.MOBILE, mobile);
        map.put(JsonKey.OrderReserve.ADDRESS, address);
        map.put(JsonKey.OrderReserve.LAT, lat);
        map.put(JsonKey.OrderReserve.LNG, lng);
        map.put(JsonKey.OrderReserve.CID, cid);
        map.put(JsonKey.OrderReserve.TIME, time);
        map.put(JsonKey.OrderReserve.REMARK, remark);
        map.put(JsonKey.OrderReserve.ADDRID, addrId);
        post(API_POST_RESERVER_INFO, map, Constant.API_ANALYZE_SUCCESS);
    }

    //取消预约
    public static void postCancelOrder(String uid, String token, String orderId, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put(JsonKey.OrderBasketKey.ORDER_ID, orderId);
        post(API_POST_CANCEL_ORDER, map, Constant.API_POST_CANCEL_ORDER_SUCCESS);
    }

    //订单评价
    public static void postOrderEvaInfo(String uid, String token, String orderId, String stars, String content, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put("orderNo", orderId);
        map.put("mark", stars);
        map.put("content", content);
        post(API_POST_ORDER_EVALUATION, map, Constant.API_ANALYZE_SUCCESS);
    }

    //获取用户未完成订单
    public static void getUncompletedOrderList(String uid, String token, int no, int size, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_UNCOMPLETED_ORDER, uid, token, no, size);
        get(URL, Constant.API_GET_UNCOMPLETED_ORDER);
    }

    //获取用户已完成订单列表
    public static void getCompletedOrderList(String uid, String token, int no, int size, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_COMPLETED_ORDER, uid, token, no, size);
        get(URL, Constant.API_GET_COMPLETED_ORDER);
    }

    //获取订单详情
    public static void getOrderDetailInfo(String uid, String token, String orderId, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_ORDER_DETAIL_INFO, uid, token, orderId);
        get(URL, Constant.API_ANALYZE_SUCCESS);
    }

    //退款详情
    public static void getRefundOrderDetail(String uid, String token, String orderId, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_ORDER_DETAIL_INFO_REFUND, uid, token, orderId);
        get(URL, Constant.API_ANALYZE_SUCCESS);
    }


    //支付接口之前
    public static void postPayBeforeInfo(String uid, String token, String orderId, String otherDiscount, String voucharId,
                                         String payType, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put(JsonKey.OrderBasketKey.ORDER_ID, orderId);
        map.put("otherDiscount", otherDiscount);
        if(!voucharId.equals("0")){
            map.put("voucherId", voucharId);
        }
        map.put("payType", payType); //0余额 1 微信 2支付宝 3线下
        post(API_POST_PAY_BEFORE_INFO, map, Constant.API_POST_PAY_BEFORE_SUCCESS);
    }

    //支付接口之后
    public static void postPayAfterInfo(String uid, String token, String orderId,String voucharId,GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put(JsonKey.OrderBasketKey.ORDER_ID, orderId);
        if(!voucharId.equals("0")){
            map.put("voucherId", voucharId);
        }
        post(API_POST_PAY_AFTER_INFO, map, Constant.API_POST_PAY_AFTER_SUCCESS);
    }


    //余额-支付接口之后
    /*public static void postBalanceAfterInfo(String uid, String token, String orderId,String voucharId,GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put(JsonKey.OrderBasketKey.ORDER_ID, orderId);
        if(!voucharId.equals("0")){
            map.put("voucherId", voucharId);
        }
        post(API_POST_BALANCE_AFTER_INFO, map, Constant.API_POST_BALANCE_AFTER_SUCCESS);
    }*/


    //支付失败调用【还原优惠券】
    public static void postRevertVoucherInfo(String uid, String token, String orderId,String voucharId, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put(JsonKey.OrderBasketKey.ORDER_ID, orderId);
//        map.put("voucherId", voucharId);
        post(API_POST_REVERT_VOUCHER, map, Constant.API_POST_REVERT_VOUCHER_SUCCESS);
    }

    //查询店铺订单列表
    public static void getShopOrderList(String uid, String token, int no, int size, String status, GetResultCallBack callback) {
        /*String[] statusArray = status.split(",");
        JSONArray array = new JSONArray();
        for(int i = 0;i < statusArray.length;i++){
            array.put(statusArray[i]);
        }*/
        mCallback = callback;
        String URL = String.format(API_GET_SHOP_ORDER_LIST, uid, token, no, size, status.toString());
        get(URL, Constant.API_GET_SHOP_ORDER_LIST_SUCCESS);
    }

    public static void postDeliveryShopOrder(String uid, String token, String oId, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put(JsonKey.OrderBasketKey.ORDER_ID, oId);
        post(API_POST_SHOP_DELIVERY_CONFIRM, map, Constant.API_POST_SHOP_DELIVERY_CONFIRM_SUCCESS);
    }

    //获取用户已完成订单列表
    public static void getLocateCityInfo(String lat, String lng, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_CITY_INFO_BY_LATANDLNG, lat, lng);
        getWithOutCheck(URL, Constant.API_GET_LOCATE_CITY_SUCCESS);
    }

    //获取订单物流信息
    public static void getFarmOrderLogisInfo(String uid, String token, String company,String code, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_ORDER_LOGIS_INFO,uid, token, company,code);
        get(URL, Constant.API_GET_ORDER_LOGIS_INFO_SUCCESS);
    }

    public static void getHttpsTest(String url,int type, GetResultCallBack callback){
        mCallback = callback;
        String URL = String.format(url);
        getWithOutCheck(URL, type);
    }

    public static void get(String url, final int type) {
        String REQUEST_URL = url;
        Log.i(TAG, "get.url=" + url);

        OkHttpClientManager.getAsyn(REQUEST_URL, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                e.printStackTrace();
                Log.i(TAG, "API_REQUEST_FAILURE");
                mCallback.getResult(request.toString(), Constant.API_REQUEST_FAILURE);
            }

            @Override
            public void onResponse(String u) {
                Log.i(TAG, "API_REQUEST_SUCCESS,u=" + u);
                try {
                    if (AppClient.isParse(u)) {
                        Log.i(TAG, "onResponse1=");
                        mCallback.getResult(u, type);
                    } else {
                        Log.i(TAG, "onResponse2=");
                        mCallback.getResult(u, Constant.API_ERROR_REBACK);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void getWithOutCheck(String url, final int type) {
        String REQUEST_URL = url;
        Log.i(TAG, "get.url=" + url);
        OkHttpClientManager.getAsyn(REQUEST_URL, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                e.printStackTrace();
                Log.e(TAG, "API_REQUEST_FAILURE");
                mCallback.getResult(request.toString(), Constant.API_REQUEST_FAILURE);
            }

            @Override
            public void onResponse(String u) {
                Log.e(TAG, "API_REQUEST_SUCCESS,u=" + u);
                mCallback.getResult(u, type);
                /*try {
                    if (AppClient.isParse(u)) {
                        mCallback.getResult(u, type);
                    } else {
                        Gson gson = new Gson();
                        ErrorBean bean = gson.fromJson(u, ErrorBean.class);
                        if (bean != null) {
                            EventBus.getDefault().post(new EventBean(EventBean.EVENT_GET_DEFAULT_LOCATION, bean.getData()));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }
        });
    }

    //获取用户收货地址
    public static void getAddressList(String uid, String token, int no, int size, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_ADDRESS_URL, uid, token, no, size);
        get(URL, Constant.API_ANALYZE_SUCCESS);
    }

    //获取用户优惠卷
    public static void getVoucherList(String uid, String token, int no, int size, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_MY_VOUCHER_INFO, uid, token, no, size);
        get(URL, Constant.API_ANALYZE_SUCCESS);
    }

    //修改昵称
    public static void postModifyNick(String uid, String token, String nickName, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put("nickname", nickName);
        post(API_POST_NICK_NAME_INFO, map, Constant.API_POST_NICKNAME_SUCCESS);
    }

    //获取用户个人信息
    public static void getUserBaseInfo(String uid, String token, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_USER_BASE_INFO, uid,token);
        get(URL, Constant.API_GET_USER_INFO_SUCCESS);
    }

    //获取充值卡信息
    public static void getVoucherCardInfo(String uid, String token, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_VOUCHERCARDS_INFO, uid,token);
        get(URL, Constant.API_GET_VOUCHER_CARD_SUCCESS);
    }

    //在线充值前
    public static void postVouchercardsBefore(String uid, String token, String vid,String type,String money, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put("vid", vid);
        map.put("type", type);
        map.put("money", money);
        post(API_POST_VOUCHERCARDS_BEFORE, map, Constant.API_POST_VOICHER_BEFORE_SUCCESS);
    }

    //在线充值后
    public static void postVouchercardsAfter(String uid, String token, String pid,String type, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put("id", pid);
        map.put("type", type);
        post(API_POST_VOUCHERCARDS_AFTER, map, Constant.API_POST_VOICHER_AFTER_SUCCESS);
    }

    //获取消费记录
    public static void getBalanceHistory(String uid, String token, int no, int size, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_BALANCE_HISTORY_URL, uid, token, no, size);
        get(URL, Constant.API_ANALYZE_SUCCESS);
    }

    //上传头像
    public static void postUploadAvator(String uid, String token,String avatarUrl, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put(JsonKey.UserKey.AVATAR, avatarUrl);
        post(API_POST_AVATAR_INFO, map, Constant.API_POST_UPLOAD_AVATAR_SUCCESS);
    }

    /*
     * STATE:待发货-PS_WAITTING;已取消-CANCELED
     */
    public static void postAcceptShopOrder(String uid, String token, String oId,String status, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put(JsonKey.OrderInfoKey.ORDER_ID, oId);
        map.put(JsonKey.OrderInfoKey.STATE, status);
        post(API_POST_SHOP_ACCEPT_ORDER, map, Constant.API_POST_SHOP_ACCEPT_ORDER_SUCCESS);
    }

    //退款申请列表
    public static void getShopRefundOrderList(String uid, String token, int no, int size,String shopId,String status, GetResultCallBack callback) {
        mCallback = callback;
        String URL = String.format(API_GET_SHOP_REFUND_LIST, uid, token, no, size,shopId,status);
        get(URL, Constant.API_GET_SHOP_REFUND_LIST_SUCCESS);
    }


    public static void postRefundStatus(String uid, String token, String rId,String status, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put(JsonKey.OrderInfoKey.ORDER_ID, rId);
        map.put(JsonKey.OrderInfoKey.REFUND_STATUS, status);
        post(API_POST_SHOP_REFUND_STATUS, map, Constant.API_POST_SHOP_REFUND_STATUS_SUCCESS);
    }


    public static void postReturnRefundStatus(String uid, String token, String rId,String status,String money, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put(JsonKey.OrderInfoKey.ORDER_ID, rId);
        map.put(JsonKey.OrderInfoKey.REFUND_STATUS, status);
        if(!TextUtils.isEmpty(money)){
            map.put(JsonKey.OrderInfoKey.MONEY, money);
        }
        post(API_POST_SHOP_RETURN_REFUND, map, Constant.API_POST_SHOP_RETURN_REFUND_SUCCESS);
    }

    /*public static void postShopRefundDeny(String uid, String token, String rId, GetResultCallBack callback) {
        mCallback = callback;
        Map<String, String> map = new HashMap<String, String>();
        map.put(JsonKey.UserKey.UID, uid);
        map.put(JsonKey.UserKey.UTOKEN, token);
        map.put(JsonKey.OrderBasketKey.ORDER_ID, rId);
        post(API_POST_SHOP_REFUND_STATUS, map, Constant.API_POST_SHOP_REFUND_DENY_SUCCESS);
    }*/

    //上传单张图片
    /*public static void postUploadAvator(String uid,String token,String path, Context mContext, GetResultCallBack callback) {
        mCallback = callback;

        File file = new File(path);
        Log.i(TAG, "uploadPic.uid=" + uid + ",path=" + path + ",file=" + file);

        OkHttpClientManager.getUploadDelegate().postAsyn(API_POST_AVATAR_INFO, "avatar", file, new OkHttpClientManager.Param[]{
                new OkHttpClientManager.Param("lt-id", uid), new OkHttpClientManager.Param("lt-token", token)}, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                mCallback.getResult(request.toString(), Constant.API_REQUEST_FAILURE);
            }

            @Override
            public void onResponse(String response) {
                try {
                    if (AppClient.isParse(response)) {
                        mCallback.getResult(response, Constant.API_POST_UPLOAD_AVATAR_SUCCESS);
                    } else {
                        mCallback.getResult(response, Constant.API_ERROR_REBACK);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, mContext);
    }*/

    public static void post(String url, Map<String, String> map, final int type) {
//        Log.i(TAG, "post,url=" + url + ",map=" + map);

        String postUrl = url;
        StringBuilder param = new StringBuilder();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (param.length() > 0) {
                    param.append("&");
                }
                param.append(entry.getKey());
                param.append("=");
                param.append(entry.getValue());
            }
        }
        postUrl = postUrl + param.toString();

        Log.i(TAG, "postUrl=" + postUrl );

        OkHttpClientManager.postAsyn(url, map, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i(TAG, "Error=" + request.toString());
                mCallback.getResult(request.toString(), Constant.API_REQUEST_FAILURE);
            }

            @Override
            public void onResponse(String response) {
                try {
                    if (AppClient.isParse(response)) {
                        Log.i(TAG, "onResponse1=" + response.toString());
                        mCallback.getResult(response, type);
                    } else {
                        Log.i(TAG, "onResponse2=" + response.toString());
                        mCallback.getResult(response, Constant.API_ERROR_REBACK);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void postWithOutCheck(String url, Map<String, String> map, final int type) {
        Log.i(TAG, "post,url=" + url + ",map=" + map);
        OkHttpClientManager.postAsyn(url, map, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                Log.i(TAG, "Error=" + request.toString());
                mCallback.getResult(request.toString(), Constant.API_REQUEST_FAILURE);
            }

            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse=" + response);
                mCallback.getResult(response, type);
            }
        });
    }

    //判断返回内容格式是否正确
    public static boolean isParse(String response) {
        boolean result = false;

        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.has("code")) {
                String code = jsonObject.getString("code");
                if (code.equals("000")) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogsUtil.error("isParse.Error=" + e.getMessage());
        }
        return result;
    }

    //上传单张图片
    public static void postUploadPicture(String uid, String token, String path, String classify, Context mContext, GetResultCallBack callback) {
        mCallback = callback;

        File file = new File(path);
        Log.i(TAG, "uploadPic.uid=" + uid + ",token=" + token + ",path=" + path + ",file=" + file);

        OkHttpClientManager.getUploadDelegate().postAsyn(API_POST_PICTURE_INFO, "img", file, new OkHttpClientManager.Param[]{
                new OkHttpClientManager.Param("lt-id", uid), new OkHttpClientManager.Param("lt-token", token), new OkHttpClientManager.Param("directory", classify)}, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                mCallback.getResult(request.toString(), Constant.API_REQUEST_FAILURE);
            }

            @Override
            public void onResponse(String response) {
                try {
                    if (AppClient.isParse(response)) {
                        mCallback.getResult(response, Constant.API_POST_UPLOAD_PICTURE_SUCCESS);
                    } else {
                        mCallback.getResult(response, Constant.API_ERROR_REBACK);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, mContext);
    }


    //多图上传<循环>
    public void uploadImgs(String uid, String token, List<String> photes, String director, Context
            context, GetBeanCallBack<ImgBean> callback) {
        List<ImageBean> data = new ArrayList<ImageBean>();
        ImgBean image = new ImgBean(data);
        for (int i = 0; i < photes.size(); i++) {
            try {
                //多图上传需一定的延迟，不然成功率低
                Thread.sleep(600);
            } catch (Exception e) {
                e.printStackTrace();
            }

            String path = photes.get(i);

            Log.i(TAG,i+"=path=>"+path);

            //先根据path将图片进行压缩处理后上传
            //获得压缩过后的bitmap
            Bitmap bitmap = ImageUtils.decodeBitmapWithOrientationMax(path, DisplayUtil.commonWidth, DisplayUtil.commonHeight);
            //将其转化为文件
            try {
                File file = ImageUtils.saveToFile2(FileUtils.getInst().getBasePath() + "/Download", true, bitmap);
                fileUpload(uid, token, file, context, callback, image, photes.size(), director);
                Log.i("TEST", "上传第:" + i + "张,一共多少张:" + photes.size());
            } catch (IOException e) {
                e.printStackTrace();
                Log.i("TEST", "Error=" + e.getMessage());
            }
        }
    }

    int pos = 0;

    public void fileUpload(String uid, String token, File file, final Context context,
                           final GetBeanCallBack<ImgBean> img, final ImgBean imgBean, final int size, String director) {
        OkHttpClientManager.getUploadDelegate().postAsyn(API_POST_PICTURE_INFO, "file", file, new OkHttpClientManager.Param[]{
                new OkHttpClientManager.Param("lt-id", uid), new OkHttpClientManager.Param("lt-token", token), new OkHttpClientManager.Param("directory", director)}, new OkHttpClientManager.ResultCallback<String>() {
            @Override
            public void onError(Request request, Exception e) {
                e.printStackTrace();
                img.getResult(imgBean, Constant.API_REQUEST_FAILURE);
                Log.e(TAG, "单张上传onError:" + e.getMessage() + "\nImgUrl=" + API_POST_PICTURE_INFO);
            }

            @Override
            public void onResponse(String response) {
                try {
                    Log.i(TAG, "单张上传onResponse:" + response);
                    if (isParse(response)) {
                        Gson gson = new Gson();
                        ImageBean imageBean = gson.fromJson(response, ImageBean.class);
                        //将返回的数据加入集合
                        imgBean.getData().add(imageBean);
                        //////结束了返回数据
                        if (size - 1 == pos) {
                            pos = 0;
//                            Log.i(TAG, "图片上传最终:" + imgBean.getData().size() + "");
                            //获得返回数据的结果
                            img.getResult(imgBean, Constant.REQUEST_TYPE_UPLOAD_PICS);
                        }
                        pos++;
                    } else {
//                        Log.i(TAG, "单张上传onResponse失败:" + response);
                        img.getResult(imgBean, Constant.API_ERROR_REBACK);
                    }
                } catch (Exception e) {
//                    Log.e(TAG, "图片上传Exception:" + e.getMessage());
                    img.getResult(imgBean, Constant.API_ERROR_REBACK);
                    e.printStackTrace();
                }
            }
        }, context);
    }
}


