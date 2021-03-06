package com.techfly.demo.activity.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity3;
import com.techfly.demo.selfview.GuideMaskView;

import butterknife.ButterKnife;
import butterknife.InjectView;


/*
 * 跟侧滑删除Activity冲突，故继承BaseActivity3
 */
public class Demo10Activity extends BaseActivity3 {

    @InjectView(R.id.demo_tv)
    TextView demo_tv;
    @InjectView(R.id.demo_et)
    EditText demo_et;
    @InjectView(R.id.demo_iv)
    ImageView demo_iv;

    private GuideMaskView guid_view1;
    private GuideMaskView guid_view2;
    private GuideMaskView guid_view3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo10);

        ButterKnife.inject(this);

        setTranslucentStatus(R.color.main_bg);

        initBaseView();
        setBaseTitle("Demo10", 0);
        initBackButton(R.id.top_back_iv);

        initGuidView();

    }

    private void initGuidView(){

        // 使用图片
        final ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.icon_new_task_guide);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        iv.setLayoutParams(params);


        final TextView tv2 = new TextView(this);
        tv2.setTextColor(Color.BLACK);
        tv2.setTextSize(20);
        tv2.setText("第二步,提示是文字");

        final TextView tv3 = new TextView(this);
        tv3.setTextColor(Color.BLACK);
        tv3.setTextSize(20);
        tv3.setText("第三步,提示在右上");


        guid_view1 = GuideMaskView.Builder
                .newInstance(this)
                .setTargetView(demo_tv)//设置目标
                .setCustomGuideView(iv)
                .setRadius(80)
                .setDirction(GuideMaskView.Direction.LEFT_BOTTOM)
                .setShape(GuideMaskView.MyShape.RECTANGULAR)   // 设置圆形显示区域，
                .setBgColor(getResources().getColor(R.color.shadow))
                .setOnclickListener(new GuideMaskView.OnClickCallback() {
                    @Override
                    public void onClickedGuideView() {
                        guid_view1.hide();
                        guid_view2.show();
                    }
                })
                .build();

        guid_view2 = GuideMaskView.Builder
                .newInstance(this)
                .setTargetView(demo_et)//设置目标
                .setCustomGuideView(tv2)
                .setRadius(80)
                .setDirction(GuideMaskView.Direction.BOTTOM)
                .setShape(GuideMaskView.MyShape.RECTANGULAR)   // 设置圆形显示区域，
                .setBgColor(getResources().getColor(R.color.shadow))
                .setOnclickListener(new GuideMaskView.OnClickCallback() {
                    @Override
                    public void onClickedGuideView() {
                        guid_view2.hide();
                        guid_view3.show();
                    }
                })
                .build();

        guid_view3 = GuideMaskView.Builder
                .newInstance(this)
                .setTargetView(demo_iv)//设置目标
                .setCustomGuideView(tv3)
                .setRadius(30)
                .setDirction(GuideMaskView.Direction.RIGHT_TOP)
                .setShape(GuideMaskView.MyShape.CIRCULAR)   // 设置圆形显示区域，
                .setBgColor(getResources().getColor(R.color.shadow))
                .setOnclickListener(new GuideMaskView.OnClickCallback() {
                    @Override
                    public void onClickedGuideView() {
                        guid_view3.hide();
                    }
                })
                .build();

        guid_view1.show();
//        guid_view2.show();
//        guid_view3.show();

    }


}
