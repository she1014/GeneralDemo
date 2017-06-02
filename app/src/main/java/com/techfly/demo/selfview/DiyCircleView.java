package com.techfly.demo.selfview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.techfly.demo.R;
import com.techfly.demo.selfview.randomcolor.RandomColor;
import com.techfly.demo.util.LogsUtil;

public class DiyCircleView extends View {

    //-------------必须给的数据相关-------------
    private String[] str = new String[]{"一年级", "二年级", "三年级", "四年级", "五年级", "六年级"};
    //分配比例大小，总比例大小为100,由于经过运算后最后会是99.55左右的数值，导致圆不能够重合，会留出点空白，所以这里的总比例大小我们用101
    private int[] strPercent = new int[]{10, 24, 18, 41, 2, 5};
    //圆的直径
    private float mRadius = 200;
    //圆的粗细
    private float mStrokeWidth = 50;
    //文字大小
    private int textSize = 20;
    //-------------画笔相关-------------
    //圆环的画笔
    private Paint cyclePaint;
    //文字的画笔
    private Paint textPaint;
    //标注的画笔
    private Paint labelPaint;
    //-------------颜色相关-------------
    //边框颜色和标注颜色
    private int[] mColor = new int[]{0xFFF06292, 0xFF9575CD, 0xFFE57373, 0xFF4FC3F7, 0xFFFFF176, 0xFF81C784};
    //文字颜色
    private int textColor = 0xFF000000;
    //-------------View相关-------------
    //View自身的宽和高
    private int mHeight;
    private int mWidth;

    private int m_angle1 = 0;
    private int m_angle2 = 270;
    private double m_circle_x = 0;
    private double m_circle_y = 0;
    private int m_circle_r = 20;
//    private Handler mHandler = null;

//    PopupWindow

    public DiyCircleView(Context context) {
        super(context);
    }

    public DiyCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DiyCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        LogsUtil.normal("onDraw");

//        drawTest5(canvas);

        //移动画布到圆环的左上角
       /* canvas.translate(mWidth / 2 - mRadius / 2, mHeight / 2 - mRadius / 2);
//        canvas.translate(300,300);
        //初始化画笔
        initPaint();
        //画圆环
        drawCycle(canvas);
        //画文字和标注
        drawTextAndLabel(canvas);*/


//        drawTest1(canvas);

//        drawTest2(canvas);
//
//        canvas.translate(getWidth() / 3, 0);
//
//        drawTest2(canvas);
//
//        drawPath(canvas);
//
//        canvas.translate(getWidth() / 3, 0);
//
//        drawTest2(canvas);
//
//        canvas.translate(-getWidth() / 3 * 2, 0);
//
//        drawTest3(canvas);
//
//        canvas.translate(getWidth() / 3, 0);

//        drawTest4(canvas);

//        drawTest5(canvas);

        drawTest6(canvas);

        refresh();

//        startAnim();
    }

    /*
     * 计算：已知圆心与半径，求圆上分布的各点坐标
     */
    private void test() {
        //圆心:O(1,1)
        //半径:r=1
        //求B点坐标
//        double r = 1;
//        double a = 1 + r * Math.cos(0 * Math.PI / 180);
//        double b = 1 + r * Math.sin(0 * Math.PI / 180);
//        LogsUtil.normal("a=" + a + ",b=" + b);
//        起点：逆时针转；0(2,1);30(1.866,1.5);90(1,2);180(0,1);270(1,0);360(2,1);
        double r = 1;
        double a = 1 + r * Math.cos(-90 * Math.PI / 180);
        double b = 1 + r * Math.sin(-90 * Math.PI / 180);
        LogsUtil.normal("a=" + a + ",b=" + b);
//        起点：顺时针转；0(2,1);30(1.866,1.5);90(1,2);180(0,1);270(1,0);360(2,1);
    }


    private void drawTest1(final Canvas canvas) {

        final int arc_radius = 200;
        int point_radius = 10;
        int arc_x = getWidth() / 2;
        int arc_y = getHeight() / 2;

        int arc_left = getWidth() / 2 - arc_radius;
        int arc_top = getHeight() / 2 - arc_radius;
        int arc_right = getWidth() / 2 + arc_radius;
        int arc_bottom = getHeight() / 2 + arc_radius;

        Paint p_arc = new Paint();
        p_arc.setAntiAlias(true);
        p_arc.setColor(getResources().getColor(R.color.red));
        canvas.drawArc(arc_left, arc_top, arc_right, arc_bottom, 0, m_angle1, true, p_arc);

        Paint p_circle = new Paint();
        p_circle.setColor(getResources().getColor(R.color.white));
        double p_x = arc_x + arc_radius / 2 * Math.cos(((360 - m_angle1) / 2 + m_angle2) * Math.PI / 180);
        double p_y = arc_y + arc_radius / 2 * Math.sin(((360 - m_angle1) / 2 + m_angle2) * Math.PI / 180);
        canvas.drawCircle(Float.parseFloat(p_x + ""), Float.parseFloat(p_y + ""), point_radius, p_circle);

        LogsUtil.normal("p_x=" + p_x + ",p_y=" + p_y);


    }

    private void drawTest2(final Canvas canvas) {

        RandomColor randomColor = new RandomColor();

        final int arc_radius = getWidth() / 6;
        int point_radius = 20;
        int arc_x = arc_radius;
        int arc_y = arc_radius;

        int arc_left = 0;
        int arc_top = 0;
        int arc_right = arc_radius * 2;
        int arc_bottom = arc_radius * 2;

        Paint p_arc = new Paint();
        p_arc.setAntiAlias(true);
        p_arc.setColor(getResources().getColor(R.color.red));
        canvas.drawArc(arc_left, arc_top, arc_right, arc_bottom, 0, m_angle2, true, p_arc);

        Paint p_circle = new Paint();
        p_circle.setColor(getResources().getColor(R.color.white));
        double alpha = (360 - m_angle2) * 1.0 / 90 * 255;
        point_radius = (int) ((360 - m_angle2) * 1.0 / 90 * 20);
        p_circle.setAlpha((int) alpha);
//        LogsUtil.normal("alpha=" + alpha);
        double p_x = arc_x + arc_radius / 2 * Math.cos(((360 - m_angle2) / 2 + m_angle2) * Math.PI / 180);
        double p_y = arc_y + arc_radius / 2 * Math.sin(((360 - m_angle2) / 2 + m_angle2) * Math.PI / 180);
        canvas.drawCircle(Float.parseFloat(p_x + ""), Float.parseFloat(p_y + ""), point_radius, p_circle);

        LogsUtil.normal("p_x=" + p_x + ",p_y=" + p_y);
    }

    private void drawTest3(final Canvas canvas) {
//        getHeight=845,getWidth=1050
        LogsUtil.normal("getHeight=" + getHeight() + ",getWidth=" + getWidth());

        int arc_left = 0;
        int arc_top = getWidth() / 3;
        int arc_right = getWidth() / 3;
        int arc_bottom = getWidth() / 3 * 2;

        Paint p_arc = new Paint();
        p_arc.setAntiAlias(true);
        p_arc.setColor(getResources().getColor(R.color.red));
        canvas.drawArc(arc_left, arc_top, arc_right, arc_bottom, 0, 300, true, p_arc);

        canvas.rotate(m_angle2);

    }

    /*
     * 五角星 + 圆环
     */
    private void drawTest4(final Canvas canvas) {

        Path path = new Path();
//        path.reset();
//        path.moveTo(0, 0);
        path.moveTo(186, 360);
        path.lineTo(214, 360);
        path.lineTo(230, 392);
        path.lineTo(200, 420);
        path.lineTo(170, 392);
//        path.moveTo(300, 300);
//        path.close();
//
        Paint p_arc = new Paint();
        p_arc.setAntiAlias(true);
        p_arc.setColor(getResources().getColor(R.color.red));
        canvas.drawPath(path, p_arc);

        /*Paint paint=new Paint();  //定义一个Paint
        paint.setStrokeWidth(20);
        //新建一个线性渐变，前两个参数是渐变开始的点坐标，第三四个参数是渐变结束的点的坐标。连接这2个点就拉出一条渐变线了，玩过PS的都懂。然后那个数组是渐变的颜色。下一个参数是渐变颜色的分布，如果为空，每个颜色就是均匀分布的。最后是模式，这里设置的是循环渐变
//        Shader mShader = new LinearGradient(0,0,40,60,new int[] {Color.RED,Color.GREEN,Color.BLUE},null,Shader.TileMode.REPEAT);
        Shader mShader = new RadialGradient(150,150,50,Color.RED,Color.WHITE, Shader.TileMode.MIRROR);
        //第一个参数是阴影扩散半径，紧接着的2个参数是阴影在X和Y方向的偏移量，最后一个参数是颜色
        paint.setShader(mShader);
//        paint.setShaderLayer(15,10,10,Color.GRAY);

        canvas.drawLine(0,0,300,300,paint);*/

    }

    private void drawTest5(Canvas canvas) {

        canvas.translate(100, 100);

        canvas.rotate(90);

        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.white));
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_phone_orange);
        canvas.drawBitmap(bitmap, 0, 0, paint);

//        canvas.drawBitmap(bitmap,new Rect(0,0,0,0),new Rect(300,300,300,300),paint);
//        Matrix matrix = new Matrix();
//        canvas.drawBitmap(bitmap,0,paint);
    }

    /*
     * 旋转进度条
     * 中间文字
     */
    private void drawTest6(Canvas canvas) {

//        canvas.rotate(m_angle1,getWidth() / 2, getHeight() / 2);


        int arc_left = getWidth() / 2 - 300;
        int arc_top = getHeight() / 2 - 300;
        int arc_right = getWidth() / 2 + 300;
        int arc_bottom = getHeight() / 2 + 300;

        Paint p_arc = new Paint();
        p_arc.setAntiAlias(true);
        p_arc.setColor(getResources().getColor(R.color.gray));
        Shader shader = new RadialGradient(150, 150, 50, Color.RED, Color.WHITE, Shader.TileMode.MIRROR);
        p_arc.setShader(shader);
        canvas.drawArc(arc_left, arc_top, arc_right, arc_bottom, 0, m_angle1, true, p_arc);

        Paint p_circle = new Paint();
        p_circle.setAntiAlias(true);
        p_circle.setColor(getResources().getColor(R.color.white));
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 200, p_circle);

        Paint p_text = new Paint();
        p_text.setColor(getResources().getColor(R.color.red));
        p_text.setTextSize(50);
        String content = "当前进度:" + m_angle1;

        float x_point =  content.length() / 2 * p_text.getTextSize();

        LogsUtil.normal("m_angle1=" + m_angle1 + ",length=" + x_point+",p_text.getTextSize()="+p_text.getTextSize()+",content.length() / 2="+content.length()*1.0 / 2);
        canvas.drawText(content, x_point, getHeight() / 2 + p_text.getTextSize()/2, p_text);
//        canvas.drawText(content,100, 100, p_text);


    }

    private void drawPath(Canvas canvas) {
        Paint p_path = new Paint();
        p_path.setColor(Color.WHITE);

        Path path = new Path();

        canvas.drawPath(path, p_path);
    }

    private void refresh() {
        if (m_angle1 < 360) {
            m_angle1 = m_angle1 + 2;
        } else {
            m_angle1 = 0;
        }

        if (m_angle2 < 360) {
            m_angle2 = m_angle2 + 2;
        } else {
            m_angle2 = 270;
        }
        postInvalidateDelayed(100);
//        invalidate();
    }

    private void startAnim() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "translationX", 0, 5000);
        animator.setRepeatMode(ObjectAnimator.RESTART);
        animator.setRepeatCount(ObjectAnimator.INFINITE);
        animator.setDuration(5000);
        animator.start();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        //边框画笔
        cyclePaint = new Paint();
        cyclePaint.setAntiAlias(true);
        cyclePaint.setStyle(Paint.Style.STROKE);
        cyclePaint.setStrokeWidth(mStrokeWidth);
        //文字画笔
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(textColor);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(1);
        textPaint.setTextSize(textSize);
        //标注画笔
        labelPaint = new Paint();
        labelPaint.setAntiAlias(true);
        labelPaint.setStyle(Paint.Style.FILL);
        labelPaint.setStrokeWidth(2);
    }

    /**
     * 画圆环
     *
     * @param canvas
     */
    private void drawCycle(Canvas canvas) {
        float startPercent = 0;
        float sweepPercent = 0;
        for (int i = 0; i < strPercent.length; i++) {
            cyclePaint.setColor(mColor[i]);
            startPercent = sweepPercent + startPercent;
            //这里采用比例占100的百分比乘于360的来计算出占用的角度，使用先乘再除可以算出值
            sweepPercent = strPercent[i] * 360 / 100;
            canvas.drawArc(new RectF(0, 0, mRadius, mRadius), startPercent, sweepPercent, false, cyclePaint);
        }
    }

    /**
     * 画文字和标注
     *
     * @param canvas
     */
    private void drawTextAndLabel(Canvas canvas) {
        for (int i = 0; i < strPercent.length; i++) {
            //文字离右边环边距为60，文字与文字之间的距离为40
            canvas.drawText(str[i], mRadius + 60, i * 40, textPaint);

            //画标注,标注离右边环边距为40,y轴则要减去半径（10）的一半才能对齐文字
            labelPaint.setColor(mColor[i]);
            canvas.drawCircle(mRadius + 40, i * 40 - 5, 10, labelPaint);
        }
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LogsUtil.normal("onFinishInflateonFinishInflate");
        /*if (mHandler != null) {
            mHandler.removeMessages(1);
        }*/
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LogsUtil.normal("onDetachedFromWindowonDetachedFromWindow");
        /*if (mHandler != null) {
            mHandler.removeMessages(1);
        }*/
    }


}