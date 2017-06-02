package com.techfly.demo.selfview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import com.techfly.demo.util.LogsUtil;

/**
 * Created by ssm on 2016/12/1.
 */
public class MyHorizontalScrollView extends HorizontalScrollView{

    private OnScrollChangedCallback mOnScrollChangedCallback;

    public MyHorizontalScrollView(Context context) {
        super(context);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs,
                                  int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScrollViewListener(OnScrollChangedCallback scrollViewListener) {
        this.mOnScrollChangedCallback = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (mOnScrollChangedCallback != null) {
            mOnScrollChangedCallback.onScroll(x, y);

        }
    }



    public void setOnScrollChangedCallback(
            final OnScrollChangedCallback onScrollChangedCallback) {
        mOnScrollChangedCallback = onScrollChangedCallback;
    }

    /**
     * Impliment in the activity/fragment/view that you want to listen to the webview
     */
    public static interface OnScrollChangedCallback {
        public void onScroll(int dx, int dy);
    }
}
