package com.techfly.demo.activity.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.selfview.BubbleLayout;
import com.techfly.demo.selfview.wheelview.BubbleView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class Demo26Activity extends BaseActivity {

    @InjectView(R.id.view_btn)
    Button view_btn;
    @InjectView(R.id.view1)
    BubbleLayout view1;
    @InjectView(R.id.view2)
    BubbleView view2;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo26);

        ButterKnife.inject(this);

    }

    @OnClick(R.id.view_btn)
    public void clickSwitch(){
        if(view1.getVisibility() == View.VISIBLE){
            view1.setVisibility(View.INVISIBLE);
            view2.setVisibility(View.VISIBLE);
        }else{
            view1.setVisibility(View.VISIBLE);
            view2.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


}
