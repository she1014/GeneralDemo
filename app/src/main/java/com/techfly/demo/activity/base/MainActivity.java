package com.techfly.demo.activity.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techfly.demo.R;
import com.techfly.demo.activity.demo.Demo10Activity;
import com.techfly.demo.activity.demo.Demo11Activity;
import com.techfly.demo.activity.demo.Demo12Activity;
import com.techfly.demo.activity.demo.Demo13Activity;
import com.techfly.demo.activity.demo.Demo14Activity;
import com.techfly.demo.activity.demo.Demo15Activity;
import com.techfly.demo.activity.demo.Demo16Activity;
import com.techfly.demo.activity.demo.Demo17Activity;
import com.techfly.demo.activity.demo.Demo18Activity;
import com.techfly.demo.activity.demo.Demo19Activity;
import com.techfly.demo.activity.demo.Demo1Activity;
import com.techfly.demo.activity.demo.Demo20Activity;
import com.techfly.demo.activity.demo.Demo21Activity;
import com.techfly.demo.activity.demo.Demo22Activity;
import com.techfly.demo.activity.demo.Demo23Activity;
import com.techfly.demo.activity.demo.Demo24Activity;
import com.techfly.demo.activity.demo.Demo25Activity;
import com.techfly.demo.activity.demo.Demo26Activity;
import com.techfly.demo.activity.demo.Demo27Activity;
import com.techfly.demo.activity.demo.Demo28Activity;
import com.techfly.demo.activity.demo.Demo29Activity;
import com.techfly.demo.activity.demo.Demo2Activity;
import com.techfly.demo.activity.demo.Demo30Activity;
import com.techfly.demo.activity.demo.Demo31Activity;
import com.techfly.demo.activity.demo.Demo32Activity;
import com.techfly.demo.activity.demo.Demo33Activity;
import com.techfly.demo.activity.demo.Demo34Activity;
import com.techfly.demo.activity.demo.Demo35Activity;
import com.techfly.demo.activity.demo.Demo3Activity;
import com.techfly.demo.activity.demo.Demo4Activity;
import com.techfly.demo.activity.demo.Demo5Activity;
import com.techfly.demo.activity.demo.Demo6Activity;
import com.techfly.demo.activity.demo.Demo7Activity;
import com.techfly.demo.activity.demo.Demo8Activity;
import com.techfly.demo.activity.demo.Demo9Activity;
import com.techfly.demo.activity.qq_demo.adapt.SlideAdapt;
import com.techfly.demo.activity.qq_demo.view.MyLineLayout;
import com.techfly.demo.activity.qq_demo.view.SlideLayout;
import com.techfly.demo.activity.refund.RefundOrderMenuActivity;
import com.techfly.demo.bean.EventBean;
import com.techfly.demo.bean.User;
import com.techfly.demo.fragment.IndexFragment;
import com.techfly.demo.fragment.MyFragment;
import com.techfly.demo.fragment.OrderFragment;
import com.techfly.demo.util.DialogUtil;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.NetWorkUtil;
import com.techfly.demo.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * 界面的父类
 */
public class MainActivity extends BaseFragmentActivity implements View.OnClickListener {

    @InjectView(R.id.main_slide_layout)
    SlideLayout main_slide_layout;
    @InjectView(R.id.layout_main_linear)
    MyLineLayout layout_main_linear;

    @InjectView(R.id.container)
    ViewPager mViewPager;

    @InjectView(R.id.rl_index)
    RelativeLayout rl_index;
    @InjectView(R.id.rl_group)
    RelativeLayout rl_group;
    @InjectView(R.id.rl_my)
    RelativeLayout rl_my;

    @InjectView(R.id.main_indexTv)
    TextView main_indexTv;
    @InjectView(R.id.main_groupTv)
    TextView main_groupTv;
    @InjectView(R.id.main_myTv)
    TextView main_myTv;

    @InjectView(R.id.main_indexIv)
    ImageView main_indexIv;
    @InjectView(R.id.main_groupIv)
    ImageView main_groupIv;
    @InjectView(R.id.main_myIv)
    ImageView main_myIv;


    @InjectView(R.id.slide_line)
    LinearLayout slide_line;
    @InjectView(R.id.slide_my_pic)
    ImageView slide_my_pic;
    @InjectView(R.id.slide_my_name)
    TextView slide_my_name;
    @InjectView(R.id.slide_my_qm)
    TextView slide_my_qm;
    @InjectView(R.id.slide_setting_tv)
    TextView slide_setting_tv;
    @InjectView(R.id.slide_list)
    ListView slide_list;

    public FragmentPagerAdapter mAdapter;
    public List<Fragment> mFragments = new ArrayList<Fragment>();

    private IndexFragment mTab01;
    private OrderFragment mTab02;
    private MyFragment mTab03;

    private long exitTime = 0;  //两次返回键退出计时
    private Boolean isJumpIndex = false;

    private User mUser;
    private String m_getIntentType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        setTranslucentStatus(R.color.main_bg);

        //禁止滑动删除
        setSwipeBackEnable(false);

        //注册EventBus
        EventBus.getDefault().register(this);
//20-28   30-42
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            layout_main_linear.getChildAt(0).setFitsSystemWindows(true);///[1]状态栏为灰色,非想要的效果
        }

        initBaseView();

        initView();

        initData();

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }

        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                setTabSelection(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //设置ViewPage预加载页面个数，最少为1
        mViewPager.setOffscreenPageLimit(3);
//        setTabSelection(0);

        if (NetWorkUtil.isNetWorkConnected(this)) {
        } else {
            DialogUtil.showNetWorkDialog(this);
        }

        loadIntent();

        initListener();

    }

    private void initData() {

        slide_list.setAdapter(new SlideAdapt(this));
        slide_list.setSelection(0);

        //添加这局,否则滑动报错
        layout_main_linear.setSlideLayout(main_slide_layout);

        slide_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                LogsUtil.normal("点击了,position="+position);

                Intent intent = new Intent();

                switch (position) {
                    case 0:
                        intent.setClass(MainActivity.this, Demo1Activity.class);
                        break;
                    case 1:
                        intent.setClass(MainActivity.this, Demo2Activity.class);
                        break;
                    case 2:
                        intent.setClass(MainActivity.this, Demo3Activity.class);
                        break;
                    case 3:
                        intent.setClass(MainActivity.this, Demo4Activity.class);
                        break;
                    case 4:
                        intent.setClass(MainActivity.this, Demo5Activity.class);
                        break;
                    case 5:
                        intent.setClass(MainActivity.this, Demo6Activity.class);
                        break;
                    case 6:
                        intent.setClass(MainActivity.this, Demo7Activity.class);
                        break;
                    case 7:
                        intent.setClass(MainActivity.this, Demo8Activity.class);
                        break;
                    case 8:
                        intent.setClass(MainActivity.this, Demo9Activity.class);
                        break;
                    case 9:
                        intent.setClass(MainActivity.this, Demo10Activity.class);
                        break;
                    case 10:
                        intent.setClass(MainActivity.this, Demo11Activity.class);
                        break;
                    case 11:
                        intent.setClass(MainActivity.this, Demo12Activity.class);
                        break;
                    case 12:
                        intent.setClass(MainActivity.this, Demo13Activity.class);
                        break;
                    case 13:
                        intent.setClass(MainActivity.this, Demo14Activity.class);
                        break;
                    case 14:
                        intent.setClass(MainActivity.this, Demo15Activity.class);
                        break;
                    case 15:
                        intent.setClass(MainActivity.this, Demo16Activity.class);
                        break;
                    case 16:
                        intent.setClass(MainActivity.this, Demo17Activity.class);
                        break;
                    case 17:
                        intent.setClass(MainActivity.this, Demo18Activity.class);
                        break;
                    case 18:
                        intent.setClass(MainActivity.this, Demo19Activity.class);
                        break;
                    case 19:
                        intent.setClass(MainActivity.this, Demo20Activity.class);
                        break;
                    case 20:
                        intent.setClass(MainActivity.this, Demo21Activity.class);
                        break;
                    case 21:
                        intent.setClass(MainActivity.this, Demo22Activity.class);
                        break;
                    case 22:
                        intent.setClass(MainActivity.this, Demo23Activity.class);
                        break;
                    case 23:
                        intent.setClass(MainActivity.this, Demo24Activity.class);
                        break;
                    case 24:
                        intent.setClass(MainActivity.this, Demo25Activity.class);
                        break;
                    case 25:
                        intent.setClass(MainActivity.this, Demo26Activity.class);
                        break;
                    case 26:
                        intent.setClass(MainActivity.this, Demo27Activity.class);
                        break;
                    case 27:
                        intent.setClass(MainActivity.this, Demo28Activity.class);
                        break;
                    case 28:
                        intent.setClass(MainActivity.this, Demo29Activity.class);
                        break;
                    case 29:
                        intent.setClass(MainActivity.this, Demo30Activity.class);
                        break;
                    case 30:
                        intent.setClass(MainActivity.this, Demo31Activity.class);
                        break;
                    case 31:
                        intent.setClass(MainActivity.this, Demo32Activity.class);
                        break;
                    case 32:
                        intent.setClass(MainActivity.this, Demo33Activity.class);
                        break;
                    case 33:
                        intent.setClass(MainActivity.this, Demo34Activity.class);
                        break;
                    case 34:
                        intent.setClass(MainActivity.this, Demo35Activity.class);
                        break;
                    default:
                        intent.setClass(MainActivity.this, Demo1Activity.class);
                        break;
                }
                startActivity(intent);
            }
        });

    }

    private void initListener() {
        main_slide_layout.setOnSlideListener(new SlideLayout.OnSlideListener() {
            @Override
            public void onSlide(View view, int left, float persent) {
//                mMainMyPic.setAlpha(1 - persent);
                LogsUtil.normal("persent=" + persent);
                EventBus.getDefault().post(new EventBean(EventBean.EVENT_MAIN_SLIDE_CHANGE, persent + ""));
            }
        });
    }

    private void loadIntent() {

        m_getIntentType = getIntent().getStringExtra(Constant.CONFIG_INTENT_TYPE);

        LogsUtil.normal("MainActivity.getIntentType=" + m_getIntentType);

        if (m_getIntentType != null) {
            if (m_getIntentType.equals(Constant.CONFIG_JUMP_TO_RECEPT)) {
                setTabSelection(1);
            } else if (m_getIntentType.equals(Constant.CONFIG_JUMP_TO_REFUND)) {
                Intent intent = new Intent(MainActivity.this, RefundOrderMenuActivity.class);
                startActivity(intent);
            }
        } else {
            setTabSelection(0);
        }
    }

    @OnClick(R.id.slide_my_pic)
    public void clickSlidePic() {
        ToastUtil.DisplayToast(this, "点击了头像?");
    }

    @OnClick(R.id.slide_my_name)
    public void clickSlideName() {
        ToastUtil.DisplayToast(this, "点击了姓名?");
    }

    @OnClick(R.id.slide_my_qm)
    public void clickSlideSign() {
        ToastUtil.DisplayToast(this, "点击了签名?");
    }

    @OnClick(R.id.slide_setting_tv)
    public void clickSlideSetting() {
        ToastUtil.DisplayToast(this, "点击了设置?");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_index:
                setTabSelection(0);
                break;
            case R.id.rl_group:
                setTabSelection(1);
                break;
            case R.id.rl_my:
                setTabSelection(2);
                break;
            default:
                break;
        }
    }

    private void setTabSelection(int index) {
        switch (index) {
            case 0:
                mViewPager.setCurrentItem(0, false);
                initBottomBtn();
                main_indexIv.setImageDrawable(getResources().getDrawable(R.drawable.icon_home_click));
                main_indexTv.setTextColor(getResources().getColor(R.color.text_font_red));
                break;
            case 1:
                mViewPager.setCurrentItem(1, false);
                initBottomBtn();
                main_groupIv.setImageDrawable(getResources().getDrawable(R.drawable.icon_order_click));
                main_groupTv.setTextColor(getResources().getColor(R.color.text_font_red));

                break;
            case 2:
                mViewPager.setCurrentItem(2, false);
                initBottomBtn();
                main_myIv.setImageDrawable(getResources().getDrawable(R.drawable.icon_me_click));
                main_myTv.setTextColor(getResources().getColor(R.color.text_font_red));
                break;
        }
    }

    private void initBottomBtn() {
        main_indexIv.setImageDrawable(getResources().getDrawable(R.drawable.icon_home));
        main_indexTv.setTextColor(getResources().getColor(R.color.gray));
        main_groupIv.setImageDrawable(getResources().getDrawable(R.drawable.icon_order));
        main_groupTv.setTextColor(getResources().getColor(R.color.gray));
        main_myIv.setImageDrawable(getResources().getDrawable(R.drawable.icon_me));
        main_myTv.setTextColor(getResources().getColor(R.color.gray));
    }

    private void initView() {

        rl_index.setOnClickListener(this);
        rl_group.setOnClickListener(this);
        rl_my.setOnClickListener(this);

        mTab01 = new IndexFragment();
        mTab02 = new OrderFragment();
        mTab03 = new MyFragment();

        mFragments.add(mTab01);
        mFragments.add(mTab02);
        mFragments.add(mTab03);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {

            if (isJumpIndex) {
                mViewPager.setCurrentItem(0, false);
                return true;
            }

            if ((System.currentTimeMillis() - exitTime) > 2000) {
                ToastUtil.DisplayToast(this, "再按一次退出！");
                exitTime = System.currentTimeMillis();
            } else {
                this.finish();
            }
            return true;
        }
        return true;
    }

    public void onEventMainThread(EventBean bean) {
        Log.i("TTSS", "onEventMainThread,Main,action=" + bean.getAction());
        if (bean.getAction().equals(EventBean.EVENT_EXIT_APP)) {
            this.finish();
        }
        if (bean.getAction().equals(EventBean.EVENT_JUMP_TO_ORDER)) {
            setTabSelection(1);
        }
        if (bean.getAction().equals(EventBean.EVENT_MAIN_SELECT_SLIDE)) {
            if (main_slide_layout.isMenuIsOpen()) {
                main_slide_layout.closeMenu();
            } else {
                main_slide_layout.openMenu();
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}
