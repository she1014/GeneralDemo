package com.techfly.demo.activity.demo;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.bean.LableBean;
import com.techfly.demo.util.DensityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * TableLayout
 */
public class Demo14Activity extends BaseActivity {

    @InjectView(R.id.tablayout_bunk)
    TableLayout m_tablayout;

    private String[] fiveArray = new String[13];
    private String[] deciArray = new String[13];
    private List<LableBean> lableBeanList = new ArrayList<LableBean>();

    private int screenWidth = 0;  //屏幕宽度
    private int screenHeight = 0; //屏幕高度

    private int tableLWidth = 700;  //表格左栏宽度
    private int tableRWidth = 300;  //表格右栏宽度
    private int tableHeight = 85;  //表格高度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo14);

        ButterKnife.inject(this);
        initBaseView();
        setBaseTitle("Demo14", 0);
        initBackButton(R.id.top_back_iv);

        setTranslucentStatus(R.color.main_bg);

        initView();

        initData();

        initTable();
    }

    private void initView(){
        screenWidth = DensityUtil.getScreenWidth(this);
        screenHeight = DensityUtil.getScreenHeight(this);
        Log.i("TTSS", "宽高为1:" + screenHeight + "," + screenWidth);

        tableLWidth = (Integer)(screenWidth / 10 * 6);
        tableRWidth = (Integer)(screenWidth / 10 * 4);

        tableHeight = (Integer)((screenHeight-300) / 14);

        Log.i("TTSS", "宽高为2:" + tableLWidth + "," + tableRWidth);
    }

    private void initData(){

        fiveArray = getResources().getStringArray(R.array.fiveArray);
        deciArray = getResources().getStringArray(R.array.decimalArray);

        for(int i = 0;i < fiveArray.length;i++){
            LableBean bean = new LableBean(fiveArray[i],deciArray[i]);
            lableBeanList.add(bean);
        }
    }

    private void initTable(){
        // 标题
        TableRow tablerow = new TableRow(this);

        TextView textTime = new TextView(this);
        textTime.setGravity(Gravity.CENTER);
        textTime.setText("标准对数视力表五分记录");
        textTime.setTextSize(18);
        textTime.setBackgroundResource(R.drawable.shape_stroke_gray_solid_empty_right_angle);
        textTime.setWidth(tableLWidth);
        textTime.setTextColor(Color.BLUE);
        textTime.setHeight(tableHeight);
        tablerow.addView(textTime);

        TextView textName = new TextView(this);
        textName.setGravity(Gravity.CENTER);
        textName.setText("小数记录");
        textName.setTextSize(18);
        textName.setBackgroundResource(R.drawable.shape_stroke_gray_solid_empty_right_angle);
        textName.setWidth(tableRWidth);
        textName.setTextColor(Color.BLUE);
        textName.setHeight(tableHeight);
        tablerow.addView(textName);

        m_tablayout.addView(tablerow, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));

        for (int i = 0; i < lableBeanList.size(); i++) {

            TableRow tableCtn = new TableRow(this);

            TextView textTimeCtn = new TextView(this);
            textTimeCtn.setGravity(Gravity.CENTER);
            textTimeCtn.setText(lableBeanList.get(i).getId());
            textTimeCtn.setTextSize(18);
            textTimeCtn.setBackgroundResource(R.drawable.shape_stroke_gray_solid_empty_right_angle);
            textTimeCtn.setWidth(tableLWidth);
            textTimeCtn.setTextColor(Color.BLACK);
            textTimeCtn.setHeight(tableHeight);
            tableCtn.addView(textTimeCtn);

            TextView textNameCtn = new TextView(this);
            textNameCtn.setGravity(Gravity.CENTER);
            textNameCtn.setText(lableBeanList.get(i).getName());
            textNameCtn.setTextSize(18);
            textNameCtn.setBackgroundResource(R.drawable.shape_stroke_gray_solid_empty_right_angle);
            textNameCtn.setWidth(tableRWidth);
            textNameCtn.setTextColor(Color.BLACK);
            textNameCtn.setHeight(tableHeight);
            tableCtn.addView(textNameCtn);

            m_tablayout.addView(tableCtn, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT));
        }
    }


}
