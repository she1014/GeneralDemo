package com.techfly.demo.activity.qq_demo.view;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.techfly.demo.R;
import com.techfly.demo.activity.demo.Demo3Activity;


/**
 * Created by Root on 2016/6/25.
 */
public class DragBallListener implements View.OnTouchListener, DragBall.onDragBallListener {

    private final DragBall mDragBall;
    private final WindowManager mWindowManager;
    private WindowManager.LayoutParams mParams;
    private Context mContext;
    private View mPoint;


    public DragBallListener(Context context,TextView msgPoint){

        mContext = context;
        mPoint = msgPoint;

        mDragBall = new DragBall(mContext);
        mDragBall.setonDragBallListener(this);
        mDragBall.setDragRang(dp2px(context,60));  //60dp
        mDragBall.setNumber((Integer) msgPoint.getTag());

        mWindowManager = (WindowManager) mContext.getSystemService(mContext.WINDOW_SERVICE);

        mParams = new WindowManager.LayoutParams();
        mParams.format = PixelFormat.TRANSLUCENT;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {

            mParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;  //设置状态栏透明

        }

    }




    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            mPoint.getParent().requestDisallowInterceptTouchEvent(true);

            mPoint.setVisibility(View.INVISIBLE);

            mDragBall.setCenterPoint(event.getRawX(), event.getRawY());

            mWindowManager.addView(mDragBall, mParams);


        }

        mDragBall.onTouchEvent(event);
        return true;

    }



    public int getStatusBarHeight() {//获取状态栏高度
        int result = 0;
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    @Override
    public void onDisappear(DragBall dragBall, float x, float y) {


        if (mWindowManager != null && mDragBall.getParent() != null){
            mWindowManager.removeView(mDragBall);
        }

        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(R.drawable.frame_bao);
        AnimationDrawable mAnimDrawable = (AnimationDrawable) imageView
                .getDrawable();

        final BaozhaLayout bubbleLayout = new BaozhaLayout(mContext);


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {

            bubbleLayout.setCenter((int) x, (int) (y));    //4.4以上把状态栏设置为透明后,顶上去了,不用减状态栏高度了
        }else {

            bubbleLayout.setCenter((int) x, (int) (y - getStatusBarHeight()));
        }


        bubbleLayout.addView(imageView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT));

        mWindowManager.addView(bubbleLayout, mParams);

        mAnimDrawable.start();

        // 播放结束后，移除帧动画
        Demo3Activity.mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                mWindowManager.removeView(bubbleLayout);
            }
        }, 600);
    }

    @Override
    public void onReset() {

        if (mWindowManager != null && mDragBall.getParent() != null){
            mWindowManager.removeView(mDragBall);
            mPoint.setVisibility(View.VISIBLE);
        }
    }


    public int dp2px(Context context,int dp){

        return (int) (context.getResources().getDisplayMetrics().density * dp + .5f);
    }

}
