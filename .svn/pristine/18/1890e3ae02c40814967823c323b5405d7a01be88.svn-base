package com.techfly.demo.activity.refund;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.adpter.RefundDetailGoodsItemLvAdapter;
import com.techfly.demo.bean.RefundOrderDetailBean;
import com.techfly.demo.bean.User;
import com.techfly.demo.interfaces.ItemMultClickListener;
import com.techfly.demo.selfview.photepicker.PhotoPagerActivity;
import com.techfly.demo.util.CommonUtils;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.RegexUtil;
import com.techfly.demo.util.SharePreferenceUtils;
import com.techfly.demo.util.ToastUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


/**
 * 退款-订单详情
 */
public class RefundOrderDetailActivity extends BaseActivity {

    @InjectView(R.id.order_number_tv)
    TextView order_number_tv;
    @InjectView(R.id.order_time_tv)
    TextView order_time_tv;
    /*@InjectView(R.id.order_service_tv)
    TextView order_service_tv;*/
    @InjectView(R.id.order_remark_tv)
    TextView order_remark_tv;


    @InjectView(R.id.address_name_tv)
    TextView address_name_tv;
    @InjectView(R.id.address_phone_tv)
    TextView address_phone_tv;
    @InjectView(R.id.address_detail_tv)
    TextView address_detail_tv;

    @InjectView(R.id.pay_type_tv)
    TextView pay_type_tv;
    @InjectView(R.id.pay_info_tv1)
    TextView pay_info_tv1;
    @InjectView(R.id.pay_info_tv2)
    TextView pay_info_tv2;

    @InjectView(R.id.detail_phone_linear)
    LinearLayout detail_phone_linear;
    @InjectView(R.id.detail_phone_tv)
    TextView detail_phone_tv;

    @InjectView(R.id.goods_lv)
    ListView goods_lv;
    @InjectView(R.id.base_linear)
    LinearLayout base_linear;

    @InjectView(R.id.detail_reason_tv)
    TextView detail_reason_tv;
    @InjectView(R.id.detail_container_linear)
    LinearLayout detail_container_linear;

    @InjectView(R.id.detail_confirm_tv)
    TextView detail_confirm_tv;
    @InjectView(R.id.detail_refuse_tv)
    TextView detail_refuse_tv;

    private List<RefundOrderDetailBean.DataEntity.GoodsInfoEntity> datasEntityList = new ArrayList<RefundOrderDetailBean.DataEntity.GoodsInfoEntity>();//模拟商品
    private RefundDetailGoodsItemLvAdapter adapter = null;

    private User mUser = null;
    //    private String m_orderId = "";   //订单ID
    private String m_type = "";
    private String m_mobile = "";

    private Boolean isOnlyRereshGoods = false; //评论后刷新界面，只刷新商品展示部分
    private Boolean m_isScroll = false;        //是否滚动到评论位置

    private String m_goodId = "";

    private String m_getIntentId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_refund_detail);

        ButterKnife.inject(this);

        initBaseView();
        setBaseTitle(getResources().getString(R.string.refund_order_detail), 0);
        initBackButton(R.id.top_back_iv);
        setTranslucentStatus(R.color.main_bg);

        initView();

        loadIntent();

        initAdapter();

    }

    private void initView() {

//        posTvArray = new TextView[]{order_point_tv1, order_point_tv2, order_point_tv3, order_point_tv4};
//        statusTvArray = new TextView[]{order_status_tv1, order_status_tv2, order_status_tv3, order_status_tv4};
//
//        Collections.addAll(statusList, statusArray);

        mUser = SharePreferenceUtils.getInstance(this).getUser();
    }

    private void updataView(RefundOrderDetailBean.DataEntity data) {

        LogsUtil.normal("updataView...2");

        detail_reason_tv.setText(data.getReason());

        String imags = data.getImages();
        imags = imags.replace("[]", "");
        if (TextUtils.isEmpty(imags)) {
            detail_container_linear.setVisibility(View.GONE);
        } else {
            detail_container_linear.setVisibility(View.VISIBLE);
            //图片部分
            detail_container_linear.removeAllViews();

            String[] imagesArray = data.getImages().split(",");
            final ArrayList<String> picList = new ArrayList<String>();
            Collections.addAll(picList, imagesArray);

            int indicate = 0;  //当前图片
            for (String imgUrl : picList) {
                View view = View.inflate(RefundOrderDetailActivity.this, R.layout.layout_imageview_review, null);
                ImageView mphote = (ImageView) view.findViewById(R.id.phote_Iv);
                Glide.with(RefundOrderDetailActivity.this)
                        .load(imgUrl)
                        .thumbnail(0.3f)
                        .placeholder(R.drawable.def_photo)
                        .error(R.drawable.def_photo)
                        .into(mphote);

                final int finalI = indicate;
                mphote.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RefundOrderDetailActivity.this, PhotoPagerActivity.class);
                        intent.putExtra(PhotoPagerActivity.EXTRA_CURRENT_ITEM, finalI);
                        intent.putExtra(PhotoPagerActivity.EXTRA_PHOTOS, picList);
                        intent.putExtra(PhotoPagerActivity.EXTRA_EDITABLE, false);
                        startActivity(intent);
                    }
                });
                setPicParams(view, true);
                detail_container_linear.addView(view);
                indicate++;
            }
        }

        //更新订单信息
        order_number_tv.setText(data.getOrder_info().getCode());
        order_time_tv.setText(data.getOrder_info().getCreate_time());
        order_remark_tv.setText(data.getOrder_info().getRemark());

        //更新用户信息
        address_name_tv.setText(data.getOrder_info().getName());
        address_phone_tv.setText(data.getOrder_info().getMobile());
        address_detail_tv.setText("地址:" + data.getOrder_info().getAddress());

        //加载商品信息
        adapter.addAll(data.getGoods_info());

        if (m_type.equals("UNPAYMENT")) {
            pay_type_tv.setText("尚未支付");
        } else {

            String tatal = data.getOrder_info().getTotal_money() + "";
            String freight = data.getOrder_info().getFreight() + "";
            String voucher = data.getOrder_info().getVoucher_money() + "";
            String discount = data.getOrder_info().getRed_packet_money() + "";

            if (TextUtils.isEmpty(tatal)) {
                tatal = "0.0";
            }
            if (TextUtils.isEmpty(freight)) {
                freight = "0.0";
            }
            if (TextUtils.isEmpty(voucher)) {
                voucher = "0.0";
            }
            if (TextUtils.isEmpty(discount)) {
                discount = "0.0";
            }

            Double orderPrice = Double.parseDouble(tatal) - Double.parseDouble(freight) + Double.parseDouble(voucher) + Double.parseDouble(discount);
            pay_info_tv1.setText("订单:¥" + RegexUtil.numberFormat("0.00",orderPrice) + "+运费¥" + freight + "-优惠券¥" + voucher + "-优惠活动¥" + discount);

            pay_info_tv2.setText("实付款:¥" + tatal);
        }

    }

    /////////////////缩略图的布局
    private void setPicParams(View view, boolean requirdRightMargin) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (requirdRightMargin) {
            params.rightMargin = (int) getResources().getDimension(R.dimen.marign_mid);
        }
        view.setLayoutParams(params);
    }

    private void initAdapter() {

        if (m_type.equals("PAYMENT")) {
            adapter = new RefundDetailGoodsItemLvAdapter(RefundOrderDetailActivity.this, datasEntityList, true);
        } else {
            adapter = new RefundDetailGoodsItemLvAdapter(RefundOrderDetailActivity.this, datasEntityList, false);
        }
        goods_lv.setAdapter(adapter);

        adapter.setItemClickListener(new ItemMultClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
//                OrderDetailBean.DataEntity.GoodsEntity bean = (OrderDetailBean.DataEntity.GoodsEntity) adapter.getItem(postion);
            }

            @Override
            public void onItemSubViewClick(int choice, int postion) {

            }
        });
    }

    private void loadIntent() {
        m_type = "PAYMENT";//getIntent().getStringExtra(Constant.CONFIG_INTENT_TYPE);

        m_getIntentId = getIntent().getStringExtra(Constant.CONFIG_INTENT_ORDER_ID);

        showProcessDialog();
        getRefundOrderDetailApi(mUser.getmId(), mUser.getmToken(), m_getIntentId);
    }

    /*private void loadData() {
        showProcessDialog();
        getOrderDetailInfoApi(mUser.getmId(), mUser.getmToken(), m_getIntentId);
    }*/

    @OnClick(R.id.detail_phone_linear)
    public void jumpToCall() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + m_mobile));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        LogsUtil.normal("requestCode=" + requestCode + ",resultCode=" + resultCode);

        if (resultCode == RESULT_OK) {
            /*if (requestCode == Constant.REQUESTCODE_NORMAL) {
                isOnlyRereshGoods = true;
                loadData();
            }*/
        }
    }

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);

        closeProcessDialog();
        result = CommonUtils.removeBrace(result);
        Gson gson = new Gson();

        if (type == Constant.API_ANALYZE_SUCCESS) {
            LogsUtil.normal("result=" + result);
            try {
                RefundOrderDetailBean data = gson.fromJson(result, RefundOrderDetailBean.class);
                if (data != null) {
                    updataView(data.getData());
                }
            } catch (Exception e) {
                ToastUtil.DisplayToastDebug(this, Constant.TOAST_MEG_REBACK_ERROR + "\n" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
