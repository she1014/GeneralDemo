package com.techfly.demo.adpter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.techfly.demo.util.ImageLoaderUtil;

public class ViewHolder {

    private SparseArray<View> mViews;
    private View mConvertView;
    private int mPosition;

    public ViewHolder(Context context, ViewGroup parent, int layoutId,
                      int positon) {
        this.mPosition = positon;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        mConvertView.setTag(this);
    }

    /**
     * 拿到�??个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder getHolder(View convertView, Context context,
                                       ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        }
        return (ViewHolder) convertView.getTag();
    }

    public <T extends View> T getView(int viewId) {
        View view = mConvertView.findViewById(viewId);
        if (view == null) {
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public <T extends ViewGroup> T getGroup(int viewId) {
        ViewGroup viewGroup = (ViewGroup) mConvertView.findViewById(viewId);
        if (viewGroup == null) {
            mViews.put(viewId, viewGroup);
        }
        return (T) viewGroup;
    }

    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    ;

    public ViewHolder setRating(int viewId, float score) {
        RatingBar bar = getView(viewId);
        bar.setRating(score);
        return this;
    }

    ;

    public ViewHolder setImageResource(int viewId, int imageId) {
        ImageView view = getView(viewId);
        view.setImageResource(imageId);
        return this;
    }

    ;

    public ViewHolder setImageResource(String url, int viewId) {
//		AppLog.Logd("Shi", "imageUrl==" + url);
        ImageView view = getView(viewId);
        ImageLoader.getInstance().displayImage(url, view,
                ImageLoaderUtil.mHallIconLoaderOptions);
        return this;
    }

    ;

    public int getPosition() {
        return mPosition;
    }

    public View getConvertView() {
        return mConvertView;
    }
}
