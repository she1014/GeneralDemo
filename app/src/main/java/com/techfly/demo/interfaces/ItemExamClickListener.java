package com.techfly.demo.interfaces;

import android.view.View;

/**
 * item点击回调接口
 * 
 * @author wen_er
 * 
 */
public interface ItemExamClickListener {

	/**
	 * Item 普通点击
	 */

	public void onItemClick(View view, int postion);

	/**
	 * Item 长按
	 */

//	public void onItemLongClick(View view, int postion);

	/**
	 * Item 内部View点击
	 */

//	public void onItemSubViewClick(View view, int postion);

	/*
	 * 选项:choice => 1,2,3,4  = A,B,C,D
	 */
	public void onItemSubViewClick(int choice, int postion);
}
