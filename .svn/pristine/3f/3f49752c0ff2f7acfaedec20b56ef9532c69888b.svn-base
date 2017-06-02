package com.techfly.demo.selfview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techfly.demo.R;
import com.techfly.demo.util.DensityUtil;


/**
 * Created by ssm on 2015/12/31.
 */
public class UMImageButtonShareItem extends LinearLayout {


    private ImageView imageViewbutton;


    private TextView textView;


    public UMImageButtonShareItem(Context context,AttributeSet attrs) {
        super(context, attrs);

        imageViewbutton = new ImageView(context, attrs);
        imageViewbutton.setPadding(DensityUtil.dip2px(context,16), DensityUtil.dip2px(context,16), DensityUtil.dip2px(context,16), DensityUtil.dip2px(context,16));

        LayoutParams layoutParams1 = new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams1.setMargins(-20, -20,-20, -20);//左上右下
        imageViewbutton.setLayoutParams(layoutParams1);


        textView =new TextView(context, attrs);
        //水平居中
        textView.setGravity(android.view.Gravity.CENTER_HORIZONTAL);
        textView.setPadding(0, 0, 0, 0);
        textView.setTextSize(13);
        textView.setTextColor(getResources().getColor(R.color.text_font_black));

        /*LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams2.setMargins(0, 0,0, 0);//左上右下
        textView.setLayoutParams(layoutParams2);*/

        setClickable(true);
        setFocusable(true);
        setOrientation(LinearLayout.VERTICAL);
        addView(imageViewbutton);
        addView(textView);
    }


    public ImageView getImageViewbutton() {
        return imageViewbutton;
    }


    public void setImageViewbutton(ImageView imageViewbutton) {
        this.imageViewbutton = imageViewbutton;
    }
}