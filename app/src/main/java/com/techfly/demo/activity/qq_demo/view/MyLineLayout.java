package com.techfly.demo.activity.qq_demo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Root on 2016/6/21.
 */
public class MyLineLayout extends LinearLayout{
    public MyLineLayout(Context context) {
        super(context);
    }

    public MyLineLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLineLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private SlideLayout mSlideLayout;

    public void setSlideLayout(SlideLayout slideLayout){
        mSlideLayout = slideLayout;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mSlideLayout.isMenuIsOpen()){
            return true;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mSlideLayout.isMenuIsOpen()){

            if (event.getAction() == MotionEvent.ACTION_UP){
                mSlideLayout.closeMenu();
            }
            return true;
        }
        return super.onTouchEvent(event);
    }
}
