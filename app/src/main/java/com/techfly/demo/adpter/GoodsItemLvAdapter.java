package com.techfly.demo.adpter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.techfly.demo.R;
import com.techfly.demo.bean.OrderDetailBean;
import com.techfly.demo.interfaces.ItemMultClickListener;
import com.techfly.demo.util.ImageLoaderUtil;

import java.util.List;

/*
 * 详情-水果列表
 */
public class GoodsItemLvAdapter extends BaseAdapter {
    private Context mContext;
    private List<OrderDetailBean.DataEntity.GoodsInfoEntity> listData;
    private LayoutInflater layoutInflater;
    private ItemMultClickListener mItemClickListener;
    private Boolean isShowEvaluate = false;

    public GoodsItemLvAdapter(Context context, List<OrderDetailBean.DataEntity.GoodsInfoEntity> list, Boolean isShow) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.listData = list;
        this.isShowEvaluate = isShow;
    }

    /*public GoodsItemLvAdapter(Context context, List<OrderDetailBean.DataEntity.GoodsEntity> list) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.listData = list;
    }*/

    public void addAll(List<OrderDetailBean.DataEntity.GoodsInfoEntity> info) {
        listData.addAll(info);
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemMultClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


    public void clearAll(){
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
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_goods_lv, null);

            holder.item_pic_iv = (ImageView) convertView.findViewById(R.id.item_pic_iv);
            holder.item_title_tv = (TextView)convertView.findViewById(R.id.item_title_tv);

            holder.item_price_tv = (TextView)convertView.findViewById(R.id.item_price_tv);

            holder.item_sum_tv = (TextView)convertView.findViewById(R.id.item_sum_tv);

            holder.item_style_tv = (TextView)convertView.findViewById(R.id.item_style_tv);
            holder.item_descrip_tip = (TextView)convertView.findViewById(R.id.item_descrip_tip);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String url = listData.get(position).getImg();
        if(TextUtils.isEmpty(url)){
            url = "";
        }
        ImageLoader.getInstance().displayImage(url, holder.item_pic_iv, ImageLoaderUtil.mEmptyIconLoaderOptions);

        holder.item_title_tv.setText(listData.get(position).getTitle());

        holder.item_price_tv.setText(listData.get(position).getPrice()+"");
        holder.item_sum_tv.setText("x" + listData.get(position).getCount());


        String style = "";
        String[] bigArray = listData.get(position).getSpeces().split(",");
        for(int i = 0;i < bigArray.length;i++){
            String[] smallArray = bigArray[i].split(":");
            if(i == bigArray.length - 1 && smallArray.length > 1){
                style = style + smallArray[1];
            }else if(smallArray.length > 1){
                style = style + smallArray[1] + ",";
            }
        }
        holder.item_style_tv.setText(style);
//        holder.item_style_tv.setText(listData.get(position).getSpeces());

        holder.item_descrip_tip.setVisibility(View.INVISIBLE);

        return convertView;
    }

    public class ViewHolder {

        public ImageView item_pic_iv;
        public TextView item_title_tv;

        public TextView item_price_tv;

        public TextView item_sum_tv;

        public TextView item_style_tv;
        public TextView item_descrip_tip;


    }

}
