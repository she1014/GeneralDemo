package com.techfly.demo.adpter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.techfly.demo.R;
import com.techfly.demo.bean.ReveiwListBean;
import com.techfly.demo.interfaces.ItemMultClickListener;
import com.techfly.demo.util.ImageLoaderUtil;

import java.util.List;

/*
 * 商品-评价列表
 */
public class OrderReviewsLvAdapter extends BaseAdapter {

    private Context mContext;
    private List<ReveiwListBean.DataEntity> listData;// = new ArrayList<List<GoodsBean>>();
    private LayoutInflater layoutInflater;
    private ItemMultClickListener mItemClickListener;


    public OrderReviewsLvAdapter(Context context, List<ReveiwListBean.DataEntity> list) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.listData = list;
    }

    public void addAll(List<ReveiwListBean.DataEntity> info) {
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
            convertView = layoutInflater.inflate(R.layout.item_review_lv, null);
            holder = new ViewHolder();

            holder.item_avator_iv = (ImageView)convertView.findViewById(R.id.item_avator_iv);

            holder.item_name_tv = (TextView)convertView.findViewById(R.id.item_name_tv);
            holder.item_ratingbar = (RatingBar)convertView.findViewById(R.id.item_ratingbar);

            holder.item_content_tv = (TextView)convertView.findViewById(R.id.item_content_tv);

            holder.item_container_linear = (LinearLayout)convertView.findViewById(R.id.item_container_linear);

            holder.item_date_tv = (TextView)convertView.findViewById(R.id.item_date_tv);

            holder.item_pic_iv = (ImageView)convertView.findViewById(R.id.item_pic_iv);
            holder.item_title_tv = (TextView)convertView.findViewById(R.id.item_title_tv);
            holder.item_price_tv = (TextView)convertView.findViewById(R.id.item_price_tv);
            holder.item_style_tv = (TextView)convertView.findViewById(R.id.item_style_tv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String url = listData.get(position).getAvatar();
        if(TextUtils.isEmpty(url)){
            url = "";
        }
        ImageLoader.getInstance().displayImage(url, holder.item_avator_iv, ImageLoaderUtil.mAvatarIconLoaderOptions);

        holder.item_name_tv.setText(listData.get(position).getMobile());

        holder.item_ratingbar.setRating(Float.parseFloat(listData.get(position).getMark()+""));
        holder.item_content_tv.setText(listData.get(position).getContent());

        String[] date = listData.get(position).getCreate_time().split(" ");
        if(date.length > 1){
            holder.item_date_tv.setText(date[0]);
        }

        String pic = listData.get(position).getGoods_img();
        if(TextUtils.isEmpty(pic)){
            pic = "";
        }
        ImageLoader.getInstance().displayImage(pic, holder.item_pic_iv, ImageLoaderUtil.mAvatarIconLoaderOptions);
        holder.item_title_tv.setText(listData.get(position).getGoods_name());
//        holder.item_price_tv.setText(listData.get(position).getContent());
        holder.item_style_tv.setText(listData.get(position).getSpeces());



        String imags = listData.get(position).getAvatar();
        /*imags = imags.replace("[]", "");
        if(TextUtils.isEmpty(imags)){
            holder.item_container_rl.setVisibility(View.GONE);
        }else{
            holder.item_container_rl.setVisibility(View.VISIBLE);
            //图片部分
            holder.item_container_linear.removeAllViews();

            String[] imagesArray = listData.get(position).getImages().split(" ");
            final ArrayList<String> picList = new ArrayList<String>();
            Collections.addAll(picList, imagesArray);

            int indicate = 0;  //当前图片
            for(String imgUrl:picList){
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
        }*/

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
        public ImageView item_avator_iv;

        public TextView item_name_tv;
        public RatingBar item_ratingbar;

        public TextView item_content_tv;

        public LinearLayout item_container_linear;
        public TextView item_date_tv;


        public ImageView item_pic_iv;
        public TextView item_title_tv;
        public TextView item_price_tv;
        public TextView item_style_tv;

    }

}
