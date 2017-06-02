package com.techfly.demo.activity.qq_demo.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.techfly.demo.R;

/**
 * Created by Root on 2016/7/1.
 */
public class RefreshListview extends ListView {


    private int mHeadViewHeight;
    private View mHeadView;

    private static final int PULL_TO_REFRESH = 1;            //下拉刷新
    private static final int RELEASE_TO_REFRESH = 2;         //松开刷新
    private static final int NOW_TO_REFRESH = 3;            //刷新中

    private int curStatus = PULL_TO_REFRESH;        //当前状态为下拉刷新

    private ImageView mIv_arrow;
    private ProgressBar mProgressBar;
    private TextView mTextView;

    private  RotateAnimation pull_to_rel_animation;
    private  RotateAnimation rel_to_pull_animation;
    private int mTopPadding;

    public RefreshListview(Context context) {
        super(context);
        init(context);
    }

    public RefreshListview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RefreshListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);


    }

    public void init(Context context) {
        mHeadView = View.inflate(context, R.layout.item_head_rlv, null);

        mIv_arrow = (ImageView) mHeadView.findViewById(R.id.iv_arrow);
        mProgressBar = (ProgressBar) mHeadView.findViewById(R.id.progress);
        mTextView = (TextView) mHeadView.findViewById(R.id.head_text);

        addHeaderView(mHeadView);

        mHeadView.measure(0,0);  //叫系统去给我测量
        mHeadViewHeight = mHeadView.getMeasuredHeight();
        mHeadView.setPadding(0,-mHeadViewHeight,0,0);           //设置负的Padding,顶上去

        pull_to_rel_animation = new RotateAnimation(0,180, Animation.RELATIVE_TO_SELF,.5f,Animation.RELATIVE_TO_SELF,
                .5f);
        pull_to_rel_animation.setDuration(300);
        pull_to_rel_animation.setFillAfter(true); //保存动画最后状态

        rel_to_pull_animation = new RotateAnimation(180,360, Animation.RELATIVE_TO_SELF,.5f,Animation.RELATIVE_TO_SELF,.5f);
        rel_to_pull_animation.setDuration(300);
        rel_to_pull_animation.setFillAfter(true); //保存动画最后状态
    }


    private int mDownY = 0;
    private boolean fistTouch = true;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){

            case MotionEvent.ACTION_DOWN:

                mDownY = (int) ev.getY();
                Log.d("RefreshListview", "mDownY:" + mDownY);
                break;

            case MotionEvent.ACTION_MOVE:

                if (fistTouch){
                    mDownY = (int) ev.getY();
                    fistTouch = false;
                }
                int deleY = (int) (ev.getY() - mDownY);  //滑动距离

                Log.d("RefreshListview", "ev.getY():" + ev.getY());
                Log.d("RefreshListview", "deleY:" + deleY);
                mTopPadding = deleY - mHeadViewHeight;

                if (mTopPadding > - mHeadViewHeight && getFirstVisiblePosition() == 0){  //topPadding不能小于头视图的高度,防止滑不下来

                    mHeadView.setPadding(0, mTopPadding,0,0);

                    if (mTopPadding >= 0 && curStatus == PULL_TO_REFRESH){ //滑动状态下只允许由下拉进入松开
                        curStatus = RELEASE_TO_REFRESH;
                        RefreshStatusChanged();

                    }else if (mTopPadding < 0 && curStatus == RELEASE_TO_REFRESH){//同上理
                        curStatus = PULL_TO_REFRESH;
                        RefreshStatusChanged();
                    }

                    Log.d("RefreshListview", "mTopPadding:" + mTopPadding);

                    return true;
                }

                break;

            case MotionEvent.ACTION_UP:


                if (mTopPadding >= 0 && curStatus == RELEASE_TO_REFRESH){ //只能由释放刷新状态进入刷新状态

                    mHeadView.setPadding(0,0,0,0);
                    curStatus = NOW_TO_REFRESH;
                    RefreshStatusChanged();

                    if (mOnRefreshListener != null){
                        mOnRefreshListener.onRefresh();
                    }

                }else {

                    mHeadView.setPadding(0,-mHeadViewHeight,0,0);

                }

                fistTouch = true;

                break;
        }


        return super.onTouchEvent(ev);
    }


    private void RefreshStatusChanged(){

        switch (curStatus){

            case PULL_TO_REFRESH:
                mTextView.setText("下拉刷新");
                mIv_arrow.startAnimation(rel_to_pull_animation);

                break;

            case RELEASE_TO_REFRESH:

                mTextView.setText("释放立即刷新");
                mIv_arrow.startAnimation(pull_to_rel_animation);

                break;

            case NOW_TO_REFRESH:

                mIv_arrow.clearAnimation();        //清除动画,不然动画没完成不能隐藏
                mIv_arrow.setVisibility(INVISIBLE);
                mProgressBar.setVisibility(VISIBLE);
                mTextView.setText("正在刷新中....");

                break;

        }
    }


    /**刷新成功
     * @param handler
     */
    public void RefreshSuccess(Handler handler){
        mTextView.setText("刷新成功");
        mProgressBar.setVisibility(INVISIBLE);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHeadView.setPadding(0,-mHeadViewHeight,0,0);
                mIv_arrow.setVisibility(VISIBLE);
                mTextView.setText("下拉刷新");
                curStatus = PULL_TO_REFRESH;
            }
        },1000);
    }

    /**
     * 刷新失败
     */
    public void RefreshError(){
        mProgressBar.setVisibility(INVISIBLE);
        mHeadView.setPadding(0,-mHeadViewHeight,0,0);
        mIv_arrow.setVisibility(VISIBLE);
        mTextView.setText("下拉刷新");
        curStatus = PULL_TO_REFRESH;
    }


    public interface OnRefreshListener{

        void onRefresh();
    }

    private OnRefreshListener mOnRefreshListener;
    public void setOnRefreshListener(OnRefreshListener onRefreshListener){
        mOnRefreshListener = onRefreshListener;
    }


}



