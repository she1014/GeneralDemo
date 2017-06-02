package com.techfly.demo.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import com.techfly.demo.R;
import com.techfly.demo.activity.application.MyApplication;


public class DisplayUtil {

    /**
     * 屏幕宽度
     */
    private static int DisplayWidthPixels = 0;
    /**
     * 屏幕高度
     */
    private static int DisplayheightPixels = 0;

    private static int gridViewItemWidth = 0;

    private static int SmallSizeGridViewItemWidth = 0;

    private static int RecommendChannelPicWidth = 0;
    private static int RecommendUserPicWidth = 0;
    private static int PointD = 0;
    private static int PhotoWidth = 0;
    private static int smallPic;
    private static int middlePic;
    private static int largePic;
    private static int thumbnailWidth;
    public static final int commonWidth = 480;
    public static final int commonHeight = 800;
    /**
     * 获取屏幕参数
     *
     * @param context
     */
    private static void getDisplayMetrics(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        DisplayWidthPixels = dm.widthPixels;// 宽度
        DisplayheightPixels = dm.heightPixels;// 高度
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getDisplayWidthPixels(Context context) {
        if (context == null) {
            return -1;
        }
        if (DisplayWidthPixels == 0) {
            getDisplayMetrics(context);
        }
        return DisplayWidthPixels;
    }

    public static int getSmallPicSize(){
        if(smallPic==0){
            smallPic = Math.min(getDisplayWidthPixels(MyApplication.getApp()) / 4, 192);
        }
        return smallPic;
    }

    public static int getMiddlePicSize(){
        if(middlePic==0) middlePic = (int) Math.min((getDisplayWidthPixels(MyApplication.getApp()) / 1.1), 698);
        return middlePic;
    }

    public static int getLargePicSize(){
        if(largePic==0)largePic = Math.min((int) (getDisplayWidthPixels(MyApplication.getApp()) * 1.1), 850);
        return largePic;
    }

    public static String pic2Small(String uri){
        return formatImgUri(uri,0);
    }

    public static String pic2Middle(String uri){
        return formatImgUri(uri,1);
    }

    public static String pic2Large(String uri){
        return formatImgUri(uri,2);
    }

    public static String formatImgUri(String uri,int size){
        String end ;
        switch (size){
            case 0:
                end = getSmallPicSize()+"x"+getSmallPicSize();
                break;
            case 1:
                end = getMiddlePicSize()+"x"+getMiddlePicSize();
                break;
            default:
                end = getLargePicSize()+"x"+getLargePicSize();
                break;
        }
        return uri+"_"+end;
    }




    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getDisplayheightPixels(Context context) {
        if (context == null) {
            return -1;
        }
        if (DisplayheightPixels == 0) {
            getDisplayMetrics(context);
        }
        return DisplayheightPixels;
    }

    /**
     * 将px值转换为dip或dp值
     *
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int getPointRadius(Context context) {
        if (PointD == 0) {
            PointD = getDisplayWidthPixels(context) / 65;
        }
        return PointD;
    }

    /**
     * 将dip或dp值转换为px值
     *
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        if (context == null) {
            return 0;
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将sp值转换为px值
     *
     * @param spValue
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int getChannelItemWidth(Context context) {
        if (context == null) {
            return -1;
        }
        return (int) ((getDisplayWidthPixels(context) - 2 * context.getResources().getDimension(R.dimen.marign_mid)) / 2.93);
    }

    public static int getThumbNailItemWidth(Context context) {
        if (context == null) {
            return -1;
        }
        if(thumbnailWidth ==0) thumbnailWidth = ((int) ((getDisplayWidthPixels(context) - 2 * context.getResources().getDimension(R.dimen.marign_middle))-4*context.getResources().getDimension(R.dimen.marign_mid)))/5;
        return thumbnailWidth;
    }

    public static float getBannerItemHeight(Context context) {
        if (context == null) {
            return -1;
        }
        return ((getDisplayWidthPixels(context) - context.getResources().getDimension(R.dimen.marign_mid)) * 9 / 16);
    }

    public static int getChannelRecommendItemWidth(Context context) {
        if (context == null) {
            return -1;
        }
        if (RecommendChannelPicWidth == 0) {
            RecommendChannelPicWidth = (int) ((getDisplayWidthPixels(context) - 2 * (context.getResources().getDimension(R.dimen.activity_horizontal_margin) + context.getResources().getDimension(R.dimen.marign_middle)) - 3 * context.getResources().getDimension(R.dimen.marign_mid)) / 4);
        }
        return RecommendChannelPicWidth;
    }

    public static int getUserItemHeight(Context context) {
        if (context == null) {
            return -1;
        }
        if (RecommendUserPicWidth == 0) {
            RecommendUserPicWidth = (int) (((getDisplayWidthPixels(context) - 2 * (context.getResources().getDimension(R.dimen.activity_horizontal_margin) + context.getResources().getDimension(R.dimen.marign_middle))) * 18 / 23 - 3 * context.getResources().getDimension(R.dimen.marign_mid)) / 4);
        }
        return RecommendUserPicWidth;
    }


    public static int getSmallSizeGridViewItemWidth(Context context) {
        if (context == null) {
            return -1;
        }
        if (SmallSizeGridViewItemWidth == 0) {
            SmallSizeGridViewItemWidth = (getDisplayWidthPixels(context) * 10 / 38 - 5 * dip2px(context, 1)) / 4;
        }
        return SmallSizeGridViewItemWidth;
    }

    public static int getBrowserChannelPicWidth(Context context) {
        if (context == null) {
            return -1;
        }
        if (PhotoWidth == 0) {
            PhotoWidth = RecommendChannelPicWidth = (int) ((getDisplayWidthPixels(context) - 2 * (context.getResources().getDimension(R.dimen.activity_horizontal_margin) + context.getResources().getDimension(R.dimen.marign_middle)) - 3 * context.getResources().getDimension(R.dimen.marign_mid)) / 3);
        }
        return PhotoWidth;
    }

}
