package com.techfly.demo.adpter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techfly.demo.R;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.activity.order.OrderDetailActivity;
import com.techfly.demo.bean.ShopOrderListBean;
import com.techfly.demo.interfaces.ItemMoreClickListener;
import com.techfly.demo.interfaces.ItemMultClickListener;
import com.techfly.demo.util.LogsUtil;

import java.util.List;

/*
 * 店铺订单
 */
public class ShopOrderLvAdapter extends BaseAdapter {
    private Context mContext;

    private List<ShopOrderListBean.DataEntity.DatasEntity> listData;// = new ArrayList<ShopBean>();
    private LayoutInflater layoutInflater;
    private ItemMoreClickListener mItemClickListener;

    //1-未收货；2-已收货
    private int mCurStatus = 0;

    public ShopOrderLvAdapter(Context context, List<ShopOrderListBean.DataEntity.DatasEntity> shopBeans, int status) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.listData = shopBeans;
        this.mCurStatus = status;
    }

    public void addAll(List<ShopOrderListBean.DataEntity.DatasEntity> list, int status) {
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
        final ShopGoodItemLvAdapter adapter = new ShopGoodItemLvAdapter(mContext, listData.get(position).getGoods_info());
        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.item_order_pending_lv, null);
            holder = new ViewHolder();

            holder.item_goods_lv = (ListView) convertView.findViewById(R.id.item_goods_lv);
            holder.item_goods_linear = (RelativeLayout) convertView.findViewById(R.id.item_goods_linear);

            holder.item_code_tv = (TextView) convertView.findViewById(R.id.item_code_tv);
            holder.item_number_tv = (TextView) convertView.findViewById(R.id.item_number_tv);
            holder.item_price_tv = (TextView) convertView.findViewById(R.id.item_price_tv);
            holder.item_remark_tv = (TextView) convertView.findViewById(R.id.item_remark_tv);
            holder.item_date_tv = (TextView) convertView.findViewById(R.id.item_date_tv);

            holder.item_button1_tv = (TextView) convertView.findViewById(R.id.item_button1_tv);
            holder.item_button2_tv = (TextView) convertView.findViewById(R.id.item_button2_tv);

            holder.item_name_tv = (TextView) convertView.findViewById(R.id.item_name_tv);
            holder.item_phone_tv = (TextView) convertView.findViewById(R.id.item_phone_tv);
            holder.item_address_tv = (TextView) convertView.findViewById(R.id.item_address_tv);

            holder.item_phone_iv = (ImageView) convertView.findViewById(R.id.item_phone_iv);
            holder.item_delete_iv = (ImageView) convertView.findViewById(R.id.item_delete_iv);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        LogsUtil.normal("position2="+position);

        holder.item_date_tv.setText(listData.get(position).getCreate_time());
        holder.item_goods_lv.setAdapter(adapter);

        final int type = mCurStatus;

        switch (listData.get(position).getStatus()){
            case "待发货":
                holder.item_button1_tv.setBackgroundResource(R.drawable.shape_stroke_gray_solid_white);
                holder.item_button1_tv.setTextColor(mContext.getResources().getColor(R.color.text_font_gray));
                holder.item_button2_tv.setText("去发货");
                break;
            case "待收货":
                holder.item_button1_tv.setBackgroundResource(R.drawable.shape_stroke_green_solid_white);
                holder.item_button1_tv.setTextColor(mContext.getResources().getColor(R.color.text_font_green));
                holder.item_button2_tv.setText("已发货");
                break;
            case "待接单":
                holder.item_button1_tv.setBackgroundResource(R.drawable.shape_stroke_gray_solid_white);
                holder.item_button1_tv.setTextColor(mContext.getResources().getColor(R.color.text_font_gray));
                holder.item_button2_tv.setText("  接单  ");
                break;
            case "已完成":
                if(listData.get(position).getReview_status().equals("已评价")){
                    holder.item_button1_tv.setBackgroundResource(R.drawable.shape_stroke_green_solid_white);
                    holder.item_button1_tv.setTextColor(mContext.getResources().getColor(R.color.text_font_green));

                    holder.item_button2_tv.setText("评论查看");
                    holder.item_button2_tv.setBackgroundResource(R.drawable.shape_stroke_pink_solid_white);
                    holder.item_button2_tv.setTextColor(mContext.getResources().getColor(R.color.text_font_pink));
                }else{
                    holder.item_button1_tv.setBackgroundResource(R.drawable.shape_stroke_green_solid_white);
                    holder.item_button1_tv.setTextColor(mContext.getResources().getColor(R.color.text_font_green));

                    holder.item_button2_tv.setText("尚未评论");
                    holder.item_button2_tv.setBackgroundResource(R.drawable.shape_stroke_gray_solid_white);
                    holder.item_button2_tv.setTextColor(mContext.getResources().getColor(R.color.text_font_gray));
                }
                break;
        }

        adapter.setItemClickListener(new ItemMultClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                if(mItemClickListener != null){
                    Intent intent = new Intent(mContext, OrderDetailActivity.class);
                    intent.putExtra(Constant.CONFIG_INTENT_ORDER_ID,listData.get(position).getId()+"");
                    mContext.startActivity(intent);
                }
            }

            @Override
            public void onItemSubViewClick(int choice, int postion) {

            }
        });

        holder.item_button1_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    switch (listData.get(position).getStatus()){
                        case "待接单":
                            mItemClickListener.onItemMulViewClick(type, 0, position);
                            break;
                        case "待发货":
                            mItemClickListener.onItemMulViewClick(type, 0, position);
                            break;
                        case "待收货":
                            mItemClickListener.onItemMulViewClick(type, 1, position);
                            break;
                        case "已完成":
                            if(listData.get(position).getReview_status().equals("已评价")) {
                                mItemClickListener.onItemMulViewClick(type, 5, position);
                            }else{
                                mItemClickListener.onItemMulViewClick(type, 4, position);
                            }
                            break;
                    }
                }
            }
        });

        holder.item_button2_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    switch (listData.get(position).getStatus()){
                        case "待接单":
                            mItemClickListener.onItemMulViewClick(type, 8, position);
                            break;
                        case "待发货":
                            mItemClickListener.onItemMulViewClick(type, 2, position);
                            break;
                        case "待收货":
                            mItemClickListener.onItemMulViewClick(type, 3, position);
                            break;
                        case "待评价":
                            mItemClickListener.onItemMulViewClick(type, 6, position);
                            break;
                        case "已完成":
                            if(listData.get(position).getReview_status().equals("已评价")) {
                                mItemClickListener.onItemMulViewClick(type, 7, position);
                            }else{
                                mItemClickListener.onItemMulViewClick(type, 6, position);
                            }
                            break;
                    }
                }
            }
        });

        return convertView;
    }

    public static class ViewHolder {
        public ListView item_goods_lv;
        public RelativeLayout item_goods_linear;

        public TextView item_code_tv;
        public TextView item_number_tv;
        public TextView item_price_tv;
        public TextView item_remark_tv;
        public TextView item_date_tv;

        public TextView item_button1_tv;
        public TextView item_button2_tv;

        public TextView item_name_tv;
        public TextView item_phone_tv;
        public TextView item_address_tv;

        public ImageView item_phone_iv;
        public ImageView item_delete_iv;
    }

}
