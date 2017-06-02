package com.techfly.demo.activity.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.activity.base.MainActivity;
import com.techfly.demo.util.ToastUtil;

public class Demo17Activity extends BaseActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText etUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo17);
        Log.v(TAG, "onCreate");

        initBaseView();
        setBaseTitle("Demo17", 0);

        Log.e(TAG, "i am on patch log");
        etUserName = (EditText) findViewById(R.id.et_user_name);

        findViewById(R.id.btn_show_name).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etUserName.getText().toString();
                ToastUtil.DisplayToast(getApplicationContext(), "输入的名字为new:" + name);
            }
        });

        /*findViewById(R.id.btn_load_patch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String patchPath  = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/patch_signed_7zip.apk";
                File file = new File(patchPath);
                if (file.exists()) {
                    Log.v(TAG, "补丁文件存在");
                    ToastUtil.DisplayToast(getApplicationContext(), "补丁文件存在");
                    TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), patchPath);
                } else {
                    ToastUtil.DisplayToast(getApplicationContext(), "补丁文件不存在");
                    Log.v(TAG,"补丁文件不存在");
                }
            }
        });

        findViewById(R.id.btn_remove_patch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.DisplayToast(getApplicationContext(), "移除patch");
                Tinker.with(getApplicationContext()).cleanPatch();
            }
        });*/

    }

}
