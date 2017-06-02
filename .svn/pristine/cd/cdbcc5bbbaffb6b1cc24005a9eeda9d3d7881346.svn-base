package com.techfly.demo.selfview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class ForbidScrollGridView extends GridView {

    public ForbidScrollGridView(Context context) {
        super(context);
    }

    public ForbidScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ForbidScrollGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO 自动生成的构造函数存根  
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO 自动生成的方法存根  
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}