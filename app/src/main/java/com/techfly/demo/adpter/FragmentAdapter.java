package com.techfly.demo.adpter;

/**
 * Created by wangchang on 2016/3/24.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by WangChang on 2016/3/20.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    private Context context;
    private int PAGE_COUNT;
    private String title[] = new String[]{};
    private ArrayList<Fragment> datas = new ArrayList<>();

    public FragmentAdapter(FragmentManager fm, Context context, ArrayList<Fragment> datas, String[] title) {
        super(fm);
        this.context = context;
        this.datas = datas;
        this.title=title;
        PAGE_COUNT=title.length;
    }

    @Override
    public Fragment getItem(int position) {
        return datas.get(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}