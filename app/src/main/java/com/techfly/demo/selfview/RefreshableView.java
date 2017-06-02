package com.techfly.demo.selfview;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.techfly.demo.R;

/**
 * 可进行左拉加载更多的自定义控件。
 * 原理：1、当横向网格右边缘滑动到父控件右边缘时，让隐藏的侧拉头逐渐出现；
 *      2、松手后，开始刷新；
 *      3、完成后隐藏侧拉头
 *
 * @author jxl
 *
 */
public class RefreshableView extends RelativeLayout {

    private static final String TAG = "hello";

    /**
     * 左拉状态
     */
    public static final int STATUS_PULL_TO_REFRESH = 0;

    /**
     * 释放立即刷新状态
     */
    public static final int STATUS_RELEASE_TO_REFRESH = 1;

    /**
     * 正在刷新状态
     */
    public static final int STATUS_REFRESHING = 2;

    /**
     * 刷新完成或未刷新状态
     */
    public static final int STATUS_REFRESH_FINISHED = 3;

    /**
     * 左拉头部回滚的速度
     */
    public static final int SCROLL_SPEED = -20;

    /**
     * 左拉加载更多的回调接口
     */
    private PullToRefreshListener mListener;

    /**
     * 左拉头的View
     */
    private View header;

    /**
     * 需要去左拉刷新的横置RecyclerView
     */
    private RecyclerView refreshView;

    /**
     * 刷新时显示的进度条
     */
    private ProgressBar progressBar;

    /**
     * 指示左拉和释放的箭头
     */
    private ImageView arrow;

    /**
     * 左拉头的布局参数
     */
    private LayoutParams headerLayoutParams;

    /**
     * 为了防止不同界面的左拉加载更多在上次更新时间上互相有冲突，使用id来做区分
     */
    private int mId = -1;

    /**
     * 左拉头的宽度
     */
    private int hideHeaderWidth;

    /**
     * 当前处理什么状态，可选值有STATUS_PULL_TO_REFRESH, STATUS_RELEASE_TO_REFRESH,
     * STATUS_REFRESHING 和 STATUS_REFRESH_FINISHED
     */
    private int currentStatus = STATUS_REFRESH_FINISHED;;

    /**
     * 记录上一次的状态是什么，避免进行重复操作
     */
    private int lastStatus = currentStatus;

    /**
     * 手指按下时的屏幕横坐标
     */
    private float xDown;

    /**
     * 在被判定为滚动之前用户手指可以移动的最大值。
     */
    private int touchSlop;

    /**
     * 是否已加载过一次layout，这里onLayout中的初始化只需加载一次
     */
    private boolean loadOnce;

    /**
     * 当前是否可以左拉，只有RecyclerView滚动到头的时候才允许左拉
     */
    private boolean ableToPull;

    /**
     * 是否允许加载
     */
    private boolean allowPull = true;

    /**
     * 左拉加载更多控件的构造函数
     *
     * @param context
     * @param attrs
     */
    public RefreshableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    /**
     * 进行一些关键性的初始化操作，比如：添加一个左拉头的布局，将左拉头向右偏移进行隐藏。
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed && !loadOnce) {
            header = getChildAt(1);
            progressBar = (ProgressBar) header.findViewById(R.id.progress_bar);
            arrow = (ImageView) header.findViewById(R.id.arrow);

            refreshView = (RecyclerView) getChildAt(0);
            if(refreshView.getWidth() >= getWidth()) {
                hideHeaderWidth = - header.getWidth();
                headerLayoutParams = (LayoutParams) header.getLayoutParams();
                headerLayoutParams.rightMargin = hideHeaderWidth;
                headerLayoutParams.addRule(CENTER_VERTICAL);
                headerLayoutParams.addRule(ALIGN_PARENT_END);
                header.setVisibility(View.INVISIBLE);//开始时要隐藏侧拉头
            } else {//如果refreshView 为空，则不允许侧拉加载更多
                header.setVisibility(View.GONE);
                ableToPull = false;
            }

            loadOnce = true;
        }
    }

    /**
     * 设置是否开启下拉加载更多
     * @param allowPull
     */
    public void setAllowPull(boolean allowPull) {
        this.allowPull = allowPull;
    }

    /**
     * 当refreshView 被触摸时调用，其中处理了各种左拉加载更多的具体逻辑。
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        setIsAbleToPull(event);
        if (ableToPull) {
            header.setVisibility(View.VISIBLE);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    xDown = event.getRawX();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float xMove = event.getRawX();
                    int distance = (int) (xMove - xDown);
                    // 如果手指右滑状态，并且左拉头是完全隐藏的，就屏蔽左拉事件
                    if (distance >= 0 && headerLayoutParams.rightMargin <= hideHeaderWidth) {
                        return super.dispatchTouchEvent(event);
                    }
                    if (distance > -touchSlop) {
                        return false;
                    }
                    if (currentStatus != STATUS_REFRESHING) {
                        if (headerLayoutParams.rightMargin >= 0) {
                            currentStatus = STATUS_RELEASE_TO_REFRESH;
                        } else {
                            currentStatus = STATUS_PULL_TO_REFRESH;
                        }
                        // 通过偏移左拉头的topMargin值，来实现左拉效果
                        headerLayoutParams.rightMargin = (-distance / 2) + hideHeaderWidth;
                        header.setLayoutParams(headerLayoutParams);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                default:
                    if (currentStatus == STATUS_RELEASE_TO_REFRESH) {
                        // 松手时如果是释放立即刷新状态，就去调用正在刷新的任务
                        new RefreshingTask().execute();
                    } else if (currentStatus == STATUS_PULL_TO_REFRESH) {
                        // 松手时如果是左拉状态，就去调用隐藏左拉头的任务
                        new HideHeaderTask().execute();
                    }
                    break;
            }
            // 时刻记得更新左拉头中的信息
            if (currentStatus == STATUS_PULL_TO_REFRESH
                    || currentStatus == STATUS_RELEASE_TO_REFRESH) {
                updateHeaderView();
                // 当前正处于左拉或释放状态，要让refreshView 失去焦点，否则被点击的那一项会一直处于选中状态
                refreshView.setPressed(false);
                refreshView.setFocusable(false);
                refreshView.setFocusableInTouchMode(false);
                lastStatus = currentStatus;
                // 当前正处于左拉或释放状态，通过返回true屏蔽掉refreshView 的滚动事件
                return true;
            }
        }
        return super.dispatchTouchEvent(event);
    }

    /**
     * 给左拉加载更多控件注册一个监听器。
     *
     * @param listener
     *            监听器的实现。
     * @param id
     *            为了防止不同界面的左拉加载更多在上次更新时间上互相有冲突， 请不同界面在注册左拉加载更多监听器时一定要传入不同的id。
     */
    public void setOnRefreshListener(PullToRefreshListener listener, int id) {
        mListener = listener;
        mId = id;
    }

    /**
     * 当所有的刷新逻辑完成后，记录调用一下，否则你的refreshView 将一直处于正在刷新状态。
     */
    public void finishRefreshing() {
        currentStatus = STATUS_REFRESH_FINISHED;
        new HideHeaderTask().execute();
    }

    /**
     * 根据当前refreshView 的滚动状态来设定 {@link #ableToPull}
     * 的值，每次都需要在onTouch中第一个执行，这样可以判断出当前应该是滚动refreshView ，还是应该进行左拉。
     *
     * @param event
     */
    private void setIsAbleToPull(MotionEvent event) {
        View lastChild = refreshView.getChildAt(refreshView.getChildCount() -1);
        if(lastChild != null) {
            //如果最后一个item的右边缘小于等于控件的款宽度，说明refreshView 已经滑动到左边或者左拉，此时应该允许左拉加载更多
            if(lastChild.getRight() <= getWidth()) {
                ableToPull = true;
            } else {
                if (headerLayoutParams.rightMargin != hideHeaderWidth) {
                    headerLayoutParams.rightMargin = hideHeaderWidth;
                    header.setLayoutParams(headerLayoutParams);
                }
                ableToPull = false;
            }
        } else {
            //如果没有元素，不允许加载更多
            ableToPull = false;
        }
        if(!allowPull) {
            ableToPull = false;
        }
    }

    /**
     * 更新左拉头中的信息。
     */
    private void updateHeaderView() {
        if (lastStatus != currentStatus) {
            if (currentStatus == STATUS_PULL_TO_REFRESH) {
                arrow.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                rotateArrow();
            } else if (currentStatus == STATUS_RELEASE_TO_REFRESH) {
                arrow.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                rotateArrow();
            } else if (currentStatus == STATUS_REFRESHING) {
                progressBar.setVisibility(View.VISIBLE);
                arrow.clearAnimation();
                arrow.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 根据当前的状态来旋转箭头。
     */
    private void rotateArrow() {
        float pivotX = arrow.getWidth() / 2f;
        float pivotY = arrow.getHeight() / 2f;
        float fromDegrees = 0f;
        float toDegrees = 0f;
        if (currentStatus == STATUS_PULL_TO_REFRESH) {
            fromDegrees = 180f;
            toDegrees = 360f;
        } else if (currentStatus == STATUS_RELEASE_TO_REFRESH) {
            fromDegrees = 0f;
            toDegrees = 180f;
        }
        RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees, pivotX, pivotY);
        animation.setDuration(100);
        animation.setFillAfter(true);
        arrow.startAnimation(animation);
    }

    /**
     * 正在刷新的任务，在此任务中会去回调注册进来的左拉加载更多监听器。
     *
     * @author jxl
     */
    class RefreshingTask extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected Integer doInBackground(Void... params) {
            int topMargin = headerLayoutParams.rightMargin;
            while (true) {
                topMargin = topMargin + SCROLL_SPEED;
                if (topMargin <= 0) {
                    topMargin = 0;
                    break;
                }
                publishProgress(topMargin);
                sleep(10);
            }
            currentStatus = STATUS_REFRESHING;
            publishProgress(0);
            return topMargin;
        }

        @Override
        protected void onProgressUpdate(Integer... topMargin) {
            updateHeaderView();
            headerLayoutParams.rightMargin = topMargin[0];
            header.setLayoutParams(headerLayoutParams);
        }

        @Override
        protected void onPostExecute(Integer topMargin) {
            if (mListener != null) {
                mListener.onRefresh();
            }
        }
    }

    /**
     * 隐藏左拉头的任务，当未进行左拉加载更多或左拉加载更多完成后，此任务将会使左拉头重新隐藏。
     *
     * @author jxl
     */
    class HideHeaderTask extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected Integer doInBackground(Void... params) {
            int topMargin = headerLayoutParams.rightMargin;
            while (true) {
                topMargin = topMargin + SCROLL_SPEED;
                if (topMargin <= hideHeaderWidth) {
                    topMargin = hideHeaderWidth;
                    break;
                }
                publishProgress(topMargin);
                sleep(10);
            }
            return topMargin;
        }

        @Override
        protected void onProgressUpdate(Integer... topMargin) {
            headerLayoutParams.rightMargin = topMargin[0];
            header.setLayoutParams(headerLayoutParams);
        }

        @Override
        protected void onPostExecute(Integer topMargin) {
            headerLayoutParams.rightMargin = topMargin;
            header.setLayoutParams(headerLayoutParams);
            currentStatus = STATUS_REFRESH_FINISHED;
        }
    }

    /**
     * 使当前线程睡眠指定的毫秒数。
     *
     * @param time
     *            指定当前线程睡眠多久，以毫秒为单位
     */
    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 左拉加载更多的监听器，使用左拉加载更多的地方应该注册此监听器来获取刷新回调。
     *
     * @author jxl
     */
    public interface PullToRefreshListener {

        /**
         * 刷新时会去回调此方法，在方法内编写具体的刷新逻辑。注意此方法是在子线程中调用的， 你可以不必另开线程来进行耗时操作。
         */
        void onRefresh();

    }

}