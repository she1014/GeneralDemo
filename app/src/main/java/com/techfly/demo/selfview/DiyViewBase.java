package com.techfly.demo.selfview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.techfly.demo.util.LogsUtil;

/**
 * Created by ssm on 2017/2/16.
 */
public class DiyViewBase extends View{

    public DiyViewBase(Context context, AttributeSet attrs) {
        super(context, attrs);
        LogsUtil.normal("DiyView");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LogsUtil.normal("onFinishInflate");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogsUtil.normal("onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogsUtil.normal("onLayout");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        LogsUtil.normal("onSizeChanged");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogsUtil.normal("onDraw");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LogsUtil.normal("onKeyDown");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        LogsUtil.normal("onKeyUp");
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        LogsUtil.normal("onTrackballEvent");
        return super.onTrackballEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogsUtil.normal("onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        LogsUtil.normal("onFocusChanged");
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        LogsUtil.normal("onWindowFocusChanged");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LogsUtil.normal("onAttachedToWindow");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LogsUtil.normal("onDetachedFromWindow");
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        LogsUtil.normal("onWindowVisibilityChanged");
    }
}

