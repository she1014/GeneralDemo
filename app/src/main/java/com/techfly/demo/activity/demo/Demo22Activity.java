package com.techfly.demo.activity.demo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.adpter.IndexGridScoreAdapter;
import com.techfly.demo.bean.ScoreRankBean;
import com.techfly.demo.interfaces.ItemClickListener;
import com.techfly.demo.selfview.FlexibileScrollView;
import com.techfly.demo.selfview.GlideCircleTransform;
import com.techfly.demo.selfview.GlideRoundTransform;
import com.techfly.demo.selfview.MyHorizontalScrollView;
import com.techfly.demo.util.DensityUtil;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/*
 * Scrollview+图片横向滑动
 */
public class Demo22Activity extends BaseActivity {

    @InjectView(R.id.demo_flv)
    FlexibileScrollView demo_flv;

    @InjectView(R.id.demo_bg_iv)
    ImageView demo_bg_iv;

    @InjectView(R.id.container_linear1)
    LinearLayout container_linear1;
    @InjectView(R.id.demo_hv1)
    MyHorizontalScrollView demo_hv1;

    @InjectView(R.id.demo_gv)
    GridView demo_gv;

    @InjectView(R.id.demo_top_linear)
    LinearLayout demo_top_linear;
    @InjectView(R.id.demo_top_left_linear)
    LinearLayout demo_top_left_linear;
    @InjectView(R.id.demo_top_right_linear)
    LinearLayout demo_top_right_linear;
    @InjectView(R.id.demo_top_right_tv)
    TextView demo_top_right_tv;

    @InjectView(R.id.container_linear2)
    LinearLayout container_linear2;
    @InjectView(R.id.demo_hv2)
    MyHorizontalScrollView demo_hv2;

    private IndexGridScoreAdapter indexGridAdapter2;
    private List<ScoreRankBean.DataEntity> rankBeanList2 = new ArrayList<ScoreRankBean.DataEntity>();

    private String m_content = "{\"state\":1,\"data\":[{\"uid\":\"120\",\"username\":\"13681863492\",\"score\":\"7150\",\"avatar\":\"http://img.popoho.com/allimg/151223/0USTB7-0.jpg\",\"name\":\"tongtaste1\",\"fansnum\":\"7\",\"watched\":0},{\"uid\":\"120\",\"username\":\"13681863492\",\"score\":\"7150\",\"avatar\":\"http://img.popoho.com/allimg/151223/0USW239-2.jpg\",\"name\":\"tongtaste2\",\"fansnum\":\"7\",\"watched\":0},{\"uid\":\"120\",\"username\":\"13681863492\",\"score\":\"7150\",\"avatar\":\"http://img.popoho.com/allimg/151223/0USU557-4.png\",\"name\":\"tongtaste3\",\"fansnum\":\"7\",\"watched\":0},{\"uid\":\"120\",\"username\":\"13681863492\",\"score\":\"7150\",\"avatar\":\"http://img.popoho.com/allimg/151223/0USVH7-6.jpg\",\"name\":\"tongtaste4\",\"fansnum\":\"7\",\"watched\":0},{\"uid\":\"120\",\"username\":\"13681863492\",\"score\":\"7150\",\"avatar\":\"http://img.popoho.com/allimg/151223/0USQ545-8.jpg\",\"name\":\"tongtaste5\",\"fansnum\":\"7\",\"watched\":0},{\"uid\":\"120\",\"username\":\"13681863492\",\"score\":\"7150\",\"avatar\":\"http://img.popoho.com/allimg/151223/0USW491-10.jpg\",\"name\":\"tongtaste6\",\"fansnum\":\"7\",\"watched\":0},{\"uid\":\"120\",\"username\":\"13681863492\",\"score\":\"7150\",\"avatar\":\"http://img.popoho.com/allimg/151223/0USR040-12.jpg\",\"name\":\"tongtaste7\",\"fansnum\":\"7\",\"watched\":0},{\"uid\":\"120\",\"username\":\"13681863492\",\"score\":\"7150\",\"avatar\":\"http://img.popoho.com/allimg/151223/0USR595-14.jpg\",\"name\":\"tongtaste8\",\"fansnum\":\"7\",\"watched\":0},{\"uid\":\"120\",\"username\":\"13681863492\",\"score\":\"7150\",\"avatar\":\"http://img.popoho.com/allimg/151223/0USU461-16.jpg\",\"name\":\"tongtaste9\",\"fansnum\":\"7\",\"watched\":0},{\"uid\":\"120\",\"username\":\"13681863492\",\"score\":\"7150\",\"avatar\":\"http://img.popoho.com/allimg/151223/0USW643-18.png\",\"name\":\"tongtaste10\",\"fansnum\":\"7\",\"watched\":0},{\"uid\":\"120\",\"username\":\"13681863492\",\"score\":\"7150\",\"avatar\":\"http://img.popoho.com/allimg/151223/0USTB7-0.jpg\",\"name\":\"tongtaste1\",\"fansnum\":\"7\",\"watched\":0},{\"uid\":\"120\",\"username\":\"13681863492\",\"score\":\"7150\",\"avatar\":\"http://img.popoho.com/allimg/151223/0USW239-2.jpg\",\"name\":\"tongtaste2\",\"fansnum\":\"7\",\"watched\":0},{\"uid\":\"120\",\"username\":\"13681863492\",\"score\":\"7150\",\"avatar\":\"http://img.popoho.com/allimg/151223/0USU557-4.png\",\"name\":\"tongtaste3\",\"fansnum\":\"7\",\"watched\":0},{\"uid\":\"120\",\"username\":\"13681863492\",\"score\":\"7150\",\"avatar\":\"http://img.popoho.com/allimg/151223/0USVH7-6.jpg\",\"name\":\"tongtaste4\",\"fansnum\":\"7\",\"watched\":0},{\"uid\":\"120\",\"username\":\"13681863492\",\"score\":\"7150\",\"avatar\":\"http://img.popoho.com/allimg/151223/0USQ545-8.jpg\",\"name\":\"tongtaste5\",\"fansnum\":\"7\",\"watched\":0},{\"uid\":\"120\",\"username\":\"13681863492\",\"score\":\"7150\",\"avatar\":\"http://img.popoho.com/allimg/151223/0USW491-10.jpg\",\"name\":\"tongtaste6\",\"fansnum\":\"7\",\"watched\":0},{\"uid\":\"120\",\"username\":\"13681863492\",\"score\":\"7150\",\"avatar\":\"http://img.popoho.com/allimg/151223/0USR040-12.jpg\",\"name\":\"tongtaste7\",\"fansnum\":\"7\",\"watched\":0},{\"uid\":\"120\",\"username\":\"13681863492\",\"score\":\"7150\",\"avatar\":\"http://img.popoho.com/allimg/151223/0USR595-14.jpg\",\"name\":\"tongtaste8\",\"fansnum\":\"7\",\"watched\":0},{\"uid\":\"120\",\"username\":\"13681863492\",\"score\":\"7150\",\"avatar\":\"http://img.popoho.com/allimg/151223/0USU461-16.jpg\",\"name\":\"tongtaste9\",\"fansnum\":\"7\",\"watched\":0},{\"uid\":\"120\",\"username\":\"13681863492\",\"score\":\"7150\",\"avatar\":\"http://img.popoho.com/allimg/151223/0USW643-18.png\",\"name\":\"tongtaste10\",\"fansnum\":\"7\",\"watched\":0}]}";

    private String[] m_network_imgs = new String[]{
            "http://img5q.duitang.com/uploads/item/201407/27/20140727020334_LN82Z.jpeg",
            "http://img5.duitang.com/uploads/item/201507/17/20150717102355_RcrHe.jpeg",
            "http://cdn.duitang.com/uploads/item/201406/04/20140604092604_KiPUP.thumb.700_0.jpeg",
            "http://img5q.duitang.com/uploads/item/201407/27/20140727022248_XSH2j.jpeg",
            "http://img5q.duitang.com/uploads/item/201505/30/20150530235255_FCryj.thumb.700_0.jpeg"};


    private int[] imgArray = new int[]{R.drawable.icon_default_show,R.drawable.icon_show_demo};
    private int m_count = 0;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.activity_demo22);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        ButterKnife.inject(this);

//        initBaseView();
//        setBaseTitle("Demo22", 0);
//        initBackButton(R.id.top_back_iv);
//        setTranslucentStatus(R.color.main_bg);

        initView();

        initBottomAdapter();

        initData1();

        initGvData();

        initData2();
    }

    private void initView() {

        //160
        //45
        //160-50=110

        Glide.with(Demo22Activity.this).load(m_network_imgs[0]).into(demo_bg_iv);

        final float m_scroll_height = DensityUtil.dip2px(this, (float) 110);

//        LogsUtil.normal("dp转px="+DensityUtil.dip2px(this,(float)160)+"");

        demo_flv.setOnScrollBoundback(new FlexibileScrollView.OnScrollBoundback() {
            @Override
            public void onBoundback(int count) {
                int m_cur = count % m_network_imgs.length;
                LogsUtil.normal("onBoundback.count=" + count + ",m_cur=" + m_cur);
                Glide.with(Demo22Activity.this).load(m_network_imgs[m_cur]).into(demo_bg_iv);
            }
        });

        demo_flv.setOnScrollChangedCallback(new FlexibileScrollView.OnScrollChangedCallback() {
            @Override
            public void onScroll(int dx, int dy) {
                if (dy <= m_scroll_height) {
                    LogsUtil.normal("比例A=" + dy / m_scroll_height + ",比例B=" + dy * 1.0 / m_scroll_height);
                    demo_top_linear.setBackgroundColor(getResources().getColor(R.color.red));
                    demo_top_linear.setAlpha((float) dy / m_scroll_height);

                    demo_top_left_linear.setVisibility(View.VISIBLE);
                    demo_top_right_tv.setVisibility(View.VISIBLE);

                }

                if (dy >= m_scroll_height) {
                    demo_top_left_linear.setVisibility(View.GONE);
                    demo_top_right_tv.setVisibility(View.GONE);
                    /*LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) demo_top_right_linear.getLayoutParams();
                    // 取控件aaa当前的布局参数
//                    linearParams.height = 365; // 当控件的高强制设成365象素
                    linearParams.width = LinearLayout.LayoutParams.MATCH_PARENT; //
                    demo_top_right_linear.setLayoutParams(linearParams);*/
                }
            }
        });
    }

    private void initData1() {
        //15*300=4500
        //4500-(720-20)=3800
        //单个子项宽度
        final int m_pic_width = 300;
        //屏幕宽度
        final int m_screenWidth = DensityUtil.getScreenWidth(this);
        //第一次，默认加载图片数量,向上取整
        Double m_default_count = Math.ceil((m_screenWidth * 1.0) / (m_pic_width * 1.0));

//        LogsUtil.normal("屏幕宽:" + DensityUtil.getScreenWidth(this) + ",m_default_count=" + m_default_count);

        int indicate = 0;  //当前图片
        for (String imgUrl : Constant.picArray) {

            if (indicate >= m_default_count) {
                break;
            }
//            LogsUtil.normal("indicate="+indicate);
            View view = View.inflate(this, R.layout.layout_imageview_horizontal_show, null);
            ImageView mphote = (ImageView) view.findViewById(R.id.phote_Iv);
            Glide.with(this)
                    .load(imgUrl)
                    .thumbnail(0.3f)
                    .placeholder(R.drawable.def_photo)
                    .error(R.drawable.def_photo)
                    .transform(new GlideRoundTransform(this, 8))
                    .into(mphote);

            final int finalI = indicate;
            mphote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.DisplayToast(Demo22Activity.this, "finalI=" + finalI);
                }
            });
            setPicParams(view, true);
            container_linear1.addView(view);
            indicate++;
        }

        demo_hv1.setOnScrollChangedCallback(new MyHorizontalScrollView.OnScrollChangedCallback() {
            @Override
            public void onScroll(int dx, int dy) {
//                LogsUtil.normal("dx=" + dx + ",dy=" + dy + ",(dx/300)=" + (dx + m_screenWidth) / m_pic_width + ",double=" + (dx + m_screenWidth) * 1.0 / (300 * 1.0));
                Double m_cur_count = Math.floor((dx + m_screenWidth) * 1.0 / (m_pic_width * 1.0));

                int getChildCount = container_linear1.getChildCount();

                Double m_sub = getChildCount - m_cur_count;
//                LogsUtil.normal("m_cur_count=" + m_cur_count + ",getChildCount=" + getChildCount + ",m_sub=" + m_sub);

                if (m_cur_count < getChildCount && m_sub <= 1) {
                    addItem(getChildCount);
                }
            }
        });
    }

    private void addItem(final int position) {

//        LogsUtil.normal("position="+position);

        if (position >= Constant.picArray.length) {
            return;
        }

        String imgUrl = Constant.picArray[position];

        View view = View.inflate(this, R.layout.layout_imageview_horizontal_show, null);
        ImageView mphote = (ImageView) view.findViewById(R.id.phote_Iv);
        Glide.with(this)
                .load(imgUrl)
                .thumbnail(0.3f)
                .placeholder(R.drawable.def_photo)
                .error(R.drawable.def_photo)
                .transform(new GlideRoundTransform(this, 8))
                .into(mphote);

        mphote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.DisplayToast(Demo22Activity.this, "position=" + position);
            }
        });
        setPicParams(view, true);
        container_linear1.addView(view);
    }


    /////////////////缩略图的布局
    private void setPicParams(View view, boolean requirdRightMargin) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (requirdRightMargin) {
//            params.rightMargin = (int) getResources().getDimension(R.dimen.marign_normal);
            params.rightMargin = 0;
        }
        view.setLayoutParams(params);
    }

    private void initData2() {
        //15*300=4500
        //4500-(720-20)=3800
        //单个子项宽度
        final int m_pic_width = 100;
        //屏幕宽度
        final int m_screenWidth = DensityUtil.getScreenWidth(this);
        //第一次，默认加载图片数量,向上取整
        Double m_default_count = Math.ceil((m_screenWidth * 1.0) / (m_pic_width * 1.0));
//        LogsUtil.normal("屏幕宽:" + DensityUtil.getScreenWidth(this) + ",m_default_count=" + m_default_count);
        int indicate = 0;  //当前图片
        for (String imgUrl : Constant.picArray) {
            if (indicate >= m_default_count) {
                break;
            }
//            LogsUtil.normal("indicate="+indicate);

            View view = View.inflate(this, R.layout.item_rank_gridview, null);

            ImageView mPhote = (ImageView) view.findViewById(R.id.rank_imgIv);
            TextView mName = (TextView) view.findViewById(R.id.rank_nameTv);

            LogsUtil.normal("加载图片,imgUrl=" + imgUrl);

            Glide.with(this)
                    .load(imgUrl)
                    .thumbnail(0.3f)
                    .placeholder(R.drawable.def_photo)
                    .error(R.drawable.def_photo)
                    .transform(new GlideCircleTransform(this))
                    .into(mPhote);
            mName.setText("姓名" + indicate);
            final int finalI = indicate;
            mPhote.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.DisplayToast(Demo22Activity.this, "finalI=" + finalI);
                }
            });
            setPicParams(view, true);
            container_linear2.addView(view);
            indicate++;
        }
        demo_hv2.setOnScrollChangedCallback(new MyHorizontalScrollView.OnScrollChangedCallback() {
            @Override
            public void onScroll(int dx, int dy) {
                Double m_cur_count = Math.floor((dx + m_screenWidth) * 1.0 / (m_pic_width * 1.0));
                int getChildCount = container_linear2.getChildCount();
                Double m_sub = getChildCount - m_cur_count;
//                LogsUtil.normal("m_cur_count=" + m_cur_count + ",getChildCount=" + getChildCount + ",m_sub=" + m_sub);
                if (m_cur_count < getChildCount && m_sub <= 1) {
                    addItem2(getChildCount);
                }
            }
        });
    }

    private void addItem2(final int position) {

//        LogsUtil.normal("position="+position);

        if (position >= Constant.picArray.length) {
            return;
        }

        String imgUrl = Constant.picArray[position];

        View view = View.inflate(this, R.layout.item_rank_gridview, null);

        ImageView mPhote = (ImageView) view.findViewById(R.id.rank_imgIv);
        TextView mName = (TextView) view.findViewById(R.id.rank_nameTv);

        LogsUtil.normal("加载图片,imgUrl=" + imgUrl);

        Glide.with(this)
                .load(imgUrl)
                .thumbnail(0.3f)
                .placeholder(R.drawable.def_photo)
                .error(R.drawable.def_photo)
                .transform(new GlideCircleTransform(this))
                .into(mPhote);

        mName.setText("姓名" + position);

        mPhote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.DisplayToast(Demo22Activity.this, "position=" + position);
            }
        });
        setPicParams(view, true);
        container_linear2.addView(view);


    }

    private void initBottomAdapter() {
        indexGridAdapter2 = new IndexGridScoreAdapter(this, rankBeanList2);
        demo_gv.setAdapter(indexGridAdapter2);
        indexGridAdapter2.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                ToastUtil.DisplayToast(Demo22Activity.this, "点击了:" + postion);
            }

            @Override
            public void onItemLongClick(View view, int postion) {
            }

            @Override
            public void onItemSubViewClick(View view, int postion) {
            }
        });
    }

    //gridview横向布局方法
    public void horizontal_layout(int count) {
        int size = count;
        int horizon_space = 20;

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;
        int allWidth = (int) (100 * size * density);///100=>item宽度
        int itemWidth = (int) (100 * density);///

        allWidth = allWidth + horizon_space * (size - 1);

//        LogsUtil.normal("size=" + size + ",density=" + density + ",allWidth=" + allWidth + ",itemWidth=" + itemWidth);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                allWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        demo_gv.setLayoutParams(params);// 设置GirdView布局参数
        demo_gv.setColumnWidth(itemWidth);// 列表项宽
        demo_gv.setHorizontalSpacing(horizon_space);// 列表项水平间距
        demo_gv.setStretchMode(GridView.NO_STRETCH);
        demo_gv.setNumColumns(size);//总长度
    }

    private void initGvData() {
//        LogsUtil.normal("m_content=" + m_content);
        Gson gson = new Gson();
        ScoreRankBean data = gson.fromJson(m_content, ScoreRankBean.class);
        if (data != null) {
            rankBeanList2 = data.getData();
            horizontal_layout(rankBeanList2.size());
            indexGridAdapter2.addAll(rankBeanList2);
        } else {
            ToastUtil.DisplayToast(this, Constant.TOAST_MEG_ANALYZE_ERROR);
        }
    }

    private void backToList() {
        Intent intent = new Intent();
        intent.putExtra(Constant.CONFIG_INTENT_NAME, "TEST");
        setResult(RESULT_OK, intent);
        this.finish();
    }

    @OnClick(R.id.demo_top_right_linear)
    public void showAnimator(){
        /*demo_top_right_linear.bringToFront();
        ScaleAnimation scal = new ScaleAnimation(1.0f, 1.4f, 1.0f, 1.4f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        scal.setDuration(2000);*/

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.full_linearlayout);
        //动画执行完毕后是否停在结束时的角度上
        animation.setFillAfter(true);
        demo_top_right_linear.startAnimation(animation);
    }

    @Override
     public void onBackPressed() {
        super.onBackPressed();
        backToList();
    }

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);
//        LogsUtil.normal("getResult");
    }


}
