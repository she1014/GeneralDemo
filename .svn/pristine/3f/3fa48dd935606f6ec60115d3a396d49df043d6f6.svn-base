package com.techfly.demo.activity.qq_demo.view;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

/**
 * Created by Root on 2016/6/20.
 */
public class MyListView extends ListView {

    private  GestureDetectorCompat mGestureDetector;
    private int mLis_width;
    private int mLis_height;
    private View mViewUp;
    private View mViewDown;
    private float mStartY;
    private int mTotleDelY;


    public MyListView(Context context) {
        this(context, null);
    }

    public MyListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mLis_width = getMeasuredWidth();
        mLis_height = getMeasuredHeight();

        if (mViewDown == null) {
            mViewUp = new View(getContext());
            mViewUp.setLayoutParams(new LayoutParams(mLis_width, mLis_height));
            addHeaderView(mViewUp);
        }

        if (mViewDown == null) {

            mViewDown = new View(getContext());
            mViewDown.setLayoutParams(new LayoutParams(mLis_width, mLis_height));
            addFooterView(mViewDown);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {


        switch (ev.getAction()) {

            case MotionEvent.ACTION_DOWN:
                mStartY = ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:

                float movY = ev.getY();
                if (getFirstVisiblePosition() <= 1 || getLastVisiblePosition() >= getCount()) {


                    int mDelY = (int) ((movY - mStartY) / 3 + .5f);
                    scrollBy(0, -mDelY);
                    mTotleDelY += mDelY;
                    mStartY = movY;
                    return true;
                }else {
                    return false;
                }

            case MotionEvent.ACTION_UP:
                mStartY = 0;
                if (getFirstVisiblePosition() == 0) {
                    scrollBy(0, mTotleDelY);
                }
                mTotleDelY = 0;
                break;
        }
        return super.onTouchEvent(ev);
    }


}
