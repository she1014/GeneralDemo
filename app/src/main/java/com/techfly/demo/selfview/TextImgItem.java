package com.techfly.demo.selfview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.techfly.demo.R;


/**
 * Created by ssm on 2015/12/31.
 */
public class TextImgItem extends LinearLayout {


    private ImageView imageViewbutton;


    private TextView textView;


    public TextImgItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        imageViewbutton = new ImageView(context, attrs);
//        imageViewbutton.setPadding(DensityUtil.dip2px(16), 20, DensityUtil.dip2px(16), 20);
        imageViewbutton.setPadding(10, 10, 10, 10);

        textView = new TextView(context, attrs);
        textView.setGravity(android.view.Gravity.CENTER_HORIZONTAL);
        textView.setPadding(0, 0, 0, 0);
        textView.setTextSize(16);
        textView.setTextColor(getResources().getColor(R.color.text_font_black));

        setClickable(true);
        setFocusable(true);
        setOrientation(LinearLayout.HORIZONTAL);
        addView(textView);
        addView(imageViewbutton);

    }

    public void setText(String content){
        textView.setText(content);
        imageViewbutton.setVisibility(View.GONE);
    }

    public String getText(){
        return textView.getText().toString();
    }

    public ImageView getImageViewbutton() {
        return imageViewbutton;
    }


    public void setImageViewbutton(ImageView imageViewbutton) {
        this.imageViewbutton = imageViewbutton;
    }
}