package com.techfly.demo.util;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.techfly.demo.bean.AddressBean;
import com.techfly.demo.bean.Area;
import com.techfly.demo.bean.ContactInfo;
import com.techfly.demo.bean.JsonKey;
import com.techfly.demo.bean.User;

/*
 * 退出登录后，记录的信息一定要清楚
 */

public class SharePreferenceUtils {
    private static final String SP_FILE_NAME = "hylyihui";
    private static final String USER_ID = "user_id";
    private static final String USER_SID = "user_sid";//店铺ID
    private static final String USER_PHONE = "user_phone";
    private static final String USER_PASS = "user_pass";
    private static final String USER_NICK = "user_nick";
    private static final String USER_IMG = "user_img";
    private static final String USER_TOKEN = "user_token";
    private static final String USER_TYPE = "user_type";
    private static final String USER_MONEY = "user_money";
    private static final String IS_FISRT = "is_first";
    private static final String SHARE_CONTENT = "share_content";
    private static final String SHARE_URL = "share_url";
    private static SharePreferenceUtils sharePreferenceUtils = null;
    private SharedPreferences mSharePreference = null;
    private Context mContext = null;
    private static final String AREA_ID = "area_id";
    private static final String AREA_NAME = "area_name";
    private static final String TECH_START_TIME = "tech_start_time";

    private static final String DEF_ADS_ID = "DEF_ADDRESS_ID";
    private static final String DEF_ADS_ISDEF = "DEF_ADS_ISDEF";
    private static final String DEF_ADS_ADDRESS = "DEF_ADS_ADDRESS";
    private static final String DEF_ADS_NAME = "DEF_ADS_NAME";
    private static final String DEF_ADS_LNG = "DEF_ADS_LNG";
    private static final String DEF_ADS_LAT = "DEF_ADS_LAT";
    private static final String DEF_ADS_CITY_NAME = "DEF_ADS_CITY_NAME";
    private static final String DEF_ADS_MOBILE = "DEF_ADS_MOBILE";
    private static final String DEF_ADS_CITY_ID = "DEF_ADS_CITY_ID";

    public synchronized void saveAddress(AddressBean.DataEntity.DatasEntity address) {
        if (mSharePreference != null && address != null) {
            Editor editor = mSharePreference.edit();
            editor.putString(DEF_ADS_ID, address.getId());
            editor.putString(DEF_ADS_ISDEF, address.getIsdefault() + "");
            editor.putString(DEF_ADS_ADDRESS, address.getAddress());
            editor.putString(DEF_ADS_NAME, address.getName());
            editor.putString(DEF_ADS_LNG, address.getLng() + "");
            editor.putString(DEF_ADS_LAT, address.getLat() + "");
            editor.putString(DEF_ADS_CITY_NAME, address.getCname());
            editor.putString(DEF_ADS_MOBILE, address.getMobile());
            editor.putString(DEF_ADS_CITY_ID, address.getCity());
            editor.commit();
        }
    }

    public synchronized AddressBean.DataEntity.DatasEntity getAddress() {
        if (mSharePreference != null) {
            String id = mSharePreference.getString(DEF_ADS_ID, null);
            String isDefault = mSharePreference.getString(DEF_ADS_ISDEF, null);
            String address = mSharePreference.getString(DEF_ADS_ADDRESS, null);
            String name = mSharePreference.getString(DEF_ADS_NAME, null);
            String lng = mSharePreference.getString(DEF_ADS_LNG, null);
            String lat = mSharePreference.getString(DEF_ADS_LAT, null);
            String cname = mSharePreference.getString(DEF_ADS_CITY_NAME, null);
            String mobile = mSharePreference.getString(DEF_ADS_MOBILE, null);
            String cid = mSharePreference.getString(DEF_ADS_CITY_ID, null);
            AddressBean.DataEntity.DatasEntity bean = new AddressBean.DataEntity.DatasEntity();
            if (!TextUtils.isEmpty(id)) {
                bean.setId(id);
                bean.setIsdefault(Integer.parseInt(isDefault));
                bean.setAddress(address);
                bean.setName(name);
                bean.setLng(lng);
                bean.setLat(lat);
                bean.setCname(cname);
                bean.setMobile(mobile);
                bean.setCity(cid);
                return bean;
            }
        }
        return null;
    }

    public synchronized void clearAddress() {
        if (mSharePreference != null) {
            Editor editor = mSharePreference.edit();
            editor.remove(DEF_ADS_ID);
            editor.remove(DEF_ADS_ISDEF);
            editor.remove(DEF_ADS_ADDRESS);
            editor.remove(DEF_ADS_NAME);
            editor.remove(DEF_ADS_LNG);
            editor.remove(DEF_ADS_LAT);
            editor.remove(DEF_ADS_CITY_NAME);
            editor.remove(DEF_ADS_MOBILE);
            editor.remove(DEF_ADS_CITY_ID);
            editor.commit();
        }
    }


    public static SharePreferenceUtils getInstance(Context context) {
        if (sharePreferenceUtils == null) {
            sharePreferenceUtils = new SharePreferenceUtils(context.getApplicationContext());
        }
        return sharePreferenceUtils;
    }

    private SharePreferenceUtils(Context context) {
        mContext = context;
        mSharePreference = mContext.getSharedPreferences(SP_FILE_NAME, Context.MODE_WORLD_WRITEABLE);
    }

    public synchronized void saveUser(User user) {
        if (mSharePreference != null && user != null) {
            Editor editor = mSharePreference.edit();
            editor.putString(USER_ID, user.getmId());
            editor.putString(USER_PHONE, user.getmPhone());
            editor.putString(USER_NICK, user.getmNick());
            editor.putString(USER_PASS, user.getmPass());
            editor.putString(USER_IMG, user.getmImage());
            editor.putString(USER_TOKEN, user.getmToken());
            editor.putString(USER_TYPE, user.getmType());
            editor.putString(USER_MONEY, user.getmMoney());
            editor.putString(USER_SID,user.getiCode());
            editor.commit();
        }
    }


    public synchronized void clearUser() {
        if (mSharePreference != null) {
            Editor editor = mSharePreference.edit();
            editor.remove(USER_ID);
            editor.remove(USER_PHONE);
            editor.remove(USER_NICK);
            editor.remove(USER_IMG);
            editor.remove(USER_PASS);
            editor.remove(USER_TOKEN);
            editor.remove(USER_TYPE);
            editor.remove(USER_MONEY);
            editor.remove(USER_SID);
            editor.commit();
        }
    }

    public synchronized User getUser() {
        if (mSharePreference != null) {
            String id = mSharePreference.getString(USER_ID, null);
            String phone = mSharePreference.getString(USER_PHONE, null);
            String pass = mSharePreference.getString(USER_PASS, null);
            String nick = mSharePreference.getString(USER_NICK, null);
            String image = mSharePreference.getString(USER_IMG, null);
            String token = mSharePreference.getString(USER_TOKEN, null);
            String type = mSharePreference.getString(USER_TYPE, null);
            String money = mSharePreference.getString(USER_MONEY, null);
            String sId = mSharePreference.getString(USER_SID,null);
            User user = new User();
            if (!TextUtils.isEmpty(id)) {
                user.setmId(id);
                user.setmPhone(phone);
                user.setmPass(pass);
                user.setmNick(nick);
                user.setmImage(image);
                user.setmToken(token);
                user.setmType(type);
                user.setmMoney(money);
                user.setiCode(sId);
                return user;
            }
        }
        return null;
    }

    public synchronized void saveContactInfo(ContactInfo info) {
        if (mSharePreference != null && info != null) {
            Editor editor = mSharePreference.edit();
            editor.putString(JsonKey.HomeKey.QQ, info.getmQQ());
            editor.putString(JsonKey.HomeKey.WEIXIN, info.getmWeixin());
            editor.commit();
        }
    }

    public synchronized ContactInfo getContactInfo() {
        if (mSharePreference != null) {
            String qq = mSharePreference.getString(JsonKey.HomeKey.QQ, "");
            String weixin = mSharePreference.getString(JsonKey.HomeKey.WEIXIN, "");
            ContactInfo info = new ContactInfo();
            if (!TextUtils.isEmpty(qq)) {
                info.setmQQ(qq);
                info.setmWeixin(weixin);
                return info;
            }
        }
        return null;
    }

    public Boolean isFirst() {
        if (mSharePreference != null) {
            Boolean isDisplay = mSharePreference.getBoolean(IS_FISRT, true);
            return isDisplay;
        }
        return null;
    }

    public void saveIsFirst(Boolean isDisplay) {
        if (mSharePreference != null && isDisplay != null) {
            Editor editor = mSharePreference.edit();
            editor.putBoolean(IS_FISRT, isDisplay);
            editor.commit();
        }
    }

    public void saveShareInfo(String shareUrl, String shareContent) {
        if (mSharePreference != null && !TextUtils.isEmpty(shareContent) && !TextUtils.isEmpty(shareUrl)) {
            Editor editor = mSharePreference.edit();
            editor.putString(SHARE_URL, shareUrl);
            editor.putString(SHARE_CONTENT, shareContent);
            editor.commit();
        }
    }

    public String getShareUrl() {
        if (mSharePreference != null) {
            String url = mSharePreference.getString(SHARE_URL, "");
            return url;
        }
        return "";
    }

    public String getShareContent() {
        if (mSharePreference != null) {
            String content = mSharePreference.getString(SHARE_CONTENT, "");
            return content;
        }
        return "";
    }

    public synchronized void saveArea(Area area) {
        if (mSharePreference != null && area != null) {
            Editor editor = mSharePreference.edit();
            editor.putString(AREA_ID, area.getmId());
            editor.putString(AREA_NAME, area.getmName());
            editor.commit();
        }
    }

    public synchronized void clearArea() {
        if (mSharePreference != null) {
            Editor editor = mSharePreference.edit();
            editor.remove(AREA_ID);
            editor.remove(AREA_NAME);
            editor.commit();
        }
    }

    public synchronized Area getArea() {
        if (mSharePreference != null) {
            String id = mSharePreference.getString(AREA_ID, null);
            String name = mSharePreference.getString(AREA_NAME, null);
            if (!TextUtils.isEmpty(id)) {
                Area area = new Area();
                area.setmId(id);
                area.setmName(name);
                return area;
            } else {
                Area area = new Area();
                area.setmId("1");
                area.setmName("安徽");
                return area;
            }
        }
        return null;
    }

    public synchronized void saveTechTime(long time) {
        if (mSharePreference != null && time != 0L) {
            Editor editor = mSharePreference.edit();
            editor.putLong(TECH_START_TIME, time);
            editor.commit();
        }
    }

    public synchronized void clearTechTime() {
        if (mSharePreference != null) {
            Editor editor = mSharePreference.edit();
            editor.remove(TECH_START_TIME);
            editor.commit();
        }
    }

    public synchronized long getTechTime() {
        if (mSharePreference != null) {
            long time = mSharePreference.getLong(TECH_START_TIME, 0L);
            if (time != 0L) {
                return time;
            }
        }
        return 0L;
    }
}
