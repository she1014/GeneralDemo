package com.techfly.demo.activity.qq_demo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.techfly.demo.R;


/**
 * Created by Root on 2016/6/22.
 */
public class DragLayout extends FrameLayout {

    private View mMainView;
    private View mRightView;
    private int mMainWidth;
    private int mRightWidth;
    private ViewDragHelper mViewDragHelper;
    private int mRange;
    private int mMainHeight;
    private boolean isOpen = false;
    private int mRightChildeCount;
    private boolean isStartOpen = false;

    DragLayout mDragLayout;

    public DragLayout(Context context) {
        this(context, null);
    }


    public DragLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DragLayout, defStyleAttr, 0);
        mRightChildeCount = typedArray.getInt(R.styleable.DragLayout_rightChildCount, -1);
        if (mRightChildeCount < 2) {
            throw new IllegalArgumentException("DragLayout右面板孩子数最少为两个");
        }
        mViewDragHelper = ViewDragHelper.create(this, new MyCallBack());
        mDragLayout = this;
    }

    @Override
    protected void onFinishInflate() {  //视图解析完成后调用,这时候就能拿到我们的孩子对象了
        super.onFinishInflate();

        mRightView = getChildAt(0);       //右边选项界面
        mMainView = getChildAt(1);         //主面板
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  //测量完成后调用,这时我们可以拿到view的大小
        super.onSizeChanged(w, h, oldw, oldh);

        mMainWidth = mMainView.getMeasuredWidth();
        mMainHeight = mMainView.getMeasuredHeight();

        mRightWidth = mRightView.getMeasuredWidth();


        mRange = mRightWidth;                 //拖动范围
    }



    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        mRightView.layout(mMainWidth,0,mMainWidth + mRightWidth,mMainHeight);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {


        return mViewDragHelper.shouldInterceptTouchEvent(ev);  //交给我们的mViewDragHelper去判断该不该拦截
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        try {

            mViewDragHelper.processTouchEvent(event);               //交给我们的mViewDragHelper去操作触摸事件,事件被拦截有可能会出错,try一下
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    class MyCallBack extends ViewDragHelper.Callback {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {  //要求我们重写,返回true执行拖到事件,false不执行
            return true;
        }

        @Override
        public int getViewHorizontalDragRange(View child) {  //水平移动范围,我们最好重写这个方法!不然碰到listView拖不动

            return mRange;
        }

        /**
         * @param child 拖动那个孩子
         * @param left  拖动建议值,view根据返回的建议值拖动,相对于左位负,相对于右为正,是view相对物理窗口左边的位置
         * @param dx    变化值
         * @return 默认返回0, 即不拖动
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {

            if (child == mMainView) {

                if (left > 0) {
                    left = 0;
                } else if (left < -mRange) {
                    left = -mRange;
                }
            } else if (child == mRightView) {
                if (left < mMainWidth - mRightWidth) {
                    left = mMainWidth - mRightWidth;
                } else if (left > mMainWidth) {
                    left = mRange;
                }
            }

            return left;
        }


        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {


            if (changedView == mMainView) {

                mRightView.layout(mMainWidth + left, mRightView.getTop(), mMainWidth + left + mRightWidth, mRightView
                        .getBottom());

            } else if (changedView == mRightView) {
                mMainView.layout(mRightView.getLeft() - mMainWidth, 0, mRightView.getLeft(),
                        mMainHeight);
            }

            if (mOnDraeListener != null) {

                int mManLeft = mMainView.getLeft();

                if (!isOpen && mManLeft == - mRightWidth){     //打开状态,原来是打开的就不用重新回调了
                    mOnDraeListener.onOpen(mDragLayout);
                    isOpen = true;

                }else if (isOpen && mManLeft == 0){
                    mOnDraeListener.onclose(mDragLayout);
                    isOpen = false;
                    isStartOpen = false;
                }else{

                    mOnDraeListener.onDrag(changedView, left);  //接口回调

                    if (!isOpen && !isStartOpen){                //只有在关闭的情况下才是要开始打开了

                        mOnDraeListener.onStartOpen(mDragLayout);
                        isStartOpen = true;
                    }

                }




            }

            invalidate();
        }

        // xvel           //水平速度
        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {


            if (xvel < 0) {
                //假设孩子数为3
                open();
            } else if (xvel == 0) {                                           //不确定是关闭还是打开状态

                if (isOpen) {
                    if (mMainView.getLeft() > -mRange / mRightChildeCount * (mRightChildeCount - 1)) {
                        close();                                         //打开状态,即向右滑超过三分之一关闭
                    } else {
                        open();
                    }
                } else {                                                 //关闭状态跟上面一样向左滑

                    if (mMainView.getLeft() > -mRange / mRightChildeCount) {

                        close();
                    } else {
                        open();
                    }
                }

            } else if (xvel > 0) {                                        //打开状态即向右滑
                close();
            }
        }


    }

    public void open() {
        mViewDragHelper.smoothSlideViewTo(mMainView, -mRange, 0);
        ViewCompat.postInvalidateOnAnimation(this);

    }

    public void close() {
        mViewDragHelper.smoothSlideViewTo(mMainView, 0, 0);
        ViewCompat.postInvalidateOnAnimation(this);

    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }


    public boolean isOpen() {
        return isOpen;
    }


    public interface onDraeListener {

        void onDrag(View view, int left);

        void onOpen(DragLayout dragLayout);  //打开了

        void onclose(DragLayout dragLayout);  //关闭了

        void onStartOpen(DragLayout dragLayout); //开始打开,不一定打开
    }

    private onDraeListener mOnDraeListener;   //引用

    public void setOnDraeListener(onDraeListener onDraeListener) {  //由外部传个名字
        mOnDraeListener = onDraeListener;
    }
}
