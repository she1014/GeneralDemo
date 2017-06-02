package com.techfly.demo.util;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.techfly.demo.R;

import java.util.Random;

public class ToastUtil {


	
	private static Toast myToast;
	/*private static Handler mhandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				myToast.setText(str);
				myToast.show();
				break;
			case 2:
				myToast = Toast.makeText(mContext,str,Toast.LENGTH_SHORT);
				myToast.show();
				break;

			default:
				break;
			}
		}
	};*/

	/*
     * 测试时用于打印错误，发布后注销
     */
	public static void DisplayToastDebug(Context mContext, String str) {
		if (myToast != null) {
			myToast.setText(str);
		} else {
			myToast = Toast.makeText(mContext, str, Toast.LENGTH_LONG);
		}
		myToast.setGravity(Gravity.BOTTOM, 0, 0);
		myToast.show();
	}

	public static void DisplayToast(Context mContext,String str){
//		Message msg = new Message();
		if(myToast != null){
			myToast.setText(str);
		}else{
			myToast = Toast.makeText(mContext,str,Toast.LENGTH_SHORT);
		}
		myToast.setGravity(Gravity.CENTER, 0, 0);
		myToast.show();
	}

	public static void DisplayToast(Context mContext,String str,int length){
//		Message msg = new Message();
		if(myToast != null){
			myToast.setText(str);
		}else{
			myToast = Toast.makeText(mContext,str,Toast.LENGTH_LONG);
		}
		myToast.setGravity(Gravity.CENTER, 0, 0);
		myToast.show();
	}

	public static void DisplayStyle(Context mContext, String str)
	{
		if(myToast != null){
			myToast.setText(str);
		}else{
			myToast = Toast.makeText(mContext,str,Toast.LENGTH_SHORT);
		}

		ViewGroup localViewGroup = (ViewGroup)myToast.getView();
		TextView localTextView = (TextView)localViewGroup.getChildAt(0);
		localTextView.setPadding(20, 5, 20, 5);//int left, int top, int right, int bottom
		localTextView.setTextColor(mContext.getResources().getColor(R.color.white));

		localViewGroup.setBackgroundResource(R.drawable.shape_stroke_empty_solid_red);

		Random rand = new Random();
		LogsUtil.normal("随机数:" + rand.nextInt(256));

		localViewGroup.setBackgroundResource(R.drawable.shape_stroke_empty_solid_empty);
		localViewGroup.setBackgroundColor(Color.argb(255, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));

//		localViewGroup.setBackgroundColor(Color.argb(0,rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)));

		localViewGroup.setLeft(20);
		localViewGroup.setRight(20);
		localViewGroup.setTop(20);
		localViewGroup.setBottom(20);

		myToast.setGravity(Gravity.CENTER, 0, 0);
		myToast.show();
	}
	
}
