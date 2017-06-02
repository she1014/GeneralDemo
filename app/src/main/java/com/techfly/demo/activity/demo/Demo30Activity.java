package com.techfly.demo.activity.demo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.techfly.demo.R;
import com.techfly.demo.adpter.RecyclerViewAdapter;
import com.techfly.demo.util.ToastUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Demo30Activity extends Activity {
    private Bitmap bitmap;
    private Button button;
    private Button ripplebutton;
    private RecyclerView recyclerview;
    private Toolbar toolbar;

    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 0;
    private Uri fileUri = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo30);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.recyclerview = (RecyclerView) findViewById(R.id.recycler_view);
        this.ripplebutton = (Button) findViewById(R.id.ripple_button);
        this.button = (Button) findViewById(R.id.button);
        // 设置Logo
        toolbar.setLogo(R.drawable.ic_launcher);
        // 设置标题
        toolbar.setTitle("Android5.0");
        // 设置子标题
        toolbar.setSubtitle("新控件");
        //设置ActionBar，之后就可以获取ActionBar并进行操作，操作的结果就会反应在toolbar上面
        setActionBar(toolbar);
        //设置了返回箭头，相当于设置了toolbar的导航按钮
        getActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i("NavigationOnClickListener","点击了返回箭头");
                ToastUtil.DisplayToast(Demo30Activity.this,"点击了返回箭头");
                finish();
            }
        });
        List<String> list=new ArrayList<>();
        for(int i=0;i<40;i++){
            list.add("数据"+(i+1));
        }

        //新建一个线性布局管理器
        LinearLayoutManager manager=new LinearLayoutManager(this);
        //设置recyclerview的布局管理器
        recyclerview.setLayoutManager(manager);
        //新建适配器
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(this,list);
        //设置recyclerview的适配器
        recyclerview.setAdapter(adapter);

        ripplebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//                try {
//                  fileUri = Uri.fromFile(createMediaFile()); // create a file to save the video
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);  // set the image file name
//                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); // set the video image quality to high
//                // start the Video Capture Intent
//                startActivityForResult(intent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);

                Intent mIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                mIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                startActivityForResult(mIntent, CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE) {
            // 录制视频完成
            try {
                AssetFileDescriptor videoAsset = getContentResolver()
                        .openAssetFileDescriptor(intent.getData(), "r");
                FileInputStream fis = videoAsset.createInputStream();
                File tmpFile = new File(
                        Environment.getExternalStorageDirectory(),
                        "recordvideo.mp4");
                FileOutputStream fos = new FileOutputStream(tmpFile);

                byte[] buf = new byte[1024];
                int len;
                while ((len = fis.read(buf)) > 0) {
                    fos.write(buf, 0, len);
                }
                fis.close();
                fos.close();
                // 文件写完之后删除/sdcard/dcim/CAMERA/XXX.MP4
                deleteDefaultFile(intent.getData());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 删除在/sdcard/dcim/Camera/默认生成的文件
    private void deleteDefaultFile(Uri uri) {
        String fileName = null;
        if (uri != null) {
            // content
            Log.d("Scheme", uri.getScheme().toString());
            if (uri.getScheme().toString().equals("content")) {
                Cursor cursor = this.getContentResolver().query(uri, null,
                        null, null, null);
                if (cursor.moveToNext()) {
                    int columnIndex = cursor
                            .getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
                    fileName = cursor.getString(columnIndex);
                    //获取缩略图id
                    /*int id = cursor.getInt(cursor
                            .getColumnIndex(VideoColumns._ID));
                    //获取缩略图
                    bitmap = Thumbnails.getThumbnail(
                            getContentResolver(), id, Thumbnails.MICRO_KIND,
                            null);*/

                    if (!fileName.startsWith("/mnt")) {
                        fileName = "/mnt/" + fileName;
                    }
                    Log.d("fileName", fileName);
                }
            }
        }
        // 删除文件
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
            Log.d("delete", "删除成功");
        }
    }

    /**
     * 改变ToolBar的背景颜色
     */
    public void changeToolbarBg(View view){
        // 获取应用程序图标的Bitmap
        bitmap= BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        // 通过bitmap生成调色板palette
        Palette palette=Palette.from(bitmap).generate();
        // 获取palette充满活力色颜色
        int vibrantColor=palette.getVibrantColor(Color.WHITE);
        // 设置toolbar的背景颜色为vibrantColor
        this.toolbar.setBackgroundColor(vibrantColor);

    }


}
