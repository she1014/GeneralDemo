package com.techfly.demo.activity.demo;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techfly.demo.R;
import com.techfly.demo.activity.qq_demo.adapt.MyViewPageAdapt;
import com.techfly.demo.activity.qq_demo.adapt.SlideAdapt;
import com.techfly.demo.activity.qq_demo.fragment.ContactFragment;
import com.techfly.demo.activity.qq_demo.fragment.MessageFragment;
import com.techfly.demo.activity.qq_demo.fragment.SpaceFragment;
import com.techfly.demo.activity.qq_demo.view.MyLineLayout;
import com.techfly.demo.activity.qq_demo.view.MyViewPage;
import com.techfly.demo.activity.qq_demo.view.SlideLayout;

import java.util.ArrayList;

public class Demo3Activity extends FragmentActivity implements View.OnClickListener {

    private LinearLayout mSlideLine;
    private ImageView mSlideMyPic;
    private TextView mSlideMyName;
    private TextView mSlideMyQm;
    private ListView mSlideList;

    public static MyLineLayout mMainLine;
    private RelativeLayout mMainTitleRel;
    private ImageView mMainMyPic;
    private ImageView mMainAdd;
    private MyViewPage mMainVp;
    private RadioButton mMainMsg;
    private RadioButton mMainCon;
    private RadioButton mMainSpac;
    private SlideLayout mSlideLayout;


    private MyViewPageAdapt mPageAdapt;
    private MessageFragment mMessageFragment;


    private void assignViews() {

        mSlideLayout = (SlideLayout) findViewById(R.id.slide_layout);

        mSlideLine = (LinearLayout) findViewById(R.id.slide_line);
        mSlideMyPic = (ImageView) findViewById(R.id.slide_my_pic);
        mSlideMyName = (TextView) findViewById(R.id.slide_my_name);
        mSlideMyQm = (TextView) findViewById(R.id.slide_my_qm);
        mSlideList = (ListView) findViewById(R.id.slide_list);

        mMainLine = (MyLineLayout) findViewById(R.id.main_line);
        mMainTitleRel = (RelativeLayout) findViewById(R.id.main_title_rel);
        mMainMyPic = (ImageView) findViewById(R.id.main_my_pic);
        mMainAdd = (ImageView) findViewById(R.id.main_add);
        mMainVp = (MyViewPage) findViewById(R.id.main_vp);
        mMainMsg = (RadioButton) findViewById(R.id.main_msg);
        mMainCon = (RadioButton) findViewById(R.id.main_con);
        mMainSpac = (RadioButton) findViewById(R.id.main_spac);
    }


    public static Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_demo3);

        assignViews();

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            mMainLine.getChildAt(0).setFitsSystemWindows(true);
        }

        initData();
        initListener();
        //cdd8da
    }


    ArrayList<Fragment> mList = new ArrayList<Fragment>();

    private void initData() {
        mSlideList.setAdapter(new SlideAdapt(this));
        mSlideList.setSelection(1);

        mMainLine.setSlideLayout(mSlideLayout);

        mMessageFragment = new MessageFragment();
        mMessageFragment.setSlideLayout(mSlideLayout);

        mList.add(mMessageFragment);
        mList.add(new ContactFragment());
        mList.add(new SpaceFragment());

        mPageAdapt = new MyViewPageAdapt(getSupportFragmentManager(), mList);
        mMainVp.setAdapter(mPageAdapt);


    }

    private void initListener() {
        mMainMyPic.setOnClickListener(this);
        mSlideLayout.setOnSlideListener(new SlideLayout.OnSlideListener() {
            @Override
            public void onSlide(View view, int left, float persent) {
                mMainMyPic.setAlpha(1 - persent);
            }
        });

        mMainMsg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mMainVp.setCurrentItem(0);
                }
            }
        });

        mMainCon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    mMainVp.setCurrentItem(1);
                }
            }
        });

        mMainSpac.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    mMainVp.setCurrentItem(2);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_my_pic:
                if (mSlideLayout.isMenuIsOpen()) {
                    mSlideLayout.closeMenu();
                } else {
                    mSlideLayout.openMenu();
                }
                break;
        }
    }


}
