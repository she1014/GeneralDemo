package com.techfly.demo.bean;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.techfly.demo.activity.demo.Demo22Activity;
import com.techfly.demo.util.ToastUtil;

public class JavaScriptObject {
    Context mContxt;

    public JavaScriptObject(Context mContxt) {
        this.mContxt = mContxt;
    }

    @JavascriptInterface//添加此注解 
    public void fun1FromAndroid(String name) {
        Toast.makeText(mContxt, name, Toast.LENGTH_LONG).show();
    }

    public void fun2(String name) {
        Toast.makeText(mContxt, "调用fun2:" + name, Toast.LENGTH_SHORT).show();
    }
    
    @JavascriptInterface//添加此注解 
    public void fun2FromAndroid(String name,String phone,String email,String city) {

        ToastUtil.DisplayToast(mContxt,name+","+phone+","+email+","+city);

        Intent intent = new Intent(mContxt, Demo22Activity.class);
        mContxt.startActivity(intent);
//        mContxt.startActivityForResult(intent, 1);
    }
}
