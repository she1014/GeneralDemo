package com.techfly.demo.selfview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.techfly.demo.R;
import com.techfly.demo.selfview.randomcolor.RandomColor;
import com.techfly.demo.util.LogsUtil;

/**
 * Created by ssm on 2017/2/16.
 */
public class DiyView extends View {

    private String mTextContent;
    private int mTextColor;
    private int mTextSize;

    private Rect mBound;
    private Paint mPaint;

    public DiyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LogsUtil.normal("DiyView");

        obtainStyledAttrs(attrs);
    }

    //获取资源文件
    private void obtainStyledAttrs(AttributeSet attrs) {
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.DiyView);
        mTextSize = (int) array.getDimension(R.styleable.DiyView_m_text_size, 20);
        mTextColor = array.getColor(R.styleable.DiyView_m_text_color, Color.RED);
        mTextContent = array.getString(R.styleable.DiyView_m_text_content);
        array.recycle();

        mPaint = new Paint();
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);

        mBound = new Rect();

        LogsUtil.normal("length=" + mTextContent.length());

        mPaint.getTextBounds(mTextContent, 0, 1, mBound);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LogsUtil.normal("onDraw");

        Matrix matrix = new Matrix();
        matrix.setTranslate(20, 200);

        Shader shader = new Shader();
        shader.setLocalMatrix(matrix);

        Paint p_point = new Paint();
        p_point.setColor(getResources().getColor(R.color.red));
        p_point.setAntiAlias(true);
        p_point.setStrokeWidth(200);
        p_point.setShader(shader);
        canvas.drawPoint(getWidth() / 2, getHeight() / 2, p_point);


        Paint p_line = new Paint();
        p_line.setColor(getResources().getColor(R.color.white));
        p_line.setStrokeWidth(40);
        canvas.drawLine(0, 0, getWidth(), getHeight(), p_line);
        canvas.drawLine(0, getHeight(), getWidth(), 0, p_line);

        Paint p_circle = new Paint();
        p_circle.setColor(getResources().getColor(R.color.pink));
        p_circle.setStrokeWidth(100);
        canvas.drawCircle(getWidth() / 4 * 2, getHeight() / 4 * 1, 20, p_circle);
        canvas.drawCircle(getWidth() / 4 * 3, getHeight() / 4 * 2, 20, p_circle);
        canvas.drawCircle(getWidth() / 4 * 2, getHeight() / 4 * 3, 20, p_circle);
        canvas.drawCircle(getWidth() / 4 * 1, getHeight() / 4 * 2, 20, p_circle);

        Paint p_rec = new Paint();
        p_rec.setColor(getResources().getColor(R.color.red));
        canvas.drawRoundRect(getWidth() / 16 * 1, getHeight() / 3 * 1, getWidth() / 16 * 3, getHeight() / 3 * 2, 20, 20, p_rec);
        canvas.drawRect(new Rect(getWidth() / 4 * 1, getHeight() / 16 * 13, getWidth() / 4 * 3, getHeight() / 16 * 15), p_rec);

        Paint p_text = new Paint();
        p_text.setTextSize(46);
        p_text.setColor(getResources().getColor(R.color.white));
        canvas.drawText("绘制出来的文字字数过长待处理", getWidth() / 5 * 2, getHeight() / 8 * 1, p_text);

        for (int i = 1; i < 9; i++) {

            RandomColor randomColor = new RandomColor();

            Paint p_combination = new Paint();
            p_combination.setAntiAlias(true);
            p_combination.setTextSize(40);
            p_combination.setColor(randomColor.randomColor());

            int start_width = getWidth() / 6 * 5;
            int start_height = getHeight() / 5 + (i * 58);

//            LogsUtil.normal("start_width=" + start_width + ",start_height=" + start_height);

            canvas.drawCircle(start_width, start_height, 20, p_combination);
            canvas.drawText("描述" + i, start_width + 30, start_height + 15, p_combination);
        }



    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LogsUtil.normal("onFinishInflate");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogsUtil.normal("onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogsUtil.normal("onLayout");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        LogsUtil.normal("onSizeChanged");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LogsUtil.normal("onKeyDown");
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        LogsUtil.normal("onKeyUp");
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        LogsUtil.normal("onTrackballEvent");
        return super.onTrackballEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogsUtil.normal("onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        LogsUtil.normal("onFocusChanged");
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        LogsUtil.normal("onWindowFocusChanged");
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        LogsUtil.normal("onAttachedToWindow");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LogsUtil.normal("onDetachedFromWindow");
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
        LogsUtil.normal("onWindowVisibilityChanged");
    }
}

