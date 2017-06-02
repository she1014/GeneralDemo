package com.techfly.demo.util;

import android.graphics.Bitmap.Config;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.techfly.demo.R;

public class ImageLoaderUtil {

    public static DisplayImageOptions mUserIconLoaderOptions = new
            DisplayImageOptions
                    .Builder().showImageForEmptyUri(R.drawable.icon_default_avatar)
            .bitmapConfig(Config
                    .RGB_565).showImageOnFail(R.drawable.icon_default_avatar).cacheInMemory
                    (true).cacheOnDisk(true)
            .showImageOnLoading(R.drawable.icon_default_avatar).displayer(new
                    SimpleBitmapDisplayer()).build();


    public static DisplayImageOptions mHallIconLoaderOptions = new DisplayImageOptions.Builder()
            .showImageForEmptyUri(R.drawable.ic_hall_no_url)
            .showImageOnLoading(R.drawable.ic_hall_no_url)
            .bitmapConfig(Config.RGB_565)
            .showImageOnFail(R.drawable.ic_hall_no_url).cacheInMemory(true)
            .cacheOnDisk(true).displayer(new SimpleBitmapDisplayer()).build();


    public static DisplayImageOptions mGridIconLoaderOptions = new DisplayImageOptions.Builder()
            .showImageForEmptyUri(R.drawable.ic_hall_no_url)
            .showImageOnLoading(R.drawable.ic_hall_no_url)
            .bitmapConfig(Config.RGB_565)
            .showImageOnFail(R.drawable.ic_hall_no_url).cacheInMemory(true)
            .cacheOnDisk(true).displayer(new RoundedBitmapDisplayer(10)).build();

    public static DisplayImageOptions mOrderServiceIconLoaderOptions = new DisplayImageOptions.Builder()
            .showImageForEmptyUri(R.drawable.ic_hall_no_url)
            .showImageOnLoading(R.drawable.ic_hall_no_url)
            .bitmapConfig(Config.RGB_565)
            .showImageOnFail(R.drawable.ic_hall_no_url).cacheInMemory(true)
            .cacheOnDisk(true).displayer(new RoundedBitmapDisplayer(10)).build();

    public static DisplayImageOptions mBannerLoaderOptions = new DisplayImageOptions.Builder()
            .showImageForEmptyUri(R.drawable.icon_defualt_null)
            .showImageOnLoading(R.drawable.icon_defualt_null)
            .bitmapConfig(Config.RGB_565)
            .showImageOnFail(R.drawable.icon_defualt_null).cacheInMemory(true)
            .cacheOnDisk(true).displayer(new SimpleBitmapDisplayer()).build();

    public static DisplayImageOptions mDetailsLoaderOptions = new DisplayImageOptions.Builder()
            .showImageForEmptyUri(R.drawable.icon_service_details_default)
            .showImageOnLoading(R.drawable.icon_service_details_default)
            .bitmapConfig(Config.RGB_565)
            .showImageOnFail(R.drawable.icon_service_details_default).cacheInMemory(true)
            .cacheOnDisk(true).displayer(new SimpleBitmapDisplayer()).build();

    public static DisplayImageOptions mEmptyIconLoaderOptions = new DisplayImageOptions.Builder()
            .showImageOnLoading(R.drawable.icon_defualt_null) //设置图片在下载期间显示的图片
            .showImageForEmptyUri(R.drawable.icon_defualt_null)//设置图片Uri为空或是错误的时候显示的图片
            .showImageOnFail(R.drawable.icon_defualt_null) //设置图片加载/解码过程中错误时候显示的图片
            .cacheInMemory(true)//设置下载的图片是否缓存在内存中
            .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
            .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
            .displayer(new RoundedBitmapDisplayer(0))//是否设置为圆角，弧度为多少
            .build();//构建完成

        public static DisplayImageOptions mAvatarIconLoaderOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.icon_defualt_null) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.icon_defualt_null)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.icon_defualt_null) //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .displayer(new RoundedBitmapDisplayer(100))//是否设置为圆角，弧度为多少
                .build();//构建完成

}
