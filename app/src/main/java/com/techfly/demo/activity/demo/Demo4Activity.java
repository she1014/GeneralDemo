package com.techfly.demo.activity.demo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.bean.EventBean;
import com.techfly.demo.bean.LuckyInfoBean;
import com.techfly.demo.bean.LuckyResultBean;
import com.techfly.demo.bean.User;
import com.techfly.demo.selfview.luckypan.LuckyPan;
import com.techfly.demo.util.CommonUtils;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.SharePreferenceUtils;
import com.techfly.demo.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Created by wang on 2016/3/11.
 * 转账
 */

public class Demo4Activity extends BaseActivity {


    @InjectView(R.id.luckypan)
    LuckyPan luckypan;
    @InjectView(R.id.start_btn)
    ImageView start_btn;

    @InjectView(R.id.top_sv)
    ScrollView top_sv;
    @InjectView(R.id.bottom_linear)
    LinearLayout bottom_linear;


    @InjectView(R.id.pan_test_ll)
    LinearLayout pan_test_ll;
    @InjectView(R.id.lucky_pan_img)
    ImageView lucky_pan_img;
    //转盘指针
    @InjectView(R.id.lucky_pan_start_img)
    ImageView lucky_pan_start_img;
    @InjectView(R.id.lucky_name_tv)
    TextView lucky_name_tv;
    @InjectView(R.id.lucky_pan_rules_tv)
    TextView lucky_pan_rules_tv;

    ImageView panView;
    Button accl;
    Boolean is_can_start = true;
    String points;
    //private float[] angles = {0, 45, 90, 135, 180, 225, 270, 315};// 8个选项的角度
    private float[] angles = {315, 270, 225, 180, 135, 90, 45, 0};// 8个选项的角度

    Map idToGoodsName = new HashMap();

    private Integer[] awardStr = new Integer[8];
    private float inStartPosition; // 开始转动的角度位置
    private Handler mHandler = new MainHandler();
    Thread tThread;
    Message message;
    String lucky_goods;

    private User mUser;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_demo4);
        //获取用户信息的方法
        ButterKnife.inject(this);
        setTranslucentStatus(R.color.main_bg);
        EventBus.getDefault().register(this);

        initBaseView();
        //设置标题
        setBaseTitle( "抽奖转盘", "切换");
        //返回按钮的点击事件
        initBackButton(R.id.top_back_iv);

        initView();
    }

    //点击"开始抽奖"按钮
    @OnClick(R.id.lucky_pan_start_img)
    public void startLuckyPan() {

        if (checkLogin(this)) {
            if (is_can_start) {

                EventBus.getDefault().post(new EventBean(EventBean.EVENT_LUCK_START_LUCK));
                // 请求网络访问
                //postLucyPanResultApi(mUser.getmId(), mUser.getmToken());
//                DialogUtil.showConfirmDialog(this, "确定消耗" + points + "进行抽奖?", EventBean.EVENT_CONFIRM_LUCKY);
//            is_can_start = false;
            } else {
                ToastUtil.DisplayToast(Demo4Activity.this, "正在旋转");
            }
        }
    }

    private void initView() {
        mUser = SharePreferenceUtils.getInstance(Demo4Activity.this).getUser();
//        getLucyInfoApi();
        EventBus.getDefault().post(new EventBean(EventBean.EVENT_LUCK_SHOW_BACKGROUD));
    }

    // 保持匀速状态
    private void accelerToUniform() {
        Animation operatingAnims = AnimationUtils.loadAnimation(
                getApplicationContext(), R.anim.lucky_pan);//读取动画参数
        LinearInterpolator lin = new LinearInterpolator();//旋转状态为匀速
        operatingAnims.setInterpolator(lin);
        if (operatingAnims != null) {
            pan_test_ll.startAnimation(operatingAnims);
        }
    }

    // Handler 用于接收从线程返回的消息并处理，作减速运动，指向相应的奖品
    public class MainHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            int amsg = msg.arg1;
            if (0 <= amsg && amsg <= 7) {
                float angle = angles[amsg]; //随机产生一个旋转角度
                toDeceleration(angle);//做减速运动，指向一个角度
            }
        }
    }

    //做减速运动，指向一个float型数，指向一个位置
    private void toDeceleration(float angle) {
        inStartPosition = 720 + angle;// 本次旋转的角度

        // 设置旋转参数
        RotateAnimation rotateAnimation = new RotateAnimation(0,
                inStartPosition, RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setDuration(4000);// 设置动画播放时间

        rotateAnimation.setFillAfter(true);//动画播放完，停留在最后一帧上

        rotateAnimation.setInterpolator(Demo4Activity.this,
                android.R.anim.decelerate_interpolator);// 设置减速

        rotateAnimation.setAnimationListener(alisen); //开始播放动画
        pan_test_ll.startAnimation(rotateAnimation);
    }


    // 动作监听，转盘切换旋转和结束状态时该做什么
    private Animation.AnimationListener alisen = new Animation.AnimationListener() {

        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override  //减速动画结束后
        public void onAnimationEnd(Animation animation) {
//            String name = awardStr[(int) (inStartPosition % 360 / 45)];//根据角度，得到指向所在位置的字符串
            is_can_start = true;
            Toast.makeText(Demo4Activity.this, lucky_goods, Toast.LENGTH_LONG).show();//将字符串显示出来
        }
    };

    public void onEventMainThread(EventBean bean) {

        if (bean.getAction().equals(EventBean.EVENT_CONFIRM_TRANSFER_ACCOUNT_INFO)) {
            setResult(RESULT_OK);
            finish();
        }

        if (bean.getAction().equals(EventBean.EVENT_CONFIRM_LUCKY)) {
            if (is_can_start) {
                // 请求网络访问
//                postLucyPanResultApi(mUser.getmId(), mUser.getmToken());
                EventBus.getDefault().post(new EventBean(EventBean.EVENT_LUCK_START_LUCK));
                is_can_start = false;
            } else {
                ToastUtil.DisplayToast(Demo4Activity.this, "正在旋转");
            }
        }

        if (bean.getAction().equals(EventBean.EVENT_LUCK_SHOW_BACKGROUD)) {
            String result = "{\"data\":{\"luck_draw_area\":[{\"id\":5,\"area_name\":\"5个积分\",\"goods_name\":\"5个积分\",\"code\":\"大转盘积分奖励\",\"points\":5.0},{\"id\":6,\"area_name\":\"100元现金红包\",\"goods_name\":\"100元现金红包\",\"code\":{},\"points\":{}},{\"id\":7,\"area_name\":\"1个积分\",\"goods_name\":\"1个积分\",\"code\":\"大转盘积分奖励\",\"points\":1.0},{\"id\":8,\"area_name\":\"30枚土鸡蛋\",\"goods_name\":\"30枚土鸡蛋\",\"code\":{},\"points\":{}},{\"id\":9,\"area_name\":\"10个积分\",\"goods_name\":\"10个积分\",\"code\":\"大转盘积分奖励\",\"points\":10.0},{\"id\":10,\"area_name\":\"5元现金红包\",\"goods_name\":\"5元现金红包\",\"code\":{},\"points\":{}},{\"id\":11,\"area_name\":\"1个积分\",\"goods_name\":\"1个积分\",\"code\":\"大转盘积分奖励\",\"points\":1.0},{\"id\":12,\"area_name\":\"88元坚果礼包\",\"goods_name\":\"88元坚果礼包\",\"code\":{},\"points\":{}}],\"win_person\":{\"datas\":[{\"nickname\":\"18205183644\",\"goods_name\":\"1个积分\",\"code\":\"大转盘积分奖励\",\"points\":1.0,\"mobile\":\"18205183644\"},{\"nickname\":\"诺诺\",\"goods_name\":\"1个积分\",\"code\":\"大转盘积分奖励\",\"points\":1.0,\"mobile\":\"18335945723\"},{\"nickname\":\"诺诺\",\"goods_name\":\"1个积分\",\"code\":\"大转盘积分奖励\",\"points\":1.0,\"mobile\":\"18335945723\"},{\"nickname\":\"诺诺\",\"goods_name\":\"1个积分\",\"code\":\"大转盘积分奖励\",\"points\":1.0,\"mobile\":\"18335945723\"},{\"nickname\":\"诺诺\",\"goods_name\":\"1个积分\",\"code\":\"大转盘积分奖励\",\"points\":1.0,\"mobile\":\"18335945723\"},{\"nickname\":\"诺诺\",\"goods_name\":\"1个积分\",\"code\":\"大转盘积分奖励\",\"points\":1.0,\"mobile\":\"18335945723\"},{\"nickname\":\"诺诺\",\"goods_name\":\"1个积分\",\"code\":\"大转盘积分奖励\",\"points\":1.0,\"mobile\":\"18335945723\"},{\"nickname\":\"18355365835\",\"goods_name\":\"1个积分\",\"code\":\"大转盘积分奖励\",\"points\":1.0,\"mobile\":\"18355365835\"},{\"nickname\":\"15588432450\",\"goods_name\":\"30枚土鸡蛋\",\"code\":{},\"points\":{},\"mobile\":\"15588432450\"},{\"nickname\":\"15588432450\",\"goods_name\":\"5个积分\",\"code\":\"大转盘积分奖励\",\"points\":5.0,\"mobile\":\"15588432450\"}],\"pageSize\":10,\"pageNumber\":1,\"totalRecord\":765,\"totalPage\":77},\"luck_draw_rule\":{\"img\":\"http:\\/\\/121.199.32.47:8088\\/fresh2\\/uploads\\/imgs\\/2016110314080429.png\",\"points\":5.0,\"info\":\"积分抽奖，奖品多多，最高可以获得价值100元的现金红包，赶快来参与吧！             1、每次抽奖耗费5积分，不限抽奖次数                        2、获得的商城积分会即时发放到您的商城积分里，可以用来兑换积分区试用商品！           3、实物奖品和现金红包中奖后，第一时间联系客服兑奖！4、我们的工作时间：周一至周六，早9点至晚5点，其余时间段中奖我们都会在上班后第一时间处理 ！                                                5、本活动最终解释权归火红商城所有！\"}},\"code\":\"000\"}";
            result = CommonUtils.removeBrace(result);
            if (result != null) {
                Gson gson = new Gson();
                try {
                    LuckyInfoBean luckyInfoBean = gson.fromJson(result, LuckyInfoBean.class);
                    updateView(luckyInfoBean);
                } catch (Exception e) {

                    ToastUtil.DisplayToastDebug(this
                            , Constant.TOAST_MEG_REBACK_ERROR + "\n" + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                ToastUtil.DisplayToast(this, Constant.TOAST_MEG_ANALYZE_ERROR);
            }
        }


        if (bean.getAction().equals(EventBean.EVENT_LUCK_START_LUCK)) {
            String result = "{\"data\":{\"area_id\":11,\"area_name\":\"1个积分\",\"goods_name\":\"1个积分\",\"points_id\":53,\"code\":\"大转盘积分奖励\",\"points\":1.0},\"code\":\"000\"}";
            result = CommonUtils.removeBrace(result);
            if (result != null) {
                Gson gson = new Gson();
                try {
                    accelerToUniform();//开始旋转，保持匀速
                    LuckyResultBean luckyInfoBean = gson.fromJson(result, LuckyResultBean.class);
                    int id = luckyInfoBean.getData().getArea_id();
                    lucky_goods = luckyInfoBean.getData().getGoods_name();
                    int stopId = -1;
                    int stopId1 = -1;

                    //根据奖品名称获取停止的位置
                    for (int j = 0; j < awardStr.length; j++) {
                        if (awardStr[j] == id) {
                            stopId = j;
                        }
                        LogsUtil.normal("位置" + j + "awardStr[j]" + awardStr[j] + "------" + "中奖的id" + id + idToGoodsName.get(id));
                    }
                    message = mHandler.obtainMessage();
                    message.arg1 = stopId;//从服务器中得到一个确定中奖的随机数
                    LogsUtil.normal("stopId" + stopId);

                    mHandler.sendMessageDelayed(message, 3000);//得到信息后发送消息给Handler
//                   toDeceleration(2);//做减速运动，指定一个停止的角度
                } catch (Exception e) {
                    ToastUtil.DisplayToastDebug(this
                            , Constant.TOAST_MEG_REBACK_ERROR + "\n" + e.getMessage());
                    e.printStackTrace();
                }
            } else {
                ToastUtil.DisplayToast(this, Constant.TOAST_MEG_ANALYZE_ERROR);
            }
        }


    }

    private void goToTransferAccount() {

//        postTransferAccountApi(mUser.getmId(), mUser.getmToken(), money, account, account);
    }

    @Override
    public void getResult(String result, int type) {

    }

    private void updateView(LuckyInfoBean luckyInfoBean) {

        LuckyInfoBean.DataEntity.LuckDrawRuleEntity lucky_pan_rules = luckyInfoBean.getData().getLuck_draw_rule();

        //奖品的id进行List存储,会根据此ID对应转盘停止的位置
        List<LuckyInfoBean.DataEntity.LuckDrawAreaEntity> lucky_draw_area = luckyInfoBean.getData().getLuck_draw_area();

        List<Integer> goods_id_list = new ArrayList<>();
        for (int i = 0; i < lucky_draw_area.size(); i++) {
            goods_id_list.add(lucky_draw_area.get(i).getId());
        }
        goods_id_list.toArray(awardStr);

//        //以奖品的id作为key 奖品名称做成values 进行存储
//        for (int i = 0; i < lucky_draw_area.size(); i++) {
//            idToGoodsName.put(lucky_draw_area.get(i).getId(), lucky_draw_area.get(i).getGoods_name());
//        }

        String pan_img_url = lucky_pan_rules.getImg();

        if (TextUtils.isEmpty(pan_img_url)) {
            pan_img_url = "";
            lucky_pan_start_img.setVisibility(View.INVISIBLE);
        }
        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder().showImageOnLoading(R.drawable.icon_defualt_null) //设置图片在下载期间显示的图片
                .showImageForEmptyUri(R.drawable.icon_defualt_null)//设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.icon_defualt_null) //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .displayer(new RoundedBitmapDisplayer(0))//是否设置为圆角，弧度为多少
                .build();//构建完成
        ImageLoader.getInstance().displayImage(pan_img_url, lucky_pan_img, options);

        List<LuckyInfoBean.DataEntity.WinPersonEntity.DatasEntity> datas = luckyInfoBean.getData().getWin_person().getDatas();

        //幸运转盘的中奖名单列表
        String lucky_number_info = "";

        for (int i = 0; i < datas.size(); i++) {
            String phone = datas.get(i).getMobile();
            String gName = datas.get(i).getGoods_name() + " ";
//            String code = datas.get(i).getCode() + " ";
//            Logger.t("Logger").d("phone" + phone + "phone.length()" + phone.length());
            if (phone.length() == 11) {
                phone = phone.substring(0, 3) + "**" + phone.substring(7, 11);
//                Logger.t("Logger").d("phone2" + phone);
            }

            lucky_number_info = lucky_number_info + phone + " " + gName;
        }
        //时间  人  奖品
        lucky_name_tv.setText(lucky_number_info);
        //参与活动消耗的积分
        points = lucky_pan_rules.getPoints();
        lucky_pan_rules_tv.setText(lucky_pan_rules.getInfo());
    }

    @OnClick(R.id.top_right_tv)
    public void change(){
        if(top_sv.getVisibility() == View.VISIBLE){
            top_sv.setVisibility(View.GONE);
            bottom_linear.setVisibility(View.VISIBLE);
        }else{
            top_sv.setVisibility(View.VISIBLE);
            bottom_linear.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.start_btn)
    public void startSecondLuck(){
        if (!luckypan.isRuning()) {
            luckypan.luckyStart(2);
            start_btn.setImageResource(R.drawable.icon_prize_stop);
        } else {
            if (!luckypan.isShoundEnd()) {
                luckypan.luckyEnd();
                start_btn.setImageResource(R.drawable.icon_prize_start);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
