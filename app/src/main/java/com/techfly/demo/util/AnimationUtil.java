/*
 * Description: Main menu
 * 
 * Programmed by Jie Zhuang
 * 
 * (c) 2013, CeresLink IT Co.Ltd
 */

package com.techfly.demo.util;

import android.app.Activity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;

import com.techfly.demo.R;


/**
 * AnimationUtil:Load all animation from resource.
 */
public class AnimationUtil {

	public Activity m_activity;
	public Animation m_AniSlideIn, m_AniFlipLeftIn, m_AniFadeIn;
	public LayoutAnimationController m_listController;

	public AnimationUtil(Activity activity) {
		m_activity = activity;

		if (m_AniSlideIn == null)
			m_AniSlideIn = AnimationUtils.loadAnimation(m_activity,
					R.anim.slidein);

		if (m_AniFlipLeftIn == null)
			m_AniFlipLeftIn = AnimationUtils.loadAnimation(m_activity,
					R.anim.flip_left_in);

		if (m_AniFadeIn == null)
			m_AniFadeIn = AnimationUtils.loadAnimation(m_activity,
					R.anim.fade_in);

	}


	public void loadListControl() {
		AnimationSet set = new AnimationSet(true);

		Animation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(50);
		set.addAnimation(animation);

		animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
				-1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		animation.setDuration(100);
		set.addAnimation(animation);

		m_listController = new LayoutAnimationController(set, 0.5f);
	}

}
