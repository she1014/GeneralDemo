package com.techfly.demo.adpter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.techfly.demo.R;
import com.techfly.demo.bean.DemoBean;
import com.techfly.demo.interfaces.ItemMultClickListener;
import com.techfly.demo.selfview.photepicker.PhotoPagerActivity;
import com.techfly.demo.selfview.photepicker.PhotoSlideActivity;
import com.techfly.demo.selfview.richeditor.RichTextEditor;
import com.techfly.demo.util.CommonUtils;
import com.techfly.demo.util.LogsUtil;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/*
 */
public class NineGridLvAdapter extends BaseAdapter {

    private Context mContext;
    private List<DemoBean> listData;// = new ArrayList<List<GoodsBean>>();
    private LayoutInflater layoutInflater;
    private ItemMultClickListener mItemClickListener;

    public NineGridLvAdapter(Context context, List<DemoBean> list) {
        this.layoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.listData = list;
    }

    public void addAll(List<DemoBean> info) {
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
            convertView = layoutInflater.inflate(R.layout.item_nine_pic, null);
            holder = new ViewHolder();

            holder.item_avator_iv = (CircleImageView)convertView.findViewById(R.id.item_avator_iv);
            holder.item_avator_type_iv = (ImageView)convertView.findViewById(R.id.item_avator_type_iv);

            holder.item_name_tv = (TextView)convertView.findViewById(R.id.item_name_tv);
            holder.item_sub_title_tv = (TextView)convertView.findViewById(R.id.item_sub_title_tv);

            holder.item_content_tv = (TextView)convertView.findViewById(R.id.item_content_tv);
            holder.item_content_ret = (RichTextEditor)convertView.findViewById(R.id.item_content_ret);

            holder.item_grid_pic_niv = (NineGridImageView)convertView.findViewById(R.id.item_grid_pic_niv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Glide.with(mContext).load(listData.get(position).getTv1()).into(holder.item_avator_iv);
        Glide.with(mContext).load(R.drawable.icon_v_blue).into(holder.item_avator_type_iv);

        holder.item_name_tv.setText(listData.get(position).getTv3());
        holder.item_sub_title_tv.setText(listData.get(position).getTv4());

        holder.item_content_tv.setText(listData.get(position).getTv5());

        holder.item_content_ret.insertContent(listData.get(position).getTv5());

        NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, String s) {
                /*Picasso.with(context)
                        .load(s)
                        .placeholder(R.drawable.ic_default_image)
                        .into(imageView);*/
                Glide.with(mContext).load(s).placeholder(R.drawable.icon_defualt_null).error(R.drawable.icon_def_photo).into(imageView);
            }

            @Override
            protected ImageView generateImageView(Context context) {
                return super.generateImageView(context);
            }

            @Override
            protected void onItemImageClick(Context context, int index, List<String> list) {
//                ToastUtil.DisplayToast(context, "点击了:" + index);
//                Toast.makeText(context, "image position is " + index, Toast.LENGTH_SHORT).show();

                ArrayList<String> picList = new ArrayList<String>();
                for(int i = 0;i < list.size();i++){
                    picList.add(list.get(i));
                }

                LogsUtil.normal("picList.size()="+picList.size());

                Intent intent = new Intent(context, PhotoSlideActivity.class);
                intent.putExtra(PhotoPagerActivity.EXTRA_CURRENT_ITEM, index);
                intent.putExtra(PhotoPagerActivity.EXTRA_PHOTOS, picList);
                intent.putExtra(PhotoPagerActivity.EXTRA_EDITABLE, false);
                context.startActivity(intent);

            }
        };
        holder.item_grid_pic_niv.setAdapter(mAdapter);

        holder.item_grid_pic_niv.setImagesData(CommonUtils.arrayToList(listData.get(position).getPicArray(),position));


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

        public CircleImageView item_avator_iv;
        public ImageView item_avator_type_iv;

        public TextView item_name_tv;
        public TextView item_sub_title_tv;

        public TextView item_content_tv;
        public RichTextEditor item_content_ret;

        public NineGridImageView item_grid_pic_niv;



    }

}
