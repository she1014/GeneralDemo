package com.techfly.demo.fragment;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.activity.goods.GoodManagerMenuActivity;
import com.techfly.demo.activity.release.CommentReleaseActivity;
import com.techfly.demo.activity.setting.SetDeliveryActivity;
import com.techfly.demo.activity.user.LoginActivity;
import com.techfly.demo.activity.user.ModifyPassActivity;
import com.techfly.demo.bean.EventBean;
import com.techfly.demo.bean.ReasultBean;
import com.techfly.demo.bean.UpdataVersionBean;
import com.techfly.demo.bean.User;
import com.techfly.demo.bean.UserInfo;
import com.techfly.demo.util.DialogUtil;
import com.techfly.demo.util.FileUtils;
import com.techfly.demo.util.ImageLoaderUtil;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.PreferenceUtil;
import com.techfly.demo.util.SharePreferenceUtils;
import com.techfly.demo.util.ToastUtil;
import com.techfly.demo.util.UpdateUtil;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


/**
 * Created by ssm on 2015/12/22.
 */
public class MyFragment extends BaseFragment {

    @InjectView(R.id.top_title_tv)
    TextView top_title_tv;

    @InjectView(R.id.mine_avator_iv)
    ImageView mine_avator_iv;
    @InjectView(R.id.mine_month_money_tv)
    TextView mine_month_money_tv;

    @InjectView(R.id.mine_version_tv)
    TextView mine_version_tv;
    @InjectView(R.id.mine_delivery_tv)
    TextView mine_delivery_tv;

    public View view;
    private Context mContext;//    private FragmentChangeListener fragmentChangeListener;

    private User mUser;
    private UserInfo userInfo;
    private SharePreferenceUtils mPreferenceUtils;

    private Boolean isVisible = false;
    private Boolean isUpdateAvator = true;

    private static int CAMERA_REQUEST_CODE = 1;
    private static int GALLERY_REQUEST_CODE = 2;
    private static int CROP_REQUEST_CODE = 3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_mine3, null);

        LogsUtil.normal("MyFragment.onCreateView");

        ButterKnife.inject(this, view);

        EventBus.getDefault().register(this);

        mContext = getActivity();

        initView();

        return view;
    }

    private void initView() {
        top_title_tv.setText(R.string.mine_center);
        mine_version_tv.setText("当前版本:" + getCurVersion());

        mPreferenceUtils = SharePreferenceUtils.getInstance(getActivity());
        mUser = SharePreferenceUtils.getInstance(mContext).getUser();

        LogsUtil.normal("MyFragment.initViews");



    }

    private void updateView(UserInfo.DataEntity dataEntity) {
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public String getCurVersion() {
        try {
            PackageManager manager = getActivity().getPackageManager();
            PackageInfo info = manager.getPackageInfo(getActivity().getPackageName(), 0);
            String versionName = info.versionName;
            LogsUtil.normal("getCurVersion.versionName="+versionName);
            return versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0.0.0";
        }
    }

    //清除缓存
    public static void showCleanDialog(final Context context) {
        final AlertDialog dialog = new AlertDialog.Builder(context, R.style.MyDialog).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);  //此处可以设置dialog显示的位置
        View view = View.inflate(context, R.layout.dialog_middle_login, null);
        window.setContentView(view);


        TextView tv0 = (TextView) view.findViewById(R.id.title_tv);
        TextView tv1 = (TextView) view.findViewById(R.id.middle_tv);
        TextView tv2 = (TextView) view.findViewById(R.id.cancel);
        TextView tv3 = (TextView) view.findViewById(R.id.ok);

        long size1 = FileUtils.getDirSize(context.getCacheDir());
        long size2 = FileUtils.getDirSize(context.getFilesDir());
        long size = size1 + size2;
        String result = FileUtils.getFormatSize(size);

        tv0.setText("提示");
        tv1.setGravity(Gravity.CENTER);
        tv1.setText("当前缓存" + result + ",确认清除？");

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Constant.IS_LOGIN = true;
                dialog.dismiss();
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                FileUtils.getInst().delete(context.getCacheDir());
                FileUtils.getInst().delete(context.getFilesDir());
//                FileUtils.getInst().delete(context.getCacheDir());
            }
        });
    }

    private void showPhotoSelectDialog(final Context context) {

        setBackgroundAlpha((float) 0.7);

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_select_photo, null);

        final PopupWindow pop = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);

        pop.update();
        pop.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);  //设置弹出菜单可输入
        pop.setTouchable(true);       //设置popupwindow可点击
        pop.setOutsideTouchable(false);//设置popupwindow外部可点击

        pop.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        pop.setFocusable(false);
        // 实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0x7DC0C0C0);
//        pop.setBackgroundDrawable(dw);

        // 设置popWindow的显示和消失动画
//        pop.setAnimationStyle(R.style.toastdig);

        pop.setBackgroundDrawable(new BitmapDrawable());
        // 在底部显示
        pop.showAtLocation(view, Gravity.BOTTOM, 0, 0);


        final TextView cancel_tv = (TextView) view.findViewById(R.id.photo_cancel_tv);
        final TextView camera_tv = (TextView) view.findViewById(R.id.photo_camera_tv);
        final TextView album_tv = (TextView) view.findViewById(R.id.photo_album_tv);


        cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundAlpha((float) 1.0);
                pop.dismiss();
            }
        });

        camera_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundAlpha((float) 1.0);
                Intent cIntent = new Intent(Intent.ACTION_PICK, null);
                cIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.
                        getExternalStorageDirectory(), "/Download/upload.png")));
                startActivityForResult(cIntent, CAMERA_REQUEST_CODE);

                pop.dismiss();
            }
        });

        album_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBackgroundAlpha((float) 1.0);
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_REQUEST_CODE);
                pop.dismiss();
            }
        });

        pop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setBackgroundAlpha((float) 1.0);
                pop.dismiss();
            }
        });

    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if (isVisibleToUser) {
            if(isUpdateAvator){

                LogsUtil.normal("MyFragment.setUserVisibleHint");

                isUpdateAvator = false;

                String url = Constant.CURRENT_AVATAR;
                if(!TextUtils.isEmpty(url)){
                    ImageLoader.getInstance().displayImage(url,mine_avator_iv,ImageLoaderUtil.mAvatarIconLoaderOptions);
                }

                if(TextUtils.isEmpty(Constant.CURRENT_SHOP_DELIVERY)){
                    mine_delivery_tv.setText("当前金额:¥0");
                }else{
                    mine_delivery_tv.setText("当前金额:¥"+Constant.CURRENT_SHOP_DELIVERY);
                }

                mine_month_money_tv.setText("本月收益\n\n¥" + Constant.CURRENT_MONTH_MONEY);
            }
        } else {
        }
    }


    @OnClick(R.id.mine_rl1)
    public void jumpToAvator() {
        if (checkLogin(mContext)) {
//            ToastUtil.DisplayToast(mContext,"上传头像...");
            showPhotoSelectDialog(mContext);
        }
    }

    @OnClick(R.id.mine_rl2)
    public void jumpToModifyPass() {
        if (checkLogin(mContext)) {
            Intent intent = new Intent(mContext, ModifyPassActivity.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.mine_rl3)
    public void jumpToCache() {
        if (checkLogin(mContext)) {
            showCleanDialog(mContext);
        }
    }

    @OnClick(R.id.mine_rl4)
    public void jumpToVersion() {
        try {
            //当前应用的版本号
            PackageManager pm = mContext.getPackageManager();
            PackageInfo pInfo = pm.getPackageInfo(mContext.getPackageName(), 0);
            String version = pInfo.versionCode + "";

//            showProcessDialog();
            postUpdateVersionInfoAPI(version);
        } catch (Exception e) {

        }
    }

    @OnClick(R.id.mine_rl5)
    public void jumpToExit() {
        if (checkLogin(mContext)) {
            DialogUtil.exitAccountDialog(mContext, mUser.getmPhone());
        }
    }

    @OnClick(R.id.mine_rl6)
    public void jumpToRelease() {
        if (checkLogin(mContext)) {
            Intent intent = new Intent(mContext, CommentReleaseActivity.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.mine_rl7)
    public void jumpToManager() {
        if (checkLogin(mContext)) {
            Intent intent = new Intent(mContext, GoodManagerMenuActivity.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.mine_rl8)
    public void jumpToDelivery() {
        if (checkLogin(mContext)) {
            Intent intent = new Intent(mContext, SetDeliveryActivity.class);
            intent.putExtra(Constant.CONFIG_INTENT_CONTENT,Constant.CURRENT_SHOP_DELIVERY);
            startActivityForResult(intent, Constant.REQUESTCODE_FREIGHT);
        }
    }


    public void onEventMainThread(EventBean bean) {

        if (bean.getAction().equals(EventBean.EVENT_REFRESH_UI) && isVisible) {
            Log.i("TTSS", "MyFragment.EVENT_REFRESH_UI");
            initView();
        }
        if (bean.getAction().equals(EventBean.EVENT_EXIT_ACCOUNT)) {

            postUserLogOutApi(mUser.getmId(),mUser.getmToken());

            mPreferenceUtils.clearUser();
            mPreferenceUtils.clearAddress();
            PreferenceUtil.clearBooleanSharePreference(mContext, Constant.CONFIG_IS_LOGIN);

            EventBus.getDefault().post(new EventBean(EventBean.EVENT_REFRESH_UI));

            Intent intent = new Intent(mContext, LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        LogsUtil.normal("requestCode=" + requestCode + ",resultCode=" + resultCode);

        setBackgroundAlpha((float) 1.0);


        if (resultCode == getActivity().RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {
//            Log.i(TAG, "相机, 开始裁剪");
                File picture = new File(Environment.getExternalStorageDirectory()
                        + "/Download/upload.png");
                startImageZoom(Uri.fromFile(picture));

            } else if (requestCode == GALLERY_REQUEST_CODE) {
                if (data == null) {
                    return;
                }
                Uri uri = data.getData();
                startImageZoom(uri);
            } else if (requestCode == CROP_REQUEST_CODE) {
                if (data == null) {
                    return;
                }
                Bundle extras = data.getExtras();
                Bitmap bm = extras.getParcelable("data");

                if (bm == null) {
                } else {
                    saveBitmap(bm);  //保存图片，可能需要

                    String path = Environment.getExternalStorageDirectory() + "/Download/upload.png";

                    showProcessDialog();
                    //上传
                    postUploadPictureApi(mUser.getmId(), mUser.getmToken(), path, Constant.UPLOAD_TYPE_USER_AVATOR, mContext);

                }
            } else if(requestCode == Constant.REQUESTCODE_FREIGHT){
                if(TextUtils.isEmpty(Constant.CURRENT_SHOP_DELIVERY)){
                    mine_delivery_tv.setText("当前金额:¥0");
                }else{
                    mine_delivery_tv.setText("当前金额:¥"+Constant.CURRENT_SHOP_DELIVERY);
                }
            }
        }
    }

    private void startImageZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            Log.i("TTSS", "startImageZoom..1");
            String url = getPath(mContext, uri);
            intent.setDataAndType(Uri.fromFile(new File(url)), "image/*");
        } else {
            Log.i("TTSS", "startImageZoom..2");
            intent.setDataAndType(uri, "image/*");
        }
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_REQUEST_CODE);
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    //以下是关键，原本uri返回的是file:///...来着的，android4.4返回的是content:///...
    @SuppressLint("NewApi")
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    private void saveBitmap(Bitmap bmp) {
        File tmpDir = new File(Environment.getExternalStorageDirectory() + "/Download");
        if (!tmpDir.exists()) {
            tmpDir.mkdir();
        }
        File img = new File(tmpDir.getAbsolutePath() + "/upload.png");
        try {
            FileOutputStream fos = new FileOutputStream(img);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);

        LogsUtil.normal("result="+result+",type="+type);

//        closeProcessDialog();
        Gson gson = new Gson();

        try {
            if (type == Constant.API_GET_USER_INFO_SUCCESS) {
                userInfo = gson.fromJson(result, UserInfo.class);
                if (userInfo != null) {
                    updateView(userInfo.getData());
                }
            }

            if (type == Constant.API_POST_UPLOAD_AVATAR_SUCCESS) {
                LogsUtil.normal("头像上传成功:" + result);

                ReasultBean data = gson.fromJson(result, ReasultBean.class);
                if (data != null && data.getCode().equals("000")) {

                    ToastUtil.DisplayToast(mContext, data.getData());
                }
            }

            if (type == Constant.API_POST_UPLOAD_PICTURE_SUCCESS) {
                LogsUtil.normal("图片上传成功:" + result);
                closeProcessDialog();

                ReasultBean data = gson.fromJson(result, ReasultBean.class);
                if (data != null && data.getCode().equals("000")) {
                    Constant.CURRENT_AVATAR = data.getData();
                    ImageLoader.getInstance().displayImage(data.getData(), mine_avator_iv, ImageLoaderUtil.mAvatarIconLoaderOptions);;

                    EventBus.getDefault().post(new EventBean(EventBean.EVENT_UPDATE_AVATAR));

                    postUploadAvatorApi(mUser.getmId(), mUser.getmToken(), data.getData());
                }
            }

            if (type == Constant.API_POST_UPDATA_VERSION_SUCCESS) {
                if (result != null) {
                    try {
                        UpdataVersionBean data = gson.fromJson(result, UpdataVersionBean.class);
                        //当前应用的版本号
                        PackageManager pm = getActivity().getPackageManager();
                        PackageInfo pInfo = pm.getPackageInfo(getActivity().getPackageName(), 0);
                        int version = pInfo.versionCode + 1;
                        String versionCode = version + "";
                        UpdateUtil manager = new UpdateUtil(mContext, versionCode, data.getData().getUrl(), data.getData().getComment(),data.getData().getMust());
                        //检测更新
                        manager.checkUpdate();
                    } catch (Exception e) {
                        ReasultBean data = gson.fromJson(result,ReasultBean.class);
                        if(data != null){
                            ToastUtil.DisplayToast(mContext,data.getData());
                        }
                        e.printStackTrace();
                    }
                }
            }

            //返回值code为000需要更新
            if (type == Constant.API_ANALYZE_FAILURE) {
                ReasultBean data = gson.fromJson(result,ReasultBean.class);
                if(data != null){
                    ToastUtil.DisplayToast(mContext,data.getData());
                }
            }

        } catch (JsonSyntaxException e) {
            ToastUtil.DisplayToastDebug(mContext,Constant.TOAST_MEG_REBACK_ERROR+"\n"+e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
