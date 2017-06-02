package com.techfly.demo.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.RemoteViews;

import com.techfly.demo.R;
import com.techfly.demo.util.FileUtils;
import com.techfly.demo.util.LogsUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * 下载+通知栏更新
 */

public class UpdateService extends Service {

    // 返回的安装包url
    private String apkUrl = "";
    /* 下载包安装路径 */
    private String saveBasePath = Environment.getExternalStorageDirectory()+"/Download/";  //  /sdcard/updateApkDemo/
    private String saveFilePath = "";  //  test.apk

    private static final int NOTIFY_ID = 0;
    private int progress;
    private boolean canceled;   //是否手动取消

    private Context mContext = this;
    private Notification mNotification;
    private NotificationManager mNotificationManager;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    // 下载完毕
                    LogsUtil.normal("下载完毕,0");
                    mNotificationManager.cancel(NOTIFY_ID);
                    installApk();
                    //停止掉当前的服务
                    stopSelf();
                    break;
                case 1:
                    //更新进度
                    int rate = msg.arg1;
                    if (rate < 100) {
                        RemoteViews contentview = mNotification.contentView;
                        contentview.setTextViewText(R.id.tv_progress, rate + "%");
                        contentview.setProgressBar(R.id.progressbar, 100, rate, false);
                    } /*else {
                        // 下载完毕后变换通知形式
                        mNotification.flags = Notification.FLAG_AUTO_CANCEL;
                        mNotification.contentView = null;
                        stopSelf();// 停掉服务自身
                    }*/
                    mNotificationManager.notify(NOTIFY_ID, mNotification);
                    break;
                case 2:
                    // 这里是用户界面手动取消，所以会经过activity的onDestroy();方法
                    mNotificationManager.cancel(NOTIFY_ID);
                    break;
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        LogsUtil.normal("onStartCommand");

        apkUrl = intent.getStringExtra("UPDATE_URL");
        saveFilePath = saveBasePath + FileUtils.getUrlFileName(apkUrl);

        setUpNotification();

        startDownload();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        LogsUtil.normal("是否执行了 onBind");
        return null;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        LogsUtil.normal("downloadservice ondestroy");
        // 假如被销毁了，无论如何都默认取消了。
    }

    @Override
    public boolean onUnbind(Intent intent) {
        // TODO Auto-generated method stub
        LogsUtil.normal("downloadservice onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        // TODO Auto-generated method stub
        super.onRebind(intent);
        LogsUtil.normal("downloadservice onRebind");
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        LogsUtil.normal("downloadservice.onCreate");
    }

    private void startDownload() {
        // TODO Auto-generated method stub
        new Thread() {
            public void run() {
                // 下载
                canceled = false;
                downloadApk();
            };
        }.start();
    }

    /**
     * 创建通知
     */
    private void setUpNotification() {

        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        mNotification = new Notification(R.drawable.ic_launcher, "开始下载", System.currentTimeMillis());
        // 放置在"正在运行"栏目中
        mNotification.flags = Notification.FLAG_ONGOING_EVENT;

        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.layout_update_service);
        contentView.setTextViewText(R.id.name, getString(R.string.app_name)+"更新");
        // 指定个性化视图
        mNotification.contentView = contentView;

        mNotification.contentIntent = PendingIntent.getActivity(this,0,new Intent(this,UpdateService.class),0);

        mNotificationManager.notify(NOTIFY_ID, mNotification);
    }

    /**
     * 下载apk
     * @param url
     */
    private Thread downLoadThread;

    private void downloadApk() {
        downLoadThread = new Thread(mdownApkRunnable);
        downLoadThread.start();
    }

    //安装下载后的apk文件
    private void installApk() {
        File apkfile = new File(saveFilePath);
        if (!apkfile.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(apkfile), "application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }

    private int lastRate = 0;
    private Runnable mdownApkRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                URL url = new URL(apkUrl);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                int length = conn.getContentLength();
                InputStream is = conn.getInputStream();

                File file = new File(saveBasePath);
                if (!file.exists()) {
                    file.mkdirs();
                }
                String apkFile = saveFilePath;
                File ApkFile = new File(apkFile);
                FileOutputStream fos = new FileOutputStream(ApkFile);

                int count = 0;
                byte buf[] = new byte[1024];

                do {
                    int numread = is.read(buf);
                    count += numread;
                    progress = (int) (((float) count / length) * 100);

//                    LogsUtil.normal("progress="+progress);

                    // 更新进度
                    Message msg = mHandler.obtainMessage();
                    msg.what = 1;
                    msg.arg1 = progress;
                    if (progress >= lastRate + 1) {
                        mHandler.sendMessage(msg);
                        lastRate = progress;
                    }
                    if (numread <= 0) {
                        // 下载完成通知安装
                        mHandler.sendEmptyMessage(0);
                        // 下载完了，cancelled也要设置
                        canceled = true;
                        break;
                    }
                    fos.write(buf, 0, numread);
                } while (!canceled);// 点击取消就停止下载.

                fos.close();
                is.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };

}
