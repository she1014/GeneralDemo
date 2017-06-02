package com.techfly.demo.activity.qq_demo.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;

/**
 * Created by Root on 2016/6/24.
 */
public class DragBall extends View {

    private Paint mPaint;
    private PointF mCicleCenterPoint;
    private PointF mDragCicleCenter;
    private float mRadius;
    private float mDragRadius;
    private Paint mTextPaint;
    private int mStatusBarHeight;
    private PointF mPointE;
    private PointF mPointA;
    private PointF mPointB;
    private PointF mPointC;
    private PointF mPointD;
    private int mRang;
    private boolean isOutOfRang = false;
    private float saveRdius = 0;
    private boolean isDisappear = false;
    private int mNumber = 0;

    public DragBall(Context context) {
        this(context, null);
    }

    public DragBall(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragBall(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);  //抗锯齿
        mPaint.setStyle(Paint.Style.FILL);  //填充
        mPaint.setDither(true);             //防抖动


        mRadius = dp2px(context,9);
        mDragRadius = mRadius;

        saveRdius = mRadius;

        mTextPaint = new Paint();
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(mRadius * 1.2f);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);


        mStatusBarHeight = getStatusBarHeight();



    }


    public void setCenterPoint(float x, float y) {


        mCicleCenterPoint = new PointF(x, y);

        mDragCicleCenter = new PointF(x, y);

        mPointA = new PointF(x + mRadius, y);
        mPointB = new PointF(x - mRadius, y);

        mPointC = new PointF(x + mRadius, y);
        mPointD = new PointF(x - mRadius, y);

        mPointE = new PointF(x, y);
    }


    public void setDragRang(int rang) {
        mRang = rang;
    }

    public void setNumber(int number) {
        mNumber = number;

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.save();

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR2) {  //4.4以上设置状态栏透明就不用减状态栏高度了

            canvas.translate(0, -mStatusBarHeight);
        }


//        mPaint.setStyle(Paint.Style.STROKE);
//        canvas.drawCircle(mCicleCenterPoint.x, mCicleCenterPoint.y, mRang, mPaint);
//        mPaint.setStyle(Paint.Style.FILL);

        if (!isOutOfRang) {

            Path path = new Path();
            path.moveTo(mPointA.x, mPointA.y);

            path.quadTo(mPointE.x, mPointE.y, mPointC.x, mPointC.y);

            path.lineTo(mPointD.x, mPointD.y);

            path.quadTo(mPointE.x, mPointE.y, mPointB.x, mPointB.y);

            path.close();

            canvas.drawPath(path, mPaint);

            canvas.drawCircle(mCicleCenterPoint.x, mCicleCenterPoint.y, mRadius, mPaint); //画固定圆
        }

        if (!isDisappear) {


            canvas.drawCircle(mDragCicleCenter.x, mDragCicleCenter.y, mDragRadius, mPaint);//画拖动圆



            canvas.drawText("" + mNumber, mDragCicleCenter.x, mDragCicleCenter.y + mDragRadius / 2, mTextPaint); //画文字
        }

        canvas.restore();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float delX;
        float dely;
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                break;

            case MotionEvent.ACTION_MOVE:

                mDragCicleCenter.x = event.getRawX();         //手机坐标
                mDragCicleCenter.y = event.getRawY();
                ComputerFivePoint();

                delX = Math.abs(mDragCicleCenter.x - mCicleCenterPoint.x);
                dely = Math.abs(mDragCicleCenter.y - mCicleCenterPoint.y);

                if (Math.sqrt(delX * delX + dely * dely) <= mRang) {
                    mRadius = (float) (saveRdius - Math.sqrt(delX * delX + dely * dely) / mRang * (mRadius / 7 * 6));           //固定圆半径缩小
                } else {
                    isOutOfRang = true;
                }

                break;

            case MotionEvent.ACTION_UP:

                if (isOutOfRang) {

                    delX = Math.abs(mDragCicleCenter.x - mCicleCenterPoint.x);
                    dely = Math.abs(mDragCicleCenter.y - mCicleCenterPoint.y);
                    if (Math.sqrt(delX * delX + dely * dely) < mRang) {
                        isOutOfRang = false;
                        mDragCicleCenter.x = mCicleCenterPoint.x;
                        mDragCicleCenter.y = mCicleCenterPoint.y;
                        ComputerFivePoint();
                        if (monDragBallListener != null){
                            monDragBallListener.onReset();
                        }
                    } else {
                        isDisappear = true;
                        invalidate();

                        if (monDragBallListener != null) {
                            monDragBallListener.onDisappear(this, mDragCicleCenter.x, mDragCicleCenter.y); //消失的时候
                        }
                    }

                } else {

                    ValueAnimator animator = ValueAnimator.ofFloat(1.0f);
                    animator.setInterpolator(new OvershootInterpolator(5.0f));

                    final PointF startPoint = new PointF(mDragCicleCenter.x,mDragCicleCenter.y);
                    animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {

                            float fraction = animation.getAnimatedFraction();  //百分比

                            mDragCicleCenter.x = startPoint.x + (mCicleCenterPoint.x - startPoint.x) * fraction;
                            mDragCicleCenter.y = startPoint.y + (mCicleCenterPoint.y - startPoint.y) * fraction;
                            Log.d("DragBall", "fraction:" + fraction);

                            ComputerFivePoint();
                        }
                    });

                    animator.setDuration(200);
                    animator.start();

                    animator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            if (monDragBallListener != null){
                                monDragBallListener.onReset();
                            }
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    });
                }

                mRadius = saveRdius;          //固定圆半径还原


                break;
        }

        return true;
    }

    /**
     * 计算图中的ABCDE点
     */
    private void ComputerFivePoint() {

        mPointE.x = mDragCicleCenter.x / 2 - mCicleCenterPoint.x / 2 + mCicleCenterPoint.x;
        mPointE.y = mDragCicleCenter.y / 2 - mCicleCenterPoint.y / 2 + mCicleCenterPoint.y;

        double tan = (mDragCicleCenter.x - mCicleCenterPoint.x) / (mDragCicleCenter.y - mCicleCenterPoint.y);
        double atan = Math.atan(tan);  //得到角度
        mPointA.x = (float) (mCicleCenterPoint.x + Math.cos(atan) * mRadius);
        mPointA.y = (float) (mCicleCenterPoint.y - Math.sin(atan) * mRadius);
        mPointB.x = (float) (mCicleCenterPoint.x - Math.cos(atan) * mRadius);
        mPointB.y = (float) (mCicleCenterPoint.y + Math.sin(atan) * mRadius);


        mPointC.x = (float) (mDragCicleCenter.x + Math.cos(atan) * mDragRadius);
        mPointC.y = (float) (mDragCicleCenter.y - Math.sin(atan) * mDragRadius);
        mPointD.x = (float) (mDragCicleCenter.x - Math.cos(atan) * mDragRadius);
        mPointD.y = (float) (mDragCicleCenter.y + Math.sin(atan) * mDragRadius);

        invalidate();
    }


    public int getStatusBarHeight() {//获取状态栏高度
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    public interface onDragBallListener {
        void onDisappear(DragBall dragBall, float x, float y);
        void onReset();
    }

    private onDragBallListener monDragBallListener;

    public void setonDragBallListener(onDragBallListener onDragBallListener) {
        monDragBallListener = onDragBallListener;
    }

    public int dp2px(Context context, int dp){

        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
    }
}
