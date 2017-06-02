package com.techfly.demo.adpter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.techfly.demo.R;
import com.techfly.demo.bean.ShopRefundOrderListBean;
import com.techfly.demo.interfaces.ItemMoreClickListener;
import com.techfly.demo.selfview.photepicker.PhotoPagerActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * 店铺订单-退款
 */
public class ShopOrderRefundLvAdapter extends BaseAdapter {
    private Context mContext;

    private List<ShopRefundOrderListBean.DataEntity.DatasEntity> listData;// = new ArrayList<ShopBean>();
    private LayoutInflater layoutInflater;
    private ItemMoreClickListener mItemClickListener;
    private int mCurStatus = 0;

    public ShopOrderRefundLvAdapter(Context context, List<ShopRefundOrderListBean.DataEntity.DatasEntity> shopBeans, int status) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.listData = shopBeans;
        this.mCurStatus = status;
    }

    public void addAll(List<ShopRefundOrderListBean.DataEntity.DatasEntity> list, int status) {
        listData.addAll(list);
        this.mCurStatus = status;
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemMoreClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public void clearAll() {
        listData.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return listData != null ? listData.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup arg2) {
        final ViewHolder holder;
        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.item_order_refund_lv, null);
            holder = new ViewHolder();

            holder.item_reple_date_tv = (TextView) convertView.findViewById(R.id.item_reple_date_tv);

            holder.item_code_tv = (TextView) convertView.findViewById(R.id.item_code_tv);
            holder.item_date_tv = (TextView) convertView.findViewById(R.id.item_date_tv);
            holder.item_money_tv = (TextView) convertView.findViewById(R.id.item_money_tv);
            holder.item_reason_tv = (TextView) convertView.findViewById(R.id.item_reason_tv);

            holder.item_container_rl = (LinearLayout) convertView.findViewById(R.id.item_container_rl);
            holder.item_container_linear = (LinearLayout) convertView.findViewById(R.id.item_container_linear);

            holder.item_main_linear = (LinearLayout) convertView.findViewById(R.id.item_main_linear);

            holder.item_mobile_linear = (LinearLayout) convertView.findViewById(R.id.item_mobile_linear);

            holder.item_button1_tv = (TextView) convertView.findViewById(R.id.item_button1_tv);
            holder.item_button2_tv = (TextView) convertView.findViewById(R.id.item_button2_tv);

            holder.item_name_tv = (TextView) convertView.findViewById(R.id.item_name_tv);
            holder.item_phone_tv = (TextView) convertView.findViewById(R.id.item_phone_tv);

            holder.item_status_tv = (TextView) convertView.findViewById(R.id.item_status_tv);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.item_reple_date_tv.setText(listData.get(position).getRefund_time());

        holder.item_code_tv.setText(listData.get(position).getCode());
        holder.item_date_tv.setText(listData.get(position).getOrder_time());
        holder.item_money_tv.setText(listData.get(position).getMoney() + "");
        holder.item_reason_tv.setText(listData.get(position).getReason());

        holder.item_name_tv.setText("客户:" + listData.get(position).getName());
        holder.item_phone_tv.setText(listData.get(position).getMobile());

        String refund_status = listData.get(position).getRefund_status();
        if (refund_status.equals("已退款") || refund_status.equals("拒绝退款")) {
            holder.item_status_tv.setVisibility(View.VISIBLE);
            holder.item_status_tv.setText("状态:" + refund_status);
        } else {
            holder.item_status_tv.setVisibility(View.INVISIBLE);
        }

        String imags = listData.get(position).getImages();
        imags = imags.replace("[]", "");
        if (TextUtils.isEmpty(imags)) {
            holder.item_container_rl.setVisibility(View.GONE);
        } else {
            holder.item_container_rl.setVisibility(View.VISIBLE);
            //图片部分
            holder.item_container_linear.removeAllViews();

            String[] imagesArray = listData.get(position).getImages().split(",");
            final ArrayList<String> picList = new ArrayList<String>();
            Collections.addAll(picList, imagesArray);

            int indicate = 0;  //当前图片
            for (String imgUrl : picList) {
                View view = View.inflate(mContext, R.layout.layout_imageview_review, null);
                ImageView mphote = (ImageView) view.findViewById(R.id.phote_Iv);
                Glide.with(mContext)
                        .load(imgUrl)
                        .thumbnail(0.3f)
                        .placeholder(R.drawable.def_photo)
                        .error(R.drawable.def_photo)
                        .into(mphote);

                final int finalI = indicate;
                mphote.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, PhotoPagerActivity.class);
                        intent.putExtra(PhotoPagerActivity.EXTRA_CURRENT_ITEM, finalI);
                        intent.putExtra(PhotoPagerActivity.EXTRA_PHOTOS, picList);
                        intent.putExtra(PhotoPagerActivity.EXTRA_EDITABLE, false);
                        mContext.startActivity(intent);
                    }
                });
                setPicParams(view, true);
                holder.item_container_linear.addView(view);
                indicate++;
            }
        }

        final int type = mCurStatus;
        holder.item_button1_tv.setText("同意");
        holder.item_button2_tv.setText("拒绝");

        holder.item_button1_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemMulViewClick(type, 0, position);
                }
            }
        });

        holder.item_main_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(holder.item_main_linear,position);
                }
            }
        });

        holder.item_button2_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemMulViewClick(type, 1, position);
                }
            }
        });

        holder.item_mobile_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemMulViewClick(type, 2, position);
                }
            }
        });

        return convertView;
    }

    /////////////////缩略图的布局
    private void setPicParams(View view, boolean requirdRightMargin) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (requirdRightMargin) {
            params.rightMargin = (int) mContext.getResources().getDimension(R.dimen.marign_mid);
        }
        view.setLayoutParams(params);
    }


    public static class ViewHolder {

        public TextView item_reple_date_tv;

        public TextView item_code_tv;
        public TextView item_date_tv;
        public TextView item_money_tv;
        public TextView item_reason_tv;

        public LinearLayout item_container_rl;
        public LinearLayout item_container_linear;

        public LinearLayout item_mobile_linear;

        public TextView item_button1_tv;
        public TextView item_button2_tv;

        public TextView item_name_tv;
        public TextView item_phone_tv;

        public TextView item_status_tv;

        public LinearLayout item_main_linear;

    }

}
