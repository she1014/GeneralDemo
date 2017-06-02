package com.techfly.demo.guidepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.gifdecoder.GifDecoder;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.activity.user.LoginActivity;
import com.techfly.demo.util.DialogUtil;
import com.techfly.demo.util.NetWorkUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * class desc: 启动画面 (1)判断是否是首次加载应用--采取读取SharedPreferences的方法
 * (2)是，则进入GuideActivity；否，则进入MainActivity (3)3s后执行(2)操作
 */
public class SplashActivity extends BaseActivity {

    @InjectView(R.id.splash_show_iv)
    ImageView splash_show_iv;
    @InjectView(R.id.splash_jump_tv)
    TextView splash_jump_tv;

    private int m_gif_duration = 0;

    private String m_gif_url = "http://img2.ph.126.net/2I-5hMyGXRnmsfCOaVqPow==/6598092312529884560.gif";
    private String m_pic_url = "http://img.zcool.cn/community/01b705571f087d32f875a399f47c02.jpg";
    private Integer m_loc_pic = R.drawable.icon_default_show;

    private final int SPLASH_TYPE = 2;//1-本地图片；2-网络图片; 3-GIF

    boolean isFirstIn = false;

    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;
    private static final int GO_UPDATE = 1002;
    // 延迟3秒
    private static final int SPLASH_DELAY_MILLIS = 2000;
    private static final String SHAREDPREFERENCES_NAME = "first_pref";

    /**
     * Handler:跳转到不同界面
     */
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
                case GO_UPDATE:
                    if(m_gif_duration > 0){
                        mHandler.sendEmptyMessageDelayed(GO_UPDATE,1000);
                    }else{
                        goHome();
                    }

                    m_gif_duration = m_gif_duration - 1000;

                    if(m_gif_duration / 1000 >= 0){
                        splash_jump_tv.setText("跳过\t"+m_gif_duration / 1000+"s");
                    }

                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.inject(this);

        checkNetWork();

        init();
    }

    private void checkNetWork() {
        if (NetWorkUtil.isNetWorkConnected(this)) {
        } else {
            DialogUtil.showNetWorkDialog(this);
        }
    }

    /*
     * 加载Gif
     */
    private void loadGif() {
        ////加载网络gif
        /*Glide.with(this).load(m_gifUrl).listener(new RequestListener<String, GlideDrawable>() {
			@Override
			public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
				return false;
			}

			@Override
			public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
				int duration = 0;
				//计算动画时长,计算不准确，差1s
				GifDrawable drawable = (GifDrawable) resource;
				GifDecoder decoder = drawable.getDecoder();
				for (int i = 0; i < drawable.getFrameCount(); i++) {
					duration += decoder.getDelay(i);
				}
				LogsUtil.normal("duration=" + duration);
				//发送延时消息，通知动画结束
				mHandler.sendEmptyMessageDelayed(GO_HOME, duration);
				return false;
			}
		}).into(new GlideDrawableImageViewTarget(splash_show_iv,1));//加载一次*/

        //加载本地gif
        Glide.with(this).load(R.drawable.icon_demo_gif).listener(new RequestListener<Integer, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, Integer model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, Integer model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                //计算动画时长,计算不准确，差1s
                GifDrawable drawable = (GifDrawable) resource;
                GifDecoder decoder = drawable.getDecoder();
                for (int i = 0; i < drawable.getFrameCount(); i++) {
                    m_gif_duration += decoder.getDelay(i);
                }

                //由于误差,加2s
                m_gif_duration = m_gif_duration + 1000;

                splash_jump_tv.setText("跳过\t" + m_gif_duration / 1000 +"s");

                return false;
            }
        }).into(new GlideDrawableImageViewTarget(splash_show_iv, 1));//加载一次

        updateCountDown();
    }

    /*
     * 加载网络图片
     */
    private void loadNetPicture(){
        Glide.with(this).load(m_pic_url).into(splash_show_iv);
    }

    /*
     * 加载本地图片
     */
    private void loadLocPicture(){
        Glide.with(this).load(m_loc_pic).into(splash_show_iv);
    }

    private void updateCountDown(){
        mHandler.sendEmptyMessageDelayed(GO_UPDATE,1000);
    }


    private void init() {
        // 读取SharedPreferences中需要的数据
        // 使用SharedPreferences来记录程序的使用次数
        SharedPreferences preferences = getSharedPreferences(
                SHAREDPREFERENCES_NAME, MODE_PRIVATE);

        // 取得相应的值，如果没有该值，说明还未写入，用true作为默认值
        isFirstIn = preferences.getBoolean("isFirstIn", true);

        // 判断程序与第几次运行，如果是第一次运行则跳转到引导界面，否则跳转到主界面
        if (!isFirstIn) {
            //加载图片的情况
            switch (SPLASH_TYPE){
                case 1:
                    m_gif_duration = SPLASH_DELAY_MILLIS;
                    splash_jump_tv.setText("跳过\t" + m_gif_duration / 1000 +"s");

                    updateCountDown();
                    loadLocPicture();
                    break;
                case 2:
                    m_gif_duration = SPLASH_DELAY_MILLIS;
                    splash_jump_tv.setText("跳过\t" + m_gif_duration / 1000 +"s");

                    updateCountDown();
                    loadNetPicture();
                    break;
                case 3:
                    loadGif();
                    break;
            }
        } else  {
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, 0);
        }

    }

    private void goHome() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void goGuide() {
        Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
        SplashActivity.this.startActivity(intent);
        this.finish();
    }

    @OnClick(R.id.splash_jump_tv)
    public void jumpToLogin() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        this.finish();
    }

}
