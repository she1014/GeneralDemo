package com.techfly.demo.activity.qq_demo.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.techfly.demo.R;
import com.techfly.demo.activity.demo.Demo3Activity;
import com.techfly.demo.activity.qq_demo.adapt.MyListAdapt;
import com.techfly.demo.activity.qq_demo.view.DragLayout;
import com.techfly.demo.activity.qq_demo.view.RefreshListview;
import com.techfly.demo.activity.qq_demo.view.SlideLayout;

import java.util.ArrayList;

/**
 * Created by Root on 2016/6/23.
 */
public class MessageFragment extends Fragment {

    private DragLayout mDragLayout;
    private RefreshListview mListView;
    private ArrayList<String> mData;
    private MyListAdapt mAdapter;

    private SlideLayout mSlideLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View view = View.inflate(getActivity(), R.layout.layout_refresh_lv,null);
            mListView = (RefreshListview) view.findViewById(R.id.list_view);

            mData = new ArrayList<String>();
            for (int i = 1; i < 51; i++) {

                mData.add("走吧走吧" + i);

            }

            mAdapter = new MyListAdapt(getActivity(), mData,mSlideLayout);
            mListView.setAdapter(mAdapter);

            mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    ArrayList<DragLayout> opListItem = mAdapter.getOpListItem();

                    for(DragLayout op : opListItem){  //
                        op.close();

                    }

                    opListItem.clear();
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                }
            });

        initListener();

        return view;
    }

    private void initListener() {
        mListView.setOnRefreshListener(new RefreshListview.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Demo3Activity.mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mListView.RefreshSuccess(Demo3Activity.mHandler);
                    }
                },3000);

            }
        });
    }


    /**
     */
    public void setSlideLayout(SlideLayout slideLayout) {
        mSlideLayout = slideLayout;
    }
}
