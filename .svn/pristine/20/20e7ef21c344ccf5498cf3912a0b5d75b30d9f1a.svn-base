package com.techfly.demo.adpter;

/*
 * @data:  2015年6月5日 下午2:57:16
 * @version:  V1.0
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techfly.demo.R;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.activity.order.OrderDetailActivity;
import com.techfly.demo.bean.ShopOrderListBean;
import com.techfly.demo.interfaces.ItemMoreClickListener;
import com.techfly.demo.interfaces.ItemMultClickListener;

import java.util.List;


/**
 * TODO<>
 * 果园众筹
 * @data: 2015年6月5日 下午2:57:16
 * @version: V1.0
 */
public class Demo7RvAdapter extends RecyclerView.Adapter<Demo7RvAdapter.MViewHolder> {

    private Context mContext;

    private List<ShopOrderListBean.DataEntity.DatasEntity> listData;// = new ArrayList<ShopBean>();
    private LayoutInflater layoutInflater;
    private ItemMoreClickListener mItemClickListener;

    //1-未收货；2-已收货
    private int mCurStatus = 0;

    public Demo7RvAdapter(Context context, List<ShopOrderListBean.DataEntity.DatasEntity> shopBeans, int status) {
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
    public int getItemCount() {
        // TODO Auto-generated method stub
        return listData.size();
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {

        //子项宽度充满屏幕
        MViewHolder holder =new MViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_order_pending_lv,
                viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MViewHolder mViewHolder,
                                 final int position) {

        final ShopGoodItemLvAdapter adapter = new ShopGoodItemLvAdapter(mContext, listData.get(position).getGoods_info());

        mViewHolder.item_date_tv.setText(listData.get(position).getCreate_time());
        mViewHolder.item_goods_lv.setAdapter(adapter);

        final int type = mCurStatus;

        switch (listData.get(position).getStatus()){
            case "待发货":
                mViewHolder.item_button1_tv.setBackgroundResource(R.drawable.shape_stroke_gray_solid_white);
                mViewHolder.item_button1_tv.setTextColor(mContext.getResources().getColor(R.color.text_font_gray));
                mViewHolder.item_button2_tv.setText("去发货");
                break;
            case "待收货":
                mViewHolder.item_button1_tv.setBackgroundResource(R.drawable.shape_stroke_green_solid_white);
                mViewHolder.item_button1_tv.setTextColor(mContext.getResources().getColor(R.color.text_font_green));
                mViewHolder.item_button2_tv.setText("已发货");
                break;
            case "待接单":
                mViewHolder.item_button1_tv.setBackgroundResource(R.drawable.shape_stroke_gray_solid_white);
                mViewHolder.item_button1_tv.setTextColor(mContext.getResources().getColor(R.color.text_font_gray));
                mViewHolder.item_button2_tv.setText("  接单  ");
                break;
            case "已完成":
                if(listData.get(position).getReview_status().equals("已评价")){
                    mViewHolder.item_button1_tv.setBackgroundResource(R.drawable.shape_stroke_green_solid_white);
                    mViewHolder.item_button1_tv.setTextColor(mContext.getResources().getColor(R.color.text_font_green));

                    mViewHolder.item_button2_tv.setText("评论查看");
                    mViewHolder.item_button2_tv.setBackgroundResource(R.drawable.shape_stroke_pink_solid_white);
                    mViewHolder.item_button2_tv.setTextColor(mContext.getResources().getColor(R.color.text_font_pink));
                }else{
                    mViewHolder.item_button1_tv.setBackgroundResource(R.drawable.shape_stroke_green_solid_white);
                    mViewHolder.item_button1_tv.setTextColor(mContext.getResources().getColor(R.color.text_font_green));

                    mViewHolder.item_button2_tv.setText("尚未评论");
                    mViewHolder.item_button2_tv.setBackgroundResource(R.drawable.shape_stroke_gray_solid_white);
                    mViewHolder.item_button2_tv.setTextColor(mContext.getResources().getColor(R.color.text_font_gray));
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

        mViewHolder.item_button1_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    switch (listData.get(position).getStatus()) {
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
                            if (listData.get(position).getReview_status().equals("已评价")) {
                                mItemClickListener.onItemMulViewClick(type, 5, position);
                            } else {
                                mItemClickListener.onItemMulViewClick(type, 4, position);
                            }
                            break;
                    }
                }
            }
        });

        mViewHolder.item_button2_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    switch (listData.get(position).getStatus()) {
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
                            if (listData.get(position).getReview_status().equals("已评价")) {
                                mItemClickListener.onItemMulViewClick(type, 7, position);
                            } else {
                                mItemClickListener.onItemMulViewClick(type, 6, position);
                            }
                            break;
                    }
                }
            }
        });

//        mViewHolder.item_person_tv.setText(listData.get(position).getPersons()+"人");
//        mViewHolder.item_money_tv.setText(listData.get(position).getMoney()+"");
    }

    public class MViewHolder extends ViewHolder {

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

        public MViewHolder(final View view) {
            super(view);

            this.item_goods_lv = (ListView) view.findViewById(R.id.item_goods_lv);
            this.item_goods_linear = (RelativeLayout) view.findViewById(R.id.item_goods_linear);

            this.item_code_tv = (TextView) view.findViewById(R.id.item_code_tv);
            this.item_number_tv = (TextView) view.findViewById(R.id.item_number_tv);
            this.item_price_tv = (TextView) view.findViewById(R.id.item_price_tv);
            this.item_remark_tv = (TextView)view.findViewById(R.id.item_remark_tv);
            this.item_date_tv = (TextView)view.findViewById(R.id.item_date_tv);

            this.item_button1_tv = (TextView)view.findViewById(R.id.item_button1_tv);
            this.item_button2_tv = (TextView)view.findViewById(R.id.item_button2_tv);

            this.item_name_tv = (TextView)view.findViewById(R.id.item_name_tv);
            this.item_phone_tv = (TextView)view.findViewById(R.id.item_phone_tv);
            this.item_address_tv = (TextView)view.findViewById(R.id.item_address_tv);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mItemClickListener != null){
                        mItemClickListener.onItemClick(view,getPosition());
                    }
                }
            });
        }
    }

}
