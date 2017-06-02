package com.techfly.demo.activity.qq_demo.view;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

public class BaozhaLayout extends FrameLayout {

	public BaozhaLayout(Context context) {
		super(context);
	}
	private int mCenterX, mCenterY;
	public void setCenter(int x, int y){
		mCenterX = x;
		mCenterY = y;
		requestLayout();
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
							int bottom) {
		View child = getChildAt(0);
		// 设置View到指定位置
		if (child != null && child.getVisibility() != GONE) {
			final int width = child.getMeasuredWidth();
			final int height = child.getMeasuredHeight();
			child.layout((int)(mCenterX - width / 2.0f), (int)(mCenterY - height / 2.0f)
					, (int)(mCenterX + width / 2.0f), (int)(mCenterY + height / 2.0f));
		}
	}

}
