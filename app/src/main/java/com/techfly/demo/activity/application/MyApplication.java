package com.techfly.demo.activity.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.OkHttpClientManager;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import cn.jpush.android.api.JPushInterface;

//import com.baidu.mapapi.BMapManager;

/**
 * Created by Administrator on 2015/7/22.
 */
public class MyApplication extends Application {

    public static boolean isTest = true;
    protected static MyApplication mInstance;
    public static SharedPreferences sf;

    public static boolean isLogin;//是否登录
    public static boolean first;//是否为第一次登录

//    public LocationService locationService;
//    public Vibrator mVibrator;
//    public final static String KEY = "";
//    private BMapManager bMapManager;

    public MyApplication() {
        mInstance = this;
    }

    public static MyApplication getApp() {
        if (mInstance != null && mInstance instanceof MyApplication) {
            return mInstance;
        } else {
            mInstance = new MyApplication();
            mInstance.onCreate();
            return mInstance;
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();

        sf = PreferenceManager.getDefaultSharedPreferences(this);
        first = sf.getBoolean(Constant.CONFIG_IS_LOGIN, true);

        initImageLoader();

        initJpush();

        initLocation();

        initBugly();

//        initHttps();//okHttp加载证书

        initTbs();
    }


    //获取应用的data/data/....File目录
    public String getFilesDirPath() {
        return getFilesDir().getAbsolutePath();
    }

    //获取应用的data/data/....Cache目录
    public String getCacheDirPath() {
        return getCacheDir().getAbsolutePath();
    }

    private void initJpush() {
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    private void initImageLoader() {

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this).threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(5 * 1024 * 1024))
                .memoryCacheSize(5 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .tasksProcessingOrder(QueueProcessingType.LIFO) // Not
                .build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);

    }

    private void initLocation() {
//        SDKInitializer.initialize(getApplicationContext());
    }


    private void initBugly() {
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        // 初始化Bugly
//        CrashReport.initCrashReport(context, "注册时申请的APPID", isDebug, strategy);
        CrashReport.initCrashReport(getApplicationContext(), "63905f142b", false);
    }


    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    private void initHttps(){
        try {
            OkHttpClientManager.getInstance().setCertificates(getAssets().open("srca.cer"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initTbs(){
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                LogsUtil.normal("onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
            }
        };

        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
                LogsUtil.normal("onDownloadFinish");
            }

            @Override
            public void onInstallFinish(int i) {
                LogsUtil.normal("onInstallFinish");
            }

            @Override
            public void onDownloadProgress(int i) {
                LogsUtil.normal("onDownloadProgress:" + i);
            }
        });
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    @Override
    public void onLowMemory() {
        // TODO Auto-generated method stub
        super.onLowMemory();
    }

}
