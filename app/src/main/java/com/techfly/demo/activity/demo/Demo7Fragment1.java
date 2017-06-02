package com.techfly.demo.activity.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techfly.demo.R;
import com.techfly.demo.fragment.BaseFragment;

import butterknife.ButterKnife;

public class Demo7Fragment1 extends BaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_demo7, container, false);

        ButterKnife.inject(this, view);

        return view;
    }

    /*@Override
    public void onItemClick(View view, int postion) {
        LogsUtil.normal("Demo7Fragment1="+postion);
    }*/
}
