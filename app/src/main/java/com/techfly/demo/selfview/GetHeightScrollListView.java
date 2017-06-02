package com.techfly.demo.selfview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class GetHeightScrollListView extends ListView {
    public GetHeightScrollListView(Context context) {
        super(context);
    }

    public GetHeightScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GetHeightScrollListView(Context context, AttributeSet attrs,
                                   int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}