package com.techfly.demo.bean;

import java.io.Serializable;

public class Comments implements Serializable {
    private static final long serialVersionUID = 2L;
    private int mId;
    private String mName;
    private String mPhone;
    private String mTitle;
    private String mContent;
    private String mStarScore;
    private int mVerfy;
    private String mTime;
    private String mReplay;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmContent() {
        return mContent;
    }

    public void setmContent(String mContent) {
        this.mContent = mContent;
    }

    public String getmStarScore() {
        return mStarScore;
    }

    public void setmStarScore(String mStarScore) {
        this.mStarScore = mStarScore;
    }

    public int getmVerfy() {
        return mVerfy;
    }

    public void setmVerfy(int mVerfy) {
        this.mVerfy = mVerfy;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getmReplay() {
        return mReplay;
    }

    public void setmReplay(String mReplay) {
        this.mReplay = mReplay;
    }

}
