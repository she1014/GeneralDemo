package com.techfly.demo.guidepage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.techfly.demo.R;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.PermissionUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 功能：引导页+开机画面
 * 引导页：首次使用时显示引导页
 * 开机画面:每次使用显示2s的开机画面
 */
public class GuideActivity extends Activity implements ViewPager.OnPageChangeListener {

	private ViewPager vp;
	private ViewPagerAdapter vpAdapter;
	private List<View> views;
	// 底部小点图片
	private ImageView[] dots;
	// 记录当前选中位置
	private int currentIndex;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		
		// 初始化页面
		initViews();

		// 初始化底部小点
		initDots();

	}

	private void initViews() {
		this.context = GuideActivity.this;
		
		LayoutInflater inflater = LayoutInflater.from(this);

		views = new ArrayList<View>();
		// 初始化引导图片列表
		views.add(inflater.inflate(R.layout.layout_guide_one, null));
		views.add(inflater.inflate(R.layout.layout_guide_two, null));
		views.add(inflater.inflate(R.layout.layout_guide_three, null));

		// 初始化Adapter
		vpAdapter = new ViewPagerAdapter(views, this,context);
		
		vp = (ViewPager) findViewById(R.id.viewpager);
		vp.setAdapter(vpAdapter);
		// 绑定回调
		vp.setOnPageChangeListener(this);
	}

	private void initDots() {
		LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
		dots = new ImageView[views.size()];

		// 循环取得小点图片
		for (int i = 0; i < views.size(); i++) {
			dots[i] = (ImageView) ll.getChildAt(i);
			dots[i].setEnabled(true);// 都设为灰色
		}
		currentIndex = 0;
		dots[currentIndex].setEnabled(false);// 设置为白色，即选中状态
	}

	private void initPermission(){
		PermissionUtils utils = new PermissionUtils(this);
		utils.needPermission(2000);
	}

	private void setCurrentDot(int position) {
		if (position < 0 || position > views.size() - 1
				|| currentIndex == position) {
			return;
		}

		dots[position].setEnabled(false);
		dots[currentIndex].setEnabled(true);

		currentIndex = position;
	}

	// 当滑动状态改变时调用
	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	// 当当前页面被滑动时调用
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	// 当新的页面被选中时调用
	@Override
	public void onPageSelected(int arg0) {
		// 设置底部小点选中状态
		LogsUtil.normal("arg0="+arg0);
		setCurrentDot(arg0);
		if(arg0 == 2){
			initPermission();
		}
	}

}
