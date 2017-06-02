/**
 *
 */
package com.techfly.demo.util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techfly.demo.R;
import com.techfly.demo.activity.user.LoginActivity;
import com.techfly.demo.bean.EventBean;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.greenrobot.event.EventBus;


/**
 * 功能描述：  得到自定义的进度条
 */
public class DialogUtil {

    public static Dialog createLoadingDialog(Context context, String msg) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
        // main.xml中的ImageView
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
        TextView tipTextView = (TextView) v.findViewById(R.id.text);// 提示文字


        // 加载动画
//        spaceshipImage.setBackgroundResource(R.drawable.dialog_animations);
//        final AnimationDrawable animationDrawable=(AnimationDrawable)spaceshipImage.getBackground();
//        animationDrawable.start();
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                context, R.anim.dialog_animation);
        LinearInterpolator lir = new LinearInterpolator();
        hyperspaceJumpAnimation.setInterpolator(lir);
        // 使用ImageView显示动画
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);
        tipTextView.setText(msg);// 设置加载信息

        Dialog loadingDialog = new Dialog(context, R.style.loading_dialogs);// 创建自定义样式dialog

        loadingDialog.setCancelable(false);// 不可以用“返回键”取消
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        loadingDialog.setOnKeyListener(new OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss();
                    HttpUtils.cancel();
//                    animationDrawable.stop();
                    //此处把dialog dismiss掉，然后把本身的activity finish掉
                    //   BarcodeActivity.this.finish();
                    return true;
                } else {
                    return false;
                }
            }
        });
        return loadingDialog;


    }

    public static Dialog loadingDialog(Context context) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_base_loading, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局

        Dialog loadingDialog = new Dialog(context, R.style.loading_dialogs);// 创建自定义样式dialog

//        loadingDialog.setCancelable(false);// 不可以用“返回键”取消
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));// 设置布局
        loadingDialog.setOnKeyListener(new OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss();
                    HttpUtils.cancel();
//                    animationDrawable.stop();
                    //此处把dialog dismiss掉，然后把本身的activity finish掉
                    //   BarcodeActivity.this.finish();
                    return true;
                } else {
                    return false;
                }
            }
        });
        return loadingDialog;
    }

    /*
     * 网络连接提示
	 */
    public static void showNetWorkDialog(final Context context) {
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

        tv0.setText("网络提示");
        tv1.setText("当前网络未开启! 是否设置网络?");
        tv2.setText("取消");
        tv3.setText("去设置");

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
//                context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                Intent intent = null;
                //判断手机系统的版本  即API大于10 就是3.0或以上版本
                if (android.os.Build.VERSION.SDK_INT > 10) {
//                    intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                    intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
//                    intent = new Intent("android.settings.WIFI_SETTINGS");
                } else {
                    intent = new Intent();
                    ComponentName component = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                context.startActivity(intent);

            }
        });
    }

    /*
     * 显示登陆提示框【注：跳转时记得结束当前活动Activity】
     */
    public static void showLoginDialog(final Context context) {
        final AlertDialog dialog = new AlertDialog.Builder(context,R.style.MyDialog).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);  //此处可以设置dialog显示的位置
//        window.setWindowAnimations(R.style.toastdig);  //添加动画
        View view = View.inflate(context, R.layout.dialog_middle_login, null);
        window.setContentView(view);

        TextView tv1 = (TextView) view.findViewById(R.id.middle_tv);
        TextView tv2 = (TextView) view.findViewById(R.id.cancel);
        TextView tv3 = (TextView) view.findViewById(R.id.ok);

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
                Intent intent = new Intent(context, LoginActivity.class);
//                intent.putExtra(Constant.CONFIG_COMEFROM_OTHER,true);
                context.startActivity(intent);
            }
        });
    }

    public static void showWaitDialog(final Context context){
        final AlertDialog dialog = new AlertDialog.Builder(context,R.style.MyDialog).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);  //此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.toastdig);  //添加动画
        View view=View.inflate(context, R.layout.dialog_middle_wait, null);
        window.setContentView(view);

        TextView tv3 = (TextView)view.findViewById(R.id.ok);

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    /*
     * 拨号提示框
     */
    public static void showDialDialog(final Context context) {
        final AlertDialog dialog = new AlertDialog.Builder(context,R.style.MyDialog).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);  //此处可以设置dialog显示的位置
//        window.setWindowAnimations(R.style.toastdig);  //添加动画
        View view = View.inflate(context, R.layout.dialog_dial, null);
        window.setContentView(view);

        final TextView tv1 = (TextView) view.findViewById(R.id.middle_tv);
        TextView tv2 = (TextView) view.findViewById(R.id.cancel);
        TextView tv3 = (TextView) view.findViewById(R.id.ok);

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + context.getResources().getString(R.string.common_phone_dial_num)));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    /*
     * 二次确认Dialog，防止用户误删
     */
    public static void showConfirmDialog(final Context context, String title, String content) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);  //此处可以设置dialog显示的位置
//        window.setWindowAnimations(R.style.toastdig);  //添加动画
        View view = View.inflate(context, R.layout.dialog_middle_login, null);
        window.setContentView(view);

        TextView tv0 = (TextView) view.findViewById(R.id.title_tv);
        TextView tv1 = (TextView) view.findViewById(R.id.middle_tv);
        TextView tv2 = (TextView) view.findViewById(R.id.cancel);
        TextView tv3 = (TextView) view.findViewById(R.id.ok);
        tv0.setText(title);
        tv1.setText(content);

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                EventBus.getDefault().post(new EventBean(EventBean.EVENT_CONFIRM_DELETE));
            }
        });

    }

    /*
     * 使用相机提示
     */
    public static void showCamareDialog(final Context context) {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);  //此处可以设置dialog显示的位置
//        window.setWindowAnimations(R.style.toastdig);  //添加动画
        View view = View.inflate(context, R.layout.dialog_middle_login, null);
        window.setContentView(view);

        TextView tv0 = (TextView) view.findViewById(R.id.title_tv);
        TextView tv1 = (TextView) view.findViewById(R.id.middle_tv);
        TextView tv2 = (TextView) view.findViewById(R.id.cancel);
        TextView tv3 = (TextView) view.findViewById(R.id.ok);

        tv0.setText("提 示");
        tv1.setGravity(Gravity.CENTER);
        tv1.setText("选择相机后无需再手动录入数据，请保证相片质量！\n提交时不要忘记填写检查时间和年龄，谢谢合作！");
        tv2.setText("手动录入");
        tv3.setText("知道了");

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                EventBus.getDefault().post(new EventBean(EventBean.EVENT_OPEN_CAMERE));
            }
        });

    }

    /*
    * 二次确认Dialog，防止用户误删
    */
    public static void showDeleteDialog(final Context context, String title, String content, final String eventbus,final String id) {
        final AlertDialog dialog = new AlertDialog.Builder(context, R.style.MyDialog).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);  //此处可以设置dialog显示的位置
//        window.setWindowAnimations(R.style.toastdig);  //添加动画
        View view = View.inflate(context, R.layout.dialog_middle_login, null);
        window.setContentView(view);

        TextView tv0 = (TextView) view.findViewById(R.id.title_tv);
        TextView tv1 = (TextView) view.findViewById(R.id.middle_tv);
        TextView tv2 = (TextView) view.findViewById(R.id.cancel);
        TextView tv3 = (TextView) view.findViewById(R.id.ok);

        tv0.setText(title);
        tv1.setText(content);
        tv2.setText("取消");
        tv3.setText("确定");

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBean(eventbus,id));
                dialog.dismiss();
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

    }

    /*
    * 二次确认Dialog，防止用户误删
    */
    public static void showSelectDialog(final Context context, String title, String content,final String id) {
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

        tv0.setText(title);

        tv1.setText(content);
        tv1.setGravity(Gravity.CENTER);

        tv2.setText("拒单");
        tv2.setTextColor(context.getResources().getColor(R.color.text_font_red));

        tv3.setText("接单");

        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBean(EventBean.CONFIRM_ORDER_ACCEPT,id));
                dialog.dismiss();
            }
        });

        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBean(EventBean.CONFIRM_ORDER_REJECT,id));
                dialog.dismiss();
            }
        });

    }

    public static void showDeleteDialog(final Context context) {
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("确定要删除此地址?")
//                .setContentText("Won't be able to recover this file!")
                .setCancelText("取消")
                .setConfirmText("确认")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {

                        EventBus.getDefault().post(new EventBean(EventBean.EVENT_CANCEL_DELETE));

                        // reuse previous dialog instance, keep widget user state, reset them if you need
                        sDialog.setTitleText("取消成功!")
//                                .setContentText("Your imaginary file is safe :)")
                                .setConfirmText("OK")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {

                        EventBus.getDefault().post(new EventBean(EventBean.EVENT_CONFIRM_DELETE));

                        sDialog.setTitleText("删除成功!")
//                                .setContentText("Your imaginary file has been deleted!")
                                .setConfirmText("OK")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                }).show();
    }


    //退出登录
    public static void exitAccountDialog(final Context context,String phone) {
        new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("确定退出账号?")
                .setContentText("当前账号:" + phone)
                .setCancelText("取消")
                .setConfirmText("确认")
                .showCancelButton(true)
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {

                        // reuse previous dialog instance, keep widget user state, reset them if you need
                        sDialog.setTitleText("取消成功!")
//                                .setContentText("Your imaginary file is safe :)")
                                .setConfirmText("OK")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                    }
                })
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {

                        EventBus.getDefault().post(new EventBean(EventBean.EVENT_EXIT_ACCOUNT));

                        sDialog.setTitleText("退出成功!")
//                                .setContentText("Your imaginary file has been deleted!")
                                .setConfirmText("OK")
                                .showCancelButton(false)
                                .setCancelClickListener(null)
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                }).show();
    }

    public static void showSuccessDialog(final Context context, final String content) {
        new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(content)
//                .setContentText("You clicked the button!")
                .show();
    }

    public static void showSuccessDialog(final Context context, final String content, final String eventbus) {
        final SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
        dialog.setTitleText(content);
        dialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    EventBus.getDefault().post(new EventBean(eventbus));
                    dialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    public static void showRechargeSuccessDialog(final Context context, final String content, final String eventbus) {

        final SweetAlertDialog dialog = new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE);
        dialog.setTitleText("充值提示");
        dialog.setContentText(content);
        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                dialog.dismiss();
                EventBus.getDefault().post(new EventBean(eventbus));
            }
        });
        dialog.show();
        /*new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("充值提示")
                .setContentText(content)
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        EventBus.getDefault().post(new EventBean(eventbus));
                    }
                })
                .show();*/
    }


    public static void showTipDialog(final Context context, final String content) {
        new SweetAlertDialog(context, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
                .setTitleText(content)
//                .setContentText("You clicked the button!")
                .show();
    }

    public static void showFailureDialog(final Context context, final String content) {
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(content)
//                .setContentText("You clicked the button!")
                .show();
    }


    public static void showRechargeFailureDialog(final Context context, final String content) {
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("充值提示")
                .setContentText(content)
                .show();
    }


    /*
     * 确认提示
     */
    /*public static void showRechargeSuccessDialog(final Context context,String message,final String eventbus) {
        new AlertDeleteDialog(context).builder().setTitle("提 示")
                .setMsg(message)
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventBus.getDefault().post(new EventBean(eventbus));
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }*/

}
