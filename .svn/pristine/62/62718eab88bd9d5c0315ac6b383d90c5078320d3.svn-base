package com.techfly.demo.activity.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techfly.demo.R;
import com.techfly.demo.selfview.slidelayout.SlideFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Demo6Fragment extends SlideFragment {

    public static Demo6Fragment putIntentInfo(String money,String time){
        Bundle args = new Bundle();
        args.putSerializable("money",money);
        args.putSerializable("time",time);
        Demo6Fragment fragment = new Demo6Fragment();
        //将Bundle设置为fragment的参数
        fragment.setArguments(args);
        return fragment;
    }

    public static Demo6Fragment init(){
        Bundle args = new Bundle();
        Demo6Fragment fragment = new Demo6Fragment();
        //将Bundle设置为fragment的参数
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.layout_slide_detail1, container, false);

        ButterKnife.inject(this, view);

        return view;
    }


}
