package com.techfly.demo.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Area implements Serializable {
    private String mId;
    private String mName;
    private ArrayList<City> mList;

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public ArrayList<City> getmList() {
        return mList;
    }

    public void setmList(ArrayList<City> mList) {
        this.mList = mList;
    }

    public Area(){

    }

    public Area(String mId, String mName) {
        this.mId = mId;
        this.mName = mName;
    }

    @Override
    public boolean equals(Object o) {
        Area area = (Area) o;
        if (mName.equals(area.getmName())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Area [mId=" + mId + ", mName=" + mName + ", mList=" + mList
                + "]";
    }

}
