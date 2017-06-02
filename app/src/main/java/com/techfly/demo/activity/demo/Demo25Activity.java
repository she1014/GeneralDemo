package com.techfly.demo.activity.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseActivity;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.selfview.photepicker.PhotoPickerActivity;
import com.techfly.demo.selfview.photepicker.utils.PhotoPickerIntent;
import com.techfly.demo.util.LogsUtil;
import com.techfly.demo.util.ShareUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class Demo25Activity extends BaseActivity implements View.OnClickListener{

    private File[] files;
    private List<String> paths = new ArrayList<String>();

    private Button mPhotoPickerBtn;
    private ArrayList<String> curPhoto = null;//当前选择图片路径

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo25);

        // 遍历 SD 卡下 .png 文件通过微信分享，保证SD卡根目录下有.png的文件
        File root = Environment.getExternalStorageDirectory();

        mPhotoPickerBtn = (Button)findViewById(R.id.share_btn2);
        mPhotoPickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPickerIntent intent = new PhotoPickerIntent(Demo25Activity.this);
                intent.setPhotoCount(9);
                startActivityForResult(intent, Constant.REQUESTCODE_NORMAL);
            }
        });
        
        files = root.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (pathname.getName().endsWith(".JPEG") || pathname.getName().endsWith(".png") || pathname.getName().endsWith(".jpg")){
                	return true;
                }
                return false;
            }
        });
        
        for(File file :files){
            paths.add(file.getAbsolutePath());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        if (requestCode == Constant.REQUESTCODE_NORMAL) {
            curPhoto = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
            LogsUtil.normal("size=" + curPhoto.size() + ",toString=" + curPhoto.toString());
            if(curPhoto.size() > 0){
                ShareUtils.share9PicsToWXCircle(this, "你好，成功的分享了多张照片到微信", curPhoto);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.share_btn1:
                if(paths == null || paths.size() == 0){
                    Toast.makeText(this,"SD卡根目录下无.png格式照片",Toast.LENGTH_SHORT).show();//分享到朋友圈
                }else{
//                    ShareUtils.share9PicsToWXCircle(this, "你好，成功的分享了多张照片到微信", paths);
                    ShareUtils.sharePicToFriendNoSDK(this, paths.get(0));//分享到朋友
                }
                break;
            
        }
    }
}
