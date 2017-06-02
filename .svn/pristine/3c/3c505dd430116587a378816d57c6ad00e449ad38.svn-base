package com.techfly.demo.util;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.techfly.demo.activity.base.Constant;

public class Utility {
	private static Dialog mDialog;

	/**
	 * getScreen size
	 * 
	 * @param activity
	 */
	public static void getScreenSize(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager mWm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		mWm.getDefaultDisplay().getMetrics(dm);
		Constant.SCREEN_WIDTH = dm.widthPixels;
		Constant.SCREEN_HEIGHT = dm.heightPixels;
		Constant.SCREEN_DENSITY = dm.density;
	}
}
