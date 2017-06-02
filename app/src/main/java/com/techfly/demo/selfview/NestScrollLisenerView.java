package com.techfly.demo.selfview;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;

/**
 * 有弹性的ScrollView 实现下拉弹回和上拉弹回
 */
public class NestScrollLisenerView extends NestedScrollView {

	private OnScrollChangedCallback mOnScrollChangedCallback;

	public NestScrollLisenerView(Context context) {
		super(context);
	}

	public NestScrollLisenerView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if (mOnScrollChangedCallback != null) {
			mOnScrollChangedCallback.onScroll(l, t);
		}
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
