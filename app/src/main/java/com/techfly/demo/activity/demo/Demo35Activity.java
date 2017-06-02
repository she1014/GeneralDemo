package com.techfly.demo.activity.demo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.techfly.demo.R;
import com.techfly.demo.selfview.imagestack.Template1Fragment;

public class Demo35Activity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_demo35);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new Template1Fragment()).commit();
		}
	}
}
