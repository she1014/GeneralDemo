package com.techfly.demo.selfview.slidelayout;


import android.content.Context;

import com.techfly.demo.fragment.BaseFragment;


/**
 * <b>Project:</b> SlideDetailsLayout<br>
 * <b>Create Date:</b> 16/1/25<br>ISlideCallback
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class SlideFragment extends BaseFragment {

    private ISlideCallback mISlideCallback;

    public SlideFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof ISlideCallback)) {
            throw new IllegalArgumentException("Your activity must be implements ISlideCallback");
        }
        mISlideCallback = (ISlideCallback) context;
    }

    protected void open(boolean smooth) {
        mISlideCallback.openDetails(smooth);
    }

    protected void close(boolean smooth) {
        mISlideCallback.closeDetails(smooth);
    }

}
