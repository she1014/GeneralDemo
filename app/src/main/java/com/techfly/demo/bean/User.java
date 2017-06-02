package com.techfly.demo.bean;

public class User {
    private String mId;
    private String mPhone;
    private String mPass;
    private String mNick;
    private String mMessage;
    private String mImage;
    private String mToken;
    private String iCode;//店铺ID
    private String mCityId;

    public User(){

    }

    public User(String mId, String mPhone, String mPass, String mNick, String mMessage, String mImage, String mToken, String iCode, String mMoney, String mType, String mCityId) {
        this.mId = mId;
        this.mPhone = mPhone;
        this.mPass = mPass;
        this.mNick = mNick;
        this.mMessage = mMessage;
        this.mImage = mImage;
        this.mToken = mToken;
        this.iCode = iCode;//shop_id
        this.mMoney = mMoney;
        this.mType = mType;
        this.mCityId = mCityId;
    }

    public String getiCode() {
        return iCode;
    }

    public void setiCode(String iCode) {
        this.iCode = iCode;
    }

    public String getmToken() {
        return mToken;
    }

    public void setmToken(String mToken) {
        this.mToken = mToken;
    }

    public String getmMoney() {
        return mMoney;
    }

    public void setmMoney(String mMoney) {
        this.mMoney = mMoney;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    private String mMoney;
    private String mType;

    public String getmCityId() {
        return mCityId;
    }

    public void setmCityId(String mCityId) {
        this.mCityId = mCityId;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmPass() {
        return mPass;
    }

    public void setmPass(String mPass) {
        this.mPass = mPass;
    }

    public String getmNick() {
        return mNick;
    }

    public void setmNick(String mNick) {
        this.mNick = mNick;
    }

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    @Override
    public String toString() {
        return "User [mId=" + mId + ", mPhone=" + mPhone + ", mPass=" + mPass + ", mNick=" + mNick
                + ", mMessage=" + mMessage + ", mImage=" + mImage + ", mToken=" + mToken + ", iCode="
                + iCode + ", mMoney=" + mMoney + ", mType=" + mType + "]";
    }
}
