package com.techfly.demo.activity.jpush;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import com.techfly.demo.R;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.activity.base.HtmlDetailActivity;
import com.techfly.demo.activity.base.MainActivity;
import com.techfly.demo.activity.refund.RefundOrderMenuActivity;
import com.techfly.demo.activity.user.LoginActivity;
import com.techfly.demo.bean.EventBean;
import com.techfly.demo.util.AppManager;
import com.techfly.demo.util.LogsUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.jpush.android.api.JPushInterface;
import de.greenrobot.event.EventBus;

/**
 * 自定义接收器
 * <p/>
 * 如果不定义这个 Receiver，则： 1) 默认用户会打开主界面 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "JPush";
    private static int NOTIFICATIONS_ID = 0x111;
    private NotificationManager nm;
    private Notification no;
    private Context mContext;

    public static boolean isShould = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;

        Bundle bundle = intent.getExtras();

        LogsUtil.normal("MyReceiver.action=" + intent.getAction());

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle
                    .getString(JPushInterface.EXTRA_REGISTRATION_ID);
//            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent
                .getAction())) {
           /* Log.d(TAG,
                    "[MyReceiver] 接收到推送下来的自定义消息: "
                            + bundle.getString(JPushInterface.EXTRA_MESSAGE));*/
            // processCustomMessage(context, bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent
                .getAction())) {
//            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");
            int notifactionId = bundle
                    .getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            JPushInterface.clearNotificationById(context, notifactionId);
//            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
            // notifiy(bundle.getString(JPushInterface.EXTRA_MESSAGE));
            notifiy(bundle);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent
                .getAction())) {

            LogsUtil.normal("[MyReceiver] 用户点击打开了通知");

            // 打开自定义的Activity
            /*Intent i = new Intent(context, DeliverDetailActivity.class);
            i.putExtras(bundle);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(i);*/

        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent
                .getAction())) {
            /*Log.d(TAG,
                    "[MyReceiver] 用户收到到RICH PUSH CALLBACK: "
                            + bundle.getString(JPushInterface.EXTRA_EXTRA));*/
            // 在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity，
            // 打开一个网页等..

        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent
                .getAction())) {
            boolean connected = intent.getBooleanExtra(
                    JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            /*Log.w(TAG, "[MyReceiver]" + intent.getAction()
                    + " connected state change to " + connected);*/
        } else if (Constant.CONFIG_BROADCAST_NOTIFICATION.equals(intent.getAction())) {
            LogsUtil.normal("取消全部效果");
            NotificationManager notificationManager = (NotificationManager) mContext
                    .getSystemService(mContext.NOTIFICATION_SERVICE);
            notificationManager.cancel(NOTIFICATIONS_ID);
        } else {
            Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
        }
    }

    @SuppressLint("SimpleDateFormat")
    private void notifiy(Bundle bundle) {
        if (bundle != null) {

            int m_type = 0;//type=1-接单；type=2-退款

            String title = bundle.getString(JPushInterface.EXTRA_TITLE);
            String content = bundle.getString(JPushInterface.EXTRA_ALERT);

            if (content.contains("配送消息")) {
                m_type = 1;
            } else if (content.contains("退货退款")) {
                m_type = 2;
            } else {
                m_type = 3;
            }

            LogsUtil.normal("推送:title=" + title + ",content=" + content + ",m_type=" + m_type);

            no = new Notification(R.drawable.ic_launcher, content,
                    System.currentTimeMillis());

            if (m_type == 1) {//接单
                no.sound = Uri.parse("android.resource://"
                        + mContext.getPackageName() + "/" + R.raw.sound1);
            } else if (m_type == 2) {//退款
                no.sound = Uri.parse("android.resource://"
                        + mContext.getPackageName() + "/" + R.raw.sound2);
            } else if (m_type == 3){ //平台公告
                no.defaults=Notification.DEFAULT_SOUND;
//                nm.notify(1, no);
            }

            if (!isActivityShow()) {  //APP未运行

                Intent intent = new Intent(mContext, LoginActivity.class);
                if (m_type == 1) {      //登录-MainActivity-接单
                    intent.putExtra(Constant.CONFIG_INTENT_TYPE, Constant.CONFIG_JUMP_TO_RECEPT);
                } else if (m_type == 2) {//登录-MainActivity-RefundOrderMenuActivity
                    intent.putExtra(Constant.CONFIG_INTENT_TYPE, Constant.CONFIG_JUMP_TO_REFUND);
                } else if (m_type == 3){
                    intent.putExtra(Constant.CONFIG_INTENT_TYPE, Constant.CONFIG_JUMP_TO_PUBLIC);
                    intent.putExtra(Constant.CONFIG_INTENT_CONTENT, content);
                }
                PendingIntent contentIntent = PendingIntent.getActivity(
                        mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                no.contentIntent = contentIntent;

            } else {                //APP运行中
                switch (m_type) {
                    case 1://接单类型
                        if (AppManager.getAppManager().currentActivity() instanceof MainActivity) {
                            LogsUtil.normal("getAppManager...1");
                            EventBus.getDefault().post(new EventBean(EventBean.EVENT_JUMP_TO_ORDER));
                        } else {
                            LogsUtil.normal("getAppManager...2");
                            AppManager.getAppManager().finishActivity();

                            Intent intent = new Intent(mContext, MainActivity.class);
                            intent.putExtra(Constant.CONFIG_INTENT_TYPE, Constant.CONFIG_JUMP_TO_RECEPT);
                            PendingIntent contentIntent = PendingIntent.getActivity(
                                    mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                            no.contentIntent = contentIntent;

                            EventBus.getDefault().post(new EventBean(EventBean.EVENT_JUMP_TO_ORDER));
                        }
                        break;
                    case 2://退单类型
                        if (AppManager.getAppManager().currentActivity() instanceof MainActivity) {
                            LogsUtil.normal("getAppManager...3");

                            Intent intent = new Intent(mContext, RefundOrderMenuActivity.class);
                            intent.putExtra(Constant.CONFIG_INTENT_TYPE, Constant.CONFIG_JUMP_TO_REFUND);
                            PendingIntent contentIntent = PendingIntent.getActivity(
                                    mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                            no.contentIntent = contentIntent;

                        } else {
                            LogsUtil.normal("getAppManager...4");

                            AppManager.getAppManager().finishActivity();

                            Intent intent = new Intent(mContext, RefundOrderMenuActivity.class);
                            intent.putExtra(Constant.CONFIG_INTENT_TYPE, Constant.CONFIG_JUMP_TO_REFUND);
                            PendingIntent contentIntent = PendingIntent.getActivity(
                                    mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                            no.contentIntent = contentIntent;
                        }
                        break;
                    case 3: //平台推送

                        if (AppManager.getAppManager().currentActivity() instanceof MainActivity) {
                            LogsUtil.normal("getAppManager...5");

                            Intent intent = new Intent(mContext, HtmlDetailActivity.class);
                            intent.putExtra(Constant.CONFIG_INTENT_TITLE, "平台公告");
                            intent.putExtra(Constant.CONFIG_INTENT_CONTENT, content);
                            PendingIntent contentIntent = PendingIntent.getActivity(
                                    mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                            no.contentIntent = contentIntent;

                        } else {
                            LogsUtil.normal("getAppManager...6");

                            AppManager.getAppManager().finishActivity();

                            Intent intent = new Intent(mContext, HtmlDetailActivity.class);
                            intent.putExtra(Constant.CONFIG_INTENT_TITLE, "平台公告");
                            intent.putExtra(Constant.CONFIG_INTENT_CONTENT, content);
                            PendingIntent contentIntent = PendingIntent.getActivity(
                                    mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                            no.contentIntent = contentIntent;
                        }

                        break;
                }
            }

            no.contentView = new RemoteViews(mContext.getPackageName(),
                    R.layout.layout_jpush_notification);

            no.contentView.setTextViewText(R.id.notification_title,
                    bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE));
            SimpleDateFormat df = new SimpleDateFormat("HH:mm");
            Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
            no.contentView.setTextViewText(R.id.notification_time,
                    df.format(curDate));
            no.contentView.setTextViewText(R.id.notification_content, content);

            if(m_type == 3){
                no.defaults=Notification.DEFAULT_SOUND;
            }else{
                no.defaults = Notification.DEFAULT_LIGHTS
                        | Notification.DEFAULT_VIBRATE;
            }

            no.flags = Notification.FLAG_INSISTENT | Notification.FLAG_AUTO_CANCEL;
//					| Notification.FLAG_INSISTENT;
            nm = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            nm.cancel(NOTIFICATIONS_ID);
            nm.notify(NOTIFICATIONS_ID, no);
        }
    }

    private boolean isActivityShow() {
        if (AppManager.getAppManager().getActivityCount() <= 0) {
            return false;
        }
        return true;
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        for (String key : bundle.keySet()) {
            if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
            } else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
                sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
            } else {
                sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
            }
        }
        return sb.toString();
    }


}
