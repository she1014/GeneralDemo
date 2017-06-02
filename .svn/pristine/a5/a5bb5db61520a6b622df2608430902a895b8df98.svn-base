package com.techfly.demo.util;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.techfly.demo.R;
import com.techfly.demo.bean.EventBean;
import com.techfly.demo.service.UpdateService;

import java.io.File;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * 检查更新
 */

public class UpdateUtil {
    /* 下载中 */
    private static final int DOWNLOAD = 1;
    /* 下载结束 */
    private static final int DOWNLOAD_FINISH = 2;
    /* 直接安装 */
    private static final int DOWNLOAD_INSTALL = 3;

    private File savaFile;

    private String versionInfo = "";
    private static Context mContext;

    private String m_version = "";
    private String m_url = "";
    private Integer m_isMust = 0;//0-自愿；1-强制

    public UpdateUtil(Context context, String version, String url, String versionInfo, int isMust) {
        Log.i("TTLS", "UpdateUtil-->versionCode" + version + "url" + url);
        this.mContext = context;
        this.m_version = version;
        this.m_url = url;
        this.versionInfo = versionInfo;
        this.m_isMust = isMust;
    }

    /**
     * 检测软件更新
     */
    public void checkUpdate() {
        if (isUpdate()) {
            // 显示提示对话框
            showNoticeDialog();
        } else {
            return;
        }
    }

    /**
     * 检查软件是否有更新版本
     *
     * @return
     */
    public boolean isUpdate() {
        // 获取当前软件版本
        double cur_version = getCurVersion();
        double server_version = Double.parseDouble(m_version);
        Log.i("TEST", "cur_version=" + cur_version + ",m_version=" + m_version + ",server_version=" + server_version);
        if (cur_version >= server_version) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public int getCurVersion() {
        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
            int versionCodee = info.versionCode;
            return versionCodee;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 显示软件更新对话框
     */
    public void showNoticeDialog() {

        final AlertDialog dialog = new Builder(mContext, R.style.MyDialog).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);  //此处可以设置dialog显示的位置
        View view = View.inflate(mContext, R.layout.dialog_three_select, null);
        window.setContentView(view);

        Boolean isAlreadyDownload = false;//是否已下载

        String sd_path = Environment.getExternalStorageDirectory() + "/Download/" + FileUtils.getUrlFileName(m_url);
        if (FileUtils.fileIsExists(sd_path)) {
            savaFile = new File(sd_path);
            isAlreadyDownload = true;
        }

        TextView tv0 = (TextView) view.findViewById(R.id.title_tv);
        TextView tv1 = (TextView) view.findViewById(R.id.middle_tv);
        TextView tv2 = (TextView) view.findViewById(R.id.cancel);
        TextView tv3 = (TextView) view.findViewById(R.id.ok);
        TextView tv4 = (TextView) view.findViewById(R.id.web);

        RelativeLayout rl2 = (RelativeLayout) view.findViewById(R.id.cancel_rl);
        RelativeLayout rl4 = (RelativeLayout) view.findViewById(R.id.web_rl);

        String[] contentArray = versionInfo.split("#");
        String content = "";
        for (int i = 0; i < contentArray.length; i++) {
            if (i == contentArray.length - 1) {
                content = content + contentArray[i];
            } else {
                content = content + contentArray[i] + "\n";
            }
        }
        tv1.setGravity(Gravity.LEFT);
        tv1.setText(content);

        if (isAlreadyDownload) {
            tv0.setText(R.string.soft_update_already_download);
            tv2.setText(R.string.soft_wait_install);
            tv3.setText(R.string.soft_now_install);

            rl2.setVisibility(View.GONE);
            rl4.setVisibility(View.GONE);

            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);

        } else {

            tv0.setText(R.string.soft_update_title);
            tv3.setText(R.string.soft_update_updatebtn);

            if (m_isMust == 0) {
                tv2.setText(R.string.soft_update_later);
            } else if (m_isMust == 1) {
                tv2.setText(R.string.soft_update_exit);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setCancelable(false);
            }
        }

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (m_isMust == 0) {
                } else if (m_isMust == 1) {
                    EventBus.getDefault().post(new EventBean(EventBean.EVENT_EXIT_APP));
                }
            }
        });

        if (isAlreadyDownload) {
            tv3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    instanll(savaFile, mContext);
                }
            });
        } else {
            tv3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    // 显示下载对话框
                    if (!isServiceWork(mContext, "com.techfly.micromall.service.UpdateService")) {
                        ToastUtil.DisplayToast(mContext, "已添加下载任务,可下拉通知栏查看", Toast.LENGTH_LONG);
                        Intent intent = new Intent(mContext, UpdateService.class);
                        intent.putExtra("UPDATE_URL", m_url);
                        mContext.startService(intent);
                    }
                }
            });
        }

        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                Uri uri = Uri.parse(m_url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(intent);

            }
        });

    }

    //安装下载后的apk文件
    private void instanll(File file, Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    /*
     * 判断某个服务是否正在运行的方法
     */
    public boolean isServiceWork(Context mContext, String serviceName) {
        boolean isWork = false;
        ActivityManager myAM = (ActivityManager) mContext
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> myList = myAM.getRunningServices(20);
        if (myList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < myList.size(); i++) {
            String mName = myList.get(i).service.getClassName().toString();
            if (mName.equals(serviceName)) {
                isWork = true;
                break;
            }
        }
        return isWork;
    }

}
