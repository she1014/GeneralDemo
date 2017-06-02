package com.techfly.demo.selfview;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class ForbidScrollRecycleView extends RecyclerView {
    public ForbidScrollRecycleView(Context context) {
        super(context);
    }

    public ForbidScrollRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ForbidScrollRecycleView(Context context, AttributeSet attrs,
                                   int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                View.MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}