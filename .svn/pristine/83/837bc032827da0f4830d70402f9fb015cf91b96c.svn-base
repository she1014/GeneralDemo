package com.techfly.demo.adpter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.techfly.demo.R;
import com.techfly.demo.bean.ShopGoodsBean;
import com.techfly.demo.interfaces.ItemMultClickListener;
import com.techfly.demo.util.ImageLoaderUtil;

import java.util.List;

/*
 * 商品管理
 */
public class GoodsManageAdapter extends BaseAdapter {

    private Context mContext;
    private List<ShopGoodsBean.DataEntity.DatasEntity> listData;// = new ArrayList<List<GoodsBean>>();
    private LayoutInflater layoutInflater;
    private ItemMultClickListener mItemClickListener;
    private int type = -1;//1-下架；2-上架

    public GoodsManageAdapter(Context context, List<ShopGoodsBean.DataEntity.DatasEntity> list, int type) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.listData = list;
        this.type = type;
    }

    public void addAll(List<ShopGoodsBean.DataEntity.DatasEntity> info) {
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
            convertView = layoutInflater.inflate(R.layout.item_goods_manage_lv, null);
            holder = new ViewHolder();

            holder.item_pic_iv = (ImageView)convertView.findViewById(R.id.item_pic_iv);

            holder.item_title_tv = (TextView)convertView.findViewById(R.id.item_title_tv);
            holder.item_descrip_tv = (TextView)convertView.findViewById(R.id.item_descrip_tv);
            holder.item_ratingbar = (RatingBar)convertView.findViewById(R.id.item_ratingbar);
            holder.item_ratingbar_tv = (TextView)convertView.findViewById(R.id.item_ratingbar_tv);

            holder.item_lable_tv = (TextView)convertView.findViewById(R.id.item_lable_tv);
            holder.item_price_tv = (TextView)convertView.findViewById(R.id.item_price_tv);
            holder.item_overdue_tv = (TextView)convertView.findViewById(R.id.item_overdue_tv);

            holder.item_modify_tv = (TextView)convertView.findViewById(R.id.item_modify_tv);
            holder.item_off_shelf_tv = (TextView)convertView.findViewById(R.id.item_off_shelf_tv);
            holder.item_delete_tv = (TextView)convertView.findViewById(R.id.item_delete_tv);

            holder.item_category_tv = (TextView)convertView.findViewById(R.id.item_category_tv);
            holder.item_style_tv = (TextView)convertView.findViewById(R.id.item_style_tv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String url = listData.get(position).getImg();
        if(TextUtils.isEmpty(url)){
            url = "";
        }
        ImageLoader.getInstance().displayImage(url, holder.item_pic_iv, ImageLoaderUtil.mEmptyIconLoaderOptions);

        holder.item_title_tv.setText(listData.get(position).getGoods_name()+"");
        holder.item_category_tv.setText(listData.get(position).getCategroy_name()+"");

        if(listData.get(position).getGoods_speces().size() > 1){
            holder.item_style_tv.setText("多规格");
        }else if(listData.get(position).getGoods_speces().size() == 1){
            holder.item_style_tv.setText(listData.get(position).getGoods_speces().get(0).getTitle());
        }

//        holder.item_lable_tv.setText(listData.get(position).getPrice_label());
//        holder.item_price_tv.setText(""+listData.get(position).getPrice());
//        holder.item_descrip_tv.setText(listData.get(position).getFeature_labels());
//        holder.item_overdue_tv.setText("门市价¥"+listData.get(position).getMarket_price());
//        holder.item_ratingbar.setRating(Float.parseFloat(listData.get(position).getMark() + ""));
//        holder.item_ratingbar_tv.setText(listData.get(position).getMark() + "分");

        if(type == 1){
            holder.item_off_shelf_tv.setText("下架");
        }else if(type == 2){
            holder.item_off_shelf_tv.setText("上架");
        }

        holder.item_modify_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemSubViewClick(0, position);
                }
            }
        });

        holder.item_off_shelf_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mItemClickListener != null){
                    mItemClickListener.onItemSubViewClick(type, position);//1-下架;2-上架
                }
            }
        });

        holder.item_delete_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemSubViewClick(3, position);
                }
            }
        });

        return convertView;
    }

    public static class ViewHolder {

        public ImageView item_pic_iv;

        public TextView item_title_tv;

        public TextView item_descrip_tv;
        public RatingBar item_ratingbar;
        public TextView item_ratingbar_tv;

        public TextView item_lable_tv;
        public TextView item_price_tv;
        public TextView item_overdue_tv;

        public TextView item_modify_tv;
        public TextView item_off_shelf_tv;
        public TextView item_delete_tv;

        public TextView item_category_tv;
        public TextView item_style_tv;

    }

}
