package com.techfly.demo.selfview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import com.techfly.demo.R;


/**
 * Created by User on 2015/8/10.
 * zhangyan
 * 限制最大行数
 */
public class LimitMaxLineEditText extends EditText {

    private  int MAX_LINE=2;

    public LimitMaxLineEditText(Context context) {
        super(context);
        limitMaxLines();
    }

    public LimitMaxLineEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        limitMaxLines();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LimitMaxLineEditText);

        int N = ta.getIndexCount();
        for (int i = 0; i < N; i++) {
			/*
			 * 获得某个属性的ID值
			 */
            int itemId = ta.getIndex(i);
            switch (itemId) {
                case R.styleable.LimitMaxLineEditText_limitlines:
                    MAX_LINE=ta.getInt(itemId, MAX_LINE);
                    break;
                default:
                    break;
            }
        }
    }
    public LimitMaxLineEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        limitMaxLines();
    }

    ///////限制最大行数
    public void limitMaxLines(){
        LimitMaxLineEditText.this.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int lines =  LimitMaxLineEditText.this.getLineCount();
                // 限制最大输入行数
                if (lines > MAX_LINE) {
                    String str = s.toString();
                    int cursorStart =  LimitMaxLineEditText.this.getSelectionStart();
                    int cursorEnd =  LimitMaxLineEditText.this.getSelectionEnd();
                    if (cursorStart == cursorEnd && cursorStart < str.length() && cursorStart >= 1) {
                        str = str.substring(0, cursorStart-1) + str.substring(cursorStart);
                    } else {
                        str = str.substring(0, s.length()-1);
                    }
                    // setText会触发afterTextChanged的递归
                    LimitMaxLineEditText.this.setText(str);
                    // setSelection用的索引不能使用str.length()否则会越界
                    LimitMaxLineEditText.this.setSelection( LimitMaxLineEditText.this.getText().length());
                }
        }});

    }

}
