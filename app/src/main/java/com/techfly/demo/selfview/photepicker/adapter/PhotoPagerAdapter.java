package com.techfly.demo.selfview.photepicker.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.techfly.demo.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by donglua on 15/6/21.
 */
public class PhotoPagerAdapter extends PagerAdapter {

    private List<String> paths = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    Bitmap bm;
    public PhotoPagerAdapter(Context mContext, List<String> paths) {
        this.mContext = mContext;
        this.paths = paths;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View itemView = mLayoutInflater.inflate(R.layout.item_pager, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.iv_pager);

        final String path = paths.get(position);
        final Uri uri;
        if (path.startsWith("http")) {
            uri = Uri.parse(path);
            ///////////加载图片并获得bitmap
            Glide.with(mContext).load(path).asBitmap().listener(new RequestListener<String, Bitmap>() {
                @Override
                public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                    return false;
                }

                @Override
                public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                    bm=resource;
                    return false;
                }
            }).placeholder(R.drawable.def_photo)
                    .error(R.drawable.def_photo).into(imageView);
        } else {
            uri = Uri.fromFile(new File(path));
            Glide.with(mContext)
                .load(uri)
                //*.override(800, 800)*//*.placeholder(R.drawable.def_photo)
                    .placeholder(R.drawable.def_photo).error(R.drawable.def_photo)
                .into(imageView);
        }
        imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               if (!((Activity) mContext).isFinishing()) {
                   ((Activity) mContext).onBackPressed();
               }
           }
       });

        container.addView(itemView);
        return itemView;
    }

    @Override
    public int getCount() {
        return paths.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
