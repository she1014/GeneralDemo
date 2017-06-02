package com.techfly.demo.selfview.photepicker;


import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.techfly.demo.R;
import com.techfly.demo.activity.base.BaseFragmentActivity;
import com.techfly.demo.activity.base.Constant;
import com.techfly.demo.selfview.photepicker.adapter.PhotoGridAdapter;
import com.techfly.demo.selfview.photepicker.adapter.PopupDirectoryListAdapter;
import com.techfly.demo.selfview.photepicker.entity.Photo;
import com.techfly.demo.selfview.photepicker.entity.PhotoDirectory;
import com.techfly.demo.selfview.photepicker.event.OnItemCheckListener;
import com.techfly.demo.selfview.photepicker.event.OnPhotoClickListener;
import com.techfly.demo.selfview.photepicker.utils.ImageCaptureManager;
import com.techfly.demo.selfview.photepicker.utils.MediaStoreHelper;
import com.techfly.demo.util.DisplayUtil;
import com.techfly.demo.util.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PhotoPickerActivity extends BaseFragmentActivity {


    public final static String EXTRA_MAX_COUNT = "MAX_COUNT";
    public final static String EXTRA_SHOW_CAMERA = "SHOW_CAMERA";
    public final static String KEY_SELECTED_PHOTOS = "SELECTED_PHOTOS";
    public final static String EXTRA_SINGLE_SELECT = "SINGLE_SELECT";
    public final static String EXTRA_CAPTURE_RETURN = "CAPTURE_RETURN";
    private ImageCaptureManager captureManager;
    private PhotoGridAdapter photoGridAdapter;
    private PopupDirectoryListAdapter listAdapter;
    private List<PhotoDirectory> directories;
    private TextView ok;
    private boolean captureReturn;
    public final static int DEFAULT_MAX_COUNT = Constant.PHOTO_MAX_COUNT;
    private ImageView titleIcon;
    private int maxCount = DEFAULT_MAX_COUNT;
    //private TextView btSwitchDirectory;
    /**
     * to prevent multiple calls to inflate menu
     */
    private boolean menuIsInflated = false;
    private View headLayout;
    private boolean singleSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_photo_picker);
        setTranslucentStatus(R.color.main_bg);

//        BarTintManger.getBar(this);
        //确定监听键，没选任何图片时不可点击
        ok = (TextView) findViewById(R.id.ok);
        ok.setEnabled(false);
        //获取最大照片数
        maxCount = getIntent().getIntExtra(EXTRA_MAX_COUNT, DEFAULT_MAX_COUNT);
        //获取是否显示相机
        boolean showCamera = getIntent().getBooleanExtra(EXTRA_SHOW_CAMERA, true);
        captureReturn = getIntent().getBooleanExtra(EXTRA_CAPTURE_RETURN, false);
        singleSelect = getIntent().getBooleanExtra(EXTRA_SINGLE_SELECT, false);

        findViewById(R.id.back_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        directories = new ArrayList<>();
        //显示照片的适配器
//        photoGridAdapter = new PhotoGridAdapter(PhotoPickerActivity.this, directories);
        photoGridAdapter = new PhotoGridAdapter(PhotoPickerActivity.this,directories);
        photoGridAdapter.setShowCamera(showCamera);
        if (singleSelect) {
            ok.setVisibility(View.GONE);
            photoGridAdapter.setSingleSelect(true);
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_photos);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, OrientationHelper.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(photoGridAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //初始化popupwindow
        listAdapter = new PopupDirectoryListAdapter(PhotoPickerActivity.this, directories);
        headLayout = findViewById(R.id.top_bar);
        titleIcon = (ImageView) findViewById(R.id.selected_photo_icon);
        View dirview = LayoutInflater.from(PhotoPickerActivity.this).inflate(R.layout.image_select_dirlist, null);
        ListView dirListView = (ListView) dirview.findViewById(R.id.id_list_dirs);

        final PopupWindow popupWindow = new PopupWindow(dirview, ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.getDisplayheightPixels(this) * 2 / 3);
        dirListView.setAdapter(listAdapter);
        dirListView.setEmptyView(dirview.findViewById(R.id.empty_view));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        // 点击“返回Back”也能使其消失
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        titleIcon.setImageResource(R.drawable.navigationbar_arrow_down);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                titleIcon.setImageResource(R.drawable.navigationbar_arrow_down);
            }
        });

        dirListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                popupWindow.dismiss();
                PhotoDirectory directory = directories.get(position);
                //btSwitchDirectory.setText(directory.getName());
                photoGridAdapter.setCurrentDirectoryIndex(position);
                photoGridAdapter.notifyDataSetChanged();
            }
        });

        findViewById(R.id.popup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.showAsDropDown(headLayout, 0, 0);
                titleIcon.setImageResource(R.drawable.navigationbar_arrow_up);
            }
        });

        captureManager = new ImageCaptureManager(PhotoPickerActivity.this);

        MediaStoreHelper.getPhotoDirs(PhotoPickerActivity.this,
                new MediaStoreHelper.PhotosResultCallback() {
                    @Override
                    public void onResultCallback(List<PhotoDirectory> directories) {
                        photoGridAdapter.notifyDataSetChanged();
                        PhotoPickerActivity.this.directories.clear();
                        PhotoPickerActivity.this.directories.addAll(directories);
                        listAdapter.notifyDataSetChanged();
                    }
                });



        //设置选择勾子监听
        photoGridAdapter.setOnItemCheckListener(new OnItemCheckListener() {
            @Override
            public boolean OnItemCheck(int position, Photo photo, final boolean isCheck, int selectedItemCount) {

                int total = selectedItemCount + (isCheck ? -1 : 1);
                ok.setEnabled(total > 0);

                if (maxCount <= 1) {
                    List<Photo> photos = photoGridAdapter.getSelectedPhotos();
                    photos.clear();
                    photoGridAdapter.notifyDataSetChanged();
                    return true;
                }

                //达到选择上限
                if (total > maxCount) {
                    ToastUtil.DisplayToast(getActivity(),getString(R.string.over_max_count_tips));
                    return false;
                }
                // menuDoneItem.setTitle(getString(R.string.done_with_count, total, maxCount));
                return true;
            }
        });

        //设置照片放大监听
        photoGridAdapter.setOnPhotoClickListener(new OnPhotoClickListener() {
            @Override
            public void onClick(View v, int position, boolean showCamera) {
                if (singleSelect) {
                    Intent intent = new Intent();
                    ArrayList<String> selectedPhotos = photoGridAdapter.getSelectedPhotoPaths();
                    intent.putStringArrayListExtra(KEY_SELECTED_PHOTOS, selectedPhotos);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    final int index = showCamera ? position - 1 : position;
                    ArrayList<String> photos = (ArrayList<String>) photoGridAdapter.getCurrentPhotoPaths();
                    Intent intent = new Intent(PhotoPickerActivity.this, PhotoPagerActivity.class);
                    intent.putExtra(PhotoPagerActivity.EXTRA_CURRENT_ITEM, index);
                    intent.putExtra(PhotoPagerActivity.EXTRA_PHOTOS, photos);
                    intent.putExtra(PhotoPagerActivity.EXTRA_EDITABLE, false);
                    startActivity(intent);
                }
            }
        });


        //设置相机点击事件
        photoGridAdapter.setOnCameraClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = captureManager.dispatchTakePictureIntent();
                    startActivityForResult(intent, ImageCaptureManager.REQUEST_TAKE_PHOTO);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                ArrayList<String> selectedPhotos = photoGridAdapter.getSelectedPhotoPaths();
                intent.putStringArrayListExtra(KEY_SELECTED_PHOTOS, selectedPhotos);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ImageCaptureManager.REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            captureManager.galleryAddPic();
            if(captureReturn){
                Intent intent = new Intent();
                intent.putStringArrayListExtra(KEY_SELECTED_PHOTOS, new ArrayList<String>(){{add(captureManager.getCurrentPhotoPath());}});
                setResult(RESULT_OK, intent);
                finish();
            }

            if (singleSelect) {
                Intent intent = new Intent();
                intent.putStringArrayListExtra(KEY_SELECTED_PHOTOS, new ArrayList<String>(){{add(captureManager.getCurrentPhotoPath());}});
                setResult(RESULT_OK, intent);
                finish();
            }

            if (directories.size() > 0) {
                String path = captureManager.getCurrentPhotoPath();
                PhotoDirectory directory = directories.get(MediaStoreHelper.INDEX_ALL_PHOTOS);
                directory.getPhotos().add(MediaStoreHelper.INDEX_ALL_PHOTOS, new Photo(path.hashCode(), path));
                directory.setCoverPath(path);
                photoGridAdapter.notifyDataSetChanged();
            }

        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public PhotoPickerActivity getActivity() {
        return this;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        captureManager.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

}
