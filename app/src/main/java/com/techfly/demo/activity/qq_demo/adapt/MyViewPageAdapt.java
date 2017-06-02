package com.techfly.demo.activity.qq_demo.adapt;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by Root on 2016/6/23.
 */
public class MyViewPageAdapt extends FragmentStatePagerAdapter {
    ArrayList<Fragment> listFragment;
    public MyViewPageAdapt(FragmentManager fm , ArrayList<Fragment> list) {
        super(fm);
        listFragment = list;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }


}
