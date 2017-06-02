package com.techfly.demo.activity.demo;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.adpter.FileLvAdapter;
import com.techfly.demo.bean.EventBean;
import com.techfly.demo.bean.MyFileBean;
import com.techfly.demo.bean.MyVisionBean;
import com.techfly.demo.util.DateUtil;
import com.techfly.demo.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;


/**
 * 我的视力档案
 */
public class Demo12Activity extends BaseActivity implements OnChartValueSelectedListener {

    @InjectView(R.id.base_lv)
    ListView base_lv;
    @InjectView(R.id.base_view)
    View base_view;

    @InjectView(R.id.top_title_tv)
    TextView top_title_tv;

    public LinearLayout more_linear = null;

    private FileLvAdapter fileLvAdapter;
    private List<MyFileBean.TestEntity> fileBeanList = new ArrayList<MyFileBean.TestEntity>();

    private List<MyVisionBean.DataEntity> visionBeanList = new ArrayList<MyVisionBean.DataEntity>();

    private String[] lineArray = new String[]{"0.0","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","0.9","1.0","1.1","1.2","1.3","1.4","1.5"};
//    private String[] lineArray = new String[]{"0.0","0.2","0.4","0.6","0.8","1.0","1.2","1.4","1.5"};

    private LineChart mChart;

    //线条颜色，取：1,2,3,4
    private int[] lineColorArray = new int[]{R.color.transparent, R.color.text_font_green, R.color.red, R.color.text_font_black, R.color.divider_line_orange,R.color.gray};

    private String file_id = "";
    private String file_name = "";

    private Handler mHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo12);

        ButterKnife.inject(this);
        initBaseView();
        initBackButton(R.id.top_back_iv);

        setBaseTitle("Demo12", 1);
        setTranslucentStatus(R.color.main_bg);

        //注册EventBus
        EventBus.getDefault().register(this);

        loadIntent();

        initView();

        setAdapter();

        loadData();

//      initLine2();  //折线图
    }

    private void loadIntent() {
        file_id = getIntent().getStringExtra(Constant.CONFIG_INTENT_ID);
        Log.i("TTSS", "gid=" + file_id);
        file_name = getIntent().getStringExtra(Constant.CONFIG_INTENT_NAME);
        if (!TextUtils.isEmpty(file_name)) {
            top_title_tv.setText(file_name + "的视力档案");
        }
    }

    private void initLine2() {
        mChart = (LineChart) findViewById(R.id.line_chart);
        mChart.setOnChartValueSelectedListener(Demo12Activity.this);
        // no description text
//        mChart.setDescription("视力变化情况");///
        mChart.setDescription("");//
        mChart.setNoDataTextDescription("当前无数据");
//        mChart.setDragDecelerationFrictionCoef(0.9f);
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);    //是否可拖拽
        mChart.setScaleEnabled(true);   //是否可缩放
//        mChart.setScaleXEnabled(false);
        mChart.setScaleYEnabled(false);

        mChart.setDrawGridBackground(false);
        mChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF);

        mChart.setHighlightPerDragEnabled(true);
        //设置是否能扩大扩小
        mChart.setPinchZoom(false);
        // set an alternative background color
        mChart.setBackgroundColor(Color.WHITE);
        mChart.animateX(1500);
//        mChart.setAutoScaleMinMaxEnabled(false);
//        mChart.setVisibleXRangeMaximum(10);

        setData(visionBeanList.size());    //加载数据

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(9f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false); //网格中X轴线条
        xAxis.setDrawAxisLine(true);
        xAxis.setSpaceBetweenLabels(1);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);


        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setAxisMinValue(0.0f);
        leftAxis.setAxisMaxValue(1.5f);
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setTextSize(8f);   //通过设置字体的大小来控制Y轴是否显示全部
        leftAxis.setDrawGridLines(false);//网格中Y轴线条
        leftAxis.setLabelCount(13, false);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setTextColor(ColorTemplate.getHoloBlue());
        rightAxis.setAxisMinValue(0.0f);//(4.0f);
        rightAxis.setAxisMaxValue(1.5f);//(5.2f);
        rightAxis.setTextSize(8f);
        rightAxis.setTextColor(Color.BLACK);
        rightAxis.setDrawGridLines(false);//网格中Y轴线条
        rightAxis.setLabelCount(13, false);

    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        Log.i("Entry selected", e.toString());
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }

    private void setData(int count) {

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < visionBeanList.size(); i++) {

            Long time = Long.parseLong(visionBeanList.get(i).getCheck_time());
            if(visionBeanList.size() == 1){
                xVals.add("");
                xVals.add("");
                xVals.add("");
                xVals.add(DateUtil.secondDate(time));///
                xVals.add("");
                xVals.add("");
                xVals.add("");
            }else{
                xVals.add(DateUtil.secondDate(time));///
            }
        }

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        for (int i = 0; i < visionBeanList.size(); i++) {
            if (!TextUtils.isEmpty(visionBeanList.get(i).getUnv_r())) {
                if(visionBeanList.size() == 1){
                    yVals1.add(new Entry(Float.parseFloat(visionBeanList.get(i).getUnv_r()), 3));///
                }else{
                    yVals1.add(new Entry(Float.parseFloat(visionBeanList.get(i).getUnv_r()), i));///
                }
            }
        }
        LineDataSet set1 = new LineDataSet(yVals1, "裸眼(右,R)");
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setLineWidth(1.5f);
        set1.setFillAlpha(65);
//        set1.setValueTextSize(10f);
//        set1.setValueTextColor(lineColorArray[1]);
        set1.setFillColor(ColorTemplate.getHoloBlue());
        set1.setHighLightColor(Color.rgb(244, 117, 117));//选中十字交叉线颜色(淡红)
        set1.setDrawCircleHole(false);  //是否实心圆点
        // 显示的圆形大小
        set1.setCircleSize(4.0f);
        // 折线的颜色
        set1.setColor(getResources().getColor(lineColorArray[1]));
        // 圆球的颜色
        set1.setCircleColor(getResources().getColor(lineColorArray[1]));

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
        for (int i = 0; i < visionBeanList.size(); i++) {
            if (!TextUtils.isEmpty(visionBeanList.get(i).getUnv_l())) {
                if(visionBeanList.size() == 1){
                    yVals2.add(new Entry(Float.parseFloat(visionBeanList.get(i).getUnv_l()), 3));///
                }else{
                    yVals2.add(new Entry(Float.parseFloat(visionBeanList.get(i).getUnv_l()), i));///
                }
            }
        }
        LineDataSet set2 = new LineDataSet(yVals2, "裸眼(左,L)");
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);
        set2.setValueTextColor(Color.rgb(0, 164, 150));
        set2.setLineWidth(1.5f);
        set2.setFillAlpha(65);
        set2.setFillColor(ColorTemplate.getHoloBlue());
        set2.setHighLightColor(Color.rgb(244, 117, 117));//选中十字交叉线颜色(淡红)
        set2.setDrawCircleHole(false);  //是否实心圆点
        // 显示的圆形大小
        set2.setCircleSize(4.0f);
        // 折线的颜色
        set2.setColor(getResources().getColor(lineColorArray[2]));
        // 圆球的颜色
        set2.setCircleColor(getResources().getColor(lineColorArray[2]));


        ArrayList<Entry> yVals3 = new ArrayList<Entry>();
        for (int i = 0; i < visionBeanList.size(); i++) {
            if (!TextUtils.isEmpty(visionBeanList.get(i).getCv_r())) {
                if(visionBeanList.size() == 1){
                    yVals3.add(new Entry(Float.parseFloat(visionBeanList.get(i).getCv_r()), 3));///
                }else{
                    yVals3.add(new Entry(Float.parseFloat(visionBeanList.get(i).getCv_r()), i));///
                }
            }
//            yVals3.add(new Entry(Float.parseFloat(visionBeanList.get(i).getCv_r()), i));///
        }
        LineDataSet set3 = new LineDataSet(yVals3, "矫正(右,R)");
        set3.setAxisDependency(YAxis.AxisDependency.LEFT);
        set3.setLineWidth(1.5f);
        set3.setFillAlpha(65);
        set3.setFillColor(ColorTemplate.getHoloBlue());
        set3.setHighLightColor(Color.rgb(244, 117, 117));//选中十字交叉线颜色(淡红)
        set3.setDrawCircleHole(false);  //是否实心圆点
        // 显示的圆形大小
        set3.setCircleSize(4.0f);
        // 折线的颜色
        set3.setColor(getResources().getColor(lineColorArray[3]));
        // 圆球的颜色
        set3.setCircleColor(getResources().getColor(lineColorArray[3]));


        ArrayList<Entry> yVals4 = new ArrayList<Entry>();
        for (int i = 0; i < visionBeanList.size(); i++) {
            if (!TextUtils.isEmpty(visionBeanList.get(i).getCv_l())) {
                if(visionBeanList.size() == 1){
                    yVals4.add(new Entry(Float.parseFloat(visionBeanList.get(i).getCv_l()), 3));///
                }else{
                    yVals4.add(new Entry(Float.parseFloat(visionBeanList.get(i).getCv_l()), i));///
                }
            }
        }
        LineDataSet set4 = new LineDataSet(yVals4, "矫正(左,L)");
        set4.setAxisDependency(YAxis.AxisDependency.LEFT);
        set4.setLineWidth(1.5f);
        set4.setFillAlpha(65);
        set4.setFillColor(ColorTemplate.getHoloBlue());
        set4.setHighLightColor(Color.rgb(244, 117, 117));//选中十字交叉线颜色(淡红)
        set4.setDrawCircleHole(false);  //是否实心圆点
        // 显示的圆形大小
        set4.setCircleSize(4.0f);
        // 折线的颜色轴标签
        set4.setColor(getResources().getColor(lineColorArray[4]));
        // 圆球的颜色
        set4.setCircleColor(getResources().getColor(lineColorArray[4]));


        /*--填充线(15条)--*/
        ArrayList<Entry> yVals5 = new ArrayList<Entry>();
        yVals5.add(new Entry(Float.parseFloat(lineArray[0]), 0));///
        LineDataSet set5 = new LineDataSet(yVals5, "");
        set5.setCircleSize(0f);
        set5.setColor(getResources().getColor(lineColorArray[0]));
        set5.setCircleColor(getResources().getColor(lineColorArray[0]));

        ArrayList<Entry> yVals6 = new ArrayList<Entry>();
        yVals6.add(new Entry(Float.parseFloat(lineArray[1]), 0));///
        LineDataSet set6 = new LineDataSet(yVals6, "");
        set6.setCircleSize(0f);
        set6.setColor(getResources().getColor(lineColorArray[0]));
        set6.setCircleColor(getResources().getColor(lineColorArray[0]));

        ArrayList<Entry> yVals7 = new ArrayList<Entry>();
        yVals7.add(new Entry(Float.parseFloat(lineArray[2]), 0));///
        LineDataSet set7 = new LineDataSet(yVals7, "");
        set7.setCircleSize(0f);
        set7.setColor(getResources().getColor(lineColorArray[0]));
        set7.setCircleColor(getResources().getColor(lineColorArray[0]));

        ArrayList<Entry> yVals8 = new ArrayList<Entry>();
        yVals8.add(new Entry(Float.parseFloat(lineArray[3]), 0));///
        LineDataSet set8 = new LineDataSet(yVals8, "");
        set8.setCircleSize(0f);
        set8.setColor(getResources().getColor(lineColorArray[0]));
        set8.setCircleColor(getResources().getColor(lineColorArray[0]));

        ArrayList<Entry> yVals9 = new ArrayList<Entry>();
        yVals9.add(new Entry(Float.parseFloat(lineArray[4]), 0));///
        LineDataSet set9 = new LineDataSet(yVals9, "");
        set9.setCircleSize(0f);
        set9.setColor(getResources().getColor(lineColorArray[0]));
        set9.setCircleColor(getResources().getColor(lineColorArray[0]));

        ArrayList<Entry> yVals10 = new ArrayList<Entry>();
        yVals10.add(new Entry(Float.parseFloat(lineArray[5]), 0));///
        LineDataSet set10 = new LineDataSet(yVals10, "");
        set10.setCircleSize(0f);
        set10.setColor(getResources().getColor(lineColorArray[0]));
        set10.setCircleColor(getResources().getColor(lineColorArray[0]));

        ArrayList<Entry> yVals11 = new ArrayList<Entry>();
        yVals11.add(new Entry(Float.parseFloat(lineArray[6]), 0));///
        LineDataSet set11 = new LineDataSet(yVals11, "");
        set11.setCircleSize(0f);
        set11.setColor(getResources().getColor(lineColorArray[0]));
        set11.setCircleColor(getResources().getColor(lineColorArray[0]));

        ArrayList<Entry> yVals12 = new ArrayList<Entry>();
        yVals12.add(new Entry(Float.parseFloat(lineArray[7]), 0));///
        LineDataSet set12 = new LineDataSet(yVals12, "");
        set12.setCircleSize(0f);
        set12.setColor(getResources().getColor(lineColorArray[0]));
        set12.setCircleColor(getResources().getColor(lineColorArray[0]));

        ArrayList<Entry> yVals13 = new ArrayList<Entry>();
        yVals13.add(new Entry(Float.parseFloat(lineArray[8]), 0));///
        LineDataSet set13 = new LineDataSet(yVals13, "");
        set13.setCircleSize(0f);
        set13.setColor(getResources().getColor(lineColorArray[0]));
        set13.setCircleColor(getResources().getColor(lineColorArray[0]));
        //9-15
        ArrayList<Entry> yVals14 = new ArrayList<Entry>();
        yVals14.add(new Entry(Float.parseFloat(lineArray[9]), 0));///
        LineDataSet set14 = new LineDataSet(yVals14, "");
        set14.setCircleSize(0f);
        set14.setColor(getResources().getColor(lineColorArray[0]));
        set14.setCircleColor(getResources().getColor(lineColorArray[0]));

        ArrayList<Entry> yVals15 = new ArrayList<Entry>();
        yVals15.add(new Entry(Float.parseFloat(lineArray[10]), 0));///
        LineDataSet set15 = new LineDataSet(yVals15, "");
        set15.setCircleSize(0f);
        set15.setColor(getResources().getColor(lineColorArray[0]));
        set15.setCircleColor(getResources().getColor(lineColorArray[0]));

        ArrayList<Entry> yVals16 = new ArrayList<Entry>();
        yVals16.add(new Entry(Float.parseFloat(lineArray[11]), 0));///
        LineDataSet set16 = new LineDataSet(yVals16, "");
        set16.setCircleSize(0f);
        set16.setColor(getResources().getColor(lineColorArray[0]));
        set16.setCircleColor(getResources().getColor(lineColorArray[0]));

        ArrayList<Entry> yVals17 = new ArrayList<Entry>();
        yVals17.add(new Entry(Float.parseFloat(lineArray[12]), 0));///
        LineDataSet set17 = new LineDataSet(yVals17, "");
        set17.setCircleSize(0f);
        set17.setColor(getResources().getColor(lineColorArray[0]));
        set17.setCircleColor(getResources().getColor(lineColorArray[0]));

        ArrayList<Entry> yVals18 = new ArrayList<Entry>();
        yVals18.add(new Entry(Float.parseFloat(lineArray[13]), 0));///
        LineDataSet set18 = new LineDataSet(yVals18, "");
        set18.setCircleSize(0f);
        set18.setColor(getResources().getColor(lineColorArray[0]));
        set18.setCircleColor(getResources().getColor(lineColorArray[0]));

        ArrayList<Entry> yVals19 = new ArrayList<Entry>();
        yVals19.add(new Entry(Float.parseFloat(lineArray[14]), 0));///
        LineDataSet set19 = new LineDataSet(yVals19, "");
        set19.setCircleSize(0f);
        set19.setColor(getResources().getColor(lineColorArray[0]));
        set19.setCircleColor(getResources().getColor(lineColorArray[0]));

        /*ArrayList<Entry> yVals20 = new ArrayList<Entry>();
        yVals20.add(new Entry(Float.parseFloat(lineArray[15]), 0));///
        LineDataSet set20 = new LineDataSet(yVals20, "");
        set20.setCircleSize(0f);
        set20.setColor(getResources().getColor(lineColorArray[0]));
        set20.setCircleColor(getResources().getColor(lineColorArray[0]));*/
        /*--填充线(15条)【适配作用】--*/

        //描述文字不需要=>需要
        LineData data1 = new LineData(xVals, set1);
        data1.setValueTextColor(getResources().getColor(lineColorArray[1]));
        data1.setValueTextSize(9f);

        LineData data2 = new LineData(xVals, set2);
        data2.setValueTextColor(getResources().getColor(lineColorArray[2]));
        data2.setValueTextSize(9f);

        LineData data3 = new LineData(xVals, set3);
        data3.setValueTextColor(getResources().getColor(lineColorArray[3]));
        data3.setValueTextSize(9f);

        LineData data4 = new LineData(xVals, set4);
        data4.setValueTextColor(getResources().getColor(lineColorArray[4]));
        data4.setValueTextSize(9f);

        /*--填充线-透明色--*/
        LineData data5 = new LineData(xVals, set5);
        data5.setValueTextSize(0f);
        LineData data6 = new LineData(xVals, set6);
        data6.setValueTextSize(0f);
        LineData data7 = new LineData(xVals, set7);
        data7.setValueTextSize(0f);
        LineData data8 = new LineData(xVals, set8);
        data8.setValueTextSize(0f);
        LineData data9 = new LineData(xVals, set9);
        data9.setValueTextSize(0f);
        LineData data10 = new LineData(xVals, set10);
        data10.setValueTextSize(0f);
        LineData data11 = new LineData(xVals, set11);
        data11.setValueTextSize(0f);
        LineData data12 = new LineData(xVals, set12);
        data12.setValueTextSize(0f);
        LineData data13 = new LineData(xVals, set13);
        data13.setValueTextSize(0f);
        LineData data14 = new LineData(xVals, set14);
        data14.setValueTextSize(0f);
        LineData data15 = new LineData(xVals, set15);
        data15.setValueTextSize(0f);
        LineData data16 = new LineData(xVals, set16);
        data16.setValueTextSize(0f);
        LineData data17 = new LineData(xVals, set17);
        data17.setValueTextSize(0f);
        LineData data18 = new LineData(xVals, set18);
        data18.setValueTextSize(0f);
        LineData data19 = new LineData(xVals, set19);
        data19.setValueTextSize(0f);
        /*--填充线-透明色--*/

        ArrayList<LineDataSet> mLineDataSets = new ArrayList<LineDataSet>();
        mLineDataSets.add(set1);
        mLineDataSets.add(set2);
        mLineDataSets.add(set3);
        mLineDataSets.add(set4);

        mLineDataSets.add(set5);///
        mLineDataSets.add(set6);///
        mLineDataSets.add(set7);///
        mLineDataSets.add(set8);///
        mLineDataSets.add(set9);///
        mLineDataSets.add(set10);///
        mLineDataSets.add(set11);///
        mLineDataSets.add(set12);///
        mLineDataSets.add(set13);///
        mLineDataSets.add(set14);///
        mLineDataSets.add(set15);///
        mLineDataSets.add(set16);///
        mLineDataSets.add(set17);///
        mLineDataSets.add(set18);///
        mLineDataSets.add(set19);///

        LineData mLineData = new LineData(xVals, mLineDataSets);
        mChart.setData(mLineData);
    }


    private void initView() {

        View footView = LayoutInflater.from(Demo12Activity.this).inflate(R.layout.layout_lv_foot, null);
        base_lv.addFooterView(footView);

        more_linear = (LinearLayout) footView.findViewById(R.id.listview_foot_moreLinear);
        more_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.DisplayToast(Demo12Activity.this,"点击跳转?");
                /*Intent intent = new Intent(Demo12Activity.this, MyFileMoreActivity.class);
                if (!TextUtils.isEmpty(file_name)) {
                    intent.putExtra(Constant.CONFIG_FILE_ID, file_id);
                    intent.putExtra(Constant.CONFIG_FILE_NAME, file_name);
                }
                startActivity(intent);*/
            }
        });
    }

    private void loadData() {

        String result1 = "{\"status\":\"ok\",\"test\":[{\"id\":\"155\",\"status\":\"1\",\"user_id\":\"102\",\"uncorrected_visual_l\":\"0.1\",\"uncorrected_visual_r\":\"0.1\",\"corrected_visual_l\":\"0.1\",\"corrected_visual_r\":\"0.1\",\"diopter_l\":\"0.00\",\"diopter_r\":\"0.00\",\"mirror_degree_l\":\"0.50\",\"mirror_degree_r\":\"0.50\",\"flash_degree_l\":\"+0.00\",\"flash_degree_r\":\"-0.00\",\"flash_axial_l\":\"0\",\"flash_axial_r\":\"0\",\"sex\":\"1\",\"age\":\"25\",\"check_time\":\"1477411200\",\"group_id\":\"0\",\"answer\":null,\"add_time\":\"2016-10-26 10:32:57\",\"add_user\":\"102\",\"mod_time\":\"2016-10-26 10:33:57\",\"mod_user\":\"102\"},{\"id\":\"154\",\"status\":\"1\",\"user_id\":\"102\",\"uncorrected_visual_l\":\"1.2\",\"uncorrected_visual_r\":\"0.3\",\"corrected_visual_l\":\"0.8\",\"corrected_visual_r\":\"1.0\",\"diopter_l\":\"-3.00\",\"diopter_r\":\"+2.75\",\"mirror_degree_l\":\"4.75\",\"mirror_degree_r\":\"2.25\",\"flash_degree_l\":\"+4.50\",\"flash_degree_r\":\"-4.25\",\"flash_axial_l\":\"35\",\"flash_axial_r\":\"100\",\"sex\":\"1\",\"age\":\"25\",\"check_time\":\"1474819200\",\"group_id\":\"0\",\"answer\":null,\"add_time\":\"2016-10-26 10:32:27\",\"add_user\":\"102\",\"mod_time\":\"2016-10-26 10:32:27\",\"mod_user\":\"102\"},{\"id\":\"153\",\"status\":\"1\",\"user_id\":\"102\",\"uncorrected_visual_l\":\"0.6\",\"uncorrected_visual_r\":\"0.2\",\"corrected_visual_l\":\"0.8\",\"corrected_visual_r\":\"0.6\",\"diopter_l\":\"-3.50\",\"diopter_r\":\"+3.25\",\"mirror_degree_l\":\"1.75\",\"mirror_degree_r\":\"2.75\",\"flash_degree_l\":\"+1.50\",\"flash_degree_r\":\"-3.25\",\"flash_axial_l\":\"95\",\"flash_axial_r\":\"80\",\"sex\":\"1\",\"age\":\"25\",\"check_time\":\"1471017600\",\"group_id\":\"0\",\"answer\":null,\"add_time\":\"2016-10-26 10:31:48\",\"add_user\":\"102\",\"mod_time\":\"2016-10-26 10:31:48\",\"mod_user\":\"102\"},{\"id\":\"152\",\"status\":\"1\",\"user_id\":\"102\",\"uncorrected_visual_l\":\"0.2\",\"uncorrected_visual_r\":\"0.12\",\"corrected_visual_l\":\"0.8\",\"corrected_visual_r\":\"0.6\",\"diopter_l\":\"-1.00\",\"diopter_r\":\"+2.00\",\"mirror_degree_l\":\"3.25\",\"mirror_degree_r\":\"3.25\",\"flash_degree_l\":\"+3.50\",\"flash_degree_r\":\"-3.25\",\"flash_axial_l\":\"35\",\"flash_axial_r\":\"70\",\"sex\":\"1\",\"age\":\"18\",\"check_time\":\"1470067200\",\"group_id\":\"0\",\"answer\":null,\"add_time\":\"2016-10-26 10:31:16\",\"add_user\":\"102\",\"mod_time\":\"2016-10-26 10:31:16\",\"mod_user\":\"102\"},{\"id\":\"113\",\"status\":\"1\",\"user_id\":\"102\",\"uncorrected_visual_l\":\"0.5\",\"uncorrected_visual_r\":\"0.5\",\"corrected_visual_l\":\"0.8\",\"corrected_visual_r\":\"1.0\",\"diopter_l\":\"-2.00\",\"diopter_r\":\"+2.75\",\"mirror_degree_l\":\"4.25\",\"mirror_degree_r\":\"4.50\",\"flash_degree_l\":\"+4.00\",\"flash_degree_r\":\"-2.50\",\"flash_axial_l\":\"95\",\"flash_axial_r\":\"80\",\"sex\":\"1\",\"age\":\"22\",\"check_time\":\"1468166400\",\"group_id\":\"0\",\"answer\":null,\"add_time\":\"2016-07-11 00:15:45\",\"add_user\":\"102\",\"mod_time\":\"2016-07-15 18:58:12\",\"mod_user\":\"102\"}]}";
        String result2 = "{\"status\":\"ok\",\"data_length\":5,\"data\":[{\"id\":\"113\",\"unv_l\":\"0.5\",\"unv_r\":\"0.5\",\"cv_l\":\"0.8\",\"cv_r\":\"1.0\",\"check_time\":\"1468166400\"},{\"id\":\"152\",\"unv_l\":\"0.2\",\"unv_r\":\"0.12\",\"cv_l\":\"0.8\",\"cv_r\":\"0.6\",\"check_time\":\"1470067200\"},{\"id\":\"153\",\"unv_l\":\"0.6\",\"unv_r\":\"0.2\",\"cv_l\":\"0.8\",\"cv_r\":\"0.6\",\"check_time\":\"1471017600\"},{\"id\":\"154\",\"unv_l\":\"1.2\",\"unv_r\":\"0.3\",\"cv_l\":\"0.8\",\"cv_r\":\"1.0\",\"check_time\":\"1474819200\"},{\"id\":\"155\",\"unv_l\":\"0.1\",\"unv_r\":\"0.1\",\"cv_l\":\"0.1\",\"cv_r\":\"0.1\",\"check_time\":\"1477411200\"}]}";

        solvePersonData(result1);
        solvePersonCurve(result2);

    }

    private void setAdapter() {
        fileLvAdapter = new FileLvAdapter(this, fileBeanList);
        base_lv.setAdapter(fileLvAdapter);
    }

    @OnClick(R.id.top_right_iv)
    public void addMyFile() {
        ToastUtil.DisplayToast(Demo12Activity.this, "点击新增?");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Log.i("TTSS", "onActivityResult");
            loadData();
        }
    }

    private void solvePersonData(String result){
        try {
            Gson gson = new Gson();
            MyFileBean data = gson.fromJson(result, MyFileBean.class);

            if (fileLvAdapter != null) {
                fileLvAdapter.clearAll();
            }

            if (data != null) {
                fileBeanList = data.getTest();
                if (fileBeanList.size() < 3) {
                    fileLvAdapter.addAll(fileBeanList);
                } else {
                    List<MyFileBean.TestEntity> list = new ArrayList<MyFileBean.TestEntity>();
                    list.add(fileBeanList.get(0));
                    list.add(fileBeanList.get(1));
                    list.add(fileBeanList.get(2));
                    fileLvAdapter.addAll(list);
                }
            } else {
                ToastUtil.DisplayToast(this, Constant.TOAST_MEG_ANALYZE_ERROR);
            }
        } catch (Exception e) {
            Log.i("TTSS", "Erro1=" + e.getMessage());
            ToastUtil.DisplayToast(this, Constant.TOAST_MEG_MESSAGE_ERROR);
            e.printStackTrace();
        }
    }

    private void solvePersonCurve(String result){
        try {
            Gson gson = new Gson();
            MyVisionBean data = gson.fromJson(result, MyVisionBean.class);
            if (data != null) {
                visionBeanList = data.getData();
                initLine2();  //折线图
            } else {
                ToastUtil.DisplayToast(this, Constant.TOAST_MEG_ANALYZE_ERROR);
            }
        } catch (Exception e) {
            Log.i("TTSS", "Erro2=" + e.getMessage());
            ToastUtil.DisplayToast(this, Constant.TOAST_MEG_MESSAGE_ERROR);
            e.printStackTrace();
        }
    }

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);

        if (type == Constant.API_REQUEST_SUCCESS) {
            Log.i("TTSS", "个人数据=" + result);
            try {
                Gson gson = new Gson();
                MyFileBean data = gson.fromJson(result, MyFileBean.class);

                if (fileLvAdapter != null) {
                    fileLvAdapter.clearAll();
                }

                if (data != null) {
                    fileBeanList = data.getTest();
                    if (fileBeanList.size() < 3) {
                        fileLvAdapter.addAll(fileBeanList);
                    } else {
                        List<MyFileBean.TestEntity> list = new ArrayList<MyFileBean.TestEntity>();
                        list.add(fileBeanList.get(0));
                        list.add(fileBeanList.get(1));
                        list.add(fileBeanList.get(2));
                        fileLvAdapter.addAll(list);
                    }
                } else {
                    ToastUtil.DisplayToast(this, Constant.TOAST_MEG_ANALYZE_ERROR);
                }
            } catch (Exception e) {
                Log.i("TTSS", "Erro1=" + e.getMessage());
                ToastUtil.DisplayToast(this, Constant.TOAST_MEG_MESSAGE_ERROR);
                e.printStackTrace();
            }

        }

        if (type == Constant.API_REQUEST_SUCCESS) {
            Log.i("TTSS", "家庭数据=" + result);
            try {
                Gson gson = new Gson();
                MyFileBean data = gson.fromJson(result, MyFileBean.class);
                if (fileLvAdapter != null) {
                    fileLvAdapter.clearAll();
                }
                if (data != null) {
                    fileBeanList = data.getTest();
                    if (fileBeanList.size() < 3) {
                        fileLvAdapter.addAll(fileBeanList);
                    } else {
                        List<MyFileBean.TestEntity> list = new ArrayList<MyFileBean.TestEntity>();
                        list.add(fileBeanList.get(0));
                        list.add(fileBeanList.get(1));
                        list.add(fileBeanList.get(2));
                        fileLvAdapter.addAll(list);
                    }
                } else {
                    ToastUtil.DisplayToast(this, Constant.TOAST_MEG_ANALYZE_ERROR);
                }
            } catch (Exception e) {
                Log.i("TTSS", "Erro1=" + e.getMessage());
                ToastUtil.DisplayToast(this, Constant.TOAST_MEG_MESSAGE_ERROR);
                e.printStackTrace();
            }
        }

        if (type == Constant.API_REQUEST_SUCCESS) {
            Log.i("TTSS", "个人折线=" + result);
            try {
                Gson gson = new Gson();
                MyVisionBean data = gson.fromJson(result, MyVisionBean.class);
                if (data != null) {
                    visionBeanList = data.getData();
                    initLine2();  //折线图
                } else {
                    ToastUtil.DisplayToast(this, Constant.TOAST_MEG_ANALYZE_ERROR);
                }
            } catch (Exception e) {
                Log.i("TTSS", "Erro2=" + e.getMessage());
                ToastUtil.DisplayToast(this, Constant.TOAST_MEG_MESSAGE_ERROR);
                e.printStackTrace();
            }
        }

        if (type == Constant.API_REQUEST_SUCCESS) {
            Log.i("TTSS", "家庭折线=" + result);
            try {
                Gson gson = new Gson();
                MyVisionBean data = gson.fromJson(result, MyVisionBean.class);
                if (data != null) {
                    visionBeanList = data.getData();
//                    mHandler.sendEmptyMessage(1);
                    initLine2();  //折线图
                } else {
                    ToastUtil.DisplayToast(this, Constant.TOAST_MEG_ANALYZE_ERROR);
                }
            } catch (Exception e) {
                Log.i("TTSS", "Erro2=" + e.getMessage());
                ToastUtil.DisplayToast(this, Constant.TOAST_MEG_MESSAGE_ERROR);
                e.printStackTrace();
            }
        }
    }

    public void onEventMainThread(EventBean bean){
        if(bean.getAction().equals(EventBean.EVENT_CLOSE_CURRENT_ACTIVITY)){
            base_view.setVisibility(View.VISIBLE);
        }
        if(bean.getAction().equals(EventBean.EVENT_CLOSE_CURRENT_ACTIVITY)){
            base_view.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
