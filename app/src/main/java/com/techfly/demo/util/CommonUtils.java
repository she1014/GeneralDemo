package com.techfly.demo.util;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bob on 2015/2/2.
 * һЩ��ͨ�ķ����ĵ���(GPS,�����ʽ����Ļ�߶ȵȵ�)
 */
public class CommonUtils {


    /**
     * �õ�״̬���߶�
     *
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Activity context) {
        int statusHeight = 0;
        Rect frame = new Rect();
        context.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        statusHeight = frame.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = context.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    /**
     * ��õ�ǰ�İ汾��Ϣ
     *
     * @return
     */
    public static String[] getVersionInfo(Context context) {
        String[] version = new String[2];

        PackageManager packageManager = context.getPackageManager();

        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            version[0] = String.valueOf(packageInfo.versionCode);
            version[1] = packageInfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return version;
    }

    /**
     * �����ʽ�Ƿ���ȷ
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {

        if (TextUtils.isEmpty(email))
            return false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches())
            return true;
        else
            return false;

    }

    /**
     * �Ƿ��� GPS
     *
     * @param context
     * @return
     */
    public static boolean isOpenGPS(Context context) {

        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean isGPSEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNPEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (isGPSEnable || isNPEnable)
            return true;


        return false;
    }


    /*public static String removeBrace(String result) {
        String content = "";
        content = result.replace("{}", "\"\"");
        return content;
    }*/
    /*
     * 去除大括号
     */
    public static String removeBrace(String result) {
        String content = "";
        content = result.replace("{}","\"\"");

        try {
            JSONObject jsonObject = new JSONObject(content);
            String data_string = jsonObject.getString("data");
            LogsUtil.normal("data_string=" + data_string);
            if(TextUtils.isEmpty(data_string)){
                return "";
            }
        } catch (JSONException e) {
            LogsUtil.normal("Error="+e.getMessage());
            e.printStackTrace();
        }
        return content;
    }

    /*
     * String转List
     */
    public static List<String> strToList(String result) {
        List<String> list = new ArrayList<String>();
        String[] content = result.split(" ");

        for(int i = 0;i < content.length;i++){
            list.add(content[i]);
        }

        return list;
    }

    /*
     * array转List
     */
    public static List<String> arrayToList(String[] array) {
        List<String> list = new ArrayList<String>();

        for(int i = 0;i < array.length;i++){
            list.add(array[i]);
        }

        return list;
    }

    /*
    * array转List,指定返回数量
    */
    public static List<String> arrayToList(String[] array,int count) {
        List<String> list = new ArrayList<String>();

        for(int i = 0;i < count;i++){
            list.add(array[i]);
        }

        return list;
    }

    public static String getSplitLines(String reasult){
        String[] contentArray = reasult.split("#");
        String content = "";
        for(int i = 0;i < contentArray.length;i++){
            if(i == contentArray.length-1){
                content = content + contentArray[i];
            }else{
                content = content + contentArray[i] + "\n";
            }
        }
        return content;
    }

    public static void openGPS(Context context) {

        Intent GPSIntent = new Intent();

        GPSIntent.setClassName("com.android.settings",
                "com.android.settings.widget.SettingsAppWidgetProvider");

        GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
        GPSIntent.setData(Uri.parse("custom:3"));

        try {
            PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    /*
     * 去除emoj符合
     */
    public static InputFilter emojiFilter = new InputFilter() {

        Pattern emoji = Pattern.compile(
                "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest,
                                   int dstart, int dend) {
            Matcher emojiMatcher = emoji.matcher(source);

            if (emojiMatcher.find()) {
                return "";
            }
            return null;
        }
    };

    public static void startPhotoZoom(Context context, Uri uri,
                                      int REQUE_CODE_CROP) {
        int dp = 500;

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);// 去黑边
        intent.putExtra("scaleUpIfNeeded", true);// 去黑边
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);//输出是X方向的比例
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高，切忌不要再改动下列数字，会卡死
        intent.putExtra("outputX", dp);//输出X方向的像素
        intent.putExtra("outputY", dp);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", false);//设置为不返回数据

        ((Activity) context).startActivityForResult(intent, REQUE_CODE_CROP);
    }

    public static Bitmap getBitmapFromUri(Uri uri, Context mContext) {
        try {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), uri);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String listToString(List<String> list){
        String result = "";
        for(int i = 0;i < list.size();i++){
            if(i == list.size() - 1){
                result = result + list.get(i);
            }else{
                result = result + list.get(i) + " ";
            }
        }
        return result;
    }


}
