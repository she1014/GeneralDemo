package com.techfly.demo.adpter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragementPagerAdapter extends FragmentPagerAdapter {

    Fragment parent;

    private String[] titles = { "签批", "受理" };

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public MyFragementPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MyFragementPagerAdapter(FragmentManager fm, String[] titles, Fragment parent) {
        super(fm);
        setTitles(titles);
        this.parent = parent;
    }

    @Override
    public Fragment getItem(int index) {
        String title = titles[index];
        return parent;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // TODO Auto-generated method stub
        return titles[position];
    }

}
