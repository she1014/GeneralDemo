package com.techfly.demo.activity.qq_demo.view;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by Root on 2016/6/20.
 */
public class SlideLayout extends FrameLayout{


    private View mSlideView;
    private View mMainView;
    private ViewDragHelper mViewDragHelper;
    private int mWd_width;
    private int mWd_height;
    private int mSl_width;
    private boolean mMenuIsOpen;

    private boolean celaIsOpen = false;

    public SlideLayout(Context context) {
        this(context,null);
    }

    public SlideLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SlideLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mViewDragHelper = ViewDragHelper.create(this,1.0f,new MyDragCallBack());

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        mSlideView = getChildAt(0);
        mMainView = getChildAt(1);

        mWd_width = MeasureSpec.getSize(widthMeasureSpec);     //使用测量说明书得到我们想要知道的宽度和高度值
        mWd_height = MeasureSpec.getSize(heightMeasureSpec);

        ///侧滑界面宽度占2/3
        int sl_widthSpec = MeasureSpec.makeMeasureSpec((mWd_width /4) * 3,MeasureSpec.EXACTLY); //制作测量说明书规定值
        int sl_heightSpec = MeasureSpec.makeMeasureSpec(mWd_height,MeasureSpec.EXACTLY);
        mSlideView.measure(sl_widthSpec,sl_heightSpec);

        mMainView.measure(widthMeasureSpec,heightMeasureSpec);   //主面板和自己一样,都是填充整个窗体,所以测量说明书的规定值一样

        setMeasuredDimension(mWd_width, mWd_height);            //测量自己用这个方法.

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mSl_width = mSlideView.getWidth();
        mSlideView.layout(mSlideView.getLeft() -  mSl_width /3 ,mSlideView.getTop(),mSlideView.getRight(),mSlideView.getBottom());
    }


    public void setCelaIsopen(boolean isopen){
        celaIsOpen = isopen;
    }


    private float mStartX = 0;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        if (celaIsOpen){                      //如果侧拉打开了,不拦截向右滑动事件

            switch (event.getAction()){

                case MotionEvent.ACTION_DOWN:
                    mStartX = event.getRawX();
                    break;

                case MotionEvent.ACTION_MOVE:
                    float movX = event.getRawX();
                    if (movX > mStartX){
                        return  false;
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    mStartX = 0;
                    break;
            }
        }


        return mViewDragHelper.shouldInterceptTouchEvent(event);   //交给它去判断该不该拦截
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        try {

            mViewDragHelper.processTouchEvent(event);    //有可能会出错,所以try一下
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }


    class MyDragCallBack extends ViewDragHelper.Callback {

        @Override  //表示要捕获那个孩子,即处理哪个孩子的拖到事件,返回false不处理
        public boolean tryCaptureView(View child, int pointerId) {
            return true;
        }

        @Override //
        public int clampViewPositionHorizontal(View child, int left, int dx) {  //left相对于屏幕左侧的偏移值,是拖动的建议值

            if (child == mMainView){            //主面板拖动范围
                if (left < 0){
                    left = 0;
                }else if (left > (mSl_width)){
                    left = mSl_width;
                }

            }else if (child == mSlideView){      //左面板拖动范围
                if (left > 0){
                    left = 0;
                }else if (left < -mSl_width){
                    left = mSl_width;
                }

            }
            return left;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            // 返回拖拽的范围, 不对拖拽进行真正的限制. 仅仅决定了动画执行速度
            return mSl_width/3 * 2;
        }

        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);

            if (changedView == mMainView){

                mSlideView.layout( left/3 - mSl_width/3,mSlideView.getTop(),left/3 - mSl_width/3+mSl_width,mSlideView.getBottom());

            }else if (changedView == mSlideView){

                mMainView.layout(mSl_width + left,mMainView.getTop(),mSl_width + left + mWd_width,mMainView.getBottom());
            }

            if (mOnSlideListener != null){
                mOnSlideListener.onSlide(changedView,left,left * 1.0f / mSl_width);
            }


            invalidate();
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {

            if (mMainView.getLeft() < mSl_width/2){

                closeMenu();
            }else {

                openMenu();

            }
        }

    }

    @Override
    public void computeScroll() {  //不停计算,不停调用
        if (mViewDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);  //不停刷新直到停止滑动
        }
    }


    public void closeMenu() {

        mViewDragHelper.smoothSlideViewTo(mMainView,0,mMainView.getTop());
        ViewCompat.postInvalidateOnAnimation(this);  //兼容,刷新界面.
        mMenuIsOpen = false;

    }


    public void openMenu() {

        mViewDragHelper.smoothSlideViewTo(mMainView,mSl_width,mMainView.getTop());
        ViewCompat.postInvalidateOnAnimation(this);
        mSlideView.layout(0,mSlideView.getTop(),mSl_width,mSlideView.getBottom());
        mMenuIsOpen = true;
    }


    public boolean isMenuIsOpen(){
        return mMenuIsOpen;
    }


    public interface OnSlideListener{
        /**
         * @param view    //触摸的是主界面还是菜单界面
         * @param left  //滑动了多长,负数向左滑,正数向右
         * @param persent //滑动相对于总长度的百分比
         */
        void onSlide(View view, int left, float persent);
    }

    private OnSlideListener mOnSlideListener;
    public void setOnSlideListener(OnSlideListener listener){
        mOnSlideListener = listener;
    }


}
