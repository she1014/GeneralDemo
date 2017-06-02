package com.techfly.demo.activity.demo;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.gson.Gson;
import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.bean.GroupCurveBean;
import com.techfly.demo.bean.VisionBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


/**
 * 群组曲线->视力状态分布图
 */
public class Demo13Activity extends BaseActivity implements OnChartValueSelectedListener{

    private List<VisionBean> visionBeanList = new ArrayList<VisionBean>();

    private LineChart mChart;

    private BarChart barChart;
    private BarData barData;
    private Typeface mTf;

    private PieChart pieChart;

    private String m_id = "";
    private List<String> valueList = new ArrayList<String>();
    private int m_tatal = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo13);

        ButterKnife.inject(this);
        initBaseView();
        initBackButton(R.id.top_back_iv);

        setBaseTitle("Demo13", 0);
        setTranslucentStatus(R.color.main_bg);

        loadIntent();

        loadData();
    }

    private void loadIntent(){
        m_id = getIntent().getStringExtra(Constant.CONFIG_INTENT_ID);
    }

    private void loadData(){
        String result = "{\"status\":\"ok\",\"detail\":[\"3\",\"1\",\"2\",\"4\",\"7\"],\"total\":17}";
        try{
            Gson gson = new Gson();
            GroupCurveBean data = gson.fromJson(result,GroupCurveBean.class);
            if(data != null){
                valueList = data.getDetail();
                m_tatal = data.getTotal();
                initBar();   //柱状图
                initPie();   //饼状图
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initBar(){
        barChart = (BarChart) findViewById(R.id.bar_chart);
        barData = getBarData(5, 100);
        showBarChart(barChart, barData);

    }

    private void showBarChart(BarChart barChart, BarData barData) {
        barChart.setDrawBorders(false);  ////是否在折线图上添加边框

        barChart.setDescription("");// 数据描述

        // 如果没有数据的时候，会显示这个，类似ListView的EmptyView
        barChart.setNoDataTextDescription("当前无数据");

        barChart.setDrawGridBackground(false); // 是否显示表格颜色
        barChart.setGridBackgroundColor(Color.WHITE & 0x70FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度

        barChart.setTouchEnabled(true); // 设置是否可以触摸

        barChart.setDragEnabled(true);// 是否可以拖拽
        barChart.setScaleEnabled(true);// 是否可以缩放

        barChart.setPinchZoom(false);//

//      barChart.setBackgroundColor();// 设置背景

        barChart.setDrawBarShadow(true);

        barChart.setData(barData); // 设置数据

        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setAxisMinValue(0);
        leftAxis.setAxisMaxValue(100);
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setDrawGridLines(true);
        leftAxis.setLabelCount(11, true);
        leftAxis.setSpaceBottom(0);

        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setTextColor(ColorTemplate.getHoloBlue());
        rightAxis.setAxisMinValue(0);
        rightAxis.setAxisMaxValue(100);
        rightAxis.setStartAtZero(false);
        rightAxis.setTextColor(Color.TRANSPARENT);
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(10, true);
        rightAxis.setSpaceBottom(0);

        Legend mLegend = barChart.getLegend(); // 设置比例图标示
        mLegend.setFormSize(20f);
//        mLegend.setForm(LegendForm.CIRCLE);// 样式
        mLegend.setForm(Legend.LegendForm.SQUARE);
        mLegend.setFormSize(6f);// 字体
        mLegend.setTextColor(Color.BLACK);// 颜色

//      X轴设定
        XAxis xAxis = barChart.getXAxis();
//        xAxis.setPosition(XAxisPosition.BOTTOM);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.animateX(0); // 立即执行的动画,x轴
//        barChart.animateX(2500); // 立即执行的动画,x轴
    }

    private BarData getBarData(int count, float range) {

        String[] xArray = new String[]{"0~0.1","0.1~0.3","0.3~0.8","0.8~1.0","1.0以上"};

        String[] yArray = new String[5];
        for(int i = 0;i < yArray.length;i++){
//            Log.i("TEST",i+"==>"+ valueList.get(i) + ",m_tatal=" + m_tatal);
            yArray[i] = (Double.parseDouble(valueList.get(i))/m_tatal * 100) + "";
//            Log.i("TEST",i+"==>"+ yArray[i]);
        }

        ArrayList<String> xValues = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xValues.add(xArray[i]);
        }

        ArrayList<BarEntry> yValues = new ArrayList<BarEntry>();

        for (int i = 0; i < count; i++) {
//            float value = (float) (Math.random() * range/*100以内的随机数*/) + 3;
            yValues.add(new BarEntry(Float.parseFloat(yArray[i]), i));
        }

        // y轴的数据集合
        BarDataSet barDataSet = new BarDataSet(yValues, "柱状分布图");

//        barDataSet.setColor(Color.rgb(114, 188, 223));
        barDataSet.setColor(Color.rgb(0, 164, 150));

        ArrayList<BarDataSet> barDataSets = new ArrayList<BarDataSet>();
        barDataSets.add(barDataSet); // add the datasets

        BarData barData = new BarData(xValues, barDataSets);

        return barData;
    }

    private void initPie(){
        pieChart = (PieChart)findViewById(R.id.pie_chart);
        PieData mPieData = getPieData(5, 100);
        showChart(pieChart, mPieData);
    }

    private void showChart(PieChart pieChart, PieData pieData) {
        pieChart.setHoleColorTransparent(true);

        pieChart.setHoleRadius(50f);  //半径
        pieChart.setTransparentCircleRadius(54f); // 半透明圈
//        pieChart.setHoleRadius(60f);  //半径
//        pieChart.setTransparentCircleRadius(64f); // 半透明圈

        //pieChart.setHoleRadius(0)  //实心圆
        pieChart.setDescription("视力分布饼状图");
        // mChart.setDrawYValues(true);
        pieChart.setDrawCenterText(true);  //饼状图中间可以添加文字
        pieChart.setDrawHoleEnabled(true);
        pieChart.setRotationAngle(90); // 初始旋转角度
        // draws the corresponding description value into the slice
        // mChart.setDrawXValues(true);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true); // 可以手动旋转
        // display percentage values
        pieChart.setUsePercentValues(true);  //显示成百分比
        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);
        // add a selection listener
//      mChart.setOnChartValueSelectedListener(this);
//         mChart.setTouchEnabled(true);
//      mChart.setOnAnimationListener(this);
        pieChart.setCenterText("视力分布情况");  //饼状图中间的文字

        //设置数据
        pieChart.setData(pieData);

        // undo all highlights
//      pieChart.highlightValues(null);
//      pieChart.invalidate();

        Legend mLegend = pieChart.getLegend();  //设置比例图
//        mLegend.setPosition(LegendPosition.RIGHT_OF_CHART);  //最右边显示
        mLegend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
//      mLegend.setForm(LegendForm.LINE);  //设置比例图的形状，默认是方形
        mLegend.setXEntrySpace(7f);
        mLegend.setYEntrySpace(-3f);

        pieChart.animateXY(0, 0);  //设置动画
//        pieChart.animateXY(1000, 1000);  //设置动画
        // mChart.spin(2000, 0, 360)
    }

    /**
     *
     * @param count 分成几部分
     * @param range
     */
    private PieData getPieData(int count, float range) {

        String[] levelArray = new String[]{"0~0.1:防盲","0.1~0.3:重度","0.3~0.8:中度","0.8~1.0:轻度","1.0以上:正常"};

        ArrayList<String> xValues = new ArrayList<String>();  //xVals用来表示每个饼块上的内容

        for (int i = 0; i < count; i++) {
//            xValues.add(levelArray[i]);

            if(Integer.parseInt(valueList.get(i)) != 0){
                xValues.add(levelArray[i]);
            }

//            xValues.add("Quarterly" + (i + 1));  //饼块上显示成Quarterly1, Quarterly2, Quarterly3, Quarterly4
        }

        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据

        // 饼图数据
        /**
         * 将一个饼形图分成四部分， 四部分的数值比例为14:14:34:38
         * 所以 14代表的百分比就是14%
         */
        /*float quarterly1 = 10;
        float quarterly2 = 10;
        float quarterly3 = 20;
        float quarterly4 = 30;
        float quarterly5 = 30;*/
        float quarterly1 = Integer.parseInt(valueList.get(0));
        float quarterly2 = Integer.parseInt(valueList.get(1));
        float quarterly3 = Integer.parseInt(valueList.get(2));
        float quarterly4 = Integer.parseInt(valueList.get(3));
        float quarterly5 = Integer.parseInt(valueList.get(4));

        for(int i = 0;i < valueList.size();i++){
            Log.i("TTSS",i+"=视力分布=>"+valueList.get(i));
        }

        if(quarterly1 != 0){
            yValues.add(new Entry(quarterly1, 0));
        }
        if(quarterly2 != 0){
            yValues.add(new Entry(quarterly2, 1));
        }
        if(quarterly3 != 0){
            yValues.add(new Entry(quarterly3, 2));
        }
        if(quarterly4 != 0){
            yValues.add(new Entry(quarterly4, 3));
        }
        if(quarterly5 != 0){
            yValues.add(new Entry(quarterly5, 4));
        }


        /*yValues.add(new Entry(quarterly1, 0));
        yValues.add(new Entry(quarterly2, 1));
        yValues.add(new Entry(quarterly3, 2));
        yValues.add(new Entry(quarterly4, 3));
        yValues.add(new Entry(quarterly5, 4));*/

        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, ""/*显示在比例图上*/);
        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离

        ArrayList<Integer> colors = new ArrayList<Integer>();

        // 饼图颜色
        /*colors.add(Color.rgb(205, 205, 205));
        colors.add(Color.rgb(114, 188, 223));
        colors.add(Color.rgb(255, 123, 124));
        colors.add(Color.rgb(57, 135, 200));
        colors.add(Color.rgb(3, 189, 34));*/
        if(quarterly1 != 0){
//            yValues.add(new Entry(quarterly1, 0));
            colors.add(Color.rgb(205, 205, 205));
        }
        if(quarterly2 != 0){
//            yValues.add(new Entry(quarterly2, 1));
            colors.add(Color.rgb(114, 188, 223));
        }
        if(quarterly3 != 0){
//            yValues.add(new Entry(quarterly3, 2));
            colors.add(Color.rgb(255, 123, 124));
        }
        if(quarterly4 != 0){
//            yValues.add(new Entry(quarterly4, 3));
            colors.add(Color.rgb(57, 135, 200));
        }
        if(quarterly5 != 0){
//            yValues.add(new Entry(quarterly5, 4));
            colors.add(Color.rgb(3, 189, 34));
        }

        pieDataSet.setColors(colors);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
//        float px = 5 * (metrics.densityDpi / 160f);
        float px = 5 * (metrics.densityDpi / 150f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度

        PieData pieData = new PieData(xValues, pieDataSet);

        return pieData;
    }




    private void initLine(){
        mChart = (LineChart) findViewById(R.id.line_chart);
        mChart.setOnChartValueSelectedListener(Demo13Activity.this);
        // no description text
        mChart.setDescription("视力变化情况");
        mChart.setNoDataTextDescription("当前无数据");
        mChart.setTouchEnabled(true);
        mChart.setDragDecelerationFrictionCoef(0.9f);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
//        mChart.setDrawGridBackground(false);

        mChart.setDrawGridBackground(false);
        mChart.setGridBackgroundColor(Color.CYAN);

        mChart.setHighlightPerDragEnabled(true);
        mChart.setPinchZoom(true);
        // set an alternative background color
        mChart.setBackgroundColor(Color.WHITE);
        mChart.animateX(2500);

        setData(7);    //加载数据

        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        l.setTextSize(11f);
        l.setTextColor(Color.BLACK);
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(9f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(true);
        xAxis.setDrawAxisLine(true);
        xAxis.setSpaceBetweenLabels(1);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setAxisMinValue(0.1f);
        leftAxis.setAxisMaxValue(1.5f);
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setDrawGridLines(true);
        leftAxis.setLabelCount(14, true);
        leftAxis.setSpaceBottom(0);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setTextColor(ColorTemplate.getHoloBlue());
        rightAxis.setAxisMinValue(4.0f);
        rightAxis.setAxisMaxValue(5.2f);
        rightAxis.setStartAtZero(false);
        rightAxis.setTextColor(Color.BLACK);
        rightAxis.setDrawGridLines(false);
        rightAxis.setLabelCount(14, true);
        rightAxis.setSpaceBottom(0);

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
        xVals.add("星期一");
        xVals.add("星期二");
        xVals.add("星期三");
        xVals.add("星期四");
        xVals.add("星期五");
        xVals.add("星期六");
        xVals.add("星期日");

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        for (int i = 0; i < xVals.size(); i++) {
            float value = (float) (Math.random() * 1.5f + 0.0f);
//            yVals1.add(new Entry(value, i));
//            Log.i("TTSS", i + "=>vlues=>" + visionBeanList.get(i).getL_ly());
            yVals1.add(new Entry(Float.parseFloat(visionBeanList.get(i).getL_ly()), i));
        }
        LineDataSet set1 = new LineDataSet(yVals1, "视力曲线图");


        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
//        set1.setColor(ColorTemplate.getHoloBlue());
//        set1.setCircleColor(Color.YELLOW);
        set1.setLineWidth(1.5f);
//        set1.setCircleSize(3f);
        set1.setFillAlpha(65);
        set1.setFillColor(ColorTemplate.getHoloBlue());
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setDrawCircleHole(false);  //是否实心圆点

        // 显示的圆形大小
        set1.setCircleSize(4.0f);
        // 折线的颜色
        set1.setColor(getResources().getColor(R.color.blue));
        // 圆球的颜色
        set1.setCircleColor(getResources().getColor(R.color.blue));

        LineData data = new LineData(xVals, set1);
        data.setValueTextColor(getResources().getColor(R.color.blue));
        data.setValueTextSize(9f);

        // set data
        mChart.setData(data);
    }

    @Override
    public void getResult(String result, int type) {
        super.getResult(result, type);
//        result = "{\"status\":\"ok\",\"detail\":[\"2\",\"3\",\"1\",\"3\",\"1\"],\"total\":3}";
        if(type == Constant.API_REQUEST_SUCCESS){
            try{
                Gson gson = new Gson();
                GroupCurveBean data = gson.fromJson(result,GroupCurveBean.class);
                if(data != null){
                    valueList = data.getDetail();
                    m_tatal = data.getTotal();
                    initBar();   //柱状图
                    initPie();   //饼状图
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
