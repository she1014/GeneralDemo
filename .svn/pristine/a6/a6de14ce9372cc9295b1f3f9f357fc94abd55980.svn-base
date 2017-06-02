package com.techfly.demo.selfview.wheelview;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * 有弹性的ScrollView 实现下拉弹回和上拉弹回
 */
public class CoordLayoutScrollLisener extends CoordinatorLayout {

	private OnScrollChangedCallback mOnScrollChangedCallback;

	public CoordLayoutScrollLisener(Context context) {
		super(context);
	}

	public CoordLayoutScrollLisener(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		/*if (mOnScrollChangedCallback != null) {
			mOnScrollChangedCallback.onScroll(l, t);
		}*/
	}

	@Override
	public void setOnScrollChangeListener(OnScrollChangeListener l) {
		super.setOnScrollChangeListener(l);
	}

	@Override
	public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
		super.onNestedPreScroll(target, dx, dy, consumed);
		/*if (mOnScrollChangedCallback != null) {
			mOnScrollChangedCallback.onScroll(dx, dy);
		}*/
	}

	@Override
	public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
		/*if (mOnScrollChangedCallback != null) {
			mOnScrollChangedCallback.onScroll(nestedScrollAxes, nestedScrollAxes);
		}*/
		return super.onStartNestedScroll(child, target, nestedScrollAxes);
	}

	public OnScrollChangedCallback getOnScrollChangedCallback() {
		return mOnScrollChangedCallback;
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
